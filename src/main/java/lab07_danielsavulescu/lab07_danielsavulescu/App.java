package lab07_danielsavulescu.lab07_danielsavulescu;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
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
        
        PathTransition pathT = new PathTransition(new Duration(7000), rectangle, circle);
        pathT.setCycleCount(Animation.INDEFINITE);
        pathT.setRate(-1);
        pathT.play();
        
        Ellipse ellipse = new Ellipse(SCENE_WIDTH / 2, SCENE_HEIGHT / 2 + 50, 150, 100);
        ellipse.setFill(null);
        ellipse.setStroke(Color.BLACK);
        
        FadeTransition fTrans = new FadeTransition();
        
        ScaleTransition sTrans = new ScaleTransition();
        
        RotateTransition rTrans = new RotateTransition();
        
        TranslateTransition tTrans = new TranslateTransition();
        
        root.getChildren().addAll(rectangle, circle, ellipse);
        var scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
