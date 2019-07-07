package ro.utcn.fulgadan.BookStore.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.fulgadan.BookStore.data.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
