/** Copyright DoubleCloud Inc. */
package doublecloud.vim.rest.api;

import java.io.IOException;

/**
 *
 * @author Steve
 */
public class PowerOnVM
{
  public static void main(String[] args) throws IOException {
    RestClient client = new RestClient("http://localhost:8080/api", "admin", "doublecloud");

    // only need to add a new vCenter once with a RestClient
    String addVC = "{\"ip\": \"192.168.0.200\",\"username\": \"root\", \"password\": \"doublecloud\"}";
    client.post("ServiceInstance", addVC);

    String task = client.post("VirtualMachine/192.168.0.200:vm-2938/powerOffVM_Task", "");
    System.out.println("vm task: " + task); //  {"returnval":{"type":"Task","content":"task-471822"}}

    // parse the task id from above string
    String taskInfo = client.get("Task/192.168.0.200:task-471822");
    System.out.println("task info: " + taskInfo);
  }
}
