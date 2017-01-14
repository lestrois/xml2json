package com.walmart.gsm.xml2json;

public class XML2JSON {

	private KeyValueList kvl;

	public XML2JSON(){
	}
	public XML2JSON(String XML){
		if(XML == null || XML.length() == 0) return;
		kvl = new KeyValueList(XML.replace("n0:", ""));
	}

	public String getWalmartGSMJsonFromXML(){
		if(kvl == null || kvl.key == null || kvl.key.length() == 0) return "";
		StringBuilder sb = new StringBuilder();
		sb.append("[{");
		if(!kvl.isList){
			sb.append("\"" + kvl.key + "\": \"" + kvl.value + "\"");
		}else{
			for(int i = 0; i < kvl.list.size(); i++){
				dfs(kvl.list.get(i), sb);
				if(i != kvl.list.size() - 1){
					sb.append(",");
				}
			}
		}
		sb.append("}]");
		return sb.toString();
	}

	public static String getWalmartGSMJsonFromXML(String XML){
		if(XML == null || XML.trim().length() == 0) return "";
		KeyValueList kvl = new KeyValueList(XML.replace("n0:", ""));
		
		StringBuilder sb = new StringBuilder();
		sb.append("[{");
		if(!kvl.isList){
			sb.append("\"" + kvl.key + "\": \"" + kvl.value + "\"");
		}else{
			for(int i = 0; i < kvl.list.size(); i++){
				dfsStatic(kvl.list.get(i), sb);
				if(i != kvl.list.size() - 1){
					sb.append(",");
				}
			}
		}
		sb.append("}]");
		return sb.toString();
	}
	
	private static void dfsStatic(KeyValueList kvl, StringBuilder sb){
		if(!kvl.isList){
			if(kvl.value.equals("true") || kvl.value.equals("false")){
				sb.append("\"" + kvl.key + "\":" + kvl.value + "");
			}else{
				sb.append("\"" + kvl.key + "\":\"" + kvl.value + "\"");
			}
		}else{
			sb.append("\"" + kvl.key + "\":{");
			for(int i = 0; i < kvl.list.size(); i++){
				dfsStatic(kvl.list.get(i), sb);
				if(i != kvl.list.size() - 1){
					sb.append(",");
				}
			}
			sb.append("}");
		}
	}

	private void dfs(KeyValueList kvl, StringBuilder sb){
		if(!kvl.isList){
			if(kvl.value.equals("true") || kvl.value.equals("false")){
				sb.append("\"" + kvl.key + "\":" + kvl.value + "");
			}else{
				sb.append("\"" + kvl.key + "\":\"" + kvl.value + "\"");
			}
		}else{
			sb.append("\"" + kvl.key + "\":{");
			for(int i = 0; i < kvl.list.size(); i++){
				dfs(kvl.list.get(i), sb);
				if(i != kvl.list.size() - 1){
					sb.append(",");
				}
			}
			sb.append("}");
		}
	}

}
