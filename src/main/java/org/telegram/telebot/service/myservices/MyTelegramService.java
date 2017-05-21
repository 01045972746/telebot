package org.telegram.telebot.service.myservices;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Service;
import org.telegram.telebot.service.TelegramBotService;

@Service
public class MyTelegramService extends TelegramBotService{

	@Override
	public String getTokenBot() {
		String myBotToken = "304855908:AAHkGwCOIwdEY2JPlXNpqC5KDiW6b-iCBWE";
		return myBotToken;
	}
	
	public byte[] test() throws IOException {
		URL u = new URL("http://www.java2s.com/binary.dat");
	    URLConnection uc = u.openConnection();
	    String contentType = uc.getContentType();
	    int contentLength = uc.getContentLength();
	    if (contentType.startsWith("text/") || contentLength == -1) {
	      throw new IOException("This is not a binary file.");
	    }
	    InputStream raw = uc.getInputStream();
	    InputStream in = new BufferedInputStream(raw);
	    byte[] data = new byte[contentLength];
	    int bytesRead = 0;
	    int offset = 0;
	    while (offset < contentLength) {
	      bytesRead = in.read(data, offset, data.length - offset);
	      if (bytesRead == -1)
	        break;
	      offset += bytesRead;
	    }
	    in.close();
	
	    if (offset != contentLength) {
	      throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
	    }
	    String filename ="";
	    filename = u.getFile().substring(filename.lastIndexOf('/') + 1);
	    FileOutputStream out = new FileOutputStream(filename);
	    out.write(data);
	    out.flush();
	    out.close();
	    
	    return data;
	  }
	

}
