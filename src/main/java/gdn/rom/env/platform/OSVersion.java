package gdn.rom.env.platform;

import java.util.Objects;

/**
 * Represents an {@link OS} version.
 */
public final class OSVersion implements Comparable<OSVersion>
{
    private static final OSVersion UNKNOWN = new OSVersion("Unknown", "Unknown");

    private final String           fullName;
    private final String           versionNumber;

    OSVersion(String fullName, String versionNumber)
    {
        this.fullName = fullName;
        this.versionNumber = versionNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(fullName, versionNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object object)
    {
        // It isn't actually necessary to implement equals here; I've just done it to fix the
        // warnings.
        return this == object;
    }

    /**
     * Returns the full name of this {@link OSVersion}, e.g. {@code "Windows 7"} or
     * {@code "macOS Catalina"}.
     *
     * @return the full name of this {@link OSVersion}.
     */
    @Override
    public String toString()
    {
        return fullName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(OSVersion version)
    {
        return fullName.compareTo(version.fullName);
    }

    /**
     * Returns the full name of this {@link OSVersion}, e.g. {@code "Windows 7"} or
     * {@code "macOS Catalina"}.
     *
     * @return the full name of this {@link OSVersion}.
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * Returns this {@link OSVersion}'s version number, e.g. {@code "10.10"}.
     * This number may be but is not necessarily included in the full name returned by
     * {@link #getFullName()}).
     *
     * @return this {@link OSVersion}'s version number.
     */
    public String getVersionNumber()
    {
        return versionNumber;
    }

    /**
     * Returns whether this {@link OSVersion} is {@link OSVersion#unknown()}.
     *
     * @return whether this {@link OSVersion} is {@link OSVersion#unknown()}.
     */
    public boolean isUnknown()
    {
        return equals(UNKNOWN);
    }

    /**
     * Returns an {@link OSVersion} that represents an unknown OS version.
     *
     * @return an {@link OSVersion} that represents an unknown OS version.
     */
    public static OSVersion unknown()
    {
        return UNKNOWN;
    }
}
