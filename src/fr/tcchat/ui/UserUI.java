package fr.tcchat.ui;

public class UserUI {
	private int id;
	private String username;
	
	public UserUI(int id, String username) {
		this.setId(id);
		this.setUsername(username);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
