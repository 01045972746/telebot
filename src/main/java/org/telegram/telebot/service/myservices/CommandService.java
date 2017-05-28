package org.telegram.telebot.service.myservices;

import org.springframework.stereotype.Service;
import org.telegram.telebot.model.methods.SendMessage;
import org.telegram.telebot.service.exceptions.FailResponseMethodException;

import java.io.IOException;

/**
 * Created by user on 2017-05-28.
 */


public interface CommandService{
    public void defaultMessage(int chat_id) throws FailResponseMethodException;
    public void welcome(int chat_id, String name) throws FailResponseMethodException;
    public void getToken(int chat_id) throws FailResponseMethodException;
    public void getHelp(int chat_id) throws IOException, FailResponseMethodException;
}
