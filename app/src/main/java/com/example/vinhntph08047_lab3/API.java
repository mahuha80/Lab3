package com.example.vinhntph08047_lab3;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    String BASE_URL = "http://dotplays.com/";

    @GET("wp-json/wp/v2/search?_embed")
    Observable<List<RootModel>> getRootModel(@Query("search") String search);
}
