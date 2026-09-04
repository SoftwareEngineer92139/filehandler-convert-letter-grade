package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.stage.Stage;



public class GradesGUIFXMLDocController implements Initializable
{

    // private reference of type FileHandler
    FileHandler gradesFileHandler;

    // private reference of type ArrayList
    ArrayList<String> m_gradesFromFile;

    // private reference to set the combo box
    ObservableList<String> observableItemList;

    @FXML
    private Label fileReadStatusMessageLabel;

    @FXML
    private TextField fileTextField;

    @FXML
    private Label GPALabel;

    @FXML
    private Button calculateGPAButton;

    @FXML
    private ComboBox<String> displayGradesComboBox;
    
    @FXML
    private Button minimizeButton;

    @FXML
    private Button okButton;

    @FXML
    private Button readGradesFileButton;

    @FXML
    void handleCalculateGPA(ActionEvent event) {
        String letter = displayGradesComboBox.getSelectionModel().getSelectedItem();
        
        // request to convert letter grade to a number grade
        double numberToDisplay = convertGradeToGPA(letter);

        // Display returned value in corresponding label
        GPALabel.setText(String.valueOf(numberToDisplay));

    }

    @FXML
    void handleOKPressed(ActionEvent event) {
        
        // close upon pressing the ok button
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();

    }

    @FXML
    void handleReadGrades(ActionEvent event) {
        String fileName = fileTextField.getText();
        gradesFileHandler.setInputFile(fileName);

        // convert the ArrayList to an ObservableList to populate comboBox

        if(gradesFileHandler.isOpenToRead() & gradesFileHandler.readGrades(m_gradesFromFile))
        {
            fileReadStatusMessageLabel.setText("Read file successful!");
            observableItemList = FXCollections.observableArrayList(m_gradesFromFile);
            displayGradesComboBox.setItems(observableItemList);
        }
        else
        {
            fileReadStatusMessageLabel.setText("Sorry, please try again!");
        }


    }

    @FXML
    void handleWindowMinimize(ActionEvent event) {
        
        // minimize button pressed to minimize window - extra credit
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    // called by FXMLLoader to initialize the controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    // private reference of type FileHandler
    gradesFileHandler = new FileHandler();

    // private reference of type ArrayList
    m_gradesFromFile = new ArrayList<String>();

    // Setting the comboBox
    observableItemList = FXCollections.observableArrayList(m_gradesFromFile);

    }

    double convertGradeToGPA(String gradeListed)
    {
        double numberGPA = 0.0;


        switch (gradeListed)
                {
                    case "A": 
                        numberGPA = 4.0;
                        break;
                    case "A-":
                        numberGPA = 3.7;
                        break;
                    case "B+":
                        numberGPA = 3.3;
                        break;
                    case "B":
                        numberGPA = 3.0;
                        break;
                    case "B-":
                        numberGPA = 2.7;
                        break;
                    case "C+":
                        numberGPA = 2.3;
                        break;
                    case "C":
                        numberGPA = 2.0;
                        break;
                    case "C-":
                        numberGPA = 1.7;
                        break;
                    case "D+":
                        numberGPA = 1.3;
                        break;
                    case "D":
                        numberGPA = 1.0;
                        break;
                    case "D-":
                        numberGPA = 0.7;
                        break;
                    case "F":
                        numberGPA = 0.0;
                        break;
                    default:
                        numberGPA = 0.0;
                }

        return numberGPA;
    }
}
