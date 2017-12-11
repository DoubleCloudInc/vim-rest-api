/** Copyright DoubleCloud Inc. */
package doublecloud.vim.rest.api.vm;

import doublecloud.vim.rest.api.JsonUtil;
import doublecloud.vim.rest.api.RestClient;
import java.io.IOException;

/**
 *
 * @author Steve
 */
public class AddDisk
{
  public static void main(String[] args) throws IOException, InterruptedException, Exception {
    RestClient client = new RestClient("http://localhost:8080/api", "admin", "doublecloud");

    String ip = "192.168.0.200";
    String username = "root";
    String password = "doublecloud";
    client.addServer(ip, username, password);

    String moid = "vm-3094";

    // if copy the following string, make sure to replace single quotes with double ones
    String spec = "{'spec':{'deviceChange':[{'operation':'add','fileOperation':'create','device':{'@type':'VirtualDisk','capacityInKB':1024,'key':-1,'backing':{'@type':'VirtualDiskFlatVer2BackingInfo', 'diskMode':'persistent','fileName':'[datastore1] dslvm/doubleclouddisk3.vmdk'},'controllerKey':200,'unitNumber':1}}]}}";
    spec = spec.replaceAll("'", "\"");

    String reconfigTaskStr = client.post("VirtualMachine/192.168.0.200:" + moid + "/reconfigVM_Task", spec);
    System.out.println("vm task: " + reconfigTaskStr); // {"returnval":{"type":"Task","val":"task-471822"}}

    String taskid = JsonUtil.getAsStringByPath(reconfigTaskStr, "returnval.val");
    String taskInfo = client.get("Task/192.168.0.200:" + taskid);
    System.out.println("task info: " + taskInfo);
  }
}
