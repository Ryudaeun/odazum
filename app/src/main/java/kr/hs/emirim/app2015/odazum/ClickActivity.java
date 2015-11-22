package kr.hs.emirim.app2015.odazum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ClickActivity extends Fragment {
    public static ClickActivity newInstance() {
        ClickActivity fragment = new ClickActivity();
        return fragment;
    }

    public ClickActivity() {

// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_click, container, false);
    }
}
