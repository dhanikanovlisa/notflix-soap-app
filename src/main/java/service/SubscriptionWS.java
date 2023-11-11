package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService
public class SubscriptionWS implements RequestProcessingService{
    @WebMethod
    public void acceptRequest(@WebParam(name = "creator_id") Integer creator_id,
                              @WebParam(name = "subscriber_id") Integer subscriber_id){

    }
    @WebMethod
    public void rejectRequest(@WebParam(name = "creator_id") Integer creator_id,
                              @WebParam(name = "subscriber_id") Integer subscriber_id){

    }
    @WebMethod
    public void request(@WebParam(name = "creator_id") Integer creator_id,
                        @WebParam(name = "subscriber_id") Integer subscriber_id){


    }
    @WebMethod
    public String greeting(String name){
        return "Hello "+name;
    }
}