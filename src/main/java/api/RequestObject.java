package api;

import java.util.HashMap;

public class RequestObject {
    private String toCountry;
    private String transType;
    private String amount;
    private String serviceLevel;
    private  String method = "c5ad5236-4019-4ca3-a4fc-b20432ca8f5d";
    private  String assetCode = "PURPLE";

    public RequestObject (String toCountry, String transType,String amount, String serviceLevel){
        this.toCountry = toCountry;
        this.transType = transType;
        this.amount = amount;
        this.serviceLevel = serviceLevel;
    }

    public HashMap getRequestModel(){
        HashMap hashMap = new HashMap();
        hashMap.put("to_country",toCountry);
        hashMap.put("trans_type",transType);
        hashMap.put("amount",amount);
        hashMap.put("service_level",serviceLevel);
        hashMap.put("method",method);
        hashMap.put("asset_code",assetCode);
        return hashMap;
    }

    public void requestPrettyPrint(){
        System.out.println("to_country: "+toCountry);
        System.out.println("trans_type: "+transType);
        System.out.println("amount: "+amount);
        System.out.println("service_level: "+serviceLevel);
        System.out.println("method: "+method);
        System.out.println("asset_code: "+assetCode+"\n"+"-------------------------------");
    }
}
