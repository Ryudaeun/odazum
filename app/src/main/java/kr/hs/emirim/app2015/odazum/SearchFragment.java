package kr.hs.emirim.app2015.odazum;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment {

    public SearchFragment() {
    }

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

    Button tag1;
    Button tag2;
    Button tag3;
    Button tag4;
    Button tag5;
    Button tag6;
    
    EditText text_price;

    Button result;

    int gender = 0;
    int order_by = 0;
    int age = 0;
    int price = 0;
    int tag[] = new int[6];

    int i = 0;

    String sel_text = "#ffffff";
    String desel_text = "#787878";

    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Intent intent = getActivity().getIntent();

        man = (Button)view.findViewById(R.id.but_man);
        gender_set(man);
        woman = (Button)view.findViewById(R.id.but_woman);
        gender_set(woman);
        inquiry = (Button)view.findViewById(R.id.but_inquiry);
        order_set(inquiry);
        newest = (Button)view.findViewById(R.id.but_new);
        order_set(newest);

        text_price = (EditText)view.findViewById(R.id.text_price);
        
        age1 = (ImageButton)view.findViewById(R.id.but_age_one);
        age1.setAlpha(255);
        age_set(age1);
        age2 = (ImageButton)view.findViewById(R.id.but_age_two);
        age2.setAlpha(0);
        age_set(age2);
        age3 = (ImageButton)view.findViewById(R.id.but_age_three);
        age3.setAlpha(0);
        age_set(age3);
        age4 = (ImageButton)view.findViewById(R.id.but_age_four);
        age4.setAlpha(0);
        age_set(age4);
        age5 = (ImageButton)view.findViewById(R.id.but_age_five);
        age5.setAlpha(0);
        age_set(age5);
        age6 = (ImageButton)view.findViewById(R.id.but_age_six);
        age6.setAlpha(0);
        age_set(age6);

        tag1 = (Button)view.findViewById(R.id.tag_one);
        tag1.setText("#발렌타인");
        tag_set(tag1);
        tag2 = (Button)view.findViewById(R.id.tag_two);
        tag2.setText("#화이트데이");
        tag_set(tag2);
        tag3 = (Button)view.findViewById(R.id.tag_three);
        tag3.setText("#크리스마스");
        tag_set(tag3);
        tag4 = (Button)view.findViewById(R.id.tag_four);
        tag4.setText("#졸업");
        tag_set(tag4);
        tag5 = (Button)view.findViewById(R.id.tag_five);
        tag5.setText("#성년의날");
        tag_set(tag5);
        tag6 = (Button)view.findViewById(R.id.tag_six);
        tag6.setText("#입학");
        tag_set(tag6);

        for(int i=0; i<6; i++) {
            tag[i] = 0;
        }

        result = (Button)view.findViewById(R.id.but_result);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    price = Integer.parseInt(text_price.getText().toString());
                    //getSupportFragmentManager().beginTransaction()
                    //    .replace(R.id.container, new ClickActivity())
                    //    .commit();
            }
        });

        return view;
    }
    
    public void gender_set(Button but_gender) {
        if(but_gender == man) {
            man.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    man.setBackgroundResource(R.drawable.select);
                    man.setTextColor(Color.parseColor(sel_text));
                    woman.setBackgroundResource(R.drawable.right_deselect);
                    woman.setTextColor(Color.parseColor(desel_text));
                    gender = 0;
                }
            });
        }
        else if(but_gender == woman) {
            woman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    man.setBackgroundResource(R.drawable.left_deselect);
                    man.setTextColor(Color.parseColor(desel_text));
                    woman.setBackgroundResource(R.drawable.select);
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
                    inquiry.setBackgroundResource(R.drawable.select);
                    inquiry.setTextColor(Color.parseColor(sel_text));
                    newest.setBackgroundResource(R.drawable.right_deselect);
                    newest.setTextColor(Color.parseColor(desel_text));
                    order_by = 0;
                }
            });
        }
        else if(but_order == newest) {
            newest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    inquiry.setBackgroundResource(R.drawable.left_deselect);
                    inquiry.setTextColor(Color.parseColor(desel_text));
                    newest.setBackgroundResource(R.drawable.select);
                    newest.setTextColor(Color.parseColor(sel_text));
                    order_by = 1;
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
    int flag = 0;
    public void tag_set(Button but_tag) {
        if(but_tag == tag1) {
            tag1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag%2 != 0) {
                        for(int i = 0 ; i < 6 ; i++) {
                            if(tag[i] != 0 && tag[i] == 1) {
                                tag[i] = tag[i+1];
                            }
                        }
                        tag[5] = 0;
                        tag1.setBackgroundResource(R.drawable.recommend);
                        tag1.setTextColor(Color.parseColor(desel_text));
                        flag++;
                    }
                    else {
                        tag[i++] = 1;
                        tag1.setBackgroundResource(R.drawable.select);
                        tag1.setTextColor(Color.parseColor(sel_text));
                        flag++;
                    }
                }
            });
        }
        else if(but_tag == tag2) {
            tag2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag%2 != 0) {
                        for(int i = 0 ; i < 6 ; i++) {
                            if(tag[i] != 0 && tag[i] == 2) {
                                tag[i] = tag[i+1];
                            }
                        }
                        tag[5] = 0;
                        tag2.setBackgroundResource(R.drawable.recommend);
                        tag2.setTextColor(Color.parseColor(desel_text));
                        flag++;
                    }
                    else {
                        tag[i++] = 2;
                        tag2.setBackgroundResource(R.drawable.select);
                        tag2.setTextColor(Color.parseColor(sel_text));
                        flag++;
                    }
                }
            });
        }
        else if(but_tag == tag3) {
            tag3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag%2 != 0) {
                        for(int i = 0 ; i < 6 ; i++) {
                            if(tag[i] != 0 && tag[i] == 3) {
                                tag[i] = tag[i+1];
                            }
                        }
                        tag[5] = 0;
                        tag3.setBackgroundResource(R.drawable.recommend);
                        tag3.setTextColor(Color.parseColor(desel_text));
                        flag++;
                    }
                    else {
                        tag[i++] = 3;
                        tag3.setBackgroundResource(R.drawable.select);
                        tag3.setTextColor(Color.parseColor(sel_text));
                        flag++;
                    }
                }
            });
        }
        else if(but_tag == tag4) {
            tag4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag%2 != 0) {
                        for(int i = 0 ; i < 6 ; i++) {
                            if(tag[i] != 0 && tag[i] == 4) {
                                tag[i] = tag[i+1];
                            }
                        }
                        tag[5] = 0;
                        tag4.setBackgroundResource(R.drawable.recommend);
                        tag4.setTextColor(Color.parseColor(desel_text));
                        flag++;
                    }
                    else {
                        tag[i++] = 4;
                        tag4.setBackgroundResource(R.drawable.select);
                        tag4.setTextColor(Color.parseColor(sel_text));
                        flag++;
                    }
                }
            });
        }
        else if(but_tag == tag5) {
            tag5.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View v) {
                    if(flag%2 != 0) {
                        for(int i = 0 ; i < 6 ; i++) {
                            if(tag[i] != 0 && tag[i] == 5) {
                                tag[i] = tag[i+1];
                            }
                        }
                        tag[5] = 0;
                        tag5.setBackgroundResource(R.drawable.recommend);
                        tag5.setTextColor(Color.parseColor(desel_text));
                        flag++;
                    }
                    else {
                        tag[i++] = 5;
                        tag5.setBackgroundResource(R.drawable.select);
                        tag5.setTextColor(Color.parseColor(sel_text));
                        flag++;
                    }
                }
            });
        }
        else if(but_tag == tag6) {
            tag6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag%2 != 0) {
                        for(int i = 0 ; i < 6 ; i++) {
                            if(tag[i] != 0 && tag[i] == 6) {
                                tag[i] = tag[i+1];   
                            }
                        }
                        tag[5] = 0;
                        tag6.setBackgroundResource(R.drawable.recommend);
                        tag6.setTextColor(Color.parseColor(desel_text));
                        flag++;
                    }
                    else {
                        tag[i++] = 6;
                        tag6.setBackgroundResource(R.drawable.select);
                        tag6.setTextColor(Color.parseColor(sel_text));
                        flag++;
                    }
                }
            });
        }
    }
}
