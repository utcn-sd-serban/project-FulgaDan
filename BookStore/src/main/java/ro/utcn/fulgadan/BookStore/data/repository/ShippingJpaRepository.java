package ro.utcn.fulgadan.BookStore.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.fulgadan.BookStore.data.entity.ShippingInfo;

@Repository
public interface ShippingJpaRepository extends JpaRepository<ShippingInfo, Integer> {
    ShippingInfo findByPhone(String phone);
}
