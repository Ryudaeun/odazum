package kr.hs.emirim.app2015.odazum;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class GridViewDemoFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<GridViewItem> mItems;    // GridView items list
    private GridViewAdapter mAdapter;    // GridView adapter


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<GridViewItem>();
        Resources resources = getResources();

        mItems.add(new GridViewItem(resources.getDrawable(R.drawable.pink_wallet), "주위에 핑크덕후 한명쯤은 있겠지"));
        mItems.add(new GridViewItem(resources.getDrawable(R.drawable.christmas), "크리스마스에 이별하고싶다면"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate the root view of the fragment
        View fragmentView = inflater.inflate(R.layout.fragment_gridview, container, false);

        // initialize the adapter
        mAdapter = new GridViewAdapter(getActivity(), mItems);

        // initialize the GridView
        GridView gridView = (GridView) fragmentView.findViewById(R.id.gridView);
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(this);

        return fragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // retrieve the GridView item
        GridViewItem item = mItems.get(position);

        Bitmap bitmap = Bitmap.createBitmap(300, 400, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        item.icon.setBounds(0, 0, 300, 400);
        item.icon.draw(canvas);
        // 사실 이 위에꺼가 drawble 객체변수를 bitmap으로 바꿔준다고 해서 별 소용이 없는 것 같다. 걍 아무것도 안하는 것 같다
        // 그래서 xml파일에서 이미지파일을 걍 고정시켜버렸다.
        // 그래서 뭘 누르던 똑같은 이미지만 나온다.

        Intent intent = new Intent(getActivity(), ItemDetail.class);
        intent.putExtra("title",item.title);
        intent.putExtra("img", bitmap);
        startActivity(intent);

        // do something
    }
}