package ro.utcn.fulgadan.BookStore.presentation.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ro.utcn.fulgadan.BookStore.business.ProductService;
import ro.utcn.fulgadan.BookStore.data.entity.Product;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class AdminViewController {

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private TextField price;

    @FXML
    private Button image;

    @FXML
    private Button newBook;

    @FXML
    private Button back;

    @FXML
    private Button users;

    @FXML
    private Button orders;

    @FXML
    private Label error;

    @Autowired
    ApplicationContext context;

    @Autowired
    ProductService productService;

    private Stage stage;
    private String encodedImage = null;

    public void handleButtonInsertImage(javafx.event.ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG images (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(this.stage);
        System.out.println(file);
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            BASE64Encoder base = new BASE64Encoder();
            encodedImage = base.encode(baos.toByteArray());
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleNewBook(javafx.event.ActionEvent event) {
        if (title.getText().equals("") || description.getText().equals("") || price.getText().equals(""))
            error.setText("All field must be completed!");
        else {
            Product product = new Product(0, title.getText(), Float.valueOf(price.getText()), encodedImage, description.getText());
            productService.insertProduct(product);
            error.setText("Product successfully added!");
        }
    }

    public void handleBack(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void handleUsers(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ListUsers.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void handleOrders(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ListOrders.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2, 600, 400);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
