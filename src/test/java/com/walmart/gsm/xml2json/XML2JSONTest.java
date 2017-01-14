package com.walmart.gsm.xml2json;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class XML2JSONTest {
	private final int MAX_TEST_CASE_AMOUNT = 6;

	@Test
	public void testGetWalmartGSMJsonFromXML() throws IOException {
		for(int i = 1; i <= MAX_TEST_CASE_AMOUNT; i++){
			String file = "src/test/resources/case" + i + "/sample.xml";
			String xml = getStringFromFile(file);	//get XML String;
			XML2JSON x2j = new XML2JSON(xml);
			String res = x2j.getWalmartGSMJsonFromXML();
			String result = getStringFromFile("src/test/resources/case" + i + "/sample.json");	//get Result String
			if(result == null) result = "";
			assertEquals(result, res);
		}
	}

	@Test
	public void testGetWalmartGSMJsonFromXMLString() throws IOException {
		for(int i = 1; i <= MAX_TEST_CASE_AMOUNT; i++){
			String file = "src/test/resources/case" + i + "/sample.xml";
			String xml = getStringFromFile(file);	//get XML String;
			String res = XML2JSON.getWalmartGSMJsonFromXML(xml);
			String result = getStringFromFile("src/test/resources/case" + i + "/sample.json");	//get Result String
			assertEquals(result, res);
		}
	}
	
	private String getStringFromFile(String file) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String ln = "";
		StringBuilder sb = new StringBuilder();
		try {
			while((ln = br.readLine()) != null){
				sb.append(ln);
				sb.append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(br != null){
			br.close();
		}
		return sb.toString().trim();
	}

}
