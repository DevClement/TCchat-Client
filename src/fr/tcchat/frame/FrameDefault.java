package fr.tcchat.frame;

import java.awt.Window;

import fr.tcchat.packet.EnumPacket;

public abstract class FrameDefault {
	
	public abstract void displayError(EnumPacket type, String message);
	
	public abstract void valid(EnumPacket type);
	
	public abstract void valid(EnumPacket type, String data);

	public abstract Window getFrame();

}
