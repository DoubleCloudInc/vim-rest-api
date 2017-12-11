/** Copyright DoubleCloud Inc. */
package doublecloud.vim.rest.api.vm;

import doublecloud.vim.rest.api.RestClient;
import java.io.IOException;

/**
 *
 * @author Steve
 */
public class ListVMs
{
  public static void main(String[] args) throws IOException {
    RestClient client = new RestClient("http://localhost:8080/api", "admin", "doublecloud");

    String ip = "192.168.0.200";
    String username = "root";
    String password = "doublecloud";
    client.addServer(ip, username, password);

    // Get all the VMs from all the vCenter/ESXi servers
    String vms = client.get("VirtualMachine");
    System.out.println("vms: " + vms);

    // Get all the VMs fron single vCenter/ESXi server
    vms = client.get("VirtualMachine?root=" + ip);
    System.out.println("vms: " + vms);

    // Get VMs under a particular entity like Folder in a single vCenter/ESXi server
    String folderId = "group-v3189";
    vms = client.get("VirtualMachine?root=" + ip + ":" + folderId);
    System.out.println("vms: " + vms);

    // Get VMs under multiple entities within one or multiple vCenter/ESXi server
    vms = client.get("VirtualMachine?root=" + ip + ":" + folderId + "&root=" + ip + ":" + folderId);
    System.out.println("vms: " + vms);
  }
}
