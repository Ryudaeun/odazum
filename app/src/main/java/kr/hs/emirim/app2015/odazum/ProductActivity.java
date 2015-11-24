package kr.hs.emirim.app2015.odazum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by Student on 2015-11-20.
 */
public class ProductActivity extends AppCompatActivity{

    private static final String TAG = "오다주움:PostListF";
    ViewPager item_pager;
    RestAdapter restAdapter;
    ProductAdapter adapter;
    int mPosition;
    int mPostId;
    Post mPost;
    List<Post> mPosts;
    Product mProduct;
    List<Product> mProducts;
    String str = null;

    public ProductActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_item);

        item_pager = (ViewPager)findViewById(R.id.detail_pager);
        mPosition = item_pager.getCurrentItem();

        ImageButton imageButton = (ImageButton)findViewById(R.id.like_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("odazum", Context.MODE_PRIVATE);
                mPostId = prefs.getInt("post_id", mPost.getId());
                SharedPreferences.Editor ed = prefs.edit();
                ed.putString("wish_item_list", String.valueOf(mPostId));
                Toast.makeText(ProductActivity.this, R.string.save_wishlist, Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton imageButton1 = (ImageButton)findViewById(R.id.link_btn);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.shopping.naver.com/search/all_search.nhn?query="+str+"&cat_id=&frm=NVSHATC&nlu=true")));
            }
        });

        //------------------------------------------------------------


        /**
         * Gson 컨버터 이용
         */
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(java.sql.Date.class, new DateTypeAdapter())
                .create();

        /**
         * 레트로핏 설정
         */
        restAdapter = new RestAdapter.Builder()
                //로그 레벨 설정
                .setLogLevel(RestAdapter.LogLevel.FULL)
                        //BASE_URL 설정
                .setEndpoint(OdazumService.API_URL)
                        //OkHttpClient 이용
                .setClient(new OkClient(new OkHttpClient()))
                        //Gson Converter 설정
                .setConverter(new GsonConverter(gson))
                .build();

    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        /**
         * 통신 콜백 메서드 Callback<List<Address>> callback
         */
        Log.i(TAG, "상품 가져오기");
        restAdapter.create(OdazumService.class).products(mPostId, new Callback<List<Product>>() {
            @Override
            public void success(List<Product> products, Response response) {
                mProducts = products;
                adapter = new ProductAdapter(ProductActivity.this, products);
                item_pager.setAdapter(adapter);
                for (int i = 0; i < products.size(); i++) {
                    Log.d(TAG, "데이터는 " + products.get(i).getId());
                }
                str = products.get(mPosition).getName();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "상품 가져오기 에러 ");
            }
        });
    }

    /*
    private void addDate(Post post) {
        Log.d(TAG, "추가되는 게시글" + post.getTitle());
        restAdapter.create(OdazumService.class).
            createPost(id, post.getTitle(), post.getDate(), post.getImage(), post.getClick(), post.getWish(), new Callback<Post>() {
                public void success(Post post, Response response) {
                    Log.d(TAG, "Post 추가하기");
                    Log.d(TAG, post.toString());
                    getData();
                }

                public void failure(RetrofitError error) {
                    Log.d(TAG, "Post 추가하기 에러!" + error.getMessage());
                }
            });
    }*/
}
