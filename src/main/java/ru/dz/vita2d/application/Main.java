package ru.dz.vita2d.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.dz.vita2d.ui.controller.MainController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {
	private static final String MAINLOCATION = "/ru/dz/vita2d/views/main.fxml";
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		try {
			launch(args);
		} catch (Exception x) {
			LOGGER.error(x.getMessage(), x);
			throw x;
		}
	}

	@Override
	public void start(Stage primaryStage) {
		Thread.setDefaultUncaughtExceptionHandler((t, x) -> LOGGER.error(x.getMessage(), x));

		MainController controller = (MainController) SpringFXMLLoader.load(MAINLOCATION);
		Scene scene = new Scene((Parent) controller.getView(), 1000, 750);
		//primaryStage.setTitle(SpringFXMLLoader.APPLICATION_CONTEXT.getEnvironment().getProperty("application.name"));
		primaryStage.setScene(scene);
		primaryStage.show();
		controller.setStage(primaryStage);
		

		/*Parent root = FXMLLoader.load(getClass().getResource(SPLASHLOCATION));
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();*/
	}

}