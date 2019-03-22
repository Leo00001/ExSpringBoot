package me.spring.boot.data.biz;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author baiyu
 * <p>
 * 产品信息表
 */
public class Product implements Serializable {


    private String name;

    private float price;

    private String description;

    private String thumbImage;

    public Product() {
    }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(thumbImage, product.thumbImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description, thumbImage);
    }
}
