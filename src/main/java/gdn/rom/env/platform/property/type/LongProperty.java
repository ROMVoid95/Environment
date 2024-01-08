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

import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import gdn.rom.env.platform.property.SystemProperty;

/**
 * A {@link SystemProperty} with a {@code long} value.
 */
public class LongProperty extends SystemProperty<Long>
{
    private static final TaggedLogger logger = Logger.tag("LongProperty");

    /**
     * Constructs an uneditable {@link LongProperty}.
     *
     * @param key
     *                the key of this {@link LongProperty}.
     */
    public LongProperty(String key)
    {
        this(key, false);
    }

    /**
     * Constructors a {@link LongProperty}.
     *
     * @param key
     *                     a key.
     * @param editable
     *                     whether this {@link LongProperty} can be edited by calling
     *                     {@link SystemProperty#set(Object)} instead of {@link SystemProperty#forceSet(String)}.
     */
    public LongProperty(String key, boolean editable)
    {
        super(key, editable);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public Long get()
    {
        final String raw = getRaw();

        if (raw == null)
        {
            return null;
        }

        try
        {
            return Long.valueOf(raw);
        } catch (NumberFormatException ex)
        {
            logger.warn("Not a valid long: {}", raw, ex);
        }

        return null;
    }
}
