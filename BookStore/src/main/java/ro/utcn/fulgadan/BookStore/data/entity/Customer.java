package ro.utcn.fulgadan.BookStore.data.entity;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name="shipping_id")
    private ShippingInfo info;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public Customer() {}

    public Customer(CustomerBuilder builder) {
        this.customerId = builder.customerId;
        this.name = builder.name;
        this.info = builder.info;
        this.user = builder.user;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public ShippingInfo getInfo() {
        return info;
    }

    public User getUser() {
        return user;
    }

    public static class CustomerBuilder {
        private Integer customerId;
        private String name;
        private ShippingInfo info;
        private User user;

        public CustomerBuilder setCustomerId(Integer id) {
            this.customerId = id;
            return this;
        }

        public CustomerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder setInfo(ShippingInfo info) {
            this.info = info;
            return this;
        }

        public CustomerBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}

