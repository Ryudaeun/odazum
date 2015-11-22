package kr.hs.emirim.app2015.odazum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
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
public class ItemDetail extends Activity{

    private static final String TAG = "오다주움:PostListF";
    RestAdapter restAdapter;
    GridView gridView;
    int mPosition;
    Post mPost;
    List<Post> mPosts;
    PostListAdapter adapter;

    public ItemDetail() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();

        ViewPager item_pager = (ViewPager)findViewById(R.id.detail_pager);
        final PagerAdapter pagerAdapter = item_pager.getAdapter();
        item_pager.setAdapter(pagerAdapter);

//        String title = intent.getExtras().getString("title");
//        TextView textView = (TextView)findViewById(R.id.post_title);
//        textView.setText(title);

//        int img = intent.getExtras().getInt("img");
//        ImageView imageView = (ImageView)findViewById(R.id.imageView);
//        imageView.setImageResource(img);

        ImageButton imageButton = (ImageButton)findViewById(R.id.like_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ItemDetail.this, R.string.save_wishlist, Toast.LENGTH_SHORT).show();
            }
        });

        final String str = "핑크지갑";

        ImageButton imageButton1 = (ImageButton)findViewById(R.id.link_btn);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.shopping.naver.com/search/all_search.nhn?query="+str+"&cat_id=&frm=NVSHATC&nlu=true")));
            }
        });

        //------------------------------------------------------------
        SharedPreferences prefs = getSharedPreferences("odazum", Context.MODE_PRIVATE);
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

        gridView = (GridView)findViewById(R.id.myGridView);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // change fragment
            }
        });

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
        restAdapter.create(OdazumService.class).posts(new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {
                mPosts = posts;
                // TODO 임시로 넣은 코드
                for (int i = 0; i < 29; i++) {
                    mPosts.add(posts.get(0));
                }
                adapter = new PostListAdapter(getApplicationContext(), posts);
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
