package fr.tcchat.ui;

import fr.tcchat.config.Config;

public class MessagesUI {
	private int id;
	
	private UserUI sender;
	private String message;
	
	private boolean myMessage;
	
	public MessagesUI(int id, UserUI sender, String message, boolean myMessage) {
		this.setId(id);
		this.setSender(sender);
		this.setMessage(message);
		this.setMyMessage(myMessage);
	}
	
	public MessagesUI(String data) {
		String[] myMessage = data.split(Config.SEPARATOR_ARRAY);
		this.setId(Integer.parseInt(myMessage[0]));
		this.setSender(new UserUI(Integer.parseInt(myMessage[1]), myMessage[2]));
		this.setMessage(myMessage[3]);
		this.setMyMessage(Boolean.parseBoolean(myMessage[4]));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserUI getSender() {
		return sender;
	}

	public void setSender(UserUI sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isMyMessage() {
		return myMessage;
	}

	public void setMyMessage(boolean myMessage) {
		this.myMessage = myMessage;
	}
}
