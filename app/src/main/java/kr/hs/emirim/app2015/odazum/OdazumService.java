package kr.hs.emirim.app2015.odazum;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by student on 2015-11-17.
 */
public interface OdazumService {

    public static final String API_URL = "http://app-odazum.cloudsc.kr";

    @GET("/items/recently/10")
    void posts(
            Callback<List<Post>> callback
    );


    @Multipart
    @POST("/wishitems")
    void wishposts(
            @Part("wish_id_list") String wish_id_list,
            Callback<List<Post>> callback
    );


}
