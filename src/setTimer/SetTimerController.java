package setTimer;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetTimerController implements Initializable {
    
    public boolean success = false;
    
    @FXML private TextField hField;
    @FXML private TextField mField;
    @FXML private TextField sField;
    
    public StringProperty h = new SimpleStringProperty(this, "");
    public StringProperty m = new SimpleStringProperty(this, "");
    public StringProperty s = new SimpleStringProperty(this, "");
    
    // cancel button pressed.
    @FXML protected void handleCancel(ActionEvent e)
    {
        success = false;
        ((Stage) hField.getScene().getWindow()).close();
    }
    
    // ok button pressed.
    @FXML protected void handleOk(ActionEvent e)
    {
        success = true;
        HashMap<TextField, Integer> fields = new HashMap<>();
        fields.put(hField, -1);
        fields.put(mField, -1);
        fields.put(sField, -1);
        
        fields.keySet().stream().forEach((field) -> {
            try
            {
                fields.replace(field, Integer.parseInt(field.getText()));
                if ((field != hField && fields.get(field) > 59) || fields.get(field) < 0 || fields.get(field) > 99)
                {
                    field.getStyleClass().add("wrong");
                    success = false;
                }
                else
                {
                    field.getStyleClass().remove("wrong");
                }
            }
            catch(NumberFormatException err)
            {
                field.getStyleClass().add("wrong");
                success = false;
            }
        });
        
        if(fields.get(hField) == 0 && fields.get(mField) == 0 && fields.get(sField) == 0)
        {
            fields.forEach((field, n) -> {
                field.getStyleClass().add("wrong");
            });
            success = false;
        }
        
        if (success)
        {
            ((Stage) hField.getScene().getWindow()).close();
        }
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
