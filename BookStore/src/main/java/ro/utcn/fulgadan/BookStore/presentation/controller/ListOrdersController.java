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
import ro.utcn.fulgadan.BookStore.business.OrderService;
import ro.utcn.fulgadan.BookStore.data.entity.Order;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ListOrdersController implements Initializable {


    @FXML
    private Button back;

    @FXML
    private TableView<Order> tableView;

    @FXML
    private TableColumn<Order, String> id;

    @FXML
    private TableColumn<Order, String> name;

    @FXML
    private TableColumn<Order, Integer> price;

    @Autowired
    ApplicationContext context;

    @Autowired
    OrderService orderService;

    public void handleButtonBack(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminView.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private ObservableList<Order> getOrders() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        orders.addAll(orderService.findAll());
        return orders;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> param) {
                return new SimpleObjectProperty<>(param.getValue().getCustomer().getName());
            }
        });
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView.setItems(getOrders());
    }
}
