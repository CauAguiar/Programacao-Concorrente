/* *********************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 30/09/2023
* Ultima alteracao.: 07/10/2023
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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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
  private Slider blueSpeedSlider; // Slider do trem azul
  private Slider redSpeedSlider; // Slider do trem vermelho
  private Rectangle2D screenBounds; // Limites da tela
  private TremDaEsquerda Te; // Thread do trem da esquerda
  private TremDaDireita Td; // Thread do trem da direita
  private VariavelDeTravamento VT; // Thread da variavel de travamento
  private EstritaAlternancia EA; // Thread da estrita alternancia
  private SolucaoPeterson peterson; // Thread da solucao de Peterson
  private Label titleLabel; // Label para o titulo do ToggleGroup
  private RadioButton option1; // RadioButton para a opcao 1 "Nenhum"
  private RadioButton option3; // RadioButton para a opcao 3 "Variavel de Travamento"
  private RadioButton option2; // RadioButton para a opcao 2 "Estrita Alternancia"
  private RadioButton option4; // RadioButton para a opcao 4 "Solucao de Peterson"


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

  /**
   * ********************************************************************
   * Metodo: start
   * Funcao: Metodo de inicializacao da aplicacao JavaFX.
   * Parametros: @param primaryStage eh o Stage principal da aplicacao,
   * que representa a janela principal.
   * Retorno: @return void
   */
  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    primaryStage.setTitle("Simulador de Trem");

    // Tamanho da tela
    this.screenBounds = Screen.getPrimary().getVisualBounds();

    Text directionText = new Text("Selecione uma das direcoes para o trem:");
    directionText.setFont(Font.font("Arial", FontWeight.BOLD, 18));

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

    Image image1 = new Image("/downdown.png");
    ImageView imageView1 = new ImageView(image1);

    Image image2 = new Image("/upup.png");
    ImageView imageView2 = new ImageView(image2);

    Image image3 = new Image("/downup.png");
    ImageView imageView3 = new ImageView(image3);

    Image image4 = new Image("/updown.png");
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

    Scene firstScene = new Scene(startHbox, 700, 400);

    Image background = new Image("/startbackground.png");
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

  /**
   * ********************************************************************
   * Metodo: createRectangle
   * Funcao: Cria um retangulo com as dimensoes especificadas e preenche-o
   * com uma imagem.
   * Parametros: @param x eh a coordenada x do retangulo, @param y eh a
   * coordenada y do retangulo, @param width eh a largura do retangulo,
   * 
   * @param height eh a altura do retangulo e @param imagePath eh o caminho
   *               para a imagem a ser usada como preenchimento do retangulo.
   *               que representa a janela principal.
   *               Retorno: @return Um objeto Rectangle preenchido com a imagem
   *               especificada.
   */
  private static Rectangle createRectangle(double x, double y, double width,
      double height, String imagePath) {
    Rectangle rectangle = new Rectangle(x, y, width, height);
    Image img = new Image(imagePath);
    rectangle.setFill(new ImagePattern(img));
    return rectangle;
  }// fim do metodo createRectangle

  /**
   * ********************************************************************
   * Metodo: createStyledButton
   * Funcao: Metodo para criar um botao estilizado.
   * Parametros: @param text eh a opcao (direcao) para o texto do botao.
   * Retorno: @return Botao estilizado.
   */
  private Button createStyledButton(Option text) {
    Button button = new Button(text.getTitle());
    button.setStyle("-fx-background-color: #4CAF50;"
        + "-fx-text-fill: white;"
        + "-fx-font-size: 14px;");
    button.setOnAction(e -> {
      if (Te != null && Te.isAlive()) {
        Te.interrupt(); // Interrompe a thread existente se estiver em execucao
      }
      if (Td != null && Td.isAlive()) {
        Td.interrupt(); // Interrompe a thread existente se estiver em execucao
      }
      openNewScreen(text);
      this.blueSpeedSlider.setValue(DEFAULT_SPEED);
      this.redSpeedSlider.setValue(DEFAULT_SPEED);
    });// fim do metodo button.setOnAction
    return button;
  }// fim do metodo createStyledButton

  /**
   * ********************************************************************
   * Metodo: openNewScreen
   * Funcao: Metodo privado para abrir uma nova tela com base na opcao
   * fornecida, aonde toda a simulacao do trem vai ser executada.
   * Parametros: @param message eh a opcao que determina o titulo
   * da nova tela e o caminho a ser seguido, sendo base para os switchs.
   * Retorno: @return void
   */
  private void openNewScreen(Option message) {
    if (this.StageNew != null) {
      // Remover todos os elementos da tela
      this.vbox.getChildren().removeAll(this.textContainer, this.blueSpeedSlider,
          this.redSpeedSlider, this.resetButton, this.hbox, this.titleLabel, this.option1, this.option2, this.option3, this.option4);

    } // fim do if
    else {
      this.StageNew = new Stage();
      this.StageNew.setResizable(false);
      this.StageNew.setY(80);
      this.StageNew.setX(this.screenBounds.getWidth() / 15);
    } // fim do else

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

    Image background = new Image("/background.png");
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

    this.primaryStage.setHeight(580);

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

    this.titleLabel = new Label("Selecione uma opcao para anti-colisao:");
    this.titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

    ToggleGroup toggleGroup = new ToggleGroup();

    this.option1 = new RadioButton("Nenhum");
    option1.setToggleGroup(toggleGroup);
    option1.setSelected(true);
    option1.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333; -fx-font-weight: bold;");

    this.option2 = new RadioButton("Variavel de Travamento");
    option2.setToggleGroup(toggleGroup);
    option2.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333; -fx-font-weight: bold;");

    this.option3 = new RadioButton("Estrita Alternancia");
    option3.setToggleGroup(toggleGroup);
    option3.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333; -fx-font-weight: bold;");

    this.option4 = new RadioButton("Solucao de Peterson");
    option4.setToggleGroup(toggleGroup);
    option4.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333; -fx-font-weight: bold;");

    this.vbox.getChildren().addAll(titleLabel, option1, option2, option3, option4);

    this.hbox = new HBox(10);
    this.hbox.setAlignment(Pos.CENTER);

    this.hbox.getChildren().add(this.blueSliderVbox);
    this.hbox.getChildren().add(this.redSliderVbox);

    this.vbox.getChildren().add(this.hbox);
    this.vbox.getChildren().add(this.resetButton);

    Rectangle blueTrain = createRectangle(-200, -200, 90, 30, "/bluetrain.png");
    Rectangle redTrain = createRectangle(-200, -200, 90, 30, "/redtrain.png");
    Rectangle carro1 = createRectangle(20, 20, 40, 80, "/car1.png");
    Rectangle carro2 = createRectangle(20, 20, 40, 80, "/car2.png");
    Rectangle carro3 = createRectangle(20, 20, 40, 80, "/car3.png");
    Rectangle carro4 = createRectangle(20, 20, 40, 120, "/car4.png");
    Rectangle carro5 = createRectangle(20, 20, 40, 80, "/car5.png");
    Rectangle carro6 = createRectangle(20, 20, 40, 60, "/car6.png");
    Rectangle carro7 = createRectangle(20, 20, 40, 60, "/car7.png");
    Rectangle carro8 = createRectangle(20, 20, 40, 80, "/car8.png");
    Rectangle carro9 = createRectangle(20, 20, 40, 60, "/car9.png");
    Rectangle carro10 = createRectangle(20, 20, 40, 60, "/car10.png");
    Rectangle carro11 = createRectangle(20, 20, 40, 80, "/car11.png");
    Rectangle carro12 = createRectangle(20, 20, 40, 90, "/car12.png");
    Rectangle carro13 = createRectangle(20, 20, 40, 80, "/car13.png");
    Rectangle carro14 = createRectangle(20, 20, 40, 90, "/car14.png");
    Rectangle carro15 = createRectangle(20, 20, 40, 80, "/car15.png");
    Rectangle carro16 = createRectangle(20, 20, 30, 90, "/car16.png");
    Rectangle carro17 = createRectangle(20, 20, 40, 80, "/car17.png");
    Rectangle carro18 = createRectangle(20, 20, 40, 60, "/car18.png");
    Rectangle carro19 = createRectangle(20, 20, 40, 80, "/car19.png");
    Rectangle carro20 = createRectangle(20, 20, 40, 80, "/car20.png");

    ArrayList<Rectangle> carros = new ArrayList<Rectangle>();
    Duration[] durations = new Duration[20]; // Array de duracoes para os carros

    Rectangle[] carrosArray = { carro1, carro2, carro3, carro4, carro5, carro6,
        carro7, carro8, carro9, carro10, carro11, carro12, carro13, carro14,
        carro15, carro16, carro17, carro18, carro19, carro20 };

    for (int i = 0; i < 20; i++) {
      carros.add(carrosArray[i]);
      durations[i] = Duration.seconds(i % 6 == 0 ? 30 : 29);
    } // fim do for

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
        new MoveTo(50, 800));// fim do pathIda

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
        new MoveTo(50, -50));// fim do pathVolta

    Path pathCarros = new Path(
        new MoveTo(465, 950),
        new VLineTo(-50));// fim do pathCarros

    Random random = new Random();

    // Cria uma nova instancia da classe Timeline para controlar a animacao dos
    // carros
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
          } // fim do if
        }));// fim do new Timeline

    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();

    Path mirroredPathIda = new Path();

    for (PathElement element : pathIda.getElements()) {
      if (element instanceof MoveTo) {
        mirroredPathIda.getElements().add(new MoveTo(
            2 * (100 - ((MoveTo) element).getX()) + ((MoveTo) element).getX(),
            ((MoveTo) element).getY()));
      } // fim do if
      else if (element instanceof LineTo) {
        mirroredPathIda.getElements().add(new LineTo(
            2 * (100 - ((LineTo) element).getX()) + ((LineTo) element).getX(),
            ((LineTo) element).getY()));
      } // fim do else if
      else if (element instanceof VLineTo) {
        mirroredPathIda.getElements().add(new VLineTo(((VLineTo) element).getY()));
      } // fim do else if
      else if (element instanceof CubicCurveTo) {
        CubicCurveTo cubicCurve = (CubicCurveTo) element;
        mirroredPathIda.getElements().add(new CubicCurveTo(
            2 * (100 - cubicCurve.getControlX1()) + cubicCurve.getControlX1(),
            cubicCurve.getControlY1(),
            2 * (100 - cubicCurve.getControlX2()) + cubicCurve.getControlX2(),
            cubicCurve.getControlY2(),
            2 * (100 - cubicCurve.getX()) + cubicCurve.getX(),
            cubicCurve.getY()));
      } // fim do else if
    } // fim do for

    Path mirroredPathVolta = new Path();

    for (PathElement element : pathVolta.getElements()) {
      if (element instanceof MoveTo) {
        mirroredPathVolta.getElements().add(new MoveTo(
            2 * (100 - ((MoveTo) element).getX()) + ((MoveTo) element).getX(),
            ((MoveTo) element).getY()));
      } // fim do if
      else if (element instanceof LineTo) {
        mirroredPathVolta.getElements().add(new LineTo(
            2 * (100 - ((LineTo) element).getX()) + ((LineTo) element).getX(),
            ((LineTo) element).getY()));
      } // fim do else if
      else if (element instanceof VLineTo) {
        mirroredPathVolta.getElements().add(new VLineTo(((VLineTo) element).getY()));
      } // fim do else if
      else if (element instanceof CubicCurveTo) {
        CubicCurveTo cubicCurve = (CubicCurveTo) element;
        mirroredPathVolta.getElements().add(new CubicCurveTo(
            2 * (100 - cubicCurve.getControlX1()) + cubicCurve.getControlX1(),
            cubicCurve.getControlY1(),
            2 * (100 - cubicCurve.getControlX2()) + cubicCurve.getControlX2(),
            cubicCurve.getControlY2(),
            2 * (100 - cubicCurve.getX()) + cubicCurve.getX(),
            cubicCurve.getY()));
      } // fim do else if
    } // fim do for

    pathIda.setStroke(Color.BLACK);
    pathIda.setStrokeWidth(2);

    mirroredPathIda.setStroke(Color.BLACK);
    mirroredPathIda.setStrokeWidth(2);

    root.getChildren().add(pathIda);
    root.getChildren().add(mirroredPathIda);

    root.getChildren().add(blueTrain);
    root.getChildren().add(redTrain);

    Te = new TremDaEsquerda(blueTrain, blueSpeedSlider);
    Td = new TremDaDireita(redTrain, redSpeedSlider);

    // Adicionar um ChangeListener ao ToggleGroup
    toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      public void changed(ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) {
        if (newToggle == null) {
          // Nenhum RadioButton selecionado

        } else if (newToggle == option1) {  
          if (VT != null) {  
            VT.encerrarExclusaoMutua();
          } // fim do if
          if (peterson != null) {
            peterson.sairRegiaoCritica(1);
            peterson.sairRegiaoCritica(0);
            peterson.sairRegiaoCritica2(1);
            peterson.sairRegiaoCritica2(0);
            peterson.encerrarExclusaoMutua();
          } // fim do if
          if (EA != null) {
            EA.encerrarExclusaoMutua();
          } // fim do if
          Te.setExclusaoMutua(null);
          Te.setEstritaAlternancia(null);
          Te.setSolucaoPeterson(null);
          Td.setExclusaoMutua(null);
          Td.setEstritaAlternancia(null);
          Td.setSolucaoPeterson(null);
          resetButtonAction(); 

        } else if (newToggle == option2) { 

          if (VT != null) {
            VT.encerrarExclusaoMutua();
          } // fim do if
          if (peterson != null) {
            peterson.sairRegiaoCritica(1);
            peterson.sairRegiaoCritica(0);
            peterson.sairRegiaoCritica2(1);
            peterson.sairRegiaoCritica2(0);
            peterson.encerrarExclusaoMutua();
          } // fim do if
          if (EA != null) {
            EA.encerrarExclusaoMutua();
          } // fim do if

          Te.setExclusaoMutua(null);
          Te.setEstritaAlternancia(null);
          Td.setEstritaAlternancia(null);
          Td.setSolucaoPeterson(null);

          VT = new VariavelDeTravamento(); // Cria uma nova instancia da classe VariavelDeTravamento
          resetButtonAction();
          Te.setExclusaoMutua(VT);
          Td.setExclusaoMutua(VT);

        } else if (newToggle == option3) {

          if (EA != null) {
            EA.encerrarExclusaoMutua();
          } // fim do if
          if (VT != null) {
            VT.encerrarExclusaoMutua();
          } // fim do if
          if (peterson != null) {
            peterson.sairRegiaoCritica(1);
            peterson.sairRegiaoCritica(0);
            peterson.sairRegiaoCritica2(1);
            peterson.sairRegiaoCritica2(0);
            peterson.encerrarExclusaoMutua();
          } // fim do if

          Te.setExclusaoMutua(null);
          Te.setSolucaoPeterson(null);
          Td.setExclusaoMutua(null);
          Td.setSolucaoPeterson(null);

          EA = new EstritaAlternancia(); // Cria uma nova instancia da classe EstritaAlternancia
          resetButtonAction();
          Te.setEstritaAlternancia(EA);
          Td.setEstritaAlternancia(EA);

        } else if (newToggle == option4) {

          if (peterson != null) {
            peterson.sairRegiaoCritica(1);
            peterson.sairRegiaoCritica(0);
            peterson.sairRegiaoCritica2(1);
            peterson.sairRegiaoCritica2(0);
            peterson.encerrarExclusaoMutua();
          } // fim do if
          if (EA != null) {
            EA.encerrarExclusaoMutua();
          } // fim do if
          if (VT != null) {
            VT.encerrarExclusaoMutua();
          } // fim do if

          Te.setExclusaoMutua(null);
          Te.setEstritaAlternancia(null);
          Td.setExclusaoMutua(null);
          Td.setEstritaAlternancia(null);

          peterson = new SolucaoPeterson(); // Cria uma nova instancia da classe SolucaoPeterson
          resetButtonAction();
          Te.setSolucaoPeterson(peterson);
          Td.setSolucaoPeterson(peterson);

        } // fim do else if
      } // fim do metodo changed
    }); // fim do toggleGroup.selectedToggleProperty().addListener

    switch (message) {
      case OP1:
        Te.setPath(pathIda);
        Te.start();

        Td.setPath(mirroredPathIda);
        Td.start();
        break;
      case OP2:
        Te.setPath(pathVolta);
        Te.start();

        Td.setPath(mirroredPathVolta);
        Td.start();
        break;
      case OP3:
        Te.setPath(pathIda);
        Te.start();

        Td.setPath(mirroredPathVolta);
        Td.start();
        break;
      case OP4:
        Te.setPath(mirroredPathIda);
        Te.start();

        Td.setPath(pathVolta);
        Td.start();
        break;
      default:
        break;
    }// fim do switch

    this.resetButton.setOnAction(e -> {
      resetButtonAction();

    });// fim do metodo resetButton.setOnAction
  }// fim do metodo openNewScreen

  /**
   * ********************************************************************
   * Metodo: resetButtonAction
   * Funcao: Metodo privado para resetar a simulacao do trem.
   * Parametros: @param void
   * Retorno: @return void
   */
  private void resetButtonAction() {
    this.blueSpeedSlider.setValue(DEFAULT_SPEED);
    this.redSpeedSlider.setValue(DEFAULT_SPEED);

    Te.stoptrain();
    Td.stoptrain();

    Te.play();
    Td.play();
  } // fim do metodo resetButtonAction

  /**
   * ********************************************************************
   * Metodo: main
   * Funcao: Metodo principal para iniciar o programa.
   * Parametros: @param args sao os argumentos da linha de comando.
   * Retorno: @return void
   */
  public static void main(String[] args) {
    launch(args);
  }// fim do metodo main
}// fim da classe Principal

/**
 * ********************************************************************
 * Classe: TremDaEsquerda
 * Funcao: Classe para criar a thread do trem azul que inicialmente sai na esquerda quando na mesma direcao
 */
class TremDaEsquerda extends Thread {
  private PathTransition pathTransition1; // Transicao de caminho para o trem
  private Rectangle blueTrain; // Trem azul
  private Slider blueSpeedSlider; // Slider para controlar a velocidade do trem
  private VariavelDeTravamento exclusaomutua; // Classe para variavel de travamento
  private EstritaAlternancia alternancia; // Classe para estrita alternancia
  private DoubleProperty dividedRateProperty; // Propriedade para controlar a taxa de animacao
  private boolean isPaused = false; // Variavel para verificar se o trem esta pausado
  private SolucaoPeterson peterson; // Classe para solucao de peterson

  public TremDaEsquerda(Rectangle blueTrain2, Slider blueSpeedSlider2) { // Construtor
    this.blueTrain = blueTrain2;
    this.blueSpeedSlider = blueSpeedSlider2;
    setupPathTransition();
  } // fim do construtor

  /**
   * ********************************************************************
   * Metodo: setupPathTransition
   * Funcao: Metodo privado para configurar a transicao de caminho para o trem.
   * Parametros: @param void
   * Retorno: @return void
   */
  private void setupPathTransition() { 
    pathTransition1 = new PathTransition();
    dividedRateProperty = new SimpleDoubleProperty();
    dividedRateProperty.bind(Bindings.divide(this.blueSpeedSlider.valueProperty().add(0.1), 30.0));
    pathTransition1.rateProperty().bind(dividedRateProperty);
    pathTransition1.setNode(blueTrain);
    pathTransition1.setDuration(Duration.seconds(4));
    pathTransition1.setCycleCount(1);
    pathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    pathTransition1.setInterpolator(Interpolator.LINEAR);
    pathTransition1.setOnFinished(event -> {
      // Iniciar a transicao de caminho novamente
      Platform.runLater(() -> {
        pathTransition1.play();
      }); // fim do metodo Platform.runLater
    }); // fim do metodo pathTransition1.setOnFinished

    dividedRateProperty.addListener((observable, oldValue, newValue) -> {
      if (newValue.doubleValue() <= 0.1) {
        // Se o valor for zero, pause o trem e registre o estado de pausa
        if (!isPaused && pathTransition1.getStatus() != Animation.Status.PAUSED) {
          pathTransition1.rateProperty().unbind();
          pathTransition1.pause();

          isPaused = true;
        } // fim do if
      } else {
        // Se o valor nao for zero e o trem estiver pausado, retome a animacao
        if (isPaused && pathTransition1.getStatus() == Animation.Status.PAUSED) {
          pathTransition1.play();
          pathTransition1.rateProperty().bind(dividedRateProperty);
          isPaused = false;
        } // fim do if
      } // fim do else
    }); // fim do dividedRateProperty.addListener
  } // fim do metodo setupPathTransition

  /**
   * ********************************************************************
   * Metodo: play
   * Funcao: Metodo para iniciar o trem.
   * Parametros: @param void
   * Retorno: @return void
   */
  public void play() {
    Platform.runLater(() -> {
      if (pathTransition1 != null) {
        pathTransition1.play();
      } // fim do if
    }); // fim do metodo Platform.runLater
  } // fim do metodo play

  /**
   * ********************************************************************
   * Metodo: stoptrain
   * Funcao: Metodo para parar o trem.
   * Parametros: @param void
   * Retorno: @return void
   */
  public void stoptrain() {
    Platform.runLater(() -> {
      if (pathTransition1 != null) {
        pathTransition1.stop();
      } // fim do if
    }); // fim do metodo Platform.runLater
  }  // fim do metodo stoptrain

  /**
   * ********************************************************************
   * Metodo: setPath
   * Funcao: Metodo para definir o caminho do trem.
   * Parametros: @param path eh o caminho do trem.
   * Retorno: @return void
   */
  public void setPath(Path path) {
    if (pathTransition1 != null) {
      pathTransition1.setPath(path);
    } // fim do if
  } // fim do metodo setPath

  /**
   * ********************************************************************
   * Metodo: run
   * Funcao: Metodo para iniciar a thread do trem.
   * Parametros: @param void
   * Retorno: @return void
   */
  @Override
  public void run() {
    Platform.runLater(() -> {
      if (pathTransition1 != null) {
        pathTransition1.play();
      } // fim do if
    }); // fim do metodo Platform.runLater

    while (true) {
      if (peterson != null) {
        double y = blueTrain.localToScene(blueTrain.getBoundsInLocal()).getMinY();
        if (y >= 50 && y <= 350) {
          if (peterson != null) {
            peterson.entrarRegiaoCritica(0, pathTransition1, blueTrain, dividedRateProperty);
          } // fim do if
          if (peterson != null) {
            peterson.sairRegiaoCritica(0);
          } // fim do if
        } else if (y >= 450 && y <= 750) {
          if (peterson != null) {
            peterson.entrarRegiaoCritica2(0, pathTransition1, blueTrain, dividedRateProperty);
          } // fim do if
          if (peterson != null) {
            peterson.sairRegiaoCritica2(0);
          } // fim do if
        } else {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } // fim do catch
        }
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // fim do catch
      } // fim do else

      if (exclusaomutua != null) {
        exclusaomutua.entrarRegiaoCritica(pathTransition1, blueTrain, dividedRateProperty);

        try {
          // Pausa a thread por um curto periodo de tempo (por exemplo, 100 milissegundos)
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // fim do catch
      } // fim do if
      if (alternancia != null) {
        alternancia.entrarRegiaoCritica(0, pathTransition1, blueTrain, dividedRateProperty);
        try {
          // Pausa a thread por um curto periodo de tempo (por exemplo, 100 milissegundos)
          Thread.sleep(100);
        } catch (InterruptedException e) {
          // Lidar com excecao (se necessario)
        } // fim do catch
      } // fim do if
    } // fim do while
  } // fim do metodo run

  public void setExclusaoMutua(VariavelDeTravamento exclusaomutua) {
    this.exclusaomutua = exclusaomutua;
  } // fim do metodo setExclusaoMutua

  public void setEstritaAlternancia(EstritaAlternancia alternancia) {
    this.alternancia = alternancia;
  } // fim do metodo setEstritaAlternancia

  public void setSolucaoPeterson(SolucaoPeterson peterson) {
    this.peterson = peterson;
  } // fim do metodo setSolucaoPeterson
} // fim da classe TremDaEsquerda


/**
 * ********************************************************************
 * Classe: TremDaDireita
 * Funcao: Classe para criar a thread do trem vermelho que se move inicialmente pela direita quando na mesma direcao
 */
class TremDaDireita extends Thread {
  private PathTransition pathTransition2; // Transicao de caminho para o trem
  private Rectangle redTrain; // Trem vermelho
  private Slider redSpeedSlider; // Slider para controlar a velocidade do trem
  private VariavelDeTravamento exclusaobasica; // Classe para variavel de travamento
  private EstritaAlternancia alternancia; // Classe para estrita alternancia
  private DoubleProperty dividedRateProperty2; // Propriedade para controlar a taxa de animacao
  private boolean isPaused = false; // Flag para controlar o estado de pausa
  private SolucaoPeterson peterson; // Classe para solucao de peterson

  public TremDaDireita(Rectangle redTrain2, Slider redSpeedSlider2) { // Construtor
    this.redTrain = redTrain2;
    this.redSpeedSlider = redSpeedSlider2;
    setupPathTransition();
  } // fim do construtor

  /**
   * ********************************************************************
   * Metodo: setupPathTransition
   * Funcao: Metodo privado para configurar a transicao de caminho para o trem.
   * Parametros: @param void
   * Retorno: @return void
   */
  private void setupPathTransition() {
    pathTransition2 = new PathTransition();
    dividedRateProperty2 = new SimpleDoubleProperty();
    dividedRateProperty2.bind(Bindings.divide(this.redSpeedSlider.valueProperty().add(0.1), 30.0));
    pathTransition2.rateProperty().bind(dividedRateProperty2);
    pathTransition2.setNode(redTrain);
    pathTransition2.setDuration(Duration.seconds(4));
    pathTransition2.setCycleCount(1);
    pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    pathTransition2.setInterpolator(Interpolator.LINEAR);
    pathTransition2.setOnFinished(event -> {
      // Garantir que as atualizacoes sobre a tela sejam feitas na thread do javafx application
      Platform.runLater(() -> {
        pathTransition2.play();
      }); // fim do metodo Platform.runLater
    }); // fim do metodo pathTransition2.setOnFinished

    dividedRateProperty2.addListener((observable, oldValue, newValue) -> {
      if (newValue.doubleValue() <= 0.1) {
        // Se o valor for zero, pause o trem e registre o estado de pausa
        if (!isPaused && pathTransition2.getStatus() != Animation.Status.PAUSED) {
          pathTransition2.rateProperty().unbind();
          pathTransition2.pause();

          isPaused = true;
        } // fim do if
      } else {
        // Se o valor nao for zero e o trem estiver pausado, retome a animacao
        if (isPaused && pathTransition2.getStatus() == Animation.Status.PAUSED) {
          pathTransition2.play();
          pathTransition2.rateProperty().bind(dividedRateProperty2);
          isPaused = false;
        } // fim do if
      } // fim do else
    }); // fim do dividedRateProperty2.addListener

  } // fim do metodo setupPathTransition

  /**
   * ********************************************************************
   * Metodo: setPath
   * Funcao: Metodo para definir o caminho do trem.
   * Parametros: @param path eh o caminho do trem.
   * Retorno: @return void
   */
  public void setPath(Path path) {
    if (pathTransition2 != null) {
      pathTransition2.setPath(path);
    } // fim do if
  } // fim do metodo setPath

  /**
   * ********************************************************************
   * Metodo: play
   * Funcao: Metodo para iniciar o trem.
   * Parametros: @param void
   * Retorno: @return void
   */
  public void play() {
    Platform.runLater(() -> {
      if (pathTransition2 != null) {
        pathTransition2.play();
      } // fim do if
    }); // fim do metodo Platform.runLater
  } // fim do metodo play

  /**
   * ********************************************************************
   * Metodo: stoptrain
   * Funcao: Metodo para parar o trem.
   * Parametros: @param void
   * Retorno: @return void
   */
  public void stoptrain() {
    Platform.runLater(() -> {
      if (pathTransition2 != null) {
        pathTransition2.stop();
      } // fim do if
    }); // fim do metodo Platform.runLater
  } // fim do metodo stoptrain

  /**
   * ********************************************************************
   * Metodo: run
   * Funcao: Metodo para iniciar a thread do trem.
   * Parametros: @param void
   * Retorno: @return void
   */
  @Override
  public void run() {
    Platform.runLater(() -> {
      if (pathTransition2 != null) {
        pathTransition2.play();
      } // fim do if
    }); // fim do metodo Platform.runLater

    while (true) {
      if (peterson != null) {
        double y = redTrain.localToScene(redTrain.getBoundsInLocal()).getMinY();
        if (y >= 50 && y <= 350) {
          if (peterson != null) {
            peterson.entrarRegiaoCritica(1, pathTransition2, redTrain, dividedRateProperty2);
          } // fim do if
          if (peterson != null) {
            peterson.sairRegiaoCritica(1);
          } // fim do if

        } else if (y >= 450 && y <= 750) {
          if (peterson != null) {
            peterson.entrarRegiaoCritica2(1, pathTransition2, redTrain, dividedRateProperty2);
          } // fim do if
          if (peterson != null) {
            peterson.sairRegiaoCritica2(1);
          }  // fim do if
        } else {

          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } // fim do catch
        } // fim do else

      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // fim do catch
      } // fim do else
      if (exclusaobasica != null) {
        exclusaobasica.entrarRegiaoCritica(pathTransition2, redTrain, dividedRateProperty2);

        try {
          // Pausa a thread por um curto periodo de tempo (por exemplo, 100 milissegundos)
          Thread.sleep(100);
        } catch (InterruptedException e) {
          // Lidar com excecao (se necessario)
        } // fim do catch
      } // fim do if
      if (alternancia != null) {
        alternancia.entrarRegiaoCritica(1, pathTransition2, redTrain, dividedRateProperty2);
        try {
          // Pausa a thread por um curto periodo de tempo (por exemplo, 100 milissegundos)
          Thread.sleep(100);
        } catch (InterruptedException e) {
          // Lidar com excecao (se necessario)
        } // fim do catch
      } // fim do if
    } // fim do while

  } // fim do metodo run

  /**
   * ********************************************************************
   * Metodo: setExclusaoMutua
   * Funcao: Metodo para definir a variavel de travamento.
   * Parametros: @param exclusaomutua eh a classe para variavel de travamento.
   * Retorno: @return void
   */
  public void setExclusaoMutua(VariavelDeTravamento exclusaomutua) {
    this.exclusaobasica = exclusaomutua;

  } // fim do metodo setExclusaoMutua

  /**
   * ********************************************************************
   * Metodo: setEstritaAlternancia
   * Funcao: Metodo para definir a estrita alternancia.
   * Parametros: @param alternancia eh a classe para estrita alternancia.
   * Retorno: @return void
   */
  public void setEstritaAlternancia(EstritaAlternancia alternancia) {
    this.alternancia = alternancia;
  } // fim do metodo setEstritaAlternancia

  /**
   * ********************************************************************
   * Metodo: setSolucaoPeterson
   * Funcao: Metodo para definir a solucao de peterson.
   * Parametros: @param peterson eh a classe para solucao de peterson.
   * Retorno: @return void
   */
  public void setSolucaoPeterson(SolucaoPeterson peterson) {
    this.peterson = peterson;
  } // fim do metodo setSolucaoPeterson
} // fim da classe TremDaDireita


/**
 * ********************************************************************
 * Classe: VariavelDeTravamento
 * Funcao: Classe para criar uma variavel de travamento.
 */
class VariavelDeTravamento {
  private volatile int lock = 0; // Variavel de travamento
  private volatile int lock2 = 0; // Variavel de travamento
  private volatile boolean shouldStop = false; // Flag para controlar o estado de parada

  /**
   * ********************************************************************
   * Metodo: entrarRegiaoCritica
   * Funcao: Metodo para entrar na regiao critica.
   * Parametros: @param pathtrans eh a transicao de caminho para o trem, @param train eh o trem,
   * @param dividedRateProperty2 eh a propriedade para controlar a taxa de reproducao.
   * Retorno: @return void
   */
  public void entrarRegiaoCritica(PathTransition pathtrans,
      Rectangle train,
      DoubleProperty dividedRateProperty2) {
    while (!shouldStop) {
      // Tenta adquirir o bloqueio
      double y = train.localToScene(train.getBoundsInLocal()).getMinY();
      if (y >= 50 && y <= 350) {
        while (lock != 0) {
          if (pathtrans.getStatus() != Animation.Status.PAUSED) {

            pathtrans.pause();
            pathtrans.rateProperty().unbind();

          } // fim do if
        } // Loop ate que o bloqueio esteja liberada
        lock = 1;

        Platform.runLater(() -> {

          pathtrans.play();
          pathtrans.rateProperty().bind(dividedRateProperty2);

        }); // fim do metodo Platform.runLater

        // Adquire o bloqueio
        // Regiao critica
        criticalRegion(train);

        // Libera o bloqueio
        lock = 0;

        // Regiao nao critica
        nonCriticalRegion();

      } else if (y >= 450 && y <= 750) {

        while (lock2 != 0) {
          if (pathtrans.getStatus() != Animation.Status.PAUSED) {

            pathtrans.pause();
            pathtrans.rateProperty().unbind();

          } // fim do if
        } // Loop ate que o bloqueio esteja liberada
        lock2 = 1;

        Platform.runLater(() -> {

          pathtrans.play();
          pathtrans.rateProperty().bind(dividedRateProperty2);

        }); // fim do metodo Platform.runLater

        // Adquire o bloqueio

        // Regiao critica
        criticalRegion2(train);

        // Libera o bloqueio
        lock2 = 0;

        // Regiao nao critica
        nonCriticalRegion2();
        // Fora da regiao critica, aguarda um momento e verifica novamente
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // fim do catch
      } // fim do else
    } // fim do while
  } // fim do metodo entrarRegiaoCritica

  /**
   * ********************************************************************
   * Metodo: criticalRegion
   * Funcao: Metodo para entrar na regiao critica.
   * Parametros: @param train eh o trem.
   * Retorno: @return void
   */
  private void criticalRegion(Rectangle train) {
    // Codigo da regiao critica

    while (true) {
      double y = train.localToScene(train.getBoundsInLocal()).getMinY();

      if (y >= 350 || y <= 50) {
        break; // Sai do loop
      } // fim do if
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } // fim do catch
    } // fim do while
  } // fim do metodo criticalRegion

  /**
   * ********************************************************************
   * Metodo: nonCriticalRegion
   * Funcao: Metodo para entrar na regiao nao critica.
   * Parametros: @param void
   * Retorno: @return void
   */
  private void nonCriticalRegion() {
    // Codigo da regiao nao critica

    // Simule algum processamento na regiao nao critica
  } // fim do metodo nonCriticalRegion

  /**
   * ********************************************************************
   * Metodo: criticalRegion2
   * Funcao: Metodo para entrar na regiao critica.
   * Parametros: @param train eh o trem.
   * Retorno: @return void
   */
  private void criticalRegion2(Rectangle train) {
    // Codigo da regiao critica

    while (true) {
      double y = train.localToScene(train.getBoundsInLocal()).getMinY();

      if (y >= 750 || y <= 450) {
        break; // Sai do loop
      }
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } // fim do catch
    } // fim do while
  } // fim do metodo criticalRegion2

  /**
   * ********************************************************************
   * Metodo: nonCriticalRegion2
   * Funcao: Metodo para entrar na regiao nao critica.
   * Parametros: @param void
   * Retorno: @return void
   */
  private void nonCriticalRegion2() {
    // Codigo da regiao nao critica

    // Simule algum processamento na regiao nao critica
  } // fim do metodo nonCriticalRegion2

  /**
   * ********************************************************************
   * Metodo: encerrarExclusaoMutua
   * Funcao: Metodo para encerrar a exclusao mutua.
   * Parametros: @param void
   * Retorno: @return void
   */
  public void encerrarExclusaoMutua() {
    shouldStop = true;
  } // fim do metodo encerrarExclusaoMutua
} // fim da classe VariavelDeTravamento

/**
 * ********************************************************************
 * Classe: EstritaAlternancia
 * Funcao: Classe para criar uma estrita alternancia.
 */
class EstritaAlternancia {
  private volatile int turno = 0; // Variavel de travamento para a primeira intersecao
  private volatile int turno2 = 1; // Variavel de travamento para a segunda intersecao
  private volatile boolean shouldStop = false; // Flag para controlar o estado de parada

  /**
   * ********************************************************************
   * Metodo: entrarRegiaoCritica
   * Funcao: Metodo para entrar na regiao critica.
   * Parametros: @param id eh o id do trem, @param pathtrans eh a transicao de caminho para o trem,
   * @param train eh o trem, @param dividedRateProperty2 eh a propriedade para controlar a taxa de reproducao.
   * Retorno: @return void
   */
  public void entrarRegiaoCritica(int id, PathTransition pathtrans, Rectangle train,
      DoubleProperty dividedRateProperty2) {
    while (!shouldStop) {
      double y = train.localToScene(train.getBoundsInLocal()).getMinY();

      if (y >= 50 && y <= 350) {
        while (turno != id) {
          if (pathtrans.getStatus() != Animation.Status.PAUSED) {

            pathtrans.pause();
            pathtrans.rateProperty().unbind();

          } // fim do if
        } // Loop ate que o bloqueio esteja liberada

        Platform.runLater(() -> {
          pathtrans.play();
          pathtrans.rateProperty().bind(dividedRateProperty2);
        }); // fim do metodo Platform.runLater

        // Regiao critica
        criticalRegion(train);

        // Simule algum processamento na regiao critica

        turno = (id + 1) % 2; // Alterna o turno

        // Saida da regiao critica
        nonCriticalRegion();

      } else if (y >= 450 && y <= 750) {
        while (turno2 != id) {
          if (pathtrans.getStatus() != Animation.Status.PAUSED) {

            pathtrans.pause();
            pathtrans.rateProperty().unbind();

          } // fim do if
        } // Loop ate que o bloqueio esteja liberada

        Platform.runLater(() -> {

          pathtrans.play();
          pathtrans.rateProperty().bind(dividedRateProperty2);

        }); // fim do metodo Platform.runLater
 
        // Adquire o bloqueio

        // Regiao critica
        criticalRegion2(train);

        // Libera o bloqueio
        turno2 = 1 - id;

        // Regiao nao critica
        nonCriticalRegion2();
        // Fora da regiao critica, aguarda um momento e verifica novamente
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // fim do catch
      } // fim do else
    } // fim do while
  } // fim do metodo entrarRegiaoCritica

  /**
   * ********************************************************************
   * Metodo: criticalRegion
   * Funcao: Metodo para entrar na regiao critica.
   * Parametros: @param train eh o trem.
   * Retorno: @return void
   */
  private void criticalRegion(Rectangle train) {
    // Codigo da regiao critica


    while (true) {
      double y = train.localToScene(train.getBoundsInLocal()).getMinY();

      if (y >= 350 || y <= 50) {

        break; // Sai do loop
      } // fim do if
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } // fim do catch
    } // fim do while
  } // fim do metodo criticalRegion

  /**
   * ********************************************************************
   * Metodo: nonCriticalRegion
   * Funcao: Metodo para entrar na regiao nao critica.
   * Parametros: @param void
   * Retorno: @return void
   */
  private void nonCriticalRegion() {
    // Codigo da regiao nao critica

    // Simule algum processamento na regiao nao critica
  } // fim do metodo nonCriticalRegion

  /**
   * ********************************************************************
   * Metodo: criticalRegion2
   * Funcao: Metodo para entrar na regiao critica.
   * Parametros: @param train eh o trem.
   * Retorno: @return void
   */
  private void criticalRegion2(Rectangle train) {
    // Codigo da regiao critica


    while (true) {
      double y = train.localToScene(train.getBoundsInLocal()).getMinY();

      if (y >= 750 || y <= 450) {
        break; // Sai do loop
      } // fim do if
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } // fim do catch
    } // fim do while
  } // fim do metodo criticalRegion2

  /**
   * ********************************************************************
   * Metodo: nonCriticalRegion2
   * Funcao: Metodo para entrar na regiao nao critica.
   * Parametros: @param void
   * Retorno: @return void
   */
  private void nonCriticalRegion2() {
    // Codigo da regiao nao critica
    // Simule algum processamento na regiao nao critica
  } // fim do metodo nonCriticalRegion2

  /**
   * ********************************************************************
   * Metodo: encerrarExclusaoMutua
   * Funcao: Metodo para encerrar a exclusao mutua.
   * Parametros: @param void
   * Retorno: @return void
   */
  public void encerrarExclusaoMutua() {
    shouldStop = true;
  } // fim do metodo encerrarExclusaoMutua
}

/**
 * ********************************************************************
 * Classe: SolucaoPeterson
 * Funcao: Classe para criar uma solucao de peterson.
 */
class SolucaoPeterson {
  private boolean[] want = new boolean[2]; // Variavel de travamento para a primeira intersecao
  private volatile int turn; // Variavel de travamento para a primeira intersecao
  private boolean[] want2 = new boolean[2]; // Variavel de travamento para a segunda intersecao
  private volatile int turn2; // Variavel de travamento para a segunda intersecao
  private volatile boolean shouldStop; // Flag para controlar o estado de parada

  /**
   * ********************************************************************
   * Metodo: entrarRegiaoCritica
   * Funcao: Metodo para entrar na regiao critica.
   * Parametros: @param id eh o id do trem, @param pathtrans eh a transicao de caminho para o trem,
   * @param train eh o trem, @param dividedRateProperty2 eh a propriedade para controlar a taxa de reproducao.
   * Retorno: @return void
   */

  public void entrarRegiaoCritica(int id, PathTransition pathtrans, Rectangle train,
      DoubleProperty dividedRateProperty2) {

    if (shouldStop == false) {
      int outraThread;
      outraThread = 1 - id; // Alternar entre 0 e 1 para as duas threads
      want[id] = true;
      turn = outraThread; 

      while (want[outraThread] && turn == outraThread) {
        // Espera ate que a outra thread saia da regiao critica
        if (pathtrans.getStatus() != Animation.Status.PAUSED) {
          Platform.runLater(() -> {
            pathtrans.pause();
            pathtrans.rateProperty().unbind();
          }); // fim do metodo Platform.runLater

        } // fim do if
      } // fim do while

      Platform.runLater(() -> {
        pathtrans.play();
        pathtrans.rateProperty().bind(dividedRateProperty2);
      }); // fim do metodo Platform.runLater

      // Regiao critica

      // Simule algum processamento na regiao critica
      while (true) {
        double y = train.localToScene(train.getBoundsInLocal()).getMinY();

        if (y >= 350 || y <= 50) {

          break; // Sai do loop
        } // fim do if

        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // fim do catch
      } // fim do while
    } // fim do if
  } // fim do metodo entrarRegiaoCritica

  /**
  * ********************************************************************
  * Metodo: entrarRegiaoCritica2
  * Funcao: Metodo para entrar na regiao critica.
  * Parametros: @param id eh o id do trem, @param pathtrans eh a transicao de caminho para o trem,
  * @param train eh o trem, @param dividedRateProperty2 eh a propriedade para controlar a taxa de reproducao.
  * Retorno: @return void
  */
  public void entrarRegiaoCritica2(int id, PathTransition pathtrans, Rectangle train,
      DoubleProperty dividedRateProperty2) {
    if (shouldStop == false) {
      int outraThread2;
      outraThread2 = 1 - id; // Alternar entre 0 e 1 para as duas threads
      want2[id] = true;
      turn2 = outraThread2;

      while (want2[outraThread2] && turn2 == outraThread2) {
        // Espera ate que a outra thread saia da regiao critica
        if (pathtrans.getStatus() != Animation.Status.PAUSED) {
          Platform.runLater(() -> {
            pathtrans.pause();
            pathtrans.rateProperty().unbind();
          }); // fim do metodo Platform.runLater

        } // fim do if
      } // fim do while

      Platform.runLater(() -> {
        pathtrans.play();
        pathtrans.rateProperty().bind(dividedRateProperty2);
      }); // fim do metodo Platform.runLater

      // Regiao critica

      // Simule algum processamento na regiao critica
      while (true) {
        double y = train.localToScene(train.getBoundsInLocal()).getMinY();

        if (y >= 750 || y <= 450) {
          break; // Sai do loop
        } // fim do if

        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // fim do catch
      } // fim do while
    } // fim do if
  } // fim do metodo entrarRegiaoCritica2

  /**
   * ********************************************************************
   * Metodo: sairRegiaoCritica
   * Funcao: Metodo para sair da regiao critica.
   * Parametros: @param id eh o id do trem.
   * Retorno: @return void
   */
  public void sairRegiaoCritica(int id) {
    if (shouldStop == false) {
      // Saida da regiao critica

      want[id] = false;
    } // fim do if
  } // fim do metodo sairRegiaoCritica

  /**
   * ********************************************************************
   * Metodo: sairRegiaoCritica2
   * Funcao: Metodo para sair da regiao critica.
   * Parametros: @param id eh o id do trem.
   * Retorno: @return void
   */
  public void sairRegiaoCritica2(int id) {
    if (shouldStop == false) {
      // Saida da regiao critica

      want2[id] = false;
    } // fim do if
  } // fim do metodo sairRegiaoCritica2

  /**
   * ********************************************************************
   * Metodo: encerrarExclusaoMutua
   * Funcao: Metodo para encerrar a exclusao mutua.
   * Parametros: @param void
   * Retorno: @return void
   */
  public void encerrarExclusaoMutua() {
    shouldStop = true;
  } // fim do metodo encerrarExclusaoMutua
} // fim da classe SolucaoPeterson
