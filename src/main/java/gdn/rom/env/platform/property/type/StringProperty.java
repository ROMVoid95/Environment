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

import gdn.rom.env.platform.property.SystemProperty;

/**
 * A {@link SystemProperty} with a string value.
 */
public class StringProperty extends SystemProperty<String>
{
    /**
     * Constructs an uneditable {@link StringProperty}.
     *
     * @param key
     *                the key of this {@link StringProperty}.
     */
    public StringProperty(String key)
    {
        this(key, false);
    }

    /**
     * Constructors a {@link StringProperty}.
     *
     * @param key
     *                     a key.
     * @param editable
     *                     whether this {@link StringProperty} can be edited by calling
     *                     {@link SystemProperty#set(Object)} instead of {@link SystemProperty#forceSet(String)}.
     */
    public StringProperty(String key, boolean editable)
    {
        super(key, editable);
    }

    /**
     * Returns this {@link StringProperty}'s value.
     *
     * @return this {@link StringProperty}'s value, which is equivalent to
     *         {@link SystemProperty#getRaw()}.
     */

    @Override
    public String get()
    {
        return getRaw();
    }
}
