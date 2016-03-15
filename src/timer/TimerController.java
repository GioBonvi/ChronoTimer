package timer;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TimerController implements Initializable
{
    int timerDigits[] = {0, 0, 0, 0, 0, 0};
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

        startBtn.setDisable(true);
        resumeBtn.setDisable(true);
        pauseBtn.setDisable(false);
        resetBtn.setDisable(false);
    }
    
    // pause button pressed.
    @FXML protected void handlePause(ActionEvent e)
    {
        
        startBtn.setDisable(true);
        resumeBtn.setDisable(false);
        pauseBtn.setDisable(true);
        resetBtn.setDisable(false);
    }
    
    // resume button pressed.
    @FXML protected void handleResume(ActionEvent e)
    {
        startBtn.setDisable(true);
        resumeBtn.setDisable(true);
        pauseBtn.setDisable(false);
        resetBtn.setDisable(false);
    }
    
    // resume button pressed.
    @FXML protected void handleReset(ActionEvent e)
    {
        startBtn.setDisable(false);
        resumeBtn.setDisable(true);
        pauseBtn.setDisable(true);
        resetBtn.setDisable(true);
    }
    
    // set button pressed.
    @FXML protected void handleSet(ActionEvent e)
    {
        
    }
    
    // chrono button pressed.
    @FXML protected void handleChrono(ActionEvent e) throws IOException
    {
        Stage stage;
        Parent root;
        stage = (Stage) chronoBtn.getScene().getWindow();
        
        root = FXMLLoader.load(getClass().getResource("/chrono/Chrono.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // timeline fires an event every 1 millisecond.
        timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(1), event -> {
           
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }   
}