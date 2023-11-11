package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

interface RequestProcessingService {
    public void acceptRequest(@WebParam Integer creator_id, @WebParam Integer request_id);
    public void rejectRequest(@WebParam Integer creator_id, @WebParam Integer request_id);
    public void request(@WebParam Integer creator_id, @WebParam Integer request_id);
}
