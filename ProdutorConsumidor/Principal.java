 /* ***************************************************************
 * Autor............: Caue Rodrigues de Aguiar
 * Matricula........: 202210181
 * Inicio...........: 17/10/2023
 * Ultima alteracao.: 28/10/2023
 * Nome.............: Principal.java
 * Funcao...........: Programa principal que inicia a aplicacao
 *             com botoes, slider, animacoes, imagens e threads.
 *************************************************************** */

import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.application.Application;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

public class Principal extends Application {

  private ObservableList<String> itemsInPreparation = FXCollections.observableArrayList(); // Lista de itens em preparacao
  private ObservableList<String> itemsReady = FXCollections.observableArrayList(); // Lista de itens prontos
  private Buffer buffer; // Buffer compartilhado
  private Produtor produtor; // Thread produtor
  private Consumidor consumidor; // Thread consumidor
  private volatile boolean exitProdutor; // Variavel de controle de execucao da thread produtor
  private volatile boolean exitConsumidor; // Variavel de controle de execucao da thread consumidor

  /* ***************************************************************
  * Metodo: start
  * Funcao: constroi a interface grafica
  * Parametros: Stage primaryStage
  * Retorno: void
  *************************************************************** */
  @Override
  public void start(Stage primaryStage) {

    Rectangle rectangle = new Rectangle(0, 700, 750, 220); 
    Color strokeColor = Color.web("0x8f7264");
    rectangle.setStroke(strokeColor);

    rectangle.setStrokeWidth(6);

    rectangle.setStrokeType(StrokeType.OUTSIDE);
    rectangle.setFill(Color.WHITE);

    Path path1 = new Path( 
        new MoveTo(200, 950),
        new CubicCurveTo(246, 663, 137, 554, 120, 462));

    Path path2 = new Path(
        new MoveTo(400, 950),
        new CubicCurveTo(446, 663, 337, 554, 320, 462));

    Path path3 = new Path(
        new MoveTo(600, 950),
        new CubicCurveTo(646, 663, 537, 554, 520, 462));

    Path reversedPath1 = new Path(
        new MoveTo(120, 462),
        new CubicCurveTo(137, 554, 246, 663, 200, 950));

    Path reversedPath2 = new Path(
        new MoveTo(320, 462),
        new CubicCurveTo(337, 554, 446, 663, 400, 950));

    Path reversedPath3 = new Path(
        new MoveTo(520, 462),
        new CubicCurveTo(537, 554, 646, 663, 600, 950));

    ArrayList<Path> reversedPaths = new ArrayList<>(); // Caminhos de volta
    reversedPaths.add(reversedPath1);
    reversedPaths.add(reversedPath2);
    reversedPaths.add(reversedPath3);

    ArrayList<Path> paths = new ArrayList<>(); // Caminhos de ida
    paths.add(path1);
    paths.add(path2);
    paths.add(path3);

    Image gifImageIda = new Image("/img/boneco.gif");
    ImageView imageViewIdaGif = new ImageView(gifImageIda);
    imageViewIdaGif.setX(-300);
    imageViewIdaGif.setFitHeight(576 / 1.2);
    imageViewIdaGif.setFitWidth(260 / 1.2);

    Image gifImageVolta = new Image("/img/boneco_segurando.gif");

    PathTransition pathTransitionIda = new PathTransition();
    pathTransitionIda.setDuration(Duration.seconds(5)); // Duracao da animacao
    pathTransitionIda.setNode(imageViewIdaGif); // Define o no a ser animado
    pathTransitionIda.setOrientation(PathTransition.OrientationType.NONE); // Orientacao do no
    pathTransitionIda.setCycleCount(1);

    PathTransition pathTransitionVolta = new PathTransition();
    pathTransitionVolta.setDuration(Duration.seconds(5)); // Duracao da animacao
    pathTransitionVolta.setOrientation(PathTransition.OrientationType.NONE); // Orientacao do no
    pathTransitionVolta.setCycleCount(1);

    ListView<String> listViewPreparando = new ListView<>(itemsInPreparation);
    listViewPreparando.setStyle("-fx-font-size: 13 px;");

    ListView<String> listViewPronto = new ListView<>(itemsReady);
    listViewPronto.setStyle("-fx-font-size: 13 px;");
    primaryStage.setTitle("MarlosDonald's");
    Image icon = new Image("/img/icon.png");
    primaryStage.getIcons().add(icon);
    primaryStage.setResizable(false);

    Button resetButton = new Button("Reset");
    Button pauseButtonProdutor = new Button("Pausar");
    Button pauseButtonConsumidor = new Button("Pausar");

    resetButton.setStyle(
        "-fx-background-color: #4CAF50; " + // Cor de fundo
            "-fx-text-fill: white; " + // Cor do texto
            "-fx-font-size: 14px; " + // Tamanho da fonte
            "-fx-background-radius: 5em; " + // Borda arredondada 
            "-fx-padding: 10px 20px;" // Preenchimento 
    ); // fim do resetButton.setStyle

    pauseButtonProdutor.setStyle(
        "-fx-background-color: #E74C3C; " + // Cor de fundo
            "-fx-text-fill: white; " + // Cor do texto
            "-fx-font-size: 14px; " + // Tamanho da fonte
            "-fx-background-radius: 5em; " + // Borda arredondada 
            "-fx-padding: 10px 20px;" // Preenchimento 
    ); // fim do pauseButtonProdutor.setStyle

    pauseButtonConsumidor.setStyle(
        "-fx-background-color: #3498db; " + // Cor de fundo
            "-fx-text-fill: white; " + // Cor do texto
            "-fx-font-size: 14px; " + // Tamanho da fonte
            "-fx-background-radius: 5em; " + // Borda arredondada 
            "-fx-padding: 10px 20px;" // Preenchimento 
    ); // fim do pauseButtonConsumidor.setStyle

    // Sliders
    Slider sliderProdutor = new Slider(8, 100, 19);
    Slider sliderConsumidor = new Slider(1, 100, 12);

    sliderProdutor.setStyle(
        "-fx-background-color: #DB3535; " + // Cor de fundo
            "-fx-background-radius: 10px; " + // Borda arredondada
            "-fx-padding: 5px; " + // Espacamento interno
            "-fx-text-fill: white;" // Cor do texto
    ); // fim do sliderProdutor.setStyle

    DoubleProperty dividedRateProperty = new SimpleDoubleProperty(); // Propriedade para dividir o valor do slider
    dividedRateProperty.bind(Bindings.divide(sliderConsumidor.valueProperty(), 10.0));
    pathTransitionIda.rateProperty().bind(dividedRateProperty);
    pathTransitionVolta.rateProperty().bind(dividedRateProperty);

    sliderConsumidor.setStyle(
        "-fx-background-color: #03A9F4; " + // Cor de fundo
            "-fx-background-radius: 10px; " + // Borda arredondada
            "-fx-padding: 5px; " + // Espacamento interno
            "-fx-text-fill: white;" // Cor do texto
    ); // fim do sliderConsumidor.setStyle

    Pane root = new Pane();

    path1.setStroke(Color.BLACK);
    path1.setStrokeWidth(2);

    VBox preparando = new VBox(listViewPreparando);
    preparando.setMaxWidth(150);
    preparando.setMaxHeight(100);

    VBox ready = new VBox(listViewPronto);
    ready.setMaxWidth(150);
    ready.setMaxHeight(100);

    HBox televisao = new HBox(20); // Caixa de listView
    televisao.getChildren().addAll(listViewPreparando, listViewPronto);
    televisao.setMaxWidth(290);
    televisao.setMaxHeight(100);
    televisao.setTranslateX(405);
    televisao.setTranslateY(60);

    HBox buttonBox = new HBox(10); // Caixa de botoes
    Label produtorLabel = new Label("Produtor");
    Label consumidorLabel = new Label("Consumidor");
    produtorLabel.setStyle("-fx-font-size: 21px; -fx-text-fill: #cc0000; -fx-font-weight: bold;");
    consumidorLabel.setStyle("-fx-font-size: 21px; -fx-text-fill: #3498db; -fx-font-weight: bold;");

    VBox produtorBox = new VBox(10); // Caixa de botoes do produtor
    produtorBox.setAlignment(Pos.CENTER);
    VBox consumidorBox = new VBox(10); // Caixa de botoes do consumidor
    consumidorBox.setAlignment(Pos.CENTER);
    produtorBox.getChildren().addAll(produtorLabel, pauseButtonProdutor, sliderProdutor);
    consumidorBox.getChildren().addAll(consumidorLabel, pauseButtonConsumidor, sliderConsumidor);
    buttonBox.getChildren().addAll(produtorBox, resetButton, consumidorBox);
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.setTranslateX(160);
    buttonBox.setTranslateY(745);

    Image image1 = new Image("/img/batata.png");
    Image image2 = new Image("/img/combo.png");
    Image image3 = new Image("/img/hamburguer.png");
    Image image4 = new Image("/img/hamburguerbatata.png");
    Image image5 = new Image("/img/hamburguerrefrigerante.png");
    Image image6 = new Image("/img/batatarefrigerante.png");

    Image clear = new Image("/img/clear.png");
    ImageView clearView1 = new ImageView(clear);
    ImageView clearView2 = new ImageView(clear);
    ImageView clearView3 = new ImageView(clear);

    VBox imageBox1 = new VBox();
    imageBox1.getChildren().add(clearView1);
    imageBox1.setAlignment(Pos.BOTTOM_CENTER);

    VBox imageBox2 = new VBox();
    imageBox2.getChildren().add(clearView2);
    imageBox2.setAlignment(Pos.BOTTOM_CENTER);

    VBox imageBox3 = new VBox();
    imageBox3.getChildren().add(clearView3);
    imageBox3.setAlignment(Pos.BOTTOM_CENTER);

    HBox lanches = new HBox(30);
    lanches.setAlignment(Pos.CENTER);
    lanches.setTranslateY(240);
    lanches.setTranslateX(50);
    lanches.getChildren().addAll(imageBox1, imageBox2, imageBox3);
    
    root.getChildren().addAll(lanches, televisao);

    Image background = new Image("/img/background.png");
    BackgroundImage backgroundImg = new BackgroundImage(background,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, 
                false, false, true, false)
    ); // fim do backgroundImg

    root.setBackground(new Background(backgroundImg));
    root.getChildren().addAll(imageViewIdaGif, rectangle, buttonBox);

    Scene scene = new Scene(root, 700, 900);
    primaryStage.setScene(scene);

    primaryStage.show();

    ArrayList<Image> images = new ArrayList<>(); // Lista de imagens
    images.addAll(Arrays.asList(image1, image2, image3, image4, image5, image6));

    exitProdutor = false; // Variaveis de controle de execucao das threads
    exitConsumidor = false; // Variaveis de controle de execucao das threads

    buffer = new Buffer(imageBox1, imageBox2, imageBox3, itemsInPreparation, 
            itemsReady, exitProdutor, exitConsumidor, lanches);

    produtor = new Produtor(buffer, images, itemsInPreparation, exitProdutor);

    consumidor = new Consumidor(buffer, clear, imageBox1, imageBox2, imageBox3, 
            itemsReady, exitConsumidor, pathTransitionIda, paths, pathTransitionVolta,
            reversedPaths, root, gifImageVolta);

    produtor.start(); // Inicia a thread produtor
    consumidor.start(); // Inicia a thread consumidor

    sliderProdutor.valueProperty().addListener((obs, oldValue, newValue) -> {
      int speed = newValue.intValue();
      produtor.setDelay(100000 / speed);
    }); // fim do sliderProdutor.valueProperty().addListener

    resetButton.setOnAction(e -> { // Botao de reset
      pathTransitionIda.getNode().setOpacity(0);
      if (pathTransitionIda.getStatus().equals(PathTransition.Status.RUNNING)) {
        pathTransitionIda.stop();
      } // fim do if
      if (pathTransitionVolta.getNode() != null) {
        pathTransitionVolta.getNode().setOpacity(0);
      } // fim do if
      if (pathTransitionVolta.getStatus().equals(PathTransition.Status.RUNNING)) {
        pathTransitionVolta.stop();
      } // fim do if
      produtor.setExit(true);
      consumidor.setExit(true);
      buffer.setExitProdutor(true);
      buffer.setExitConsumidor(true);

      itemsInPreparation.clear();
      itemsReady.clear();
      sliderProdutor.setValue(15);
      sliderConsumidor.setValue(12);
      imageBox1.getChildren().clear();
      imageBox2.getChildren().clear();
      imageBox3.getChildren().clear();
      imageBox1.getChildren().add(clearView1);
      imageBox2.getChildren().add(clearView2);
      imageBox3.getChildren().add(clearView3);

      exitProdutor = false;
      exitConsumidor = false;
      
      buffer = new Buffer(imageBox1, imageBox2, imageBox3, itemsInPreparation,
              itemsReady, exitProdutor, exitConsumidor, lanches);

      produtor = new Produtor(buffer, images, itemsInPreparation, exitProdutor);

      consumidor = new Consumidor(buffer, clear, imageBox1, imageBox2, imageBox3,
              itemsReady, exitProdutor, pathTransitionIda, paths, pathTransitionVolta,
              reversedPaths, root, gifImageVolta);

      produtor.setExit(false);
      consumidor.setExit(false);

      produtor.start();
      consumidor.start();
    }); // fim do resetButton.setOnAction

    pauseButtonProdutor.setOnAction(e -> { // Botao de pausa do produtor
      if (exitProdutor == false) {
        exitProdutor = true;
        pauseButtonProdutor.setText("Retomar");
        produtor.setExit(true);
        buffer.setProducerPause(true);
      } else if (exitProdutor == true) {
        pauseButtonProdutor.setText("Pausar");
        produtor.setExit(false);
        buffer.setProducerPause(false);
        exitProdutor = false;
      } // fim do if
    }); // fim do pauseButtonProdutor.setOnAction

    pauseButtonConsumidor.setOnAction(e -> { // Botao de pausa do consumidor
      if (exitConsumidor == false) {
        exitConsumidor = true;

        if (pathTransitionIda.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionIda.pause();
        } // fim do if
        if (pathTransitionVolta.getStatus().equals(PathTransition.Status.RUNNING)) {
          pathTransitionVolta.pause();
        } // fim do if
        pauseButtonConsumidor.setText("Retomar");
        buffer.setConsumerPause(true);
        consumidor.setExit(true);
      } else if (exitConsumidor == true) {
        if (pathTransitionIda.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionIda.play();
        } // fim do if
        if (pathTransitionVolta.getStatus().equals(PathTransition.Status.PAUSED)) {
          pathTransitionVolta.play();
        } // fim do if
        pauseButtonConsumidor.setText("Pausar");
        buffer.setConsumerPause(false);
        consumidor.setExit(false);
        exitConsumidor = false;
      } // fim do if
    }); // fim do pauseButtonConsumidor.setOnAction
  } // fim do metodo start

  /* ***************************************************************
  * Metodo: main
  * Funcao: metodo principal que inicia a aplicacao
  * Parametros: String[] args
  * Retorno: void
  *************************************************************** */
  public static void main(String[] args) {
    launch(args);
  } // fim do metodo main
} // fim da classe Principal
