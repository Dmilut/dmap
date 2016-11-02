package ru.dz.vita2d.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXRippler;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Component
public class MainController extends AbstractController {
	private static final String JFOENIX_MAIN_DEMO_CSS = "/ru/dz/vita2d/css/jfoenix-main-demo.css";
	private static final String JFOENIX_DESIGN_CSS = "/ru/dz/vita2d/css/jfoenix-design.css";
	private static final String JFOENIX_FONTS_CSS = "/ru/dz/vita2d/css/jfoenix-fonts.css";
	private static final int MINWIDTH = 1024;
	private static final int MINHEIGHT = 768;
	private static final String SIDEMENULOCATION = "/ru/dz/vita2d/views/sideMenu.fxml";
	private static final String CONTENTLOCATION = "/ru/dz/vita2d/views/content.fxml";
	private static final String ICOMOONFONTLOCATION = "/ru/dz/vita2dfonts/icomoon.svg";
	private Stage stage;
	private Scene scene;
	private StackPane stackPane = null;
	private AnchorPane contentArea = null;

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@FXML
	private AnchorPane root;
	@FXML
	private StackPane menu;
	@FXML
	private StackPane titleBurgerContainer;
	@FXML
	private JFXHamburger titleBurger;
	@FXML
	private StackPane optionsBurger;
	@FXML
	private JFXRippler optionsRippler;
	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXPopup toolbarPopup;
	@FXML
	private Label exit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Platform.runLater(() -> {
			scene = root.getScene();
			scene.getStylesheets().add(JFOENIX_MAIN_DEMO_CSS);
			scene.getStylesheets().add(JFOENIX_DESIGN_CSS);
			scene.getStylesheets().add(JFOENIX_FONTS_CSS);

			stage = (Stage) root.getScene().getWindow();
			stage.setMinWidth(MINWIDTH);
			stage.setMinHeight(MINHEIGHT);
		});

		initSideMenu();
		initPopup();
		initContentArea();

		drawer.setOnDrawerOpening((handler) -> {
			titleBurger.getAnimation().setRate(1);
			titleBurger.getAnimation().play();
		});

		drawer.setOnDrawerClosing((handler) -> {
			titleBurger.getAnimation().setRate(-1);
			titleBurger.getAnimation().play();
		});

		titleBurgerContainer.setOnMouseClicked((handler) -> {
			if (drawer.isHidden() || drawer.isHidding())
				drawer.open();
			else
				drawer.close();
		});

		optionsBurger.setOnMouseClicked((handler) -> {
			toolbarPopup.show(PopupVPosition.TOP, PopupHPosition.RIGHT, -12, 15);
		});

		// close application
		exit.setOnMouseClicked((handler) -> {
			Platform.exit();
		});

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	private void initContentArea() {
		try {
			contentArea = FXMLLoader.load(getClass().getResource(CONTENTLOCATION));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		root.getChildren().add(contentArea);

	}

	private void initPopup() {
		toolbarPopup.setPopupContainer(menu);
		toolbarPopup.setSource(optionsRippler);
		menu.getChildren().remove(toolbarPopup);
	}

	private void initSideMenu() {
		try {
			stackPane = FXMLLoader.load(getClass().getResource(SIDEMENULOCATION));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		drawer.setSidePane(stackPane);
	}

}
