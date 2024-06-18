/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: P24.java
* Funcao...........: Thread que controla o movimento do fantasma branco, caminho 24.
*************************************************************** */

import java.util.concurrent.Semaphore;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class P24 extends Thread {

    private Rectangle whiteCircle; // circulo branco que representa o pacman
    private volatile boolean interrupt = true; // variavel que define se a thread deve ser interrompida
    Semaphore[] last = new Semaphore[48]; // vetor de semaforos que guarda o ultimo semaforo usado

    public boolean normal = true; // variavel que define se o pacman esta normal ou nao
    // variaveis que controlam o movimento do pacman
    int case1 = -1;
    int case2 = -1;
    int case3 = -1;
    int case4 = -1;
    int case5 = -1;
    int case6 = -1;
    int case7 = -1;
    int case8 = -1;
    int case9 = -1;
    int case10 = -1;
    int case11 = -1;
    int case12 = -1;
    int case13 = -1;
    int case14 = -1;
    int case15 = -1;
    int case16 = -1;
    int case17 = -1;
    int case18 = -1;
    int case19 = -1;
    int case20 = -1;
    int case21 = -1;
    int case22 = -1;
    int case23 = -1;
    int case24 = -1;
    int case25 = -1;
    int case26 = -1;
    int case27 = -1;
    int case28 = -1;
    int case29 = -1;
    int case30 = -1;
    int case31 = -1;
    int case32 = -1;
    int case33 = -1;
    int case34 = -1;
    int case35 = -1;
    int case36 = -1;
    int case37 = -1;

    ThreadInfo p; // objeto que guarda informacoes das threads

    public PathTransition pathTransition; // variavel que controla a animacao do pacman

    public Slider slider; // slider que controla a velocidade do pacman
    private volatile boolean pause = false; // variavel que define se a animacao deve ser pausada

    public P24(int i, Rectangle whiteCircle, ThreadInfo threadInfo, Slider sliderP24) {

        this.whiteCircle = whiteCircle;
        this.slider = sliderP24;

        this.p = threadInfo;
    } // fim do construtor

    /* ***************************************************************
    * Metodo: run
    * Funcao: inicializa as imagens do pacman
    * Parametros: sem parametros
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        Paint imagePatternRight = whiteCircle.getFill();

        Path path1 = new Path(
                new MoveTo(100, 600),
                new LineTo(170, 600));

        Path path3 = new Path(
                new MoveTo(170, 600),
                new LineTo(230, 600));

        Path path4 = new Path(
                new MoveTo(230, 600),
                new LineTo(270, 600));

        Path path5 = new Path(
                new MoveTo(270, 600),
                new LineTo(300, 600),
                new LineTo(300, 570));

        Path path6 = new Path(
                new MoveTo(300, 570),
                new LineTo(300, 500),
                new LineTo(330, 500));

        Path path8 = new Path(
                new MoveTo(330, 500),
                new LineTo(370, 500));

        Path path9 = new Path(
                new MoveTo(370, 500),
                new LineTo(400, 500),
                new LineTo(400, 470));

        Path path10 = new Path(
                new MoveTo(400, 470),
                new LineTo(400, 430));

        Path path11 = new Path(
                new MoveTo(400, 430),
                new LineTo(400, 400),
                new LineTo(470, 400));

        Path path13 = new Path(
                new MoveTo(470, 400),
                new LineTo(500, 400),
                new LineTo(500, 370));

        Path path14 = new Path(
                new MoveTo(500, 370),
                new LineTo(500, 330));

        Path path15 = new Path(
                new MoveTo(500, 330),
                new LineTo(500, 300),
                new LineTo(530, 300));

        Path path16 = new Path(
                new MoveTo(530, 300),
                new LineTo(570, 300));

        Path path17 = new Path(
                new MoveTo(570, 300),
                new LineTo(600, 300),
                new LineTo(600, 270));

        Path path18 = new Path(
                new MoveTo(600, 270),
                new LineTo(600, 230));

        Path path19 = new Path(
                new MoveTo(600, 230),
                new LineTo(600, 170));

        Path path20 = new Path(
                new MoveTo(600, 170),
                new LineTo(600, 100),
                new LineTo(530, 100));

        Path path23 = new Path(
                new MoveTo(530, 100),
                new LineTo(470, 100));

        Path path24 = new Path(
                new MoveTo(470, 100),
                new LineTo(400, 100),
                new LineTo(400, 130));

        Path path26 = new Path(
                new MoveTo(400, 130),
                new LineTo(400, 170));

        Path path27 = new Path(
                new MoveTo(400, 170),
                new LineTo(400, 200),
                new LineTo(370, 200));

        Path path28 = new Path(
                new MoveTo(370, 200),
                new LineTo(300, 200),
                new LineTo(300, 230));

        Path path30 = new Path(
                new MoveTo(300, 230),
                new LineTo(300, 270));

        Path path31 = new Path(
                new MoveTo(300, 270),
                new LineTo(300, 300),
                new LineTo(270, 300));

        Path path32 = new Path(
                new MoveTo(270, 300),
                new LineTo(230, 300));

        Path path33 = new Path(
                new MoveTo(230, 300),
                new LineTo(200, 300),
                new LineTo(200, 330));

        Path path34 = new Path(
                new MoveTo(200, 330),
                new LineTo(200, 370));

        Path path35 = new Path(
                new MoveTo(200, 370),
                new LineTo(200, 400),
                new LineTo(170, 400));

        Path path36 = new Path(
                new MoveTo(170, 400),
                new LineTo(130, 400));

        Path path37 = new Path(
                new MoveTo(130, 400),
                new LineTo(100, 400),
                new LineTo(100, 430));

        Path path38 = new Path(
                new MoveTo(100, 430),
                new LineTo(100, 600),
                new LineTo(170, 600));

        Image ghostleft = new Image("/img/ezgif.com-rotate (7).gif");
        Image ghostup = new Image("/img/blackghost_1_1_04077.gif");
        Image ghostdown = new Image("/img/blackghost_2_04015.gif");
        ImagePattern imagePatternLeft = new ImagePattern(ghostleft);
        ImagePattern imagePatternUp = new ImagePattern(ghostup);
        ImagePattern imagePatternDown = new ImagePattern(ghostdown);

        pathTransition = new PathTransition();

        if (interrupt) {
            SimpleDoubleProperty dividedRateProperty = new SimpleDoubleProperty();
            dividedRateProperty.bind(Bindings.divide(this.slider.valueProperty(), 1.0));
            pathTransition.rateProperty().bind(dividedRateProperty);
            pathTransition.setDuration(Duration.seconds(2.8));
            pathTransition.setPath(path1);
            pathTransition.setNode(whiteCircle);
            pathTransition.setCycleCount(1);
            pathTransition.setInterpolator(Interpolator.LINEAR);
        } // fim do if

        Platform.runLater(() -> {
            if (interrupt) {
                if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                    pathTransition.play();
                    pathTransition.setPath(null);
                } // fim do if
            } // fim do if
        }); // fim do Platform.runLater

        if (interrupt) {
            try {
                p.semaphore100_400__200_600.acquire();
                last[3] = p.semaphore100_400__200_600;
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            } // fim do try/catch
        } // fim do if

        while (interrupt) {

            if (pathTransition.getStatus() == PathTransition.Status.STOPPED) {

                switch (whiteCircle.getTranslateX() + " " + whiteCircle.getTranslateY()) {

                    case 170.0 + " " + 600.0:
                        if (case2 == -1 && interrupt) {
                            case37 = -1;
                            pathTransition.stop();

                            if (normal) {
                                whiteCircle.setFill(imagePatternRight);
                            } // fim do if

                            try {
                                p.semaphore200_600__300_600.acquire();
                                last[40] = p.semaphore200_600__300_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case2 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 230.0 + " " + 600.0:
                        if (case3 == -1 && interrupt) {
                            case2 = -1;
                            pathTransition.stop();

                            p.semaphore100_400__200_600.release();
                            last[3] = null;

                            case3 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path4);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 270.0 + " " + 600.0:
                        if (case4 == -1 && interrupt) {
                            case3 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore300_600__300_500.acquire();
                                last[28] = p.semaphore300_600__300_500;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case4 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path5);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 300.0 + " " + 570.0:
                        if (case5 == -1 && interrupt) {
                            case4 = -1;
                            pathTransition.stop();
                            if (normal) {
                                whiteCircle.setFill(imagePatternUp);
                            } // fim do if

                            p.semaphore200_600__300_600.release();
                            last[40] = null;

                            case5 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path6);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 330.0 + " " + 500.0:
                        if (case7 == -1 && interrupt) {
                            case5 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternRight);
                            } // fim do if
                            pathTransition.stop();

                            p.semaphore300_600__300_500.release();
                            last[28] = null;

                            case7 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 370.0 + " " + 500.0:
                        if (case8 == -1 && interrupt) {
                            case7 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore_intersec_370_500__430_500.acquire();
                                last[41] = p.semaphore_intersec_370_500__430_500;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case8 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path9);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 400.0 + " " + 470.0:
                        if (case9 == -1 && interrupt) {
                            case8 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternUp);
                            } // fim do if
                            pathTransition.stop();

                            p.semaphore_intersec_370_500__430_500.release();
                            last[41] = null;

                            case9 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path10);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 400.0 + " " + 430.0:
                        if (case10 == -1 && interrupt) {
                            case9 = -1;
                            pathTransition.stop();

                            while (interrupt) {
                                if (p.semaphore400_400__500_400.tryAcquire()) {
                                    last[11] = p.semaphore400_400__500_400;
                                    if (p.semaphore500_400__500_300.tryAcquire()) {
                                        last[36] = p.semaphore500_400__500_300;
                                        break;
                                    } else {
                                        p.semaphore400_400__500_400.release();
                                        last[11] = null;

                                    } // fim do if

                                } else {
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {

                                        Thread.currentThread().interrupt();
                                    } // fim do try/catch
                                } // fim do if
                            } // fim do while

                            case10 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path11);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 470.0 + " " + 400.0:
                        if (case12 == -1 && interrupt) {
                            case10 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternRight);
                            } // fim do if
                            pathTransition.stop();

                            try {
                                p.semaphore_inter_470_400__530_400.acquire();
                                last[13] = p.semaphore_inter_470_400__530_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case12 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path13);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break; 

                    case 500.0 + " " + 370.0:
                        if (case13 == -1 && interrupt) {
                            case12 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternUp);
                            } // fim do if
                            pathTransition.stop();

                            p.semaphore400_400__500_400.release();
                            last[11] = null;

                            p.semaphore_inter_470_400__530_400.release();
                            last[13] = null;

                            case13 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path14);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 500.0 + " " + 330.0:
                        if (case14 == -1 && interrupt) {
                            case13 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore500_300__600_300.acquire();
                                last[42] = p.semaphore500_300__600_300;
                                p.semaphore_inter_470_300__530_300.acquire();
                                last[35] = p.semaphore_inter_470_300__530_300;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case14 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path15);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 530.0 + " " + 300.0:
                        if (case15 == -1 && interrupt) {
                            case14 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternRight);
                            } // fim do if
                            pathTransition.stop();

                            p.semaphore500_400__500_300.release();
                            last[36] = null;
                            p.semaphore_inter_470_300__530_300.release();
                            last[35] = null;

                            case15 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path16);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if 
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 570.0 + " " + 300.0:
                        if (case16 == -1 && interrupt) {
                            case15 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore600_300__600_200.acquire();
                                last[18] = p.semaphore600_300__600_200;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case16 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path17);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 600.0 + " " + 270.0:
                        if (case17 == -1 && interrupt) {
                            case16 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternUp);
                            } // fim do if
                            pathTransition.stop();

                            p.semaphore500_300__600_300.release();
                            last[42] = null;

                            case17 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path18);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if  
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 600.0 + " " + 230.0:
                        if (case18 == -1 && interrupt) {
                            case17 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore600_200__500_100.acquire();
                                last[19] = p.semaphore600_200__500_100;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case18 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path19);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 600.0 + " " + 170.0:
                        if (case19 == -1 && interrupt) {
                            case18 = -1;
                            pathTransition.stop();

                            p.semaphore600_300__600_200.release();
                            last[18] = null;

                            case19 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(5.6));
                                    pathTransition.setPath(path20);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 530.0 + " " + 100.0:
                        if (case22 == -1 && interrupt) {
                            case19 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternLeft);
                            } // fim do if
                            pathTransition.stop();

                            while (interrupt) {
                                if (p.semaphore500_100__400_100.tryAcquire()) {
                                    last[21] = p.semaphore500_100__400_100;
                                    if (p.semaphore400_200__400_100.tryAcquire()) {
                                        last[20] = p.semaphore400_200__400_100;
                                        break;
                                    } else {
                                        p.semaphore500_100__400_100.release();
                                        last[21] = null;
                                    } // fim do if
                                } else {
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {

                                        Thread.currentThread().interrupt();
                                    } // fim do try/catch
                                } // fim do if
                            } // fim do while

                            case22 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path23);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 470.0 + " " + 100.0:
                        if (case23 == -1 && interrupt) {
                            case22 = -1;
                            pathTransition.stop();

                            p.semaphore600_200__500_100.release();
                            last[19] = null;

                            case23 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path24);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 400.0 + " " + 130.0:
                        if (case25 == -1 && interrupt) {
                            case23 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternDown);
                            } // fim do if
                            pathTransition.stop();

                            p.semaphore500_100__400_100.release();
                            last[21] = null;

                            case25 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path26);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 400.0 + " " + 170.0:
                        if (case26 == -1 && interrupt) {
                            case25 = -1;
                            pathTransition.stop();

                            while (interrupt) {
                                if (p.semaphore400_200__300_200.tryAcquire()) {
                                    last[22] = p.semaphore400_200__300_200;
                                    if (p.semaphore300_300__300_200.tryAcquire()) {
                                        last[30] = p.semaphore300_300__300_200;
                                        if (p.semaphore_inter_270_200__330_200.tryAcquire()) {
                                            last[24] = p.semaphore_inter_270_200__330_200;
                                            break;
                                        } else {
                                            p.semaphore400_200__300_200.release();
                                            last[22] = null;
                                            p.semaphore300_300__300_200.release();
                                            last[30] = null;
                                        } // fim do if
                                    } else {
                                        p.semaphore400_200__300_200.release();
                                        last[22] = null;
                                    } // fim do if
                                } else {
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {

                                        Thread.currentThread().interrupt();
                                    } // fim do try/catch
                                } // fim do if
                            } // fim do while

                            case26 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path27);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 370.0 + " " + 200.0:
                        if (case27 == -1 && interrupt) {
                            case26 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternLeft);
                            } // fim do if
                            pathTransition.stop();

                            p.semaphore400_200__400_100.release();
                            last[20] = null;

                            case27 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path28);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 300.0 + " " + 230.0:
                        if (case29 == -1 && interrupt) {
                            case27 = -1;
                            pathTransition.stop();
                            if (normal) {
                                whiteCircle.setFill(imagePatternDown);
                            } // fim do if

                            p.semaphore_inter_270_200__330_200.release();

                            last[24] = null;
                            p.semaphore400_200__300_200.release();
                            last[22] = null;

                            case29 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path30);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 300.0 + " " + 270.0:
                        if (case30 == -1 && interrupt) {
                            case29 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore_inter_270_300__330_300.acquire();
                                last[10] = p.semaphore_inter_270_300__330_300;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case30 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path31);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 270.0 + " " + 300.0:
                        if (case31 == -1 && interrupt) {
                            case30 = -1;
                            pathTransition.stop();
                            if (normal) {
                                whiteCircle.setFill(imagePatternLeft);
                            } // fim do if

                            p.semaphore_inter_270_300__330_300.release();
                            last[10] = null;
                            p.semaphore300_300__300_200.release();
                            last[30] = null;

                            case31 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path32);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 230.0 + " " + 300.0:
                        if (case32 == -1 && interrupt) {
                            case31 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore_inter_200_270__200_330.acquire();
                                last[43] = p.semaphore_inter_200_270__200_330;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case32 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path33);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 200.0 + " " + 330.0:
                        if (case33 == -1 && interrupt) {
                            case32 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternDown);
                            } // fim do if
                            pathTransition.stop();

                            p.semaphore_inter_200_270__200_330.release();
                            last[43] = null;

                            case33 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path34);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 200.0 + " " + 370.0:
                        if (case34 == -1 && interrupt) {
                            case33 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore100_400__200_400.acquire();
                                last[39] = p.semaphore100_400__200_400;
                                p.semaphore_inter_200_370__200_430.acquire();
                                last[6] = p.semaphore_inter_200_370__200_430;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case34 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path35);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 170.0 + " " + 400.0:
                        if (case35 == -1 && interrupt) {
                            case34 = -1;
                            if (normal) {
                                whiteCircle.setFill(imagePatternLeft);
                            } // fim do if
                            pathTransition.stop();

                            p.semaphore_inter_200_370__200_430.release();
                            last[6] = null;

                            case35 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path36);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 130.0 + " " + 400.0:
                        if (case36 == -1 && interrupt) {
                            case35 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore100_400__200_600.acquire();
                                last[3] = p.semaphore100_400__200_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } // fim do try/catch

                            case36 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path37);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                    case 100.0 + " " + 430.0:
                        if (case37 == -1 && interrupt) {
                            case36 = -1;
                            pathTransition.stop();
                            if (normal) {
                                whiteCircle.setFill(imagePatternDown);
                            } // fim do if

                            p.semaphore100_400__200_400.release();
                            last[39] = null;

                            case37 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(9.6));
                                    pathTransition.setPath(path38);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    } // fim do if
                                } // fim do if
                            }); // fim do Platform.runLater
                        } // fim do if
                        break;

                } // fim do switch

            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                } // fim do try/catch
            } // fim do if

            if (isInterrupted()) {
                p.resetAllSemaphores();
                break;
            } // fim do if

            if (!interrupt) {
                for (Semaphore semaforo : last) {
                    if (semaforo != null) {
                        semaforo.release();
                    } // fim do if
                } // fim do for
                break;
            } // fim do if
        } // fim do while

    } // fim do metodo run

    /* ***************************************************************
    * Metodo: setInterrupt
    * Funcao: interrompe a thread
    * Parametros: sem parametros
    * Retorno: void
    *************************************************************** */
    public void setInterrupt() {

        interrupt = false;

        case1 = 0;
        case2 = 0;
        case3 = 0;
        case4 = 0;
        case5 = 0;
        case6 = 0;
        case7 = 0;
        case8 = 0;
        case9 = 0;
        case10 = 0;
        case11 = 0;
        case12 = 0;
        case13 = 0;
        case14 = 0;
        case15 = 0;
        case16 = 0;
        case17 = 0;
        case18 = 0;
        case19 = 0;
        case20 = 0;
        case21 = 0;
        case22 = 0;
        case23 = 0;
        case24 = 0;
        case25 = 0;
        case26 = 0;
        case27 = 0;
        case28 = 0;
        case29 = 0;
        case30 = 0;
        case31 = 0;
        case32 = 0;
        case33 = 0;
        case34 = 0;
        case35 = 0;
        case36 = 0;
        case37 = 0;

        if (pathTransition != null) {
            pathTransition.stop();
            Platform.runLater(() -> {
                pathTransition.setNode(null);
                pathTransition.setPath(null);
            }); // fim do Platform.runLater
        } // fim do if
    } // fim do metodo setInterrupt

    /* ***************************************************************
    * Metodo: setPause
    * Funcao: pausa a thread
    * Parametros: boolean b
    * Retorno: void
    *************************************************************** */
    public void setPause(boolean b) {
        pause = b;
        if (b == true) {
            Platform.runLater(() -> {
                pathTransition.pause();
            }); // fim do Platform.runLater
        } // fim do if
    } // fim do metodo setPause
} // fim da classe P24
