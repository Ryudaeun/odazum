package kr.hs.emirim.app2015.odazum;

import android.graphics.drawable.Drawable;

public class GridViewItem {
    public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String title;        // the text for the GridView item title

    public GridViewItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }
}