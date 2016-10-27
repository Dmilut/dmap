package ru.dz.vita2d.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.svg.SVGGlyphLoader;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class MainController implements Initializable {
	private static final String FONTS_ICOMOON_SVG = "/ru/dz/vita2dfonts/icomoon.svg";

	private Stage stage;

	@FXML
	private StackPane root;
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
			stage = (Stage) root.getScene().getWindow();
			stage.setMinWidth(800);
			stage.setMinHeight(600);

			//scene = stage.getScene();

		});
		// init the title hamburger icon
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

		// init Popup
		toolbarPopup.setPopupContainer(root);
		toolbarPopup.setSource(optionsRippler);
		root.getChildren().remove(toolbarPopup);

		optionsBurger.setOnMouseClicked((handler) -> {
			toolbarPopup.show(PopupVPosition.TOP, PopupHPosition.RIGHT, -12, 15);
		});

		// close application
		exit.setOnMouseClicked((handler) -> {
			Platform.exit();
		});
		// });
	}

	private void loadSVG() {
		new Thread(() -> {
			try {
				SVGGlyphLoader.loadGlyphsFont(MainController.class.getResourceAsStream(FONTS_ICOMOON_SVG),
						"icomoon.svg");
				System.out.println("loadGlyphsFont true !!!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block

				System.out.println("loadGlyphsFont false !!!!");
				e.printStackTrace();
			}
		}).start();
	}

}
