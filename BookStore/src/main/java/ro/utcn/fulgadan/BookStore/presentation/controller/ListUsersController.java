package ro.utcn.fulgadan.BookStore.presentation.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ro.utcn.fulgadan.BookStore.business.CustomerService;
import ro.utcn.fulgadan.BookStore.data.entity.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ListUsersController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TableView<Customer> tableView;


    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> username;

    @FXML
    private TableColumn<Customer, Integer> age;

    @FXML
    private TableColumn<Customer, String> address;

    @FXML
    private TableColumn<Customer, String> phone;

    @Autowired
    ApplicationContext context;

    @Autowired
    CustomerService customerService;

    public void handleButtonBack(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminView.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private ObservableList<Customer> getCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        customers.addAll(customerService.findAll());
        return customers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        username.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getUser().getUsername());
            }
        });
        age.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Customer, Integer> param) {
                return new SimpleObjectProperty<>(param.getValue().getInfo().getAge());
            }
        });
        address.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getInfo().getAddress());
            }
        });
        phone.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getInfo().getPhone());
            }
        });
        tableView.setItems(getCustomers());
    }
}
