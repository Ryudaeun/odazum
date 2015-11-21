package kr.hs.emirim.app2015.odazum;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by student on 2015-11-17.
 */
public class Gender {
    @Getter @Setter
    int id;
    @Getter @Setter
    String name;
    public Gender() {}
    public Gender(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
