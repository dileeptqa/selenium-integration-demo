package utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class qTestUploader {
    public static void uploadResultToQTest(String testCaseName, boolean status) throws Exception {
        String qtestURL = "https://your-qtest-url/api/v3/projects/108781/auto-test-logs";
        String auth = "bearer 9bbf04fd-aed2-4e05-8428-7fc5ecff7bb8";  // Replace with your token

        String payload = "{\"name\": \"" + testCaseName + "\", \"status\": \"" + (status ? "PASS" : "FAIL") + "\"}";

        HttpPost post = new HttpPost(qtestURL);
        post.setHeader("Authorization", auth);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(payload));

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(post)) {
            System.out.println("qTest response: " + response.getStatusLine());
        }
    }
}
