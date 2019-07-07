package ro.utcn.fulgadan.BookStore.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.fulgadan.BookStore.data.entity.ShoppingCart;
import ro.utcn.fulgadan.BookStore.data.entity.User;

@Repository
public interface CartJpaRepository extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findByUser(User user);
}
