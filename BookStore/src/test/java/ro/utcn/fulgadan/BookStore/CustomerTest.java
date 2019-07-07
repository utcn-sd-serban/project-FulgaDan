package ro.utcn.fulgadan.BookStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.utcn.fulgadan.BookStore.business.CustomerService;
import ro.utcn.fulgadan.BookStore.data.entity.Customer;
import ro.utcn.fulgadan.BookStore.data.entity.ShippingInfo;
import ro.utcn.fulgadan.BookStore.data.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class CustomerTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    CustomerService customerService;

    @Test
    public void shouldFindByUser() {
        User user = new User(15, "fulgadan", "1234", 1);
        ShippingInfo info = new ShippingInfo(14, "Crangului 16", 22, "0721769057");
        Customer customer = new Customer.CustomerBuilder().setCustomerId(5).setName("Dan Fulga").setUser(user).setInfo(info).build();

        when(customerService.findByUser(user)).thenReturn(customer);
        assertTrue(customerService.findByUser(user).equals(customer));
    }

    @Test
    public void shouldListAllCustomers() {
        User user = new User(15, "fulgadan", "1234", 1);
        ShippingInfo info = new ShippingInfo(14, "Crangului 16", 22, "0721769057");
        Customer customer = new Customer.CustomerBuilder().setCustomerId(5).setName("Dan Fulga").setUser(user).setInfo(info).build();

        User user2 = new User(13, "dan", "5678", 1);
        ShippingInfo info2 = new ShippingInfo(12, "21, caminul 7", 22, "0789456123");
        Customer customer2 = new Customer.CustomerBuilder().setCustomerId(3).setName("Dan").setUser(user2).setInfo(info2).build();

        User user3 = new User(16, "danfulga", "9012", 1);
        ShippingInfo info3 = new ShippingInfo(15, "Strada Lalelelor", 26, "0789123123");
        Customer customer3 = new Customer.CustomerBuilder().setCustomerId(6).setName("Fulga").setUser(user3).setInfo(info3).build();

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer2);
        customers.add(customer3);

        when(customerService.findAll()).thenReturn(customers);
        assertTrue(customerService.findAll().equals(customers));
    }

}
