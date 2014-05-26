/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hexomzetter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Wim
 */
public class HexOmzetter extends Application {

    private Button convert;
    private TextField txt_hexValue;
    private TextField txt_decValue;

    private final int HGAP=10;
    
    @Override
    public void start(Stage primaryStage) {
        convert = new Button();
        convert.setText("Zet Om");
        convert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    txt_decValue.setText("" + Integer.parseInt(txt_hexValue.getText(), 16));
                } catch (Exception e) {
                    txt_hexValue.setText("");
                    txt_decValue.setText("");
                }
            }
        });

        txt_hexValue = new TextField();
        txt_hexValue.setPrefColumnCount(5);
        
        txt_decValue = new TextField();
        txt_decValue.setPrefColumnCount(10);
        txt_decValue.setEditable(false);

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(HGAP, HGAP, HGAP, HGAP));
        root.getChildren().add(new Label("Hexadecimaal getal:"));
        root.getChildren().add(txt_hexValue);
        root.getChildren().add(new Label("Decimaal getal:"));
        root.getChildren().add(txt_decValue);
        root.getChildren().add(convert);

        Scene scene = new Scene(root);//, 300, 250);

        primaryStage.setTitle("HexOmzetter");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
