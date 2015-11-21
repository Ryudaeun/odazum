package kr.hs.emirim.app2015.odazum;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by student on 2015-11-17.
 */
public class Tag {
    @Getter @Setter
    int id;
    @Getter @Setter
    String word;
    public Tag() {}
    public Tag(int id, String word) {
        this.id = id;
        this.word = word;
    }
}
