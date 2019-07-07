package ro.utcn.fulgadan.BookStore.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.fulgadan.BookStore.data.entity.Customer;
import ro.utcn.fulgadan.BookStore.data.entity.User;
import ro.utcn.fulgadan.BookStore.data.repository.CustomerJpaRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerJpaRepository customerJpaRepository;

    public void insertCustomer(Customer customer) {
        customerJpaRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerJpaRepository.findAll();
    }

    public Customer findByUser(User user) {
        return  customerJpaRepository.findByUser(user);
    }
}
