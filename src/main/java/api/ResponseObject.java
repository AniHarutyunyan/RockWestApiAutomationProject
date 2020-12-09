package api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import core.utils.JsonToObject;

public class ResponseObject extends JsonToObject {
    protected JsonObject beneficiary_fields;
    protected JsonObject charges;
    protected int code = 0;
    protected JsonArray points;
    protected JsonObject remitter_fields;


    public String  getRemitterFields(){
        return remitter_fields.getAsString();
    }

    public boolean isBeneficiaryFieldsEmpty(){
        if(beneficiary_fields.size()==0)
            return true;
        else return false;
    }

    public double getTotalCharge(){
        if(!charges.has("TotalCharges")){
            return -1;
        }
        System.out.println("TotalCharges: "+charges.get("TotalCharges").getAsDouble());
        return charges.get("TotalCharges").getAsDouble();
    }

    public double getRate(){
        if(!charges.has("Rate")){
            return -1;
        }
        System.out.println("Rate: "+charges.get("Rate").getAsDouble());
        return charges.get("Rate").getAsDouble();
    }

    public double getDestinationAmount(){
        if(!charges.has("DestinationAmount")){
            return -1;
        }
        System.out.println("DestinationAmount: "+charges.get("DestinationAmount").getAsDouble());
        return charges.get("DestinationAmount").getAsDouble();
    }

    public int getCode(){
        return code;
    }

    public boolean isPointsValid(){

        if(points!=null){
            return hasArrayElementsSpecifiedKeys();
        }
        else return false;
    }

    public boolean hasArrayElementsSpecifiedKeys(){
        for (int i = 0; i < points.size(); i++) {
            JsonObject obj = points.get(i).getAsJsonObject();
            if(obj.has("address")&&obj.has("bank")&&obj.has("city")&&obj.has("name"))
                continue;
            else {
                System.out.println(i+"th element has no specified key");
                return false;
            }
        }
        return true;
    }

}
