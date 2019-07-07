package ro.utcn.fulgadan.BookStore.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer cartId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cart_product",
            joinColumns = { @JoinColumn(name = "cart_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")})
    private List<Product> products = new ArrayList<>();

    public ShoppingCart() {
    }

    public ShoppingCart(Integer cartId, User user, List<Product> products) {
        this.cartId = cartId;
        this.user = user;
        this.products = products;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
