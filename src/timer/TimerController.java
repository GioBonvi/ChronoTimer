package timer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TimerController implements Initializable
{
    Duration duration = Duration.ofMinutes(1);
    Instant endTime;
    Instant pausedAt;
    Timeline timeline;
    
    @FXML private Label timeLbl;
    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button resumeBtn;
    @FXML private Button resetBtn;
    @FXML private Button setBtn;
    @FXML private Button chronoBtn;
    
    // start button pressed.
    @FXML protected void handleStart(ActionEvent e)
    {
        endTime = Instant.now().plus(duration);
        timeline.play();
        
        startBtn.setDisable(true);
        resumeBtn.setDisable(true);
        pauseBtn.setDisable(false);
        setBtn.setDisable(true);
        resetBtn.setDisable(false);
    }
    
    // pause button pressed.
    @FXML protected void handlePause(ActionEvent e)
    {
        timeline.pause();
        pausedAt = Instant.now();
        
        startBtn.setDisable(true);
        resumeBtn.setDisable(false);
        pauseBtn.setDisable(true);
        resetBtn.setDisable(false);
    }
    
    // resume button pressed.
    @FXML protected void handleResume(ActionEvent e)
    {
        timeline.play();
        endTime = endTime.plus(Duration.between(pausedAt, Instant.now()));
        startBtn.setDisable(true);
        resumeBtn.setDisable(true);
        pauseBtn.setDisable(false);
        resetBtn.setDisable(false);
    }
    
    // reset button pressed.
    @FXML protected void handleReset(ActionEvent e)
    {
        timeline.stop();
        showTime(true);
        
        startBtn.setDisable(false);
        resumeBtn.setDisable(true);
        pauseBtn.setDisable(true);
        setBtn.setDisable(false);
        resetBtn.setDisable(true);
    }
    
    // set button pressed.
    @FXML protected void handleSet(ActionEvent e) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/setTimer/SetTimer.fxml"));
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Set timer");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        VBox layout = (VBox) loader.load();
        Scene scene = new Scene(layout);
        dialogStage.setScene(scene);

        setTimer.SetTimerController controller = loader.getController();

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        
        // If the dialog was successful update the timer.
        if(controller.success)
        {
            long time = Integer.parseInt(controller.h.get()) * 3600 +
                        Integer.parseInt(controller.m.get()) * 60 +
                        Integer.parseInt(controller.s.get());
            duration = Duration.ofSeconds(time);
            showTime(true);
        }
    }
    
    // chrono button pressed.
    @FXML protected void handleChrono(ActionEvent e) throws IOException
    {
        Stage stage;
        Parent root;
        stage = (Stage) chronoBtn.getScene().getWindow();
        
        root = FXMLLoader.load(this.getClass().getResource("/stopwatch/Stopwatch.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Stopwatch");
        stage.show();
    }
    
    private void showTime()
    {
        showTime(false);
    }
    
    private void showTime(boolean showDuration)
    {
        // Time remaining to the end of the timer.
        long remaining;
        if (showDuration)
        {
            remaining = duration.getSeconds();
        }
        else
        {
            remaining = Duration.between(Instant.now(), endTime).getSeconds();
        }
        remaining = remaining < 0 ? 0 : remaining;
        long s = remaining;
        long m = s / 60;
        long h = m / 60;
        s = s % 60;
        m = m % 60;
        // Text is displayed with leading zeroes.
        String newText = String.format("%02d:%02d:%02d", h, m, s);
        timeLbl.setText(newText);
        if (remaining == 0)
        {
                timeline.stop();
                startBtn.setDisable(true);
                resumeBtn.setDisable(true);
                pauseBtn.setDisable(true);
                setBtn.setDisable(true);
                resetBtn.setDisable(false);
                
                Media media = new Media(this.getClass().getResource("/timer/timer_end.wav").toString());
                MediaPlayer player = new MediaPlayer(media);
                player.play();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // timeline fires an event every 1 millisecond.
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            showTime();            
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        
        resetBtn.fire();
    }
}