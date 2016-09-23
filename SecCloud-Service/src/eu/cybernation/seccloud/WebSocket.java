package eu.cybernation.seccloud;


import java.io.Console;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.framing.FrameBuilder;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eu.cybernation.seccloud.api.*;
import eu.cybernation.seccloud.annotations.*;

public class WebSocket extends WebSocketServer{

	public WebSocket( int port , Draft d ) throws UnknownHostException {
		super( new InetSocketAddress( port ), Collections.singletonList( d ) );
	}
	
	public WebSocket( InetSocketAddress address, Draft d ) {
		super( address, Collections.singletonList( d ) );
	}
	
	
	private static int counter = 0;
	

	@Override
	public void onOpen(org.java_websocket.WebSocket conn, ClientHandshake handshake) {
		// TODO Auto-generated method stub
		counter++;
		System.out.println( "///////////Opened connection number" + counter );
		
	}

	@Override
	public void onClose(org.java_websocket.WebSocket conn, int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		System.out.println( "closed" );
	}

	@Override
	public void onMessage(org.java_websocket.WebSocket conn, String message) {
		// TODO Auto-generated method stub
		
		String response = "test";
		System.out.println("received message: " + message);
		if(message.contains("getmethods")){
			System.out.println("get methods");
			Class metaInfoProvider = MetaInfoProvider.class;
			Method[] methods = metaInfoProvider.getMethods();
			ArrayList<JSONObject> mlist = new ArrayList<JSONObject>();
			for (Method method : methods){
				if(method.isAnnotationPresent(RemoteExecutable.class) && method.getAnnotation(RemoteExecutable.class).value() == true){
					JSONObject jObject = new JSONObject();
					jObject.clear();
					jObject.put("name", method.getName());
					
					//jObject.put("Paramter", method.getParameters());
					ArrayList<JSONObject> plist = new ArrayList<JSONObject>();
					for ( Parameter p :  method.getParameters()){
						JSONObject jObjPar = new JSONObject();
						jObjPar.put("name", p.getName());
						jObjPar.put("type", p.getType());
						plist.add(jObjPar);
					}
					jObject.put("paramters", plist);
					mlist.add(jObject);
				}
			}
			JSONArray jsArray = new JSONArray(mlist);
			response = jsArray.toJSONString();
		}
		else{
			JSONParser parser = new JSONParser();
			
			try {
				JSONObject req = (JSONObject)parser.parse(message);
				if( req.get("request-type") == "info" ){
					
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			
			response = "invalid request";
		}
		
		conn.send( response );
	}

	@Override
	public void onError(org.java_websocket.WebSocket conn, Exception ex) {
		// TODO Auto-generated method stub
		System.out.println( "Error:" );
		ex.printStackTrace();
	}
	
	
	
}
