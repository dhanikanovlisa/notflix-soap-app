package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import enums.Status;
import model.SubscriptionModel;
import object.Subscription;

import java.util.List;

@WebService
public class SubscriptionWS extends BaseWS{
    @WebMethod
    public List<Subscription> getAllSubscription(){
        if(verifyApiKey()){
            try{
                return SubscriptionModel.getInstance().getAllSubscription();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Not valid API-Key");
        }
        return null;
    }

    @WebMethod
    public String acceptRequest(@WebParam(name = "user_id") Integer user_id){
        if(verifyApiKey()){
            try{
                return SubscriptionModel.getInstance().updateSubscriptionState(user_id, Status.ACCEPTED);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Not valid API-Key");
        }
        return null;
    }
    @WebMethod
    public String rejectRequest(@WebParam(name = "user_id") Integer user_id){
        if(verifyApiKey()){
            try{
                return SubscriptionModel.getInstance().updateSubscriptionState(user_id, Status.REJECTED);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Not valid API-Key");
        }
        return null;
    }
    @WebMethod
    public String request(@WebParam(name = "user_id") Integer user_id){
        if(verifyApiKey()){
            try{
                return SubscriptionModel.getInstance().requestSubscription(user_id);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Not valid API-Key");
        }
        return null;
    }

    @WebMethod
    public String greeting(String name){
        return "Hello "+name;
    }
}