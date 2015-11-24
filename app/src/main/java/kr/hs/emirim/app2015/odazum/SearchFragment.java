package kr.hs.emirim.app2015.odazum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
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
public class SearchFragment extends Fragment {
    RestAdapter restAdapter;
    int mPosition;
    Tag mTag;
    List<Tag> mTags;
    List<Button> mButtonTags = new ArrayList<Button>();

    Button woman;
    Button man;
    Button inquiry;
    Button newest;

    ImageButton age1;
    ImageButton age2;
    ImageButton age3;
    ImageButton age4;
    ImageButton age5;
    ImageButton age6;

//    Button tag1;
//    Button tag2;
//    Button tag3;
//    Button tag4;
//    Button tag5;
//    Button tag6;

    EditText text_price;

    Button result;

    int gender = 0;
    int order_by = 0;
    int age = 0;
    //int price = 0;

    String tag[] = new String[6];

    int i = 0;

    String sel_text = "#ffffff";
    String desel_text = "#787878";

    public SearchFragment() {
    }

    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        Log.v("minn", "SearchFragment");

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
        //-----------------------------------------------------------

        man = (Button)view.findViewById(R.id.but_man);
        gender_set(man);
        woman = (Button)view.findViewById(R.id.but_woman);
        gender_set(woman);
        inquiry = (Button)view.findViewById(R.id.but_inquiry);
        order_set(inquiry);
        newest = (Button)view.findViewById(R.id.but_new);
        order_set(newest);

        text_price = (EditText)view.findViewById(R.id.text_price);

        age1 = (ImageButton)view.findViewById(R.id.but_age_1);
        age1.setAlpha(255);
        age_set(age1);
        age2 = (ImageButton)view.findViewById(R.id.but_age_2);
        age2.setAlpha(0);
        age_set(age2);
        age3 = (ImageButton)view.findViewById(R.id.but_age_3);
        age3.setAlpha(0);
        age_set(age3);
        age4 = (ImageButton)view.findViewById(R.id.but_age_4);
        age4.setAlpha(0);
        age_set(age4);
        age5 = (ImageButton)view.findViewById(R.id.but_age_5);
        age5.setAlpha(0);
        age_set(age5);
        age6 = (ImageButton)view.findViewById(R.id.but_age_6);
        age6.setAlpha(0);
        age_set(age6);

        for(int i = 0; i <6; i++) {
            mButtonTags.add((Button) view.findViewById(R.id.tag_1 + i));
            //mButtonTags.get(i).setText("불러오는 중..");
            tag_set((Button) mButtonTags.get(i));
        }
//
//        tag1 = (Button)view.findViewById(R.id.tag_one);
//        tag1.setText("#발렌타인");
//        tag_set(tag1);
//        tag2 = (Button)view.findViewById(R.id.tag_two);
//        tag2.setText("#화이트데이");
//        tag_set(tag2);
//        tag3 = (Button)view.findViewById(R.id.tag_three);
//        tag3.setText("#크리스마스");
//        tag_set(tag3);
//        tag4 = (Button)view.findViewById(R.id.tag_four);
//        tag4.setText("#졸업");
//        tag_set(tag4);
//        tag5 = (Button)view.findViewById(R.id.tag_five);
//        tag5.setText("#성년의날");
//        tag_set(tag5);
//        tag6 = (Button)view.findViewById(R.id.tag_six);
//        tag6.setText("#입학");
//        tag_set(tag6);

        for(int i=0; i<6; i++) {
            tag[i] = null;
        }

        result = (Button)view.findViewById(R.id.but_result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getActivity().getSharedPreferences("odazum", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("gender", gender);
                editor.putInt("age", age);
                editor.putInt("orderby", order_by);
                editor.putInt("max_price", Integer.parseInt(text_price.getText().toString()));
                String tags = tag.toString();
                editor.putString("tags", tags);
                editor.commit();
                getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SearchPostListFragment())
                    .commit();
            }
        });

        return view;
    }

    public void gender_set(Button but_gender) {
        if(but_gender == man) {
            man.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    man.setBackgroundDrawable(getResources().getDrawable(R.drawable.select));
                    man.setTextColor(Color.parseColor(sel_text));
                    woman.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_deselect));
                    woman.setTextColor(Color.parseColor(desel_text));
                    gender = 2;
                }
            });
        }
        else if(but_gender == woman) {
            woman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    man.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_deselect));
                    man.setTextColor(Color.parseColor(desel_text));
                    woman.setBackgroundDrawable(getResources().getDrawable(R.drawable.select));
                    woman.setTextColor(Color.parseColor(sel_text));
                    gender = 1;
                }
            });
        }
    }

    public void order_set(Button but_order) {
        if(but_order == inquiry) {
            inquiry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inquiry.setBackgroundDrawable(getResources().getDrawable(R.drawable.select));
                    inquiry.setTextColor(Color.parseColor(sel_text));
                    newest.setBackgroundDrawable(getResources().getDrawable(R.drawable.right_deselect));
                    newest.setTextColor(Color.parseColor(desel_text));
                    order_by = 1;
                }
            });
        }
        else if(but_order == newest) {
            newest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inquiry.setBackgroundDrawable(getResources().getDrawable(R.drawable.left_deselect));
                    inquiry.setTextColor(Color.parseColor(desel_text));
                    newest.setBackgroundDrawable(getResources().getDrawable(R.drawable.select));
                    newest.setTextColor(Color.parseColor(sel_text));
                    order_by = 2;
                }
            });
        }
    }

    public void age_set(ImageButton but_age) {
        if(but_age == age1) {
            age1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    age1.setAlpha(255);
                    age2.setAlpha(0);
                    age3.setAlpha(0);
                    age4.setAlpha(0);
                    age5.setAlpha(0);
                    age6.setAlpha(0);
                    age = 1;
                }
            });
        }
        else if(but_age == age2) {
            age2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    age1.setAlpha(0);
                    age2.setAlpha(255);
                    age3.setAlpha(0);
                    age4.setAlpha(0);
                    age5.setAlpha(0);
                    age6.setAlpha(0);
                    age = 2;
                }
            });
        }
        else if(but_age == age3) {
            age3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    age1.setAlpha(0);
                    age2.setAlpha(0);
                    age3.setAlpha(255);
                    age4.setAlpha(0);
                    age5.setAlpha(0);
                    age6.setAlpha(0);
                    age = 3;
                }
            });
        }
        else if(but_age == age4) {
            age4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    age1.setAlpha(0);
                    age2.setAlpha(0);
                    age3.setAlpha(0);
                    age4.setAlpha(255);
                    age5.setAlpha(0);
                    age6.setAlpha(0);
                    age = 4;
                }
            });
        }
        else if(but_age == age5) {
            age5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    age1.setAlpha(0);
                    age2.setAlpha(0);
                    age3.setAlpha(0);
                    age4.setAlpha(0);
                    age5.setAlpha(255);
                    age6.setAlpha(0);
                    age = 5;
                }
            });
        }
        else if(but_age == age6) {
            age6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    age1.setAlpha(0);
                    age2.setAlpha(0);
                    age3.setAlpha(0);
                    age4.setAlpha(0);
                    age5.setAlpha(0);
                    age6.setAlpha(255);
                    age = 6;
                }
            });
        }
    }
    private static final String TAG = "오다주움:MainFrag";
    boolean flag[] = new boolean[6];

    public void tag_set(Button but_tag) {
        for(int i = 0; i < 6; i++) {
            flag[i] = false;
        }

        int button_number = but_tag.getId()  - R.id.tag_1;
        mButtonTags.get(button_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int button_number = v.getId()  - R.id.tag_1;
                Button tag_but = mButtonTags.get(button_number);
                if(flag[button_number]) {
                    if(tag[button_number] != null && tag[button_number].equals(tag_but.getText().toString()))
                        tag[button_number] = null;
                    tag_but.setBackgroundDrawable(getResources().getDrawable(R.drawable.recommend));
                    tag_but.setTextColor(Color.parseColor(desel_text));
                    flag[button_number] = false;
                }
                else {
                    tag[button_number] = tag_but.getText().toString();
                    tag_but.setBackgroundDrawable(getResources().getDrawable(R.drawable.select));
                    tag_but.setTextColor(Color.parseColor(sel_text));
                    flag[button_number] = true;

                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        getTags();
    }

    private void getTags() {
        /**
         * 통신 콜백 메서드 Callback<List<Address>> callback
         */
        Log.i(TAG, "위시리스트 가져오기");


        restAdapter.create(OdazumService.class).randomtags( new Callback<List<Tag>>() {
            @Override
            public void success(List<Tag> tags, Response response) {
                mTags = tags;

                for (int i = 0; i < tags.size(); i++) {
                    Log.d(TAG, "데이터는 " + tags.get(i).getName());
                }
                for(int i = 0; i < tags.size(); i++){
                    mButtonTags.get(i).setText(tags.get(i).getName());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(TAG, "product 가져오기 에러 ");
            }
        });
    }


}




