/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 02/11/2023
* Ultima alteracao.: 12/11/2023
* Nome.............: Buffer.java 
* Funcao...........: Classe que implementa o buffer do programa.
*************************************************************** */

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class Buffer extends Thread {
  final int READERS = 5; // Numero de leitores
  final int WRITERS = 5; // Numero de escritores
  ArrayList<Reader> r = new ArrayList<>(); // Lista de leitores
  ArrayList<Writer> w = new ArrayList<>(); // Lista de escritores
  public Semaphore mutex = new Semaphore(1); // Semaforo mutex
  public Semaphore db = new Semaphore(1); // Semaforo db
  public int rc = 0; // Contador de leitores
  public ArrayList<ImageView> steveViewLeitor; // Lista de imagens dos leitores
  public ArrayList<ImageView> steveViewEscritor; // Lista de imagens dos escritores
  public ArrayList<StackPane> balloonView; // Lista de baloes
  public ArrayList<Image> items; // Lista de itens
  public Pane rectangle; // Retangulo
  public ArrayList<Path> paths; // Lista de caminhos
  public ArrayList<Pane> paneEscritor; // Lista de paineis dos escritores
  public PathTransition pathTransitionEscritorIda; // Transicao de caminho do escritor
  public Image gifImageSteve; // Imagem do Steve
  public ArrayList<Image> gifImage; // Lista de imagens
  public ArrayList<Rectangle> rectangles; // Lista de retangulos
  public PathTransition pathTransitionEscritorVolta; // Transicao de caminho do escritor
  public ArrayList<PathTransition> pathTransitionLeitor; // Lista de transicoes de caminho dos leitores
  public int imagemAtual = -1; // Imagem atual
  public ImageView chestView; // Imagem do bau
  public ArrayList<Rectangle> cellRectangles; // Lista de retangulos
  private Paint transparent; // Cor transparente
  private int count = 0; // Contador de itens
  private int pathNow = -1; // Caminho atual

  public Buffer(ArrayList<ImageView> steveViewLeitor, ArrayList<ImageView> steveViewEscritor,
      ArrayList<StackPane> balloonView, ArrayList<Image> items, Pane rectangle, ArrayList<Path> paths,
      ArrayList<Pane> paneEscritor, PathTransition pathTransitionEscritorIda, Image gifImageSteve,
      ArrayList<Image> gifImage, ArrayList<Rectangle> rectangles, PathTransition pathTransitionEscritorVolta,
      ArrayList<PathTransition> pathTransitionLeitor, ImageView chestView, ArrayList<Rectangle> cellRectangles) {
    this.steveViewLeitor = steveViewLeitor;
    this.steveViewEscritor = steveViewEscritor;
    this.balloonView = balloonView;
    this.items = items;
    this.rectangle = rectangle;
    this.paths = paths;
    this.paneEscritor = paneEscritor;
    this.pathTransitionEscritorIda = pathTransitionEscritorIda;
    this.gifImageSteve = gifImageSteve;
    this.gifImage = gifImage;
    this.rectangles = rectangles;
    this.pathTransitionEscritorVolta = pathTransitionEscritorVolta;
    this.pathTransitionLeitor = pathTransitionLeitor;
    this.chestView = chestView;
    this.cellRectangles = cellRectangles;
    this.transparent = cellRectangles.get(10).getFill();
  } // fim do construtor

  /* ***************************************************************
  * Metodo: run
  * Funcao: Metodo que inicia as threads de leitores e escritores.
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  @Override
  public void run() {
    for (int i = 0; i < READERS; i++) {
      r.add(new Reader(i, this, rectangles, gifImage));
      r.get(i).start();
    } // fim do for
 
    for (int i = 0; i < WRITERS; i++) {
      w.add(new Writer(i, this));
      w.get(i).start();
    } // fim do for
  } // fim do metodo run

  /* ***************************************************************
  * Metodo: setLeitorPause
  * Funcao: Metodo que seta a variavel pause da thread leitor.
  * Parametros: int i, boolean b
  * Retorno: void
  *************************************************************** */
  public void setLeitorPause(int i, boolean b) {
    r.get(i - 1).setPause(b);
  } // fim do metodo setLeitorPause

  /* ***************************************************************
  * Metodo: setEscritorPause
  * Funcao: Metodo que seta a variavel pause da thread escritor.
  * Parametros: int i, boolean b
  * Retorno: void
  *************************************************************** */
  public void setEscritorPause(int i, boolean b) {
    w.get(i - 1).setPause(b);
  } // fim do metodo setEscritorPause

  /* ***************************************************************
  * Metodo: setAllEscritorPause
  * Funcao: Metodo que seta a variavel pause de todas as threads escritor.
  * Parametros: boolean b
  * Retorno: void
  *************************************************************** */
  public void setAllEscritorPause(boolean b) {
    for (int i = 0; i < WRITERS; i++) {
      w.get(i).setPause(b);
    } // fim do for
  } // fim do metodo setAllEscritorPause

  /* ***************************************************************
  * Metodo: setAllLeitorPause
  * Funcao: Metodo que seta a variavel pause de todas as threads leitor.
  * Parametros: boolean b
  * Retorno: void
  *************************************************************** */
  public void setAllLeitorPause(boolean b) {
    for (int i = 0; i < READERS; i++) {
      r.get(i).setPause(b);
    } // fim do for
  } // fim do metodo setAllLeitorPause

  /* ***************************************************************
  * Metodo: setDelayEscritorObter
  * Funcao: Metodo que seta o valor da variavel delayObter da thread escritor.
  * Parametros: int i, int speed
  * Retorno: void
  *************************************************************** */
  public void setDelayEscritorObter(int i, int speed) {
    w.get(i - 1).setDelayObter(speed);
  } // fim do metodo setDelayEscritorObter

  /* ***************************************************************
  * Metodo: setDelayEscritorEscrever
  * Funcao: Metodo que seta o valor da variavel delayEscrever da thread escritor.
  * Parametros: int i, int speed
  * Retorno: void
  *************************************************************** */
  public void setDelayEscritorEscrever(int i, int speed) {
    w.get(i - 1).setDelayEscrever(speed);
  } // fim do metodo setDelayEscritorEscrever
 
  /* ***************************************************************
  * Metodo: setDelayLeitorLer
  * Funcao: Metodo que seta o valor da variavel delayLer da thread leitor.
  * Parametros: int i, int speed
  * Retorno: void
  *************************************************************** */
  public void setDelayLeitorLer(int i, int speed) {
    r.get(i - 1).setDelayLer(speed);
  } // fim do metodo setDelayLeitorLer

  /* ***************************************************************
  * Metodo: setDelayLeitorUtilizar
  * Funcao: Metodo que seta o valor da variavel delayUtilizar da thread leitor.
  * Parametros: int i, int speed
  * Retorno: void
  *************************************************************** */
  public void setDelayLeitorUtilizar(int i, int speed) {
    r.get(i - 1).setDelayUtilizar(speed);
  } // fim do metodo setDelayLeitorUtilizar

  /* ***************************************************************
  * Metodo: getPathTransitionLeitor
  * Funcao: Metodo que retorna o valor da variavel pathTransitionLeitor.
  * Parametros: int i
  * Retorno: PathTransition
  *************************************************************** */
  public synchronized PathTransition getPathTransitionLeitor(int i) {
    return pathTransitionLeitor.get(i);
  } // fim do metodo getPathTransitionLeitor

  /* ***************************************************************
  * Metodo: getImagemAtual
  * Funcao: Metodo que retorna o valor da variavel imagemAtual.
  * Parametros: void
  * Retorno: int
  *************************************************************** */
  public synchronized int getImagemAtual() {
    return imagemAtual;
  } // fim do metodo getImagemAtual

  /* ***************************************************************
  * Metodo: getRectangle
  * Funcao: Metodo que retorna o valor da variavel rectangles.
  * Parametros: int i
  * Retorno: Rectangle
  *************************************************************** */
  public synchronized Rectangle getRectangle(int i) {
    return rectangles.get(i);
  } // fim do metodo getRectangle

  /* ***************************************************************
  * Metodo: getGifImage
  * Funcao: Metodo que retorna o valor da variavel gifImage.
  * Parametros: int i
  * Retorno: Image
  *************************************************************** */
  public synchronized Image getGifImage(int i) {
    return gifImage.get(i);
  } // fim do metodo getGifImage

  /* ***************************************************************
  * Metodo: getSteveViewLeitor
  * Funcao: Metodo que retorna o valor da variavel steveViewLeitor.
  * Parametros: int i
  * Retorno: ImageView
  *************************************************************** */
  public synchronized ImageView getSteveViewLeitor(int i) {
    return steveViewLeitor.get(i);
  } // fim do metodo getSteveViewLeitor

  /* ***************************************************************
  * Metodo: getCellRectangles
  * Funcao: Metodo que retorna o valor da variavel cellRectangles.
  * Parametros: int i, int j
  * Retorno: boolean
  *************************************************************** */
  public synchronized boolean getCellRectangles(int i, int j) {
    if (cellRectangles.get(i).getFill().equals(transparent)) {
      cellRectangles.get(i).setFill(new ImagePattern(items.get(j)));
      count = i + 1;
      return true;
    } else {
      return false;
    } // fim do if
  } // fim do metodo getCellRectangles
 
  /* ***************************************************************
  * Metodo: getCellSize
  * Funcao: Metodo que retorna o valor da variavel count.
  * Parametros: void
  * Retorno: int
  *************************************************************** */
  public synchronized int getCellSize() {
    return count;
  } // fim do metodo getCellSize

  /* ***************************************************************
  * Metodo: getSteveViewEscritor
  * Funcao: Metodo que retorna o valor da variavel steveViewEscritor.
  * Parametros: int i
  * Retorno: ImageView
  *************************************************************** */
  public synchronized ImageView getSteveViewEscritor(int i) {
    return steveViewEscritor.get(i);
  } // fim do metodo getSteveViewEscritor

  /* ***************************************************************
  * Metodo: setTranslateXandY
  * Funcao: Metodo que seta o valor da variavel translateX e translateY
  * Parametros: int i, int j, int k
  * Retorno: void
  *************************************************************** */
  public synchronized void setTranslateXandY(int i, int j, int k) {
    steveViewEscritor.get(i).setTranslateX(j);
    steveViewEscritor.get(i).setTranslateY(k);
  } // fim do metodo setTranslateXandY

  /* ***************************************************************
  * Metodo: setPathNow
  * Funcao: Metodo que seta o valor da variavel pathNow.
  * Parametros: int i
  * Retorno: void
  *************************************************************** */
  public synchronized void setPathNow(int i) {
    pathNow = i;
  } // fim do metodo setPathNow

  /* ***************************************************************
  * Metodo: getPathNow
  * Funcao: Metodo que retorna o valor da variavel pathNow.
  * Parametros: void
  * Retorno: int
  *************************************************************** */
  public synchronized int getPathNow() {
    return pathNow;
  } // fim do metodo getPathNow

  /* **************************************************************
  * Metodo: setExitAll
  * Funcao: Metodo que seta a variavel exit de todas as threads.
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public synchronized void setExitAll() {
    for (int i = 0; i < READERS; i++) {
      r.get(i).setExit(true);
    } // fim do for

    for (int i = 0; i < WRITERS; i++) {
      w.get(i).setExit(true);
    } // fim do for
  } // fim do metodo setExitAll

  /* ***************************************************************
  * Metodo: removerFlip
  * Funcao: Metodo que remove o flip de todos os leitores.
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public synchronized void removerFlip() {
    for (int i = 0; i < READERS; i++) {
      r.get(i).removerFlip();
    } // fim do for
  } // fim do metodo removerFlip

  /* ***************************************************************
  * Metodo: interruptWriter
  * Funcao: Metodo que interrompe todas as threads de escritores.
  * Parametros: void
  * Retorno: void
  *************************************************************** */
  public void interruptReader() {
    for (int i = 0; i < READERS; i++) {
      r.get(i).interrupt();
    } // fim do for
  } // fim do metodo interruptWriter
} // fim da classe Buffer