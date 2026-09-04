// GradesGUIFXMLDoc.java
// Main app class that loads and displays the Grades GUI program, that read grades and calculates GPA

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GradesGUIFXMLDoc extends Application {
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("GradesGUIFXMLDoc.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Grades GUI");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
