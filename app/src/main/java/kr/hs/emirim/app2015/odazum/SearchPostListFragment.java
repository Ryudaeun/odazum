package kr.hs.emirim.app2015.odazum;

import android.content.Context;
import android.content.Intent;
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
public class SearchPostListFragment extends Fragment {

    private static final String TAG = "????:SearchPLF";
    RestAdapter restAdapter;
    GridView gridView;
    int mPosition;
    int mPostId;
    Post mPost;
    List<Post> mPosts;
    PostListAdapter adapter;

    int s_gender;
    int s_age;
    int s_orderby;
    int s_max_price;
    String s_tags;

    public SearchPostListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_postlist, container, false);

        //------------------------------------------------------------
        SharedPreferences prefs = getActivity().getSharedPreferences("odazum", Context.MODE_PRIVATE);
        s_gender = prefs.getInt("gender", 0);
        s_age = prefs.getInt("age", 0);
        s_tags = prefs.getString("tags", "");
        s_max_price = prefs.getInt("max_price", 0);
        s_orderby = prefs.getInt("orderby", 0);

        Log.d(TAG, "gender: "+s_gender+"/" + "age: " + s_age + "/" + "tags: " + s_tags + "/" + "max_price: " + s_max_price + "/" + "orderby:" + s_orderby);

        //SharedPreferences.Editor ed = prefs.edit();
        //ed.putInt("post_id", mPostId);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(java.sql.Date.class, new DateTypeAdapter())
                .create();

        restAdapter = new RestAdapter.Builder()
                //?? ?? ??
                .setLogLevel(RestAdapter.LogLevel.FULL)
                        //BASE_URL ??
                .setEndpoint(OdazumService.API_URL)
                        //OkHttpClient ??
                .setClient(new OkClient(new OkHttpClient()))
                        //Gson Converter ??
                .setConverter(new GsonConverter(gson))
                .build();
        //------------------------------------------------------------

        gridView = (GridView) view.findViewById(R.id.myGridView);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    private void getData() {
        Log.i(TAG, "????? ????");
        restAdapter.create(OdazumService.class).searchposts(s_gender, s_age, s_tags, s_max_price, s_orderby,
                new Callback<List<Post>>() {
                    @Override
                    public void success(List<Post> posts, Response response) {
                        mPosts = posts;
                        adapter = new PostListAdapter(getActivity().getApplicationContext(), posts);
                        gridView.setAdapter(adapter);
                        for (int i = 0; i < posts.size(); i++) {
                            Log.d(TAG, "???? " + posts.get(i).getTitle());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i(TAG, "post???? ?? ");
                    }
                });
    }

}