package eu.cybernation.seccloud.api;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;


import org.json.simple.JSONArray;

import eu.cybernation.seccloud.datatypes.*;
import eu.cybernation.seccloud.annotations.*;

public class MetaInfoProvider {
	
	@RemoteExecutable(true)
	public JSONArray listFiles(){
		return listFiles("/");
		
		
	}

	@RemoteExecutable(true)
	public JSONArray listFiles(String path){
		
		JSONArray js = new JSONArray();
		
		CloudFile cf1 = new CloudFile();
		CloudFile cf2 = new CloudFile();
		CloudFile cf3 = new CloudFile();
		
		cf1.fullPath = "/TestIMG.jpg";
		cf1.name = "TestIMG.jpg";
		cf1.lastChange = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(12));
		cf1.createdAt = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(12));
		
		cf1.fullPath = "/HowToUse.odt";
		cf2.name = "HowToUse.odt";
		cf2.lastChange = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(12));
		cf2.createdAt = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(12));
		
		cf3.fullPath = "/readme.txt";
		cf3.name = "readme.txt";
		cf3.lastChange = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(12));
		cf3.createdAt = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(12));
		
		js.add(cf1);
		js.add(cf2);
		js.add(cf3);
		
		return  js;
	}

}
