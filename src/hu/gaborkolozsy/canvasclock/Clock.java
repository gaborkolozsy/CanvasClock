package hu.gaborkolozsy.canvasclock;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Design clock.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @version 1.0.0
 * @see AnimationTimer
 * @see KeyFrame
 * @see Timeline
 * @see ActionEvent
 * @see Canvas
 * @see GraphicsContext
 * @see Light
 * @see Lighting
 * @see Color
 * @see Rectangle
 * @see Duration
 */
public class Clock extends Pane {
    
    private final ClockIcon clockIcon = new ClockIcon();
    private ClockDraw draw;
    private AnimationTimer timer;    
    
    public Clock() {
        this.clockIcon.setPrefWidth(80);
        this.clockIcon.setPrefHeight(30); 
    }

    public ClockIcon getClockIcon() {
        return clockIcon;
    }

    public Pane getAsNode() {
        return this;
    }
    
    public void init() {
        Rectangle background = new Rectangle(); 
        background.setArcWidth(20); 
        background.setArcHeight(20); 
        background.setHeight(226); 
        background.setWidth(226);
        background.setFill(Color.STEELBLUE);
        
        Light.Spot light = new Light.Spot();
        light.setX(380);
        light.setY(380);
        light.setZ(110);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);
        background.setEffect(lighting);
        getChildren().add(background);
        
        Canvas canvas = new Canvas(226, 226);
        getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        draw = new ClockDraw(gc);
        run();
    }
    
    private void run() { 
        Timeline delayTimeline = new Timeline();
        delayTimeline.getKeyFrames().add( 
            new KeyFrame(new Duration(1000 - (System.currentTimeMillis() % 1000)), (ActionEvent event) -> {
                timer = new AnimationTimer() {
                    @Override 
                    public void handle(long now) { 
                        draw.refreshClock();
                        draw.drawClock();
                    } 
                };
            })
        );
        delayTimeline.play();
    }
    
    public void start() {
        if (timer != null) { 
            timer.start(); 
        } 
    }
    
    public void stop() {
        if (timer != null) { 
            timer.stop(); 
        } 
    }
    
}
