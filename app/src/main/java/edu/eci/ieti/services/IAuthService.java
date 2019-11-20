package edu.eci.ieti.services;

import edu.eci.ieti.pojos.LoginWrapper;
import edu.eci.ieti.pojos.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthService {

    @POST("/login")
    Call<Token> login(@Body LoginWrapper login);
}