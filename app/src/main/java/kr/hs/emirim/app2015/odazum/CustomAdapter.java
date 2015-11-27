package kr.hs.emirim.app2015.odazum;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class CustomAdapter extends PagerAdapter {

    private static final String TAG = "오다주움:MainViewPager";

    LayoutInflater inflater;
    int mPosition;

    public CustomAdapter() {}

    public CustomAdapter(LayoutInflater inflater) {
        // TODO Auto-generated constructor stub
        //전달 받은 LayoutInflater를 멤버변수로 전달
        this.inflater=inflater;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3; //이미지 개수 리턴(그림이 10개라서 10을 리턴)
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub

        View view=inflater.inflate(R.layout.viewpager_childview, null);

        ImageView img= (ImageView)view.findViewById(R.id.img_viewpager_childimage);

        switch(position) {
            case 0:
                img.setImageResource(R.drawable.image1);
                break;
            case 1:
                img.setImageResource(R.drawable.image2);
                break;
            case 2:
                img.setImageResource(R.drawable.image3);
                break;
        }
        container.addView(view);

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub

        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        // TODO Auto-generated method stub
        return v==obj;
    }

}