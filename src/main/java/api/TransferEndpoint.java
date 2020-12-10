package api;

import core.HttpBuilder;
import core.utils.JsonToObject;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import java.util.HashMap;

public class TransferEndpoint extends EndPoint{
    private final String transferURL = "https://stage-wallet-api.rock-west.net/api/v1/wallet/8efe48b1-5988-498c-8e57-1d5c8874eb70/payment-remit-info";
    private final String infoURL = "https://wallet-api.rock-west.org/api/v1/wallet/f8ede8f4-39fb-46c0-9438-c70eca7a5f02/payment-remit-info";
    public ResponseObject getRemittanceInfoForCountry(HashMap requestBody){
        HttpResponse<JsonNode> response = httpBuilder.sendGetRequest(transferURL,requestBody);
        return JsonToObject.getObjectFromJson(response, ResponseObject.class);
    }

    public PaymentRemitInfoModel getPaymentRemitInfo(HashMap requestBody){
        HttpResponse<JsonNode> response = httpBuilder.sendGetRequest(infoURL,requestBody);
        return JsonToObject.getObjectFromJson(response, PaymentRemitInfoModel.class);
    }

}
