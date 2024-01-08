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

package gdn.rom.env.platform.property;

import gdn.rom.env.platform.property.type.BooleanProperty;
import gdn.rom.env.platform.property.type.StringListProperty;
import gdn.rom.env.platform.property.type.StringProperty;
import lombok.experimental.UtilityClass;

/**
 * Contains {@link SystemProperty} fields that represent all known macOS/Mac OS X system properties.
 */
@UtilityClass
public final class MacSystemProperties
{
    /**
     * The {@code "awt.nativeDoubleBuffering"} system property.
     */
    public static final BooleanProperty    awtNativeDoubleBuffering = new BooleanProperty("awt.nativeDoubleBuffering");

    /**
     * The {@code "awt.toolkit"} system property.
     */
    public static final StringProperty     awtToolkit               = new StringProperty("awt.toolkit");

    /**
     * The {@code "ftp.nonProxyHosts"} system property.
     */
    public static final StringListProperty ftpNonProxyHosts         = new StringListProperty("ftp.nonProxyHosts", '|');

    /**
     * The {@code "gopherProxySet"} system property.
     */
    public static final BooleanProperty    gopherProxySet           = new BooleanProperty("gopherProxySet");

    /**
     * The {@code "http.nonProxyHosts"} system property.
     */
    public static final StringListProperty httpNonProxyHosts        = new StringListProperty("http.nonProxyHosts", '|');

    /**
     * The {@code "mrj.version"} system property.
     */
    public static final StringProperty     mrjVersion               = new StringProperty("mrj.version");

    /**
     * The {@code "socksNonProxyHosts"} system property.
     */
    public static final StringListProperty socksNonProxyHosts       = new StringListProperty("socksNonProxyHosts", '|');
}
