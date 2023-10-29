package service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class SubscriptionWS{
    @WebMethod
    public String greeting(String name){
        return "Hello "+name;
    }
}