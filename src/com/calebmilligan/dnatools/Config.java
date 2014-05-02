package com.calebmilligan.dnatools;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Config{
    @SuppressWarnings("unchecked")
    public static Map<String, String> configure(){
        try{
            File file = new File("config.yml");
            FileInputStream stream = new FileInputStream(file);
            Yaml yam = new Yaml();
            Map<String, String> map = (Map<String, String>)yam.load(stream);
            stream.close();
            return map;
        }
        catch(Exception e){
            return null;
        }
    }
}