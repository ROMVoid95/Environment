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

import java.util.HashSet;
import java.util.Set;

import lombok.experimental.UtilityClass;

/**
 * Contains {@link OSVersion}s that represent Windows versions that can run JRE 8 or newer.
 */
@UtilityClass
public final class WindowsVersion
{
    /**
     * Windows Vista.
     */
    public static final OSVersion       WINDOWS_VISTA       = get("Vista");

    /**
     * Windows 7.
     */
    public static final OSVersion       WINDOWS_7           = get("7");

    /**
     * Windows Server 2008, the server version of Windows Vista,
     * or Windows Server 2008 R2, the server version of Windows 7.
     */
    public static final OSVersion       WINDOWS_SERVER_2008 = get("Server 2008");

    /**
     * Windows 8.
     */
    public static final OSVersion       WINDOWS_8           = get("8");

    /**
     * Windows 8.1.
     */
    public static final OSVersion       WINDOWS_8_1         = get("8.1");

    /**
     * Windows Server 2012, the server version of Windows 8.
     */
    public static final OSVersion       WINDOWS_SERVER_2012 = get("Server 2012");

    /**
     * Windows 10.
     */
    public static final OSVersion       WINDOWS_10          = get("10");
    
    public static final OSVersion       WINDOWS_11          = get("11");

    /**
     * Windows Server 2016, the server version of Windows 10.
     */
    public static final OSVersion       WINDOWS_SERVER_2016 = get("Server 2016");

    private static final Set<OSVersion> versions            = Set.of(WINDOWS_VISTA, WINDOWS_7, WINDOWS_SERVER_2008, WINDOWS_8, WINDOWS_8_1, WINDOWS_SERVER_2012, WINDOWS_10, WINDOWS_11, WINDOWS_SERVER_2016);

    /**
     * Returns all {@link OSVersion} instances that represent known Windows versions.
     *
     * @return a mutable {@link Set} containing all {@link OSVersion} instances that represent
     *         known Windows versions.
     */
    public static Set<OSVersion> getVersions()
    {
        return new HashSet<>(versions);
    }

    private static OSVersion get(String versionNumber)
    {
        return new OSVersion("Windows " + versionNumber, versionNumber);
    }
}
