package ro.utcn.fulgadan.BookStore.data.entity;

import javax.persistence.*;

@Entity
@Table(name="shipping_info")
public class ShippingInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private Integer shippingId;

    @Column(name="address")
    private String address;

    @Column(name="age")
    private Integer age;

    @Column(name="phone")
    private String phone;

    public ShippingInfo() {}

    public ShippingInfo(Integer shippingId, String address, Integer age, String phone) {
        this.shippingId = shippingId;
        this.address = address;
        this.age = age;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
