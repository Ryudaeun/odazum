package kr.hs.emirim.app2015.odazum;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;

/**
 * Created by student on 2015-11-17.
 */
public interface OdazumService {

    public static final String API_URL = "http://app-odazum.cloudsc.kr";

    @GET("/items/recently/10")
    void posts(
            Callback<List<Post>> callback
    );

    @GET("/post/{post_id}/products")
    void products(
            @Path("post_id") int post_id,
            Callback<List<Product>> callback
    );

    @Multipart
    @POST("/wishitems")
    void wishposts(
            @Part("wish_id_list") String wish_id_list,
            Callback<List<Post>> callback
    );

    @GET("/items/mostwish/20")
    void mostwish(
            Callback<List<Post>> callback
    );

    @GET("/items/mostview/20")
    void mostview(
            Callback<List<Post>> callback
    );

    @GET("/fashionclothes/recently")
    void fashionclothes(
            Callback<List<Post>> callback
    );

    @GET("/fashiongoods/recently")
    void fashiongoods(
            Callback<List<Post>> callback
    );

    @GET("/beautygoods/recently")
    void beautygoods(
            Callback<List<Post>> callback
    );

    @GET("/foods/recently")
    void foods(
            Callback<List<Post>> callback
    );

    @GET("/fancygoods/recently")
    void fancygoods (
            Callback<List<Post>> callback
    );

    @GET("/randomtags/")
    void randomtags(
            Callback<List<Tag>> callback
    );

    @Multipart
    @POST("/items")
    void searchposts(
            @Part("gender") int gender,
            @Part("age") int age,
            @Part("tags") String tags,
            @Part("max_price") int max_price,
            @Part("orderby") int orderby,
            Callback<List<Post>> callback
    );

    @POST("/post/products")
    void postclicks(
            @Part("post_id") int post_id,
            Callback<List<Product>> callback
    );
}
