/*
 * The MIT License (MIT)
 * Copyright (c) 2019-2020 TheRandomLabs
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package gdn.rom.env.platform;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import gdn.rom.env.util.Checks;

/**
 * An enum for known OS's that can run JRE 8 or newer.
 */
public enum OS
{
    /**
     * AIX.
     */
    AIX("AIX"),
    /**
     * Android.
     */
    ANDROID("Android", "linux"),
    /**
     * FreeBSD.
     */
    FREEBSD("FreeBSD"),
    /**
     * GNU/Linux.
     */
    GNU_LINUX("GNU/Linux", "gnu"),
    /**
     * HP-UX.
     */
    HP_UX("HP-UX"),
    /**
     * IBM i.
     */
    IBM_I("IBM i", "os/400", "os/400®"),
    /**
     * kFreeBSD.
     */
    KFREEBSD("kFreeBSD", "gnu/kfreebsd"),
    /**
     * Linux.
     */
    LINUX("Linux"),
    /**
     * macOS or Mac OS X.
     */
    MACOS("macOS", MacOSVersion.getVersions(), false, "Mac OS X"),
    /**
     * NetBSD.
     */
    NETBSD("NetBSD"),
    /**
     * OpenBSD.
     */
    OPENBSD("OpenBSD"),
    /**
     * Solaris.
     */
    SOLARIS("Solaris", "solaris", "sunos"),
    /**
     * Windows.
     */
    WINDOWS("Windows", WindowsVersion.getVersions(), true),
    /**
     * Windows CE.
     */
    WINDOWS_CE("Windows CE"),
    /**
     * Z/OS.
     */
    Z_OS("Z/OS"),
    /**
     * Unknown OS.
     */
    UNKNOWN("Unknown");

    /**
     * An immutable set of all {@link OS}'s.
     */
    public static final Set<OS>  VALUES       = Set.of(values());

    /**
     * An immutable set of all {@link OS}'s other than {@link #UNKNOWN}.
     */
    public static final Set<OS>  KNOWN_VALUES = Arrays.stream(values()).filter(os -> os != UNKNOWN).collect(Collectors.toSet());

    private final String         friendlyName;
    private final List<String>   knownNames;
    private final Set<OSVersion> versions;
    private final boolean        versionNumberInOSName;

    OS(String friendlyName, String... knownNames)
    {
        this(friendlyName, Set.of(), false, knownNames);
    }

    OS(String friendlyName, Set<OSVersion> versions, boolean versionNumberInOSName, String... knownNames)
    {
        this.friendlyName = friendlyName;

        if (knownNames.length == 0)
        {
            this.knownNames = List.of(friendlyName.toLowerCase(Locale.ENGLISH));
        } else
        {
            this.knownNames = List.of(knownNames);
        }

        this.versions = Set.copyOf(versions);
        this.versionNumberInOSName = versionNumberInOSName;
    }

    /**
     * Returns whether this {@link OS} is {@link #WINDOWS} or {@link #WINDOWS_CE}.
     *
     * @return {@code true} if this {@link OS} is {@link #WINDOWS} or {@link #WINDOWS_CE},
     *         or otherwise {@code false}.
     */
    public boolean isWindowsOrWindowsCE()
    {
        return this == WINDOWS || this == WINDOWS_CE;
    }

    /**
     * Returns the friendly name of this {@link OS}.
     *
     * @return the friendly name of this {@link OS}.
     */
    public String getFriendlyName()
    {
        return friendlyName;
    }

    /**
     * Returns an immutable set containing all known versions of this {@link OS}.
     *
     * @return an immutable set containing all known versions of this {@link OS}.
     */
    public Set<OSVersion> getVersions()
    {
        return versions;
    }

    /**
     * Returns whether the version number of any {@link OSVersion} of this {@link OS} is included in
     * The {@code {@code os.name}} system property. For example, this is {@code true} for Windows,
     * as The {@code {@code os.name}} system property.contains the version number in its value, e.g.
     * {@code "Windows 8.1"} or {@code "Windows 10"}.
     *
     * @return {@code true} if the version number of any {@link OSVersion} of this {@link OS} is
     *         included in The {@code {@code os.name}} system property. or otherwise {@code false}.
     */
    public boolean isVersionNumberInOSName()
    {
        return versionNumberInOSName;
    }

    /**
     * Returns the {@link OSVersion} with a name equivalent to the specified name.
     *
     * @param  name
     *                  a name, e.g. {@code "Windows 10"}.
     * 
     * @return      the {@link OSVersion} with a name equivalent to the specified name,
     *              or {@link OSVersion#unknown()} if it cannot be found.
     */
    public OSVersion getVersionByName(String name)
    {
        Checks.checkNotNull(name, "name should not be null");

        if (versions.isEmpty())
        {
            return OSVersion.unknown();
        }

        name = name.trim();

        if (name.isEmpty())
        {
            return OSVersion.unknown();
        }

        return getVersion(name, OSVersion::getFullName);
    }

    /**
     * Returns the {@link OSVersion} with a version number equivalent to the specified version
     * number.
     *
     * @param  versionNumber
     *                           a version number.
     * 
     * @return               the {@link OSVersion} with a version number equivalent to the specified version
     *                       number, or {@link OSVersion#unknown()} if it cannot be found.
     */
    public OSVersion getVersionByVersionNumber(String versionNumber)
    {
        if (versions.isEmpty())
        {
            return OSVersion.unknown();
        }

        versionNumber = versionNumber.trim();

        if (versionNumber.isEmpty())
        {
            return OSVersion.unknown();
        }

        // First, we try directly matching the version numbers.
        final OSVersion quickMatch = getVersion(versionNumber, OSVersion::getVersionNumber);

        if (!quickMatch.isUnknown())
        {
            return quickMatch;
        }

        // Then, we try matching by ignoring minor versions, e.g. 10.13.6 is matched to 10.13.
        final String[] versionParts = versionNumber.split("\\.");

        for (OSVersion version : versions)
        {
            if (versionsMatch(versionParts, version.getVersionNumber().split("\\.")))
            {
                return version;
            }
        }

        return OSVersion.unknown();
    }

    private OSVersion getVersion(String toFind, Function<OSVersion, String> versionToString)
    {
        for (OSVersion version : versions)
        {
            if (toFind.equalsIgnoreCase(versionToString.apply(version)))
            {
                return version;
            }
        }

        return OSVersion.unknown();
    }

    private boolean versionsMatch(String[] version1Parts, String[] version2Parts)
    {
        final int partsToCheck = Math.min(version1Parts.length, version2Parts.length);

        for (int i = 0; i < partsToCheck; i++)
        {
            if (!version1Parts[i].equalsIgnoreCase(version2Parts[i]))
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the {@link OS} with the specified OS name.
     *
     * @param  osName
     *                    an OS name.
     * 
     * @return        the {@link OS} with the specified OS name, or {@link #UNKNOWN} if it does not
     *                exist.
     */
    public static OS fromName(String osName)
    {
        final String nameToFind = osName.trim().toLowerCase(Locale.ENGLISH);

        if (nameToFind.isEmpty())
        {
            return UNKNOWN;
        }

        // Since WINDOWS_CE is defined before WINDOWS, we need to check for WINDOWS_CE first.
        if (nameToFind.equals(WINDOWS_CE.knownNames.get(0)))
        {
            return WINDOWS_CE;
        }

        for (OS os : KNOWN_VALUES)
        {
            if (os.knownNames.stream().anyMatch(nameToFind::startsWith))
            {
                return os;
            }
        }

        return UNKNOWN;
    }
}
