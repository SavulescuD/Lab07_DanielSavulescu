package lab07_danielsavulescu.lab07_danielsavulescu;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        final double RECTANGLE_WIDTH = SCENE_WIDTH - SPACING * 2, RECTANGLE_HEIGHT = SCENE_HEIGHT - SPACING * 2;
        final double TOTAL_DURATION = 10000;
        
        double perimeter = 2 * (RECTANGLE_WIDTH + RECTANGLE_HEIGHT);
        Duration topBottom = Duration.millis(TOTAL_DURATION * (RECTANGLE_WIDTH / perimeter));
        Duration leftRight = Duration.millis(TOTAL_DURATION * (RECTANGLE_HEIGHT / perimeter));
        
        Pane pane = new Pane();
        
        Rectangle rectangle = new Rectangle(SPACING, SPACING, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);
        
        Circle circle = new Circle(rectangle.getX(), rectangle.getY(), 15);
        circle.setFill(Color.DARKRED);
        
        PathTransition pathT = new PathTransition(Duration.millis(TOTAL_DURATION), rectangle, circle);
        pathT.setCycleCount(Animation.INDEFINITE);
        pathT.setInterpolator(Interpolator.LINEAR);
        pathT.setCycleCount(1);
        
        Ellipse ellipse = new Ellipse(SCENE_WIDTH / 2, SCENE_HEIGHT / 2, 150, 100);
        ellipse.setFill(null);
        ellipse.setStroke(Color.BLACK);
        pane.getChildren().addAll(rectangle, circle, ellipse);
        
        FadeTransition fTrans = new FadeTransition(new Duration(2500));
        fTrans.setNode(ellipse);
        fTrans.setFromValue(1.0);
        fTrans.setToValue(0.25);
        
        ScaleTransition sTrans = new ScaleTransition(new Duration(2500));
        sTrans.setNode(ellipse);
        sTrans.setFromX(1.0);
        sTrans.setFromY(1.0);
        sTrans.setToX(2.0);
        sTrans.setToY(2.0);
        
        RotateTransition rTrans = new RotateTransition(new Duration(2500));
        rTrans.setNode(ellipse);
        rTrans.setFromAngle(0.0);
        rTrans.setToAngle(360.0);
        
        TranslateTransition tTrans = new TranslateTransition(new Duration(2500));
        tTrans.setNode(ellipse);
        tTrans.setCycleCount(Animation.INDEFINITE);
        tTrans.setAutoReverse(true);
        tTrans.setToX(SCENE_WIDTH / 2);
        tTrans.setToY(SCENE_HEIGHT / 2 - 50);
        
        SequentialTransition sqTrans = new SequentialTransition(fTrans, sTrans, sTrans, tTrans);
        
        ParallelTransition allAnimations = new ParallelTransition(sqTrans, pathT);
        
        Button startBtn = new Button("Start");
        Button resetBtn = new Button("Reset");
        Button exitBtn = new Button("Exit");
        
        HBox buttons = new HBox(15, startBtn, resetBtn, exitBtn);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20));
        
        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(buttons);
        
        startBtn.setOnAction(event -> {
            if (allAnimations.getStatus() != Animation.Status.RUNNING) {
                resetShapes(circle, ellipse);
                allAnimations.playFromStart();
                startBtn.setDisable(true);
            }
        });
        
        resetBtn.setOnAction(event -> {
            allAnimations.stop();
            resetShapes(circle, ellipse);
            startBtn.setDisable(false);
        });
        
        exitBtn.setOnAction(event -> {
            allAnimations.stop();
            Platform.exit();
        });
        
        var scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT + 60);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    /**
     * resets the status of the circle and ellipse
     * @param circle the circle
     * @param ellipse the ellipse
     */
    public static void resetShapes(Circle circle, Ellipse ellipse) {
        circle.setTranslateX(0);
        circle.setTranslateY(0);
        ellipse.setOpacity(1);
        ellipse.setScaleX(1);
        ellipse.setScaleY(1);
        ellipse.setRotate(0);
        ellipse.setTranslateX(0);
        ellipse.setTranslateY(0);
    }

}
