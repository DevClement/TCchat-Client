package fr.tcchat.ui;

import fr.tcchat.config.Config;

public class ConversationsUI {
	private int id;
	
	private String name;
	private String lastMessage;
	
	private final int MAX_LENGTH_MESSAGE = 36;
	
	public ConversationsUI(int id, String name, String lastMessage) {
		this.setId(id);
		this.setName(name);
		this.setLastMessage(lastMessage);
	}
	
	public ConversationsUI(String data) {
		String[] myGroup = data.split(Config.SEPARATOR_ARRAY);
		this.setId(Integer.parseInt(myGroup[0]));
		this.setName(myGroup[1]);
		this.setLastMessage(myGroup[2]);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		if(lastMessage.length() > this.MAX_LENGTH_MESSAGE) {
			lastMessage = lastMessage.substring(0, this.MAX_LENGTH_MESSAGE -3) + "..";
		}
		this.lastMessage = lastMessage;
	}
}
