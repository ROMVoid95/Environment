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
import gdn.rom.env.platform.property.type.PathListProperty;
import gdn.rom.env.platform.property.type.PathProperty;
import gdn.rom.env.platform.property.type.StringProperty;
import lombok.experimental.UtilityClass;

/**
 * Contains {@link SystemProperty} fields that represent all known universal system properties
 * on JRE 8 and newer.
 */
@UtilityClass
public final class SystemProperties
{
    /**
     * The {@code "java.vm.specification.name"} system property.
     */
    public static final StringProperty   jvmSpecificationName        = new StringProperty("java.vm.specification.name");

    /**
     * The {@code "java.vm.specification.version"} system property.
     */
    public static final StringProperty   jvmSpecificationVersion     = new StringProperty("java.vm.specification.version");

    /**
     * The {@code "java.vm.specification.vendor"} system property.
     */
    public static final StringProperty   jvmSpecificationVendor      = new StringProperty("java.vm.specification.vendor");

    /**
     * The {@code "java.vm.name"} system property.
     */
    public static final StringProperty   jvmName                     = new StringProperty("java.vm.name");

    /**
     * The {@code "java.vm.version"} system property.
     */
    public static final StringProperty   jvmVersion                  = new StringProperty("java.vm.version");

    /**
     * The {@code "java.vm.vendor"} system property.
     */
    public static final StringProperty   jvmVendor                   = new StringProperty("java.vm.vendor");

    /**
     * The {@code "java.vm.info"} system property.
     */
    public static final StringProperty   jvmInfo                     = new StringProperty("java.vm.info");

    /**
     * The {@code "java.runtime.name"} system property.
     */
    public static final StringProperty   javaRuntimeName             = new StringProperty("java.runtime.name");

    /**
     * The {@code "java.runtime.version"} system property.
     */
    public static final StringProperty   javaRuntimeVersion          = new StringProperty("java.runtime.version");

    /**
     * The {@code "java.version"} system property.
     */
    public static final StringProperty   javaVersion                 = new StringProperty("java.version");

    /**
     * The {@code "java.vendor"} system property.
     */
    public static final StringProperty   javaVendor                  = new StringProperty("java.vendor");

    /**
     * The {@code "java.vendor.url"} system property.
     */
    public static final StringProperty   javaVendorUrl               = new StringProperty("java.vendor.url");

    /**
     * The {@code "java.vendor.url.bug"} system property.
     */
    public static final StringProperty   javaVendorBugUrl            = new StringProperty("java.vendor.url.bug");

    /**
     * The {@code "java.home"} system property.
     */
    public static final PathProperty     javaInstallationDirectory   = new PathProperty("java.home");

    /**
     * The {@code "java.ext.dirs"} system property.
     */
    public static final StringProperty   javaExtensionDirectories    = new StringProperty("java.ext.dirs");

    /**
     * The {@code "java.specification.name"} system property.
     */
    public static final StringProperty   javaSpecificationName       = new StringProperty("java.specification.name");

    /**
     * The {@code "java.specification.version"} system property.
     */
    public static final StringProperty   javaSpecificationVersion    = new StringProperty("java.specification.version");

    /**
     * The {@code "java.specification.vendor"} system property.
     */
    public static final StringProperty   javaSpecificationVendor     = new StringProperty("java.specification.vendor");

    /**
     * The {@code "java.class.version"} system property.
     */
    public static final StringProperty   javaClassFormatVersion      = new StringProperty("java.class.version");

    /**
     * The {@code "java.class.path"} system property.
     */
    public static final PathListProperty javaClasspath               = new PathListProperty("java.class.path");

    /**
     * The {@code "java.library.path"} system property.
     */
    public static final PathListProperty librarySearchDirectories    = new PathListProperty("java.library.path");

    /**
     * The {@code "java.endorsed.dirs"} system property.
     */
    public static final PathListProperty endorsedSearchDirectories   = new PathListProperty("java.endorsed.dirs");

    /**
     * The {@code "java.compiler"} system property.
     */
    public static final StringProperty   javaCompilerName            = new StringProperty("java.compiler");

    /**
     * The {@code "user.name"} system property.
     */
    public static final StringProperty   userAccountName             = new StringProperty("user.name");

    /**
     * The {@code "user.home"} system property.
     */
    public static final PathProperty     userHomeDirectory           = new PathProperty("user.home");

    /**
     * The {@code "user.dir"} system property.
     */
    public static final PathProperty     userCurrentWorkingDirectory = new PathProperty("user.dir");

    /**
     * The {@code "user.country"} system property.
     */
    public static final StringProperty   userCountry                 = new StringProperty("user.country");

    /**
     * The {@code "user.language"} system property.
     */
    public static final StringProperty   userLanguage                = new StringProperty("user.language");

    /**
     * The {@code "user.timezone"} system property.
     */
    public static final StringProperty   userTimeZone                = new StringProperty("user.timezone");

    /**
     * The {@code "java.awt.graphicsenv"} system property.
     */
    public static final StringProperty   awtGraphicsEnvironment      = new StringProperty("java.awt.graphicsenv");

    /**
     * The {@code "java.awt.printerjob"} system property.
     */
    public static final StringProperty   awtPrinterJob               = new StringProperty("java.awt.printerjob");

    /**
     * The {@code "java.awt.headless"} system property.
     */
    public static final BooleanProperty  awtHeadlessMode             = new BooleanProperty("java.awt.headless", true);

    /**
     * The {@code "os.name"} system property.
     */
    public static final StringProperty   osName                      = new StringProperty("os.name");

    /**
     * The {@code "os.arch"} system property.
     */
    public static final StringProperty   jvmArchitecture             = new StringProperty("os.arch");

    /**
     * The {@code "os.version"} system property.
     */
    public static final StringProperty   osVersion                   = new StringProperty("os.version");

    /**
     * The {@code "path.separator"} system property.
     */
    public static final StringProperty   pathListSeparator           = new StringProperty("path.separator");

    /**
     * The {@code "line.separator"} system property.
     */
    public static final StringProperty   lineSeparator               = new StringProperty("line.separator");

    /**
     * The {@code "file.encoding"} system property.
     */
    public static final StringProperty   fileEncoding                = new StringProperty("file.encoding");

    /**
     * The {@code "file.encoding.pkg"} system property.
     */
    public static final StringProperty   fileEncodingPackage         = new StringProperty("file.encoding.pkg");

    /**
     * The {@code "java.io.tmpdir"} system property.
     * <p>
     * When {@link SystemProperty#get()} is called on this system property,
     * the directory that its value references is created if it does not exist.
     * <p>
     * This system property is editable.
     */
    public static final PathProperty     tempDirectory               = new PathProperty("java.io.tmpdir", PathProperty.Behavior.ENSURE_DIRECTORY_EXISTS, true);
}
