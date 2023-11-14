package service;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import model.RequestFilmModel;
import object.RequestFilm;

import java.util.Date;
import java.util.List;

@WebService(endpointInterface = "service.RequestFilmWS")
@HandlerChain(file = "log_and_auth.xml")
public class RequestFilmWS{
    @WebMethod
    public List<RequestFilm> getAllRequestFilms() {
        try {
            List<RequestFilm> result =  RequestFilmModel.getInstance().getAllRequestFilm();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<RequestFilm> getAllRequestFilmById(@WebParam(name="user_id") int user_id){
        try {
            List<RequestFilm> result =  RequestFilmModel.getInstance().getAllRequestFilm();
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public RequestFilm getRequestFilmByFilmId(@WebParam(name="requestFilm_id") int requestFilm_Id){
        try {
            RequestFilm result =  RequestFilmModel.getInstance().getRequestFilmByFilmId(requestFilm_Id);
            return result;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public String createRequestFilm(@WebParam(name="user_id") int user_id, @WebParam(name="filmName") String filmName,
                                    @WebParam(name="description") String description, @WebParam(name="film_path") String film_path,
                                    @WebParam(name="film_poster") String film_poster, @WebParam(name="film_header") String film_header,
                                    @WebParam(name="date_release") Date date_release,@WebParam(name="duration") int duration){
        try {
            return RequestFilmModel.getInstance().createRequestFilm(user_id, filmName, description, film_path, film_poster, film_header, date_release, duration);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public String editRequestFilm(@WebParam(name="requestFilm_Id") int requestFilm_id, @WebParam(name="user_id") int user_id, @WebParam(name="filmName") String filmName,
                                    @WebParam(name="description") String description, @WebParam(name="film_path") String film_path,
                                    @WebParam(name="film_poster") String film_poster, @WebParam(name="film_header") String film_header,
                                    @WebParam(name="date_release") Date date_release,@WebParam(name="duration") int duration){
        try {
            return RequestFilmModel.getInstance().editRequestFilm(requestFilm_id, user_id, filmName, description, film_path,
                    film_poster, film_header, date_release, duration);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public String deleteRequestFilm(@WebParam(name="requestFilm_id") int requestFilm_id){
        try{
            return RequestFilmModel.getInstance().deleteRequestFilm(requestFilm_id);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
