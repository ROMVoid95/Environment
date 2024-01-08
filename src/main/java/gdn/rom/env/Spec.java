package gdn.rom.env;

import java.lang.reflect.Field;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigSpec;
import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import com.electronwill.nightconfig.core.conversion.Path;

import lombok.Data;

public class Spec
{
    public static class RethinkSpec extends ConfigSpec
    {
        private static final RethinkSpec INSTANCE = new RethinkSpec();
        
        public static ConfigSpec get()
        {
            try {
                var inst = new Rethink();
                for(Field field : Rethink.class.getDeclaredFields())
                {
                    INSTANCE.define(field.getAnnotation(Path.class).value(), field.get(inst));
                }
                return INSTANCE;
            } catch (Exception e) {
                return INSTANCE;
            }
        }
    }

    @Data
    public static class Rethink
    {
        @Path("RethinkDB.hostname")
        private String hostname = "localhost";
        @Path("RethinkDB.username")
        private String username = "admin";
        @Path("RethinkDB.password")
        private String password = "";
        @Path("RethinkDB.port")
        private int port = 28015;
        
        public static Rethink fromConfig(Config config)
        {
            return new ObjectConverter().toObject(config, Rethink::new);
        }
        
        public static void toConfig()
        {
            var config = Config.inMemoryUniversalConcurrent();
            try {
                var inst = new Rethink();
                for(Field field : Rethink.class.getDeclaredFields())
                {
                    if(!config.contains(field.getAnnotation(Path.class).value()) && !config.isNull(field.getAnnotation(Path.class).value()))
                        config.set(field.getAnnotation(Path.class).value(), field.get(inst));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
