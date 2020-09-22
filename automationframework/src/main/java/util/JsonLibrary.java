package util;

import com.google.gson.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.json.JsonOutput;

import java.io.FileReader;
import java.util.*;

public class JsonLibrary {

    public static HashMap<String, List<String>> getLocators(String locatorpage) {
        JSONParser parser = new JSONParser();
        Map<String, List<String>> locatorList = new HashMap<>();
        try {
            Object obj = parser.parse(new FileReader(locatorpage));
            JSONArray ja = null;

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            if (obj instanceof JSONArray) {
                ja = (JSONArray) obj;
            }
            for (int i = 0; i < ja.size(); i++) {
                JSONObject jsonObject = (JSONObject) ja.get(i);
                String[] array = {(String) jsonObject.get("by"), (String) jsonObject.get("bydesc")};
                locatorList.put((String) jsonObject.get("locator"), Arrays.asList(array));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (HashMap<String, List<String>>) locatorList;
    }

    public static Map<String, Map<String, String>> getTestData(String testDataFile) {
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
        try {
            obj = (JSONObject) parser.parse(new FileReader(testDataFile));
            for (Object key : obj.keySet()){
                data.put(key.toString(), getJsonData((JSONObject) obj.get(key)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Map<String, String> getJsonData(JSONObject jsonValues) {
        JSONParser parser = new JSONParser();
        Map<String, String> data = new HashMap<String, String>();
        try {
            for (Object key : jsonValues.keySet()){
               data.put(key.toString(), jsonValues.get(key).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String[] toStringArray(JSONArray jsonArray) {
        try {
            String[] output = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++){
                Object jsonObject = jsonArray.get(i);
                output[i]=jsonObject.toString();
                System.out.println(output[i]);
            }
            return output;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String[] toStringArray(JsonArray jsonArray) {
        try {
            String[] output = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++){
                Object jsonObject = jsonArray.get(i);
                output[i]=jsonObject.toString();
                System.out.println(output[i]);
            }
            return output;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
