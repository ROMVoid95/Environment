package gdn.rom.env;

import com.electronwill.nightconfig.core.file.FileConfig;

import gdn.rom.env.Spec.Rethink;

public class Loader
{
    public static void main(String[] args)
    {        
        //var config = EnvConfig.local("env");
        var c2 = FileConfig.of("env.toml");
        
        System.out.println(c2.toString());
        
        //var rethink = Rethink.fromConfig(config);
        
        //System.out.println(rethink.getHostname());
    }
}
