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

import java.util.Objects;

import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import gdn.rom.env.util.Checks;

/**
 * Represents a system property.
 *
 * @param <T> the system property value type.
 */
public abstract class SystemProperty<T> {
	private static final TaggedLogger logger = Logger.tag("Environment.SystemProperty");

	private final String key;
	private final boolean editable;

	/**
	 * Constructs a {@link SystemProperty}.
	 *
	 * @param key the key of this {@link SystemProperty}.
	 * @param editable whether this {@link SystemProperty} can be edited by calling
	 * {@link #set(Object)} instead of {@link #forceSet(String)}.
	 */
	protected SystemProperty(String key, boolean editable) {
		Checks.checkNotNull(key, "key should not be null");
		this.key = key;
		this.editable = editable;
	}

	/**
	 * Returns the string value of this {@link SystemProperty}.
	 *
	 * @return the string value of {@link #get()}, generally as returned by
	 * {@link String#valueOf(Object)}.
	 */
	@Override
	public String toString() {
		return String.valueOf(get());
	}

	/**
	 * Returns the key of this {@link SystemProperty}.
	 *
	 * @return the key of this {@link SystemProperty}.
	 */
	public final String getKey() {
		return key;
	}

	/**
	 * Returns whether this {@link SystemProperty} can be edited by calling
	 * {@link #set(Object)} instead of {@link #forceSet(String)}.
	 *
	 * @return {@code true} if this {@link SystemProperty} can be edited by calling
	 * {@link #set(Object)}, or otherwise {@code false}.
	 */
	public final boolean isEditable() {
		return editable;
	}

	/**
	 * Returns whether this {@link SystemProperty} has a value.
	 *
	 * @return {@code true} if this {@link SystemProperty} has a non-{@code null} raw string value,
	 * or otherwise {@code false}.
	 */
	public final boolean hasValue() {
		return getRaw() != null;
	}

	/**
	 * Returns whether this {@link SystemProperty} has a valid value.
	 *
	 * @return {@code true} if this {@link SystemProperty} has a valid value, or otherwise
	 * {@code false}. A {@link SystemProperty} has a valid value if it has a non-{@code null}
	 * raw string value, and {@link #get()} also returns a non-{@code null} value.
	 */
	public final boolean hasValidValue() {
		return getRaw() == null || get() != null;
	}

	/**
	 * Returns this {@link SystemProperty}'s raw string value.
	 *
	 * @return this {@link SystemProperty}'s raw string value.
	 */
	
	public final String getRaw() {
		return getProperty(key);
	}

	/**
	 * Returns this {@link SystemProperty}'s value.
	 *
	 * @return this {@link SystemProperty}'s value.
	 */
	
	public abstract T get();

	/**
	 * Returns this {@link SystemProperty}'s value if it is non-{@code null}.
	 * Otherwise, the specified default value is returned.
	 *
	 * @param defaultValue a default value.
	 * @return this {@link SystemProperty}'s value if it is non-{@code null}, or otherwise the
	 * specified default value.
	 */
	public final T get(T defaultValue) {
		Checks.checkNotNull(defaultValue, "defaultValue should not be null");
		final T value = get();
		return value == null ? defaultValue : value;
	}

	/**
	 * Sets this {@link SystemProperty}'s value to the specified value.
	 *
	 * @param value a value.
	 * @return the previous value of this {@link SystemProperty}.
	 * @throws UnsupportedOperationException if {@link #isEditable()} returns {@code false}.
	 */
	
	public final T set( T value) {
		if (!isEditable()) {
			throw new UnsupportedOperationException("Cannot set value of uneditable property");
		}

		final T previous = get();

		if (value == null) {
			forceSet(null);
		} else {
			setProperty(key, toRawString(value));
		}

		return previous;
	}

	/**
	 * Sets this {@link SystemProperty}'s raw string value to the specified value.
	 * This works regardless of the value returned by {@link #isEditable()},
	 * and no validation is performed on the new value.
	 *
	 * @param value a raw string value.
	 * @return the previous raw string value of this {@link SystemProperty}.
	 */
	
	public final String forceSet( String value) {
		final String previous = getRaw();
		setProperty(key, value);
		return previous;
	}

	/**
	 * Returns whether this {@link SystemProperty}'s value is equivalent to the specified value.
	 *
	 * @param value a value.
	 * @return {@code true} if {@link Objects#equals(Object, Object)} returns {@code true} for
	 * both this {@link SystemProperty}'s value and the specified value, or otherwise {@code false}.
	 */
	public final boolean valueEquals( T value) {
		return Objects.equals(get(), value);
	}

	/**
	 * Returns whether {@link #toString()} is equivalent to the specified string value.
	 *
	 * @param value a string value.
	 * @return {@code true} if {@link #toString()} is equivalent to the specified string value,
	 * or otherwise {@code false}.
	 */
	public final boolean stringEquals(String value) {
		return toString().equals(value);
	}

	/**
	 * Returns whether this {@link SystemProperty}'s raw string value is equivalent to the
	 * specified raw string value.
	 *
	 * @param value a raw string value.
	 * @return {@code true} if {@link #getRaw()} is equivalent to the specified raw string value,
	 * or otherwise {@code false}.
	 */
	public final boolean rawValueEquals( String value) {
		return Objects.equals(getRaw(), value);
	}

	/**
	 * Converts the specified value to a raw string value.
	 *
	 * @param value a non-{@code null} value.
	 * @return the raw string value representation of the specified value.
	 */
	protected String toRawString(T value) {
		Checks.checkNotNull(value, "value should not be null");
		return value.toString();
	}

	/**
	 * Returns the raw string value of the system property with the specified key.
	 *
	 * @param key a system property key.
	 * @return the raw string value of the system property with the specified key.
	 */
	
	public static String getProperty(String key) {
		Checks.checkNotNull(key, "key should not be null");

		try {
			return System.getProperty(key);
		} catch (SecurityException ex) {
			logger.warn("Could not retrieve system property: {}", key, ex);
		}

		return null;
	}

	/**
	 * Returns the raw string value of the system property with the specified key, or the
	 * specified default value if it is {@code null}.
	 *
	 * @param key a system property key.
	 * @param defaultValue a default raw string value.
	 * @return the raw string value of the system property with the specified key, or the
	 * specified default value if it is {@code null}.
	 */
	public static String getProperty(String key, String defaultValue) {
		Checks.checkNotNull(key, "key should not be null");
		Checks.checkNotNull(defaultValue, "defaultValue should not be null");

		try {
			return System.getProperty(key, defaultValue);
		} catch (SecurityException ex) {
			logger.warn("Could not retrieve system property: {}", key, ex);
		}

		return defaultValue;
	}

	/**
	 * Sets the raw string value of the system property with the specified key to the specified
	 * raw string value.
	 *
	 * @param key a system property key.
	 * @param value a raw string value.
	 * @return the previous raw string value of the system property.
	 */
	
	public static String setProperty(String key, String value) {
		Checks.checkNotNull(key, "key should not be null");

		try {
			return System.setProperty(key, value);
		} catch (SecurityException ex) {
			logger.warn("Could not set system property: {}", key, ex);
		}

		return null;
	}
}
