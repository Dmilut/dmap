package ru.dz.vita2d.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import ru.dz.vita2d.init.SpringContextInitializer;
import ru.dz.vita2d.init.StageInitializer;
import ru.dz.vita2d.ui.animation.FadeInLeftTransition;
import ru.dz.vita2d.ui.animation.FadeInRightTransition;
import ru.dz.vita2d.ui.animation.FadeInTransition;

/**
 * @author dmilut
 */

@Controller
public class SplashController implements Initializable {

	private final String STAGELOCATION = "/ru/dz/vita2d/views/login.fxml";
	private final String STAGETITLE = "Sample App";
	private final boolean ISRESIZE = true;
	private final boolean ISMAXIMIZED = false;
	StageInitializer stageInitializer = StageInitializer.getInstance();

	@FXML
	private Text labelWelcome;
	@FXML
	private Text labelRudy;
	@FXML
	private VBox vboxBottom;
	@FXML
	private Label labelClose;
	@FXML
	private ImageView imgLoading;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		startApplication();

		labelClose.setOnMouseClicked((MouseEvent event) -> {
			Platform.exit();
			System.exit(0);
		});
	}

	private void startApplication() {
		Service<ApplicationContext> service = initApplicationContext();

		service.start();

		service.setOnRunning((WorkerStateEvent event) -> {
			new FadeInLeftTransition(labelWelcome).play();
			new FadeInRightTransition(labelRudy).play();
			new FadeInTransition(vboxBottom).play();
		});

		service.setOnSucceeded((WorkerStateEvent event) -> {

			stageInitializer.initStage(labelClose, STAGELOCATION, STAGETITLE, ISRESIZE, StageStyle.UNDECORATED,
					ISMAXIMIZED);

		});
	}

	private Service<ApplicationContext> initApplicationContext() {
		Service<ApplicationContext> service = new Service<ApplicationContext>() {
			@Override
			protected Task<ApplicationContext> createTask() {
				return new Task<ApplicationContext>() {
					@Override
					protected ApplicationContext call() throws Exception {
						ApplicationContext appContex = SpringContextInitializer.getInstance().getApplicationContext();
						int max = appContex.getBeanDefinitionCount();
						updateProgress(0, max);
						for (int k = 0; k < max; k++) {
							Thread.sleep(50);
							updateProgress(k + 1, max);
						}
						return appContex;
					}
				};
			}
		};

		return service;
	}

}
