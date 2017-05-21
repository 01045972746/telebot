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
	

}
