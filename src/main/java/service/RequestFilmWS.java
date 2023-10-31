package service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

public class RequestFilmWS implements RequestProcessingService{
    public void acceptRequest(@WebParam(name = "creator_id") Integer creator_id,
                              @WebParam(name = "requestFilm_id") Integer subscriber_id){

    }
    @WebMethod
    public void rejectRequest(@WebParam(name = "creator_id") Integer creator_id,
                              @WebParam(name = "requestFilm_id") Integer subscriber_id){

    }
    @WebMethod
    public void request(@WebParam(name = "creator_id") Integer creator_id,
                        @WebParam(name = "requestFilm_id") Integer subscriber_id){

    }
}
