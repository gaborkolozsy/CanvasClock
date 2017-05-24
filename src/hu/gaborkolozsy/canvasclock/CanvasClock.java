package hu.gaborkolozsy.canvasclock;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * This application is a design clock.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @version 1.0.0
 * @see ClockIcon
 * @see ClockStage
 * @see Application
 * @see EventHandler
 * @see Group
 * @see Scene
 * @see MouseEvent
 * @see Color
 * @see Stage
 * @see StageStyle
 * @see WindowEvent
 */
public class CanvasClock extends Application {

    private final Clock clock;
    private Point2D mousePointer;

    public CanvasClock() {
        this.clock = new Clock();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        ClockIcon widgetIcon = clock.getClockIcon();
        widgetIcon.setOnMouseClicked(createEventHandler(clock));
        
        Group root = new Group();
        root.getChildren().add(widgetIcon);
        
        clock.init();
        
        root.setOnMousePressed((MouseEvent me) -> {
            mousePointer = new Point2D(me.getSceneX(), me.getSceneY());
        });
        
        root.setOnMouseDragged((MouseEvent me) -> {
            stage.setX(me.getScreenX() - mousePointer.getX());
            stage.setY(me.getScreenY() - mousePointer.getY());
        });
        
        stage.initStyle(StageStyle.UNIFIED);
        stage.setOpacity(0.7);
        stage.centerOnScreen();
        Scene scene = new Scene(root, 80, 30, Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent event) -> {
            clock.stop();
            System.exit(0);
        });
        stage.show();
    }
    
    private EventHandler<? super MouseEvent> createEventHandler(Clock clock) {
        return new EventHandler<MouseEvent>() { 
            @Override
            public void handle(MouseEvent me) { 
                ClockStage clockStage = new ClockStage(clock);
                clock.start();
                clockStage.show();
            }
        };
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
