package org.telegram.telebot.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.telegram.telebot.model.Document;
import org.telegram.telebot.model.Update;
import org.telegram.telebot.model.methods.SendDocument;
import org.telegram.telebot.model.methods.SendMessage;
import org.telegram.telebot.service.TelegramBotService;
import org.telegram.telebot.service.exceptions.FailResponseMethodException;

@Controller
public class MyWebhook {

	@Autowired
	TelegramBotService service;
	
	@RequestMapping(value = "/webhook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void myWebhook(@RequestBody Update update) throws FailResponseMethodException, IOException {
		if(update != null) {
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
			case "/getTest":
				URL url = new URL("https://downloads.meetinggear.com/prod_data/webapps/upload/board/2017/02/27/0a42469b-373b-4636-a625-c83ebcc8152a.pdf");
				String tDir = System.getProperty("java.io.tmpdir");
				String path = tDir + "tmp" + ".pdf";
				File file = new File(path);
				file.deleteOnExit();
				FileUtils.copyURLToFile(url, file);
				SendDocument sd = new SendDocument();
				sd.setFileToSend(file);
				service.sendDocument(sd);
				break;
		}
		
		
		
		}

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
