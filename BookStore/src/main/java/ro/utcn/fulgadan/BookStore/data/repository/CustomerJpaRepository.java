package ro.utcn.fulgadan.BookStore.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.fulgadan.BookStore.data.entity.Customer;
import ro.utcn.fulgadan.BookStore.data.entity.User;

import java.util.List;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAll();
    Customer findByUser(User user);
}
