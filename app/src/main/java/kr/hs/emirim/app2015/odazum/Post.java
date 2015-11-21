package kr.hs.emirim.app2015.odazum;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by student on 2015-11-17.
 */
public class Post {
    @Getter @Setter
    int id;
    @Getter @Setter
    String title;
    @Getter @Setter
    String date;
    @Getter @Setter
    String image;
    @Getter @Setter
    int click;
    @Getter @Setter
    int wish;
    //Category category;

    public Post(){}
    public Post(int id, String title, String date, String image, int click, int wish) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.image = image;
        this.click = click;
        this.wish = wish;
        //this.category = category;
    }
}
