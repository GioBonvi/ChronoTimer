package chronotimer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChronoTimer extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent chrono = FXMLLoader.load(getClass().getResource("/stopwatch/Stopwatch.fxml"));
        
        Scene scene = new Scene(chrono);
        
        stage.setScene(scene);
        stage.setTitle("Stopwatch");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }   
}