/** Copyright DoubleCloud Inc. */
package doublecloud.vim.rest.api.vm;

import doublecloud.vim.rest.api.JsonUtil;
import doublecloud.vim.rest.api.RestClient;
import java.io.IOException;

/**
 *
 * @author Steve
 */
public class PowerOnVM
{
  public static void main(String[] args) throws IOException, InterruptedException {
    RestClient client = new RestClient("http://localhost:8080/api", "admin", "doublecloud");

    String ip = "192.168.0.200";
    String username = "root";
    String password = "doublecloud";
    client.addServer(ip, username, password);

    String moid = "vm-3094";
    String name = client.get("VirtualMachine/192.168.0.200:" + moid + "/name,runtime.powerState");
    System.out.println(name);
    String powerOffTaskStr = client.post("VirtualMachine/192.168.0.200:" + moid + "/powerOffVM_Task", "");
    System.out.println("vm task: " + powerOffTaskStr); // {"returnval":{"type":"Task","val":"task-471822"}}

    String taskid = JsonUtil.getAsStringByPath(powerOffTaskStr, "returnval.val");
    printTaskInfo(client, taskid);

    Thread.sleep(3000);

    String powerOnBody = "{\"host\":{\"type\":\"HostSystem\",\"val\":\"host-123\"}}";
    String powerOnTaskStr = client.post("VirtualMachine/192.168.0.200:" + moid + "/powerOnVM_Task", powerOnBody);
    System.out.println("vm task: " + powerOnTaskStr); //  {"returnval":{"type":"Task","val":"task-471822"}}

    String taskid2 = JsonUtil.getAsStringByPath(powerOnTaskStr, "returnval.val");
    printTaskInfo(client, taskid2);
  }

  static void printTaskInfo(RestClient client, String taskid) throws IOException {
    String taskInfo = client.get("Task/192.168.0.200:" + taskid);
    System.out.println("task info: " + taskInfo);
  }
}

