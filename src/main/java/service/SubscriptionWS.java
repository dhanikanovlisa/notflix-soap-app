package service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class SubscriptionWS implements RequestProcessingService{
    @WebMethod
    public void acceptRequest(){

    }
    @WebMethod
    public void rejectRequest(){

    }
    @WebMethod
    public void request(){

    }
    @WebMethod
    public String greeting(String name){
        return "Hello "+name;
    }
}