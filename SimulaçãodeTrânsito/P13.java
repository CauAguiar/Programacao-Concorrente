/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: P13.java
* Funcao...........: Thread que controla o movimento do fantasma roxo, caminho 13.
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

public class P13 extends Thread {

    private Rectangle purpleCircle; // Fantasma roxo
    private volatile boolean interrupt = true; // Variavel para interromper a thread
    private volatile boolean pause = false; // Variavel para pausar a thread

    Semaphore[] last = new Semaphore[48]; // Vetor de semaforos

    public boolean normal = true; // Variavel para verificar se o pacman esta normal ou nao
    // Variaveis para verificar se o fantasma esta em um determinado ponto
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
    int case2_0 = -1;

    ThreadInfo p; // Objeto da classe ThreadInfo

    public PathTransition pathTransition; // Objeto da classe PathTransition

    Slider slider; // Slider para controlar a velocidade do pacman

    public P13(int i, Rectangle purpleCircle, ThreadInfo threadInfo, Slider sliderP13) {

        this.purpleCircle = purpleCircle;
        this.slider = sliderP13;

        this.p = threadInfo;
    }

    /* ***************************************************************
    * Metodo: run
    * Funcao: inicializa as imagens do pacman
    * Parametros: sem parametros
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        Paint imagePatternRight = purpleCircle.getFill();

        Path path = new Path(
                new MoveTo(300, 600),
                new LineTo(300, 570));

        Path path1 = new Path(
                new MoveTo(300, 570),
                new LineTo(300, 470));

        Path path0 = new Path(
                new MoveTo(300, 470),
                new LineTo(300, 430));

        Path path2 = new Path(
                new MoveTo(300, 430),
                new LineTo(300, 370));

        Path path3 = new Path(
                new MoveTo(300, 370),
                new LineTo(300, 330));

        Path path4 = new Path(
                new MoveTo(300, 330),
                new LineTo(300, 270));

        Path path5 = new Path(
                new MoveTo(300, 270),
                new LineTo(300, 230));

        Path path6 = new Path(
                new MoveTo(300, 230),
                new LineTo(300, 170));

        Path path7 = new Path(
                new MoveTo(300, 170),
                new LineTo(300, 100),
                new LineTo(330, 100));

        Path path9 = new Path(
                new MoveTo(330, 100),
                new LineTo(370, 100));

        Path path10 = new Path(
                new MoveTo(370, 100),
                new LineTo(430, 100));

        Path path11 = new Path(
                new MoveTo(430, 100),
                new LineTo(500, 100),
                new LineTo(500, 130));

        Path path13 = new Path(
                new MoveTo(500, 130),
                new LineTo(500, 170));

        Path path14 = new Path(
                new MoveTo(500, 170),
                new LineTo(500, 230));

        Path path15 = new Path(
                new MoveTo(500, 230),
                new LineTo(500, 270));

        Path path16 = new Path(
                new MoveTo(500, 270),
                new LineTo(500, 330));

        Path path17 = new Path(
                new MoveTo(500, 330),
                new LineTo(500, 370));

        Path path18 = new Path(
                new MoveTo(500, 370),
                new LineTo(500, 430));

        Path path19 = new Path(
                new MoveTo(500, 430),
                new LineTo(500, 470));

        Path path20 = new Path(
                new MoveTo(500, 470),
                new LineTo(500, 530));

        Path path21 = new Path(
                new MoveTo(500, 530),
                new LineTo(500, 570));

        Path path22 = new Path(
                new MoveTo(500, 570),
                new LineTo(500, 600),
                new LineTo(470, 600));

        Path path23 = new Path(
                new MoveTo(470, 600),
                new LineTo(330, 600));

        Path path24 = new Path(
                new MoveTo(330, 600),
                new LineTo(300, 600),
                new LineTo(300, 570));

        Image ghostleft = new Image("/img/ezgif.com-rotate (5).gif");
        Image ghostup = new Image("/img/blackghost_1_1_04062.gif");
        Image ghostdown = new Image("/img/blackghost_2_03988.gif");
        ImagePattern imagePatternLeft = new ImagePattern(ghostleft);
        ImagePattern imagePatternUp = new ImagePattern(ghostup);
        ImagePattern imagePatternDown = new ImagePattern(ghostdown);

        pathTransition = new PathTransition();

        if (interrupt) {
            SimpleDoubleProperty dividedRateProperty = new SimpleDoubleProperty();
            dividedRateProperty.bind(Bindings.divide(this.slider.valueProperty(), 1.0));
            pathTransition.rateProperty().bind(dividedRateProperty);
            pathTransition.setDuration(Duration.seconds(1.2));
            pathTransition.setPath(path);
            pathTransition.setNode(purpleCircle);
            if (normal) {
                purpleCircle.setFill(imagePatternUp);
            }
            pathTransition.setCycleCount(1);
            pathTransition.setInterpolator(Interpolator.LINEAR);
        }

        Platform.runLater(() -> {
            if (interrupt) {
                if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                    pathTransition.play();
                    pathTransition.setPath(null);
                }
            }
        });

        if (interrupt) {
            try {
                p.semaphore300_600__500_600.acquire();
                last[25] = p.semaphore300_600__500_600;
                p.semaphore_new_300_300__300_600__500_600.acquire();
                last[26] = p.semaphore_new_300_300__300_600__500_600;
                p.semaphore300_500__300_600__400_600.acquire();
                last[27] = p.semaphore300_500__300_600__400_600;
                p.semaphore300_600__300_500.acquire();
                last[28] = p.semaphore300_600__300_500;
                p.semaphore300_600__400_600.acquire();
                last[29] = p.semaphore300_600__400_600;
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }

        while (interrupt) {
            /*
             * System.out.println("purple " + purpleCircle.getTranslateX() + " "
             * + purpleCircle.getTranslateY());
             */

            if (pathTransition.getStatus() == PathTransition.Status.STOPPED) {

                /*
                 * System.out.println("purple  f" + purpleCircle.getTranslateX() + " "
                 * + purpleCircle.getTranslateY());
                 */

                switch (purpleCircle.getTranslateX() + " " + purpleCircle.getTranslateY()) {
                    case 300.0 + " " + 570.0:
                        if (case1 == -1 && interrupt) {
                            case24 = -1;
                            pathTransition.stop();

                            p.semaphore300_600__500_600.release();
                            last[25] = null;
                            p.semaphore300_600__400_600.release();
                            last[29] = null;

                            case1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 470.0:
                        if (case2_0 == -1 && interrupt) {
                            case1 = -1;
                            pathTransition.stop();

                            p.semaphore300_500__300_600__400_600.release();
                            last[27] = null;
                            p.semaphore300_600__300_500.release();
                            last[28] = null;

                            case2_0 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 430.0:
                        if (case2 == -1 && interrupt) {
                            case2_0 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore300_400__300_300.acquire();
                                last[8] = p.semaphore300_400__300_300;
                                p.semaphore_inter_270_400__330_400.acquire();
                                last[9] = p.semaphore_inter_270_400__330_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case2 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path2);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 370.0:
                        if (case3 == -1 && interrupt) {
                            case2 = -1;
                            pathTransition.stop();

                            p.semaphore_inter_270_400__330_400.release();
                            last[9] = null;
                            case3 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 330.0:
                        if (case4 == -1 && interrupt) {
                            case3 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore300_300__300_200.acquire();
                                last[30] = p.semaphore300_300__300_200;
                                p.semaphore_inter_270_300__330_300.acquire();
                                last[10] = p.semaphore_inter_270_300__330_300;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case4 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 270.0:
                        if (case5 == -1 && interrupt) {
                            case4 = -1;
                            pathTransition.stop();

                            p.semaphore300_400__300_300.release();
                            last[8] = null;
                            p.semaphore_new_300_300__300_600__500_600.release();
                            last[26] = null;
                            p.semaphore_inter_270_300__330_300.release();
                            last[10] = null;

                            case5 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path5);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 230.0:
                        if (case6 == -1 && interrupt) {
                            case5 = -1;
                            pathTransition.stop();

                            while (interrupt) {

                                if (p.semaphore300_200__300_100.tryAcquire()) {
                                    last[23] = p.semaphore300_200__300_100;
                                    if (p.semaphore_inter_270_200__330_200.tryAcquire()) {
                                        last[24] = p.semaphore_inter_270_200__330_200;
                                        if (p.semaphore300_100_500_100.tryAcquire()) {
                                            last[31] = p.semaphore300_100_500_100;
                                            if (p.semaphore300_100__400_100.tryAcquire()) {
                                                last[32] = p.semaphore300_100__400_100;
                                                break;
                                            } else {
                                                p.semaphore300_100_500_100.release();
                                                last[31] = null;
                                                p.semaphore_inter_270_200__330_200.release();

                                                last[24] = null;
                                                p.semaphore300_200__300_100.release();

                                                last[23] = null;
                                            }
                                        } else {
                                            p.semaphore300_200__300_100.release();

                                            last[23] = null;
                                            p.semaphore_inter_270_200__330_200.release();

                                            last[24] = null;

                                        }
                                    } else {

                                        p.semaphore300_200__300_100.release();

                                        last[23] = null;

                                    }
                                } else {
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {

                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }

                            case6 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path6);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 170.0:
                        if (case7 == -1 && interrupt) {
                            case6 = -1;
                            pathTransition.stop();

                            p.semaphore_inter_270_200__330_200.release();

                            last[24] = null;
                            p.semaphore300_300__300_200.release();
                            last[30] = null;
                            case7 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path7);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 100.0:
                        if (case9 == -1 && interrupt) {
                            case7 = -1;

                            if (normal) {
                                purpleCircle.setFill(imagePatternRight);
                            }

                            pathTransition.stop();

                            p.semaphore300_200__300_100.release();

                            last[23] = null;

                            case9 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path9);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 370.0 + " " + 100.0:
                        if (case10 == -1 && interrupt) {
                            case9 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore500_100__400_100.acquire();
                                last[21] = p.semaphore500_100__400_100;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case10 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path10);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 430.0 + " " + 100.0:
                        if (case11 == -1 && interrupt) {
                            case10 = -1;

                            if (normal) {
                                purpleCircle.setFill(imagePatternDown);
                            }

                            pathTransition.stop();

                            p.semaphore300_100__400_100.release();
                            last[32] = null;

                            case11 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path11);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 130.0:
                        if (case13 == -1 && interrupt) {
                            case11 = -1;
                            pathTransition.stop();

                            p.semaphore300_100_500_100.release();
                            last[31] = null;
                            p.semaphore500_100__400_100.release();
                            last[21] = null;

                            case13 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path13);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 170.0:
                        if (case14 == -1 && interrupt) {
                            case13 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore_novo_500_300__500_200.acquire();
                                last[33] = p.semaphore_novo_500_300__500_200;
                                p.semaphore_inter_470_200__530_200.acquire();
                                last[34] = p.semaphore_inter_470_200__530_200;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case14 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path14);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 230.0:
                        if (case15 == -1 && interrupt) {
                            case14 = -1;
                            pathTransition.stop();

                            p.semaphore_inter_470_200__530_200.release();
                            last[34] = null;

                            case15 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path15);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 270.0:
                        if (case16 == -1 && interrupt) {
                            case15 = -1;
                            pathTransition.stop();

                            while (interrupt) {
                                if (p.semaphore_inter_470_300__530_300.tryAcquire()) {
                                    last[35] = p.semaphore_inter_470_300__530_300;
                                    if (p.semaphore500_400__500_300.tryAcquire()) {
                                        last[36] = p.semaphore500_400__500_300;
                                        break;
                                    } else {
                                        p.semaphore_inter_470_300__530_300.release();
                                        last[35] = null;
                                    }
                                } else {
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {

                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }

                            case16 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path16);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 330.0:
                        if (case17 == -1 && interrupt) {
                            case16 = -1;
                            pathTransition.stop();

                            p.semaphore_novo_500_300__500_200.release();
                            last[33] = null;
                            p.semaphore_inter_470_300__530_300.release();
                            last[35] = null;

                            case17 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path17);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 370.0:
                        if (case18 == -1 && interrupt) {
                            case17 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore_novo_500_500__500_400.acquire();
                                last[12] = p.semaphore_novo_500_500__500_400;
                                p.semaphore_inter_470_400__530_400.acquire();
                                last[13] = p.semaphore_inter_470_400__530_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case18 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path18);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 430.0:
                        if (case19 == -1 && interrupt) {
                            case18 = -1;
                            pathTransition.stop();

                            p.semaphore_inter_470_400__530_400.release();
                            last[13] = null;

                            case19 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path19);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 470.0:
                        if (case20 == -1 && interrupt) {
                            case19 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore500_500__500_600.acquire();
                                last[14] = p.semaphore500_500__500_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case20 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path20);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 530.0:
                        if (case21 == -1 && interrupt) {
                            case20 = -1;
                            pathTransition.stop();

                            p.semaphore500_400__500_300.release();
                            last[36] = null;
                            p.semaphore_novo_500_500__500_400.release();
                            last[12] = null;

                            case21 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path21);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 570.0:
                        if (case22 == -1 && interrupt) {
                            case21 = -1;
                            if (normal) {
                                purpleCircle.setFill(imagePatternLeft);
                            }
                            pathTransition.stop();

                            try {

                                while (interrupt) {
                                    if (p.semaphore300_600__500_600.tryAcquire()) {
                                        last[25] = p.semaphore300_600__500_600;
                                        if (p.semaphore_new_300_300__300_600__500_600.tryAcquire()) {
                                            last[26] = p.semaphore_new_300_300__300_600__500_600;
                                            if (p.semaphore300_500__300_600__400_600.tryAcquire()) {
                                                last[27] = p.semaphore300_500__300_600__400_600;
                                                if (p.semaphore300_600__400_600.tryAcquire()) {
                                                    last[29] = p.semaphore300_600__400_600;
                                                    break;
                                                } else {
                                                    p.semaphore300_500__300_600__400_600.release();
                                                    last[27] = null;
                                                    p.semaphore_new_300_300__300_600__500_600.release();
                                                    last[26] = null;
                                                    p.semaphore300_600__500_600.release();
                                                    last[25] = null;
                                                }

                                            } else {
                                                p.semaphore300_600__500_600.release();
                                                last[25] = null;
                                                p.semaphore_new_300_300__300_600__500_600.release();
                                                last[26] = null;
                                            }

                                        } else {
                                            p.semaphore300_600__500_600.release();
                                            last[25] = null;
                                        }
                                    } else {
                                        Thread.sleep(10);
                                    }
                                }

                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case22 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path22);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 600.0:
                        if (case23 == -1 && interrupt) {
                            case22 = -1;
                            pathTransition.stop();

                            p.semaphore500_500__500_600.release();
                            last[14] = null;

                            case23 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(5.6));
                                    pathTransition.setPath(path23);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 600.0:
                        if (case24 == -1 && interrupt) {
                            case23 = -1;
                            pathTransition.stop();

                            if (normal) {
                                purpleCircle.setFill(imagePatternUp);
                            }

                            try {
                                p.semaphore300_600__300_500.acquire();
                                last[28] = p.semaphore300_600__300_500;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case24 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path24);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                }
            }
            if (isInterrupted()) {
                p.resetAllSemaphores();
                break;
            }

            if (!interrupt) {
                for (Semaphore semaforo : last) {
                    if (semaforo != null) {
                        semaforo.release();
                    }
                }
                break;
            }

        }

    }

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
        case2_0 = 0;

        if (pathTransition != null) {
            pathTransition.stop();
            Platform.runLater(() -> {
                pathTransition.setNode(null);
                pathTransition.setPath(null);
            });
        }
    }

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
            });
        }
    }
}