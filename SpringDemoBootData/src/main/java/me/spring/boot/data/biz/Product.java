package me.spring.boot.data.biz;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author baiyu
 * <p>
 * 产品信息表
 */
public class Product implements Serializable {

    private int id;

    private String pro_name;

    private float price;

    private String proDescription;

    private String thumbImage;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 &&
                Objects.equals(pro_name, product.pro_name) &&
                Objects.equals(proDescription, product.proDescription) &&
                Objects.equals(thumbImage, product.thumbImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pro_name, price, proDescription, thumbImage);
    }
}
