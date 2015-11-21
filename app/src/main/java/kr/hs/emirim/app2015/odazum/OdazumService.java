package kr.hs.emirim.app2015.odazum;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by student on 2015-11-17.
 */
public interface OdazumService {

    public static final String API_URL = "http://app-odazum.cloudsc.kr";

    @GET("/items/recently/10")
    void posts(
            Callback<List<Post>> callback
    );
}
