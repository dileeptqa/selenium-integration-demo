package utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.Base64;

public class JiraUploader {
    public static void createBug(String summary, String description) throws Exception {
        String url = "https://dileepreddy.atlassian.net/rest/api/2/issue";
        String email = "tenalidileepreddy04@gmail.com";
        String token = "ATATT3xFfGF0X7aFMHL167C0HgzzhKbJCAJx2tY93TiRSlIlt6zeeY0VAjuLVpO8c11AOy1f-Q0zwL1WpGxc9lQXA8_kD6RL1hb8VBXCrMzkgnRa2Xc-hUIDqrXMWFdDvOLG8zdH4xXNASS81dUCB9QGrwnc_8rZ_tsaOUmHuwFiIF8I74_FAso=B91C7B10";
        String auth = Base64.getEncoder().encodeToString((email + ":" + token).getBytes());

        // ✅ Build JSON payload safely
        JSONObject fields = new JSONObject();
        fields.put("summary", summary);
        fields.put("description", description);

        JSONObject issueType = new JSONObject();
        issueType.put("name", "Bug");

        JSONObject project = new JSONObject();
        project.put("key", "SCRUM");

        JSONObject payload = new JSONObject();
        payload.put("fields", new JSONObject()
                .put("project", project)
                .put("summary", summary)
                .put("description", description)
                .put("issuetype", issueType));

        HttpPost post = new HttpPost(url);
        post.setHeader("Authorization", "Basic " + auth);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(payload.toString()));

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(post)) {

            System.out.println("Jira response status: " + response.getStatusLine());
            String jsonResponse = EntityUtils.toString(response.getEntity());
            System.out.println("Jira response body: " + jsonResponse);
        }
    }
}
