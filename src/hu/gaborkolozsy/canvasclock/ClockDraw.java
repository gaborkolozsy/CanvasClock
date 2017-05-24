package hu.gaborkolozsy.canvasclock;

import static hu.gaborkolozsy.canvasclock.ClockColors.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;

/**
 * ClockDraw the canvas design clock.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 1.0.0
 * @see LocalDateTime
 * @see DateTimeFormatter
 * @see GraphicsContext
 * @see DropShadow
 * @see Color
 * @see ArcType
 * @see StrokeLineCap
 * @see Font
 */
public class ClockDraw {
    private final GraphicsContext gc;
    private LocalDateTime now;
    private String today;
    private String time;
    private int hours;
    private int minutes;
    private int seconds;
    private long millis;
    private int nano;
    private double start1;
    private double start2;
    private double start3;
    private double start4;
    private double start5;
    private double start6;
    private double start7 = 180;
    private double start8;
    private double HOUR_ROUND;

    public ClockDraw(GraphicsContext gc) {
        this.gc = gc;
    }

    public void refreshClock() {
        now = LocalDateTime.now();
        today = now.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy LLL dd E"));
        time = now.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        hours = now.getHour();
        minutes = now.getMinute();
        seconds = now.getSecond();
        nano = LocalDateTime.now().getNano();
        millis = (seconds * 100) + (nano / 10_000_000);
        start1 += 2;
        start2 -= 3;
        start3 += 1.5;
        start4 -= 1;
        start5 -= 0.146;
        start6 -= 3.5;
        start7 += 2;
        start8 -= 1.75;
        HOUR_ROUND = 0.0; // must update but once draw the circle then delete from it
    }

    public void drawClock() {
        gc.setEffect(null);
        gc.clearRect(0, 0, 500, 500);

        DropShadow shadow = new DropShadow(6, 2, 2, BLUE1.get());
        gc.setEffect(shadow);

        gc.beginPath();

        gc.setStroke(BLUE1.get());
        gc.setLineWidth(8);
        gc.setLineCap(StrokeLineCap.ROUND);

        // at 12 and 00 o'clock show full circle
        if (hours > 12 || hours == 0) {
            HOUR_ROUND = 360.0;
        }
        gc.strokeArc(13, 13, 200, 200, 90, -((hours * 30) - HOUR_ROUND), ArcType.OPEN);

        // minutes
        gc.strokeArc(28, 28, 170, 170, 90, -(minutes * 6), ArcType.OPEN);

        // seconds
        gc.strokeArc(43, 43, 140, 140, 90, -(millis * .06), ArcType.OPEN);

        // 2 sectors
        if (start7 < -180) {
            start7 = 180;
        }
        gc.setEffect(null);
        gc.setStroke(BLUE5.get());
        gc.setLineWidth(4);
        gc.strokeArc(55, 55, 116, 116, start7, 35, ArcType.ROUND);

        if (start4 > 360) {
            start4 = 0;
        }
        gc.setStroke(BLUE4.get());
        gc.setLineWidth(4);
        gc.strokeArc(69.5, 69.5, 87, 87, start4, 35, ArcType.ROUND);

        // hour hand(hour)
        if (start5 < -360) {
            start5 = 0;
        }
        gc.setStroke(RED1.get());
        gc.setLineWidth(5);
        gc.strokeArc(74, 74, 78, 78, start5, 1, ArcType.ROUND);

        // hour hand(minute)
        if (start8 < -360) {
            start8 = 0;
        }
        gc.setStroke(RED3.get());
        gc.setLineWidth(5);
        gc.strokeArc(59, 59, 108, 108, start8, 1, ArcType.ROUND);

        // hour hand(second)
        if (start6 > 360) {
            start6 = 0;
        }
        gc.setStroke(RED2.get());
        gc.setLineWidth(66);
        //gc.strokeArc(80, 80, 66, 66, 90 - (seconds * 6), 2, ArcType.CHORD);
        gc.strokeArc(80, 80, 66, 66, start6, 2, ArcType.CHORD);

        // date
        shadow = new DropShadow(6, 2, 2, BLUE1.get());
        gc.setEffect(shadow);
        gc.setFont(new Font("Arial Bold", 14));
        gc.setFill(BLUE1.get());
        gc.fillText(today, 68, 110);

        // time
        shadow = new DropShadow(6, 2, 2, Color.RED);
        gc.setEffect(shadow);
        gc.setFont(new Font("Arial", 10));
        gc.setFill(Color.RED);
        gc.fillText(time, 68, 130);

        // 3 curves
        if (start1 > 360) {
            start1 = 0;
        }
        gc.setEffect(null);
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.strokeArc(35.5, 35.5, 155, 155, start1, -(seconds * 6), ArcType.OPEN);

        if (start2 < -360) {
            start2 = 0;
        }
        gc.setStroke(BLUE2.get());
        gc.setLineWidth(2);
        gc.strokeArc(20.5, 20.5, 185, 185, start2, 30, ArcType.OPEN);

        if (start3 > 360) {
            start3 = 0;
        }
        gc.setStroke(BLUE3.get());
        gc.setLineWidth(2);
        gc.strokeArc(5.5, 5.5, 215, 215, start3, 50, ArcType.OPEN);

        gc.stroke();
    }
    
}
