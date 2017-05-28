package org.telegram.telebot.controller;

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
import org.telegram.telebot.service.exceptions.FailResponseMethodException;
import org.telegram.telebot.service.myservices.CommandService;

@Controller
public class MyWebhook {

	@Autowired
	private CommandService commandService;
	
	@RequestMapping(value = "/webhook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void myWebhook(@RequestBody Update update) throws FailResponseMethodException, IOException {
		if(update.getMessage() != null) {
		int chat_id = update.getMessage().getChat().getId();
		if(chat_id < 0) {
			return;
		}
		String chat_text = update.getMessage().getText();

		switch(chat_text) {
			case "/welcome":
				commandService.welcome(chat_id, update.getMessage().getChat().getFirst_name());
				break;
			case "/gettoken":
				commandService.getToken(chat_id);
				break;
			case "/gethelp":
				commandService.getHelp(chat_id);
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
