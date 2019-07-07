package ro.utcn.fulgadan.BookStore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BookStoreApplication extends Application {

	private ConfigurableApplicationContext springContext;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		springContext = SpringApplication.run(BookStoreApplication.class);

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		Parent root = fxmlLoader.load();
		primaryStage.setTitle("Main page");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}
}
