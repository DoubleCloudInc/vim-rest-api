/** Copyright DoubleCloud Inc. */
package doublecloud.vim.rest.api.task;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import doublecloud.vim.rest.api.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 *
 * @author Steve
 */
public class ListTasks
{
  public static void main(String[] args) throws IOException {
    RestClient client = new RestClient("http://localhost:8080/api", "admin", "doublecloud");

    String ip = "192.168.0.200";
    String username = "root";
    String password = "doublecloud";
    client.addServer(ip, username, password);

    // for events per type, entity, time range - no need to have all but some
    String filterSpec = "{'filter':{'state':['error']}}";
    String res = client.post("TaskManager/" + ip + "/createCollectorForTasks", filterSpec);
    System.out.println("res:" + res);

    Gson g = new Gson();
    JsonObject jo = g.fromJson(res, JsonObject.class);
    String id = jo.getAsJsonObject("returnval").get("val").getAsString();
    System.out.println(id);

    String relPath = "TaskHistoryCollector/" + ip + ":" + URLEncoder.encode(id, "UTF-8");
    System.out.println(relPath);
    String collector = client.get(relPath);
    System.out.println(collector);

    String delPath = "TaskHistoryCollector/" + ip + ":" + URLEncoder.encode(id, "UTF-8") + "/destroyCollector";
    System.out.println(delPath);
    String delRes = client.post(delPath, "");
    System.out.println(delRes);
  }
}
