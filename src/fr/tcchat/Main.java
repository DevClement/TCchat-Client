package fr.tcchat;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import fr.tcchat.config.Config;
import fr.tcchat.config.ConfigFile;
import fr.tcchat.frame.FramePopup;
import fr.tcchat.ihm.MainIHM;
import fr.tcchat.packet.EnumPacket;
import fr.tcchat.packet.TCPacket;

public class Main {
	
	private static Main instance;
	
	private static final String host = "51.75.246.53";
	private static final int port = 25565;
	
	private static Socket connection = null;
	private static PrintWriter writer = null;
	private static BufferedInputStream reader = null;
	private static ConfigFile config = null;
	
	private static MainIHM ihm;
	
	public static void main(String[] args) 
	{
		instance = new Main();
		
		startConnection();
		
		config = new ConfigFile();
		
		ihm = new MainIHM();
			
	    try {
	    	writer = new PrintWriter(connection.getOutputStream(), true);
            reader = new BufferedInputStream(connection.getInputStream());
                        
            while(true) {
                TCPacket response = Main.getInstance().read();

   				switch(response.getType()) {
   					case CONNECTION_VALID:
   						System.out.println("Connexion Valid");
  						Main.getInstance().getIHM().getCurrentFrame().valid(response.getType());
   						//Main.getInstance().sendPacket(new TCPacket(EnumPacket.MESSAGE).write("Salut les boloss", 1));
   						break;
   						
  					case CONNECTION_INVALID:
  						System.out.println("Connexion invalide");
  						Main.getInstance().getIHM().getCurrentFrame().displayError(response.getType(), response.getData());
   						break;
   						
  					case REGISTRATION_VALID:
  						System.out.println("Inscription Valid");
  						System.out.println(response.getData());
  						//Main.getInstance().getIHM().getCurrentFrame().valid(response.getType());
   						break;
   						
  					case REGISTRATION_INVALID:
  						System.out.println("Inscription invalide");
  						Main.getInstance().getIHM().getCurrentFrame().displayError(response.getType(), response.getData());
   						break;
   						
  					case INITIALIZE_GROUPS:
  						Main.getInstance().getIHM().getCurrentFrame().valid(response.getType(), response.getData());
  						break;
  						
  					case SEND_MESSAGE:
  						Main.getInstance().getIHM().getCurrentFrame().valid(response.getType(), response.getData());
  						break;
  					case INITIALIZE_MESSAGES:
  						Main.getInstance().getIHM().getCurrentFrame().valid(response.getType(), response.getData());
  						break;
   						
					case UNKNOW:
						System.out.println("Packet pas connu " + response.getData());
						break;
						
					case SEND_USERS:
						String[] userCompact = response.getData().split(Config.SEPARATOR_DATA);
						
						HashMap<Integer, String> users = new HashMap<Integer, String>();
						
						for(int i = 0; i < userCompact.length; i+=2) {
							try {
								users.put(Integer.parseInt(userCompact[i]), userCompact[i+1]);
							} catch (NumberFormatException e) {
							}
						}
						FramePopup.getInstance().updateSelector(users);
						break;
						
					case INITIALIZE_GROUP: 
						Main.getInstance().getIHM().getCurrentFrame().valid(response.getType(), response.getData());
					case LOAD_GROUP: 
						Main.getInstance().getIHM().getCurrentFrame().valid(response.getType(), response.getData());
					case UPDATE_GROUP: 
						Main.getInstance().getIHM().getCurrentFrame().valid(response.getType(), response.getData());
						break;
						
					default:
						break;
				}
            }
            
        } catch (IOException e1) {
            e1.printStackTrace();
        }
		
	}
	
	private static void startConnection()
	{
		try {
			connection = new Socket(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
   	public boolean sendPacket(String packetContent)
   	{
   		try {
   			writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8), true);
   		
			writer.write(packetContent);
			writer.flush();
			
			return true;
		
		} catch (IOException e) {
			return false;
		}
   	}
	
   	private TCPacket read() throws IOException{      
   		String response = "";
   		int stream;
   		byte[] b = new byte[4096];
   		stream = reader.read(b);
   		response = new String(b, 0, stream, StandardCharsets.UTF_8);
   		
   		String[] responsePart = response.split(Config.SEPARATOR_PACKET);
   		
   		if(responsePart.length == 1 ) {
   	   		return new TCPacket(Integer.parseInt(responsePart[0]), "");
   		}
   		return new TCPacket(Integer.parseInt(responsePart[0]), responsePart[1]);
   	}
	
	public static Main getInstance() 
	{
		return instance;
	}
	
	public MainIHM getIHM() {
		return ihm;
	}
	
	public ConfigFile getConfig() {
		return config;
	}
}
