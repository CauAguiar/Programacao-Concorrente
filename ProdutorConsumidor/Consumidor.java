 /* ***************************************************************
 * Autor............: Caue Rodrigues de Aguiar
 * Matricula........: 202210181
 * Inicio...........: 17/10/2023
 * Ultima alteracao.: 28/10/2023
 * Nome.............: Consumidor.java
 * Funcao...........: Classe Consumidor que implementa o consumidor
 *                  de itens, que sao retirados do buffer.
 *************************************************************** */

import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Consumidor extends Thread {
  private Buffer buffer; // Buffer compartilhado
  private Image clear; // Imagem de fundo transparente
  private Image gifImageVolta; // Gif de fundo
  private ImageView imageViewLanche; // Imagem do lanche
  private VBox imageBox1; // VBox do item 1
  private VBox imageBox2; // VBox do item 2
  private VBox imageBox3; // VBox do item 3
  private ObservableList<String> itemsReady; // Lista de itens prontos
  private PathTransition pathTransitionIda; // Transicao de ida
  private PathTransition pathTransitionVolta; // Transicao de volta
  private ArrayList<Path> paths; // Lista de paths
  private ArrayList<Path> reversedPaths; // Lista de paths reversos
  private Pane root; // Pane principal
  private Group group; // Grupo de imagens
  private boolean exit; // Variavel de controle para parar a thread

  public Consumidor(Buffer buffer, Image clear, VBox imageBox1, VBox imageBox2, VBox imageBox3,
      ObservableList<String> itemsReady, boolean exit, PathTransition pathTransitionIda, ArrayList<Path> paths,
      PathTransition pathTransitionVolta, ArrayList<Path> reversedPaths, Pane root, Image gifImageVolta) {
    this.buffer = buffer;
    this.clear = clear;
    this.imageBox1 = imageBox1;
    this.imageBox2 = imageBox2;
    this.imageBox3 = imageBox3;
    this.itemsReady = itemsReady;
    this.exit = exit;
    this.pathTransitionIda = pathTransitionIda;
    this.paths = paths;
    this.pathTransitionVolta = pathTransitionVolta;
    this.reversedPaths = reversedPaths;
    this.root = root;
    this.gifImageVolta = gifImageVolta;
  } // Fim do construtor

  /* ***************************************************************
  * Metodo...........: setExit
  * Funcao...........: Altera a variavel de controle para parar a thread.
  * Parametros.......: boolean exit
  * Retorno..........: void
  *************************************************************** */
  public void setExit(boolean exit) {
    this.exit = exit;
  } // Fim do metodo setExit

  /* ***************************************************************
  * Metodo...........: run
  * Funcao...........: Metodo que executa a thread.
  * Parametros.......: void
  * Retorno..........: void
  *************************************************************** */
  @Override
  public void run() {
    while (true) {
      if (!exit) { // Se a variavel de controle for falsa, executa o codigo abaixo
        try {
          buffer.full.acquire();
          buffer.mutex.acquire();
          int item = buffer.removeItem();
          buffer.mutex.release();
          buffer.empty.release();

          consumeItem(item);

        } catch (InterruptedException e) {
          e.printStackTrace();
          break;
        } // Fim do try-catch
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // Fim do try-catch
        continue;
      } // Fim do if-else
    } // Fim do while
  } // Fim do metodo run

  /* ***************************************************************
  * Metodo...........: consumeItem
  * Funcao...........: Metodo que consome um item do buffer.
  * Parametros.......: int item
  * Retorno..........: void
  *************************************************************** */
  private void consumeItem(int item) {
    if (!exit) { // Se a variavel de controle for falsa, executa o codigo abaixo
      if (item != -1) { 
        pathTransitionIda.getNode().setOpacity(1.0);
        pathTransitionIda.setPath(paths.get(item - 1));
        pathTransitionVolta.setPath(reversedPaths.get(item - 1));
        ((ImageView) pathTransitionIda.getNode()).setX(-300);
        pathTransitionIda.play();

        pathTransitionIda.setOnFinished(event -> { // Evento que ocorre quando a transicao de ida termina
          while (true) {
            if (!exit) {
              if (item != -1) {
                Platform.runLater(() -> {
                  switch (buffer.getIndexConsumidor()) { // Remove o item da lista de itens prontos
                    case 0:
                      itemsReady.remove(0);
                      break;
                    case 1:
                      itemsReady.remove(1);
                      break;
                    case 2:
                      itemsReady.remove(2);
                      break;
                  } // Fim do switch-case
                }); // Fim do Platform.runLater
                break;
              } else {
                break;
              } // Fim do if-else
            } // Fim do if
          } // Fim do while

          ImageView clearView = new ImageView(clear);

          switch (item) { // Remove a imagem do lanche da VBox
            case 1:
              imageViewLanche = null;
              ObservableList<Node> children = imageBox1.getChildren();
              if (!children.isEmpty() && children.get(0) instanceof ImageView) {
                imageViewLanche = (ImageView) children.get(0);
              } // Fim do if
              imageBox1.getChildren().clear();
              imageBox1.getChildren().add(clearView);
              break;
            case 2:
              imageViewLanche = null;
              children = imageBox2.getChildren();
              if (!children.isEmpty() && children.get(0) instanceof ImageView) {
                imageViewLanche = (ImageView) children.get(0);
              } // Fim do if
              imageBox2.getChildren().clear();
              imageBox2.getChildren().add(clearView);
              break;
            case 3:
              imageViewLanche = null;
              children = imageBox3.getChildren();
              if (!children.isEmpty() && children.get(0) instanceof ImageView) {
                imageViewLanche = (ImageView) children.get(0);
              } // Fim do if
              imageBox3.getChildren().clear();
              imageBox3.getChildren().add(clearView);
              break;
            case -1:
              break;
          } // Fim do switch-case

          ImageView imageViewVoltaGif = new ImageView(gifImageVolta);
          imageViewVoltaGif.setX(-300);
          imageViewVoltaGif.setFitHeight(576 / 1.2);
          imageViewVoltaGif.setFitWidth(260 / 1.2);

          group = new Group(); // Grupo de imagens
          imageViewLanche.setX(-300);
          imageViewLanche.setTranslateX(60);
          imageViewLanche.setTranslateY(60);
          group.getChildren().addAll(imageViewLanche, imageViewVoltaGif);

          root.getChildren().add(group);
          group.toBack();

          pathTransitionVolta.setNode(group);

          FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.1), 
                  pathTransitionIda.getNode()); // Transicao de fade

          fadeTransition.setToValue(0.0);

          fadeTransition.play();

          pathTransitionVolta.play();
          buffer.removeVerify(buffer.getIndexConsumidor());

        }); // Fim do setOnFinished

        pathTransitionVolta.setOnFinished(event -> {
          root.getChildren().remove(group);

        }); // Fim do setOnFinished

        while (true) { 
          if (!pathTransitionIda.getStatus().equals(PathTransition.Status.RUNNING)
              && !pathTransitionIda.getStatus().equals(PathTransition.Status.PAUSED)) {
            if (!pathTransitionVolta.getStatus().equals(PathTransition.Status.RUNNING)
                && !pathTransitionVolta.getStatus().equals(PathTransition.Status.PAUSED)) {
              break;
            } else {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                e.printStackTrace();
              } // Fim do try-catch
              continue;
            } // Fim do if-else
          } else {
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              e.printStackTrace();
            } // Fim do try-catch
            continue;
          } // Fim do if-else
        } // Fim do while
      } // Fim do if
    } // Fim do if
  } // Fim do metodo consumeItem
} // Fim da classe Consumidor