package org.telegram.telebot.service.myservices;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.springframework.stereotype.Service;
import org.telegram.telebot.service.TelegramBotService;

@Service
public class MyTelegramService extends TelegramBotService{

	@Override
	public String getTokenBot() {
		String myBotToken = "304855908:AAHkGwCOIwdEY2JPlXNpqC5KDiW6b-iCBWE";
		return myBotToken;
	}
	
	public String getFileFromURL(String url, String filename) {
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
	
	public String deleteFileFromLocal(String filename) {
		File file = new File(filename);
		
		if(file.delete()) {
			return "Success";
		}
		else {
			return "Failed";
		}
		
	}
	

}
