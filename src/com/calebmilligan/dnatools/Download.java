package com.calebmilligan.dnatools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class Download {
    public static String drive = "https://dl.dropboxusercontent.com/s/1vcq3vk4yat9gcv/Current%20Version.txt?dl=1&token_hash=AAGzIl7LNSPU0hLDJj928WWzHV_fiB9_rh3KZo0qEDgcnw";
    public static void download(String url, String file) throws MalformedURLException, IOException{
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
    }
    public static String checkUpdates() throws MalformedURLException, IOException{
        URL url = new URL(drive);
        Scanner scan = new Scanner(url.openStream());
        String line = scan.next();
        scan.close();
        return line;
    }
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
