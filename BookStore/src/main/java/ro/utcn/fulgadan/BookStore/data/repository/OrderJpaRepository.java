package ro.utcn.fulgadan.BookStore.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.fulgadan.BookStore.data.entity.Order;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Integer> {
    List<Order> findAll();
}
