package kr.hs.emirim.app2015.odazum;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by student on 2015-11-17.
 */
public class age_post {
    @Getter @Setter
    int p_id;
    @Getter @Setter
    int a_id;

    public age_post() {
    }
    public age_post(int p_id, int a_id) {
        this.p_id = p_id;
        this.a_id = a_id;
    }
}
