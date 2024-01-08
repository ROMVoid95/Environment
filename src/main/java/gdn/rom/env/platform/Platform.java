package gdn.rom.env.platform;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.Locale;

import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import gdn.rom.env.platform.property.IBMSystemProperties;
import gdn.rom.env.platform.property.SunSystemProperties;
import gdn.rom.env.platform.property.SystemProperties;
import gdn.rom.env.platform.property.type.IntProperty;
import gdn.rom.env.util.Checks;
import lombok.experimental.UtilityClass;

/**
 * Contains constants and methods for accessing platform information.
 */
@UtilityClass
public final class Platform
{
    /**
     * The current {@link OS}.
     */
    public static final OS            CURRENT_OS                = getOS();

    /**
     * The current {@link OSVersion}.
     */
    public static final OSVersion     CURRENT_OS_VERSION        = CURRENT_OS.isVersionNumberInOSName() ? CURRENT_OS.getVersionByName(SystemProperties.osName.get()) : CURRENT_OS.getVersionByVersionNumber(SystemProperties.osVersion.get());

    /**
     * Whether the current {@link OS} is either {@link OS#WINDOWS} or {@link OS#WINDOWS_CE}.
     */
    public static final boolean       IS_WINDOWS_OR_WINDOWS_CE  = CURRENT_OS.isWindowsOrWindowsCE();

    /**
     * The current JVM {@link Architecture}.
     */
    public static final Architecture  JVM_ARCHITECTURE;

    /**
     * The JRE directory.
     */
    public static final Path          JRE_DIRECTORY             = Checks.checkNotNull(SystemProperties.javaInstallationDirectory.get(), "java.home should not be null");

    /**
     * The JRE {@code bin} directory.
     */
    public static final Path          JRE_BIN_DIRECTORY         = JRE_DIRECTORY.resolve("bin");

    /**
     * The {@code java} executable.
     */
    public static final Path          JAVA_EXECUTABLE           = getJREExecutable("java");

    /**
     * The {@code javaw} executable.
     */
    public static final Path          JAVAW_EXECUTABLE          = getJREExecutable("javaw");

    /**
     * The current working directory, i.e. the directory in which {@code java} or {@code javaw}
     * was started.
     */
    public static final Path          CURRENT_WORKING_DIRECTORY = SystemProperties.userCurrentWorkingDirectory.get(Paths.get(".").toAbsolutePath());

    private static final TaggedLogger logger                    = Logger.tag("Environment.Platform");

    static
    {
        final IntProperty dataModel = SunSystemProperties.architectureDataModel.hasValue() ? SunSystemProperties.architectureDataModel : IBMSystemProperties.vmBitMode;

        if (dataModel.valueEquals(32))
        {
            JVM_ARCHITECTURE = Architecture.THIRTY_TWO_BIT;
        } else if (dataModel.valueEquals(64))
        {
            JVM_ARCHITECTURE = Architecture.SIXTY_FOUR_BIT;
        } else
        {
            // Fall back to os.arch.
            JVM_ARCHITECTURE = Architecture.fromName(SystemProperties.jvmArchitecture.get());
        }
    }

    /**
     * Returns the JRE executable with the specified file name.
     *
     * @param  name
     *                  a JRE executable file name.
     * 
     * @return      a {@link Path} that represents the JRE executable with the specified file name.
     */
    public static Path getJREExecutable(String name)
    {
        Checks.checkNotNull(name, "name should not be null");
        name = name.toLowerCase(Locale.ENGLISH);
        Checks.checkArgument(name.matches("[a-z]+"), "name should be alphabetic");
        return JRE_BIN_DIRECTORY.resolve(CURRENT_OS == OS.WINDOWS ? name + ".exe" : name);
    }

    /**
     * Returns the MAC address in lowercase of the local machine with each group separated by
     * a colon.
     *
     * @return the MAC address in lowercase of the local machine with each group separated by
     *         a colon, or {@code null} if it cannot be retrieved.
     */

    public static String getMACAddress()
    {
        return getMACAddress(':');
    }

    /**
     * Returns the MAC address in lowercase of the local machine with each group separated by the
     * specified character.
     *
     * @param  separator
     *                       a separator character.
     * 
     * @return           the MAC address in lowercase of the local machine with each group separated by the
     *                   specified character, or {@code null} if it cannot be retrieved.
     */

    public static String getMACAddress(char separator)
    {
        return getMACAddress(String.valueOf(separator));
    }

    /**
     * Returns the MAC address in lowercase of the local machine with each group separated by the
     * specified string.
     *
     * @param  separator
     *                       a separator string.
     * 
     * @return           the MAC address in lowercase of the local machine with each group separated by the
     *                   specified string, or {@code null} if it cannot be retrieved.
     */

    public static String getMACAddress(String separator)
    {
        try
        {
            final StringBuilder macAddress = new StringBuilder();

            final InetAddress localHost = InetAddress.getLocalHost();
            final NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);

            if (networkInterface == null)
            {
                logger.warn("Failed to retrieve MAC address of local machine");
                return null;
            }

            for (byte b : networkInterface.getHardwareAddress())
            {
                // Format each byte as a hexadecimal number.
                macAddress.append(String.format("%02x", b)).append(separator);
            }

            return macAddress.substring(0, macAddress.length() - separator.length());
        } catch (UnknownHostException | SocketException ex)
        {
            logger.warn("Failed to retrieve MAC address of local machine", ex);
        }

        return null;
    }

    /**
     * Returns the path to the base location of the specified class.
     * <p>
     * If the class is directly on the filesystem, then the path to the base directory is returned.
     * For example, if the path to the class is {@code /path/to/my/package/MyClass.class},
     * {@code /path/to} is returned.
     * <p>
     * If the class is inside a JAR file, the path to the JAR is returned.
     *
     * @param  clazz
     *                   a class.
     * 
     * @return       the path to the location of the specified class. If a valid path cannot be found,
     *               {@code null} is returned.
     */

    public static Path getClassLocation(Class<?> clazz)
    {
        Checks.checkNotNull(clazz, "clazz should not be null");

        // Taken from: https://stackoverflow.com/a/12733172

        final URL url = getClassURL(clazz);

        if (url == null)
        {
            return null;
        }

        final String path = url.toExternalForm();
        String correctedPath = path;

        // Change initial file: to file:/ if necessary, for example, file:C:\dir => file:/C:\dir.
        if (IS_WINDOWS_OR_WINDOWS_CE && path.matches("file:[A-Za-z]:.*"))
        {
            correctedPath = "file:/" + path.substring(5);
        }

        try
        {
            return Paths.get(new URL(correctedPath).toURI());
        } catch (MalformedURLException | URISyntaxException ignored)
        {
        }

        // Try again, but without the "file:" prefix.
        if (path.startsWith("file:"))
        {
            return Paths.get(path.substring(5));
        }

        logger.warn("Failed to convert class location URL to path: {}", url);
        return null;
    }

    private static URL getClassURL(Class<?> clazz)
    {
        try
        {
            final CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();

            if (codeSource != null)
            {
                final URL location = codeSource.getLocation();

                if (location != null)
                {
                    return location;
                }
            }
        } catch (SecurityException ignored)
        {
            // This occurs when we aren't allowed to retrieve the ProtectionDomain.
        }

        // We ask for the class itself as a resource, then strip the class's path from the URL.
        final URL resource = clazz.getResource(clazz.getSimpleName() + ".class");

        if (resource == null)
        {
            // The class resource cannot be found, so we give up.
            return null;
        }

        final String url = resource.toExternalForm();
        final String canonicalName = clazz.getCanonicalName();

        if (canonicalName == null)
        {
            return null;
        }

        final String suffix = canonicalName.replace('.', '/') + ".class";

        if (!url.endsWith(suffix))
        {
            // The URL is in an unknown format, so we give up.
            logger.warn("Class location URL is in an unknown format: {}", url);
            return null;
        }

        String base = url.substring(0, url.length() - suffix.length());

        // We remove the "jar:" prefix and "!/" suffix if they are present.
        if (base.startsWith("jar:"))
        {
            base = base.substring(4, base.length() - 2);
        }

        try
        {
            return new URL(base);
        } catch (MalformedURLException ex)
        {
            logger.warn("Class location URL is in an invalid format: {}", base, ex);
        }

        return null;
    }

    private static OS getOS()
    {
        final OS os = OS.fromName(SystemProperties.osName.get());

        if (os == OS.LINUX && SystemProperties.jvmName.stringEquals("Dalvik"))
        {
            return OS.ANDROID;
        }

        return os;
    }
}
