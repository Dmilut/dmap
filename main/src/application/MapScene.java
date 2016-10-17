package application;

import java.io.IOException;

import org.json.JSONObject;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.dz.vita2d.maps.DeviceMapData;
import ru.dz.vita2d.maps.IMapData;
import ru.dz.vita2d.maps.IndoorMapData;
import ru.dz.vita2d.maps.MapOverlay;
import ru.dz.vita2d.maps.OutoorMapData;

public class MapScene {

	
	// --------------------------------------------------
	// To kill
	// --------------------------------------------------
	
	OutoorMapData bigMapData = new OutoorMapData("map.png", "����� �������");
	OutoorMapData mainMapData = new OutoorMapData("orl_a_map.png", "����� ���-�");
	IndoorMapData mainPlanData = new IndoorMapData("plan.png", "���� ������");
	DeviceMapData deviceData = new DeviceMapData("device_00.png", "�����"); 

	{
		bigMapData.addOverlay( "orl-a-icon.png", 926, 1205, mainMapData );
	}

	// --------------------------------------------------
	
	
	
	
	private static final int MIN_PIXELS = 10;

	private Stage primaryStage;
	private Main main;

	private Scene scene;

	private IMapData mData = bigMapData;
	
	private ImageView imageView;
	private double width, height; 
	
	
	public MapScene( Stage primaryStage, Main main ) {
		this.primaryStage = primaryStage;
		this.main = main;		
	}
	

	
	
	


	
	

	private void setOverviewScale() 
	{
		reset(imageView, width, height);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private HBox createButtons(double width, double height, ImageView imageView) 
	{
		Button reset = new Button("����� ��������");
		reset.setOnAction(e -> reset(imageView, width / 2, height / 2));

		Button full = new Button("�����");
		full.setOnAction(e -> setOverviewScale());

		Button m0 = new Button("����� �����");
		m0.setOnAction(e -> setMapData(bigMapData));        

		Button m1 = new Button("����� �������");
		m1.setOnAction(e -> setMapData(mainMapData));

		Button m2 = new Button("��������");
		m2.setOnAction(e -> setMapData(mainPlanData) );

		Button m3 = new Button("�����");
		m3.setOnAction(e -> setMapData(deviceData) );

		Button mroot = new Button("Map list test");
		mroot.setOnAction(e -> setMapData(main.ml.getRootMap()) );

		//Button s1 = new Button("Beep");
		//s1.setOnAction(e -> { new AudioClip(getResource("click.wav").toString()).play(); } );

		Button r1 = new Button("Means test");
		r1.setOnAction(e -> {
			try {
				//RestCaller.dumpJson(fieldNames);

				JSONObject mr = main.rc.getMeansRecord( 2441372 );

				JSONObject entity = mr.getJSONObject("entity");

				JsonAsFlowDialog jd = new JsonAsFlowDialog( entity );
				//jd.setDataModel(sc.getFieldNamesMap());
				jd.setServerCache( main.sc );
				jd.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} );





		HBox buttons = new HBox(10, reset, full, m0, m1, m2, m3, r1, mroot);
		buttons.setAlignment(Pos.CENTER);
		buttons.setPadding(new Insets(10));
		return buttons;
	}


	// reset to the top left:
	private void reset(ImageView imageView, double width, double height) {
		imageView.setViewport(new Rectangle2D(0, 0, width, height));
	}

	
	
	
	
	
	
	
	
	private void fillMenu(MenuBar menuBar) {
		// File menu - new, save, exit
		Menu fileMenu = new Menu("����");

		MenuItem loginMenuItem = new MenuItem("������� ������������");
		loginMenuItem.setOnAction(actionEvent -> main.logout());

		MenuItem exitMenuItem = new MenuItem("����� (��������� �������)");
		exitMenuItem.setOnAction(actionEvent -> Platform.exit());
		exitMenuItem.setAccelerator(KeyCombination.keyCombination("Alt+F4"));


		fileMenu.getItems().addAll( loginMenuItem,
				new SeparatorMenuItem(), exitMenuItem);



		Menu navMenu = new Menu("���������");

		MenuItem navHomeMap = new MenuItem("�� ����� �����");
		navHomeMap.setOnAction(actionEvent -> setMapData(bigMapData));

		Menu navMaps = new Menu("�����");
		//navMaps.setOnAction(actionEvent -> setMapData(bigMapData));
		main.ml.fillMapsMenu( navMaps, this );

		MenuItem navOverview = new MenuItem("�����");
		navOverview.setOnAction(actionEvent -> setOverviewScale());

		navMenu.getItems().addAll( navHomeMap, navMaps, new SeparatorMenuItem(), navOverview );

		/*
	    Menu webMenu = new Menu("Web");
	    CheckMenuItem htmlMenuItem = new CheckMenuItem("HTML");
	    htmlMenuItem.setSelected(true);
	    webMenu.getItems().add(htmlMenuItem);

	    CheckMenuItem cssMenuItem = new CheckMenuItem("CSS");
	    cssMenuItem.setSelected(true);
	    webMenu.getItems().add(cssMenuItem);

	    Menu sqlMenu = new Menu("SQL");
	    ToggleGroup tGroup = new ToggleGroup();
	    RadioMenuItem mysqlItem = new RadioMenuItem("MySQL");
	    mysqlItem.setToggleGroup(tGroup);

	    RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
	    oracleItem.setToggleGroup(tGroup);
	    oracleItem.setSelected(true);

	    sqlMenu.getItems().addAll(mysqlItem, oracleItem,
	        new SeparatorMenuItem());

	    Menu tutorialManeu = new Menu("Tutorial");
	    tutorialManeu.getItems().addAll(
	        new CheckMenuItem("Java"),
	        new CheckMenuItem("JavaFX"),
	        new CheckMenuItem("Swing"));

	    sqlMenu.getItems().add(tutorialManeu);
		 */

		Menu aboutMenu = new Menu("� �������");

		MenuItem version = new MenuItem("������");
		version.setOnAction(actionEvent -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("������ �������");
			alert.setHeaderText("���� ������� ��������");

			String s =
					"������ ������������ ����������: 0.1\n"+
							"������ �������: "+main.rc.getServerVersion()+"\n"+
							"����� �������: "+main.getHostServices().getCodeBase()+"\n"+
							"������� ������: "+main.getHostServices().getDocumentBase()+"\n" +
							"������������: "+main.rc.getLoggedInUser()+"\n"
							;

			alert.setContentText(s);
			alert.show();
		});

		MenuItem aboutDz = new MenuItem("Digital Zone");
		aboutDz.setOnAction(actionEvent -> main.getHostServices().showDocument(Defs.HOME_URL));

		MenuItem aboutVita = new MenuItem("VitaSoft");
		aboutVita.setOnAction(actionEvent -> main.getHostServices().showDocument("vtsft.ru"));

		aboutMenu.getItems().addAll( version, new SeparatorMenuItem(), aboutDz, aboutVita );

		// --------------- Menu bar

		//menuBar.getMenus().addAll(fileMenu, webMenu, sqlMenu);
		menuBar.getMenus().addAll(fileMenu, navMenu, aboutMenu );
	}

	
	
	
	
	
	
	
	
	
	public void setMapData(IMapData mapData) 
	{
		mData = mapData;

		Image image = mData.getImage(); //new Image(IMAGE_URL);

		double width = mData.getImage().getWidth();
		double height = mData.getImage().getHeight();

		imageView = new ImageView( mData.putOverlays( image ) );
		imageView.setPreserveRatio(true);
		//reset(imageView, width / 2, height / 2);
		reset(imageView, width, height);
		/*
    	if( scene != null )
    		scene.getRoot().requestLayout(); // hope it will repaint us?
		 */
		restart();
	}
	
	
	private void restart() {

		width = mData.getImage().getWidth();
		height = mData.getImage().getHeight();

		ObjectProperty<Point2D> mouseDown = new SimpleObjectProperty<>();

		imageView.setOnMousePressed(e -> {            
			Point2D mousePress = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));
			mouseDown.set(mousePress);
			//System.out.println("press @ x=" + mousePress.getX()+" y=" + mousePress.getY());
		});

		imageView.setOnMouseDragged(e -> {
			Point2D dragPoint = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));
			shift(imageView, dragPoint.subtract(mouseDown.get()));
			mouseDown.set(imageViewToImage(imageView, new Point2D(e.getX(), e.getY())));
		});

		imageView.setOnScroll(e -> {
			double delta = e.getDeltaY();
			Rectangle2D viewport = imageView.getViewport();

			double scale = clamp(Math.pow(1.01, delta),

					// don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction:
					Math.min(MIN_PIXELS / viewport.getWidth(), MIN_PIXELS / viewport.getHeight()),

					// don't scale so that we're bigger than image dimensions:
					Math.max(width / viewport.getWidth(), height / viewport.getHeight())

					);

			Point2D mouse = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));

			double newWidth = viewport.getWidth() * scale;
			double newHeight = viewport.getHeight() * scale;

			// To keep the visual point under the mouse from moving, we need
			// (x - newViewportMinX) / (x - currentViewportMinX) = scale
			// where x is the mouse X coordinate in the image

			// solving this for newViewportMinX gives

			// newViewportMinX = x - (x - currentViewportMinX) * scale 

			// we then clamp this value so the image never scrolls out
			// of the imageview:

			double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale, 
					0, width - newWidth);
			double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale, 
					0, height - newHeight);

			imageView.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
		});

		imageView.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) 
			{
				reset(imageView, width, height);            
			}

			Point2D mouseClick = imageViewToImage(imageView, new Point2D(e.getX(), e.getY()));
			System.out.println("click @ x=" + mouseClick.getX()+" y=" + mouseClick.getY());

			MapOverlay overlay = mData.getOverlayByRectangle( mouseClick.getX(), mouseClick.getY() );
			if( overlay != null )
			{
				setMapData(overlay.getHyperlink());
			}
		});


		HBox buttons = createButtons(width, height, imageView);
		Tooltip tooltip = new Tooltip("Scroll to zoom, drag to pan");
		Tooltip.install(buttons, tooltip);

		Pane container = new Pane(imageView);
		container.setPrefSize(800, 600);

		imageView.fitWidthProperty().bind(container.widthProperty());
		imageView.fitHeightProperty().bind(container.heightProperty());

		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		fillMenu(menuBar);

		//HBox links = createLinks();

		//VBox root = new VBox(menuBar, links, container, buttons);
		VBox root = new VBox(menuBar, container, buttons);
		root.setFillWidth(true);
		VBox.setVgrow(container, Priority.ALWAYS);

		scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle( "����: " + mData.getTitle() ); //"���� - ������� ��������");
		primaryStage.show();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// shift the viewport of the imageView by the specified delta, clamping so
	// the viewport does not move off the actual image:
	private static void shift(ImageView imageView, Point2D delta) {
		Rectangle2D viewport = imageView.getViewport();

		double width = imageView.getImage().getWidth() ;
		double height = imageView.getImage().getHeight() ;

		double maxX = width - viewport.getWidth();
		double maxY = height - viewport.getHeight();

		double minX = clamp(viewport.getMinX() - delta.getX(), 0, maxX);
		double minY = clamp(viewport.getMinY() - delta.getY(), 0, maxY);

		imageView.setViewport(new Rectangle2D(minX, minY, viewport.getWidth(), viewport.getHeight()));
	}
	
	
	private static double clamp(double value, double min, double max) {

		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;
	}

	// convert mouse coordinates in the imageView to coordinates in the actual image:
	private static Point2D imageViewToImage(ImageView imageView, Point2D imageViewCoordinates) {
		double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInLocal().getWidth();
		double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInLocal().getHeight();

		Rectangle2D viewport = imageView.getViewport();
		return new Point2D(
				viewport.getMinX() + xProportion * viewport.getWidth(), 
				viewport.getMinY() + yProportion * viewport.getHeight());
	}
	
	
}
