/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doublecloud.vim.rest.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 *
 * @author Steve
 */
public class JsonUtil
{
  final private static Gson GSON = new Gson();

  public static String getAsStringByPath(String jsonStr, String path) {
    JsonObject jo = GSON.fromJson(jsonStr, JsonObject.class);
    String[] parts = path.split("\\.");
    JsonObject node = jo;
    for(int i=0; i < parts.length -1; i++) {
      node = node.getAsJsonObject(parts[i]);
      if(node == null) return null;
    }
    JsonElement je = node.get(parts[parts.length-1]);
    return je == null? null : je.getAsString();
  }
}
