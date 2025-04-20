package utils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class QTestUploader {
    public static void createDefect(String title, String description) {
        try {
            String token = System.getenv("QTEST_TOKEN");
            String projectId = System.getenv("QTEST_PROJECT_ID");
            String endpoint = "https://qtest.qasymphony.com/api/v3/projects/" + projectId + "/defects";

            if (token == null || projectId == null) {
                System.out.println("❌ Environment variables QTEST_TOKEN or QTEST_PROJECT_ID are not set.");
                return;
            }

            String payload = "{ \"title\": \"" + title + "\", \"description\": \"" + description + "\" }";

            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes());
            }

            System.out.println("✅ qTest response code: " + conn.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
