/** Copyright DoubleCloud Inc. */
package doublecloud.vim.rest.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 *
 * @author Steve
 */
public class RestClient
{
  String baseUrl;
  String authHeader;

  public RestClient(String baseUrl, String username, String password) {
    this.baseUrl = baseUrl;
    this.authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
  }

  public String get(String relPath) throws MalformedURLException, IOException {
    URL url = new URL(baseUrl + "/" + relPath);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Authorization", authHeader);
    return readStream(conn.getInputStream());
  }

  public String post(String relPath, String reqBody) throws MalformedURLException, IOException {
    URL url = new URL(baseUrl + "/" + relPath);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setRequestProperty("Authorization", authHeader);
    conn.setRequestProperty("Connect-Type", "application/json");
    conn.setRequestProperty("Connent-Length", String.valueOf(reqBody.getBytes().length));
    OutputStream out = conn.getOutputStream();
    out.write(reqBody.getBytes());
    out.close();
    int resCode = conn.getResponseCode();
    if(resCode >=200 && resCode < 300) {
      return readStream(conn.getInputStream());
    }
    else {
      return readStream(conn.getErrorStream());
    }
  }

  private static String readStream(InputStream in) throws IOException {
    if(in == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    try (BufferedReader bis = new BufferedReader(new InputStreamReader(in))) {
      String line;
      while( (line = bis.readLine()) != null) {
        sb.append(line);
        sb.append("\n");
      }
    }
    return sb.toString();
  }
}
