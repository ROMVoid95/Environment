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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.tinylog.Logger;
import org.tinylog.TaggedLogger;

import gdn.rom.env.platform.property.SystemProperty;
import gdn.rom.env.util.Checks;

/**
 * A {@link SystemProperty} with a path value.
 */
public class PathProperty extends SystemProperty<Path>
{
    /**
     * An enum containing actions that should be performed when {@link PathProperty#get()} is
     * called.
     */
    public enum Behavior
    {
        /**
         * Do nothing.
         */
        DO_NOTHING,
        /**
         * Ensure a file exists at the path specified by the value of the {@link PathProperty}.
         */
        ENSURE_FILE_EXISTS,
        /**
         * Ensure a directory exists at the path specified by the value of the {@link PathProperty}.
         */
        ENSURE_DIRECTORY_EXISTS
    }

    private static final TaggedLogger logger = Logger.tag("PathProperty");

    private final Behavior            behavior;

    /**
     * Constructs an uneditable {@link PathProperty} with {@link Behavior#DO_NOTHING}.
     *
     * @param key
     *                the key of this {@link PathProperty}.
     */
    public PathProperty(String key)
    {
        this(key, Behavior.DO_NOTHING);
    }

    /**
     * Constructs an uneditable {@link PathProperty}.
     *
     * @param key
     *                     the key of this {@link PathProperty}.
     * @param behavior
     *                     the action that should be performed when {@link #get()} is called.
     */
    public PathProperty(String key, Behavior behavior)
    {
        this(key, behavior, false);
    }

    /**
     * Constructs a {@link PathProperty}.
     *
     * @param key
     *                     the key of this {@link PathProperty}.
     * @param behavior
     *                     the action that should be performed when {@link #get()} is called.
     * @param editable
     *                     whether this {@link PathProperty} can be edited by calling
     *                     {@link SystemProperty#set(Object)} instead of {@link SystemProperty#forceSet(String)}.
     */
    public PathProperty(String key, Behavior behavior, boolean editable)
    {
        super(key, editable);
        Checks.checkNotNull(behavior, "behavior should not be null");
        this.behavior = behavior;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public Path get()
    {
        String raw = getRaw();

        if (raw == null)
        {
            return null;
        }

        try
        {
            final Path path = Paths.get(raw);

            if (behavior == Behavior.ENSURE_FILE_EXISTS)
            {
                if (!Files.exists(path))
                {
                    ensureParentExists(path);
                    Files.createFile(path);
                }
            } else if (behavior == Behavior.ENSURE_DIRECTORY_EXISTS && !Files.exists(path))
            {
                Files.createDirectories(path);
            }

            return path.toAbsolutePath().normalize();
        } catch (InvalidPathException ex)
        {
            logger.warn("Invalid path: {}", raw, ex);
        } catch (IOException ex)
        {
            logger.warn("Failed to create: {}", raw, ex);
        }

        return null;
    }

    /**
     * Returns the action that should be performed when {@link #get()} is called.
     *
     * @return the action that should be performed when {@link #get()} is called.
     */
    public final Behavior getBehavior()
    {
        return behavior;
    }

    private void ensureParentExists(Path path) throws IOException
    {
        final Path parent = path.getParent();

        if (parent != null && !Files.exists(parent))
        {
            Files.createDirectories(parent);
        }
    }
}
