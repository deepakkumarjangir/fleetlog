/**
 * FleetLog
 * May 26, 2019 2:59:20 AM
 * @author Deepak Daneva
 */
package com.deepakdaneva.fleetlog.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtility {
	
	private JSONUtility() {}
	
	public static Map<String, Object> jsonToMap(JSONObject jsonObject) throws JSONException {
		Map<String, Object> map = new HashMap<>();

		if (jsonObject != JSONObject.NULL) {
			map = toMap(jsonObject);
		}
		return map;
	}

	public static Map<String, Object> toMap(JSONObject jsonObject) throws JSONException {
		Map<String, Object> map = new HashMap<>();
		Iterator<String> keysIterator = jsonObject.keys();
		while (keysIterator.hasNext()) {
			String key = keysIterator.next();
			Object object = jsonObject.get(key);
			if (object instanceof JSONArray) {
				object = toList((JSONArray) object);
			} else if (object instanceof JSONObject) {
				object = toMap((JSONObject) object);
			}
			map.put(key, object);
		}
		return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<>();
		for (int i = 0; i < array.length(); i++) {
			Object object = array.get(i);
			if (object instanceof JSONArray) {
				object = toList((JSONArray) object);
			} else if (object instanceof JSONObject) {
				object = toMap((JSONObject) object);
			}
			list.add(object);
		}
		return list;
	}
}
