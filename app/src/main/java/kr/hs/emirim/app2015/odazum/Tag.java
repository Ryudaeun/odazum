package kr.hs.emirim.app2015.odazum;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by student on 2015-11-17.
 */
public class Tag {
    @Getter @Setter
    int no;
    @Getter @Setter
    String name;
    public Tag() {}
    public Tag(int no, String name) {
        this.no = no;
        this.name = name;
    }
}
