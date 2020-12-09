package api;

import core.HttpBuilder;

public class EndPoint {
    protected HttpBuilder httpBuilder;
    public EndPoint (){
        httpBuilder = new HttpBuilder();
    }
}
