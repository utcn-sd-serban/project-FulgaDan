package ro.utcn.fulgadan.BookStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.utcn.fulgadan.BookStore.business.ShippingService;
import ro.utcn.fulgadan.BookStore.data.entity.ShippingInfo;
import ro.utcn.fulgadan.BookStore.data.entity.User;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class ShippingTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    ShippingService shippingService;

    @Test
    public void shouldFindByPhone1() {
        User user = new User(15, "fulgadan", "1234", 1);
        ShippingInfo info = new ShippingInfo(14, "Crangului 16", 22, "0721769057");

        when(shippingService.findByPhone("0721769057")).thenReturn(info);
        assertTrue(shippingService.findByPhone("0721769057").equals(info));
    }

    @Test
    public void shouldFindByPhone2() {
        User user2 = new User(13, "dan", "5678", 1);
        ShippingInfo info2 = new ShippingInfo(12, "21, caminul 7", 22, "0789456123");

        when(shippingService.findByPhone("0789456123")).thenReturn(info2);
        assertTrue(shippingService.findByPhone("0789456123").equals(info2));
    }

    @Test
    public void shouldCheckAge() {
        ShippingInfo info2 = new ShippingInfo(12, "21, caminul 7", 22, "0789456123");

        when(shippingService.checkAge(info2)).thenReturn(true);
        assertTrue(shippingService.checkAge(info2));
    }
}
