package kr.hs.emirim.app2015.odazum;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by student on 2015-11-17.
 */
public class gender_post {

    @Getter @Setter
    int p_id;
    @Getter @Setter
    int g_id;

    public gender_post() {
    }
    public gender_post(int p_id, int g_id) {
        this.p_id = p_id;
        this.g_id = g_id;
    }
}
