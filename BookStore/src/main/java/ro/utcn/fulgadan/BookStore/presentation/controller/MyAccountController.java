package ro.utcn.fulgadan.BookStore.presentation.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ro.utcn.fulgadan.BookStore.business.CustomerService;
import ro.utcn.fulgadan.BookStore.business.ShippingService;
import ro.utcn.fulgadan.BookStore.data.entity.Customer;
import ro.utcn.fulgadan.BookStore.data.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MyAccountController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private Button update;

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField phone;

    @FXML
    private Text message;

    @Autowired
    ApplicationContext context;

    @Autowired
    CustomerService customerService;

    @Autowired
    ShippingService shippingService;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateBackButton(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UserView.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2, 700, 450);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void updateUpdateButton(javafx.event.ActionEvent event) {
        shippingService.updateShippingInfo(customerService.findByUser(user).getInfo(), address.getText(), phone.getText(), Integer.valueOf(age.getText()));
        message.setText("Update successfully made!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Customer customer = customerService.findByUser(user);
        message.setText("Welcome back, " + customer.getName() + " !");
        address.setText(customer.getInfo().getAddress());
        phone.setText(customer.getInfo().getPhone());
        age.setText(customer.getInfo().getAge().toString());
    }
}
