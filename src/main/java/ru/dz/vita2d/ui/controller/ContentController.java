package ru.dz.vita2d.ui.controller;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

@Component
public class ContentController extends AbstractController {
	private static final String IMAGE_URL = "http://www.nasa.gov/sites/default/files/thumbnails/image/global-mosaic-of-pluto-in-true-color.jpg";
	private static final int MIN_PIXELS = 200;

	@FXML
	private AnchorPane contentArea;

	ImageView imageView = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			contentArea.toBack();
		
			imageView.setOnMouseClicked((event) -> {
				System.out.println("Click!!!");
			});

			ObservableList<Node> nodes1 = contentArea.getChildren();
			nodes1.forEach(
					node -> System.out.println("Node name= " + node.getClass().getName() + " id= " + node.getId()));
			Parent parent = contentArea.getParent();
			System.out.println("Parent name= " + parent.getClass().getName());
			parent.getChildrenUnmodifiable().forEach(
					node -> System.out.println("Children= " + node.getClass().getName() + " id= " + node.getId()));

		});

		Image image = new Image(IMAGE_URL);
		double width = image.getWidth();
		double height = image.getHeight();

		imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		reset(imageView, width / 2, height / 2);

		ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();

		imageView.setOnMousePressed(e -> {
			Point2D mousePress = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));
			mouseDown.set(mousePress);
		});

	/*	imageView.setOnMouseDragged(e -> {
			Point2D dragPoint = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));
			shift(imageView, dragPoint.subtract(mouseDown.get()));
			mouseDown.set(imageViewToImage(imageView, new Point2D(e.getX(), e.getY())));
		});*/

		imageView.setOnScroll(e -> {
			double delta = e.getDeltaY();
			Rectangle2D viewport = imageView.getViewport();

			double scale = clamp(Math.pow(1.01, delta),
					Math.min(MIN_PIXELS / viewport.getWidth(), MIN_PIXELS / viewport.getHeight()),
					Math.max(width / viewport.getWidth(), height / viewport.getHeight())
			);

			Point2D mouse = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));

			double newWidth = viewport.getWidth() * scale;
			double newHeight = viewport.getHeight() * scale;
			double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale, 0, width - newWidth);
			double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale, 0, height - newHeight);

			imageView.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
		});

/*		imageView.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				reset(imageView, width, height);
			}
		});*/

		imageView.fitWidthProperty().bind(contentArea.widthProperty());
		imageView.fitHeightProperty().bind(contentArea.heightProperty());

		contentArea.getChildren().add(imageView);
		// container.getChildren().add(imageView);
		// contentArea.requestFocus();

	}

	// reset to the top left:
	private void reset(ImageView imageView, double width, double height) {
		imageView.setViewport(new Rectangle2D(0, 0, width, height));
	}

	// shift the viewport of the imageView by the specified delta, clamping so
	// the viewport does not move off the actual image:
/*	private void shift(ImageView imageView, Point2D delta) {
		Rectangle2D viewport = imageView.getViewport();

		double width = imageView.getImage().getWidth();
		double height = imageView.getImage().getHeight();

		double maxX = width - viewport.getWidth();
		double maxY = height - viewport.getHeight();

		double minX = clamp(viewport.getMinX() - delta.getX(), 0, maxX);
		double minY = clamp(viewport.getMinY() - delta.getY(), 0, maxY);

		imageView.setViewport(new Rectangle2D(minX, minY, viewport.getWidth(), viewport.getHeight()));
	}*/

	private double clamp(double value, double min, double max) {
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;
	}

	private Point2D imageViewToImage(ImageView imageView, Point2D imageViewCoordinates) {
		double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInLocal().getWidth();
		double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInLocal().getHeight();

		Rectangle2D viewport = imageView.getViewport();
		return new Point2D(viewport.getMinX() + xProportion * viewport.getWidth(),
				viewport.getMinY() + yProportion * viewport.getHeight());
	}
}
