package ru.dz.vita2d.init;

import java.io.IOException;

import com.jfoenix.controls.JFXDecorator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author dmilut
 */

public class StageInitializer {
	private static final String JFOENIX_MAIN_DEMO_CSS = "/ru/dz/vita2d/css/jfoenix-main-demo.css";
	private static final String JFOENIX_DESIGN_CSS = "/ru/dz/vita2d/css/jfoenix-design.css";
	private static final String JFOENIX_FONTS_CSS = "/ru/dz/vita2d/css/jfoenix-fonts.css";

	private static volatile StageInitializer instance;

	public static StageInitializer getInstance() {
		StageInitializer localInstance = instance;

		if (localInstance == null) {
			synchronized (StageInitializer.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new StageInitializer();
				}
			}
		}
		return localInstance;
	}

	public void initStage(Label label, String location, String title, boolean isResize, StageStyle style,
			boolean isMaximized) {

		Stage previusStage = (Stage) label.getScene().getWindow();
		Parent root = null;

		try {
			root = FXMLLoader.load(getClass().getResource(location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Stage newStage = new Stage();
		newStage.setTitle(title);
		newStage.setResizable(isResize);
		Scene scene;
		if (style.equals(StageStyle.DECORATED)) {
			
			JFXDecorator decorator = new JFXDecorator(newStage, root);
			scene = new Scene(decorator);
			newStage.initStyle(StageStyle.UNDECORATED); // TODO

			scene.getStylesheets().add(JFOENIX_MAIN_DEMO_CSS);
			scene.getStylesheets().add(JFOENIX_DESIGN_CSS);
			scene.getStylesheets().add(JFOENIX_FONTS_CSS);

		} else {
			scene = new Scene(root);
			newStage.initStyle(style);
		}
		// newStage.initStyle(style);
		newStage.setMaximized(isMaximized);

		newStage.setScene(scene);
		newStage.show();

		previusStage.close();
	}

	public static void dialog(Alert.AlertType alertType, String s) {
		Alert alert = new Alert(alertType, s);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Info");
		alert.showAndWait();
	}

	public void loadAnchorPane(AnchorPane ap, String a) {
		try {
			AnchorPane p = FXMLLoader.load(getClass().getResource("/com/dmilut/views/" + a));
			ap.getChildren().setAll(p);
		} catch (IOException e) {
		}
	}
}