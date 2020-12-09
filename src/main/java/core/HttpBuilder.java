package core;

import kong.unirest.*;
import java.util.HashMap;

public class HttpBuilder {
    public HttpResponse <JsonNode> sendGetRequest(String url, HashMap params){
        HttpResponse <JsonNode> response = null;
        try {
            HttpRequest request = Unirest.get(url)
                    .queryString(params);
            response = request.asJson();
        }
        catch (UnirestException e){
            e.printStackTrace();
        }
        return response;
    }

}
