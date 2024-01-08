package gdn.rom.env.platform;

import java.util.HashSet;
import java.util.Set;

import lombok.experimental.UtilityClass;

/**
 * Contains {@link OSVersion}s that represent macOS or Mac OS X versions that can run JRE 8
 * or newer.
 */
@UtilityClass
public class MacOSVersion
{
    /**
     * Mac OS X 10.8 Mountain Lion.
     */
    public static final OSVersion       MOUNTAIN_LION = get("Mountain Lion", 8);

    /**
     * Mac OS X 10.9 Mavericks.
     */
    public static final OSVersion       MAVERICKS     = get("Mavericks", 9);

    /**
     * Mac OS X 10.10 Yosemite.
     */
    public static final OSVersion       YOSEMITE      = get("Yosemite", 10);

    /**
     * Mac OS X 10.11 El Capitan.
     */
    public static final OSVersion       EL_CAPITAN    = get("El Capitan", 11);

    /**
     * macOS 10.12 Sierra.
     */
    public static final OSVersion       SIERRA        = get("Sierra", 12);

    /**
     * macOS 10.13 High Sierra.
     */
    public static final OSVersion       HIGH_SIERRA   = get("High Sierra", 13);

    /**
     * macOS 10.14 Mojave.
     */
    public static final OSVersion       MOJAVE        = get("Mojave", 14);

    /**
     * macOS 10.15 Catalina.
     */
    public static final OSVersion       CATALINA      = get("Catalina", 15);

    private static final Set<OSVersion> versions      = Set.of(MOUNTAIN_LION, MAVERICKS, YOSEMITE, EL_CAPITAN, SIERRA, HIGH_SIERRA, MOJAVE, CATALINA);

    /**
     * Returns all {@link OSVersion} instances that represent known macOS/Mac OS X versions.
     *
     * @return a mutable {@link Set} containing all {@link OSVersion} instances that represent
     *         known macOS/Mac OS X versions.
     */
    public static Set<OSVersion> getVersions()
    {
        return new HashSet<>(versions);
    }

    private static OSVersion get(String name, int versionNumber)
    {
        return new OSVersion((versionNumber > 11 ? "macOS " : "Mac OS X ") + name, "10." + versionNumber);
    }
}
