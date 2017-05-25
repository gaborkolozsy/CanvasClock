package hu.gaborkolozsy.canvasclock;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * Design clock stage.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 1.0.0
 * @see Point2D
 * @see Group
 * @see Scene
 * @see Stage
 * @see StageStyle
 * @see WindowEvent
 */
public final class ClockStage extends Stage {

    private final Group root;
    private Point2D anchorPoint;
    private Point2D actualLocation;   

    public ClockStage(Clock clock) {
        this.root = new Group();
        this.root.getChildren().add(clock.getAsNode());
        
        styleInitializing();
        actualLocation();
        mousePointer();
        dragging();
        dragStop();
        closeByClick();
        
        Scene scene = new Scene(root, clock.getAsNode().getPrefWidth(), 
                                      clock.getAsNode().getPrefHeight(), null);
        setScene(scene);

    }

    /**
     * App without frame.
     */
    private void styleInitializing() {
        initStyle(StageStyle.TRANSPARENT);
    }
    
    /**
     * Store design clock initial location for dragging.
     */
    private void actualLocation() {
        addEventHandler(WindowEvent.WINDOW_SHOWN, (e) -> {
            actualLocation = new Point2D(getX(), getY());
        });
    }
    
    /**
     * Store mouse cursor anchor point for dragging.
     */
    private void mousePointer() {
        root.setOnMousePressed(me -> {
            anchorPoint = new Point2D(me.getScreenX(), me.getScreenY());
        });
    }
    
    /**
     * Dragging.
     * (getSceneX() and getSceneY() methods too slow for it)
     */
    private void dragging() {
        root.setOnMouseDragged(me -> {
            if (anchorPoint != null && actualLocation != null) {
                setX(actualLocation.getX() + me.getScreenX() - anchorPoint.getX());
                setY(actualLocation.getY() + me.getScreenY() - anchorPoint.getY());
            }
        });
    }
    
    /**
     * Store design clock actual location for dragging.
     */
    private void dragStop() {
        root.setOnMouseReleased(me -> {
            actualLocation = new Point2D(getX(), getY());
        });
    }
    
    /**
     * Close the design clock after 2 click on it.
     */
    private void closeByClick() {
        root.setOnMouseClicked(me -> {
            if (me.getClickCount() == 2) {
                close();
            }
        });
    }

    @Override
    public void close() {
        super.close();
        root.getChildren().clear();
    }
    
}
