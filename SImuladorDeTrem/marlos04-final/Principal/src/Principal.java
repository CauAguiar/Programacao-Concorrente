import java.util.concurrent.Semaphore;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Principal extends Application{
    Semaphore semaphore = new Semaphore(1);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello JavaFX");
        
        // Create a simple button
        Button btn = new Button();
        
        // Create a layout and add the button to it
        Pane root = new Pane();
        root.getChildren().add(btn);

        Path path = new Path(
                new MoveTo(100, 100),
                new LineTo(100, 200),
                new LineTo(300, 200)
        );

        Path path1_1 = new Path(
                new MoveTo(300, 200),
                new LineTo(300, 100)
        );

        Path path1_12 = new Path(
                new MoveTo(300, 100),
                new LineTo(100, 100)
        );

        Path path2 = new Path(
                new MoveTo(500, 200),
                new LineTo(500, 100),
                new LineTo(300, 100)
        );

        Path path2_1 = new Path(
                new MoveTo(300, 100),
                new LineTo(300, 200)
                
        );

        Path path2_11 = new Path(
                new MoveTo(300, 200),
                new LineTo(500, 200)
        );

        //!root.getChildren().add(path2_1);
        //!root.getChildren().add(path2_11);
        //!root.getChildren().add(path1_1);
        //!root.getChildren().add(path1_12);

        Path quadrado = new Path();
        quadrado.getElements().add(new MoveTo(100, 100));
        quadrado.getElements().add(new LineTo(600, 100));
        quadrado.getElements().add(new LineTo(600, 600));
        quadrado.getElements().add(new LineTo(100, 600));
        quadrado.getElements().add(new LineTo(100, 100));
        quadrado.setStroke(Color.RED);
        quadrado.setStrokeWidth(5);
        root.getChildren().add(quadrado);

        Path path3 = new Path(
                new MoveTo(100, 100),
                new LineTo(100, 200),
                new LineTo(100, 300),
                new LineTo(100, 400),
                new LineTo(100, 500),
                new LineTo(100, 600),
                new LineTo(200, 600),
                new LineTo(200, 500),
                new LineTo(200, 400),
                new LineTo(300, 400),
                new LineTo(300, 300),
                new LineTo(400, 300),
                new LineTo(400, 400),
                new LineTo(500, 400),
                new LineTo(500, 500),
                new LineTo(500, 600),
                new LineTo(600, 600),
                new LineTo(600, 500),
                new LineTo(600, 400),
                new LineTo(600, 300),
                new LineTo(600, 200),
                new LineTo(600, 100),
                new LineTo(500, 100),
                new LineTo(400, 100),
                new LineTo(400, 200),
                new LineTo(300, 200),
                new LineTo(300, 100),
                new LineTo(200, 100),
                new LineTo(100, 100)
        );

        Path path4 = new Path(
                new MoveTo(600, 100),
                new LineTo(500, 100),
                new LineTo(400, 100),
                new LineTo(300, 100),
                new LineTo(200, 100),
                new LineTo(100, 100),
                new LineTo(100, 200),
                new LineTo(100, 300),
                new LineTo(100, 400),
                new LineTo(100, 500),
                new LineTo(100, 600),
                new LineTo(200, 600),
                new LineTo(300, 600),
                new LineTo(400, 600),
                new LineTo(500, 600),
                new LineTo(600, 600),
                new LineTo(600, 500),
                new LineTo(600, 400),
                new LineTo(600, 300),
                new LineTo(600, 200),
                new LineTo(600, 100)                
        );

        Path path5 = new Path(
                new MoveTo(100, 200),
                new LineTo(200, 200),
                new LineTo(300, 200),
                new LineTo(400, 200),
                new LineTo(500, 200),
                new LineTo(600, 200),
                new LineTo(600, 300),
                new LineTo(600, 400),
                new LineTo(500, 400),
                new LineTo(400, 400),
                new LineTo(300, 400),
                new LineTo(200, 400),
                new LineTo(100, 400),
                new LineTo(100, 300),
                new LineTo(100, 200)
        );

        Path path6 = new Path(
                new MoveTo(600, 600),
                new LineTo(600, 500),
                new LineTo(600, 400),
                new LineTo(500, 400),
                new LineTo(400, 400),
                new LineTo(300, 400),
                new LineTo(200, 400),
                new LineTo(100, 400),
                new LineTo(100, 500),
                new LineTo(100, 600),
                new LineTo(200, 600),
                new LineTo(300, 600),
                new LineTo(400, 600),
                new LineTo(500, 600),
                new LineTo(600, 600)
        );

        Path path7 = new Path(
                new MoveTo(300, 600),
                new LineTo(300, 500),
                new LineTo(300, 400),
                new LineTo(300, 300),
                new LineTo(300, 200),
                new LineTo(300, 100),
                new LineTo(400, 100),
                new LineTo(500, 100),
                new LineTo(500, 200),
                new LineTo(500, 300),
                new LineTo(500, 400),
                new LineTo(500, 500),
                new LineTo(500, 600),
                new LineTo(400, 600),
                new LineTo(300, 600)
        );


        Path path8 = new Path(
                new MoveTo(600, 300),
                new LineTo(500, 300),
                new LineTo(400, 300),
                new LineTo(300, 300),
                new LineTo(300, 400),
                new LineTo(300, 500),
                new LineTo(300, 600),
                new LineTo(400, 600),
                new LineTo(500, 600),
                new LineTo(600, 600),
                new LineTo(600, 500),
                new LineTo(600, 400),
                new LineTo(600, 300)
        );

        Path path9 = new Path(
                new MoveTo(400, 600),
                new LineTo(400, 500),
                new LineTo(500, 500),
                new LineTo(500, 400),
                new LineTo(600, 400),
                new LineTo(600, 300),
                new LineTo(500, 300),
                new LineTo(500, 200),
                new LineTo(400, 200),
                new LineTo(400, 100),
                new LineTo(300, 100),
                new LineTo(300, 200),
                new LineTo(200, 200),
                new LineTo(200, 300),
                new LineTo(100, 300),
                new LineTo(100, 400),
                new LineTo(200, 400),
                new LineTo(200, 500),
                new LineTo(300, 500),
                new LineTo(300, 600),
                new LineTo(400, 600)
        );

        Path path10 = new Path(
                new MoveTo(100, 600),
                new LineTo(200, 600),
                new LineTo(300, 600),
                new LineTo(300, 500),
                new LineTo(400, 500),
                new LineTo(400, 400),
                new LineTo(500, 400),
                new LineTo(500, 300),
                new LineTo(600, 300),
                new LineTo(600, 200),
                new LineTo(600, 100),
                new LineTo(500, 100),
                new LineTo(400, 100),
                new LineTo(400, 200),
                new LineTo(300, 200),
                new LineTo(300, 300),
                new LineTo(200, 300),
                new LineTo(200, 400),
                new LineTo(100, 400),
                new LineTo(100, 500),
                new LineTo(100, 600)
        );

        path10.setStroke(Color.BLUEVIOLET);
        path10.setStrokeWidth(5);
        root.getChildren().add(path10);


        path2_1.setStroke(Color.RED);
        path2_1.setStrokeWidth(5);
        path2_11.setStroke(Color.RED);
        path2_11.setStrokeWidth(5);
        path2.setStroke(Color.RED);
        path2.setStrokeWidth(5);
        //!root.getChildren().add(path2);


        // Set the path color
        path.setStroke(Color.BLUE);
        path.setStrokeWidth(5);
        //!root.getChildren().add(path);
        // Set the scene















        Circle blackCircle = new Circle(10, Color.BLACK);







        //pathTransition1.setAutoReverse(true);

                // Create a yellow circle
        Circle yellowCircle = new Circle(10, Color.CHOCOLATE);

         PathTransition pathTransition1 = new PathTransition();
        pathTransition1.setDuration(Duration.seconds(6));
        pathTransition1.setPath(path);
        pathTransition1.setNode(yellowCircle);
        pathTransition1.setCycleCount(1);
        //!pathTransition1.play();

        // Create a PathTransition for the yellow circle

        
/*         pathTransition2.setOnFinished(event -> {

        }); */

        root.getChildren().addAll(blackCircle, yellowCircle);

        PathTransition pathTransition2 = new PathTransition();
        pathTransition2.setDuration(Duration.seconds(4));
        pathTransition2.setPath(path2);
        pathTransition2.setNode(blackCircle);
        pathTransition2.setCycleCount(1);
        //!pathTransition2.play();

        ThreadPath t = new ThreadPath(1, pathTransition2, path2, path2_1, path2_11, this);
        ThreadPath t1 = new ThreadPath(2, pathTransition1, path, path1_1, path1_12, this);
        //t.start();
        //t1.start();

        //pathTransition1.play();
        //pathTransition2.play();

        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        
        // Show the stage
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    } // fim do main
}

