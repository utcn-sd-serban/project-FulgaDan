package ro.utcn.fulgadan.BookStore.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.fulgadan.BookStore.data.entity.Product;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();
    Product findByName(String title);
}
