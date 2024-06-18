/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: P8.java
* Funcao...........: Thread que controla o movimento do fantasma azul, caminho 8.
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

public class P8 extends Thread {

    private Rectangle blueCircle; // Fantasma azul
    ThreadInfo p; // Informacoes das threads
    private volatile boolean pause = false; // Variavel para pausar a thread

    private volatile boolean interrupt = true; // Variavel para interromper a thread

    Semaphore[] last = new Semaphore[48]; // Vetor de semaforos
    // Variaveis para controlar o movimento do fantasma
    public boolean normal = true;
    int case1 = -1;
    int case2 = -1;
    int case2_1 = -1;
    int case3 = -1;
    int case4 = -1;
    int case41 = -1;
    int case5 = -1;
    int case6 = -1;
    int case7 = -1;
    int case8 = -1;
    int case9 = -1;
    int case10 = -1;
    int case5_1 = -1;
    int case6_1 = -1;
    int case7_1 = -1;
    int case8_1 = -1;
    int case9_1 = -1;
    int case1_0 = -1;
    int case2_01 = -1;
    int case2_02 = -1;
    int case3_0 = -1;
    int case001 = -1;
    int case01 = -1;
    int case10_0 = -1;
    int case4_0 = -1;

    public PathTransition pathTransition; // Transicao de caminho
    public Slider slider; // Slider para controlar a velocidade da thread

    public P8(int i, Rectangle blueCircle2, ThreadInfo threadInfo, Slider sliderP8) {
        this.blueCircle = blueCircle2;
        this.slider = sliderP8;

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
        Paint imagePatternRight = blueCircle.getFill();

        Path path = new Path(
                new MoveTo(100, 200),
                new LineTo(170, 200));

        Path path0 = new Path(
                new MoveTo(170, 200),
                new LineTo(270, 200));

        Path path8_0 = new Path(
                new MoveTo(270, 200),
                new LineTo(330, 200));

        Path path8_00 = new Path(
                new MoveTo(330, 200),
                new LineTo(430, 200));

        Path path8_1 = new Path(
                new MoveTo(430, 200),
                new LineTo(470, 200));

        Path path8_1_1 = new Path(
                new MoveTo(470, 200),
                new LineTo(530, 200));

        Path path8_1_2 = new Path(
                new MoveTo(530, 200),
                new LineTo(570, 200));

        Path path8_2 = new Path(
                new MoveTo(570, 200),
                new LineTo(600, 200),
                new LineTo(600, 330));

        Path path8_2_00 = new Path(
                new MoveTo(600, 330),
                new LineTo(600, 370));

        Path path8_2_1 = new Path(
                new MoveTo(600, 370),
                new LineTo(600, 400),
                new LineTo(570, 400));

        Path path8_3 = new Path(
                new MoveTo(570, 400),
                new LineTo(530, 400));

        Path path8_3_1 = new Path(
                new MoveTo(530, 400),
                new LineTo(470, 400));

        Path path8_4 = new Path(
                new MoveTo(470, 400),
                new LineTo(430, 400));

        Path path8_4_1 = new Path(
                new MoveTo(430, 400),
                new LineTo(370, 400));

        Path path8_5 = new Path(
                new MoveTo(370, 400),
                new LineTo(330, 400));

        Path path8_5_1 = new Path(
                new MoveTo(330, 400),
                new LineTo(270, 400));

        Path path8_6 = new Path(
                new MoveTo(270, 400),
                new LineTo(230, 400));

        Path path8_6_1 = new Path(
                new MoveTo(230, 400),
                new LineTo(170, 400));

        Path path8_7 = new Path(
                new MoveTo(170, 400),
                new LineTo(130, 400));

        Path path8_7_1 = new Path(
                new MoveTo(130, 400),
                new LineTo(100, 400),
                new LineTo(100, 370));

        Path path8_8 = new Path(
                new MoveTo(100, 370),
                new LineTo(100, 300),
                new LineTo(100, 270));

        Path path8_8_0 = new Path(
                new MoveTo(100, 270),
                new LineTo(100, 200),
                new LineTo(130, 200));

        Path path8_9 = new Path(
                new MoveTo(130, 200),
                new LineTo(170, 200));

        Image ghostleft = new Image("/img/ezgif.com-rotate (1).gif");
        Image ghostup = new Image("/img/blackghost_1_1_04032.gif");
        Image ghostdown = new Image("/img/blackghost_2_03942.gif");
        ImagePattern imagePatternLeft = new ImagePattern(ghostleft);
        ImagePattern imagePatternUp = new ImagePattern(ghostup);
        ImagePattern imagePatternDown = new ImagePattern(ghostdown);

        pathTransition = new PathTransition();

        if (interrupt) {
            SimpleDoubleProperty dividedRateProperty = new SimpleDoubleProperty();
            dividedRateProperty.bind(Bindings.divide(this.slider.valueProperty(), 1.0));
            pathTransition.rateProperty().bind(dividedRateProperty);
            pathTransition.setDuration(Duration.seconds(2.8));
            pathTransition.setPath(path);
            pathTransition.setNode(blueCircle);
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

        while (interrupt) {
            if (pathTransition.getStatus() == PathTransition.Status.STOPPED) {
                /*
                 * System.out.println("yellow " + redCircle.getTranslateX() + " "
                 * + redCircle.getTranslateY());
                 */
                switch (blueCircle.getTranslateX() + " " + blueCircle.getTranslateY()) {
                    case 170.0 + " " + 200.0:
                        if (case001 == -1 && interrupt) {
                            case10 = -1;
                            pathTransition.stop();

                            if (normal) {
                                blueCircle.setFill(imagePatternRight);
                            }

                            try {
                                p.semaphore200_200__300_200.acquire();
                                last[46] = p.semaphore200_200__300_200;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case001 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 270.0 + " " + 200.0:
                        if (case1 == -1 && interrupt) {
                            case001 = -1;
                            pathTransition.stop();

                            while (interrupt) {
                                if (p.semaphore400_200__300_200.tryAcquire()) {
                                    last[22] = p.semaphore400_200__300_200;
                                    if (p.semaphore_inter_270_200__330_200.tryAcquire()) {
                                        last[24] = p.semaphore_inter_270_200__330_200;
                                        if (p.semaphore500_200__400_200.tryAcquire()) {
                                            last[44] = p.semaphore500_200__400_200;
                                            break;
                                        } else {
                                            p.semaphore400_200__300_200.release();
                                            last[22] = null;
                                            p.semaphore_inter_270_200__330_200.release();
                                            last[24] = null;
                                        }
                                    } else {
                                        p.semaphore400_200__300_200.release();
                                        last[22] = null;
                                    }
                                } else {
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {

                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }
                            case1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path8_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 200.0:
                        if (case1_0 == -1 && interrupt) {
                            case1 = -1;
                            pathTransition.stop();
                            p.semaphore_inter_270_200__330_200.release();
                            last[24] = null;
                            p.semaphore200_200__300_200.release();
                            last[46] = null;

                            case1_0 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path8_00);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 430.0 + " " + 200.0:
                        if (case2 == -1 && interrupt) {
                            case1_0 = -1;
                            pathTransition.stop();

                            p.semaphore400_200__300_200.release();
                            last[22] = null;

                            case2 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 200.0:
                        if (case2_01 == -1 && interrupt) {
                            case2 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore_inter_470_200__530_200.acquire();
                                last[34] = p.semaphore_inter_470_200__530_200;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case2_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path8_1_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 200.0:
                        if (case2_02 == -1 && interrupt) {
                            case2_01 = -1;
                            pathTransition.stop();
                            p.semaphore_inter_470_200__530_200.release();
                            last[34] = null;
                            p.semaphore500_200__400_200.release();
                            last[44] = null;

                            case2_02 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8_1_2);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 570.0 + " " + 200.0:
                        if (case3 == -1 && interrupt) {
                            case2_02 = -1;

                            if (normal) {
                                blueCircle.setFill(imagePatternDown);
                            }

                            pathTransition.stop();

                            while (interrupt) {
                                if (p.semaphore600_400__600_200.tryAcquire()) {
                                    last[16] = p.semaphore600_400__600_200;
                                    if (p.semaphore600_300_600_400.tryAcquire()) {
                                        last[17] = p.semaphore600_300_600_400;
                                        if (p.semaphore600_300__600_200.tryAcquire()) {
                                            last[18] = p.semaphore600_300__600_200;
                                            break;
                                        } else {
                                            p.semaphore600_400__600_200.release();
                                            last[16] = null;
                                            p.semaphore600_300_600_400.release();
                                            last[17] = null;
                                        }
                                    } else {
                                        p.semaphore600_400__600_200.release();
                                        last[16] = null;
                                    }
                                } else {
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {

                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }

                            case3 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(6.4));
                                    pathTransition.setPath(path8_2);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 330.0:
                        if (case4_0 == -1 && interrupt) {
                            case3 = -1;
                            pathTransition.stop();

                            p.semaphore600_300__600_200.release();
                            last[18] = null;

                            case4_0 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8_2_00);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 370.0:
                        if (case4 == -1 && interrupt) {
                            case4_0 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore500_400__600_400.acquire();
                                last[37] = p.semaphore500_400__600_400;

                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case4 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path8_2_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 570.0 + " " + 400.0:
                        if (case41 == -1 && interrupt) {
                            case4 = -1;

                            if (normal) {
                                blueCircle.setFill(imagePatternLeft);
                            }

                            pathTransition.stop();

                            p.semaphore600_400__600_200.release();
                            last[16] = null;
                            p.semaphore600_300_600_400.release();
                            last[17] = null;

                            case41 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8_3);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 400.0:
                        if (case5_1 == -1 && interrupt) {
                            case41 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore400_400__500_400.acquire();
                                last[11] = p.semaphore400_400__500_400;
                                p.semaphore_inter_470_400__530_400.acquire();
                                last[13] = p.semaphore_inter_470_400__530_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }
                            case5_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path8_3_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 400.0:
                        if (case5 == -1 && interrupt) {
                            case5_1 = -1;
                            pathTransition.stop();

                            p.semaphore500_400__600_400.release();
                            last[37] = null;
                            p.semaphore_inter_470_400__530_400.release();
                            last[13] = null;

                            case5 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8_4);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 430.0 + " " + 400.0:
                        if (case6_1 == -1 && interrupt) {
                            case5 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore300_400__400_400.acquire();
                                last[38] = p.semaphore300_400__400_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case6_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path8_4_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 370.0 + " " + 400.0:
                        if (case6 == -1 && interrupt) {
                            case6_1 = -1;
                            pathTransition.stop();

                            p.semaphore400_400__500_400.release();
                            last[11] = null;

                            case6 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8_5);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 400.0:
                        if (case7_1 == -1 && interrupt) {
                            case6 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore200_400__300_400.acquire();
                                last[5] = p.semaphore200_400__300_400;
                                p.semaphore_inter_270_400__330_400.acquire();
                                last[9] = p.semaphore_inter_270_400__330_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case7_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path8_5_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 270.0 + " " + 400.0:
                        if (case7 == -1 && interrupt) {
                            case7_1 = -1;
                            pathTransition.stop();

                            p.semaphore300_400__400_400.release();
                            last[38] = null;
                            p.semaphore_inter_270_400__330_400.release();
                            last[9] = null;

                            case7 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8_6);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 230.0 + " " + 400.0:
                        if (case8_1 == -1 && interrupt) {
                            case7 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore100_400__200_400.acquire();
                                last[39] = p.semaphore100_400__200_400;
                                p.semaphore_inter_200_370__200_430.acquire();
                                last[6] = p.semaphore_inter_200_370__200_430;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case8_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path8_6_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 170.0 + " " + 400.0:
                        if (case8 == -1 && interrupt) {
                            case8_1 = -1;
                            pathTransition.stop();

                            p.semaphore200_400__300_400.release();
                            last[5] = null;
                            p.semaphore_inter_200_370__200_430.release();
                            last[6] = null;

                            case8 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8_7);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 130.0 + " " + 400.0:
                        if (case9_1 == -1 && interrupt) {
                            case8 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore100_200__100_400.acquire();
                                last[1] = p.semaphore100_200__100_400;
                                p.semaphore100_300__100_400.acquire();
                                last[2] = p.semaphore100_300__100_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case9_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path8_7_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 370.0:
                        if (case9 == -1 && interrupt) {
                            case9_1 = -1;

                            if (normal) {
                                blueCircle.setFill(imagePatternUp);
                            }

                            pathTransition.stop();

                            p.semaphore100_400__200_400.release();
                            last[39] = null;

                            case9 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path8_8);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 270.0:
                        if (case10_0 == -1 && interrupt) {
                            case9 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore100_300__100_400.release();
                                last[2] = null;
                            } catch (Exception e) {

                                Thread.currentThread().interrupt();
                            }

                            case10_0 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path8_8_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 130.0 + " " + 200.0:
                        if (case10 == -1 && interrupt) {
                            case10_0 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore100_200__100_400.release();
                                last[1] = null;
                            } catch (Exception e) {

                                Thread.currentThread().interrupt();
                            }

                            case10 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8_9);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    default:
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
        case2_1 = 0;
        case3 = 0;
        case4 = 0;
        case41 = 0;
        case5 = 0;
        case6 = 0;
        case7 = 0;
        case8 = 0;
        case9 = 0;
        case10 = 0;
        case5_1 = 0;
        case6_1 = 0;
        case7_1 = 0;
        case8_1 = 0;
        case9_1 = 0;
        case1_0 = 0;
        case2_01 = 0;
        case2_02 = 0;
        case3_0 = 0;
        case001 = 0;
        case01 = 0;
        case10_0 = 0;
        case4_0 = 0;

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
