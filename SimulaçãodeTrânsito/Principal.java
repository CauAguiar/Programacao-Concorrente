/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: Principal.java
* Funcao...........: Classe principal do programa que contem o metodo main
*                 e a interface grafica do programa.
*************************************************************** */

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Principal extends Application {
    Rectangle blackCircle = new Rectangle(25, 25); // fantasma preto
    Rectangle blueCircle = new Rectangle(25, 25);  // fantasma azul
    Rectangle pinkCircle = new Rectangle(25, 25); // fantasma rosa
    Rectangle purpleCircle = new Rectangle(25, 25); // fantasma roxo
    Rectangle orangeCircle = new Rectangle(25, 25); // fantasma laranja
    Rectangle greenCircle = new Rectangle(25, 25); // fantasma verde
    Rectangle whiteCircle = new Rectangle(25, 25); // fantasma branco

    int caseReset = 0; // variavel que indica se o botao reset foi pressionado

    Path pathAll; // caminho que contem todos os caminhos dos fantasmas

    Rectangle redCircle = new Rectangle(25, 25); // fantasma vermelho

    Circle pacman = new Circle(15, Color.YELLOW); // pacman
    private boolean pauseFlagP1 = true; // variavel que indica se o botao pause do fantasma preto foi pressionado
    private boolean pauseFlagP5 = true; // variavel que indica se o botao pause do fantasma vermelho foi pressionado
    private boolean pauseFlagP8 = true; // variavel que indica se o botao pause do fantasma azul foi pressionado
    private boolean pauseFlagP10 = true; // variavel que indica se o botao pause do fantasma rosa foi pressionado
    private boolean pauseFlagP13 = true; // variavel que indica se o botao pause do fantasma roxo foi pressionado
    private boolean pauseFlagP18 = true; // variavel que indica se o botao pause do fantasma laranja foi pressionado
    private boolean pauseFlagP20 = true; // variavel que indica se o botao pause do fantasma verde foi pressionado
    private boolean pauseFlagP24 = true; // variavel que indica se o botao pause do fantasma branco foi pressionado
    private P1 t; // thread do fantasma preto
    private P5 t1; // thread do fantasma vermelho
    private P8 t2; // thread do fantasma azul
    private P10 t3; // thread do fantasma rosa
    private P13 t4; // thread do fantasma roxo
    private P18 t5; // thread do fantasma laranja
    private P20 t6; // thread do fantasma verde
    private P24 t7; // thread do fantasma branco
    private ThreadInfo threadInfo; // thread que atualiza a interface grafica
    private Pane root; // painel principal
    private Slider sliderP1; // slider do fantasma preto
    private Slider sliderP5; // slider do fantasma vermelho
    private Slider sliderP8; // slider do fantasma azul
    private Slider sliderP10; // slider do fantasma rosa
    private Slider sliderP13; // slider do fantasma roxo
    private Slider sliderP18; // slider do fantasma laranja
    private Slider sliderP20; // slider do fantasma verde
    private Slider sliderP24; // slider do fantasma branco
    private Button reset; // botao reset
    private Button pauseP1; // botao pause do fantasma preto
    private ImageView imageViewPauseP1; // imagem do botao pause do fantasma preto
    private Button pauseP5; // botao pause do fantasma vermelho
    private ImageView imageViewPauseP5; // imagem do botao pause do fantasma vermelho
    private Button pauseP8; // botao pause do fantasma azul
    private ImageView imageViewPauseP8; // imagem do botao pause do fantasma azul
    private Button pauseP10; // botao pause do fantasma rosa
    private ImageView imageViewPauseP10; // imagem do botao pause do fantasma rosa
    private Button pauseP13; // botao pause do fantasma roxo
    private ImageView imageViewPauseP13; // imagem do botao pause do fantasma roxo
    private Button pauseP18; // botao pause do fantasma laranja
    private ImageView imageViewPauseP18; // imagem do botao pause do fantasma laranja
    private Button pauseP20; // botao pause do fantasma verde
    private ImageView imageViewPauseP20; // imagem do botao pause do fantasma verde
    private Button pauseP24; // botao pause do fantasma branco
    private ImageView imageViewPauseP24; // imagem do botao pause do fantasma branco
    private Button showP1; // botao show do fantasma preto
    private ImageView imageViewShowP1; // imagem do botao show do fantasma preto
    private Button showP5; // botao show do fantasma vermelho
    private ImageView imageViewShowP5; // imagem do botao show do fantasma vermelho
    private Button showP8; // botao show do fantasma azul
    private ImageView imageViewShowP8; // imagem do botao show do fantasma azul
    private Button showP10; // botao show do fantasma rosa
    private ImageView imageViewShowP10; // imagem do botao show do fantasma rosa
    private Button showP13; // botao show do fantasma roxo
    private ImageView imageViewShowP13; // imagem do botao show do fantasma roxo
    private Button showP18; // botao show do fantasma laranja
    private ImageView imageViewShowP18; // imagem do botao show do fantasma laranja
    private Button showP20; // botao show do fantasma verde
    private ImageView imageViewShowP20; // imagem do botao show do fantasma verde
    private Button showP24; // botao show do fantasma branco
    private ImageView imageViewShowP24; // imagem do botao show do fantasma branco
    private Pane grid; // painel que contem os pontos de comida
    public int score = 0; // pontuacao do jogo
    private Text scoreText; // texto que contem a pontuacao do jogo
    public Button start; // botao start
    private Set<Point2D> usedCoordinatesBig; // conjunto de coordenadas dos pontos de comida grandes
    private ImagePattern imagePattern0; // imagem do fantasma nao normal
    private ImagePattern imagePattern; // imagem do fantasma vermelho
    private ImagePattern imagePattern2; // imagem do fantasma azul
    private ImagePattern imagePattern3; // imagem do fantasma rosa
    private ImagePattern imagePattern4; // imagem do fantasma preto
    private ImagePattern imagePattern5; // imagem do fantasma roxo
    private ImagePattern imagePattern6; // imagem do fantasma laranja
    private ImagePattern imagePattern7; // imagem do fantasma verde
    private ImagePattern imagePattern8; // imagem do fantasma branco
    private boolean schedule; // variavel que indica se o scheduler esta ativo
    private ScheduledExecutorService scheduler; // scheduler que controla o tempo de jogo
    private ScheduledFuture<?> scheduledFuture; // variavel que controla o tempo de jogo

    /* ***************************************************************
    * Metodo: start
    * Funcao: metodo que inicia a interface grafica do programa
    * Parametros: Stage primaryStage
    * Retorno: void
    *************************************************************** */

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello JavaFX");

        Image image0 = new Image("/img/notnormalghost.gif");
        imagePattern0 = new ImagePattern(image0);

        Image image = new Image("/img/redghost.gif");

        imagePattern = new ImagePattern(image);

        redCircle.setFill(imagePattern);
        redCircle.setX(-12.5);
        redCircle.setY(-12.5);

        Image image2 = new Image("/img/blueghost.gif");

        imagePattern2 = new ImagePattern(image2);

        blueCircle.setFill(imagePattern2);
        blueCircle.setX(-12.5);
        blueCircle.setY(-12.5);

        Image image3 = new Image("/img/pinkghost.gif");

        imagePattern3 = new ImagePattern(image3);

        pinkCircle.setFill(imagePattern3);
        pinkCircle.setX(-12.5);
        pinkCircle.setY(-12.5);

        Image image4 = new Image("/img/blackghost.gif");

        imagePattern4 = new ImagePattern(image4);

        blackCircle.setFill(imagePattern4);
        blackCircle.setX(-12.5);
        blackCircle.setY(-12.5);

        Image image5 = new Image("/img/purpleghost.gif");

        imagePattern5 = new ImagePattern(image5);

        purpleCircle.setFill(imagePattern5);
        purpleCircle.setX(-12.5);
        purpleCircle.setY(-12.5);

        Image image6 = new Image("/img/orangeghost.gif");

        imagePattern6 = new ImagePattern(image6);

        orangeCircle.setFill(imagePattern6);
        orangeCircle.setX(-12.5);
        orangeCircle.setY(-12.5);

        Image image7 = new Image("/img/greenghost.gif");

        imagePattern7 = new ImagePattern(image7);

        greenCircle.setFill(imagePattern7);
        greenCircle.setX(-12.5);
        greenCircle.setY(-12.5);

        Image image8 = new Image("/img/whiteghost.gif");

        imagePattern8 = new ImagePattern(image8);

        whiteCircle.setFill(imagePattern8);
        whiteCircle.setX(-12.5);
        whiteCircle.setY(-12.5);

        Button btn = new Button();

        root = new Pane();
        root.setFocusTraversable(true);
        root.getChildren().add(btn);

        Path quadrado = new Path();
        quadrado.getElements().add(new MoveTo(100, 100));
        quadrado.getElements().add(new LineTo(600, 100));
        quadrado.getElements().add(new LineTo(600, 600));
        quadrado.getElements().add(new LineTo(100, 600));
        quadrado.getElements().add(new LineTo(100, 100));
        quadrado.setStroke(Color.RED);
        quadrado.setStrokeWidth(5);

        Path pathP1 = new Path(
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
                new LineTo(100, 100));

        Path pathP5 = new Path(
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
                new LineTo(600, 100));

        Path pathP8 = new Path(
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
                new LineTo(100, 200));

        Path pathP10 = new Path(
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
                new LineTo(600, 600));

        Path pathP13 = new Path(
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
                new LineTo(300, 600));

        Path pathP18 = new Path(
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
                new LineTo(600, 300));

        Path pathP20 = new Path(
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
                new LineTo(400, 600));

        Path pathP24 = new Path(
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
                new LineTo(100, 600));

        pathAll = new Path();
        pathAll.getElements().addAll(pathP1.getElements());
        pathAll.getElements().addAll(pathP5.getElements());
        pathAll.getElements().addAll(pathP8.getElements());
        pathAll.getElements().addAll(pathP10.getElements());
        pathAll.getElements().addAll(pathP13.getElements());
        pathAll.getElements().addAll(pathP18.getElements());
        pathAll.getElements().addAll(pathP20.getElements());
        pathAll.getElements().addAll(pathP24.getElements());

        pathAll.setStrokeWidth(10);

        pathP1.setStroke(Color.GRAY);
        pathP1.setStrokeWidth(10);
        root.getChildren().add(pathP1);
        pathP1.setVisible(false);

        pathP5.setStroke(Color.RED);
        pathP5.setStrokeWidth(10);
        root.getChildren().add(pathP5);
        pathP5.setVisible(false);

        pathP8.setStroke(Color.LIGHTBLUE);
        pathP8.setStrokeWidth(10);
        root.getChildren().add(pathP8);
        pathP8.setVisible(false);

        pathP10.setStroke(Color.PINK);
        pathP10.setStrokeWidth(10);
        root.getChildren().add(pathP10);
        pathP10.setVisible(false);

        pathP13.setStroke(Color.PURPLE);
        pathP13.setStrokeWidth(10);
        root.getChildren().add(pathP13);
        pathP13.setVisible(false);

        pathP18.setStroke(Color.ORANGE);
        pathP18.setStrokeWidth(10);
        root.getChildren().add(pathP18);
        pathP18.setVisible(false);

        pathP20.setStroke(Color.GREEN);
        pathP20.setStrokeWidth(10);
        root.getChildren().add(pathP20);
        pathP20.setVisible(false);

        pathP24.setStroke(Color.WHITE);
        pathP24.setStrokeWidth(10);
        root.getChildren().add(pathP24);
        pathP24.setVisible(false);

        sliderP1 = new Slider(1, 8, 1);
        sliderP1.setFocusTraversable(false);
        sliderP5 = new Slider(1, 8, 1);
        sliderP5.setFocusTraversable(false);
        sliderP8 = new Slider(1, 8, 1);
        sliderP8.setFocusTraversable(false);
        sliderP10 = new Slider(1, 8, 1);
        sliderP10.setFocusTraversable(false);
        sliderP13 = new Slider(1, 8, 1);
        sliderP13.setFocusTraversable(false);
        sliderP18 = new Slider(1, 8, 1);
        sliderP18.setFocusTraversable(false);
        sliderP20 = new Slider(1, 8, 1);
        sliderP20.setFocusTraversable(false);
        sliderP24 = new Slider(1, 8, 1);
        sliderP24.setFocusTraversable(false);

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/img/emulogic.ttf"), 16);
        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/img/emulogic.ttf"), 12);

        scoreText = new Text("Score " + this.score);
        scoreText.setFont(customFont);
        scoreText.setFill(Color.WHITE);
        scoreText.setTranslateX(80);
        scoreText.setTranslateY(50);

        start = new Button();
        start.setDisable(true);
        start.setFont(customFont);

        Image imageStart = new Image("/img/start.png");
        ImageView imageViewStart = new ImageView(imageStart);

        start.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        start.setMinSize(imageViewStart.getFitWidth(), imageViewStart.getFitHeight());
        start.setMaxSize(imageViewStart.getFitWidth(), imageViewStart.getFitHeight());

        start.setGraphic(imageViewStart);

        start.setOnAction(event -> {
            pacman.setCenterX(100);
            pacman.setCenterY(100);
            pacman.setFill(Color.YELLOW);
            t.p.setGameStart();
            start.setDisable(true);

            Pane x = createFoodPointsGrid();
            root.getChildren().add(x);
            x.toBack();
        }); // fim do start.setOnAction

        start.setOnMouseEntered(e -> start.setCursor(javafx.scene.Cursor.HAND));
        start.setOnMouseExited(e -> start.setCursor(javafx.scene.Cursor.DEFAULT));

        reset = new Button();
        reset.setFont(customFont);

        Image imageReset = new Image("/img/reset.png");
        ImageView imageViewReset = new ImageView(imageReset);

        reset.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        reset.setMinSize(imageViewReset.getFitWidth(), imageViewReset.getFitHeight());
        reset.setMaxSize(imageViewReset.getFitWidth(), imageViewReset.getFitHeight());

        reset.setGraphic(imageViewReset);

        reset.setOnAction(event -> {
            schedule = false;

            if (grid != null) {
                grid.getChildren().clear();
            } // fim do if

            start.setDisable(true);

            pauseFlagP1 = true;
            pauseFlagP5 = true;
            pauseFlagP8 = true;
            pauseFlagP10 = true;
            pauseFlagP13 = true;
            pauseFlagP18 = true;
            pauseFlagP20 = true;
            pauseFlagP24 = true;

            blackCircle.setFill(imagePattern4);
            redCircle.setFill(imagePattern);
            blueCircle.setFill(imagePattern2);
            pinkCircle.setFill(imagePattern3);
            purpleCircle.setFill(imagePattern5);
            orangeCircle.setFill(imagePattern6);
            greenCircle.setFill(imagePattern7);
            whiteCircle.setFill(imagePattern8);

            pauseP1.setGraphic(imageViewPauseP1);
            pauseP5.setGraphic(imageViewPauseP5);
            pauseP8.setGraphic(imageViewPauseP8);
            pauseP10.setGraphic(imageViewPauseP10);
            pauseP13.setGraphic(imageViewPauseP13);
            pauseP18.setGraphic(imageViewPauseP18);
            pauseP20.setGraphic(imageViewPauseP20);
            pauseP24.setGraphic(imageViewPauseP24);

            pathP1.setVisible(false);
            pathP5.setVisible(false);
            pathP8.setVisible(false);
            pathP10.setVisible(false);
            pathP13.setVisible(false);
            pathP18.setVisible(false);
            pathP20.setVisible(false);
            pathP24.setVisible(false);

            showP1.setGraphic(imageViewShowP1);
            showP5.setGraphic(imageViewShowP5);
            showP8.setGraphic(imageViewShowP8);
            showP10.setGraphic(imageViewShowP10);
            showP13.setGraphic(imageViewShowP13);
            showP18.setGraphic(imageViewShowP18);
            showP20.setGraphic(imageViewShowP20);
            showP24.setGraphic(imageViewShowP24);

            t.p.resetAllSemaphores();

            t.p.setInterrupt();
            t.setInterrupt();
            t1.setInterrupt();
            t2.setInterrupt();
            t3.setInterrupt();
            t4.setInterrupt();
            t5.setInterrupt();
            t6.setInterrupt();
            t7.setInterrupt();

            threadInfo.interrupt();

            pacman.setCenterX(-100);
            pacman.setCenterY(-100);

            sliderP1.setValue(1);
            sliderP5.setValue(1);
            sliderP8.setValue(1);
            sliderP10.setValue(1);
            sliderP13.setValue(1);
            sliderP18.setValue(1);
            sliderP20.setValue(1);
            sliderP24.setValue(1);

            t.interrupt();
            t1.interrupt();
            t2.interrupt();
            t3.interrupt();
            t4.interrupt();
            t5.interrupt();
            t6.interrupt();
            t7.interrupt();

            try {
                t.join();
                t1.join();
                t2.join();
                t3.join();
                t4.join();
                t5.join();
                t6.join();
                t7.join();
            } catch (InterruptedException e) {
 
            } // fim do try catch

            redCircle.setX(-12.5);
            redCircle.setY(-12.5);
            blueCircle.setX(-12.5);
            blueCircle.setY(-12.5);
            pinkCircle.setX(-12.5);
            pinkCircle.setY(-12.5);
            purpleCircle.setX(-12.5);
            purpleCircle.setY(-12.5);
            orangeCircle.setX(-12.5);
            orangeCircle.setY(-12.5);
            greenCircle.setX(-12.5);
            greenCircle.setY(-12.5);
            whiteCircle.setX(-12.5);
            whiteCircle.setY(-12.5);
            blackCircle.setX(-12.5);
            blackCircle.setY(-12.5);

            ThreadInfo tf = new ThreadInfo(root, pacman, blackCircle, blueCircle, pinkCircle, redCircle,
                    purpleCircle, orangeCircle, greenCircle, whiteCircle, pathAll, this);

            t = new P1(1, blackCircle, tf, sliderP1);
            t1 = new P5(2, redCircle, tf, sliderP5);
            t2 = new P8(3, blueCircle, tf, sliderP8);
            t3 = new P10(4, pinkCircle, tf, sliderP10);
            t4 = new P13(5, purpleCircle, tf, sliderP13);
            t5 = new P18(6, orangeCircle, tf, sliderP18);
            t6 = new P20(7, greenCircle, tf, sliderP20);
            t7 = new P24(8, whiteCircle, tf, sliderP24);

            greenCircle.setTranslateX(0);
            greenCircle.setTranslateY(0);
            pinkCircle.setTranslateX(0);
            pinkCircle.setTranslateY(0);
            purpleCircle.setTranslateX(0);
            purpleCircle.setTranslateY(0);
            redCircle.setTranslateX(0);
            redCircle.setTranslateY(0);
            blueCircle.setTranslateX(0);
            blueCircle.setTranslateY(0);
            orangeCircle.setTranslateX(0);
            orangeCircle.setTranslateY(0);
            whiteCircle.setTranslateX(0);
            whiteCircle.setTranslateY(0);
            blackCircle.setTranslateX(0);
            blackCircle.setTranslateY(0);

            tf.start();

            try {
                tf.join();
            } catch (InterruptedException e) {

            } // fim do try catch

            t.start();
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            t7.start();

            this.score = 0;
            scoreText.setText("Score " + this.score);

        }); // fim do reset.setOnAction

        reset.setOnMouseEntered(e -> reset.setCursor(javafx.scene.Cursor.HAND));
        reset.setOnMouseExited(e -> reset.setCursor(javafx.scene.Cursor.DEFAULT));

        HBox hboxMenu = new HBox(start, reset);
        hboxMenu.setSpacing(200);
        hboxMenu.setAlignment(Pos.CENTER);
        hboxMenu.setTranslateX(250);
        hboxMenu.setTranslateY(650);

        pauseP1 = new Button();
        pauseP1.setFont(customFont2);
        Image imagePlay = new Image("/img/play.png");
        ImageView imageViewPlayP1 = new ImageView(imagePlay);
        imageViewPlayP1.setFitWidth(117.3);
        imageViewPlayP1.setFitHeight(38);

        pauseP1.setStyle("-fx-padding: 0; -fx-background-color: transparent;");

        Image imagePause = new Image("/img/pause.png");
        imageViewPauseP1 = new ImageView(imagePause);
        imageViewPauseP1.setFitWidth(117.3);
        imageViewPauseP1.setFitHeight(38);

        pauseP1.setMinSize(imageViewPauseP1.getFitWidth(), imageViewPauseP1.getFitHeight());
        pauseP1.setMaxSize(imageViewPauseP1.getFitWidth(), imageViewPauseP1.getFitHeight());

        pauseP1.setGraphic(imageViewPauseP1);

        pauseP1.setOnAction(event -> {
            if (pauseFlagP1) {
                pauseP1.setGraphic(imageViewPlayP1);
                pauseFlagP1 = false;
                t.pathTransition.pause();
                t.setPause(true);
            } else {
                pauseP1.setGraphic(imageViewPauseP1);
                pauseFlagP1 = true;
                t.pathTransition.play();
                t.setPause(false);
            } // fim do if else
        }); // fim do pauseP1.setOnAction

        pauseP1.setOnMouseEntered(e -> pauseP1.setCursor(javafx.scene.Cursor.HAND));
        pauseP1.setOnMouseExited(e -> pauseP1.setCursor(javafx.scene.Cursor.DEFAULT));

        pauseP5 = new Button();
        pauseP5.setFont(customFont2);

        ImageView imageViewPlayP5 = new ImageView(imagePlay);
        imageViewPlayP5.setFitWidth(117.3);
        imageViewPlayP5.setFitHeight(38);

        pauseP5.setStyle("-fx-padding: 0; -fx-background-color: transparent;");

        imageViewPauseP5 = new ImageView(imagePause);
        imageViewPauseP5.setFitWidth(117.3);
        imageViewPauseP5.setFitHeight(38);

        pauseP5.setMinSize(imageViewPauseP5.getFitWidth(), imageViewPauseP5.getFitHeight());
        pauseP5.setMaxSize(imageViewPauseP5.getFitWidth(), imageViewPauseP5.getFitHeight());

        pauseP5.setGraphic(imageViewPauseP5);

        pauseP5.setOnAction(event -> {
            if (pauseFlagP5) {
                pauseP5.setGraphic(imageViewPlayP5);
                pauseFlagP5 = false;
                t1.pathTransition.pause();
                t1.setPause(true);
            } else {
                pauseP5.setGraphic(imageViewPauseP5);
                pauseFlagP5 = true;
                t1.pathTransition.play();
                t1.setPause(false);
            } // fim do if else
        }); // fim do pauseP5.setOnAction

        pauseP5.setOnMouseEntered(e -> pauseP5.setCursor(javafx.scene.Cursor.HAND));
        pauseP5.setOnMouseExited(e -> pauseP5.setCursor(javafx.scene.Cursor.DEFAULT));

        pauseP8 = new Button();
        pauseP8.setFont(customFont2);

        ImageView imageViewPlayP8 = new ImageView(imagePlay);
        imageViewPlayP8.setFitWidth(117.3);
        imageViewPlayP8.setFitHeight(38);

        pauseP8.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        imageViewPauseP8 = new ImageView(imagePause);
        imageViewPauseP8.setFitWidth(117.3);
        imageViewPauseP8.setFitHeight(38);

        pauseP8.setMinSize(imageViewPauseP8.getFitWidth(), imageViewPauseP8.getFitHeight());
        pauseP8.setMaxSize(imageViewPauseP8.getFitWidth(), imageViewPauseP8.getFitHeight());

        pauseP8.setGraphic(imageViewPauseP8);

        pauseP8.setOnAction(event -> {
            if (pauseFlagP8) {
                pauseP8.setGraphic(imageViewPlayP8);
                pauseFlagP8 = false;
                t2.pathTransition.pause();
                t2.setPause(true);
            } else {
                pauseP8.setGraphic(imageViewPauseP8);
                pauseFlagP8 = true;
                t2.pathTransition.play();
                t2.setPause(false);
            } // fim do if else
        }); // fim do pauseP8.setOnAction

        pauseP8.setOnMouseEntered(e -> pauseP8.setCursor(javafx.scene.Cursor.HAND));
        pauseP8.setOnMouseExited(e -> pauseP8.setCursor(javafx.scene.Cursor.DEFAULT));

        pauseP10 = new Button();
        pauseP10.setFont(customFont2);

        ImageView imageViewPlayP10 = new ImageView(imagePlay);
        imageViewPlayP10.setFitWidth(117.3);
        imageViewPlayP10.setFitHeight(38);

        pauseP10.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        imageViewPauseP10 = new ImageView(imagePause);
        imageViewPauseP10.setFitWidth(117.3);
        imageViewPauseP10.setFitHeight(38);

        pauseP10.setMinSize(imageViewPauseP10.getFitWidth(), imageViewPauseP10.getFitHeight());
        pauseP10.setMaxSize(imageViewPauseP10.getFitWidth(), imageViewPauseP10.getFitHeight());

        pauseP10.setGraphic(imageViewPauseP10);

        pauseP10.setOnAction(event -> {
            if (pauseFlagP10) {
                pauseP10.setGraphic(imageViewPlayP10);
                pauseFlagP10 = false;
                t3.pathTransition.pause();
                t3.setPause(true);
            } else {
                pauseP10.setGraphic(imageViewPauseP10);
                pauseFlagP10 = true;
                t3.pathTransition.play();
                t3.setPause(false);
            } // fim do if else
        }); // fim do pauseP10.setOnAction

        pauseP10.setOnMouseEntered(e -> pauseP10.setCursor(javafx.scene.Cursor.HAND));
        pauseP10.setOnMouseExited(e -> pauseP10.setCursor(javafx.scene.Cursor.DEFAULT));

        pauseP13 = new Button();
        pauseP13.setFont(customFont2);

        ImageView imageViewPlayP13 = new ImageView(imagePlay);
        imageViewPlayP13.setFitWidth(117.3);
        imageViewPlayP13.setFitHeight(38);

        pauseP13.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        imageViewPauseP13 = new ImageView(imagePause);
        imageViewPauseP13.setFitWidth(117.3);
        imageViewPauseP13.setFitHeight(38);

        pauseP13.setMinSize(imageViewPauseP13.getFitWidth(), imageViewPauseP13.getFitHeight());
        pauseP13.setMaxSize(imageViewPauseP13.getFitWidth(), imageViewPauseP13.getFitHeight());

        pauseP13.setGraphic(imageViewPauseP13);

        pauseP13.setOnAction(event -> {
            if (pauseFlagP13) {
                pauseP13.setGraphic(imageViewPlayP13);
                pauseFlagP13 = false;
                t4.pathTransition.pause();
                t4.setPause(true);
            } else {
                pauseP13.setGraphic(imageViewPauseP13);
                pauseFlagP13 = true;
                t4.pathTransition.play();
                t4.setPause(false);
            } // fim do if else
        }); // fim do pauseP13.setOnAction

        pauseP13.setOnMouseEntered(e -> pauseP13.setCursor(javafx.scene.Cursor.HAND));
        pauseP13.setOnMouseExited(e -> pauseP13.setCursor(javafx.scene.Cursor.DEFAULT));

        pauseP18 = new Button();
        pauseP18.setFont(customFont2);

        ImageView imageViewPlayP18 = new ImageView(imagePlay);
        imageViewPlayP18.setFitWidth(117.3);
        imageViewPlayP18.setFitHeight(38);

        pauseP18.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        imageViewPauseP18 = new ImageView(imagePause);
        imageViewPauseP18.setFitWidth(117.3);
        imageViewPauseP18.setFitHeight(38);

        pauseP18.setMinSize(imageViewPauseP18.getFitWidth(), imageViewPauseP18.getFitHeight());
        pauseP18.setMaxSize(imageViewPauseP18.getFitWidth(), imageViewPauseP18.getFitHeight());

        pauseP18.setGraphic(imageViewPauseP18);

        pauseP18.setOnAction(event -> {
            if (pauseFlagP18) {
                pauseP18.setGraphic(imageViewPlayP18);
                pauseFlagP18 = false;
                t5.pathTransition.pause();
                t5.setPause(true);
            } else {
                pauseP18.setGraphic(imageViewPauseP18);
                pauseFlagP18 = true;
                t5.pathTransition.play();
                t5.setPause(false);
            } // fim do if else
        }); // fim do pauseP18.setOnAction

        pauseP18.setOnMouseEntered(e -> pauseP18.setCursor(javafx.scene.Cursor.HAND));
        pauseP18.setOnMouseExited(e -> pauseP18.setCursor(javafx.scene.Cursor.DEFAULT));

        pauseP20 = new Button();
        pauseP20.setFont(customFont2);

        ImageView imageViewPlayP20 = new ImageView(imagePlay);
        imageViewPlayP20.setFitWidth(117.3);
        imageViewPlayP20.setFitHeight(38);

        pauseP20.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        imageViewPauseP20 = new ImageView(imagePause);
        imageViewPauseP20.setFitWidth(117.3);
        imageViewPauseP20.setFitHeight(38);

        pauseP20.setMinSize(imageViewPauseP20.getFitWidth(), imageViewPauseP20.getFitHeight());
        pauseP20.setMaxSize(imageViewPauseP20.getFitWidth(), imageViewPauseP20.getFitHeight());

        pauseP20.setGraphic(imageViewPauseP20);

        pauseP20.setOnAction(event -> {
            if (pauseFlagP20) {
                pauseP20.setGraphic(imageViewPlayP20);
                pauseFlagP20 = false;
                t6.pathTransition.pause();
                t6.setPause(true);
            } else {
                pauseP20.setGraphic(imageViewPauseP20);
                pauseFlagP20 = true;
                t6.pathTransition.play();
                t6.setPause(false);
            } // fim do if else
        }); // fim do pauseP20.setOnAction

        pauseP20.setOnMouseEntered(e -> pauseP20.setCursor(javafx.scene.Cursor.HAND));
        pauseP20.setOnMouseExited(e -> pauseP20.setCursor(javafx.scene.Cursor.DEFAULT));

        pauseP24 = new Button();
        pauseP24.setFont(customFont2);

        ImageView imageViewPlayP24 = new ImageView(imagePlay);
        imageViewPlayP24.setFitWidth(117.3);
        imageViewPlayP24.setFitHeight(38);

        pauseP24.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        imageViewPauseP24 = new ImageView(imagePause);
        imageViewPauseP24.setFitWidth(117.3);
        imageViewPauseP24.setFitHeight(38);

        pauseP24.setMinSize(imageViewPauseP24.getFitWidth(), imageViewPauseP24.getFitHeight());
        pauseP24.setMaxSize(imageViewPauseP24.getFitWidth(), imageViewPauseP24.getFitHeight());

        pauseP24.setGraphic(imageViewPauseP24);

        pauseP24.setOnAction(event -> {
            if (pauseFlagP24) {
                pauseP24.setGraphic(imageViewPlayP24);
                pauseFlagP24 = false;
                t7.pathTransition.pause();
                t7.setPause(true);
            } else {
                pauseP24.setGraphic(imageViewPauseP24);
                pauseFlagP24 = true;
                t7.pathTransition.play();
                t7.setPause(false);
            } // fim do if else
        }); // fim do pauseP24.setOnAction

        pauseP24.setOnMouseEntered(e -> pauseP24.setCursor(javafx.scene.Cursor.HAND));
        pauseP24.setOnMouseExited(e -> pauseP24.setCursor(javafx.scene.Cursor.DEFAULT));

        showP1 = new Button();
        showP1.setFont(customFont2);

        Image imageShow = new Image("/img/show.png");

        imageViewShowP1 = new ImageView(imageShow);
        imageViewShowP1.setFitWidth(117.3);
        imageViewShowP1.setFitHeight(38);

        showP1.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        showP1.setMinSize(imageViewShowP1.getFitWidth(), imageViewShowP1.getFitHeight());
        showP1.setMaxSize(imageViewShowP1.getFitWidth(), imageViewShowP1.getFitHeight());

        showP1.setGraphic(imageViewShowP1);

        Image imageHide = new Image("/img/hide.png");
        ImageView imageViewHideP1 = new ImageView(imageHide);
        imageViewHideP1.setFitWidth(117.3);
        imageViewHideP1.setFitHeight(38);

        showP1.setOnAction(event -> {
            if (pathP1.isVisible()) {
                pathP1.setVisible(false);
                showP1.setGraphic(imageViewShowP1);
            } else {
                pathP1.setVisible(true);
                showP1.setGraphic(imageViewHideP1);
            } // fim do if else
        }); // fim do showP1.setOnAction

        showP1.setOnMouseEntered(e -> showP1.setCursor(javafx.scene.Cursor.HAND));
        showP1.setOnMouseExited(e -> showP1.setCursor(javafx.scene.Cursor.DEFAULT));

        showP5 = new Button();
        showP5.setFont(customFont2);

        imageViewShowP5 = new ImageView(imageShow);
        imageViewShowP5.setFitWidth(117.3);
        imageViewShowP5.setFitHeight(38);

        showP5.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        showP5.setMinSize(imageViewShowP5.getFitWidth(), imageViewShowP5.getFitHeight());
        showP5.setMaxSize(imageViewShowP5.getFitWidth(), imageViewShowP5.getFitHeight());

        showP5.setGraphic(imageViewShowP5);

        ImageView imageViewHideP5 = new ImageView(imageHide);
        imageViewHideP5.setFitWidth(117.3);
        imageViewHideP5.setFitHeight(38);

        showP5.setOnAction(event -> {
            if (pathP5.isVisible()) {
                pathP5.setVisible(false);
                showP5.setGraphic(imageViewShowP5);
            } else {
                pathP5.setVisible(true);
                showP5.setGraphic(imageViewHideP5);
            } // fim do if else
        }); // fim do showP5.setOnAction

        showP5.setOnMouseEntered(e -> showP5.setCursor(javafx.scene.Cursor.HAND));
        showP5.setOnMouseExited(e -> showP5.setCursor(javafx.scene.Cursor.DEFAULT));

        showP8 = new Button();
        showP8.setFont(customFont2);

        imageViewShowP8 = new ImageView(imageShow);
        imageViewShowP8.setFitWidth(117.3);
        imageViewShowP8.setFitHeight(38);

        showP8.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        showP8.setMinSize(imageViewShowP8.getFitWidth(), imageViewShowP8.getFitHeight());
        showP8.setMaxSize(imageViewShowP8.getFitWidth(), imageViewShowP8.getFitHeight());

        showP8.setGraphic(imageViewShowP8);

        ImageView imageViewHideP8 = new ImageView(imageHide);
        imageViewHideP8.setFitWidth(117.3);
        imageViewHideP8.setFitHeight(38);

        showP8.setOnAction(event -> {
            if (pathP8.isVisible()) {
                pathP8.setVisible(false);
                showP8.setGraphic(imageViewShowP8);
            } else {
                pathP8.setVisible(true);
                showP8.setGraphic(imageViewHideP8);
            } // fim do if else
        }); // fim do showP8.setOnAction

        showP8.setOnMouseEntered(e -> showP8.setCursor(javafx.scene.Cursor.HAND));
        showP8.setOnMouseExited(e -> showP8.setCursor(javafx.scene.Cursor.DEFAULT));

        showP10 = new Button();
        showP10.setFont(customFont2);

        imageViewShowP10 = new ImageView(imageShow);
        imageViewShowP10.setFitWidth(117.3);
        imageViewShowP10.setFitHeight(38);

        showP10.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        showP10.setMinSize(imageViewShowP10.getFitWidth(), imageViewShowP10.getFitHeight());
        showP10.setMaxSize(imageViewShowP10.getFitWidth(), imageViewShowP10.getFitHeight());

        showP10.setGraphic(imageViewShowP10);

        ImageView imageViewHideP10 = new ImageView(imageHide);
        imageViewHideP10.setFitWidth(117.3);
        imageViewHideP10.setFitHeight(38);

        showP10.setOnAction(event -> {
            if (pathP10.isVisible()) {
                pathP10.setVisible(false);
                showP10.setGraphic(imageViewShowP10);
            } else {
                pathP10.setVisible(true);
                showP10.setGraphic(imageViewHideP10);
            } // fim do if else
        }); // fim do showP10.setOnAction

        showP10.setOnMouseEntered(e -> showP10.setCursor(javafx.scene.Cursor.HAND));
        showP10.setOnMouseExited(e -> showP10.setCursor(javafx.scene.Cursor.DEFAULT));

        showP13 = new Button();
        showP13.setFont(customFont2);

        imageViewShowP13 = new ImageView(imageShow);
        imageViewShowP13.setFitWidth(117.3);
        imageViewShowP13.setFitHeight(38);

        showP13.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        showP13.setMinSize(imageViewShowP13.getFitWidth(), imageViewShowP13.getFitHeight());
        showP13.setMaxSize(imageViewShowP13.getFitWidth(), imageViewShowP13.getFitHeight());

        showP13.setGraphic(imageViewShowP13);

        ImageView imageViewHideP13 = new ImageView(imageHide);
        imageViewHideP13.setFitWidth(117.3);
        imageViewHideP13.setFitHeight(38);

        showP13.setOnAction(event -> {
            if (pathP13.isVisible()) {
                pathP13.setVisible(false);
                showP13.setGraphic(imageViewShowP13);
            } else {
                pathP13.setVisible(true);
                showP13.setGraphic(imageViewHideP13);
            } // fim do if else
        }); // fim do showP13.setOnAction

        showP13.setOnMouseEntered(e -> showP13.setCursor(javafx.scene.Cursor.HAND));
        showP13.setOnMouseExited(e -> showP13.setCursor(javafx.scene.Cursor.DEFAULT));

        showP18 = new Button();
        showP18.setFont(customFont2);

        imageViewShowP18 = new ImageView(imageShow);
        imageViewShowP18.setFitWidth(117.3);
        imageViewShowP18.setFitHeight(38);

        showP18.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        showP18.setMinSize(imageViewShowP18.getFitWidth(), imageViewShowP18.getFitHeight());
        showP18.setMaxSize(imageViewShowP18.getFitWidth(), imageViewShowP18.getFitHeight());

        showP18.setGraphic(imageViewShowP18);

        ImageView imageViewHideP18 = new ImageView(imageHide);
        imageViewHideP18.setFitWidth(117.3);
        imageViewHideP18.setFitHeight(38);

        showP18.setOnAction(event -> {
            if (pathP18.isVisible()) {
                pathP18.setVisible(false);
                showP18.setGraphic(imageViewShowP18);
            } else {
                pathP18.setVisible(true);
                showP18.setGraphic(imageViewHideP18);
            } // fim do if else
        }); // fim do showP18.setOnAction

        showP18.setOnMouseEntered(e -> showP18.setCursor(javafx.scene.Cursor.HAND));
        showP18.setOnMouseExited(e -> showP18.setCursor(javafx.scene.Cursor.DEFAULT));

        showP20 = new Button();
        showP20.setFont(customFont2);

        imageViewShowP20 = new ImageView(imageShow);
        imageViewShowP20.setFitWidth(117.3);
        imageViewShowP20.setFitHeight(38);

        showP20.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        showP20.setMinSize(imageViewShowP20.getFitWidth(), imageViewShowP20.getFitHeight());
        showP20.setMaxSize(imageViewShowP20.getFitWidth(), imageViewShowP20.getFitHeight());

        showP20.setGraphic(imageViewShowP20);

        ImageView imageViewHideP20 = new ImageView(imageHide);
        imageViewHideP20.setFitWidth(117.3);
        imageViewHideP20.setFitHeight(38);

        showP20.setOnAction(event -> {
            if (pathP20.isVisible()) {
                pathP20.setVisible(false);
                showP20.setGraphic(imageViewShowP20);
            } else {
                pathP20.setVisible(true);
                showP20.setGraphic(imageViewHideP20);
            } // fim do if else
        }); // fim do showP20.setOnAction

        showP20.setOnMouseEntered(e -> showP20.setCursor(javafx.scene.Cursor.HAND));
        showP20.setOnMouseExited(e -> showP20.setCursor(javafx.scene.Cursor.DEFAULT));

        showP24 = new Button();
        showP24.setFont(customFont2);

        imageViewShowP24 = new ImageView(imageShow);
        imageViewShowP24.setFitWidth(117.3);
        imageViewShowP24.setFitHeight(38);

        showP24.setStyle("-fx-padding: 0; -fx-background-color: transparent;");
        showP24.setMinSize(imageViewShowP24.getFitWidth(), imageViewShowP24.getFitHeight());
        showP24.setMaxSize(imageViewShowP24.getFitWidth(), imageViewShowP24.getFitHeight());

        showP24.setGraphic(imageViewShowP24);

        ImageView imageViewHideP24 = new ImageView(imageHide);
        imageViewHideP24.setFitWidth(117.3);
        imageViewHideP24.setFitHeight(38);

        showP24.setOnAction(event -> {
            if (pathP24.isVisible()) {
                pathP24.setVisible(false);
                showP24.setGraphic(imageViewShowP24);
            } else {
                pathP24.setVisible(true);
                showP24.setGraphic(imageViewHideP24);
            } // fim do if else
        }); // fim do showP24.setOnAction

        showP24.setOnMouseEntered(e -> showP24.setCursor(javafx.scene.Cursor.HAND));
        showP24.setOnMouseExited(e -> showP24.setCursor(javafx.scene.Cursor.DEFAULT));

        HBox buttonsP1 = new HBox(pauseP1, showP1);
        buttonsP1.setAlignment(Pos.CENTER);
        HBox buttonsP5 = new HBox(pauseP5, showP5);
        buttonsP5.setAlignment(Pos.CENTER);
        HBox buttonsP8 = new HBox(pauseP8, showP8);
        buttonsP8.setAlignment(Pos.CENTER);
        HBox buttonsP10 = new HBox(pauseP10, showP10);
        buttonsP10.setAlignment(Pos.CENTER);
        HBox buttonsP13 = new HBox(pauseP13, showP13);
        buttonsP13.setAlignment(Pos.CENTER);
        HBox buttonsP18 = new HBox(pauseP18, showP18);
        buttonsP18.setAlignment(Pos.CENTER);
        HBox buttonsP20 = new HBox(pauseP20, showP20);
        buttonsP20.setAlignment(Pos.CENTER);
        HBox buttonsP24 = new HBox(pauseP24, showP24);
        buttonsP24.setAlignment(Pos.CENTER);

        Text textP1 = new Text("Caminho 1");
        textP1.setFont(customFont);
        textP1.setFill(Color.WHITE);
        Text textP5 = new Text("Caminho 5");
        textP5.setFont(customFont);
        textP5.setFill(Color.WHITE);
        Text textP8 = new Text("Caminho 8");
        textP8.setFont(customFont);
        textP8.setFill(Color.WHITE);
        Text textP10 = new Text("Caminho 10");
        textP10.setFont(customFont);
        textP10.setFill(Color.WHITE);
        Text textP13 = new Text("Caminho 13");
        textP13.setFont(customFont);
        textP13.setFill(Color.WHITE);
        Text textP18 = new Text("Caminho 18");
        textP18.setFont(customFont);
        textP18.setFill(Color.WHITE);
        Text textP20 = new Text("Caminho 20");
        textP20.setFont(customFont);
        textP20.setFill(Color.WHITE);
        Text textP24 = new Text("Caminho 24");
        textP24.setFont(customFont);
        textP24.setFill(Color.WHITE);

        VBox vboxP1 = new VBox(textP1, buttonsP1, sliderP1);
        vboxP1.setSpacing(10);
        vboxP1.setAlignment(Pos.CENTER);
        VBox vboxP5 = new VBox(textP5, buttonsP5, sliderP5);
        vboxP5.setSpacing(10);
        vboxP5.setAlignment(Pos.CENTER);
        VBox vboxP8 = new VBox(textP8, buttonsP8, sliderP8);
        vboxP8.setSpacing(10);
        vboxP8.setAlignment(Pos.CENTER);
        VBox vboxP10 = new VBox(textP10, buttonsP10, sliderP10);
        vboxP10.setSpacing(10);
        vboxP10.setAlignment(Pos.CENTER);
        VBox vboxP13 = new VBox(textP13, buttonsP13, sliderP13);
        vboxP13.setSpacing(10);
        vboxP13.setAlignment(Pos.CENTER);
        VBox vboxP18 = new VBox(textP18, buttonsP18, sliderP18);
        vboxP18.setSpacing(10);
        vboxP18.setAlignment(Pos.CENTER);
        VBox vboxP20 = new VBox(textP20, buttonsP20, sliderP20);
        vboxP20.setSpacing(10);
        vboxP20.setAlignment(Pos.CENTER);
        VBox vboxP24 = new VBox(textP24, buttonsP24, sliderP24);
        vboxP24.setSpacing(10);
        vboxP24.setAlignment(Pos.CENTER);

        vboxP1.setTranslateX(650);
        vboxP1.setTranslateY(100);

        vboxP5.setTranslateX(650);
        vboxP5.setTranslateY(250);

        vboxP8.setTranslateX(650);
        vboxP8.setTranslateY(400);

        vboxP10.setTranslateX(645);
        vboxP10.setTranslateY(550);

        vboxP13.setTranslateX(930);
        vboxP13.setTranslateY(100);

        vboxP18.setTranslateX(930);
        vboxP18.setTranslateY(250);

        vboxP20.setTranslateX(930);
        vboxP20.setTranslateY(400);

        vboxP24.setTranslateX(930);
        vboxP24.setTranslateY(550);

        pacman.setCenterX(-100);
        pacman.setCenterY(-100);

        root.getChildren().addAll(pacman, blackCircle, redCircle, blueCircle, pinkCircle, purpleCircle, orangeCircle,
                greenCircle, whiteCircle, vboxP1, vboxP5, vboxP8, vboxP10, vboxP13, vboxP18, vboxP20, vboxP24,
                hboxMenu, scoreText);

        threadInfo = new ThreadInfo(root, pacman, blackCircle, blueCircle, pinkCircle, redCircle,
                purpleCircle, orangeCircle, greenCircle, whiteCircle, pathAll, this);

        threadInfo.start();

        try {
            threadInfo.join();
        } catch (InterruptedException e1) {

            e1.printStackTrace();
        } // fim do try catch

        t = new P1(1, blackCircle, threadInfo, sliderP1);
        t1 = new P5(2, redCircle, threadInfo, sliderP5);
        t2 = new P8(3, blueCircle, threadInfo, sliderP8);
        t3 = new P10(4, pinkCircle, threadInfo, sliderP10);
        t4 = new P13(5, purpleCircle, threadInfo, sliderP13);
        t5 = new P18(6, orangeCircle, threadInfo, sliderP18);
        t6 = new P20(7, greenCircle, threadInfo, sliderP20);
        t7 = new P24(8, whiteCircle, threadInfo, sliderP24);

        t.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

        Image background = new Image("/img/background.png");
        BackgroundImage backgroundImg = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,

                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                        false, false, false, true));

        root.setBackground(new Background(backgroundImg));

        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);

        primaryStage.show();
    } // fim do metodo start
    
    /* ***************************************************************
    * Metodo: createFoodPointsGrid
    * Funcao: cria os pontos de comida
    * Parametros: void
    * Retorno: Pane
    *************************************************************** */
    private Pane createFoodPointsGrid() {
        grid = new Pane();
        Random random = new Random();
        Set<Point2D> usedCoordinatesSmall = new HashSet<>();
        usedCoordinatesBig = new HashSet<>();
        pathAll.setStrokeWidth(1);

        for (int i = 0; i < 20; i++) {
            double x, y;
            Point2D newCoordinate;

            do {
                x = random.nextInt(501) + 100;
                y = random.nextInt(501) + 100;
                newCoordinate = new Point2D(x, y);
            } while (!isCoordinateValid(newCoordinate, usedCoordinatesSmall, 20.0));

            usedCoordinatesSmall.add(newCoordinate);

            Circle foodPoint = createFoodPoint(5);
            grid.getChildren().add(foodPoint);
            foodPoint.setTranslateX(x);
            foodPoint.setTranslateY(y);
        } // fim do for

        for (int i = 0; i < 5; i++) {
            double x, y;
            Point2D newCoordinate;

            do {
                x = random.nextInt(501) + 100;
                y = random.nextInt(501) + 100;
                newCoordinate = new Point2D(x, y);
            } while (!isCoordinateValid(newCoordinate, usedCoordinatesSmall, 50.0));

            usedCoordinatesSmall.add(newCoordinate);
            usedCoordinatesBig.add(newCoordinate);

            Circle foodPoint = createFoodPoint(10);
            grid.getChildren().add(foodPoint);
            foodPoint.setTranslateX(x);
            foodPoint.setTranslateY(y);
        } // fim do for

        pathAll.setStrokeWidth(10);
        return grid;
    } // fim do metodo createFoodPointsGrid

    /* ***************************************************************
    * Metodo: isCoordinateValid
    * Funcao: verifica se a coordenada eh valida
    * Parametros: Point2D newCoordinate, Set<Point2D> usedCoordinates, double circleRadius
    * Retorno: boolean
    *************************************************************** */
    private boolean isCoordinateValid(Point2D newCoordinate, Set<Point2D> usedCoordinates, double circleRadius) {
        for (Point2D usedCoordinate : usedCoordinates) {
            if (usedCoordinate.distance(newCoordinate) < 2 * circleRadius) {
                return false;
            } // fim do if
        } // fim do for
        return pathAll.contains(newCoordinate);
    } // fim do metodo isCoordinateValid

    /* ***************************************************************
    * Metodo: createFoodPoint
    * Funcao: cria o ponto de comida
    * Parametros: int x
    * Retorno: Circle
    *************************************************************** */
    private Circle createFoodPoint(int x) {
        Circle foodPoint = new Circle(x, Color.WHITE);
        return foodPoint;
    } // fim do metodo createFoodPoint

    /* ***************************************************************
    * Metodo: checkAndRemoveCollision
    * Funcao: verifica e remove o circulo caso colida
    * Parametros: double x, double y
    * Retorno: void
    *************************************************************** */
    public void checkAndRemoveCollision(double x, double y) {
        Circle foodPointToRemove = null;

        for (Node node : grid.getChildren()) {
            if (node instanceof Circle) {
                Circle foodPoint = (Circle) node;
                if (checkCollision(x, y, foodPoint)) {
                    foodPointToRemove = foodPoint;
                    break;
                } // fim do if
            } // fim do if
        } // fim do for

        if (foodPointToRemove != null) {
            Point2D circlePosition = new Point2D(foodPointToRemove.getTranslateX() + foodPointToRemove.getCenterX(),
                    foodPointToRemove.getTranslateY() + foodPointToRemove.getCenterY());

            if (usedCoordinatesBig.contains(circlePosition)) {
                this.score += 50;

                scheduler = Executors.newScheduledThreadPool(1);
                t.p.normal = false;

                t.normal = false;
                t1.normal = false;
                t2.normal = false;
                t3.normal = false;
                t4.normal = false;
                t5.normal = false;
                t6.normal = false;
                t7.normal = false;

                blackCircle.setFill(imagePattern0);
                redCircle.setFill(imagePattern0);
                blueCircle.setFill(imagePattern0);
                pinkCircle.setFill(imagePattern0);
                purpleCircle.setFill(imagePattern0);
                orangeCircle.setFill(imagePattern0);
                greenCircle.setFill(imagePattern0);
                whiteCircle.setFill(imagePattern0);

                schedule = true;

                if (scheduledFuture != null && !scheduledFuture.isDone()) {
                    scheduledFuture.cancel(true);
                } // fim do if

                scheduledFuture = scheduler.schedule(() -> {
                    if (schedule) {
                        t.p.normal = true;
                        blackCircle.setFill(imagePattern4);
                        redCircle.setFill(imagePattern);
                        blueCircle.setFill(imagePattern2);
                        pinkCircle.setFill(imagePattern3);
                        purpleCircle.setFill(imagePattern5);
                        orangeCircle.setFill(imagePattern6);
                        greenCircle.setFill(imagePattern7);
                        whiteCircle.setFill(imagePattern8);

                        t.normal = true;
                        t1.normal = true;
                        t2.normal = true;
                        t3.normal = true;
                        t4.normal = true;
                        t5.normal = true;
                        t6.normal = true;
                        t7.normal = true;
                    } // fim do if

                }, 8, TimeUnit.SECONDS);

            } else {
                this.score += 10;
            } // fim do if else

            grid.getChildren().remove(foodPointToRemove);
            scoreText.setText("Score " + this.score);
        } // fim do if
    } // fim do metodo checkAndRemoveCollision

    /* ***************************************************************
    * Metodo: checkCollision
    * Funcao: verifica se houve colisao
    * Parametros: double x, double y, Circle foodPoint
    * Retorno: boolean
    *************************************************************** */
    public boolean checkCollision(double x, double y, Circle foodPoint) {
        double circleX = foodPoint.getTranslateX() + foodPoint.getCenterX();
        double circleY = foodPoint.getTranslateY() + foodPoint.getCenterY();
        double circleRadius = foodPoint.getRadius();

        double distance = Math.sqrt(Math.pow(x - circleX, 2) + Math.pow(y - circleY, 2));

        return distance < circleRadius;
    } // fim do metodo checkCollision

    /* ***************************************************************
    * Metodo: dead
    * Funcao: mata o pacman
    * Parametros: void
    * Retorno: void
    *************************************************************** */
    public void dead() {
        grid.getChildren().clear();

        t.p.resetAllSemaphores();

        t.p.setInterrupt();
        t.setInterrupt();
        t1.setInterrupt();
        t2.setInterrupt();
        t3.setInterrupt();
        t4.setInterrupt();
        t5.setInterrupt();
        t6.setInterrupt();
        t7.setInterrupt();

        threadInfo.interrupt();

        t.interrupt();
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
        t4.interrupt();
        t5.interrupt();
        t6.interrupt();
        t7.interrupt();

        try {
            t.join();
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
        } catch (InterruptedException e) {

        } // fim do try catch

        redCircle.setX(-12.5);
        redCircle.setY(-12.5);
        blueCircle.setX(-12.5);
        blueCircle.setY(-12.5);
        pinkCircle.setX(-12.5);
        pinkCircle.setY(-12.5);
        purpleCircle.setX(-12.5);
        purpleCircle.setY(-12.5);
        orangeCircle.setX(-12.5);
        orangeCircle.setY(-12.5);
        greenCircle.setX(-12.5);
        greenCircle.setY(-12.5);
        whiteCircle.setX(-12.5);
        whiteCircle.setY(-12.5);
        blackCircle.setX(-12.5);
        blackCircle.setY(-12.5);

        greenCircle.setTranslateX(0);
        greenCircle.setTranslateY(0);
        pinkCircle.setTranslateX(0);
        pinkCircle.setTranslateY(0);
        purpleCircle.setTranslateX(0);
        purpleCircle.setTranslateY(0);
        redCircle.setTranslateX(0);
        redCircle.setTranslateY(0);
        blueCircle.setTranslateX(0);
        blueCircle.setTranslateY(0);
        orangeCircle.setTranslateX(0);
        orangeCircle.setTranslateY(0);
        whiteCircle.setTranslateX(0);
        whiteCircle.setTranslateY(0);
        blackCircle.setTranslateX(0);
        blackCircle.setTranslateY(0);
    } // fim do metodo dead

    /* ***************************************************************
    * Metodo: kill
    * Funcao: mata o fantasma
    * Parametros: Rectangle rec
    * Retorno: void
    *************************************************************** */
    public void kill(Rectangle rec) {
        if (rec == redCircle) {
            t1.setInterrupt();
            redCircle.setTranslateX(0);
            redCircle.setTranslateY(0);
        } else if (rec == blueCircle) {
            t2.setInterrupt();
            blueCircle.setTranslateX(0);
            blueCircle.setTranslateY(0);
        } else if (rec == pinkCircle) {
            t3.setInterrupt();
            pinkCircle.setTranslateX(0);
            pinkCircle.setTranslateY(0);
        } else if (rec == purpleCircle) {
            t4.setInterrupt();
            purpleCircle.setTranslateX(0);
            purpleCircle.setTranslateY(0);
        } else if (rec == orangeCircle) {
            t5.setInterrupt();
            orangeCircle.setTranslateX(0);
            orangeCircle.setTranslateY(0);
        } else if (rec == greenCircle) {
            t6.setInterrupt();
            greenCircle.setTranslateX(0);
            greenCircle.setTranslateY(0);
        } else if (rec == whiteCircle) {
            t7.setInterrupt();
            whiteCircle.setTranslateX(0);
            whiteCircle.setTranslateY(0);
        } else if (rec == blackCircle) {
            t.setInterrupt();
            blackCircle.setTranslateX(0);
            blackCircle.setTranslateY(0);
        } // fim do if else
    } // fim do metodo kill

    /* ***************************************************************
    * Metodo: main
    * Funcao: metodo principal
    * Parametros: String[] args
    * Retorno: void
    *************************************************************** */
    public static void main(String[] args) {
        launch(args);
    } // fim do metodo main
} // fim da classe Principal
