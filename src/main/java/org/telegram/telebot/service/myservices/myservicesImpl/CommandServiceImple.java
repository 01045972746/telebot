package org.telegram.telebot.service.myservices.myservicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telebot.model.User;
import org.telegram.telebot.model.methods.SendDocument;
import org.telegram.telebot.model.methods.SendMessage;
import org.telegram.telebot.service.TelegramBotService;
import org.telegram.telebot.service.myservices.CommandService;
import org.telegram.telebot.service.myservices.MyTelegramService;
import org.telegram.telebot.service.exceptions.FailResponseMethodException;
import org.telegram.telebot.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by user on 2017-05-28.
 */
public class CommandServiceImple implements CommandService{

    @Autowired
    private TelegramBotService teleService;
    @Autowired
    private MyTelegramService myService;


    @Override
    public void welcome(int chat_id, String name) throws FailResponseMethodException {
        SendMessage msg = new SendMessage();
        msg.setChat_id(chat_id);
        msg.setText("Hi, "+name);
        teleService.sendMessage(msg);
    }

    @Override
    public void getToken(int chat_id) throws FailResponseMethodException {
        SendMessage msg = new SendMessage();
        msg.setChat_id(chat_id);
        msg.setText(myService.getTokenBot());
        teleService.sendMessage(msg);
    }

    @Override
    public void getHelp(int chat_id) throws IOException, FailResponseMethodException {
        SendMessage msg = new SendMessage();
        msg.setChat_id(chat_id);
        msg.setText("Plz Wait for downloading.....");
        teleService.sendMessage(msg);

        String filename = FileUtils.getFileFromURL("https://downloads.meetinggear.com/prod_data/webapps/upload/board/2017/02/27/0a42469b-373b-4636-a625-c83ebcc8152a.pdf", "meetinggear.pdf");
        File f = new File(filename);

        SendDocument sd = new SendDocument();
        sd.setChat_id(chat_id);
        sd.setCaption("Meetinggear");
        sd.setFileToSend(f);
        teleService.sendDocument(sd);

        if(FileUtils.deleteFileFromLocal(filename).equals("Success")) {
            System.out.println("Success!");
        }else {
            System.out.println("Failed!");
        }


    }
}
