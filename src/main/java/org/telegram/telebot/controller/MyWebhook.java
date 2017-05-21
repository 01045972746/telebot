package org.telegram.telebot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.telegram.telebot.model.Update;
import org.telegram.telebot.model.methods.SendMessage;
import org.telegram.telebot.service.TelegramBotService;
import org.telegram.telebot.service.exceptions.FailResponseMethodException;

@Controller
public class MyWebhook {

	@Autowired
	TelegramBotService service;
	
	@RequestMapping(value = "/webhook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void myWebhook(@RequestBody Update update) throws FailResponseMethodException {
		System.out.println(update.getMessage().getText());
		if(update != null) {
		int chat_id = update.getMessage().getChat().getId();
		if(chat_id < 0) {
			return;
		}
		String chat_text = update.getMessage().getText();
		System.out.println(update.getMessage().getChat().getId());
		System.out.println(update.getMessage().getChat().getFirst_name());
		System.out.println(update.getMessage().getText());
		
		
		if(chat_text.equals("/welcome")) {
			SendMessage msg = new SendMessage();
			msg.setChat_id(chat_id);
			msg.setText("Hi, "+update.getMessage().getChat().getFirst_name());
			service.sendMessage(msg);
			return;
		}
		
		if(chat_text.equals("/test1")) {
			SendMessage msg = new SendMessage();
			msg.setChat_id(chat_id);
			msg.setText("Hi, Mister John Doetest");
			service.sendMessage(msg);
			return;
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
