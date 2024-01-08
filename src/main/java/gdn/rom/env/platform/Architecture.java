package gdn.rom.env.platform;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import gdn.rom.env.util.Checks;

/**
 * An enum for 32-bit, 64-bit and unknown architectures.
 */
public enum Architecture
{
    /**
     * 32-bit architecture.
     */
    THIRTY_TWO_BIT("32-bit", "x86_32", "x86-32", "x86", "i386", "i486", "i586", "i686", "ia32", "x32", "sparc", "sparc32", "arm", "arm32", "ppc", "ppc32", "powerpc", "s390"),
    /**
     * 64-bit architecture.
     */
    SIXTY_FOUR_BIT("64-bit", "x86_64", "x86-64", "amd64", "ia32e", "em64t", "x64", "ia64", "itanium64", "sparcv9", "sparc64", "aarch64", "powerpc64", "ppc64", "ppc64le", "s390x"),
    /**
     * Unknown architecture.
     */
    UNKNOWN("Unknown");

    private final String      friendlyName;
    private final Set<String> architectureNames;
    private final Set<String> searchNames;

    Architecture(String friendlyName, String... architectureNames)
    {
        this.friendlyName = friendlyName;
        this.architectureNames = Set.of(architectureNames);

        // We create a separate set with hyphens and underscores stripped so that
        // Architecture#fromName has an easier time finding the correct Architecture.
        final Set<String> searchNames = new HashSet<>(architectureNames.length + 1);
        searchNames.add(friendlyName.replaceAll("[-_]", ""));

        for (String name : architectureNames)
        {
            searchNames.add(name.replaceAll("[-_]", ""));
        }

        this.searchNames = Set.copyOf(searchNames);
    }

    /**
     * Returns an immutable set containing the names of all known supported architectures with
     * this {@link Architecture}'s bitness.
     *
     * @return the names of all known supported architectures with this {@link Architecture}'s
     *         bitness.
     */
    public Set<String> getArchitectureNames()
    {
        return architectureNames;
    }

    /**
     * Returns the friendly name of this {@link Architecture}.
     *
     * @return the friendly name of this {@link Architecture}.
     */
    public String getFriendlyName()
    {
        return friendlyName;
    }

    /**
     * Returns the {@link Architecture} that the specified architecture name represents.
     *
     * @param  architectureName
     *                              an architecture name, for example,
     *                              {@code "32-bit"} or {@code "x86-64"}.
     *                              Casing, whitespace, hyphens and underscores are ignored.
     * 
     * @return                  the {@link Architecture} that the specified architecture name represents, or
     *                          {@link #UNKNOWN} if it cannot be found.
     */
    public static Architecture fromName(String architectureName)
    {
        Checks.checkNotNull(architectureName, "architectureName should not be null");
        architectureName = architectureName.toLowerCase(Locale.ENGLISH).replaceAll("[\\s-_]", "");

        for (Architecture architecture : values())
        {
            if (architecture.searchNames.contains(architectureName))
            {
                return architecture;
            }
        }

        return UNKNOWN;
    }
}
