package kr.hs.emirim.app2015.odazum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


public class MainFragment extends Fragment implements View.OnClickListener {
    RestAdapter restAdapter;
    int mPosition;
    String[] pager_word = new String[3];
    ViewPager pager;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main2, container, false);

        pager_word[0] = "크리스마스";
        pager_word[1] = "수능";
        pager_word[2] = "다이어리";

        pager = (ViewPager)view.findViewById(R.id.pager);
        pager.setOnClickListener(this);

        CustomAdapter adapter= new CustomAdapter(getActivity().getLayoutInflater());
        //ViewPager에 Adapter 설정
        pager.setAdapter(adapter);
        mPosition = pager.getCurrentItem();

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

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new MostWishFragment())
                .commit();
        return view;
    }

    public void onClick(View v) {
        Log.d("MainFragment", "onClick");
        /*SharedPreferences prefs = getActivity().getSharedPreferences("odazum", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("word", pager_word[mPosition]);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new SearchbarPostListFragment())
                .commit();*/
    }
}