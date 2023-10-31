package service;

import jakarta.jws.WebParam;

interface RequestProcessingService {
    public void acceptRequest(@WebParam Integer creator_id, @WebParam Integer request_id);
    public void rejectRequest(@WebParam Integer creator_id, @WebParam Integer request_id);
    public void request(@WebParam Integer creator_id, @WebParam Integer request_id);
}
