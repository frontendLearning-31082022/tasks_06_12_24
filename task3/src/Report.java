import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Report {

    public static void main(String[] args) throws IOException {
        JSONObject resultTestsInput = new JSONObject(Files.readString(Path.of(args[0])));
        JSONObject reportTemplate = new JSONObject(Files.readString(Path.of(args[1])));
        Path pathReportOutput = Path.of(args[2]);

        Map<String, String> resultTests = ((JSONArray) resultTestsInput.get("values")).toList().stream().collect(
                Collectors.toMap(x -> String.valueOf(((HashMap) x).get("id")),
                        x -> String.valueOf(((HashMap) x).get("value"))));

        recursiveFillStatus(reportTemplate, null, resultTests);

        Files.writeString(pathReportOutput, reportTemplate.toString(1));
    }

    static void recursiveFillStatus(JSONObject jsonObject, JSONArray jsonArray, Map<String, String> fillData) {
        Consumer<Object> recurCallFn = new Consumer<Object>() {
            @Override
            public void accept(Object node) {
                if (node instanceof JSONObject) recursiveFillStatus((JSONObject) node, null, fillData);
                if (node instanceof JSONArray) recursiveFillStatus(null, (JSONArray) node, fillData);
            }
        };

        if (jsonObject != null) {
            if (jsonObject.has("id") && jsonObject.has("value")) {
                String id = String.valueOf(jsonObject.get("id"));
                jsonObject.put("value", fillData.get(id));
            }

            for (String key : jsonObject.keySet()) {
                Object node = jsonObject.get(key);
                recurCallFn.accept(node);
            }
            new String();
        }

        if (jsonArray != null) {
            Iterator<Object> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                Object node = iterator.next();
                recurCallFn.accept(node);
                new String();
            }
        }
    }
}
