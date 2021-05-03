package fr.tcchat.packet;

import java.util.HashMap;
import java.util.Map;

public enum EnumPacket {
	
	/* Client sender */
	UNKNOW("unknow", 0),
	CONNECTION("Connection", 1),
	REGISTRATION("Registration", 2),
	MESSAGE("Message", 3),
	GET_MESSAGES("Get_messages", 4),
	DISCONNECTION("Disconnection", 5),
	GET_USERS("Get_users", 6),
	CREATE_GROUP("Create_group", 7),
	LEAVE_GROUP("Leave_group", 8),
	EDIT_PROFIL("Edit_profil", 9),
	
	/* Server sender */
	CONNECTION_VALID("Connection_valid", 101),
	CONNECTION_INVALID("Connection_invalid", 102),
	REGISTRATION_VALID("Registration_valid", 103),
	REGISTRATION_INVALID("Registration_invalid", 104),
	INITIALIZE_GROUPS("Initialize_groups", 105),
	INITIALIZE_MESSAGES("Initialize_messages", 106),
	SEND_MESSAGE("Send_message", 107),
	SEND_USERS("Send_users", 108),
	INITIALIZE_GROUP("Initialize_group", 109),
	LOAD_GROUP("Load_group", 110),
	UPDATE_GROUP("Update_group", 111),
	UPDATE_PROFIL("Update_profil", 112),

	;

	EnumPacket(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
    private static final Map<Integer, EnumPacket> enumPacket = new HashMap<Integer, EnumPacket>();

    static {
        for (EnumPacket p : EnumPacket.values()) {
        	enumPacket.put(p.getID(), p);
        }
    }
	
	private String name;
	private int id;
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static EnumPacket getByID(int id)
	{
		return enumPacket.get(id);
	}
}
