import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
    public static String parseContentFromJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String content = jsonObject.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
            return content;
        } catch (JSONException e) {
            System.err.println("Problem z przetwarzaniem JSON-a: " + e.getMessage());
            return "Problem z przetwarzaniem JSON-a: " + e.getMessage();
        }
    }
}
