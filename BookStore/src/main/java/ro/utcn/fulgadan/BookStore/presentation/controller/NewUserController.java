package ro.utcn.fulgadan.BookStore.presentation.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ro.utcn.fulgadan.BookStore.business.CartService;
import ro.utcn.fulgadan.BookStore.business.CustomerService;
import ro.utcn.fulgadan.BookStore.business.ShippingService;
import ro.utcn.fulgadan.BookStore.business.UserService;
import ro.utcn.fulgadan.BookStore.data.entity.Customer;
import ro.utcn.fulgadan.BookStore.data.entity.ShippingInfo;
import ro.utcn.fulgadan.BookStore.data.entity.ShoppingCart;
import ro.utcn.fulgadan.BookStore.data.entity.User;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class NewUserController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField phone;

    @FXML
    private Button register;

    @FXML
    private Button back;

    @FXML
    private Label error;

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ShippingService shippingService;

    @Autowired
    CartService cartService;

    @Autowired
    ApplicationContext context;

    public void handleButtonBack(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void handleButtonRegister(javafx.event.ActionEvent event) {
        if (!userService.checkUsername(username.getText())) {
            error.setText("Username already taken!");
            username.clear();
        }
        else {
            User user = new User(0, username.getText(), password.getText(), 1);
            ShippingInfo shippingInfo = new ShippingInfo(0, address.getText(), Integer.valueOf(age.getText()), phone.getText());

            if (!shippingService.checkAge(shippingInfo)) {
                error.setText("Age must be greater than 12!");
                age.clear();
            }
            else
            if (!shippingService.checkPhone(shippingInfo)) {
                error.setText("Phone number must have 10 digits!");
                phone.clear();
            }
            else {
                userService.insertUser(user);
                shippingService.addNewShipping(shippingInfo);
                Customer customer = new Customer.CustomerBuilder().setCustomerId(0).setName(name.getText()).setInfo(shippingService.findByPhone(phone.getText())).setUser(userService.findByUsername(username.getText())).build();
                customerService.insertCustomer(customer);

                ShoppingCart cart = new ShoppingCart(0, userService.findByUsername(username.getText()), new ArrayList<>());
                cartService.insertCart(cart);
                error.setText("Account successfully created!");
            }
        }
    }
}
