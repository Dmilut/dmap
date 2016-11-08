package ru.dz.vita2d.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.jfoenix.controls.JFXListView;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

@Component
public class SideMenuController extends AbstractController {
	@FXML
	private StackPane sideMenu;	
	@FXML
	private Label button1;
	@FXML
	private Label button2;
	@FXML
	private Label button3;
	@FXML
	private JFXListView<?> sideList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

}
