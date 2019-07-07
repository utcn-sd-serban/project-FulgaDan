package ro.utcn.fulgadan.BookStore.presentation.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ro.utcn.fulgadan.BookStore.business.CartService;
import ro.utcn.fulgadan.BookStore.business.CustomerService;
import ro.utcn.fulgadan.BookStore.business.OrderService;
import ro.utcn.fulgadan.BookStore.data.entity.Order;
import ro.utcn.fulgadan.BookStore.data.entity.Product;
import ro.utcn.fulgadan.BookStore.data.entity.ShoppingCart;
import ro.utcn.fulgadan.BookStore.data.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ShoppingCartController implements Initializable {

    @FXML
    private Button order;

    @FXML
    private Button back;

    @FXML
    private ListView<String> listView;

    @FXML
    private Label label;

    @FXML
    private Label price;

    @Autowired
    ApplicationContext context;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void handleButtonOrder(javafx.event.ActionEvent event) {
        ShoppingCart toBuy = cartService.findCartByUser(user);
        float total = 0;
        for (Product p: toBuy.getProducts())
            total += p.getPrice();
        Order order = new Order(0, customerService.findByUser(user), toBuy.getProducts(), total);
        orderService.insertOrder(order);
        label.setText("Order successfully made!");
        price.setText("Total: 0 lei");
        cartService.emptyCart(toBuy);
        listView.getItems().clear();
    }

    public void handleButtonBack(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UserView.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2, 700, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ShoppingCart cart = cartService.findCartByUser(user);
        List<Product> products = cart.getProducts();
        List<String> toShow = new ArrayList<>();
        String spaces;

        float total = 0;
        for (Product p: products) {
            total += p.getPrice();
            int nrOfSpaces = 80 - p.getName().length();
            String space = new String(new char[nrOfSpaces]).replace('\0', ' ');
            toShow.add(p.getName() + space + p.getPrice());
        }
        price.setText("Total: " + total + " lei");
        label.setText("You have " + products.size() + " item(s) in the cart!");

        listView.getItems().addAll(toShow);
    }
}
