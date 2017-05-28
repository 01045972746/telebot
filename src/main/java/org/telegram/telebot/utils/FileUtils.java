package org.telegram.telebot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by user on 2017-05-28.
 */
public class FileUtils {
    public static String getFileFromURL(String url, String filename) {
        URL website = null;
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;

        try {

            website = new URL(url);
            rbc = Channels.newChannel(website.openStream());
            fos = new FileOutputStream(filename);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);


        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            website = null;
            if(rbc != null)
                try {
                    rbc.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if(fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        return filename;
    }

    public static String deleteFileFromLocal(String filename) {
        File file = new File(filename);

        if(file.delete()) {
            return "Success";
        }
        else {
            return "Failed";
        }

    }

}
