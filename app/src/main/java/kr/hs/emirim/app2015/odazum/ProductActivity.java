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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    static boolean bool = false;

    public ProductActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_item);

        item_pager = (ViewPager)findViewById(R.id.detail_pager);

        Log.d(TAG, ""+mPosition);

        ImageButton imageButton = (ImageButton)findViewById(R.id.like_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("odazum", Context.MODE_PRIVATE);
                String current_wish_id_list = prefs.getString("wish_id_list", "");
                Log.d(TAG, "read current wish list : " + current_wish_id_list);

                ArrayList<String> tmpWishIdLists = new ArrayList<String>();
                String wish_array[] = current_wish_id_list.split(",");

                for(int i = 0; i < wish_array.length; i ++){
                    tmpWishIdLists.add(wish_array[i]);
                }

                Log.d(TAG, "now current post id : " +  mPostId);
                Log.d(TAG, tmpWishIdLists.toString());

                if(tmpWishIdLists.contains(""+mPostId)) {
                    for (int i = 0; i < wish_array.length; i++) {
                        if (tmpWishIdLists.get(i).equals("" + mPostId)) {
                            tmpWishIdLists.remove(i);
                            break;
                        }
                    }
                }else{
                    tmpWishIdLists.add("" + mPostId);
                }

                Log.d(TAG, "tmpWishIdLists's size " + tmpWishIdLists.size());

                current_wish_id_list = "";
                for(int i = 0; i < tmpWishIdLists.size(); i ++) {
                    if (i == 0)
                        current_wish_id_list = tmpWishIdLists.get(0);
                    else
                        current_wish_id_list += "," + tmpWishIdLists.get(i);
                }
                if(bool == false)
                    Log.d(TAG, "current_wish_id_list is " + current_wish_id_list.substring(1));
                else
                    Log.d(TAG, "current_wish_id_list is " + current_wish_id_list);
                // 1. 현재 위시를 가져와서
                // 2. 인트의 리스트 형태 로 변환
                // 3. 현재 아이디 있는지 찾아서 있으면 삭제, 없으면 추가
                // 4. 리스트를 스트링 형태로 변환  1,2,3
                SharedPreferences.Editor ed = prefs.edit();
                if(bool == false) {
                    ed.putString("wish_id_list", current_wish_id_list.substring(1));
                    bool = true;
                }
                else
                    ed.putString("wish_id_list", current_wish_id_list);
                if(tmpWishIdLists.size() == 0 && bool == true)
                    bool = false;
                if(tmpWishIdLists.contains(""+mPostId))
                    Toast.makeText(ProductActivity.this, R.string.save_wishlist, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ProductActivity.this, R.string.delete_wishlist, Toast.LENGTH_SHORT).show();
                ed.commit();
            }
        });

        ImageButton imageButton1 = (ImageButton)findViewById(R.id.link_btn);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = item_pager.getCurrentItem();
                str = mProducts.get(mPosition).getName();
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
        Intent intent = getIntent();
        mPostId = intent.getIntExtra("post_id", -1);
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
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "상품 가져오기 에러 ");
            }
        });
    }

}
