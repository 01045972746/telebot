package org.telegram.telebot.model.inline;

import java.io.Serializable;

import org.telegram.telebot.model.Location;
import org.telegram.telebot.model.User;

public class ChosenInlineResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6109753490294422827L;

	private String result_id;
	private User from;
	private Location location;
	private String inline_message_id;
	private String query;

	public String getResult_id() {
		return result_id;
	}

	public void setResult_id(String result_id) {
		this.result_id = result_id;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getInline_message_id() {
		return inline_message_id;
	}

	public void setInline_message_id(String inline_message_id) {
		this.inline_message_id = inline_message_id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}