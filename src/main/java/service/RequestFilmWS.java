package service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import model.RequestFilmModel;
import object.RequestFilm;

import java.util.List;

@WebService
public class RequestFilmWS {


    @WebMethod
    public List<RequestFilm> getAllRequestFilms(){
        try {
            return RequestFilmModel.getInstance().getAllRequestFilm();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
