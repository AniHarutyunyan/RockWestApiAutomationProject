package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import core.utils.JsonToObject;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaymentRemitInfoModel extends JsonToObject {
    protected int code;
    protected JsonObject remit;

    public List<String> getCountry() {
        return getKeyList(remit.get("country").getAsJsonObject());
    }
    public List<String> getFromCountry() {
        return getKeyList(remit.get("from_country").getAsJsonObject());
    }
    public List<String> getMethods() {
        JsonArray array = remit.get("methods").getAsJsonArray();
        List<String> list=new ArrayList<String>();
        for (int i = 0; i < array.size (); ++i) {
            list.add(array.get(i).getAsJsonObject().get("guid").getAsString());
        }
        return list;
    }
    public List<String> getServiceLevel() {
        return getValueList(remit,"service_level");
    }
    public List<String> getTransType() {
        return getValueList(remit,"trans_type");
    }

    public String getOperationCode(){
        return Integer.toString(code);
    }
    ///By giving Json Object to method, it will return the keys as Strings in list
    public List<String> getKeyList(JsonObject object){
        JSONObject objects = new JSONObject(object.toString());
        JSONArray key = objects.names ();
        List<String> list=new ArrayList<String>();
        for (int i = 0; i < key.length (); ++i) {
            String keys = key.getString (i);
            list.add(keys);
        }
        return list;
    }
    public List<String> getValueList(JsonObject object, String key){
        JsonArray array = object.get(key).getAsJsonArray();
        List<String> list=new ArrayList<String>();
        for (int i = 0; i < array.size (); ++i) {
            list.add(array.get(i).getAsString());
        }
        return list;
    }
}
