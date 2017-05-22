package org.telegram.telebot.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.telegram.telebot.model.Update;
import org.telegram.telebot.model.methods.SendDocument;
import org.telegram.telebot.model.methods.SendMessage;
import org.telegram.telebot.service.Requests;
import org.telegram.telebot.service.TelegramBotService;
import org.telegram.telebot.service.exceptions.FailResponseMethodException;
import org.telegram.telebot.service.myservices.MyTelegramService;

@Controller
public class MyWebhook {

	@Autowired
	TelegramBotService service;
	
	@Autowired
	MyTelegramService myservice;
	
	@RequestMapping(value = "/webhook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void myWebhook(@RequestBody Update update) throws FailResponseMethodException, IOException {
		if(update.getMessage() != null) {
		int chat_id = update.getMessage().getChat().getId();
		if(chat_id < 0) {
			return;
		}
		String chat_text = update.getMessage().getText();
		SendMessage msg = new SendMessage();
		msg.setChat_id(chat_id);
		switch(chat_text) {
			case "/welcome":
				msg.setText("Hi, "+update.getMessage().getChat().getFirst_name());
				service.sendMessage(msg);
				break;
			case "/gettoken":
				msg.setText(service.getTokenBot());
				service.sendMessage(msg);
				break;
			case "/gethelp":
				msg.setText("Plz Wait for downloading.....");
				service.sendMessage(msg);
				
				String filename = myservice.getFileFromURL("https://downloads.meetinggear.com/prod_data/webapps/upload/board/2017/02/27/0a42469b-373b-4636-a625-c83ebcc8152a.pdf", "meetinggear.pdf");
				File f = new File(filename);
				
				SendDocument sd = new SendDocument();
				sd.setChat_id(chat_id);
				sd.setCaption("Meetinggear");
				sd.setFileToSend(f);
				service.sendDocument(sd);
				
				if(myservice.deleteFileFromLocal(filename).equals("Success")) {
					System.out.println("Success!");
				}else {
					System.out.println("Failed!");
				}
				
				break;
		}
		
		}
		
		return ;
	}
	
	/**
	 * From this JSP you can register webhooks on telegram
	 * @return
	 */
	@RequestMapping(value = "/registerbot", method = RequestMethod.GET)
	public String registerBot() {
		return "register";
	}
	
}
