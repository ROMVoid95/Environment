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
import gdn.rom.env.platform.property.type.IntProperty;
import gdn.rom.env.platform.property.type.PathListProperty;
import gdn.rom.env.platform.property.type.StringListProperty;
import gdn.rom.env.platform.property.type.StringProperty;
import lombok.experimental.UtilityClass;

/**
 * Contains {@link SystemProperty} fields that represent all known Sun system properties.
 * These properties comprise the majority of properties whose keys begin with "sun.".
 */
@UtilityClass
public final class SunSystemProperties
{
    /**
     * The {@code "sun.arch.data.model"} system property.
     */
    public static final IntProperty        architectureDataModel = new IntProperty("sun.arch.data.model");

    /**
     * The {@code "sun.boot.class.path"} system property.
     */
    public static final PathListProperty   bootClassPath         = new PathListProperty("sun.boot.class.path");

    /**
     * The {@code "sun.boot.library.path"} system property.
     */
    public static final PathListProperty   bootLibraryPath       = new PathListProperty("sun.boot.library.path");

    /**
     * The {@code "sun.cpu.endian"} system property.
     */
    public static final StringProperty     cpuEndian             = new StringProperty("sun.cpu.endian");

    /**
     * The {@code "sun.cpu.isalist"} system property.
     */
    public static final StringListProperty cpuISAList            = new StringListProperty("sun.cpu.isalist", ' ');

    /**
     * The {@code "sun.io.unicode.encoding"} system property.
     */
    public static final StringProperty     ioUnicodeEncoding     = new StringProperty("sun.io.unicode.encoding");

    /**
     * The {@code "sun.java.launcher"} system property.
     */
    public static final StringProperty     javaLauncher          = new StringProperty("sun.java.launcher");

    /**
     * The {@code "sun.jnu.encoding"} system property.
     */
    public static final StringProperty     jnuEncoding           = new StringProperty("sun.jnu.encoding");

    /**
     * The {@code "sun.management.compiler"} system property.
     */
    public static final StringProperty     managementCompiler    = new StringProperty("sun.management.compiler");

    /**
     * The {@code "sun.os.patch.level"} system property.
     */
    public static final StringProperty     osPatchLevel          = new StringProperty("sun.os.patch.level");

    /**
     * The {@code "sun.awt.disableMixing"} system property.
     */
    public static final BooleanProperty    awtDisableMixing      = new BooleanProperty("sun.awt.disableMixing", true);

    /**
     * The {@code "sun.awt.noerasebackground"} system property.
     * <p>
     * This system property is editable.
     */
    public static final BooleanProperty    awtNoEraseBackground  = new BooleanProperty("sun.awt.noerasebackground", true);

    /**
     * The {@code "sun.awt.xembedserver"} system property.
     * <p>
     * This system property is editable.
     */
    public static final BooleanProperty    awtXEmbedServer       = new BooleanProperty("sun.awt.xembedserver", true);

    /**
     * The {@code "sun.desktop"} system property.
     */
    public static final StringProperty     desktop               = new StringProperty("sun.desktop");

    /**
     * The {@code "sun.java2d.opengl"} system property.
     * <p>
     * This system property is editable.
     */
    public static final BooleanProperty    java2DOpenGL          = new BooleanProperty("sun.java2d.opengl", true);

    /**
     * The {@code "sun.java2d.d3d"} system property.
     * <p>
     * This system property is editable.
     */
    public static final BooleanProperty    java2DDirect3D        = new BooleanProperty("sun.java2d.d3d", true);
}
