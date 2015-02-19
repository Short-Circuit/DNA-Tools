package com.calebmilligan.dnatools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;

public class Download {
    public static void saveDefaults(){
        try{
            File run = new File("run.bat");
            if(!run.exists()){
                run.createNewFile();
                InputStream runStream = DNA_Tools.class.getClassLoader().getResourceAsStream("run.bat");
                FileUtils.copyInputStreamToFile(runStream, run);
                runStream.close();
            }
            File config = new File("config.yml");
            if(!config.exists()){
                config.createNewFile();
                InputStream configStream = DNA_Tools.class.getClassLoader().getResourceAsStream("config.yml");
                FileUtils.copyInputStreamToFile(configStream, config);
                configStream.close();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
