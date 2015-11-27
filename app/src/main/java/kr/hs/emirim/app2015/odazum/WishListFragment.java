package kr.hs.emirim.app2015.odazum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

/**
 * A placeholder fragment containing a simple view.
 */
public class WishListFragment extends Fragment  {

    private static final String TAG = "오다주움:WishListF";
    RestAdapter restAdapter;
    ListView listView;
    int mPosition;
    Post mPost;
    List<Post> mPosts;
    WishListAdapter adapter;
    String mWishItemList;
    int s_click;
    int s_wish;


    public WishListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MainActivity.mLastMenu = MainActivity.WISH;

        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);


        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(java.sql.Date.class, new DateTypeAdapter())
                .create();

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

        listView=(ListView)view.findViewById(R.id.myListView);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                intent.putExtra("post_id", mPosts.get(position).getId());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
    String post_id;
    private void getData(){
        //------------------------------------------------------------
        SharedPreferences prefs = getActivity().getSharedPreferences("odazum", Context.MODE_PRIVATE);
        mWishItemList = prefs.getString("wish_id_list", "");
        s_click = prefs.getInt("click", 0);
        s_wish = prefs.getInt("wish", 0);

        Log.i(TAG, "위시리스트 가져오기");
        restAdapter.create(OdazumService.class)
                .wishposts(mWishItemList, s_click, s_wish, new Callback<List<Post>>() {
                    @Override
                    public void success(List<Post> posts, Response response) {
                        mPosts = posts;
                        adapter = new WishListAdapter(getActivity().getApplicationContext(), posts);
                        listView.setAdapter(adapter);
                        for (int i = 0; i < posts.size(); i++) {
                            Log.d(TAG, "데이터는 " + posts.get(i).getTitle());
                            Log.d(TAG, "id" + mWishItemList);
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.i(TAG, "wishlist 가져오기 에러 ");
                    }
        });
    }
}
