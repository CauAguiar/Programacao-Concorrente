 /* ***************************************************************
 * Autor............: Caue Rodrigues de Aguiar
 * Matricula........: 202210181
 * Inicio...........: 17/10/2023
 * Ultima alteracao.: 28/10/2023
 * Nome.............: Produtor.java
 * Funcao...........: Classe Produtor que implementa o produtor
 *                  de itens, que sao inseridos no buffer.
 *************************************************************** */

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.Random;

public class Produtor extends Thread {
  private Buffer buffer; // Buffer compartilhado
  private ArrayList<Image> images; // Lista de imagens
  private ObservableList<String> itemsInPreparation; // Lista de itens em preparacao
  private int delay = 8333; // Delay de 8.3 segundos
  private boolean exit; // Variavel de controle para parar a thread

  public Produtor(Buffer buffer, ArrayList<Image> images, ObservableList<String> itemsInPreparation, boolean exit) {
    this.buffer = buffer;
    this.images = images;
    this.itemsInPreparation = itemsInPreparation;
    this.exit = exit;
  } // Fim do construtor

  /* ***************************************************************
  * Metodo...........: setDelay
  * Funcao...........: Altera o delay de producao de itens.
  * Parametros.......: int delay
  * Retorno..........: void
  *************************************************************** */
  public void setDelay(int delay) {
    this.delay = delay;
  } // Fim do metodo setDelay

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
          ImageView item = produceItem();
          buffer.empty.acquire(); 
          buffer.mutex.acquire(); 
          buffer.insertItem(item);
          buffer.mutex.release();
          buffer.full.release();
          Thread.sleep(delay);

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

  private ImageView produceItem() {
    if (buffer.getItemFinal() == null) { // Se o item final do buffer for nulo, executa o codigo abaixo

      Random random = new Random();
      int randomIndexProdutor = random.nextInt(images.size());
      Image randomImage = images.get(randomIndexProdutor);
      ImageView randomImageView = new ImageView(randomImage);

      while (true) {
        if (!exit) { // Se a variavel de controle for falsa, executa o codigo abaixo
          Platform.runLater(() -> {
            switch (randomIndexProdutor) { // Adiciona o item na lista de itens em preparacao
              case 0:
                itemsInPreparation.add("Batata");
                break;
              case 1:
                itemsInPreparation.add("Combo");
                break;
              case 2:
                itemsInPreparation.add("Hamburguer");
                break;
              case 3:
                itemsInPreparation.add("Hamburguer&Batata");
                break;
              case 4:
                itemsInPreparation.add("Hamburguer&Refri");
                break;
              case 5:
                itemsInPreparation.add("Batata&Refri");
                break;
            } // Fim do switch-case
          }); // Fim do runLater

          while (true) {
            if (buffer.getVerifySize() < 3) {
              break;
            } else {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                e.printStackTrace();
              } // Fim do try-catch
              continue;
            } // Fim do if-else
          } // Fim do while

          return randomImageView;

        } else {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } // Fim do try-catch
          continue;
        } // Fim do if-else
      } // Fim do while
    } else {
      return null;
    } // Fim do if-else
  } // Fim do metodo produceItem
} // Fim da classe Produtor