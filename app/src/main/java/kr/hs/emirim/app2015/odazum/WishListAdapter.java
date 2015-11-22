package kr.hs.emirim.app2015.odazum;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Student on 2015-10-22.
 */
public class WishListAdapter extends BaseAdapter {

    private static String TAG="오다주움:WishListAdapter";
    private Post mPost;
    MediaPlayer player;
    private Context mContext;
    private List<Post> mPosts;
    public WishListAdapter(Context aContext, List<Post> aPosts) {
        mContext = aContext;
        mPosts = aPosts;
    }

    @Override
    public int getCount() {
        return mPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return mPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LinearLayout tmpLL;
        Log.d(TAG, "getView호출");
        if(convertView==null)
        {
            tmpLL=(LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.fragment_wishlist_item, parent,false);
        }
        else
        {
            tmpLL=(LinearLayout)convertView;
        }
        //ImageButton btnig=(ImageButton)tmpLL.findViewById(R.id.btn_send);
        TextView tmpTV=(TextView)tmpLL.findViewById(R.id.wish_title);
        mPost = mPosts.get(position);
        Log.d(TAG, "아이템 생성 : " + mPost.toString());
        tmpTV.setText(mPost.getTitle());

        ImageView tmpIV = (ImageView) tmpLL.findViewById(R.id.wish_image);
        Glide.with(mContext)
                .load(mPost.getImage())
                .crossFade()
                .centerCrop()
                .override(250, 250)
                .into(tmpIV);

        // 글라이드 소스가 들어갈 부분
        // https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-xpf1/v/t1.0-9/12241701_961036407322267_2265052574018231303_n.jpg?oh=46d4204fe1a6aa2dc2941095c3519008&oe=56F0C60E&__gda__=1454770788_07a23b747fc0b07812e745de85149808

        //TextView tmpTV2=(TextView)tmpLL.findViewById(R.id.wish_date);
        //tmpTV2.setText(mPost.getDate());

        return tmpLL;
    }

//
//
//    private void playAudio(String url) throws Exception{
//        killMediaPlayer();
//
//        player = new MediaPlayer();
//        player.setDataSource(url);
//        player.prepare();
//        player.start();
//    }
//    private void killMediaPlayer() {
//        if(player != null){
//            try {
//                player.release();
//            } catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//
//    }
}
