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

import org.w3c.dom.Text;

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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=inflater.inflate(R.layout.viewpager, null);

        mProduct = mProducts.get(position);
        Log.d(TAG, mProduct.toString());

        ImageView img= (ImageView)view.findViewById(R.id.pager_img);
        TextView name = (TextView)view.findViewById(R.id.pager_name);
        TextView txt = (TextView)view.findViewById(R.id.pager_txt);
        TextView price = (TextView)view.findViewById(R.id.pager_price);

        name.setText(mProduct.getName());
        txt.setText(mProduct.getText());
        price.setText(" 최저 ~" + mProduct.getPrice() + "원 ");
        Glide.with(mContext)
                .load(mProduct.getImage())
                .crossFade()
                .override(800, 800)
                .into(img);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);

    }

    //instantiateItem() 메소드에서 리턴된 Ojbect가 View가  맞는지 확인하는 메소드
    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v==obj;
    }
}
