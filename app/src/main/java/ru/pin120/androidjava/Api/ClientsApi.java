package ru.pin120.androidjava.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ru.pin120.androidjava.Entities.Clients;

public interface ClientsApi {
    @GET("/clients/get-all")
    Call<List<Clients>> getAllClients();

    @POST("/clients/save")
    Call<Clients> save(@Body Clients client);
}
