package com.thinkelect.thinkelect;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by micahherrera on 8/8/16.
 */
public interface ISunlightDistrict {
    @GET("legislators/locate?latitude={lat}&longitude={long}&apikey=" + Constants.SUNLIGHT_API_KEY)
    Call<List<Candidate>> listMyCandidates(@Path("lat") double lat, @Path("long") double longi);
}
