package kr.hs.emirim.app2015.odazum;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by student on 2015-11-17.
 */
public class Product {
    @Getter @Setter
    int id;
    @Getter @Setter
    String name;
    @Getter @Setter
    int price;
    @Getter @Setter
    String image;
    @Getter @Setter
    int p_id;
    @Getter @Setter
    String text;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", p_id=" + p_id +
                ", text='" + text + '\'' +
                '}';
    }

    public Product() {}
    public Product(int id, String name, int price, String image, int p_id, String text) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.p_id = p_id;
        this.text = text;
    }
}
