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

package gdn.rom.env.platform.property.type;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import gdn.rom.env.platform.property.SystemProperty;

/**
 * A {@link SystemProperty} with a list value.
 *
 * @param <E>
 *                The {@code element type of the} system property.list value.
 */
public abstract class ListProperty<E> extends SystemProperty<List<E>>
{
    private final String separator;

    /**
     * Constructs a {@link ListProperty}.
     *
     * @param key
     *                      the key of this {@link ListProperty}.
     * @param separator
     *                      the character that separates the list elements in the raw string value
     *                      of this {@link ListProperty}.
     * @param editable
     *                      whether this {@link ListProperty} can be edited by calling
     *                      {@link SystemProperty#set(Object)} instead of {@link SystemProperty#forceSet(String)}.
     */
    protected ListProperty(String key, char separator, boolean editable)
    {
        this(key, String.valueOf(separator), editable);
    }

    /**
     * Constructs a {@link ListProperty}.
     *
     * @param key
     *                      the key of this {@link ListProperty}.
     * @param separator
     *                      the string that separates the list elements in the raw string value
     *                      of this {@link ListProperty}.
     * @param editable
     *                      whether this {@link ListProperty} can be edited by calling
     *                      {@link SystemProperty#set(Object)} instead of {@link SystemProperty#forceSet(String)}.
     */
    protected ListProperty(String key, String separator, boolean editable)
    {
        super(key, editable);
        this.separator = separator;
    }

    /**
     * Returns this {@link ListProperty}'s value.
     *
     * @return a mutable {@link List} containing the elements of this {@link ListProperty}'s value.
     */

    @Override
    public final List<E> get()
    {
        final List<String> raw = getRawList();
        return raw == null ? null : convertList(raw);
    }

    /**
     * Returns the raw string list value of this {@link ListProperty}.
     *
     * @return a mutable {@link List} containing the raw string elements of this
     *         {@link ListProperty}'s value.
     */

    public final List<String> getRawList()
    {
        final String raw = getRaw();
        return raw == null ? null : Arrays.asList(raw.split(Pattern.quote(separator)));
    }

    /**
     * Sets this {@link ListProperty}'s raw string list value to the specified value.
     * This works regardless of the value returned by {@link SystemProperty#isEditable()},
     * and no validation is performed on the new value.
     *
     * @param  list
     *                  a raw string list value.
     * 
     * @return      the previous raw string list value of this {@link ListProperty}.
     */

    public final List<String> forceSetArray(Iterable<? extends CharSequence> list)
    {
        final List<String> previous = getRawList();
        forceSet(list == null ? null : String.join(separator, list));
        return previous;
    }

    /**
     * Returns this {@link ListProperty}'s separator, which is the string that separates the
     * array elements in the raw string value of this {@link ListProperty}.
     *
     * @return this {@link ListProperty}'s separator.
     */
    public final String getSeparator()
    {
        return separator;
    }

    /**
     * Converts a raw string list value into a list value of type {@link E}.
     *
     * @param  list
     *                  a raw string list value.
     * 
     * @return      a mutable {@link List} of type {@link E} converted from the specified
     *              raw string list value.
     */
    protected abstract List<E> convertList(List<String> list);
}
