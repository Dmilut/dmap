package ru.dz.vita2d.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

@Component
public class ContentController extends AbstractController {
	@FXML
	private AnchorPane contentArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {

			contentArea.setBackground(
					new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			contentArea.toBack();
		});

	}

}
