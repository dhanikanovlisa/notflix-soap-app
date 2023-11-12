package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import model.RequestFilmModel;
import object.RequestFilm;

import java.util.Date;
import java.util.List;

@WebService(endpointInterface = "service.RequestFilmWS")
public class RequestFilmWS extends BaseWS{
    @WebMethod
    public List<RequestFilm> getAllRequestFilms() {
        boolean test = true;
        if(test){
            try {
                List<RequestFilm> result =  RequestFilmModel.getInstance().getAllRequestFilm();
                if(result.isEmpty()){
                    return null;
                } else {
                    insertLog("Get All Request Film", "service.RequestFilmWS");
                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("Not valid API-Key");
            return null;
        }
    }

    @WebMethod
    public List<RequestFilm> getAllRequestFilmById(@WebParam(name="user_id") int user_id){
        if(verifyApiKey()){
            try {
                List<RequestFilm> result =  RequestFilmModel.getInstance().getAllRequestFilm();
                if(result.isEmpty()){
                    return null;
                } else {
                    insertLog("Get All Request Film", "service.RequestFilmWS");
                    return result;
                }
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("Not valid API-Key");
            return null;
        }
    }

    @WebMethod
    public String createRequestFilm(@WebParam(name="user_id") int user_id, @WebParam(name="filmName") String filmName,
                                    @WebParam(name="description") String description, @WebParam(name="film_path") String film_path,
                                    @WebParam(name="film_poster") String film_poster, @WebParam(name="film_header") String film_header,
                                    @WebParam(name="date_release") Date date_release,@WebParam(name="duration") int duration){
        if(verifyApiKey()){
            try {
                return RequestFilmModel.getInstance().createRequestFilm( user_id, filmName, description, film_path,
                        film_poster, film_header, date_release, duration);
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("Not valid API-Key");
            return null;
        }
    }



    @WebMethod
    public String deleteRequestFilm(@WebParam(name="requestFilm_id") int requestFilm_id){
        if(verifyApiKey()){
            try{
                return RequestFilmModel.getInstance().deleteRequestFilm(requestFilm_id);
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("Not valid API-Key");
            return null;
        }
    }
}
