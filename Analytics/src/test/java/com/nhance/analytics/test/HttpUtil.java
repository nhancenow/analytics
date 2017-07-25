/**
 * @author vinoth
 *
 */
package com.nhance.analytics.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.nhance.analytics.model.Event;

public class HttpUtil {

	/**
	 * @param url
	 * @param param
	 * @return
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) throws InterruptedException {
		ObjectWriter ow = new ObjectMapper().writer();
		try {
			/*for (int i = 0; i < 5000; i++) {*/
				Event event = new Event();
				event.setEventName("Page_Visit");
				Map<String, Object> prop = new HashMap<String, Object>();
				prop.put("CustomerCode", "13000007");
				prop.put("MenuName", "Dashboard");
				prop.put("Name", "vinoth");
				prop.put("EmailId", "vinoth@gmail.com");
				prop.put("MobileNumber", "9620521051");
				prop.put("BrandCode", "BC_1004");
				prop.put("BrandName", "IPhone");
				prop.put("Os", "Web");
				prop.put("TrackEventTime", new Date());
				event.setProperties(prop);
				sendPost("http://127.0.0.1:8080/Analytics/send", ow.writeValueAsString(event));
//			}
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
