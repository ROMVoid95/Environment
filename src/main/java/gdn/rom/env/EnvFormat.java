package gdn.rom.env;

import java.time.temporal.Temporal;
import java.util.Map;
import java.util.function.Supplier;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.file.FormatDetector;
import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.core.io.ConfigWriter;
import com.electronwill.nightconfig.toml.TomlFormat;
import com.electronwill.nightconfig.toml.TomlParser;
import com.electronwill.nightconfig.toml.TomlWriter;

public class EnvFormat implements ConfigFormat<CommentedConfig>
{
    private final static EnvFormat INSTANCE = new EnvFormat();
    
    /**
     * @return the unique instance of TomlFormat
     */
    public static EnvFormat instance() {
        return INSTANCE;
    }
    
    static {
        FormatDetector.registerExtension("local", INSTANCE);
        FormatDetector.registerExtension("production", INSTANCE);
    }
    
    /**
     * @return a new config with the toml format
     */
    public static CommentedConfig newConfig() {
        return newConcurrentConfig();
    }

    /**
     * @return a new config with the given map creator
     */
    public static CommentedConfig newConfig(Supplier<Map<String, Object>> s) {
        return TomlFormat.instance().createConfig(s);
    }

    /**
     * @return a new thread-safe config with the toml format
     */
    private static CommentedConfig newConcurrentConfig() {
        return TomlFormat.instance().createConcurrentConfig();
    }
    
    @Override
    public ConfigWriter createWriter()
    {
        return new TomlWriter();
    }

    @Override
    public ConfigParser<CommentedConfig> createParser()
    {
        return new TomlParser();
    }

    @Override
    public CommentedConfig createConfig(Supplier<Map<String, Object>> mapCreator)
    {
        return CommentedConfig.of(mapCreator, this);
    }

    @Override
    public boolean supportsComments()
    {
        return true;
    }

    @Override
    public boolean supportsType(Class<?> type) {
        return type != null && (ConfigFormat.super.supportsType(type) || Temporal.class.isAssignableFrom(type));
    }
}
