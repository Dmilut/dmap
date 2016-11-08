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

import com.jfoenix.controls.JFXDecorator;

public class Main extends Application {
	private static final String MAINLOCATION = "/ru/dz/vita2d/views/main.fxml";
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		try {
			launch(args);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public void start(Stage primaryStage) {
		Thread.setDefaultUncaughtExceptionHandler((t, x) -> LOGGER.error(x.getMessage(), x));

		MainController controller = (MainController) SpringFXMLLoader.load(MAINLOCATION);
		//Scene scene = new Scene((Parent) controller.getView(), 300, 750);
		//primaryStage.setTitle(SpringFXMLLoader.APPLICATION_CONTEXT.getEnvironment().getProperty("application.name"));
	
		JFXDecorator decorator = new JFXDecorator(primaryStage, (Parent) controller.getView());
		primaryStage.initStyle(StageStyle.UNDECORATED); // TODO
		
		primaryStage.setScene(new Scene(decorator));
		primaryStage.show();
		controller.setStage(primaryStage);
	}

}