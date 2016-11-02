package ru.dz.vita2d.application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import ru.dz.vita2d.init.AppConfig;
import ru.dz.vita2d.ui.controller.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

/**
 * @author dmilut
 * created on Nov 2, 2016
 */

public class SpringFXMLLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringFXMLLoader.class);
    static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);

    public static Controller load(String url) {
        try (InputStream fxmlStream = SpringFXMLLoader.class.getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            //loader.setResources(ResourceBundle.getBundle("i18n.messages"));
            loader.setControllerFactory(APPLICATION_CONTEXT::getBean);

            Node view = loader.load(fxmlStream);
            Controller controller = loader.getController();
            controller.setView(view);

            return controller;
        } catch (IOException e) {
            LOGGER.error("Can't load resource", e);
            throw new RuntimeException(e);
        }
    }
}
