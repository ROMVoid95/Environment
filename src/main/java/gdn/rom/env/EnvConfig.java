package gdn.rom.env;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileConfig;

public class EnvConfig
{
    public static FileConfig local(String fileName)
    
    {
        var config = buildConfig(fileName, "local");
        config.load();
        return config;
    }

    public static FileConfig production(String fileName)
    {
        var config = buildConfig(fileName, "production");
        config.load();
        return config;
    }

    private static FileConfig buildConfig(String file, String type)
    {
        return CommentedFileConfig.builder("%s.%s".formatted(file, type), EnvFormat.instance()).concurrent().sync().autoreload().autosave().build();
    }
}
