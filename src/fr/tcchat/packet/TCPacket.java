package fr.tcchat.packet;

import fr.tcchat.config.Config;
import fr.tcchat.packet.EnumPacket;

public class TCPacket {
	
	private EnumPacket packetType;
	private String data;

	public TCPacket(int ID, String data)
	{
		this.packetType = EnumPacket.getByID(ID);
		this.data = data;
	}
	
	public TCPacket(EnumPacket type)
	{
		this.packetType = type;
	}
	
	/* Get type 
	 * 
	 * @returns {packetType} The type
	 * */
	public EnumPacket getType()
	{
		return this.packetType;
	}
	
	/* Get data 
	 * 
	 * @returns {String} The data
	 * */
	public String getData() 
	{
		return this.data;
	}
	
	/* Write packet
	 * 
	 * @param [] {Object} They objects
	 * 
	 * @returns {String} The formated data 
	 * */
	public String write(Object... datas)
	{
		String result = String.valueOf(this.getType().getID());
		result += Config.SEPARATOR_PACKET;
		
		for(Object data : datas) {
			data = String.valueOf(data);
			
			result += data + Config.SEPARATOR_DATA;
		}
		
		return result.substring(0, result.length() - Config.SEPARATOR_DATA.length());
	}
	
	
	/* Write packet
	 * 
	 * @returns {String} The formated data 
	 * */
	public String writeNull()
	{
		String result = String.valueOf(this.getType().getID());
		result += Config.SEPARATOR_PACKET;
		return result;
	}
}
