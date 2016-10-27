package ru.dz.vita2d.ui.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.dz.vita2d.application.AbstractMapScene;
import ru.dz.vita2d.application.BackgroundScene;
import ru.dz.vita2d.application.Defs;
import ru.dz.vita2d.application.MapScene;
import ru.dz.vita2d.data.IRestCaller;
import ru.dz.vita2d.data.RestCaller;
import ru.dz.vita2d.data.ServerCache;
import ru.dz.vita2d.data.store.LocalFileStorage;
import ru.dz.vita2d.init.StageInitializer;
import ru.dz.vita2d.maps.MapList;
import ru.dz.vita2d.media.Player;
import ru.dz.vita2d.ui.LoginFormWindow;
import ru.dz.vita2d.ui.animation.FadeInLeftTransition;
import ru.dz.vita2d.ui.animation.FadeInLeftTransition1;
import ru.dz.vita2d.ui.animation.FadeInRightTransition;

/**
 * @author dmilut
 */

@Controller
public class LoginController implements Initializable {
	private Stage primaryStage;
	public IRestCaller rc;
	public ServerCache sc;
	public MapList ml;
	public Properties property = new Properties();
	public LocalFileStorage store = new LocalFileStorage();

	private final String STAGELOCATION = "/ru/dz/vita2d/views/main.fxml";
	private final String STAGETITLE = "Sample App";
	private final boolean ISRESIZE = true;
	private final boolean ISMAXIMIZED = false;
	StageInitializer stageInitializer = StageInitializer.getInstance();

	@FXML
	private TextField textUsername;
	@FXML
	private PasswordField textPassword;
	@FXML
	private Text labelWelcome;
	@FXML
	private Text labelUserLogin;
	@FXML
	private Text labelUsername;
	@FXML
	private Text labelPassword;
	@FXML
	private Button buttonLogin;
	@FXML
	private Text labelCompanyName;
	@FXML
	private Label labelClose;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			new FadeInLeftTransition(labelWelcome).play();
			new FadeInRightTransition(labelUserLogin).play();
			new FadeInLeftTransition1(labelUsername).play();
			new FadeInLeftTransition1(labelPassword).play();
			new FadeInLeftTransition1(textUsername).play();
			new FadeInLeftTransition1(textPassword).play();
			new FadeInRightTransition(buttonLogin).play();

			labelClose.setOnMouseClicked((MouseEvent event) -> {
				Platform.exit();
				System.exit(0);
			});
		});
		// TODO
	}

	@FXML
	private void login(ActionEvent event) {
		if (textUsername.getText().equals("1") && textPassword.getText().equals("1")) {
			
			stageInitializer.initStage(labelClose, STAGELOCATION, STAGETITLE, ISRESIZE, StageStyle.DECORATED,
					ISMAXIMIZED);

		} else {
			StageInitializer.dialog(Alert.AlertType.ERROR, "Error Login, Please Chek Username And Password");
		}
	}

	/*
	 * public void init() throws Exception { //super.init();
	 * 
	 * InputStream propStream =
	 * getClass().getResourceAsStream(Defs.PROPERTIES_URL); InputStreamReader
	 * psr = new InputStreamReader(propStream, "UTF-8"); if (propStream != null)
	 * property.load(psr);
	 * 
	 * rc = new RestCaller(property.getProperty(Defs.PROP_HOST,
	 * Defs.HOST_NAME)); // rc = new CacheRestCaller( new //
	 * RestCaller(property.getProperty(Defs.PROP_HOST, Defs.HOST_NAME) ) ); sc =
	 * new ServerCache(rc);
	 * 
	 * // Load data from local file with list of maps ml = new MapList();
	 * 
	 * }
	 * 
	 * 
	 * public void start(Stage primaryStage) { this.primaryStage = primaryStage;
	 * 
	 * if (Defs.FULL_SCREEN) { //
	 * primaryStage.initStyle(StageStyle.UNDECORATED);
	 * primaryStage.setMaximized(true); primaryStage.setResizable(false); }
	 * 
	 * // primaryStage.initStyle(StageStyle.DECORATED); logout(); }
	 * 
	 * LoginFormWindow lw;
	 * 
	 * public void logout() { // LoginScene ls = // new LoginScene(rc,
	 * primaryStage, this ); new BackgroundScene(primaryStage);
	 * 
	 * // do { lw = new LoginFormWindow(rc, login -> afterLogin()); // }
	 * while(!lw.isLoggedIn());
	 * 
	 * }
	 * 
	 * public void afterLogin() { // Resets Scene lw.close(); //
	 * AbstractMapScene ms = new MapScene(primaryStage, this); //
	 * ms.setMapData(ml.getRootMap());
	 * 
	 * if (!Defs.FULL_SCREEN) primaryStage.centerOnScreen();
	 * 
	 * // primaryStage.setMaximized(true); // primaryStage.setMaximized(true);
	 * Player.bell(); }
	 * 
	 * 
	 * public void requestShutdown() { // System.
	 * 
	 * try { Alert alert = new Alert(AlertType.CONFIRMATION);
	 * alert.setTitle("Выключение системы");
	 * alert.setHeaderText("ОРВД Планшет Инженера");
	 * 
	 * String s = "Действитено выключить планшет?";
	 * 
	 * alert.setContentText(s); Optional<ButtonType> bt = alert.showAndWait();
	 * 
	 * if (alert.getResult() == ButtonType.YES) {
	 * Runtime.getRuntime().exec("shutdown -s -f"); }
	 * 
	 * } catch (Throwable e) { e.printStackTrace(); }
	 * 
	 * Platform.exit(); }
	 */

}
