/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;

import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.time.Instant;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;

public class FXMLDocumentController implements Initializable
{
    Instant startTime;
    java.time.Duration pausedAfter;
    Timeline timeline;
    
    @FXML private Label timeLbl;
    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button resumeBtn;
    @FXML private Button resetBtn;
    
    // start button pressed.
    @FXML protected void handleStart(ActionEvent e)
    {
        // Save the current time.
        startTime = Instant.now();
        // Start the animation.
        timeline.play();

        startBtn.setDisable(true);
        resumeBtn.setDisable(true);
        pauseBtn.setDisable(false);
        resetBtn.setDisable(false);
    }
    
    // pause button pressed.
    @FXML protected void handlePause(ActionEvent e)
    {
        // Pause the animation.
        timeline.pause();
        // Save how long since the start button was pressed.
        pausedAfter = java.time.Duration.between(startTime, Instant.now());

        startBtn.setDisable(true);
        resumeBtn.setDisable(false);
        pauseBtn.setDisable(true);
        resetBtn.setDisable(false);
    }
    
    // resume button pressed.
    @FXML protected void handleResume(ActionEvent e)
    {
        // Update the start time (ignore the time it has been paused).
        startTime = Instant.now().minus(pausedAfter);
        // Restart the animation.
        timeline.play();

        startBtn.setDisable(true);
        resumeBtn.setDisable(true);
        pauseBtn.setDisable(false);
        resetBtn.setDisable(false);
    }
    
    // resume button pressed.
    @FXML protected void handleReset(ActionEvent e)
    {
        // Stop the animation.
        timeline.stop();
        // Reset the scene.
        timeLbl.setText("0:00:00.000");

        startBtn.setDisable(false);
        resumeBtn.setDisable(true);
        pauseBtn.setDisable(true);
        resetBtn.setDisable(true);
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // timeline fires an event every 1 millisecond.
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(1), event -> {
            // Time passed since start button was pressed.
            long elapsed = java.time.Duration.between(startTime, Instant.now()).toMillis();
            long ms = elapsed;
            long s = ms / 1000;
            long m = s / 60;
            long h = m / 60;
            ms = ms % 1000;
            s = s % 60;
            m = m % 60;
            // Text is displayed with leading zeroes.
            String newText = String.format("%d:%02d:%02d.%03d", h, m, s, ms);
            timeLbl.setText(newText);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }
      
    
}
