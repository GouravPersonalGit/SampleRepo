package automationFramework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader {
	public static Object getFileLocation()
			throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		JSONParser parser = new JSONParser();
		return parser.parse(new FileReader(Config.fol_jasonFilePath));
	}

	public static String getParameterString(String key, String node)
			throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		Object obj = getFileLocation();
		JSONArray array = new JSONArray();
		array.add(obj);
		JSONObject jsonObject = (JSONObject) array.get(0);
		JSONObject record = (JSONObject) jsonObject.get(node);
		String value = (String) record.get(key);
		return value;
	}
}
