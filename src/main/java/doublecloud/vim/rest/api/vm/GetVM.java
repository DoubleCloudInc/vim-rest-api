/** Copyright DoubleCloud Inc. */
package doublecloud.vim.rest.api.vm;

import doublecloud.vim.rest.api.RestClient;
import java.io.IOException;

/**
 *
 * @author Steve
 */
public class GetVM
{
  public static void main(String[] args) throws IOException {
    RestClient client = new RestClient("http://localhost:8080/api", "admin", "doublecloud");

    String ip = "192.168.0.200";
    String username = "root";
    String password = "doublecloud";
    client.addServer(ip, username, password);

    // the vm id which is part of URL can be retrieved using list operation
    String moid = "vm-126";
    String vmAllProps = client.get("VirtualMachine/192.168.0.200:" + moid);
    System.out.println("vm all props: " + vmAllProps);

    String vmSingleProp = client.get("VirtualMachine/192.168.0.200:" + moid + "/name");
    System.out.println("vm single prop: " + vmSingleProp);

    String vmMultiProps = client.get("VirtualMachine/192.168.0.200:" + moid + "/name,config.hardware.device");
    System.out.println("vm multi props: " + vmMultiProps);

    String vmAllPropsCached = client.get("VirtualMachine/192.168.0.200:" + moid + "?cache=false&user=" + username);
    System.out.println("vm all props: " + vmAllPropsCached);
  }
}
