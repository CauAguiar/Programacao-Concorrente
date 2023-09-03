/* *********************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 24/08/2023
* Ultima alteracao.: 02/09/2023
* Nome.............: Principal.java
* Funcao...........: Projeto de Simulador de Trem desenvolvido em Java 
* utilizando a biblioteca JavaFX. O simulador permite visualizar trens 
* se movendo em trilhas definidas e oferece opcoes de controle e interacao.
************************************************************************ */

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Principal extends Application {
  private static final double DEFAULT_SPEED = 10; // Velocidade padrao dos trens
  private Stage primaryStage; // Janela principal
  private Stage StageNew; // Janela da simulacao
  private HBox hbox; // Caixa horizontal do painel de controle
  private VBox vbox; // Caixa vertical do painel de controle
  private VBox blueSliderVbox; // Caixa para slider e label azul
  private VBox redSliderVbox; // Caixa para slider e label vermelho
  private VBox textContainer; // Conteiner de texto
  private Button resetButton; // Botao de redefinicao da simulacao
  private CheckBox checkBox; // Caixa de selecao para ativar a funcao de anti-colisao
  private Slider blueSpeedSlider; // Slider do trem azul
  private Slider redSpeedSlider; // Slider do trem vermelho
  private Rectangle2D screenBounds; // Limites da tela
  private BooleanProperty pauseTrains1; // Propriedade booleana para pausar trens 1
  private BooleanProperty pauseTrains2; // Propriedade booleana para pausar trens 2
  private Thread collisionDetectionThread; // Thread de deteccao de colisao
  

  private enum Option {
    OP1("Mesma Direcao"),
    OP2("Mesma Direcao Inversa"),
    OP3("Direcao Oposta"),
    OP4("Direcao Oposta Inversa");

    private final String title;

    Option(String title) {
      this.title = title;
    }// fim do construtor Option

    public String getTitle() {
      return title;
    }// fim do metodo getTitle
  }// fim do enum Option

  
  /** ********************************************************************
  * Metodo: start
  * Funcao: Metodo de inicializacao da aplicacao JavaFX.
  * Parametros: @param primaryStage eh o Stage principal da aplicacao, 
  * que representa a janela principal.
  * Retorno: @return void
  ******************************************************************** */
  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    primaryStage.setTitle("Simulador de Trem");

    // Tamanho da tela
    this.screenBounds = Screen.getPrimary().getVisualBounds();

    Text directionText = new Text("Selecione uma das direcoes para o trem:");
    directionText.setFont(Font.font("Arial", FontWeight.BOLD, 18));

    // Propriedade booleana para utilizacao nas threads
    this.pauseTrains1 = new SimpleBooleanProperty(false);
    this.pauseTrains2 = new SimpleBooleanProperty(false);

    Button button1 = createStyledButton(Option.OP1);
    Button button2 = createStyledButton(Option.OP2);
    Button button3 = createStyledButton(Option.OP3);
    Button button4 = createStyledButton(Option.OP4);

    HBox buttonContainer = new HBox(10); 
    buttonContainer.setAlignment(Pos.CENTER); 
    buttonContainer.setPadding(new Insets(0, 20, 20, 20)); 

    VBox textContainer = new VBox(directionText);
    textContainer.setAlignment(Pos.CENTER); 
    textContainer.setPadding(new Insets(10, 0, 0, 0)); 

    this.vbox = new VBox(10); 
    this.vbox.setAlignment(Pos.TOP_CENTER);
    this.vbox.getChildren().addAll(textContainer, buttonContainer);

    VBox buttonImage1 = new VBox(2);
    buttonImage1.setAlignment(Pos.CENTER);
    buttonImage1.setPadding(new Insets(10, 0, 0, 0));

    VBox buttonImage2 = new VBox(2);
    buttonImage2.setAlignment(Pos.CENTER);
    buttonImage2.setPadding(new Insets(10, 0, 0, 0));

    VBox buttonImage3 = new VBox(2);
    buttonImage3.setAlignment(Pos.CENTER);
    buttonImage3.setPadding(new Insets(10, 0, 0, 0));

    VBox buttonImage4 = new VBox(2);
    buttonImage4.setAlignment(Pos.CENTER);
    buttonImage4.setPadding(new Insets(10, 0, 0, 0));

    Image image1 = new Image("/img/downdown.png");
    ImageView imageView1 = new ImageView(image1);

    Image image2 = new Image("/img/upup.png");
    ImageView imageView2 = new ImageView(image2);

    Image image3 = new Image("/img/downup.png");
    ImageView imageView3 = new ImageView(image3);

    Image image4 = new Image("/img/updown.png");
    ImageView imageView4 = new ImageView(image4);

    buttonImage1.getChildren().add(button1);
    buttonImage1.getChildren().add(imageView1);
    buttonImage2.getChildren().add(button2);
    buttonImage2.getChildren().add(imageView2);
    buttonImage3.getChildren().add(button3);
    buttonImage3.getChildren().add(imageView3);
    buttonImage4.getChildren().add(button4);
    buttonImage4.getChildren().add(imageView4);

    buttonContainer.getChildren().addAll(buttonImage1, buttonImage2, buttonImage3, buttonImage4);

    Scene scene = new Scene(this.vbox);

    primaryStage.setScene(scene);
    primaryStage.setResizable(false);

    Stage firstStage = new Stage();
    firstStage.setTitle("Simulador de Trem");

    Button startButton = new Button("Start");  

    startButton.setStyle("-fx-background-color: #4CAF50;"
        + "-fx-text-fill: white;"
        + "-fx-font-size: 16px;" 
        + "-fx-padding: 10px 20px;"); 

    HBox startHbox = new HBox(10);
    startHbox.setAlignment(Pos.BOTTOM_CENTER);
    startHbox.setPadding(new Insets(0, 0, 20, 0));
    startHbox.getChildren().add(startButton);

    Scene firstScene = new Scene(startHbox,700,400);

    Image background = new Image("/img/startbackground.png");
    BackgroundImage backgroundImg = new BackgroundImage(background,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
            false, false, true, true));

    startHbox.setBackground(new Background(backgroundImg));


    firstStage.setScene(firstScene);
    firstStage.setResizable(false);
    firstStage.show();

    // Obter o tamanho da janela
    double sceneHeight = firstStage.getHeight();

    // Calcular a posicao Y
    double centerY = (this.screenBounds.getHeight() - sceneHeight) / 2.05;

    firstStage.setY(centerY / 4);

    startButton.setOnAction(e -> {
        primaryStage.show();
        primaryStage.setX(firstStage.getX());
        primaryStage.setY(centerY / 4);
        firstStage.close();
    });// fim do metodo startButton.setOnAction

  }// fim do metodo start

  /** ********************************************************************
  * Metodo: createRectangle
  * Funcao: Cria um retangulo com as dimensoes especificadas e preenche-o 
  * com uma imagem.
  * Parametros: @param x eh a coordenada x do retangulo, @param y eh a 
  * coordenada y do retangulo, @param width eh a largura do retangulo,
  * @param height eh a altura do retangulo e @param imagePath eh o caminho
  * para a imagem a ser usada como preenchimento do retangulo.
  * que representa a janela principal.
  * Retorno: @return Um objeto Rectangle preenchido com a imagem especificada.
  ********************************************************************* */
  private static Rectangle createRectangle(double x, double y, double width,
      double height, String imagePath) {
    Rectangle rectangle = new Rectangle(x, y, width, height);
    Image img = new Image(imagePath);
    rectangle.setFill(new ImagePattern(img));
    return rectangle;
  }// fim do metodo createRectangle

  /** ********************************************************************
  * Metodo: createStyledButton
  * Funcao: Metodo para criar um botao estilizado.
  * Parametros: @param text eh a opcao (direcao) para o texto do botao.
  * Retorno: @return Botao estilizado.
  ******************************************************************** */
  private Button createStyledButton(Option text) {
    Button button = new Button(text.getTitle());
    button.setStyle("-fx-background-color: #4CAF50;"
        + "-fx-text-fill: white;"
        + "-fx-font-size: 14px;");
    button.setOnAction(e -> {
      openNewScreen(text);
      this.blueSpeedSlider.setValue(DEFAULT_SPEED);
      this.redSpeedSlider.setValue(DEFAULT_SPEED);
      this.pauseTrains1.set(false);
      this.pauseTrains2.set(false);
    });// fim do metodo button.setOnAction
    return button;
  }// fim do metodo createStyledButton
  
  /** ********************************************************************
  * Metodo: openNewScreen
  * Funcao: Metodo privado para abrir uma nova tela com base na opcao (direcao)
  * fornecida, aonde toda a simulacao do trem vai ser executada.
  * Parametros: @param message eh a opcao (direcao) que determina o titulo
  * da nova tela e o caminho a ser seguido, sendo base para os switchs.
  * Retorno: @return void
  ******************************************************************** */
  private void openNewScreen(Option message) {
    if (this.StageNew != null) {
      // Remover todos os elementos da tela
      this.vbox.getChildren().removeAll(this.textContainer, this.blueSpeedSlider, 
        this.redSpeedSlider, this.resetButton, this.hbox, this.checkBox);
    }// fim do if 
    else {
      this.StageNew = new Stage();
      this.StageNew.setResizable(false);
      this.StageNew.setY(80);
      this.StageNew.setX(this.screenBounds.getWidth() / 15);
    }// fim do else

    if (this.collisionDetectionThread != null) {
      this.collisionDetectionThread.interrupt();
      try {
        this.collisionDetectionThread.join(); // Esperar a thread terminar
      }// fim do try 
      catch (InterruptedException e) {
        e.printStackTrace();
      }// fim do catch
      this.collisionDetectionThread = null; // Resetar a thread
    }// fim do if

    switch (message) {
      case OP1:
        this.StageNew.setTitle("Mesma Direcao");
        break;
      case OP2:
        this.StageNew.setTitle("Mesma Direcao Inversa");
        break;
      case OP3:
        this.StageNew.setTitle("Direcao Oposta");
        break;
      case OP4:
        this.StageNew.setTitle("Direcao Oposta Inversa");
        break;
      default:
        break;
    }// fim do switch

    Pane root = new Pane();
    Scene scene = new Scene(root, 500, 900);

    this.blueSpeedSlider = new Slider(0, 100, DEFAULT_SPEED); 
    this.blueSpeedSlider.setMinWidth(300);
    this.blueSpeedSlider.setMaxWidth(300);
    this.blueSpeedSlider.setShowTickMarks(true);
    this.blueSpeedSlider.setShowTickLabels(true);
    this.blueSpeedSlider.setStyle("-fx-control-inner-background: #2196F3;"
        + "-fx-background-color: transparent;");

    this.redSpeedSlider = new Slider(0, 100, DEFAULT_SPEED); 
    this.redSpeedSlider.setMinWidth(300);
    this.redSpeedSlider.setMaxWidth(300);
    this.redSpeedSlider.setShowTickMarks(true);
    this.redSpeedSlider.setShowTickLabels(true);
    this.redSpeedSlider.setStyle("-fx-control-inner-background: #F44336;"
        + "-fx-background-color: transparent;");

    Image background = new Image("/img/background.png");
    BackgroundImage backgroundImg = new BackgroundImage(background,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
            false, false, true, true));

    root.setBackground(new Background(backgroundImg));

    this.resetButton = new Button("Reset");
    this.resetButton.setStyle("-fx-background-color: #F44336;"
        + "-fx-text-fill: white;"
        + "-fx-font-size: 14px;");

    Text painelText = new Text("PAINEL DE CONTROLE");
    painelText.setFont(Font.font("Arial", FontWeight.BOLD, 25));

    this.textContainer = new VBox(painelText);
    this.textContainer.setAlignment(Pos.CENTER); 
    this.textContainer.setPadding(new Insets(10, 0, 0, 0)); 

    this.primaryStage.setHeight(440);

    this.vbox.getChildren().add(this.textContainer);

    Text blueTrainLabel = new Text("Velocidade do Trem Azul");
    blueTrainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16)); 
    Text redTrainLabel = new Text("Velocidade do Trem Vermelho");
    redTrainLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16)); 

    this.blueSliderVbox = new VBox(10);
    this.blueSliderVbox.setAlignment(Pos.CENTER);
    this.blueSliderVbox.setPadding(new Insets(10, 0, 0, 0));

    this.blueSliderVbox.getChildren().addAll(blueTrainLabel, this.blueSpeedSlider);

    this.redSliderVbox = new VBox(10);
    this.redSliderVbox.setAlignment(Pos.CENTER);
    this.redSliderVbox.setPadding(new Insets(10, 0, 0, 0));

    this.redSliderVbox.getChildren().addAll(redTrainLabel, this.redSpeedSlider);

    this.checkBox = new CheckBox("Funcao Experimental de Anti-Colisao Automatica");
    this.checkBox.setStyle("-fx-font-size: 16px;"
        + "-fx-font-family: 'Arial';"
        + "-fx-text-fill: #333333;"
        + "-fx-font-weight: bold;"
        + "-fx-padding: 10px;"
        + "-fx-background-color: #f2f2f2;"
        + "-fx-border-color: #cccccc;"
        + "-fx-border-width: 2px;"
        + "-fx-border-radius: 5px;"
        + "-fx-cursor: hand;");

    this.vbox.getChildren().add(this.checkBox);

    this.hbox = new HBox(10);
    this.hbox.setAlignment(Pos.CENTER); 

    this.hbox.getChildren().add(this.blueSliderVbox);
    this.hbox.getChildren().add(this.redSliderVbox);

    this.vbox.getChildren().add(this.hbox);
    this.vbox.getChildren().add(this.resetButton);

    Rectangle blueTrain = createRectangle(-200, -200, 90, 30, "/img/bluetrain.png");
    Rectangle redTrain = createRectangle(-200, -200, 90, 30, "/img/redtrain.png");
    Rectangle carro1 = createRectangle(20, 20, 40, 80, "/img/car1.png");
    Rectangle carro2 = createRectangle(20, 20, 40, 80, "/img/car2.png");
    Rectangle carro3 = createRectangle(20, 20, 40, 80, "/img/car3.png");
    Rectangle carro4 = createRectangle(20, 20, 40, 120, "/img/car4.png");
    Rectangle carro5 = createRectangle(20, 20, 40, 80, "/img/car5.png");
    Rectangle carro6 = createRectangle(20, 20, 40, 60, "/img/car6.png");
    Rectangle carro7 = createRectangle(20, 20, 40, 60, "/img/car7.png");
    Rectangle carro8 = createRectangle(20, 20, 40, 80, "/img/car8.png");
    Rectangle carro9 = createRectangle(20, 20, 40, 60, "/img/car9.png");
    Rectangle carro10 = createRectangle(20, 20, 40, 60, "/img/car10.png");
    Rectangle carro11 = createRectangle(20, 20, 40, 80, "/img/car11.png");
    Rectangle carro12 = createRectangle(20, 20, 40, 90, "/img/car12.png");
    Rectangle carro13 = createRectangle(20, 20, 40, 80, "/img/car13.png");
    Rectangle carro14 = createRectangle(20, 20, 40, 90, "/img/car14.png");
    Rectangle carro15 = createRectangle(20, 20, 40, 80, "/img/car15.png");
    Rectangle carro16 = createRectangle(20, 20, 30, 90, "/img/car16.png");
    Rectangle carro17 = createRectangle(20, 20, 40, 80, "/img/car17.png");
    Rectangle carro18 = createRectangle(20, 20, 40, 60, "/img/car18.png");
    Rectangle carro19 = createRectangle(20, 20, 40, 80, "/img/car19.png");
    Rectangle carro20 = createRectangle(20, 20, 40, 80, "/img/car20.png");

    ArrayList<Rectangle> carros = new ArrayList<Rectangle>();
    Duration[] durations = new Duration[20]; // Array de duracoes para os carros

    Rectangle[] carrosArray = { carro1, carro2, carro3, carro4, carro5, carro6,
        carro7, carro8, carro9, carro10, carro11, carro12, carro13, carro14,
        carro15, carro16, carro17, carro18, carro19, carro20 };

    for (int i = 0; i < 20; i++) {
      carros.add(carrosArray[i]); 
      durations[i] = Duration.seconds(i % 6 == 0 ? 30 : 29); 
    }// fim do for

    this.StageNew.setScene(scene);
    this.StageNew.show();

    Path pathIda = new Path(
        new MoveTo(50, -50),
        new VLineTo(100), 
        new CubicCurveTo(50, 160, 100, 160, 100, 200),
        new VLineTo(280), 
        new CubicCurveTo(100, 340, 150, 320, 150, 400),
        new VLineTo(500),
        new CubicCurveTo(150, 560, 100, 560, 100, 600),
        new VLineTo(680), 
        new CubicCurveTo(100, 740, 50, 720, 50, 800),
        new VLineTo(950), 
        new MoveTo(50, 800)
    );// fim do pathIda

    Path pathVolta = new Path(
        new MoveTo(50, 950),
        new VLineTo(800),
        new CubicCurveTo(50, 720, 100, 740, 100, 680),
        new VLineTo(600),
        new CubicCurveTo(100, 560, 150, 560, 150, 500),
        new VLineTo(400),
        new CubicCurveTo(150, 320, 100, 340, 100, 280),
        new VLineTo(200),
        new CubicCurveTo(100, 160, 50, 160, 50, 100),
        new VLineTo(-50),
        new MoveTo(50, -50)
    );// fim do pathVolta

    Path pathCarros = new Path(
        new MoveTo(465, 950),
        new VLineTo(-50)
    );// fim do pathCarros

    Random random = new Random();

    // Cria uma nova instancia da classe Timeline para controlar a animacao dos carros
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(3.5), event -> {
          int newRandomIndex = random.nextInt(carros.size());
          Rectangle newCar = carros.get(newRandomIndex);

          if (!root.getChildren().contains(newCar)) {
            newCar.setX(465);
            newCar.setY(950);
            root.getChildren().add(newCar);
            PathTransition pathTransitionCarro = new PathTransition(
              durations[newRandomIndex], pathCarros, newCar);
            pathTransitionCarro.setInterpolator(Interpolator.LINEAR);
            pathTransitionCarro.play();

            pathTransitionCarro.setOnFinished(event2 -> {
              root.getChildren().remove(newCar);
            });// fim do metodo pathTransitionCarro.setOnFinished
          }// fim do if
        }));// fim do new Timeline

    timeline.setCycleCount(Animation.INDEFINITE); 
    timeline.play();

    Path mirroredPathIda = new Path();

    for (PathElement element : pathIda.getElements()) {
      if (element instanceof MoveTo) {
        mirroredPathIda.getElements().add(new MoveTo(
          2 * (100 - ((MoveTo) element).getX()) + ((MoveTo) element).getX(),
          ((MoveTo) element).getY()));
      }// fim do if 
      else if (element instanceof LineTo) {
        mirroredPathIda.getElements().add(new LineTo(
          2 * (100 - ((LineTo) element).getX()) + ((LineTo) element).getX(),
          ((LineTo) element).getY()));
      }// fim do else if 
      else if (element instanceof VLineTo) {
        mirroredPathIda.getElements().add(new VLineTo(((VLineTo) element).getY()));
      }// fim do else if 
      else if (element instanceof CubicCurveTo) {
        CubicCurveTo cubicCurve = (CubicCurveTo) element;
        mirroredPathIda.getElements().add(new CubicCurveTo(
          2 * (100 - cubicCurve.getControlX1()) + cubicCurve.getControlX1(),
          cubicCurve.getControlY1(),
          2 * (100 - cubicCurve.getControlX2()) + cubicCurve.getControlX2(),
          cubicCurve.getControlY2(),
          2 * (100 - cubicCurve.getX()) + cubicCurve.getX(),
          cubicCurve.getY()));
      }// fim do else if
    }// fim do for

    Path mirroredPathVolta = new Path();

    for (PathElement element : pathVolta.getElements()) {
      if (element instanceof MoveTo) {
        mirroredPathVolta.getElements().add(new MoveTo(
          2 * (100 - ((MoveTo) element).getX()) + ((MoveTo) element).getX(),
          ((MoveTo) element).getY()));
      }// fim do if
      else if (element instanceof LineTo) {
        mirroredPathVolta.getElements().add(new LineTo(
          2 * (100 - ((LineTo) element).getX()) + ((LineTo) element).getX(),
          ((LineTo) element).getY()));
      }// fim do else if
      else if (element instanceof VLineTo) {
        mirroredPathVolta.getElements().add(new VLineTo(((VLineTo) element).getY()));
      }// fim do else if 
      else if (element instanceof CubicCurveTo) {
        CubicCurveTo cubicCurve = (CubicCurveTo) element;
        mirroredPathVolta.getElements().add(new CubicCurveTo(
          2 * (100 - cubicCurve.getControlX1()) + cubicCurve.getControlX1(),
          cubicCurve.getControlY1(),
          2 * (100 - cubicCurve.getControlX2()) + cubicCurve.getControlX2(),
          cubicCurve.getControlY2(),
          2 * (100 - cubicCurve.getX()) + cubicCurve.getX(),
          cubicCurve.getY()));
      }// fim do else if
    }// fim do for

    pathIda.setStroke(Color.BLACK);
    pathIda.setStrokeWidth(2);

    mirroredPathIda.setStroke(Color.BLACK);
    mirroredPathIda.setStrokeWidth(2);

    root.getChildren().add(pathIda);
    root.getChildren().add(mirroredPathIda);

    root.getChildren().add(blueTrain);
    root.getChildren().add(redTrain);

    PathTransition pathTransition1 = new PathTransition();
    PathTransition pathTransition2 = new PathTransition();

    Task<Void> collisionDetectionTask = new Task<Void>() {
      @Override
      protected Void call() throws Exception {
        while (!Thread.currentThread().isInterrupted()) {
          if (checkBox.isSelected()) {
            Platform.runLater(() -> {
              if (pauseTrains1.get()) {
                pathTransition1.pause();
              }// fim do if 
              else if (pauseTrains2.get()) {
                pathTransition2.pause();
              }// fim do else if
              else {
                pathTransition1.play();
                pathTransition2.play();
              }// fim do else
            });

            // Obter as posicoes dos retangulos dos trens
            Point2D rectPosition1 = new Point2D(blueTrain.getTranslateX(),
                blueTrain.getTranslateY());
            Point2D rectPosition2 = new Point2D(redTrain.getTranslateX(),
                redTrain.getTranslateY());

            // Calcular a distancia entre os retangulos
            double distance = rectPosition1.distance(rectPosition2);

            double safeDistance = 90;

            if (pathTransition1.getDuration().toMillis() > 1000) {
              switch (message) {
                case OP1:
                  if (distance < safeDistance) {
                    if (blueTrain.getTranslateY() > redTrain.getTranslateY()) {
                      pauseTrains2.set(true);
                    }// fim do if
                    else {
                      pauseTrains1.set(true);
                    }// fim do else
                  }// fim do if 
                  else {
                    pauseTrains1.set(false); 
                    pauseTrains2.set(false);
                  }// fim do else
                  break;
                case OP2:
                  if (distance < safeDistance) {
                    if (blueTrain.getTranslateY() < redTrain.getTranslateY()) {
                      pauseTrains2.set(true);
                    }// fim do if 
                    else {
                      pauseTrains1.set(true);
                    }// fim do else
                  }// fim do if 
                  else {
                    pauseTrains1.set(false); 
                    pauseTrains2.set(false);
                  }// fim do else
                  break;
                case OP3:
                  /* continua para o OP4 */
                case OP4:
                  if (blueTrain.getTranslateY() > 660
                      && blueTrain.getTranslateY() < 1000
                      && redTrain.getTranslateY() > 660
                      && redTrain.getTranslateY() < 1000) {
                    if (redTrain.getTranslateY() < 1000
                        && redTrain.getTranslateY() > 920) {
                      pauseTrains2.set(true);
                    }// fim do if 
                    else {
                      pauseTrains1.set(true);
                    }// fim do else
                  }// fim do if 
                  else if (blueTrain.getTranslateY() > 250
                      && blueTrain.getTranslateY() < 640
                      && redTrain.getTranslateY() > 250
                      && redTrain.getTranslateY() < 640) {
                    if (redTrain.getTranslateY() < 640
                        && redTrain.getTranslateY() > 480) {
                      pauseTrains2.set(true);
                    }// fim do if 
                    else {
                      pauseTrains1.set(true);
                    }// fim do else
                  }// fim do else if
                  else {
                    pauseTrains1.set(false); 
                    pauseTrains2.set(false);
                  }// fim do else
                  break;
                default:
                  break;
              }// fim do switch
            }// fim do if

            try {
              Thread.sleep(100); 
            }// fim do try 
            catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }// fim do catch
          }// fim do if
        }// fim do while
        return null;
      }// fim do metodo call
    };// fim do metodo collisionDetectionTask

    this.checkBox.setOnAction(event -> {
      if (this.checkBox.isSelected()) {
        this.collisionDetectionThread = new Thread(collisionDetectionTask);
        this.collisionDetectionThread.setDaemon(true);
        this.collisionDetectionThread.start();
      }// fim do if
    });// fim do metodo checkBox.setOnAction

    this.resetButton.setOnAction(e -> {
      this.pauseTrains1.set(false);
      this.pauseTrains2.set(false);

      this.blueSpeedSlider.setValue(DEFAULT_SPEED);
      this.redSpeedSlider.setValue(DEFAULT_SPEED);

      pathTransition1.stop();
      pathTransition1.play();

      pathTransition2.stop();
      pathTransition2.play();
    });// fim do metodo resetButton.setOnAction

    DoubleProperty dividedRateProperty = new SimpleDoubleProperty();
    dividedRateProperty.bind(Bindings.divide(this.blueSpeedSlider.valueProperty(), 30.0));
    pathTransition1.rateProperty().bind(dividedRateProperty);
    pathTransition1.setNode(blueTrain);
    pathTransition1.setDuration(Duration.seconds(4));
    pathTransition1.setCycleCount(1); 
    pathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    pathTransition1.setInterpolator(Interpolator.LINEAR);

    DoubleProperty dividedRateProperty2 = new SimpleDoubleProperty();
    dividedRateProperty2.bind(Bindings.divide(this.redSpeedSlider.valueProperty(), 30.0));
    pathTransition2.rateProperty().bind(dividedRateProperty2);
    pathTransition2.setNode(redTrain);
    pathTransition2.setDuration(Duration.seconds(4));
    pathTransition2.setCycleCount(1);
    pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    pathTransition2.setInterpolator(Interpolator.LINEAR);

    pathTransition1.setOnFinished(event -> {
      pathTransition1.play(); 
    });// fim do metodo pathTransition1.setOnFinished

    pathTransition2.setOnFinished(event -> {
      pathTransition2.play(); 
    });// fim do metodo pathTransition2.setOnFinished

    switch (message) {
      case OP1:
        pathTransition1.setPath(pathIda);
        pathTransition1.play();

        pathTransition2.setPath(mirroredPathIda);
        pathTransition2.play();
        break;
      case OP2:
        pathTransition1.setPath(pathVolta);
        pathTransition1.play();

        pathTransition2.setPath(mirroredPathVolta);
        pathTransition2.play();
        break;
      case OP3:
        pathTransition1.setPath(pathIda);
        pathTransition1.play();

        pathTransition2.setPath(mirroredPathVolta);
        pathTransition2.play();
        break;
      case OP4:
        pathTransition1.setPath(mirroredPathIda);
        pathTransition1.play();

        pathTransition2.setPath(pathVolta);
        pathTransition2.play();
        break;
      default:
        break;
    }// fim do switch
  }// fim do metodo openNewScreen

  /** ********************************************************************
  * Metodo: main
  * Funcao: Metodo principal para iniciar o programa.
  * Parametros: @param args sao os argumentos da linha de comando.
  * Retorno: @return void
  ******************************************************************** */
  public static void main(String[] args) {
    launch(args);
  }// fim do metodo main
}// fim da classe MainApp
