package service;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import model.LikesModel;


@WebService(endpointInterface = "service.LikesWS")
@HandlerChain(file = "log_and_auth.xml")
public class LikesWS{
    @WebMethod
    public Boolean isUserLikeFilm(@WebParam(name="film_id") int film_id, @WebParam(name="user_id") int user_id) {
        try {
            Boolean result =  LikesModel.getInstance().isUserLikeFilm(film_id, user_id);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public Boolean addLikes(@WebParam(name="film_id") int film_id, @WebParam(name="user_id") int user_id){
        try {
            Boolean result =  LikesModel.getInstance().addLikes(film_id, user_id);
            return result;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public Boolean deleteLikes(@WebParam(name="film_id") int film_id, @WebParam(name="user_id") int user_id){
        try {
            Boolean result =  LikesModel.getInstance().deleteLikes(film_id, user_id);
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

