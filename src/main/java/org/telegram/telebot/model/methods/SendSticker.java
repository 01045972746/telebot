package org.telegram.telebot.model.methods;

import java.io.File;
import java.io.Serializable;

import org.telegram.telebot.model.ReplyKeyboardMarkup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SendSticker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -647797927452274549L;

	// TODO: See how to do with String and Integer
	private Integer chat_id;
	// sticker file_id to resend
	private String sticker;
	private File fileToSend;
	private Boolean disable_notification = false;
	private Integer reply_to_message_id;
	private ReplyKeyboardMarkup reply_markup;

	public Integer getChat_id() {
		return chat_id;
	}

	public void setChat_id(Integer chat_id) {
		this.chat_id = chat_id;
	}

	public String getSticker() {
		return sticker;
	}

	public void setSticker(String sticker) {
		this.sticker = sticker;
	}

	public Boolean getDisable_notification() {
		return disable_notification;
	}

	public void setDisable_notification(Boolean disable_notification) {
		this.disable_notification = disable_notification;
	}

	public Integer getReply_to_message_id() {
		return reply_to_message_id;
	}

	public void setReply_to_message_id(Integer reply_to_message_id) {
		this.reply_to_message_id = reply_to_message_id;
	}

	public ReplyKeyboardMarkup getReply_markup() {
		return reply_markup;
	}

	public void setReply_markup(ReplyKeyboardMarkup reply_markup) {
		this.reply_markup = reply_markup;
	}
	
	@JsonIgnore
	@JsonProperty(value = "fileToSend")
	public File getFileToSend() {
		return fileToSend;
	}

	@JsonProperty(value = "fileToSend")
	public void setFileToSend(File fileToSend) {
		this.fileToSend = fileToSend;
	}

}
