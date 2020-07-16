package com.example.vinhntph08047_lab3;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API {
    public static final String BASE_URL = "http://dotplays.com/";

    @GET("wp-json/wp/v2/search?search=android&_embed")
    Observable<List<RootModel>> getRootModel();
}
