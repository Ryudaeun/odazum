package kr.hs.emirim.app2015.odazum;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
import retrofit.http.Path;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductFragment extends Fragment {

    private static final String TAG = "오다주움:ProductListF";
    RestAdapter restAdapter;
    int mPosition;
    Product mProduct;
    List<Product> mProducts;
    ProductAdapter adapter;
    ViewPager pager;

    public ProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products, container, false);

        pager= (ViewPager)view.findViewById(R.id.pager);

        //------------------------------------------------------------
        SharedPreferences prefs = getActivity().getSharedPreferences("odazum", Context.MODE_PRIVATE);
        //m_user_id = prefs.getInt("user_id", 0);
        //m_isGrand = prefs.getBoolean("isGrand", false);

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
        //------------------------------------------------------------


        return view;
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
        Log.i(TAG, "위시리스트 가져오기");


        restAdapter.create(OdazumService.class).products(1, new Callback<List<Product>>() {
            @Override
            public void success(List<Product> products, Response response) {
                mProducts = products;


                //ViewPager에 설정할 Adapter 객체 생성
                //ListView에서 사용하는 Adapter와 같은 역할.
                //다만. ViewPager로 스크롤 될 수 있도록 되어 있다는 것이 다름
                //PagerAdapter를 상속받은 CustomAdapter 객체 생성
                //CustomAdapter에게 LayoutInflater 객체 전달
                adapter= new ProductAdapter(getActivity(), mProducts);

                //ViewPager에 Adapter 설정
                pager.setAdapter(adapter);

                for (int i = 0; i < products.size(); i++) {
                    Log.d(TAG, "데이터는 " + products.get(i).getName());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "product 가져오기 에러 ");
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
