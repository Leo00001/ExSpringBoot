package me.spring.boot.data.biz;

import javax.persistence.*;

/**
 * @author baiyu
 * <p>
 * Jpa操作的实体类
 */
@Entity
@Table(name = "al_address", indexes = {@Index(columnList = "id")})
public class Address {


    /**
     * Id 自动增长
     * https://blog.csdn.net/qq_40325734/article/details/81409196
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)

    private Long id;

    @Column(name = "province", length = 20)
    private String province;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "detail", length = 200)
    private String detail;

    @Column(name = "post_code", length = 10)
    private String postCode;


    @Column(name = "mobile", length = 11)
    private String mobile;

    @Column(name = "uname", length = 10)
    private String name;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", detail='" + detail + '\'' +
                ", postCode='" + postCode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (province != null ? !province.equals(address.province) : address.province != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (detail != null ? !detail.equals(address.detail) : address.detail != null) return false;
        if (postCode != null ? !postCode.equals(address.postCode) : address.postCode != null) return false;
        if (mobile != null ? !mobile.equals(address.mobile) : address.mobile != null) return false;
        return name != null ? name.equals(address.name) : address.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + (postCode != null ? postCode.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
