package fr.tcchat.ihm;

import java.util.ArrayList;

import fr.tcchat.frame.FrameConnection;
import fr.tcchat.frame.FrameDefault;
import fr.tcchat.frame.FrameHome;
import fr.tcchat.frame.FrameInscription;

public class MainIHM {
	
	private FrameDefault currentFrame;
	
	public FrameConnection frameConnection;
	public FrameInscription frameInscription;
	public FrameHome frameHome;
	
	private ArrayList <FrameDefault> frame = new ArrayList();  


	public MainIHM()
	{
		this.frameConnection = new FrameConnection();
		this.frameInscription = new FrameInscription();
		this.frameHome = new FrameHome();
		
		frame.add(this.frameConnection);
		frame.add(this.frameInscription);
		frame.add(this.frameHome);
		
		this.show(this.frameConnection);
	}

	
	public void show(FrameDefault frame) {
		for (FrameDefault frameDefault : this.frame) {
			frameDefault.getFrame().setVisible(false);
		}
		
		this.setCurrentFrame(frame);
		frame.getFrame().setVisible(true);
	}
	
	
	private void setCurrentFrame(FrameDefault frame) {
		this.currentFrame = frame;
	}
	
	public FrameDefault getCurrentFrame() {
		return this.currentFrame;
	}
}
