/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 02/11/2023
* Ultima alteracao.: 12/11/2023
* Nome.............: Principal.java
* Funcao...........: Classe que contem o metodo de start da aplicacao.
*************************************************************** */

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Principal extends Application {
  private Buffer b; // Buffer
  private boolean exitLeitor1; // Variavel de controle de saida da thread leitor 1
  private boolean exitLeitor2; // Variavel de controle de saida da thread leitor 2
  private boolean exitLeitor3; // Variavel de controle de saida da thread leitor 3
  private boolean exitLeitor4; // Variavel de controle de saida da thread leitor 4
  private boolean exitLeitor5; // Variavel de controle de saida da thread leitor 5
  private boolean exitEscritor1; // Variavel de controle de saida da thread escritor 1
  private boolean exitEscritor2; // Variavel de controle de saida da thread escritor 2
  private boolean exitEscritor3; // Variavel de controle de saida da thread escritor 3
  private boolean exitEscritor4; // Variavel de controle de saida da thread escritor 4
  private boolean exitEscritor5; // Variavel de controle de saida da thread escritor 5
  private boolean exitLeitorAll; // Variavel de controle de saida de todas as threads leitoras
  private boolean exitEscritorAll; // Variavel de controle de saida de todas as threads escritoras
  private boolean clickCheck; // Variavel de controle do clique no bau
  private int delayLeitorInicialLer = 20000; // Delay inicial da thread leitora
  private int delayLeitorInicialUtilizar = 50000; // Delay inicial da thread leitora
  private int delayEscritorInicialObter = 100000; // Delay inicial da thread escritora
  private int delayEscritoInicialEscrever = 20000; // Delay inicial da thread escritora
  private int firstTimeCheck; // Variavel de controle da primeira vez que foi para tela inicial
  private PathTransition pathTransitionEscritorIda; // Variavel de controle da animacao do escritor
  private PathTransition pathTransitionEscritorVolta; // Variavel de controle da animacao do escritor
  private PathTransition pathTransitionLeitorIda1; // Variavel de controle da animacao do leitor 1
  private PathTransition pathTransitionLeitorVolta1; // Variavel de controle da animacao do leitor 1
  private PathTransition pathTransitionLeitorIda2; // Variavel de controle da animacao do leitor 2
  private PathTransition pathTransitionLeitorVolta2; // Variavel de controle da animacao do leitor 2
  private PathTransition pathTransitionLeitorIda3; // Variavel de controle da animacao do leitor 3
  private PathTransition pathTransitionLeitorVolta3; // Variavel de controle da animacao do leitor 3
  private PathTransition pathTransitionLeitorIda4; // Variavel de controle da animacao do leitor 4
  private PathTransition pathTransitionLeitorVolta4; // Variavel de controle da animacao do leitor 4
  private PathTransition pathTransitionLeitorIda5; // Variavel de controle da animacao do leitor 5
  private PathTransition pathTransitionLeitorVolta5; // Variavel de controle da animacao do leitor 5
  private ArrayList<PathTransition> pathTransitionLeitor; // Variavel de controle da animacao do leitor

  /* ***************************************************************
  * Metodo: start
  * Funcao: metodo de start da aplicacao
  * Parametros: Stage primaryStage
  * Retorno: void
  *************************************************************** */
  @Override
  public void start(Stage primaryStage) {
    Font customFont = Font.loadFont(getClass().getResourceAsStream("/img/MinecraftRegular-Bmg3.otf"), 16);

    Stage startStage = new Stage();

    Image icon = new Image("/img/icon.png");

    startStage.setTitle("Minecraft");
    primaryStage.setTitle("Minecraft");

    startStage.getIcons().add(icon);
    primaryStage.getIcons().add(icon);

    Image opcoes = new Image("/img/opcoes.png");
    ImageView opcoesView = new ImageView(opcoes);
    opcoesView.setTranslateX(370);
    opcoesView.setTranslateY(530);
    opcoesView.setFitWidth(260);
    opcoesView.setFitHeight(55);

    FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(3000), opcoesView);
    fadeTransition1.setFromValue(0.0);
    fadeTransition1.setToValue(1.0);

    Image multiplayer = new Image("/img/multiplayer.png");
    ImageView multiplayerView = new ImageView(multiplayer);
    multiplayerView.setFitWidth(540);
    multiplayerView.setFitHeight(55);
    multiplayerView.setTranslateX(370);
    multiplayerView.setTranslateY(380);

    FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(3000), multiplayerView);
    fadeTransition3.setFromValue(0.0);
    fadeTransition3.setToValue(1.0);

    Image realms = new Image("/img/realms.png");
    ImageView realmsView = new ImageView(realms);
    realmsView.setFitWidth(540);
    realmsView.setFitHeight(55);
    realmsView.setTranslateX(370);
    realmsView.setTranslateY(450);

    FadeTransition fadeTransition4 = new FadeTransition(Duration.millis(3000), realmsView);
    fadeTransition4.setFromValue(0.0);
    fadeTransition4.setToValue(1.0);

    Image minecraftLogo = new Image("/img/minecraftlogo.png");
    ImageView minecraftLogoView = new ImageView(minecraftLogo);
    minecraftLogoView.setFitWidth(742.6);
    minecraftLogoView.setFitHeight(190);
    minecraftLogoView.setTranslateX(260);
    minecraftLogoView.setTranslateY(60);

    FadeTransition fadeTransition0 = new FadeTransition(Duration.millis(3000), minecraftLogoView);
    fadeTransition0.setFromValue(0.0);
    fadeTransition0.setToValue(1.0);

    Image imageButton = new Image("/img/startbutton.png");
    ImageView imageButtonView = new ImageView(imageButton);
    imageButtonView.setOpacity(0.0);
    imageButtonView.setFitWidth(540);
    imageButtonView.setFitHeight(55);
    imageButtonView.setTranslateX(370);
    imageButtonView.setTranslateY(310);

    FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000), imageButtonView);
    fadeTransition.setFromValue(0.0);
    fadeTransition.setToValue(1.0);

    Image imageLeaveButton = new Image("/img/leavebutton.png");
    ImageView imageLeaveButtonView = new ImageView(imageLeaveButton);
    imageLeaveButtonView.setFitWidth(260);
    imageLeaveButtonView.setFitHeight(55);
    imageLeaveButtonView.setTranslateX(650);
    imageLeaveButtonView.setTranslateY(530);

    FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(3000), imageLeaveButtonView);
    fadeTransition2.setFromValue(0.0);
    fadeTransition2.setToValue(1.0);

    imageLeaveButtonView.setOnMouseEntered(event -> {
      imageLeaveButtonView.setCursor(Cursor.HAND);
      imageLeaveButtonView.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); -fx-transition: all 0.3s;");
      imageLeaveButtonView.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    imageLeaveButtonView.setOnMouseExited(event -> {
      imageLeaveButtonView.setCursor(Cursor.DEFAULT);
      imageLeaveButtonView.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); -fx-transition: all 0.3s;");
      imageLeaveButtonView.setOpacity(1.0);
    }); // fim do setOnMouseExited

    imageLeaveButtonView.setOnMouseClicked(event -> {
      System.exit(0);
    }); // fim do setOnMouseClicked

    Pane startRoot = new Pane();

    Image startBackground2 = new Image("/img/startbackground2.gif");
    BackgroundImage startBackgroundImg2 = new BackgroundImage(startBackground2,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,

        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
            false, false, false, true));

    Duration duration = Duration.seconds(6);
    KeyFrame keyFrame = new KeyFrame(duration, event -> {
      startRoot.setBackground(new Background(startBackgroundImg2));
    }); // fim do keyFrame

    Timeline timeline = new Timeline(keyFrame);
    timeline.setCycleCount(1);

    startStage.setOnShown(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent event) {
        Image startBackground = new Image("/img/startbackground.gif");
        BackgroundImage startBackgroundImg = new BackgroundImage(startBackground,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,

            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                false, false, false, true));

        startRoot.setBackground(new Background(startBackgroundImg));
        timeline.play();
        fadeTransition.play();
        fadeTransition2.play();
        fadeTransition0.play();
        fadeTransition1.play();
        fadeTransition3.play();
        fadeTransition4.play();
      } // fim do handle
    }); // fim do setOnShown

    startRoot.getChildren().addAll(imageButtonView, imageLeaveButtonView, minecraftLogoView, multiplayerView,
        realmsView, opcoesView);

    imageButtonView.setOnMouseEntered(event -> {
      imageButtonView.setCursor(Cursor.HAND);
      imageButtonView.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); -fx-transition: all 0.3s;");
      imageButtonView.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    imageButtonView.setOnMouseExited(event -> {
      imageButtonView.setCursor(Cursor.DEFAULT);
      imageButtonView.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); -fx-transition: all 0.3s;");
      imageButtonView.setOpacity(1.0);
    }); // fim do setOnMouseExited

    Scene startScene = new Scene(startRoot, 1280, 720);
    startStage.setScene(startScene);
    startStage.setResizable(false);

    Image gui = new Image("/img/gui.png");
    ImageView guiView = new ImageView(gui);
    guiView.setFitWidth(500);
    guiView.setFitHeight(400);
    guiView.setTranslateX(200);
    guiView.setTranslateY(10);

    GridPane gridPane = new GridPane();
    guiView.setVisible(false);
    gridPane.setVisible(false);
    gridPane.setPrefWidth(460);
    gridPane.setPrefHeight(330);
    gridPane.setTranslateX(220);
    gridPane.setTranslateY(64);

    ColumnConstraints col1 = new ColumnConstraints(50.5);
    ColumnConstraints col2 = new ColumnConstraints(50.5);
    ColumnConstraints col3 = new ColumnConstraints(50.5);
    ColumnConstraints col4 = new ColumnConstraints(50.5);
    ColumnConstraints col5 = new ColumnConstraints(50.5);
    ColumnConstraints col6 = new ColumnConstraints(50.5);
    ColumnConstraints col7 = new ColumnConstraints(50.5);
    ColumnConstraints col8 = new ColumnConstraints(50.5);
    ColumnConstraints col9 = new ColumnConstraints(50.5);

    RowConstraints row1 = new RowConstraints(54);
    RowConstraints row2 = new RowConstraints(54);
    RowConstraints row3 = new RowConstraints(54);
    RowConstraints row4 = new RowConstraints(54);
    RowConstraints row5 = new RowConstraints(54);
    RowConstraints row6 = new RowConstraints(54);

    gridPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);
    gridPane.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6);

    Rectangle cell00 = new Rectangle(50, 50);
    cell00.setFill(Color.TRANSPARENT);
    Rectangle cell01 = new Rectangle(50, 50);
    cell01.setFill(Color.TRANSPARENT);
    Rectangle cell02 = new Rectangle(50, 50);
    cell02.setFill(Color.TRANSPARENT);
    Rectangle cell03 = new Rectangle(50, 50);
    cell03.setFill(Color.TRANSPARENT);
    Rectangle cell04 = new Rectangle(50, 50);
    cell04.setFill(Color.TRANSPARENT);
    Rectangle cell05 = new Rectangle(50, 50);
    cell05.setFill(Color.TRANSPARENT);

    Rectangle cell10 = new Rectangle(50, 50);
    cell10.setFill(Color.TRANSPARENT);
    Rectangle cell11 = new Rectangle(50, 50);
    cell11.setFill(Color.TRANSPARENT);
    Rectangle cell12 = new Rectangle(50, 50);
    cell12.setFill(Color.TRANSPARENT);
    Rectangle cell13 = new Rectangle(50, 50);
    cell13.setFill(Color.TRANSPARENT);
    Rectangle cell14 = new Rectangle(50, 50);
    cell14.setFill(Color.TRANSPARENT);
    Rectangle cell15 = new Rectangle(50, 50);
    cell15.setFill(Color.TRANSPARENT);

    Rectangle cell20 = new Rectangle(50, 50);
    cell20.setFill(Color.TRANSPARENT);
    Rectangle cell21 = new Rectangle(50, 50);
    cell21.setFill(Color.TRANSPARENT);
    Rectangle cell22 = new Rectangle(50, 50);
    cell22.setFill(Color.TRANSPARENT);
    Rectangle cell23 = new Rectangle(50, 50);
    cell23.setFill(Color.TRANSPARENT);
    Rectangle cell24 = new Rectangle(50, 50);
    cell24.setFill(Color.TRANSPARENT);
    Rectangle cell25 = new Rectangle(50, 50);
    cell25.setFill(Color.TRANSPARENT);

    Rectangle cell30 = new Rectangle(50, 50);
    cell30.setFill(Color.TRANSPARENT);
    Rectangle cell31 = new Rectangle(50, 50);
    cell31.setFill(Color.TRANSPARENT);
    Rectangle cell32 = new Rectangle(50, 50);
    cell32.setFill(Color.TRANSPARENT);
    Rectangle cell33 = new Rectangle(50, 50);
    cell33.setFill(Color.TRANSPARENT);
    Rectangle cell34 = new Rectangle(50, 50);
    cell34.setFill(Color.TRANSPARENT);
    Rectangle cell35 = new Rectangle(50, 50);
    cell35.setFill(Color.TRANSPARENT);

    Rectangle cell40 = new Rectangle(50, 50);
    cell40.setFill(Color.TRANSPARENT);
    Rectangle cell41 = new Rectangle(50, 50);
    cell41.setFill(Color.TRANSPARENT);
    Rectangle cell42 = new Rectangle(50, 50);
    cell42.setFill(Color.TRANSPARENT);
    Rectangle cell43 = new Rectangle(50, 50);
    cell43.setFill(Color.TRANSPARENT);
    Rectangle cell44 = new Rectangle(50, 50);
    cell44.setFill(Color.TRANSPARENT);
    Rectangle cell45 = new Rectangle(50, 50);
    cell45.setFill(Color.TRANSPARENT);

    Rectangle cell50 = new Rectangle(50, 50);
    cell50.setFill(Color.TRANSPARENT);
    Rectangle cell51 = new Rectangle(50, 50);
    cell51.setFill(Color.TRANSPARENT);
    Rectangle cell52 = new Rectangle(50, 50);
    cell52.setFill(Color.TRANSPARENT);
    Rectangle cell53 = new Rectangle(50, 50);
    cell53.setFill(Color.TRANSPARENT);
    Rectangle cell54 = new Rectangle(50, 50);
    cell54.setFill(Color.TRANSPARENT);
    Rectangle cell55 = new Rectangle(50, 50);
    cell55.setFill(Color.TRANSPARENT);

    Rectangle cell60 = new Rectangle(50, 50);
    cell60.setFill(Color.TRANSPARENT);
    Rectangle cell61 = new Rectangle(50, 50);
    cell61.setFill(Color.TRANSPARENT);
    Rectangle cell62 = new Rectangle(50, 50);
    cell62.setFill(Color.TRANSPARENT);
    Rectangle cell63 = new Rectangle(50, 50);
    cell63.setFill(Color.TRANSPARENT);
    Rectangle cell64 = new Rectangle(50, 50);
    cell64.setFill(Color.TRANSPARENT);
    Rectangle cell65 = new Rectangle(50, 50);
    cell65.setFill(Color.TRANSPARENT);

    Rectangle cell70 = new Rectangle(50, 50);
    cell70.setFill(Color.TRANSPARENT);
    Rectangle cell71 = new Rectangle(50, 50);
    cell71.setFill(Color.TRANSPARENT);
    Rectangle cell72 = new Rectangle(50, 50);
    cell72.setFill(Color.TRANSPARENT);
    Rectangle cell73 = new Rectangle(50, 50);
    cell73.setFill(Color.TRANSPARENT);
    Rectangle cell74 = new Rectangle(50, 50);
    cell74.setFill(Color.TRANSPARENT);
    Rectangle cell75 = new Rectangle(50, 50);
    cell75.setFill(Color.TRANSPARENT);

    Rectangle cell80 = new Rectangle(50, 50);
    cell80.setFill(Color.TRANSPARENT);
    Rectangle cell81 = new Rectangle(50, 50);
    cell81.setFill(Color.TRANSPARENT);
    Rectangle cell82 = new Rectangle(50, 50);
    cell82.setFill(Color.TRANSPARENT);
    Rectangle cell83 = new Rectangle(50, 50);
    cell83.setFill(Color.TRANSPARENT);
    Rectangle cell84 = new Rectangle(50, 50);
    cell84.setFill(Color.TRANSPARENT);
    Rectangle cell85 = new Rectangle(50, 50);
    cell85.setFill(Color.TRANSPARENT);

    ArrayList<Rectangle> cellRectangles = new ArrayList<>();
    cellRectangles.add(cell00);
    cellRectangles.add(cell10);
    cellRectangles.add(cell20);
    cellRectangles.add(cell30);
    cellRectangles.add(cell40);
    cellRectangles.add(cell50);
    cellRectangles.add(cell60);
    cellRectangles.add(cell70);
    cellRectangles.add(cell80);
    cellRectangles.add(cell01);
    cellRectangles.add(cell11);
    cellRectangles.add(cell21);
    cellRectangles.add(cell31);
    cellRectangles.add(cell41);
    cellRectangles.add(cell51);
    cellRectangles.add(cell61);
    cellRectangles.add(cell71);
    cellRectangles.add(cell81);
    cellRectangles.add(cell02);
    cellRectangles.add(cell12);
    cellRectangles.add(cell22);
    cellRectangles.add(cell32);
    cellRectangles.add(cell42);
    cellRectangles.add(cell52);
    cellRectangles.add(cell62);
    cellRectangles.add(cell72);
    cellRectangles.add(cell82);
    cellRectangles.add(cell03);
    cellRectangles.add(cell13);
    cellRectangles.add(cell23);
    cellRectangles.add(cell33);
    cellRectangles.add(cell43);
    cellRectangles.add(cell53);
    cellRectangles.add(cell63);
    cellRectangles.add(cell73);
    cellRectangles.add(cell83);
    cellRectangles.add(cell04);
    cellRectangles.add(cell14);
    cellRectangles.add(cell24);
    cellRectangles.add(cell34);
    cellRectangles.add(cell44);
    cellRectangles.add(cell54);
    cellRectangles.add(cell64);
    cellRectangles.add(cell74);
    cellRectangles.add(cell84);
    cellRectangles.add(cell05);
    cellRectangles.add(cell15);
    cellRectangles.add(cell25);
    cellRectangles.add(cell35);
    cellRectangles.add(cell45);
    cellRectangles.add(cell55);
    cellRectangles.add(cell65);
    cellRectangles.add(cell75);
    cellRectangles.add(cell85);

    gridPane.add(cell00, 0, 0);
    gridPane.add(cell01, 0, 1);
    gridPane.add(cell02, 0, 2);
    gridPane.add(cell03, 0, 3);
    gridPane.add(cell04, 0, 4);
    gridPane.add(cell05, 0, 5);
    gridPane.add(cell10, 1, 0);
    gridPane.add(cell11, 1, 1);
    gridPane.add(cell12, 1, 2);
    gridPane.add(cell13, 1, 3);
    gridPane.add(cell14, 1, 4);
    gridPane.add(cell15, 1, 5);
    gridPane.add(cell20, 2, 0);
    gridPane.add(cell21, 2, 1);
    gridPane.add(cell22, 2, 2);
    gridPane.add(cell23, 2, 3);
    gridPane.add(cell24, 2, 4);
    gridPane.add(cell25, 2, 5);
    gridPane.add(cell30, 3, 0);
    gridPane.add(cell31, 3, 1);
    gridPane.add(cell32, 3, 2);
    gridPane.add(cell33, 3, 3);
    gridPane.add(cell34, 3, 4);
    gridPane.add(cell35, 3, 5);
    gridPane.add(cell40, 4, 0);
    gridPane.add(cell41, 4, 1);
    gridPane.add(cell42, 4, 2);
    gridPane.add(cell43, 4, 3);
    gridPane.add(cell44, 4, 4);
    gridPane.add(cell45, 4, 5);
    gridPane.add(cell50, 5, 0);
    gridPane.add(cell51, 5, 1);
    gridPane.add(cell52, 5, 2);
    gridPane.add(cell53, 5, 3);
    gridPane.add(cell54, 5, 4);
    gridPane.add(cell55, 5, 5);
    gridPane.add(cell60, 6, 0);
    gridPane.add(cell61, 6, 1);
    gridPane.add(cell62, 6, 2);
    gridPane.add(cell63, 6, 3);
    gridPane.add(cell64, 6, 4);
    gridPane.add(cell65, 6, 5);
    gridPane.add(cell70, 7, 0);
    gridPane.add(cell71, 7, 1);
    gridPane.add(cell72, 7, 2);
    gridPane.add(cell73, 7, 3);
    gridPane.add(cell74, 7, 4);
    gridPane.add(cell75, 7, 5);
    gridPane.add(cell80, 8, 0);
    gridPane.add(cell81, 8, 1);
    gridPane.add(cell82, 8, 2);
    gridPane.add(cell83, 8, 3);
    gridPane.add(cell84, 8, 4);
    gridPane.add(cell85, 8, 5);

    Image balloonNotification = new Image("/img/bubble.png");
    ImageView balloonViewNotification = new ImageView(balloonNotification);
    balloonViewNotification.setFitWidth(300);
    balloonViewNotification.setFitHeight(150);
    balloonViewNotification.setTranslateX(200);
    balloonViewNotification.setTranslateY(550);

    Text textNotification1 = new Text("Clique no bau");
    textNotification1.setFont(customFont);
    textNotification1.setStyle("-fx-font-size: 30px;");
    textNotification1.setTranslateX(250);
    textNotification1.setTranslateY(570);

    Text textNotification2 = new Text("para abrir");
    textNotification2.setFont(customFont);
    textNotification2.setStyle("-fx-font-size: 30px;");
    textNotification2.setTranslateX(250);
    textNotification2.setTranslateY(600);

    StackPane textNotification = new StackPane(textNotification1, textNotification2);
    textNotification.setAlignment(Pos.CENTER);

    Text escritorLabel = new Text("Escritores");
    escritorLabel.setFont(customFont);
    escritorLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; ");
    escritorLabel.setTranslateX(1250);
    escritorLabel.setTranslateY(400);

    Text leitorLabel = new Text("Leitores");
    leitorLabel.setFont(customFont);
    leitorLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
    leitorLabel.setTranslateX(550);
    leitorLabel.setTranslateY(400);

    Image steve = new Image("/img/leitor.png");
    Image chest = new Image("/img/chest.png");
    Image apple = new Image("/img/apple.png");
    Image axe = new Image("/img/axe.png");
    Image compass = new Image("/img/compass.png");
    Image diamond = new Image("/img/diamond.png");
    Image egg = new Image("/img/egg.png");
    Image emerald = new Image("/img/emerald.png");
    Image goldenapple = new Image("/img/goldenapple.png");
    Image pickaxe = new Image("/img/pickaxe.png");
    Image sword = new Image("/img/sword.png");
    Image torch = new Image("/img/torch.png");
    Image totem = new Image("/img/totem.png");
    Image uu = new Image("/img/uu.png");

    Pane rectangle = new Pane();
    rectangle.setMaxSize(90, 90);
    rectangle.setTranslateX(845);
    rectangle.setTranslateY(554);

    ArrayList<Image> items = new ArrayList<>();
    items.add(apple);
    items.add(axe);
    items.add(compass);
    items.add(diamond);
    items.add(egg);
    items.add(emerald);
    items.add(goldenapple);
    items.add(pickaxe);
    items.add(sword);
    items.add(torch);
    items.add(totem);
    items.add(uu);

    Image balloon = new Image("/img/balloon.png");

    ImageView balloonView1 = new ImageView(balloon);
    StackPane balloonViewStackPane1 = new StackPane(balloonView1);
    balloonViewStackPane1.setTranslateX(1330);
    balloonViewStackPane1.setTranslateY(390);

    ImageView balloonView2 = new ImageView(balloon);
    StackPane balloonViewStackPane2 = new StackPane(balloonView2);
    balloonViewStackPane2.setTranslateX(1330);
    balloonViewStackPane2.setTranslateY(470);

    ImageView balloonView3 = new ImageView(balloon);
    StackPane balloonViewStackPane3 = new StackPane(balloonView3);
    balloonViewStackPane3.setTranslateX(1320);
    balloonViewStackPane3.setTranslateY(550);

    ImageView balloonView4 = new ImageView(balloon);
    StackPane balloonViewStackPane4 = new StackPane(balloonView4);
    balloonViewStackPane4.setTranslateX(1300);
    balloonViewStackPane4.setTranslateY(630);

    ImageView balloonView5 = new ImageView(balloon);
    StackPane balloonViewStackPane5 = new StackPane(balloonView5);
    balloonViewStackPane5.setTranslateX(1280);
    balloonViewStackPane5.setTranslateY(710);

    balloonViewStackPane1.setVisible(false);
    balloonViewStackPane2.setVisible(false);
    balloonViewStackPane3.setVisible(false);
    balloonViewStackPane4.setVisible(false);
    balloonViewStackPane5.setVisible(false);

    ArrayList<StackPane> balloonView = new ArrayList<>();
    balloonView.add(balloonViewStackPane1);
    balloonView.add(balloonViewStackPane2);
    balloonView.add(balloonViewStackPane3);
    balloonView.add(balloonViewStackPane4);
    balloonView.add(balloonViewStackPane5);

    ImageView chestView = new ImageView(chest);
    chestView.setFitWidth(200);
    chestView.setFitHeight(200);
    chestView.setTranslateY(620);
    chestView.setTranslateX(160);

    clickCheck = false;

    chestView.setOnMouseClicked(e -> {
      textNotification.setVisible(false);
      balloonViewNotification.setVisible(false);

      if (clickCheck == false) {
        guiView.setVisible(true);
        gridPane.setVisible(true);
        clickCheck = true;
      } else {
        guiView.setVisible(false);
        gridPane.setVisible(false);
        clickCheck = false;
      } // fim do else
    }); // fim do setOnMouseClicked

    chestView.setOnMouseEntered(e -> chestView.setCursor(Cursor.HAND));
    chestView.setOnMouseExited(e -> chestView.setCursor(Cursor.DEFAULT));

    Rectangle stevae1 = new Rectangle(1440, 980, 260, 290);
    Rectangle stevae2 = new Rectangle(1440, 980, 260, 290);
    Rectangle stevae3 = new Rectangle(1440, 980, 260, 290);
    Rectangle stevae4 = new Rectangle(1440, 980, 260, 290);
    Rectangle stevae5 = new Rectangle(1440, 980, 260, 290);
    Rectangle stevae6 = new Rectangle(1440, 980, 260, 290);
    Rectangle stevae7 = new Rectangle(1440, 980, 260, 290);
    Rectangle stevae8 = new Rectangle(1440, 980, 260, 290);
    Rectangle stevae9 = new Rectangle(1440, 980, 260, 290);
    Rectangle stevae10 = new Rectangle(1440, 980, 260, 290);

    ArrayList<Rectangle> rectangles = new ArrayList<>();
    rectangles.add(stevae5);
    rectangles.add(stevae4);
    rectangles.add(stevae3);
    rectangles.add(stevae2);
    rectangles.add(stevae1);
    rectangles.add(stevae10);
    rectangles.add(stevae9);
    rectangles.add(stevae8);
    rectangles.add(stevae7);
    rectangles.add(stevae6);

    Path path1 = new Path(
        new MoveTo(1305, 825),
        new LineTo(1125, 705));

    Path path2 = new Path(
        new MoveTo(1325, 765),
        new LineTo(1125, 705));

    Path path3 = new Path(
        new MoveTo(1345, 705),
        new LineTo(1125, 705));

    Path path4 = new Path(
        new MoveTo(1365, 645),
        new LineTo(1125, 705));

    Path path5 = new Path(
        new MoveTo(1385, 585),
        new LineTo(1125, 705));

    Path path6 = new Path(
        new MoveTo(1125, 705),
        new LineTo(1305, 825));

    Path path7 = new Path(
        new MoveTo(1125, 705),
        new LineTo(1325, 765));

    Path path8 = new Path(
        new MoveTo(1125, 705),
        new LineTo(1345, 705));

    Path path9 = new Path(
        new MoveTo(1125, 705),
        new LineTo(1365, 645));

    Path path10 = new Path(
        new MoveTo(1125, 705),
        new LineTo(1385, 585));

    Path path11 = new Path(
        new MoveTo(568, 825),
        new HLineTo(810));

    Path path12 = new Path(
        new MoveTo(588, 765),
        new HLineTo(810));

    Path path13 = new Path(
        new MoveTo(608, 705),
        new HLineTo(810));

    Path path14 = new Path(
        new MoveTo(628, 645),
        new HLineTo(810));

    Path path15 = new Path(
        new MoveTo(648, 585),
        new HLineTo(810));

    Path path16 = new Path(
        new MoveTo(810, 825),
        new HLineTo(375));

    Path path17 = new Path(
        new MoveTo(810, 765),
        new HLineTo(375));

    Path path18 = new Path(
        new MoveTo(810, 705),
        new HLineTo(375));

    Path path19 = new Path(
        new MoveTo(810, 645),
        new HLineTo(375));

    Path path20 = new Path(
        new MoveTo(810, 585),
        new HLineTo(375));

    Path path21 = new Path(
        new MoveTo(375, 825),
        new HLineTo(568));

    Path path22 = new Path(
        new MoveTo(375, 765),
        new HLineTo(588));

    Path path23 = new Path(
        new MoveTo(375, 705),
        new HLineTo(608));

    Path path24 = new Path(
        new MoveTo(375, 645),
        new HLineTo(628));

    Path path25 = new Path(
        new MoveTo(375, 585),
        new HLineTo(648));

    ArrayList<Path> paths = new ArrayList<>();
    paths.add(path5);
    paths.add(path4);
    paths.add(path3);
    paths.add(path2);
    paths.add(path1);
    paths.add(path10);
    paths.add(path9);
    paths.add(path8);
    paths.add(path7);
    paths.add(path6);
    paths.add(path15);
    paths.add(path14);
    paths.add(path13);
    paths.add(path12);
    paths.add(path11);
    paths.add(path20);
    paths.add(path19);
    paths.add(path18);
    paths.add(path17);
    paths.add(path16);
    paths.add(path25);
    paths.add(path24);
    paths.add(path23);
    paths.add(path22);
    paths.add(path21);

    Image gifImageSteve = new Image("/img/steve.gif");
    Image gifImageSteveFlip = new Image("/img/steveflip.gif");
    Image gifImageSteveApple = new Image("/img/steveapple.gif");
    Image gifImageSteveAxe = new Image("/img/steveaxe.gif");
    Image gifImageSteveCompass = new Image("/img/stevecompass.gif");
    Image gifImageSteveDiamond = new Image("/img/stevediamond.gif");
    Image gifImageSteveEgg = new Image("/img/steveegg.gif");
    Image gifImageSteveEmerald = new Image("/img/steveemerald.gif");
    Image gifImageSteveGoldenApple = new Image("/img/stevegoldenapple.gif");
    Image gifImageStevePickaxe = new Image("/img/stevepickaxe.gif");
    Image gifImageSteveSword = new Image("/img/stevesword.gif");
    Image gifImageSteveTorch = new Image("/img/stevetorch.gif");
    Image gifImageSteveTotem = new Image("/img/stevetotem.gif");
    Image gifImageSteveUU = new Image("/img/steveuu.gif");
    Image gifChest = new Image("/img/chest.gif");

    ArrayList<Image> gifImage = new ArrayList<>();
    gifImage.add(gifImageSteveApple);
    gifImage.add(gifImageSteveAxe);
    gifImage.add(gifImageSteveCompass);
    gifImage.add(gifImageSteveDiamond);
    gifImage.add(gifImageSteveEgg);
    gifImage.add(gifImageSteveEmerald);
    gifImage.add(gifImageSteveGoldenApple);
    gifImage.add(gifImageStevePickaxe);
    gifImage.add(gifImageSteveSword);
    gifImage.add(gifImageSteveTorch);
    gifImage.add(gifImageSteveTotem);
    gifImage.add(gifImageSteveUU);
    gifImage.add(gifImageSteve);
    gifImage.add(gifImageSteveFlip);
    gifImage.add(gifChest);

    Pane paneEscritor1 = new Pane();
    Pane paneEscritor2 = new Pane();
    Pane paneEscritor3 = new Pane();
    Pane paneEscritor4 = new Pane();
    Pane paneEscritor5 = new Pane();
    Pane paneEscritor6 = new Pane();

    paneEscritor6.setTranslateX(930);
    paneEscritor6.setTranslateY(687);

    ArrayList<Pane> paneEscritor = new ArrayList<>();
    paneEscritor.add(paneEscritor1);
    paneEscritor.add(paneEscritor4);
    paneEscritor.add(paneEscritor3);
    paneEscritor.add(paneEscritor2);
    paneEscritor.add(paneEscritor5);
    paneEscritor.add(paneEscritor6);

    pathTransitionEscritorIda = new PathTransition();
    pathTransitionEscritorIda.setDuration(Duration.seconds(5));

    pathTransitionEscritorIda.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionEscritorIda.setCycleCount(1);
    pathTransitionEscritorIda.setInterpolator(Interpolator.LINEAR);

    pathTransitionEscritorVolta = new PathTransition();
    pathTransitionEscritorVolta.setDuration(Duration.seconds(5));

    pathTransitionEscritorVolta.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionEscritorVolta.setCycleCount(1);
    pathTransitionEscritorVolta.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorIda1 = new PathTransition();
    pathTransitionLeitorIda1.setDuration(Duration.seconds(5));
    pathTransitionLeitorIda1.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorIda1.setCycleCount(1);
    pathTransitionLeitorIda1.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorIda2 = new PathTransition();
    pathTransitionLeitorIda2.setDuration(Duration.seconds(5));
    pathTransitionLeitorIda2.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorIda2.setCycleCount(1);
    pathTransitionLeitorIda2.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorIda3 = new PathTransition();
    pathTransitionLeitorIda3.setDuration(Duration.seconds(5));
    pathTransitionLeitorIda3.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorIda3.setCycleCount(1);
    pathTransitionLeitorIda3.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorIda4 = new PathTransition();
    pathTransitionLeitorIda4.setDuration(Duration.seconds(5));
    pathTransitionLeitorIda4.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorIda4.setCycleCount(1);
    pathTransitionLeitorIda4.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorIda5 = new PathTransition();
    pathTransitionLeitorIda5.setDuration(Duration.seconds(5));
    pathTransitionLeitorIda5.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorIda5.setCycleCount(1);
    pathTransitionLeitorIda5.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorVolta1 = new PathTransition();
    pathTransitionLeitorVolta1.setDuration(Duration.seconds(5));
    pathTransitionLeitorVolta1.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorVolta1.setCycleCount(1);
    pathTransitionLeitorVolta1.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorVolta2 = new PathTransition();
    pathTransitionLeitorVolta2.setDuration(Duration.seconds(5));
    pathTransitionLeitorVolta2.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorVolta2.setCycleCount(1);
    pathTransitionLeitorVolta2.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorVolta3 = new PathTransition();
    pathTransitionLeitorVolta3.setDuration(Duration.seconds(5));
    pathTransitionLeitorVolta3.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorVolta3.setCycleCount(1);
    pathTransitionLeitorVolta3.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorVolta4 = new PathTransition();
    pathTransitionLeitorVolta4.setDuration(Duration.seconds(5));
    pathTransitionLeitorVolta4.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorVolta4.setCycleCount(1);
    pathTransitionLeitorVolta4.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitorVolta5 = new PathTransition();
    pathTransitionLeitorVolta5.setDuration(Duration.seconds(5));
    pathTransitionLeitorVolta5.setOrientation(PathTransition.OrientationType.NONE);
    pathTransitionLeitorVolta5.setCycleCount(1);
    pathTransitionLeitorVolta5.setInterpolator(Interpolator.LINEAR);

    pathTransitionLeitor = new ArrayList<>();
    pathTransitionLeitor.add(pathTransitionLeitorVolta5);
    pathTransitionLeitor.add(pathTransitionLeitorVolta4);
    pathTransitionLeitor.add(pathTransitionLeitorVolta3);
    pathTransitionLeitor.add(pathTransitionLeitorVolta2);
    pathTransitionLeitor.add(pathTransitionLeitorVolta1);
    pathTransitionLeitor.add(pathTransitionLeitorIda5);
    pathTransitionLeitor.add(pathTransitionLeitorIda4);
    pathTransitionLeitor.add(pathTransitionLeitorIda3);
    pathTransitionLeitor.add(pathTransitionLeitorIda2);
    pathTransitionLeitor.add(pathTransitionLeitorIda1);

    Scale flipHorizontal = new Scale(-1, 1);

    ImageView steveViewEscritor1 = new ImageView(steve);
    steveViewEscritor1.getTransforms().add(flipHorizontal);
    steveViewEscritor1.setTranslateY(680);
    steveViewEscritor1.setTranslateX(1440);
    steveViewEscritor1.setFitWidth(260);
    steveViewEscritor1.setFitHeight(290);

    ImageView steveViewEscritor2 = new ImageView(steve);
    steveViewEscritor2.getTransforms().add(flipHorizontal);
    steveViewEscritor2.setTranslateY(620);
    steveViewEscritor2.setTranslateX(1460);
    steveViewEscritor2.setFitWidth(260);
    steveViewEscritor2.setFitHeight(290);

    ImageView steveViewEscritor3 = new ImageView(steve);
    steveViewEscritor3.getTransforms().add(flipHorizontal);
    steveViewEscritor3.setTranslateY(560);
    steveViewEscritor3.setTranslateX(1480);
    steveViewEscritor3.setFitWidth(260);
    steveViewEscritor3.setFitHeight(290);

    ImageView steveViewEscritor4 = new ImageView(steve);
    steveViewEscritor4.getTransforms().add(flipHorizontal);
    steveViewEscritor4.setTranslateY(500);
    steveViewEscritor4.setTranslateX(1500);
    steveViewEscritor4.setFitWidth(260);
    steveViewEscritor4.setFitHeight(290);

    ImageView steveViewEscritor5 = new ImageView(steve);
    steveViewEscritor5.getTransforms().add(flipHorizontal);
    steveViewEscritor5.setTranslateY(440);
    steveViewEscritor5.setTranslateX(1520);
    steveViewEscritor5.setFitWidth(260);
    steveViewEscritor5.setFitHeight(290);

    ArrayList<ImageView> steveViewEscritor = new ArrayList<>();
    steveViewEscritor.add(steveViewEscritor5);
    steveViewEscritor.add(steveViewEscritor4);
    steveViewEscritor.add(steveViewEscritor3);
    steveViewEscritor.add(steveViewEscritor2);
    steveViewEscritor.add(steveViewEscritor1);

    ImageView steveViewLeitor1 = new ImageView(steve);
    steveViewLeitor1.setTranslateY(680);
    steveViewLeitor1.setTranslateX(440);
    steveViewLeitor1.setFitWidth(260);
    steveViewLeitor1.setFitHeight(290);

    ImageView steveViewLeitor2 = new ImageView(steve);
    steveViewLeitor2.setTranslateY(620);
    steveViewLeitor2.setTranslateX(460);
    steveViewLeitor2.setFitWidth(260);
    steveViewLeitor2.setFitHeight(290);

    ImageView steveViewLeitor3 = new ImageView(steve);
    steveViewLeitor3.setTranslateY(560);
    steveViewLeitor3.setTranslateX(480);
    steveViewLeitor3.setFitWidth(260);
    steveViewLeitor3.setFitHeight(290);

    ImageView steveViewLeitor4 = new ImageView(steve);
    steveViewLeitor4.setTranslateY(500);
    steveViewLeitor4.setTranslateX(500);
    steveViewLeitor4.setFitWidth(260);
    steveViewLeitor4.setFitHeight(290);

    ImageView steveViewLeitor5 = new ImageView(steve);
    steveViewLeitor5.setTranslateY(440);
    steveViewLeitor5.setTranslateX(520);
    steveViewLeitor5.setFitWidth(260);
    steveViewLeitor5.setFitHeight(290);

    ArrayList<ImageView> steveViewLeitor = new ArrayList<>();
    steveViewLeitor.add(steveViewLeitor5);
    steveViewLeitor.add(steveViewLeitor4);
    steveViewLeitor.add(steveViewLeitor3);
    steveViewLeitor.add(steveViewLeitor2);
    steveViewLeitor.add(steveViewLeitor1);

    Button resetButton = new Button("Reset");
    resetButton.setMaxWidth(180);
    resetButton.setGraphicTextGap(-115);
    resetButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    resetButton.setOnMouseEntered(event -> {
      resetButton.setCursor(Cursor.HAND);
      resetButton.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      resetButton.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    resetButton.setOnMouseExited(event -> {
      resetButton.setCursor(Cursor.DEFAULT);
      resetButton.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      resetButton.setOpacity(1.0);
    }); // fim do setOnMouseExited

    resetButton.setFont(customFont);

    Image bar = new Image("/img/bar.png");
    ImageView barViewReset = new ImageView(bar);
    barViewReset.setTranslateX(-33.5);
    barViewReset.setFitWidth(119);
    barViewReset.setFitHeight(40);
    barViewReset.toBack();

    resetButton.setGraphic(barViewReset);

    Button pauseButtonAllLeitor = new Button("Pausar todos");
    pauseButtonAllLeitor.setMaxWidth(180);
    pauseButtonAllLeitor.setGraphicTextGap(-135);
    pauseButtonAllLeitor.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonAllLeitor.setOnMouseEntered(event -> {
      pauseButtonAllLeitor.setCursor(Cursor.HAND);
      pauseButtonAllLeitor.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonAllLeitor.setOpacity(0.9);
    }); // fim do setOnMouseEntered
 
    pauseButtonAllLeitor.setOnMouseExited(event -> {
      pauseButtonAllLeitor.setCursor(Cursor.DEFAULT);
      pauseButtonAllLeitor.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonAllLeitor.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonAllLeitor.setFont(customFont);

    Image barbigger = new Image("/img/barbigger.png");
    ImageView barViewAllLeitor = new ImageView(barbigger);
    barViewAllLeitor.setTranslateX(-12);
    barViewAllLeitor.setFitWidth(139);
    barViewAllLeitor.setFitHeight(40);
    barViewAllLeitor.toBack();

    pauseButtonAllLeitor.setGraphic(barViewAllLeitor);

    Button pauseButtonLeitor1 = new Button("Pausar");
    pauseButtonLeitor1.setMaxWidth(180);
    pauseButtonLeitor1.setGraphicTextGap(-115);
    pauseButtonLeitor1.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonLeitor1.setOnMouseEntered(event -> {
      pauseButtonLeitor1.setCursor(Cursor.HAND);
      pauseButtonLeitor1.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor1.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonLeitor1.setOnMouseExited(event -> {
      pauseButtonLeitor1.setCursor(Cursor.DEFAULT);
      pauseButtonLeitor1.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor1.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonLeitor1.setFont(customFont);

    ImageView barView1 = new ImageView(bar);
    barView1.setTranslateX(-28.5);
    barView1.setFitWidth(119);
    barView1.setFitHeight(40);
    barView1.toBack();

    pauseButtonLeitor1.setGraphic(barView1);

    Button pauseButtonLeitor2 = new Button("Pausar");
    pauseButtonLeitor2.setMaxWidth(180);
    pauseButtonLeitor2.setGraphicTextGap(-115);
    pauseButtonLeitor2.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonLeitor2.setOnMouseEntered(event -> {
      pauseButtonLeitor2.setCursor(Cursor.HAND);
      pauseButtonLeitor2.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor2.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonLeitor2.setOnMouseExited(event -> {
      pauseButtonLeitor2.setCursor(Cursor.DEFAULT);
      pauseButtonLeitor2.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor2.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonLeitor2.setFont(customFont);

    ImageView barView2 = new ImageView(bar);
    barView2.setTranslateX(-28.5);
    barView2.setFitWidth(119);
    barView2.setFitHeight(40);
    barView2.toBack();

    pauseButtonLeitor2.setGraphic(barView2);

    Button pauseButtonLeitor3 = new Button("Pausar");
    pauseButtonLeitor3.setMaxWidth(180);
    pauseButtonLeitor3.setGraphicTextGap(-115);
    pauseButtonLeitor3.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonLeitor3.setOnMouseEntered(event -> {
      pauseButtonLeitor3.setCursor(Cursor.HAND);
      pauseButtonLeitor3.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor3.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonLeitor3.setOnMouseExited(event -> {
      pauseButtonLeitor3.setCursor(Cursor.DEFAULT);
      pauseButtonLeitor3.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor3.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonLeitor3.setFont(customFont);

    ImageView barView3 = new ImageView(bar);
    barView3.setTranslateX(-28.5);
    barView3.setFitWidth(119);
    barView3.setFitHeight(40);
    barView3.toBack();

    pauseButtonLeitor3.setGraphic(barView3);

    Button pauseButtonLeitor4 = new Button("Pausar");
    pauseButtonLeitor4.setMaxWidth(180);
    pauseButtonLeitor4.setGraphicTextGap(-115);
    pauseButtonLeitor4.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonLeitor4.setOnMouseEntered(event -> {
      pauseButtonLeitor4.setCursor(Cursor.HAND);
      pauseButtonLeitor4.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor4.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonLeitor4.setOnMouseExited(event -> {
      pauseButtonLeitor4.setCursor(Cursor.DEFAULT);
      pauseButtonLeitor4.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor4.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonLeitor4.setFont(customFont);

    ImageView barView4 = new ImageView(bar);
    barView4.setTranslateX(-28.5);
    barView4.setFitWidth(119);
    barView4.setFitHeight(40);
    barView4.toBack();

    pauseButtonLeitor4.setGraphic(barView4);

    Button pauseButtonLeitor5 = new Button("Pausar");
    pauseButtonLeitor5.setMaxWidth(180);
    pauseButtonLeitor5.setGraphicTextGap(-115);
    pauseButtonLeitor5.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonLeitor5.setOnMouseEntered(event -> {
      pauseButtonLeitor5.setCursor(Cursor.HAND);
      pauseButtonLeitor5.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor5.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonLeitor5.setOnMouseExited(event -> {
      pauseButtonLeitor5.setCursor(Cursor.DEFAULT);
      pauseButtonLeitor5.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonLeitor5.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonLeitor5.setFont(customFont);

    ImageView barView5 = new ImageView(bar);
    barView5.setTranslateX(-28.5);
    barView5.setFitWidth(119);
    barView5.setFitHeight(40);
    barView5.toBack();

    pauseButtonLeitor5.setGraphic(barView5);

    Button pauseButtonAllEscritor = new Button("Pausar todos");
    pauseButtonAllEscritor.setMaxWidth(250);
    pauseButtonAllEscritor.setGraphicTextGap(-135);

    pauseButtonAllEscritor.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonAllEscritor.setOnMouseEntered(event -> {
      pauseButtonAllEscritor.setCursor(Cursor.HAND);
      pauseButtonAllEscritor.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonAllEscritor.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonAllEscritor.setOnMouseExited(event -> {
      pauseButtonAllEscritor.setCursor(Cursor.DEFAULT);
      pauseButtonAllEscritor.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonAllEscritor.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonAllEscritor.setFont(customFont);

    ImageView barView0 = new ImageView(barbigger);
    barView0.setTranslateX(-12);
    barView0.setFitWidth(139);
    barView0.setFitHeight(40);
    barView0.toBack();

    pauseButtonAllEscritor.setGraphic(barView0);

    Button pauseButtonEscritor1 = new Button("Pausar");
    pauseButtonEscritor1.setMaxWidth(180);
    pauseButtonEscritor1.setGraphicTextGap(-115);
    pauseButtonEscritor1.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonEscritor1.setOnMouseEntered(event -> {
      pauseButtonEscritor1.setCursor(Cursor.HAND);
      pauseButtonEscritor1.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor1.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonEscritor1.setOnMouseExited(event -> {
      pauseButtonEscritor1.setCursor(Cursor.DEFAULT);
      pauseButtonEscritor1.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor1.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonEscritor1.setFont(customFont);

    ImageView barView6 = new ImageView(bar);
    barView6.setTranslateX(-28.5);
    barView6.setFitWidth(119);
    barView6.setFitHeight(40);
    barView6.toBack();

    pauseButtonEscritor1.setGraphic(barView6);

    Button pauseButtonEscritor2 = new Button("Pausar");
    pauseButtonEscritor2.setMaxWidth(180);
    pauseButtonEscritor2.setGraphicTextGap(-115);
    pauseButtonEscritor2.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonEscritor2.setOnMouseEntered(event -> {
      pauseButtonEscritor2.setCursor(Cursor.HAND);
      pauseButtonEscritor2.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor2.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonEscritor2.setOnMouseExited(event -> {
      pauseButtonEscritor2.setCursor(Cursor.DEFAULT);
      pauseButtonEscritor2.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor2.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonEscritor2.setFont(customFont);

    ImageView barView7 = new ImageView(bar);
    barView7.setTranslateX(-28.5);
    barView7.setFitWidth(119);
    barView7.setFitHeight(40);
    barView7.toBack();

    pauseButtonEscritor2.setGraphic(barView7);

    Button pauseButtonEscritor3 = new Button("Pausar");
    pauseButtonEscritor3.setMaxWidth(180);
    pauseButtonEscritor3.setGraphicTextGap(-115);
    pauseButtonEscritor3.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonEscritor3.setOnMouseEntered(event -> {
      pauseButtonEscritor3.setCursor(Cursor.HAND);
      pauseButtonEscritor3.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor3.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonEscritor3.setOnMouseExited(event -> {
      pauseButtonEscritor3.setCursor(Cursor.DEFAULT);
      pauseButtonEscritor3.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor3.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonEscritor3.setFont(customFont);

    ImageView barView8 = new ImageView(bar);
    barView8.setTranslateX(-28.5);
    barView8.setFitWidth(119);
    barView8.setFitHeight(40);
    barView8.toBack();

    pauseButtonEscritor3.setGraphic(barView8);

    Button pauseButtonEscritor4 = new Button("Pausar");
    pauseButtonEscritor4.setMaxWidth(180);
    pauseButtonEscritor4.setGraphicTextGap(-115);
    pauseButtonEscritor4.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonEscritor4.setOnMouseEntered(event -> {
      pauseButtonEscritor4.setCursor(Cursor.HAND);
      pauseButtonEscritor4.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor4.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonEscritor4.setOnMouseExited(event -> {
      pauseButtonEscritor4.setCursor(Cursor.DEFAULT);
      pauseButtonEscritor4.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor4.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonEscritor4.setFont(customFont);

    ImageView barView9 = new ImageView(bar);
    barView9.setTranslateX(-28.5);
    barView9.setFitWidth(119);
    barView9.setFitHeight(40);
    barView9.toBack();

    pauseButtonEscritor4.setGraphic(barView9);

    Button pauseButtonEscritor5 = new Button("Pausar");
    pauseButtonEscritor5.setMaxWidth(180);
    pauseButtonEscritor5.setGraphicTextGap(-115);
    pauseButtonEscritor5.setStyle("-fx-background-color: transparent; -fx-text-fill: #e0e0e0;");

    pauseButtonEscritor5.setOnMouseEntered(event -> {
      pauseButtonEscritor5.setCursor(Cursor.HAND);
      pauseButtonEscritor5.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 15, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor5.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    pauseButtonEscritor5.setOnMouseExited(event -> {
      pauseButtonEscritor5.setCursor(Cursor.DEFAULT);
      pauseButtonEscritor5.setStyle(
          "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0); " +
              "-fx-transition: all 0.3s; -fx-background-color: transparent; -fx-text-fill: #e0e0e0;");
      pauseButtonEscritor5.setOpacity(1.0);
    }); // fim do setOnMouseExited

    pauseButtonEscritor5.setFont(customFont);

    ImageView barView10 = new ImageView(bar);
    barView10.setTranslateX(-28.5);
    barView10.setFitWidth(119);
    barView10.setFitHeight(40);
    barView10.toBack();

    pauseButtonEscritor5.setGraphic(barView10);

    HBox centerButtons = new HBox(40);
    centerButtons.setAlignment(Pos.CENTER);
    centerButtons.setTranslateX(670);
    centerButtons.setTranslateY(800);
    centerButtons.getChildren().addAll(pauseButtonAllLeitor, resetButton, pauseButtonAllEscritor);

    Slider sliderLeitorLer1 = new Slider(5, 100, 10);
    Slider sliderLeitorUtilizar1 = new Slider(5, 100, 10);
    Slider sliderEscritorObter1 = new Slider(5, 100, 10);
    Slider sliderEscritorEscrever1 = new Slider(5, 100, 10);

    Slider sliderLeitorLer2 = new Slider(5, 100, 10);
    Slider sliderLeitorUtilizar2 = new Slider(5, 100, 10);
    Slider sliderEscritorObter2 = new Slider(5, 100, 10);
    Slider sliderEscritorEscrever2 = new Slider(5, 100, 10);

    Slider sliderLeitorLer3 = new Slider(5, 100, 10);
    Slider sliderLeitorUtilizar3 = new Slider(5, 100, 10);
    Slider sliderEscritorObter3 = new Slider(5, 100, 10);
    Slider sliderEscritorEscrever3 = new Slider(1, 100, 10);

    Slider sliderLeitorLer4 = new Slider(5, 100, 10);
    Slider sliderLeitorUtilizar4 = new Slider(5, 100, 10);
    Slider sliderEscritorObter4 = new Slider(5, 100, 10);
    Slider sliderEscritorEscrever4 = new Slider(5, 100, 10);

    Slider sliderLeitorLer5 = new Slider(5, 100, 10);
    Slider sliderLeitorUtilizar5 = new Slider(5, 100, 10);
    Slider sliderEscritorObter5 = new Slider(5, 100, 10);
    Slider sliderEscritorEscrever5 = new Slider(5, 100, 10);

    Label labelLeitor1 = new Label("Leitor 1");
    labelLeitor1.setFont(customFont);
    labelLeitor1.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorLer1 = new Label("Ler");
    labelLeitorLer1.setFont(customFont);
    labelLeitorLer1.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorUtilizar1 = new Label("Guardar");
    labelLeitorUtilizar1.setFont(customFont);
    labelLeitorUtilizar1.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritor1 = new Label("Escritor 1");
    labelEscritor1.setFont(customFont);
    labelEscritor1.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorObter1 = new Label("Pensar");
    labelEscritorObter1.setFont(customFont);
    labelEscritorObter1.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorEscrever1 = new Label("Escrever");
    labelEscritorEscrever1.setFont(customFont);
    labelEscritorEscrever1.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitor2 = new Label("Leitor 2");
    labelLeitor2.setFont(customFont);
    labelLeitor2.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorLer2 = new Label("Ler");
    labelLeitorLer2.setFont(customFont);
    labelLeitorLer2.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorUtilizar2 = new Label("Guardar");
    labelLeitorUtilizar2.setFont(customFont);
    labelLeitorUtilizar2.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritor2 = new Label("Escritor 2");
    labelEscritor2.setFont(customFont);
    labelEscritor2.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorObter2 = new Label("Pensar");
    labelEscritorObter2.setFont(customFont);
    labelEscritorObter2.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorEscrever2 = new Label("Escrever");
    labelEscritorEscrever2.setFont(customFont);
    labelEscritorEscrever2.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitor3 = new Label("Leitor 3");
    labelLeitor3.setFont(customFont);
    labelLeitor3.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorLer3 = new Label("Ler");
    labelLeitorLer3.setFont(customFont);
    labelLeitorLer3.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorUtilizar3 = new Label("Guardar");
    labelLeitorUtilizar3.setFont(customFont);
    labelLeitorUtilizar3.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritor3 = new Label("Escritor 3");
    labelEscritor3.setFont(customFont);
    labelEscritor3.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorObter3 = new Label("Pensar");
    labelEscritorObter3.setFont(customFont);
    labelEscritorObter3.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorEscrever3 = new Label("Escrever");
    labelEscritorEscrever3.setFont(customFont);
    labelEscritorEscrever3.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitor4 = new Label("Leitor 4");
    labelLeitor4.setFont(customFont);
    labelLeitor4.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorLer4 = new Label("Ler");
    labelLeitorLer4.setFont(customFont);
    labelLeitorLer4.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorUtilizar4 = new Label("Guardar");
    labelLeitorUtilizar4.setFont(customFont);
    labelLeitorUtilizar4.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritor4 = new Label("Escritor 4");
    labelEscritor4.setFont(customFont);
    labelEscritor4.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorObter4 = new Label("Pensar");
    labelEscritorObter4.setFont(customFont);
    labelEscritorObter4.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorEscrever4 = new Label("Escrever");
    labelEscritorEscrever4.setFont(customFont);
    labelEscritorEscrever4.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitor5 = new Label("Leitor 5");
    labelLeitor5.setFont(customFont);
    labelLeitor5.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorLer5 = new Label("Ler");
    labelLeitorLer5.setFont(customFont);
    labelLeitorLer5.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelLeitorUtilizar5 = new Label("Guardar");
    labelLeitorUtilizar5.setFont(customFont);
    labelLeitorUtilizar5.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritor5 = new Label("Escritor 5");
    labelEscritor5.setFont(customFont);
    labelEscritor5.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorObter5 = new Label("Pensar");
    labelEscritorObter5.setFont(customFont);
    labelEscritorObter5.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    Label labelEscritorEscrever5 = new Label("Escrever");
    labelEscritorEscrever5.setFont(customFont);
    labelEscritorEscrever5.setTextFill(javafx.scene.paint.Color.web("#e0e0e0"));

    VBox hboxLeitor1 = new VBox();
    hboxLeitor1.getChildren().addAll(labelLeitorLer1, sliderLeitorLer1, labelLeitorUtilizar1,
        sliderLeitorUtilizar1);
    VBox hboxEscritor1 = new VBox();
    hboxEscritor1.getChildren().addAll(labelEscritorObter1, sliderEscritorObter1, labelEscritorEscrever1,
        sliderEscritorEscrever1);
    VBox hboxLeitor2 = new VBox();
    hboxLeitor2.getChildren().addAll(labelLeitorLer2, sliderLeitorLer2, labelLeitorUtilizar2,
        sliderLeitorUtilizar2);
    VBox hboxEscritor2 = new VBox();
    hboxEscritor2.getChildren().addAll(labelEscritorObter2, sliderEscritorObter2, labelEscritorEscrever2,
        sliderEscritorEscrever2);
    VBox hboxLeitor3 = new VBox();
    hboxLeitor3.getChildren().addAll(labelLeitorLer3, sliderLeitorLer3, labelLeitorUtilizar3,
        sliderLeitorUtilizar3);
    VBox hboxEscritor3 = new VBox();
    hboxEscritor3.getChildren().addAll(labelEscritorObter3, sliderEscritorObter3, labelEscritorEscrever3,
        sliderEscritorEscrever3);
    VBox hboxLeitor4 = new VBox();
    hboxLeitor4.getChildren().addAll(labelLeitorLer4, sliderLeitorLer4, labelLeitorUtilizar4,
        sliderLeitorUtilizar4);
    VBox hboxEscritor4 = new VBox();
    hboxEscritor4.getChildren().addAll(labelEscritorObter4, sliderEscritorObter4, labelEscritorEscrever4,
        sliderEscritorEscrever4);
    VBox hboxLeitor5 = new VBox();
    hboxLeitor5.getChildren().addAll(labelLeitorLer5, sliderLeitorLer5, labelLeitorUtilizar5,
        sliderLeitorUtilizar5);
    VBox hboxEscritor5 = new VBox();
    hboxEscritor5.getChildren().addAll(labelEscritorObter5, sliderEscritorObter5, labelEscritorEscrever5,
        sliderEscritorEscrever5);

    VBox Leitor1 = new VBox(10);
    Leitor1.getChildren().addAll(labelLeitor1, hboxLeitor1, pauseButtonLeitor1);
    Leitor1.setTranslateX(30);
    Leitor1.setTranslateY(10);
    Leitor1.setMaxWidth(120);
    Leitor1.setAlignment(Pos.CENTER);

    VBox Escritor1 = new VBox(10);
    Escritor1.getChildren().addAll(labelEscritor1, hboxEscritor1, pauseButtonEscritor1);
    Escritor1.setAlignment(Pos.CENTER);
    Escritor1.setTranslateX(1620);
    Escritor1.setMaxWidth(120);
    Escritor1.setTranslateY(10);

    VBox Leitor2 = new VBox(10);
    Leitor2.getChildren().addAll(labelLeitor2, hboxLeitor2, pauseButtonLeitor2);
    Leitor2.setTranslateX(30);
    Leitor2.setTranslateY(190);
    Leitor2.setMaxWidth(120);
    Leitor2.setAlignment(Pos.CENTER);

    VBox Escritor2 = new VBox(10);
    Escritor2.getChildren().addAll(labelEscritor2, hboxEscritor2, pauseButtonEscritor2);
    Escritor2.setAlignment(Pos.CENTER);
    Escritor2.setTranslateX(1620);
    Escritor2.setMaxWidth(120);
    Escritor2.setTranslateY(190);

    VBox Leitor3 = new VBox(10);
    Leitor3.getChildren().addAll(labelLeitor3, hboxLeitor3, pauseButtonLeitor3);
    Leitor3.setTranslateX(30);
    Leitor3.setTranslateY(370);
    Leitor3.setMaxWidth(120);
    Leitor3.setAlignment(Pos.CENTER);

    VBox Escritor3 = new VBox(10);
    Escritor3.getChildren().addAll(labelEscritor3, hboxEscritor3, pauseButtonEscritor3);
    Escritor3.setAlignment(Pos.CENTER);
    Escritor3.setTranslateX(1620);
    Escritor3.setMaxWidth(120);
    Escritor3.setTranslateY(370);

    VBox Leitor4 = new VBox(10);
    Leitor4.getChildren().addAll(labelLeitor4, hboxLeitor4, pauseButtonLeitor4);
    Leitor4.setTranslateX(30);
    Leitor4.setTranslateY(550);
    Leitor4.setMaxWidth(120);
    Leitor4.setAlignment(Pos.CENTER);

    VBox Escritor4 = new VBox(10);
    Escritor4.getChildren().addAll(labelEscritor4, hboxEscritor4, pauseButtonEscritor4);
    Escritor4.setAlignment(Pos.CENTER);
    Escritor4.setTranslateX(1620);
    Escritor4.setTranslateY(550);
    Escritor4.setMaxWidth(120);

    VBox Leitor5 = new VBox(10);
    Leitor5.getChildren().addAll(labelLeitor5, hboxLeitor5, pauseButtonLeitor5);
    Leitor5.setTranslateX(30);
    Leitor5.setTranslateY(730);
    Leitor5.setMaxWidth(120);
    Leitor5.setAlignment(Pos.CENTER);

    VBox Escritor5 = new VBox(10);
    Escritor5.getChildren().addAll(labelEscritor5, hboxEscritor5, pauseButtonEscritor5);
    Escritor5.setAlignment(Pos.CENTER);
    Escritor5.setTranslateX(1620);
    Escritor5.setTranslateY(730);
    Escritor5.setMaxWidth(120);

    Pane root = new Pane();
    Image background = new Image("/img/background.png");
    BackgroundImage backgroundImg = new BackgroundImage(background,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.CENTER,

        new BackgroundSize(1400, 900,
            false, false, true, false));

    root.setBackground(new Background(backgroundImg));

    Image brownbackground = new Image("/img/brownbackground.png");
    ImageView brownbackgroundView1 = new ImageView(brownbackground);
    ImageView brownbackgroundView2 = new ImageView(brownbackground);
    brownbackgroundView2.setTranslateX(1585);

    Image back = new Image("/img/back.png");
    ImageView backView = new ImageView(back);
    backView.setTranslateX(1520);
    backView.setTranslateY(10);
    backView.setFitWidth(50);
    backView.setFitHeight(50);

    backView.setOnMouseEntered(event -> {
      backView.setCursor(Cursor.HAND);
      backView.setOpacity(0.9);
    }); // fim do setOnMouseEntered

    backView.setOnMouseExited(event -> {
      backView.setCursor(Cursor.DEFAULT);
      backView.setOpacity(1.0);
    }); // fim do setOnMouseExited

    primaryStage.setResizable(false);

    backView.setOnMouseClicked(event -> {
      primaryStage.setMaxWidth(1776);
      primaryStage.setMaxHeight(935);
      primaryStage.hide();

      b.setAllLeitorPause(true);
      b.setAllEscritorPause(true);
      startStage.show();
    }); // fim do setOnMouseClicked

    root.getChildren().addAll(chestView, brownbackgroundView1, brownbackgroundView2, paneEscritor1,
        steveViewLeitor5, stevae10, steveViewLeitor4, stevae9, steveViewLeitor3, stevae8, steveViewLeitor2,
        stevae7, steveViewLeitor1, stevae6, steveViewEscritor5, paneEscritor5, stevae5, steveViewEscritor4,
        paneEscritor4, stevae4, steveViewEscritor3, paneEscritor3, stevae3, steveViewEscritor2, stevae2,
        paneEscritor2, steveViewEscritor1, stevae1, balloonViewStackPane1, balloonViewStackPane2,
        balloonViewStackPane3, balloonViewStackPane4, balloonViewStackPane5, rectangle, paneEscritor6,
        leitorLabel, guiView, gridPane, balloonViewNotification, textNotification, backView, escritorLabel);

    root.getChildren().addAll(Leitor1, Escritor1, Leitor2, Escritor2, Leitor3, Escritor3, Leitor4, Escritor4,
        Leitor5, Escritor5, centerButtons);

    Scene scene = new Scene(root, 1770, 900);

    b = new Buffer(steveViewLeitor, steveViewEscritor, balloonView, items, rectangle, paths, paneEscritor,
        pathTransitionEscritorIda, gifImageSteve, gifImage, rectangles, pathTransitionEscritorVolta,
        pathTransitionLeitor, chestView, cellRectangles);

    startStage.show();

    firstTimeCheck = -1;

    imageButtonView.setOnMouseClicked(e -> {
      startStage.close();

      primaryStage.setMaxWidth(1770);
      primaryStage.setMaxHeight(935);
      primaryStage.setMinWidth(1770);
      primaryStage.setMinHeight(935);

      primaryStage.setWidth(1770);
      primaryStage.setHeight(935);
      primaryStage.setMaximized(false);
      primaryStage.show();

      if (firstTimeCheck == -1) {
        primaryStage.setScene(scene);

        b.start();
        firstTimeCheck++;
      } else {
        resetButton.fire();
      } // fim do else

    }); // fim do setOnMouseClicked

    sliderEscritorObter1.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritorInicialObter / speed;
      b.setDelayEscritorObter(1, speed);
    }); // fim do addListener

    sliderEscritorEscrever1.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritoInicialEscrever / speed;
      b.setDelayEscritorEscrever(1, speed);
    }); // fim do addListener

    sliderLeitorLer1.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialLer / speed;
      b.setDelayLeitorLer(1, speed);
    }); // fim do addListener

    sliderLeitorUtilizar1.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialUtilizar / speed;
      b.setDelayLeitorUtilizar(1, speed);
    }); // fim do addListener

    sliderEscritorObter2.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritorInicialObter / speed;
      b.setDelayEscritorObter(2, speed);
    }); // fim do addListener

    sliderEscritorEscrever2.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritoInicialEscrever / speed;
      b.setDelayEscritorEscrever(2, speed);
    }); // fim do addListener

    sliderLeitorLer2.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialLer / speed;
      b.setDelayLeitorLer(2, speed);
    }); // fim do addListener

    sliderLeitorUtilizar2.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialUtilizar / speed;
      b.setDelayLeitorUtilizar(2, speed);
    }); // fim do addListener

    sliderEscritorObter3.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritorInicialObter / speed;
      b.setDelayEscritorObter(3, speed);
    }); // fim do addListener

    sliderEscritorEscrever3.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritoInicialEscrever / speed;
      b.setDelayEscritorEscrever(3, speed);
    }); // fim do addListener

    sliderLeitorLer3.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialLer / speed;
      b.setDelayLeitorLer(3, speed);
    }); // fim do addListener

    sliderLeitorUtilizar3.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialUtilizar / speed;
      b.setDelayLeitorUtilizar(3, speed);
    }); // fim do addListener

    sliderEscritorObter4.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritorInicialObter / speed;
      b.setDelayEscritorObter(4, speed);
    }); // fim do addListener

    sliderEscritorEscrever4.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritoInicialEscrever / speed;
      b.setDelayEscritorEscrever(4, speed);
    }); // fim do addListener

    sliderLeitorLer4.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialLer / speed;
      b.setDelayLeitorLer(4, speed);
    }); // fim do addListener

    sliderLeitorUtilizar4.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialUtilizar / speed;
      b.setDelayLeitorUtilizar(4, speed);
    }); // fim do addListener

    sliderEscritorObter5.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritorInicialObter / speed;
      b.setDelayEscritorObter(5, speed);
    }); // fim do addListener

    sliderEscritorEscrever5.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayEscritoInicialEscrever / speed;
      b.setDelayEscritorEscrever(5, speed);
    }); // fim do addListener

    sliderLeitorLer5.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialLer / speed;
      b.setDelayLeitorLer(5, speed);
    }); // fim do addListener

    sliderLeitorUtilizar5.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      speed = delayLeitorInicialUtilizar / speed;
      b.setDelayLeitorUtilizar(5, speed);
    }); // fim do addListener

    pauseButtonLeitor1.setOnAction(e -> {
      if (exitLeitor1 == false) {
        if (pathTransitionLeitorIda5.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda5.pause();
        } // fim do if
        if (pathTransitionLeitorVolta5.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta5.pause();
        } // fim do if
        exitLeitor1 = true;
        pauseButtonLeitor1.setText("Retomar");
        pauseButtonLeitor1.setGraphicTextGap(-120);

        b.setLeitorPause(1, true);

      } else if (exitLeitor1 == true) {
        if (pathTransitionLeitorIda5.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda5.play();
        } // fim do if
        if (pathTransitionLeitorVolta5.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta5.play();
        } // fim do if
        pauseButtonLeitor1.setText("Pausar");
        pauseButtonLeitor1.setGraphicTextGap(-115);

        b.setLeitorPause(1, false);
        exitLeitor1 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonLeitor2.setOnAction(e -> {
      if (exitLeitor2 == false) {
        if (pathTransitionLeitorIda4.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda4.pause();
        } // fim do if
        if (pathTransitionLeitorVolta4.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta4.pause();
        } // fim do if
        exitLeitor2 = true;
        pauseButtonLeitor2.setText("Retomar");
        pauseButtonLeitor2.setGraphicTextGap(-120);

        b.setLeitorPause(2, true);

      } else if (exitLeitor2 == true) {
        if (pathTransitionLeitorIda4.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda4.play();
        } // fim do if
        if (pathTransitionLeitorVolta4.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta4.play();
        } // fim do if
        pauseButtonLeitor2.setText("Pausar");
        pauseButtonLeitor2.setGraphicTextGap(-115);

        b.setLeitorPause(2, false);
        exitLeitor2 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonLeitor3.setOnAction(e -> {
      if (exitLeitor3 == false) {

        if (pathTransitionLeitorIda3.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda3.pause();
        } // fim do if
        if (pathTransitionLeitorVolta3.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta3.pause();
        } // fim do if
        exitLeitor3 = true;
        pauseButtonLeitor3.setText("Retomar");
        pauseButtonLeitor3.setGraphicTextGap(-120);

        b.setLeitorPause(3, true);

      } else if (exitLeitor3 == true) {
        if (pathTransitionLeitorIda3.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda3.play();
        } // fim do if
        if (pathTransitionLeitorVolta3.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta3.play();
        } // fim do if
        pauseButtonLeitor3.setText("Pausar");
        pauseButtonLeitor3.setGraphicTextGap(-115);

        b.setLeitorPause(3, false);
        exitLeitor3 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonLeitor4.setOnAction(e -> {
      if (exitLeitor4 == false) {
        if (pathTransitionLeitorIda2.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda2.pause();
        } // fim do if
        if (pathTransitionLeitorVolta2.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta2.pause();
        } // fim do if
        exitLeitor4 = true;
        pauseButtonLeitor4.setText("Retomar");
        pauseButtonLeitor4.setGraphicTextGap(-120);

        b.setLeitorPause(4, true);
      } else if (exitLeitor4 == true) {
        if (pathTransitionLeitorIda2.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda2.play();
        } // fim do if
        if (pathTransitionLeitorVolta2.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta2.play();
        } // fim do if
        pauseButtonLeitor4.setText("Pausar");
        pauseButtonLeitor4.setGraphicTextGap(-115);

        b.setLeitorPause(4, false);
        exitLeitor4 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonLeitor5.setOnAction(e -> {
      if (exitLeitor5 == false) {
        if (pathTransitionLeitorIda1.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda1.pause();
        } // fim do if
        if (pathTransitionLeitorVolta1.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta1.pause();
        } // fim do if
        exitLeitor5 = true;
        pauseButtonLeitor5.setText("Retomar");
        pauseButtonLeitor5.setGraphicTextGap(-120);

        b.setLeitorPause(5, true);
      } else if (exitLeitor5 == true) {
        if (pathTransitionLeitorIda1.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda1.play();
        } // fim do if
        if (pathTransitionLeitorVolta1.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta1.play();
        } // fim do if
        pauseButtonLeitor5.setText("Pausar");
        pauseButtonLeitor5.setGraphicTextGap(-115);

        b.setLeitorPause(5, false);
        exitLeitor5 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonAllLeitor.setOnAction(e -> {
      if (exitLeitorAll == false) {
        if (pathTransitionLeitorIda1.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda1.pause();
        } // fim do if
        if (pathTransitionLeitorVolta1.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta1.pause();
        } // fim do if
        if (pathTransitionLeitorIda2.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda2.pause();
        } // fim do if
        if (pathTransitionLeitorVolta2.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta2.pause();
        } // fim do if
        if (pathTransitionLeitorIda3.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda3.pause();
        } // fim do if
        if (pathTransitionLeitorVolta3.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta3.pause();
        } // fim do if
        if (pathTransitionLeitorIda4.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda4.pause();
        } // fim do if
        if (pathTransitionLeitorVolta4.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta4.pause();
        } // fim do if
        if (pathTransitionLeitorIda5.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorIda5.pause();
        } // fim do if
        if (pathTransitionLeitorVolta5.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionLeitorVolta5.pause();
        } // fim do if
        exitLeitorAll = true;
        pauseButtonAllLeitor.setText("Retomar todos");
        pauseButtonAllLeitor.setGraphicTextGap(-140);

        b.setAllLeitorPause(true);

      } else if (exitLeitorAll == true) {
        if (pathTransitionLeitorIda1.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda1.play();
        } // fim do if
        if (pathTransitionLeitorVolta1.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta1.play();
        } // fim do if
        if (pathTransitionLeitorIda2.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda2.play();
        } // fim do if
        if (pathTransitionLeitorVolta2.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta2.play();
        } // fim do if
        if (pathTransitionLeitorIda3.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda3.play();
        } // fim do if
        if (pathTransitionLeitorVolta3.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta3.play();
        } // fim do if
        if (pathTransitionLeitorIda4.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda4.play();
        } // fim do if
        if (pathTransitionLeitorVolta4.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta4.play();
        } // fim do if
        if (pathTransitionLeitorIda5.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorIda5.play();
        } // fim do if
        if (pathTransitionLeitorVolta5.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionLeitorVolta5.play();
        } // fim do if

        pauseButtonLeitor5.setText("Pausar");
        pauseButtonLeitor4.setText("Pausar");
        pauseButtonLeitor3.setText("Pausar");
        pauseButtonLeitor2.setText("Pausar");
        pauseButtonLeitor1.setText("Pausar");
        exitLeitor1 = false;
        exitLeitor2 = false;
        exitLeitor3 = false;
        exitLeitor4 = false;
        exitLeitor5 = false;

        pauseButtonAllLeitor.setText("Pausar todos");
        pauseButtonAllLeitor.setGraphicTextGap(-135);

        b.setAllLeitorPause(false);
        exitLeitorAll = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonEscritor1.setOnAction(e -> {
      if (exitEscritor1 == false) {
        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 1) {
            pathTransitionEscritorIda.pause();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 1) {
            pathTransitionEscritorVolta.pause();
          } // fim do if
        } // fim do if

        exitEscritor1 = true;
        pauseButtonEscritor1.setText("Retomar");
        pauseButtonEscritor1.setGraphicTextGap(-120);

        b.setEscritorPause(1, true);
      } else if (exitEscritor1 == true) {

        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 1) {
            pathTransitionEscritorIda.play();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 1) {
            pathTransitionEscritorVolta.play();
          } // fim do if
        } // fim do if

        pauseButtonEscritor1.setText("Pausar");
        pauseButtonEscritor1.setGraphicTextGap(-115);

        b.setEscritorPause(1, false);
        exitEscritor1 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonEscritor2.setOnAction(e -> {
      if (exitEscritor2 == false) {
        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 2) {
            pathTransitionEscritorIda.pause();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 2) {
            pathTransitionEscritorVolta.pause();
          } // fim do if
        } // fim do if
        exitEscritor2 = true;
        pauseButtonEscritor2.setText("Retomar");
        pauseButtonEscritor2.setGraphicTextGap(-120);

        b.setEscritorPause(2, true);
      } else if (exitEscritor2 == true) {
        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 2) {
            pathTransitionEscritorIda.play();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 2) {
            pathTransitionEscritorVolta.play();
          } // fim do if
        } // fim do if
        pauseButtonEscritor2.setText("Pausar");
        pauseButtonEscritor2.setGraphicTextGap(-115);

        b.setEscritorPause(2, false);
        exitEscritor2 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonEscritor3.setOnAction(e -> {
      if (exitEscritor3 == false) {

        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 3) {
            pathTransitionEscritorIda.pause();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 3) {
            pathTransitionEscritorVolta.pause();
          } // fim do if
        } // fim do if
        exitEscritor3 = true;
        pauseButtonEscritor3.setText("Retomar");
        pauseButtonEscritor3.setGraphicTextGap(-120);

        b.setEscritorPause(3, true);

      } else if (exitEscritor3 == true) {
        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 3) {
            pathTransitionEscritorIda.play();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 3) {
            pathTransitionEscritorVolta.play();
          } // fim do if
        } // fim do if
        pauseButtonEscritor3.setText("Pausar");
        pauseButtonEscritor3.setGraphicTextGap(-115);

        b.setEscritorPause(3, false);
        exitEscritor3 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonEscritor4.setOnAction(e -> {
      if (exitEscritor4 == false) {

        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 4) {
            pathTransitionEscritorIda.pause();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 4) {
            pathTransitionEscritorVolta.pause();
          } // fim do if
        } // fim do if
        exitEscritor4 = true;
        pauseButtonEscritor4.setText("Retomar");
        pauseButtonEscritor4.setGraphicTextGap(-120);
        b.setEscritorPause(4, true);
      } else if (exitEscritor4 == true) {
        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 4) {
            pathTransitionEscritorIda.play();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 4) {
            pathTransitionEscritorVolta.play();
          } // fim do if
        } // fim do if
        pauseButtonEscritor4.setText("Pausar");
        pauseButtonEscritor4.setGraphicTextGap(-115);

        b.setEscritorPause(4, false);
        exitEscritor4 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonEscritor5.setOnAction(e -> {
      if (exitEscritor5 == false) {

        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 5) {
            pathTransitionEscritorIda.pause();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.RUNNING)) {
          if (number == 5) {
            pathTransitionEscritorVolta.pause();
          } // fim do if
        } // fim do if
        exitEscritor5 = true;
        pauseButtonEscritor5.setText("Retomar");
        pauseButtonEscritor5.setGraphicTextGap(-120);

        b.setEscritorPause(5, true);
      } else if (exitEscritor5 == true) {
        int number = b.getPathNow();
        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 5) {
            pathTransitionEscritorIda.play();
          } // fim do if
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.PAUSED)) {
          if (number == 5) {
            pathTransitionEscritorVolta.play();
          } // fim do if
        } // fim do if
        pauseButtonEscritor5.setText("Pausar");
        pauseButtonEscritor5.setGraphicTextGap(-115);

        b.setEscritorPause(5, false);
        exitEscritor5 = false;
      } // fim do else if
    }); // fim do setOnAction

    pauseButtonAllEscritor.setOnAction(e -> {
      if (exitEscritorAll == false) {

        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionEscritorIda.pause();
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionEscritorVolta.pause();

        } // fim do if
        exitEscritorAll = true;
        pauseButtonAllEscritor.setText("Retomar todos");
        pauseButtonAllEscritor.setGraphicTextGap(-140);

        b.setAllEscritorPause(true);
      } else if (exitEscritorAll == true) {

        if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionEscritorIda.play();
        } // fim do if
        if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionEscritorVolta.play();
        } // fim do if
        pauseButtonAllEscritor.setText("Pausar todos");
        pauseButtonAllEscritor.setGraphicTextGap(-135);
        exitEscritor5 = false;
        exitEscritor4 = false;
        exitEscritor3 = false;
        exitEscritor2 = false;
        exitEscritor1 = false;
        pauseButtonEscritor5.setText("Pausar");
        pauseButtonEscritor4.setText("Pausar");
        pauseButtonEscritor3.setText("Pausar");
        pauseButtonEscritor2.setText("Pausar");
        pauseButtonEscritor1.setText("Pausar");

        b.setAllEscritorPause(false);
        exitEscritorAll = false;
      } // fim do else if
    }); // fim do setOnAction

    resetButton.setOnAction(e -> {
      if (pathTransitionLeitorIda1.getNode() != null) {
        pathTransitionLeitorIda1.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorIda1.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorIda1.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorIda1.stop();
      } // fim do if
      if (pathTransitionLeitorIda2.getNode() != null) {
        pathTransitionLeitorIda2.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorIda2.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorIda2.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorIda2.stop();
      } // fim do if
      if (pathTransitionLeitorIda3.getNode() != null) {
        pathTransitionLeitorIda3.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorIda3.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorIda3.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorIda3.stop();
      } // fim do if
      if (pathTransitionLeitorIda4.getNode() != null) {
        pathTransitionLeitorIda4.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorIda4.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorIda4.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorIda4.stop();
      } // fim do if
      if (pathTransitionLeitorIda5.getNode() != null) {
        pathTransitionLeitorIda5.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorIda5.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorIda5.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorIda5.stop();
      } // fim do if
      if (pathTransitionLeitorVolta1.getNode() != null) {
        pathTransitionLeitorVolta1.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorVolta1.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorVolta1.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorVolta1.stop();
      } // fim do if
      if (pathTransitionLeitorVolta2.getNode() != null) {
        pathTransitionLeitorVolta2.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorVolta2.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorVolta2.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorVolta2.stop();
      } // fim do if
      if (pathTransitionLeitorVolta3.getNode() != null) {
        pathTransitionLeitorVolta3.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorVolta3.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorVolta3.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorVolta3.stop();
      } // fim do if
      if (pathTransitionLeitorVolta4.getNode() != null) {
        pathTransitionLeitorVolta4.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorVolta4.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorVolta4.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorVolta4.stop();
      } // fim do if
      if (pathTransitionLeitorVolta5.getNode() != null) {
        pathTransitionLeitorVolta5.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionLeitorVolta5.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionLeitorVolta5.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionLeitorVolta5.stop();
      } // fim do if
      if (pathTransitionEscritorIda.getNode() != null) {
        pathTransitionEscritorIda.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionEscritorIda.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionEscritorIda.stop();
      } // fim do if
      if (pathTransitionEscritorVolta.getNode() != null) {
        pathTransitionEscritorVolta.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.RUNNING)
          || pathTransitionEscritorVolta.getStatus().equals(PathTransition.Status.PAUSED)) {
        pathTransitionEscritorVolta.stop();
      } // fim do if
      b.setExitAll();
      rectangle.getChildren().clear();
      balloonViewStackPane1.getChildren().clear();
      balloonViewStackPane1.getChildren().add(balloonView1);
      balloonViewStackPane2.getChildren().clear();
      balloonViewStackPane2.getChildren().add(balloonView2);
      balloonViewStackPane3.getChildren().clear();
      balloonViewStackPane3.getChildren().add(balloonView3);
      balloonViewStackPane4.getChildren().clear();
      balloonViewStackPane4.getChildren().add(balloonView4);
      balloonViewStackPane5.getChildren().clear();
      balloonViewStackPane5.getChildren().add(balloonView5);
      balloonViewStackPane1.setVisible(false);
      balloonViewStackPane2.setVisible(false);
      balloonViewStackPane3.setVisible(false);
      balloonViewStackPane4.setVisible(false);
      balloonViewStackPane5.setVisible(false);

      b.removerFlip();

      steveViewEscritor1.setTranslateY(680);
      steveViewEscritor1.setTranslateX(1440);
      steveViewEscritor2.setTranslateY(620);
      steveViewEscritor2.setTranslateX(1460);
      steveViewEscritor3.setTranslateY(560);
      steveViewEscritor3.setTranslateX(1480);
      steveViewEscritor4.setTranslateY(500);
      steveViewEscritor4.setTranslateX(1500);
      steveViewEscritor5.setTranslateY(440);
      steveViewEscritor5.setTranslateX(1520);
      steveViewLeitor1.setTranslateY(680);
      steveViewLeitor1.setTranslateX(440);
      steveViewLeitor2.setTranslateY(620);
      steveViewLeitor2.setTranslateX(460);
      steveViewLeitor3.setTranslateY(560);
      steveViewLeitor3.setTranslateX(480);
      steveViewLeitor4.setTranslateY(500);
      steveViewLeitor4.setTranslateX(500);
      steveViewLeitor5.setTranslateY(440);
      steveViewLeitor5.setTranslateX(520);

      cell00.setFill(Color.TRANSPARENT);
      cell01.setFill(Color.TRANSPARENT);
      cell02.setFill(Color.TRANSPARENT);
      cell03.setFill(Color.TRANSPARENT);
      cell04.setFill(Color.TRANSPARENT);
      cell05.setFill(Color.TRANSPARENT);
      cell10.setFill(Color.TRANSPARENT);
      cell11.setFill(Color.TRANSPARENT);
      cell12.setFill(Color.TRANSPARENT);
      cell13.setFill(Color.TRANSPARENT);
      cell14.setFill(Color.TRANSPARENT);
      cell15.setFill(Color.TRANSPARENT);
      cell20.setFill(Color.TRANSPARENT);
      cell21.setFill(Color.TRANSPARENT);
      cell22.setFill(Color.TRANSPARENT);
      cell23.setFill(Color.TRANSPARENT);
      cell24.setFill(Color.TRANSPARENT);
      cell25.setFill(Color.TRANSPARENT);
      cell30.setFill(Color.TRANSPARENT);
      cell31.setFill(Color.TRANSPARENT);
      cell32.setFill(Color.TRANSPARENT);
      cell33.setFill(Color.TRANSPARENT);
      cell34.setFill(Color.TRANSPARENT);
      cell35.setFill(Color.TRANSPARENT);
      cell40.setFill(Color.TRANSPARENT);
      cell41.setFill(Color.TRANSPARENT);
      cell42.setFill(Color.TRANSPARENT);
      cell43.setFill(Color.TRANSPARENT);
      cell44.setFill(Color.TRANSPARENT);
      cell45.setFill(Color.TRANSPARENT);
      cell50.setFill(Color.TRANSPARENT);
      cell51.setFill(Color.TRANSPARENT);
      cell52.setFill(Color.TRANSPARENT);
      cell53.setFill(Color.TRANSPARENT);
      cell54.setFill(Color.TRANSPARENT);
      cell55.setFill(Color.TRANSPARENT);
      cell60.setFill(Color.TRANSPARENT);
      cell61.setFill(Color.TRANSPARENT);
      cell62.setFill(Color.TRANSPARENT);
      cell63.setFill(Color.TRANSPARENT);
      cell64.setFill(Color.TRANSPARENT);
      cell65.setFill(Color.TRANSPARENT);
      cell70.setFill(Color.TRANSPARENT);
      cell71.setFill(Color.TRANSPARENT);
      cell72.setFill(Color.TRANSPARENT);
      cell73.setFill(Color.TRANSPARENT);
      cell74.setFill(Color.TRANSPARENT);
      cell75.setFill(Color.TRANSPARENT);
      cell80.setFill(Color.TRANSPARENT);
      cell81.setFill(Color.TRANSPARENT);
      cell82.setFill(Color.TRANSPARENT);
      cell83.setFill(Color.TRANSPARENT);
      cell84.setFill(Color.TRANSPARENT);
      cell85.setFill(Color.TRANSPARENT);
      gridPane.setVisible(false);
      guiView.setVisible(false);

      paneEscritor1.getChildren().clear();
      paneEscritor2.getChildren().clear();
      paneEscritor3.getChildren().clear();
      paneEscritor4.getChildren().clear();
      paneEscritor5.getChildren().clear();
      paneEscritor6.getChildren().clear();

      steveViewEscritor1.setVisible(true);
      steveViewEscritor2.setVisible(true);
      steveViewEscritor3.setVisible(true);
      steveViewEscritor4.setVisible(true);
      steveViewEscritor5.setVisible(true);
      steveViewLeitor1.setVisible(true);
      steveViewLeitor2.setVisible(true);
      steveViewLeitor3.setVisible(true);
      steveViewLeitor4.setVisible(true);
      steveViewLeitor5.setVisible(true);

      chestView.setVisible(true);

      b.setAllEscritorPause(true);
      b.setAllLeitorPause(true);

      exitEscritor1 = false;
      exitEscritor2 = false;
      exitEscritor3 = false;
      exitEscritor4 = false;
      exitEscritor5 = false;
      exitLeitor1 = false;
      exitLeitor2 = false;
      exitLeitor3 = false;
      exitLeitor4 = false;
      exitLeitor5 = false;
      exitLeitorAll = false;
      exitEscritorAll = false;
      pauseButtonEscritor1.setText("Pausar");
      pauseButtonEscritor2.setText("Pausar");
      pauseButtonEscritor3.setText("Pausar");
      pauseButtonEscritor4.setText("Pausar");
      pauseButtonEscritor5.setText("Pausar");
      pauseButtonLeitor1.setText("Pausar");
      pauseButtonLeitor2.setText("Pausar");
      pauseButtonLeitor3.setText("Pausar");
      pauseButtonLeitor4.setText("Pausar");
      pauseButtonLeitor5.setText("Pausar");
      pauseButtonAllEscritor.setText("Pausar todos");
      pauseButtonAllLeitor.setText("Pausar todos");

      b = new Buffer(steveViewLeitor, steveViewEscritor, balloonView, items, rectangle, paths, paneEscritor,
          pathTransitionEscritorIda, gifImageSteve, gifImage, rectangles, pathTransitionEscritorVolta,
          pathTransitionLeitor, chestView, cellRectangles);
      b.start();

    }); // fim do setOnAction
  } // fim do start

  /* ***************************************************************
  * Metodo: main
  * Funcao: metodo principal da classe
  * Parametros: String[] args
  * Retorno: void
  *************************************************************** */
  public static void main(String[] args) {
    launch(args);
  } // fim do main
} // fim da classe
