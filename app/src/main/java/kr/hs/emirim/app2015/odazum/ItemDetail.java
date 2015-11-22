package kr.hs.emirim.app2015.odazum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Student on 2015-11-20.
 */
public class ItemDetail extends Activity{

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
                Toast.makeText(ItemDetail.this, "보관함에 저장되었습니다.", Toast.LENGTH_SHORT).show();
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

    }
}
