package service;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import enums.Status;
import model.SubscriptionModel;
import object.Subscription;
import utils.EmailUtil;

import java.util.List;

@WebService(endpointInterface = "service.SubscriptionWS")
@HandlerChain(file = "log_and_auth.xml")
public class SubscriptionWS{
    @WebMethod
    public List<Subscription> getAllSubscription(){
        try{
            return SubscriptionModel.getInstance().getAllSubscription();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public Subscription checkSubscriptionStatus(@WebParam(name="user_id") Integer user_id){
        try{
            return SubscriptionModel.getInstance().getSubscriptionStatus(user_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    @WebMethod
    public List<Subscription> getSubscriptionsByStatus(@WebParam(name="status") String status){
        try{
            return SubscriptionModel.getInstance().getSubscriptionsByStatus(Status.fromStatusCode(status));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public String acceptRequest(@WebParam(name = "user_id") Integer user_id){
        try{
            return SubscriptionModel.getInstance().updateSubscriptionState(user_id, Status.ACCEPTED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @WebMethod
    public String rejectRequest(@WebParam(name = "user_id") Integer user_id){
        try{
            return SubscriptionModel.getInstance().updateSubscriptionState(user_id, Status.REJECTED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @WebMethod
    public String request(@WebParam(name = "user_id") Integer user_id){
        try{
            EmailUtil.getInstance().sendEmail();
            return SubscriptionModel.getInstance().requestSubscription(user_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}