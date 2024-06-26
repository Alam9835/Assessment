package org.parsejson.parseString;



import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

//
//import org.json.JSONArray;
//import org.json.JSONObject;


public class App 
{
	public static void main(String[] args) {

		String inputJson = "{\"name\":\"Alam\",\"age\":22,\"addresses\":[{\"type\":\"home\",\"street\":\"123 Main St\",\"city\":\"Dhanbad\"},{\"type\":\"work\",\"street\":\"456 Park Ave\",\"city\":\"Ranchi\"}]}";

		JSONObject inputJSONOBject = new JSONObject(inputJson);

		getKey(inputJSONOBject, "addresses");

	}
    
    
    public static void parseObject(JSONObject json, String key) {
		// System.out.println(json.has(key));
		System.out.println(json.get(key));
	}
    
    
    public static void getKey(JSONObject json, String key) {

		boolean exists = json.has(key);
		Iterator<?> keys;
		String nextKeys;

		if (!exists) {
			keys = json.keys();
			while (keys.hasNext()) {
				nextKeys = (String) keys.next();
				try {

					if (json.get(nextKeys) instanceof JSONObject) {

						if (exists == false) {
							getKey(json.getJSONObject(nextKeys), key);
						}

					} else if (json.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonarray = json.getJSONArray(nextKeys);
						for (int i = 0; i < jsonarray.length(); i++) {
							String jsonarrayString = jsonarray.get(i).toString();
							JSONObject innerJSOn = new JSONObject(jsonarrayString);

							if (exists == false) {
								getKey(innerJSOn, key);
							}

						}

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		} else {
			parseObject(json, key);
		}

	}
    
}

