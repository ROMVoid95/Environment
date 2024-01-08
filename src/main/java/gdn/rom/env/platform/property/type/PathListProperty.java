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

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import gdn.rom.env.platform.property.SystemProperty;

/**
 * A {@link SystemProperty} with a list value consisting of path elements.
 */
public class PathListProperty extends ListProperty<Path>
{
    private static final TaggedLogger logger = Logger.tag("PathListProperty");

    /**
     * Constructs a {@link PathListProperty} with {@link File#pathSeparator} as the separator.
     *
     * @param key
     *                the key of this {@link PathListProperty}.
     */
    public PathListProperty(String key)
    {
        this(key, false);
    }

    /**
     * Constructs a {@link PathListProperty} with {@link File#pathSeparator} as the separator.
     *
     * @param key
     *                     the key of this {@link PathListProperty}.
     * @param editable
     *                     whether this {@link PathListProperty} can be edited by calling
     *                     {@link SystemProperty#set(Object)} instead of {@link SystemProperty#forceSet(String)}.
     */
    public PathListProperty(String key, boolean editable)
    {
        super(key, File.pathSeparator, editable);
    }

    /**
     * Constructs a {@link PathListProperty}.
     *
     * @param key
     *                      the key of this {@link PathListProperty}.
     * @param separator
     *                      the string that separates the list elements in the raw string value
     *                      of this {@link PathListProperty}.
     * @param editable
     *                      whether this {@link PathListProperty} can be edited by calling
     *                      {@link SystemProperty#set(Object)} instead of {@link SystemProperty#forceSet(String)}.
     */
    public PathListProperty(String key, String separator, boolean editable)
    {
        super(key, separator, editable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Path> convertList(List<String> list)
    {
        final List<Path> paths = new ArrayList<>(list.size());

        for (String path : list)
        {
            try
            {
                paths.add(Paths.get(path).toAbsolutePath().normalize());
            } catch (InvalidPathException ex)
            {
                logger.warn("Invalid path: {}", path, ex);
            }
        }

        return paths;
    }
}
