package kr.hs.emirim.app2015.odazum;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

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
 * A placeholder fragment containing a simple view.
 */
public class FancyFragment extends Fragment {

    private static final String TAG = "오다주움:PostListF";
    RestAdapter restAdapter;
    GridView gridView;
    int mPosition;
    Post mPost;
    List<Post> mPosts;
    PostListAdapter adapter;

    public FancyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_postlist, container, false);

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

        gridView = (GridView) view.findViewById(R.id.myGridView);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // change fragment
            }
        });

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
        Log.i(TAG, "인기순 20 가져오기");
        restAdapter.create(OdazumService.class).fancygoods(new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {
                mPosts = posts;
                /*
                // TODO 임시로 넣은 코드
                for (int i = 0; i < 29; i++) {
                    mPosts.add(posts.get(0));
                }
                */
                adapter = new PostListAdapter(getActivity().getApplicationContext(), posts);
                gridView.setAdapter(adapter);
                for (int i = 0; i < posts.size(); i++) {
                    Log.d(TAG, "데이터는 " + posts.get(i).getTitle());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "post가져오기 에러 ");
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