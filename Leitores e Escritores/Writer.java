/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 02/11/2023
* Ultima alteracao.: 12/11/2023
* Nome.............: Writer.java
* Funcao...........: Classe que implementa a thread escritor.
*************************************************************** */

import java.util.Random;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Writer extends Thread {
  private int myNumber; // identificador da thread
  private Buffer buffer; // objeto compartilhado com outras threads
  private boolean pause; // variavel para pausar a thread
  private int delayObter = 10000; // delay para obter o item
  private int delayEscrever = 2000; // delay para escrever o item
  private ImageView item; // item que o escritor vai escrever
  private int index; // indice do item que o escritor vai escrever
  private boolean finish; // variavel para saber se a thread terminou
  private boolean exit = false; // variavel para saber se a thread terminou
  private ImageView itemCheck = null; // variavel que indica se o escritor esta escrevendo

  public Writer(int i, Buffer buffer) {
    myNumber = i + 1;
    this.buffer = buffer;
  }

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
          think_up_data();
          buffer.db.acquire();
          write_data_base();
          buffer.db.release();
          if (exit) {
            Thread.currentThread().interrupt();
            break;
          } // fim do if
        } catch (InterruptedException e) {
          e.printStackTrace();
          break;
        } // fim do try-catch
      } else {
        try {
          Thread.sleep(100);
          if (exit) {
            Thread.currentThread().interrupt();
            break;
          } // fim do if
        } catch (InterruptedException e) {
          e.printStackTrace();
          Thread.currentThread().interrupt();
        } // fim do try-catch
      } // fim do if-else
    } // fim do while
  } // fim do metodo run

  /* ***************************************************************
  * Metodo: think_up_data
  * Funcao: metodo que faz o escritor pensar em um item
  * Parametros: sem parametros
  * Retorno: sem retorno
  *************************************************************** */
  public void think_up_data() {
    if (itemCheck == null) {
      if (!pause) {
        StackPane pane = buffer.balloonView.get(myNumber - 1);
        Platform.runLater(() -> {
          pane.setVisible(true);
        }); // fim do runLater
        try {
          Random random = new Random();
          index = random.nextInt(buffer.items.size());

          Thread.sleep(delayObter);
          item = new ImageView(buffer.items.get(index));
          item.setFitWidth(100);
          item.setFitHeight(100);
          item.setTranslateX(20);

          while (true) {
            if (!pause) {
              Platform.runLater(() -> {
                if (!exit) {
                  pane.getChildren().add(item);
                  itemCheck = item;
                } // fim do if
              }); // fim do runLater
              break;
            } else {
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              } // fim do try-catch
            } // fim do if-else
          } // fim do while
          if (!exit) {
            while (true) {
              if (!pause) {
                Thread.sleep(6000);
                break;
              } else {
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                } // fim do try-catch
              } // fim do if-else
            } // fim do while
            while (true) {
              if (!pause) {
                if (!exit) {
                  Platform.runLater(() -> {
                    pane.setVisible(false);
                    pane.getChildren().remove(item);
                  }); // fim do runLater
                } // fim do if
                break;
              } else {
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                } // fim do try-catch
              } // fim do if-else
            } // fim do while
          } // fim do if

        } catch (InterruptedException ex) {
        } // fim do try-catch
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
        } // fim do try-catch
      } // fim do if-else
    } // fim do if
  } // fim do metodo think_up_data

  /* ***************************************************************
  * Metodo: write_data_base
  * Funcao: metodo que escreve o item no banco de dados
  * Parametros: sem parametros
  * Retorno: sem retorno
  *************************************************************** */
  public void write_data_base() {
    if (!pause) {
      double translateXAntigo = buffer.getSteveViewEscritor(myNumber - 1).getTranslateX();
      double translateYAntigo = buffer.getSteveViewEscritor(myNumber - 1).getTranslateY();
      try {
        Thread.sleep(3000);
        Platform.runLater(() -> {
        }); // fim do runLater
      } catch (InterruptedException e) {
        e.printStackTrace();
      } // fim do try-catch
      if (!exit) {

        ImageView steve = new ImageView(buffer.getGifImage(index));
        steve.setVisible(true);

        Rectangle node = buffer.getRectangle(myNumber - 1);

        node.setVisible(true);

        node.setFill(new ImagePattern(buffer.getGifImage(index)));

        buffer.pathTransitionEscritorIda.setNode(node);
        buffer.pathTransitionEscritorIda.setPath(buffer.paths.get(myNumber - 1));
        buffer.getSteveViewEscritor(myNumber - 1).setVisible(false);

        if (!exit) {
          buffer.pathTransitionEscritorIda.play();
          buffer.pathTransitionEscritorIda.getNode().setOpacity(1.0);
          buffer.setPathNow(myNumber);
        } // fim do if

        finish = false;

        Pane foritem = buffer.paneEscritor.get(5);
        buffer.pathTransitionEscritorIda.setOnFinished(event -> {
          if (!exit) {
            ImageView bonecoquesome = buffer.getSteveViewEscritor(myNumber - 1);
            bonecoquesome.setVisible(true);
            buffer.setTranslateXandY(myNumber - 1, 1260, 560);
            item.setFitWidth(73);
            item.setFitHeight(73);
            foritem.getChildren().add(item);
            item.setVisible(true);

            node.setVisible(false);

            finish = true;
          } // fim do if
        }); // fim do setOnFinished

        while (true) {
          if (finish) {
            try {
              while (true) {
                if (!pause) {

                  Thread.sleep(delayEscrever);
                  break;
                } else {
                  try {
                    Thread.sleep(1000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  } // fim do try-catch
                } // fim do if-else
              } // fim do while
              while (true) {
                if (!pause) {
                  Platform.runLater(() -> {
                    foritem.getChildren().clear();
                    item.setFitWidth(90);
                    item.setFitHeight(90);
                    buffer.rectangle.getChildren().clear();
                    buffer.rectangle.getChildren().add(item);
                    buffer.imagemAtual = index;
                  }); // fim do runLater
                  break;
                } else {
                  try {
                    Thread.sleep(1000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  } // fim do try-catch
                } // fim do if-else
              } // fim do while
            } catch (InterruptedException e) {
              e.printStackTrace();
            } // fim do try-catch
            break;
          } else {

            try {
              Thread.sleep(100);
              continue;
            } catch (InterruptedException e) {
              e.printStackTrace();
            } // fim do try-catch
          } // fim do if-else
        } // fim do while

        Rectangle nodeVolta = buffer.getRectangle(myNumber - 1);

        finish = false;

        if (!exit) {
          nodeVolta.setFill(new ImagePattern(buffer.gifImage.get(13)));
          nodeVolta.setVisible(true);

          buffer.pathTransitionEscritorVolta.setPath(buffer.paths.get(4 + myNumber));
          buffer.pathTransitionEscritorVolta.setNode(nodeVolta);
          if (!exit) {
            buffer.pathTransitionEscritorVolta.play();
          } // fim do if
          buffer.pathTransitionEscritorVolta.getNode().setOpacity(1.0);
          buffer.setPathNow(myNumber);
          buffer.getSteveViewEscritor(myNumber - 1).setVisible(false);
        } // fim do if

        buffer.pathTransitionEscritorVolta.setOnFinished(event -> {
          finish = true;
        }); // fim do setOnFinished

        while (true) {
          if (finish) {
            Platform.runLater(() -> {
              buffer.getSteveViewEscritor(myNumber - 1).setVisible(true);
              nodeVolta.setVisible(false);
              buffer.getSteveViewEscritor(myNumber - 1).setTranslateX(translateXAntigo);
              buffer.getSteveViewEscritor(myNumber - 1).setTranslateY(translateYAntigo);
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              } // fim do try-catch
            }); // fim do runLater

            break;
          } else {
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              e.printStackTrace();
            } // fim do try-catch
          } // fim do if-else
        } // fim do while
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
        } // fim do try-catch
      } // fim do if-else
      itemCheck = null;
    } // fim do if
  } // fim do metodo write_data_base

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
  * Metodo: setDelayObter
  * Funcao: metodo que seta o delay para obter o item
  * Parametros: int speed
  * Retorno: sem retorno
  *************************************************************** */
  public void setDelayObter(int speed) {
    this.delayObter = speed;
  } // fim do metodo setDelayObter

  /* ***************************************************************
  * Metodo: setDelayEscrever
  * Funcao: metodo que seta o delay para escrever o item
  * Parametros: int speed
  * Retorno: sem retorno
  *************************************************************** */
  public void setDelayEscrever(int speed) {
    this.delayEscrever = speed;
  } // fim do metodo setDelayEscrever

  /* ***************************************************************
  * Metodo: setExit
  * Funcao: metodo que seta a variavel exit
  * Parametros: boolean b
  * Retorno: sem retorno
  *************************************************************** */
  public void setExit(boolean b) {
    this.exit = b;
  } // fim do metodo setExit

} // fim da classe Writer