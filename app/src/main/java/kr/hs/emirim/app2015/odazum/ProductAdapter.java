package kr.hs.emirim.app2015.odazum;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Student on 2015-10-22.
 */
public class ProductAdapter extends PagerAdapter {
    LayoutInflater inflater;
    private static String TAG = "오다주움:ProductListAdapter";
    private Product mProduct;
    private Context mContext;
    private List<Product> mProducts;

    public ProductAdapter(LayoutInflater inflater) {
        //전달 받은 LayoutInflater를 멤버변수로 전달
        this.inflater=inflater;
    }

    public ProductAdapter(AppCompatActivity aActivity, List<Product> aProducts) {
        mContext = aActivity.getApplicationContext();
        mProducts = aProducts;
        this.inflater=aActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        LinearLayout tmpLL;
        Log.d(TAG, "getView호출");
        if(convertView==null)
        {
            tmpLL=(LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.activity_products, parent,false);
        }
        else
        {
            tmpLL=(LinearLayout)convertView;
        }

        View view=inflater.inflate(R.layout.activity_products_item, null);

        mProduct = mProducts.get(position);

        ImageView img= (ImageView)view.findViewById(R.id.pager_img);
        TextView txt = (TextView)view.findViewById(R.id.pager_txt);
        TextView price = (TextView)view.findViewById(R.id.pager_price);

        Log.d(TAG, "아이템 생성 : " + mProduct.toString());
        txt.setText(mProduct.getName());

        Glide.with(mContext)
                .load(mProduct.getImage())
                .crossFade()
                .centerCrop()
                .override(800, 800)
                .into(img);

        // 글라이드 소스가 들어갈 부분
        // https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-xpf1/v/t1.0-9/12241701_961036407322267_2265052574018231303_n.jpg?oh=46d4204fe1a6aa2dc2941095c3519008&oe=56F0C60E&__gda__=1454770788_07a23b747fc0b07812e745de85149808

        //TextView tmpTV2=(TextView)tmpLL.findViewById(R.id.wish_date);
        //tmpTV2.setText(mPost.getDate());

        return tmpLL;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub

        //ViewPager에서 보이지 않는 View는 제거
        //세번째 파라미터가 View 객체 이지만 데이터 타입이 Object여서 형변환 실시
        container.removeView((View)object);

    }

    //instantiateItem() 메소드에서 리턴된 Ojbect가 View가  맞는지 확인하는 메소드
    @Override
    public boolean isViewFromObject(View v, Object obj) {
        // TODO Auto-generated method stub
        return v==obj;
    }
}
