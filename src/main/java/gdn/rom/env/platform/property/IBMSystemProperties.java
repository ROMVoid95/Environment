/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019-2020 TheRandomLabs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package gdn.rom.env.platform.property;

import gdn.rom.env.platform.property.type.IntProperty;
import gdn.rom.env.platform.property.type.PathProperty;
import gdn.rom.env.platform.property.type.StringProperty;
import lombok.experimental.UtilityClass;

/**
 * Contains {@link SystemProperty} fields that represent all known IBM system properties.
 */
@UtilityClass
public final class IBMSystemProperties {
	/**
	 * The {@code "com.ibm.vm.bitmode"} system property.
	 */
	public static final IntProperty vmBitMode = new IntProperty("com.ibm.vm.bitmode");

	/**
	 * The {@code "com.ibm.cpu.endian"} system property.
	 */
	public static final StringProperty cpuEndian = new StringProperty("com.ibm.cpu.endian");

	/**
	 * The {@code "com.ibm.oti.configuration"} system property.
	 */
	public static final StringProperty otiConfiguration =
			new StringProperty("com.ibm.oti.configuration");

	/**
	 * The {@code "com.ibm.oti.jcl.build"} system property.
	 */
	public static final StringProperty otiJCLBuild = new StringProperty("com.ibm.oti.jcl.build");

	/**
	 * The {@code "com.ibm.oti.vm.bootstrap.library.path"} system property.
	 */
	public static final PathProperty otiVMBootstrapLibraryPath =
			new PathProperty("com.ibm.oti.vm.bootstrap.library.path");

	/**
	 * The {@code "com.ibm.oti.vm.library.version"} system property.
	 */
	public static final StringProperty otiVMLibraryVersion =
			new StringProperty("com.ibm.oti.vm.library.version");

	/**
	 * The {@code "com.ibm.util.extralibs.properties"} system property.
	 */
	public static final StringProperty extraLibsProperties =
			new StringProperty("com.ibm.util.extralibs.properties");

	/**
	 * The {@code "ibm.signalhandling.rs"} system property.
	 */
	public static final StringProperty signalHandlingRS =
			new StringProperty("ibm.signalhandling.rs");

	/**
	 * The {@code "ibm.signalhandling.sigchain"} system property.
	 */
	public static final StringProperty signalHandlingSigchain =
			new StringProperty("ibm.signalhandling.sigchain");

	/**
	 * The {@code "ibm.signalhandling.sigint"} system property.
	 */
	public static final StringProperty signalHandlingSigint =
			new StringProperty("ibm.signalhandling.sigint");

	/**
	 * The {@code "ibm.system.encoding"} system property.
	 */
	public static final StringProperty systemEncoding = new StringProperty("ibm.system.encoding");

	/**
	 * The {@code "invokedviajava"} system property.
	 */
	public static final StringProperty invokedViaJava = new StringProperty("invokedviajava");

	/**
	 * The {@code "java.assistive"} system property.
	 */
	public static final StringProperty javaAssistive = new StringProperty("java.assistive");

	/**
	 * The {@code "java.awt.fonts"} system property.
	 */
	public static final StringProperty awtFonts = new StringProperty("java.awt.fonts");

	/**
	 * The {@code "java.jcl.version"} system property.
	 */
	public static final StringProperty jclVersion = new StringProperty("java.jcl.version");

	/**
	 * The {@code "java.utils.prefs.PreferencesFactory"} system property.
	 */
	public static final StringProperty javaUtilsPrefsPreferencesFactory =
			new StringProperty("java.utils.prefs.PreferencesFactory");

	/**
	 * The {@code "jxe.current.romimage.version"} system property.
	 */
	public static final StringProperty jxeCurrentRomImageVersion =
			new StringProperty("jxe.current.romimage.version");

	/**
	 * The {@code "jxe.lowest.romimage.version"} system property.
	 */
	public static final StringProperty jxexLowestRomImageVersion =
			new StringProperty("jxe.lowest.romimage.version");

	/**
	 * The {@code "sun.java2d.fontpath"} system property.
	 */
	public static final PathProperty java2DFontPath = new PathProperty("sun.java2d.fontpath");
}
