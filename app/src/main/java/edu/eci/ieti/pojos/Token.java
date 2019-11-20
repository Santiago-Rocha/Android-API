package edu.eci.ieti.pojos;

public class Token {
    String accessToken;


    public Token( String accessToken )
    {
        this.accessToken = accessToken;
    }


    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken( String access_token )
    {
        this.accessToken = access_token;
    }
}
