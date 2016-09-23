package eu.cybernation.seccloud;

import java.net.UnknownHostException;

import org.java_websocket.drafts.Draft_17;

public class Main {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		System.out.println("starting ws server ...");
		WebSocket ws = new WebSocket( 8080, new Draft_17() );
		ws.start();
		System.out.println("ws server is listing on " + ws.getAddress());
		System.out.print("closing ws server ...");
		

	}

}
