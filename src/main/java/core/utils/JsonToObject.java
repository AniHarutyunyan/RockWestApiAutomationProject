package core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import java.lang.reflect.Type;

public class JsonToObject {
    public final <T>T getObjectFromJson(HttpResponse<JsonNode> response){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(response.getBody().toString());
        return (T) gson.fromJson(jsonObject,this.getClass());
    }
    public final static  <T>T  getObjectFromJson(HttpResponse<JsonNode> response, Type type){
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(response.getBody().toString());
        if(!jsonObject.has("remitter_fields")){
            jsonObject.add("remitter_fields", new JsonObject());
        }
        if(jsonObject.has("points")&&jsonObject.get("points").isJsonNull()) {
            jsonObject.remove("points");
            jsonObject.add("points", new JsonArray());
        }
        else{
            if(!jsonObject.has("beneficiary_fields")&&!jsonObject.has("charges")&&!jsonObject.has("points")){
                jsonObject.add("beneficiary_fields", new JsonObject());
                jsonObject.add("charges", new JsonObject());
                jsonObject.add("points", new JsonArray());
            }
        }
        return gson.fromJson(jsonObject,type);
    }
}
