package lab07_danielsavulescu.lab07_danielsavulescu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        final double SCENE_WIDTH = 640, SCENE_HEIGHT = 480;
        final double SPACING = 30;
        
        Pane root = new Pane();
        
        Rectangle rectangle = new Rectangle(SPACING, SPACING, SCENE_WIDTH - SPACING * 2, SCENE_HEIGHT - SPACING * 2);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);
        
        Circle circle = new Circle(rectangle.getX(), rectangle.getY(), 30);
        circle.setFill(Color.DARKRED);
        
        PathTransition pathT = new PathTransition(new Duration(5000), rectangle, circle);
        pathT.setCycleCount(Animation.INDEFINITE);
        pathT.setRate(-1);
        pathT.play();
        
        root.getChildren().addAll(rectangle, circle);
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
