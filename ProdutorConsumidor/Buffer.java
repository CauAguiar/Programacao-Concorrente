 /* ***************************************************************
 * Autor............: Caue Rodrigues de Aguiar
 * Matricula........: 202210181
 * Inicio...........: 17/10/2023
 * Ultima alteracao.: 28/10/2023
 * Nome.............: Buffer.java
 * Funcao...........: Classe Buffer que implementa o buffer compartilhado
 *                   entre o produtor e o consumidor.
 *************************************************************** */

import java.util.ArrayList;

import javafx.application.Platform;

import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Buffer {
  static final int N = 3; // Tamanho do buffer
  Semaphore mutex = new Semaphore(1); // Semaforo para exclusao mutua
  Semaphore empty = new Semaphore(N); // Semaforo para controle de itens vazios
  Semaphore full = new Semaphore(0); // Semaforo para controle de itens cheios
  private VBox imageBox1; // VBox que contem a imagem do item 1
  private VBox imageBox2; // VBox que contem a imagem do item 2
  private VBox imageBox3; // VBox que contem a imagem do item 3
  private ObservableList<String> itemsInPreparation; // Lista de itens em preparacao
  private ObservableList<String> itemsReady; // Lista de itens prontos
  private ArrayList<Character> verify; // Lista de verificacao de itens
  private ImageView itemFinal = null; // Imagem do item que sera inserido no buffer
  private volatile boolean exitProdutor; // Variavel de controle de execucao do produtor
  private volatile boolean exitConsumidor; // Variavel de controle de execucao do consumidor
  private volatile boolean consumerPause = false; // Variavel de controle de pausa do consumidor 
  private volatile boolean producerPause = false; // Variavel de controle de pausa do produtor
  private int randomIndexProdutor; // indice aleatorio do produtor
  private int randomIndexConsumidor; // indice aleatorio do consumidor
  private HBox lanches; // HBox que contem as imagens dos lanches

  public Buffer(VBox imageBox1, VBox imageBox2, VBox imageBox3,
      ObservableList<String> itemsInPreparation, ObservableList<String> itemsReady, boolean exitProdutor,
      boolean exitConsumidor, HBox lanches) {
    this.imageBox1 = imageBox1;
    this.imageBox2 = imageBox2;
    this.imageBox3 = imageBox3;
    this.itemsInPreparation = itemsInPreparation;
    this.itemsReady = itemsReady;
    this.verify = new ArrayList<>();
    this.exitProdutor = exitProdutor;
    this.exitConsumidor = exitConsumidor;
    this.lanches = lanches;
  } // Fim do construtor

  /* ***************************************************************
  * Metodo...........: setExitProdutor
  * Funcao...........: Seta o valor da variavel exitProdutor.
  * Parametros.......: boolean exitProdutor
  * Retorno..........: void
  *************************************************************** */
  public void setExitProdutor(boolean exitProdutor) {
    this.exitProdutor = exitProdutor;
  } // Fim do metodo setExitProdutor

  /* ***************************************************************
  * Metodo...........: setExitConsumidor
  * Funcao...........: Seta o valor da variavel exitConsumidor.
  * Parametros.......: boolean exitConsumidor
  * Retorno..........: void
  *************************************************************** */
  public void setExitConsumidor(boolean exitConsumidor) {
    this.exitConsumidor = exitConsumidor;
  } // Fim do metodo setExitConsumidor

  /* ***************************************************************
  * Metodo...........: setConsumerPause
  * Funcao...........: Seta o valor da variavel consumerPause.
  * Parametros.......: boolean consumerPause
  * Retorno..........: void
  *************************************************************** */
  public void setConsumerPause(boolean consumerPause) {
    this.consumerPause = consumerPause;
  } // Fim do metodo setConsumerPause

  /* ***************************************************************
  * Metodo...........: getVerifySize
  * Funcao...........: Retorna o tamanho da lista de verificacao.
  * Parametros.......: void
  * Retorno..........: int
  *************************************************************** */
  public int getVerifySize() {
    return verify.size();
  } // Fim do metodo getVerifySize

  /* ***************************************************************
  * Metodo...........: setProducerPause
  * Funcao...........: Seta o valor da variavel producerPause.
  * Parametros.......: boolean producerPause
  * Retorno..........: void
  *************************************************************** */
  public void setProducerPause(boolean producerPause) {
    this.producerPause = producerPause;
  } // Fim do metodo setProducerPause

  /* ***************************************************************
  * Metodo...........: getItemFinal
  * Funcao...........: Retorna a imagem do item que sera inserido no buffer.
  * Parametros.......: void
  * Retorno..........: ImageView
  *************************************************************** */
  public ImageView getItemFinal() {
    return itemFinal;
  } // Fim do metodo getItemFinal

  /* ***************************************************************
  * Metodo...........: getIndexConsumidor
  * Funcao...........: Retorna o indice aleatorio do consumidor.
  * Parametros.......: void
  * Retorno..........: int
  *************************************************************** */
  public int getIndexConsumidor() {
    return randomIndexConsumidor;
  } // Fim do metodo getIndexConsumidor

  /* ***************************************************************
  * Metodo...........: removeVerify
  * Funcao...........: Remove um elemento da lista de verificacao.
  * Parametros.......: int index
  * Retorno..........: void
  *************************************************************** */
  public void removeVerify(int index) {
    verify.remove(index);
  } // Fim do metodo removeVerify

  /* ***************************************************************
  * Metodo...........: insertItem
  * Funcao...........: Insere um item no buffer.
  * Parametros.......: ImageView item
  * Retorno..........: void
  *************************************************************** */
  public void insertItem(ImageView item) {
    if (verify.size() < 3) {

      if (item != null) {
        this.itemFinal = item;
      } // Fim do if

      Random random = new Random();

      while (true) {
        if (!exitProdutor) { // Verifica se o produtor esta em execucao
          randomIndexProdutor = random.nextInt(3) + 1;
          char randomChar = (char) (randomIndexProdutor + '0');

          if (!verify.contains(randomChar)) {
            break;
          } // Fim do if
        } else {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } // Fim do try-catch
          continue;
        } // Fim do if-else
      } // Fim do while

      while (true) {
        if (!producerPause) { // Verifica se o produtor esta pausado
          try {
            Thread.sleep(2500);
            break;
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
          } // Fim do try-catch
        } else {
          break;
        } // Fim do if-else
      } // Fim do while

      if (!producerPause) {
        while (true) {
          if (!exitProdutor) { // Verifica se o produtor esta em execucao
            Platform.runLater(() -> {
              switch (randomIndexProdutor) {
                case 1:
                  imageBox1.getChildren().clear();

                  imageBox1.getChildren().add(itemFinal);
                  lanches.toBack();

                  itemsReady.add(itemsInPreparation.remove(0));
                  verify.add('1');
                  break;
                case 2:
                  imageBox2.getChildren().clear();

                  imageBox2.getChildren().add(itemFinal);
                  lanches.toBack();

                  if (itemsInPreparation.size() >= 2) {
                    itemsReady.add(itemsInPreparation.remove(1));
                  } else {
                    itemsReady.add(itemsInPreparation.remove(0));
                  } // Fim do if-else
                  verify.add('2');
                  break;
                case 3:
                  imageBox3.getChildren().clear();

                  imageBox3.getChildren().add(itemFinal);
                  lanches.toBack();
                  
                  if (itemsInPreparation.size() >= 3) {
                    itemsReady.add(itemsInPreparation.remove(2));
                  } else if (itemsInPreparation.size() >= 2) {
                    itemsReady.add(itemsInPreparation.remove(1));
                  } else {
                    itemsReady.add(itemsInPreparation.remove(0));
                  } // Fim do if-else
                  verify.add('3');
                  break;
              }
              itemFinal = null;
            }); // Fim do runLater
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
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        } // Fim do try-catch
      } // Fim do if-else
    } else {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } // Fim do try-catch
    } // Fim do if-else
  } // Fim do metodo insertItem

  /* ***************************************************************
  * Metodo...........: removeItem
  * Funcao...........: Remove um item do buffer.
  * Parametros.......: void
  * Retorno..........: int
  *************************************************************** */
  public int removeItem() {

    while (true) {
      if (!exitConsumidor) { // Verifica se o consumidor esta em execucao
        try {
          Thread.sleep(500);
          break;
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          e.printStackTrace();
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
    
    Random random = new Random();

    while (true) {
      if (!exitConsumidor) { // Verifica se o consumidor esta em execucao
        if (!verify.isEmpty()) {
          randomIndexConsumidor = random.nextInt(verify.size());
          char charValue = verify.get(randomIndexConsumidor);
          int intValue = Character.getNumericValue(charValue);

          if (!consumerPause) {
            try {
              Thread.sleep(1000);
              return intValue;
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              e.printStackTrace();
            } // Fim do try-catch
          } else {
            return -1;
          } // Fim do if-else

          return intValue;

        } else {
          return -1;
        } // Fim do if-else
      } // Fim do if
    } // Fim do while
  } // Fim do metodo removeItem
} // Fim da classe Buffer