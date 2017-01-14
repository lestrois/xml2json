package com.walmart.gsm.xml2json;

import java.util.ArrayList;
import java.util.List;

public class KeyValueList {
	public String key;
	public String value;
	public boolean isList;
	public List<KeyValueList> list;
	public KeyValueList(String xml){
		xml = xml.trim();
		if(xml.length() >= 5 && xml.substring(0, 5).equals("<?xml")){
			int ind = xml.indexOf("?>");
			xml = xml.substring(ind + 2).trim();
		}
		key = getKey(xml);
		String body = getBody(key, xml).trim();
		if(body.equals("") || body.charAt(0) != '<'){
			isList = false;
			value = body;
		}else{
			isList = true;
			list = getKVList(body);
		}
	}

	private List<KeyValueList> getKVList(String xml){
		List<KeyValueList> res = new ArrayList<KeyValueList>();
		while(!xml.equals("")){
			xml = xml.trim();
			String key = getKey(xml);
			int nullInd = xml.indexOf("<" + key + "/>");
			int nextInd = -1;
			if(nullInd == 0){
				nextInd = key.length() + 3;
				res.add(new KeyValueList("<" + key + "/>"));
			}else{
				int ind = xml.indexOf("</" + key + ">", key.length() + 2);
				nextInd = ind + key.length() + 3;
				res.add(new KeyValueList(xml.substring(0, nextInd)));
			}
			xml = xml.substring(nextInd);
		}
		return res;
	}
	
	private String getKey(String xml){
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < xml.length(); i++){
			if(xml.charAt(i) != '>' && xml.charAt(i) != '/' && xml.charAt(i) != ' '){
				sb.append(xml.charAt(i));
			}else{
				break;
			}
		}
		return sb.toString();
	}
	
	private String getBody(String key, String xml){
		int nullInd = xml.indexOf("<" + key + "/>");
		if(nullInd == 0) return "";
		int ind2 = xml.indexOf(">");
		int ind = xml.indexOf("</" + key + ">", ind2 + 1);
		return xml.substring(ind2 + 1, ind);
	}
}
