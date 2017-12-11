/** Copyright DoubleCloud Inc. */
package doublecloud.vim.rest.api;

import java.io.IOException;

/**
 *
 * @author Steve
 */
public class GetVM
{
  public static void main(String[] args) throws IOException {
    RestClient client = new RestClient("http://localhost:8080/api", "admin", "doublecloud");

    // only need to add a new vCenter once
    String addVC = "{\"ip\": \"192.168.0.200\",\"username\": \"root\", \"password\": \"doublecloud\"}";
    client.post("ServiceInstance", addVC);

    // the vm id which is part of URL can be retrieved using list operation
    String vmAllProps = client.get("VirtualMachine/192.168.0.200:vm-126");
    System.out.println("vm all props: " + vmAllProps);

    String vmSingleProp = client.get("VirtualMachine/192.168.0.200:vm-126/name");
    System.out.println("vm single prop: " + vmSingleProp);

    String vmMultiProps = client.get("VirtualMachine/192.168.0.200:vm-126/name,config.hardware.device");
    System.out.println("vm multi props: " + vmMultiProps);
  }
}
