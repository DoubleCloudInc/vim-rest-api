/** Copyright DoubleCloud Inc. */
package doublecloud.vim.rest.api;

import java.io.IOException;

/**
 *
 * @author Steve
 */
public class ListVMs
{
  public static void main(String[] args) throws IOException {
    RestClient client = new RestClient("http://localhost:8080/api", "admin", "doublecloud");

    String addVC = "{\"ip\": \"192.168.0.200\",\"username\": \"root\", \"password\": \"doublecloud\"}";
    client.post("ServiceInstance", addVC);

    String vms = client.get("VirtualMachine");
    System.out.println("vms: " + vms);
  }
}
