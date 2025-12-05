/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 02/11/2023
* Ultima alteracao.: 12/11/2023
* Nome.............: Reader.java
* Funcao...........: Classe que implementa a thread leitor.
*************************************************************** */

import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

public class Reader extends Thread {
  private int myNumber; // identificador da thread
  private Buffer buffer; // objeto compartilhado com outras threads
  private boolean pause; // variavel para pausar a thread
  private int delayLer = 2000; // delay para ler
  private int delayUtilizar = 5000; // delay para utilizar
  private double translateXAntigo; // variavel para guardar a posicao x antiga do steve
  private double translateYAntigo; // variavel para guardar a posicao y antiga do steve
  private boolean finish = false; // variavel para verificar se a animacao terminou
  private int indexAtual; // variavel para guardar o index do item atual
  private boolean exit = false; // variavel para verificar se a thread deve ser encerrada
  private Scale flipHorizontal; // variavel para fazer o flip horizontal do steve

  public Reader(int i, Buffer buffer, ArrayList<Rectangle> rectangles, ArrayList<Image> gifImage) {
    myNumber = i + 1;
    this.buffer = buffer;
  } // fim do construtor

  /* ***************************************************************
  * Metodo: run
  * Funcao: metodo que executa a thread
  * Parametros: sem parametros
  * Retorno: sem retorno
  *************************************************************** */
  @Override
  public void run() {
    while (true) {
      if (!pause) {
        try {
          Thread.sleep(1000);
          buffer.mutex.acquire();
          buffer.rc = buffer.rc + 1;
          if (buffer.rc == 1)
            buffer.db.acquire();
          buffer.mutex.release();
          read_data_base();

          buffer.mutex.acquire();
          buffer.rc = buffer.rc - 1;
          if (buffer.rc == 0)
            buffer.db.release();
          buffer.mutex.release();
          use_data_read();

          if (exit) {
            Thread.currentThread().interrupt();
            break;
          } // fim do if
        } catch (InterruptedException e) {
          e.printStackTrace();
          Thread.currentThread().interrupt();
          break;
        } // fim do catch
      } else {
        try {
          Thread.sleep(100);
          if (exit) {
            Thread.currentThread().interrupt();
            break;
          } // fim do if
        } catch (InterruptedException ex) {
          ex.printStackTrace();
          Thread.currentThread().interrupt();
        } // fim do catch
      } // fim do else
    } // fim do while
  } // fim do metodo run

  /* ***************************************************************
  * Metodo: read_data_base
  * Funcao: metodo tal que o leitor le o dado
  * Parametros: sem parametros
  * Retorno: sem retorno
  *************************************************************** */
  public void read_data_base() {
    if (!pause) {
      if (!exit) {
        translateXAntigo = buffer.getSteveViewLeitor(myNumber - 1).getTranslateX();
        translateYAntigo = buffer.getSteveViewLeitor(myNumber - 1).getTranslateY();

        finish = false;
        Rectangle node = buffer.getRectangle(4 + myNumber);
        Platform.runLater(() -> {

          node.setVisible(true);

          node.setFill(new ImagePattern(buffer.getGifImage(13)));

          PathTransition transition = buffer.getPathTransitionLeitor(4 + myNumber);
          transition.setNode(node);
          transition.setPath(buffer.paths.get(9 + myNumber));

          transition.setOnFinished(e -> {
            finish = true;
          }); // fim do setOnFinished

          if (!exit) {
            transition.play();
            transition.getNode().setOpacity(1.0);
            buffer.getSteveViewLeitor(myNumber - 1).setVisible(false);
            buffer.getSteveViewLeitor(myNumber - 1).setTranslateX(680);
          } // fim do if

        }); // fim do runLater

        while (true) {
          if (finish) {

            if (!exit) {
              Platform.runLater(() -> {
                buffer.getSteveViewLeitor(myNumber - 1).setVisible(true);
                node.setVisible(false);
              }); // fim do runLater
            } // fim do if

            try {
              Thread.sleep(delayLer);
            } catch (InterruptedException e) {
              e.printStackTrace();
            } // fim do catch
            break;

          } else {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            } // fim do catch
          } // fim do else
        } // fim do while

        while (true) {
          if (!pause) {
            break;
          } else {
            try {
              Thread.sleep(100);
            } catch (InterruptedException ex) {
              ex.printStackTrace();
              Thread.currentThread().interrupt();
            } // fim do catch
          } // fim do else
        } // fim do while

      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
          Thread.currentThread().interrupt();
        } // fim do catch
      } // fim do else
    } else {
      try {
        Thread.sleep(100);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
        Thread.currentThread().interrupt();
      } // fim do catch
    } // fim do else
  } // fim do metodo read_data_base

  /* ***************************************************************
  * Metodo: use_data_read
  * Funcao: metodo tal que o leitor utiliza o dado
  * Parametros: sem parametros
  * Retorno: sem retorno
  *************************************************************** */
  public void use_data_read() {
    if (!pause) {
      Pane pane = buffer.paneEscritor.get(0);
      pane.setVisible(true);
      ImageView chestGif = new ImageView(buffer.getGifImage(14));
      chestGif.setFitWidth(200);
      chestGif.setFitHeight(200);
      chestGif.setTranslateY(620);
      chestGif.setTranslateX(160);
      finish = false;

      flipHorizontal = new Scale(-1, 1);

      Rectangle node = buffer.getRectangle(4 + myNumber);

      Platform.runLater(() -> {
        node.setVisible(true);

        indexAtual = buffer.getImagemAtual();

        if (indexAtual == -1) {
          node.setFill(new ImagePattern(buffer.getGifImage(12)));
        } else {
          node.setFill(new ImagePattern(buffer.getGifImage(indexAtual)));
        } // fim do else

        if (!exit) {
          PathTransition transition = buffer.getPathTransitionLeitor(4 + myNumber);
          transition.setNode(node);
          transition.setPath(buffer.paths.get(14 + myNumber));

          transition.setOnFinished(e -> {

            if (!exit) {
              buffer.paneEscritor.get(0).getChildren().clear();
              buffer.paneEscritor.get(0).getChildren().add(chestGif);
              buffer.chestView.setVisible(false);
              finish = true;
            } // fim do if
          }); // fim do setOnFinished
          if (!exit) {
            transition.play();
            transition.getNode().setOpacity(1.0);
            buffer.steveViewLeitor.get(myNumber - 1).setVisible(false);
            if (!exit) {
              buffer.steveViewLeitor.get(myNumber - 1).getTransforms().add(flipHorizontal);
            } // fim do if
            buffer.steveViewLeitor.get(myNumber - 1).setTranslateX(510);
          } // fim do if
        } // fim do if
      }); // fim do runLater

      if (!exit) {
        while (true) {
          if (!exit) {
            if (finish) {

              if (!exit) {
                Platform.runLater(() -> {
                  buffer.steveViewLeitor.get(myNumber - 1).setVisible(true);
                  node.setVisible(false);
                }); // fim do runLater

                try {
                  Thread.sleep(delayUtilizar);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                } // fim do catch

                if (indexAtual != -1) {
                  for (int j = buffer.getCellSize(); j < buffer.cellRectangles.size(); j++) {
                    if (buffer.getCellRectangles(j, indexAtual)) {
                      break;
                    } else {
                      try {
                        Thread.sleep(100);
                      } catch (InterruptedException e) {
                        e.printStackTrace();
                      } // fim do catch
                    } // fim do else
                  } // fim do for
                } // fim do if
              } else {

              } // fim do else
              break;

            } else {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                e.printStackTrace();
              } // fim do catch
            } // fim do else
          } else {
            break;
          } // fim do else
        } // fim do while
      } else {

      } // fim do else

      while (true) {
        if (!pause) {
          break;
        } else {
          try {
            Thread.sleep(100);
          } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
          } // fim do catch
        } // fim do else
      } // fim do while

      if (!exit) {
        finish = false;

        Rectangle nodeVolta = buffer.getRectangle(4 + myNumber);

        Platform.runLater(() -> {
          buffer.steveViewLeitor.get(myNumber - 1).getTransforms().remove(flipHorizontal);
          nodeVolta.setVisible(true);

          nodeVolta.setFill(new ImagePattern(buffer.getGifImage(13)));

          PathTransition transitionVolta = buffer.getPathTransitionLeitor(myNumber - 1);
          transitionVolta.setNode(nodeVolta);
          transitionVolta.setPath(buffer.paths.get(19 + myNumber));

          transitionVolta.setOnFinished(e -> {
            if (!exit) {
              finish = true;
            } // fim do if
          }); // fim do setOnFinished

          if (!exit) {
            transitionVolta.play();
            transitionVolta.getNode().setOpacity(1.0);
            buffer.chestView.setVisible(true);
            pane.getChildren().clear();
            buffer.steveViewLeitor.get(myNumber - 1).setVisible(false);
            buffer.steveViewLeitor.get(myNumber - 1).setTranslateX(translateXAntigo);
            buffer.steveViewLeitor.get(myNumber - 1).setTranslateY(translateYAntigo);
          } // fim do if
        }); // fim do runLater

        while (true) {
          if (finish) {

            if (!exit) {
              Platform.runLater(() -> {
                buffer.steveViewLeitor.get(myNumber - 1).setVisible(true);
                nodeVolta.setVisible(false);
              }); // fim do runLater
            } // fim do if
            break;

          } else {
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              e.printStackTrace();
            } // fim do catch
          } // fim do else
        } // fim do while
      } else {

      } // fim do else

    } else {
      try {
        Thread.sleep(100);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
        Thread.currentThread().interrupt();
      } // fim do catch
    } // fim do else
  } // fim do metodo use_data_read

  /* ***************************************************************
  * Metodo: setPause
  * Funcao: metodo que pausa a thread
  * Parametros: boolean b
  * Retorno: sem retorno
  *************************************************************** */
  public void setPause(boolean b) {
    this.pause = b;
  } // fim do metodo setPause

  /* ***************************************************************
  * Metodo: setDelayUtilizar
  * Funcao: metodo que seta o delay para utilizar
  * Parametros: int speed
  * Retorno: sem retorno
  *************************************************************** */
  public void setDelayUtilizar(int speed) {
    delayUtilizar = speed;
  } // fim do metodo setDelayUtilizar

  /* ***************************************************************
  * Metodo: setDelayLer
  * Funcao: metodo que seta o delay para ler
  * Parametros: int speed
  * Retorno: sem retorno
  *************************************************************** */
  public void setDelayLer(int speed) {
    delayLer = speed;
  } // fim do metodo setDelayLer

  /* ***************************************************************
  * Metodo: setExit
  * Funcao: metodo que seta a variavel exit
  * Parametros: boolean b
  * Retorno: sem retorno
  *************************************************************** */
  public void setExit(boolean b) {
    this.exit = b;
  } // fim do metodo setExit

  /* ***************************************************************
  * Metodo: removerFlip
  * Funcao: metodo que remove o flip horizontal
  * Parametros: sem parametros
  * Retorno: sem retorno
  *************************************************************** */
  public void removerFlip() {
    buffer.steveViewLeitor.get(myNumber - 1).getTransforms().remove(flipHorizontal);
  } // fim do metodo removerFlip

} // fim da classe Reader