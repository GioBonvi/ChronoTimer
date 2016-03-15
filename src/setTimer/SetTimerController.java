package setTimer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetTimerController implements Initializable {
    
    public boolean success = false;
    
    @FXML TextField hField;
    @FXML TextField mField;
    @FXML TextField sField;
    
    // cancel button pressed.
    @FXML protected void handleCancel(ActionEvent e)
    {
        success = false;
        Stage stage = (Stage) hField.getScene().getWindow();
        stage.close();
    }
    
    // ok button pressed.
    @FXML protected void handleOk(ActionEvent e)
    {
        success = true;
        Stage stage = (Stage) hField.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
