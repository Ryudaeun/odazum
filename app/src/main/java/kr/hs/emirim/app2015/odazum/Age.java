package kr.hs.emirim.app2015.odazum;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by student on 2015-11-17.
 */
public class Age {
    @Getter @Setter
    int id;
    @Getter @Setter
    String age;
    public Age() {}
    public Age(int id, String age) {
        this.id = id;
        this.age = age;
    }
}
