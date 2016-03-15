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
    
    public StringProperty h, m, s;
    public boolean result = false;
    
    @FXML TextField hField;
    @FXML TextField mField;
    @FXML TextField sField;
    
    @FXML protected void handleCancel(ActionEvent e)
    {
        result = false;
        Stage stage = (Stage) hField.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        h.bind(hField.textProperty());
        m.bind(mField.textProperty());
        s.bind(sField.textProperty());
    }    
    
}
