/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: P18.java
* Funcao...........: Thread que controla o movimento do fantasma laranja, caminho 18.
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

public class P18 extends Thread {

    private Rectangle orangeCircle; // Fantasma laranja
    private volatile boolean pause = false; // Variavel para pausar a thread

    private volatile boolean interrupt = true; // Variavel para interromper a thread
    Semaphore[] last = new Semaphore[48]; // Vetor de semaforos
    ThreadInfo p; // Objeto da classe ThreadInfo
    public boolean normal = true; // Variavel para verificar se o pacman esta normal ou nao
    // Variaveis para verificar se o fantasma esta em determinada posicao
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
    int case17_1 = -1;
    int case14_0 = -1;

    public PathTransition pathTransition; // Objeto da classe PathTransition
    public Slider slider; // Slider para controlar a velocidade do jogo

    public P18(int i, Rectangle orangeCircle, ThreadInfo threadInfo, Slider sliderP18) {

        this.orangeCircle = orangeCircle;

        this.slider = sliderP18;
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
        Paint imagePatternRight = orangeCircle.getFill();

        Path path1 = new Path(
                new MoveTo(600, 300),
                new LineTo(570, 300));

        Path path2 = new Path(
                new MoveTo(570, 300),
                new LineTo(530, 300));

        Path path3 = new Path(
                new MoveTo(530, 300),
                new LineTo(470, 300));

        Path path4 = new Path(
                new MoveTo(470, 300),
                new LineTo(430, 300));

        Path path5 = new Path(
                new MoveTo(430, 300),
                new LineTo(330, 300));

        Path path7 = new Path(
                new MoveTo(330, 300),
                new LineTo(300, 300),
                new LineTo(300, 330));

        Path path8 = new Path(
                new MoveTo(300, 330),
                new LineTo(300, 370));

        Path path9 = new Path(
                new MoveTo(300, 370),
                new LineTo(300, 430));

        Path path10 = new Path(
                new MoveTo(300, 430),
                new LineTo(300, 470));

        Path path11 = new Path(
                new MoveTo(300, 470),
                new LineTo(300, 570));

        Path path13 = new Path(
                new MoveTo(300, 570),
                new LineTo(300, 600),
                new LineTo(330, 600));

        Path path14 = new Path(
                new MoveTo(330, 600),
                new LineTo(430, 600));

        Path path14_0 = new Path(
                new MoveTo(430, 600),
                new LineTo(470, 600));

        Path path15 = new Path(
                new MoveTo(470, 600),
                new LineTo(530, 600));

        Path path16 = new Path(
                new MoveTo(530, 600),
                new LineTo(600, 600),
                new LineTo(600, 430));

        Path path17 = new Path(
                new MoveTo(600, 430),
                new LineTo(600, 370));

        Path path17_1 = new Path(
                new MoveTo(600, 370),
                new LineTo(600, 330));

        Path path18 = new Path(
                new MoveTo(600, 330),
                new LineTo(600, 300),
                new LineTo(570, 300));

        Image ghostleft = new Image("/img/ezgif.com-rotate (3).gif");
        Image ghostup = new Image("/img/blackghost_1_1_04046.gif");
        Image ghostdown = new Image("/img/blackghost_2_03966.gif");
        ImagePattern imagePatternLeft = new ImagePattern(ghostleft);
        ImagePattern imagePatternUp = new ImagePattern(ghostup);
        ImagePattern imagePatternDown = new ImagePattern(ghostdown);

        pathTransition = new PathTransition();

        if (interrupt) {
            SimpleDoubleProperty dividedRateProperty = new SimpleDoubleProperty();
            dividedRateProperty.bind(Bindings.divide(this.slider.valueProperty(), 1.0));
            pathTransition.rateProperty().bind(dividedRateProperty);
            pathTransition.setDuration(Duration.seconds(1.2));
            pathTransition.setPath(path1);
            pathTransition.setNode(orangeCircle);
            if (normal) {
                orangeCircle.setFill(imagePatternLeft);
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
                p.semaphore600_300_600_400.acquire();
                last[17] = p.semaphore600_300_600_400;
                p.semaphore500_300__600_300.acquire();
                last[42] = p.semaphore500_300__600_300;
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }

        while (interrupt) {

            if (pathTransition.getStatus() == PathTransition.Status.STOPPED) {

                switch (orangeCircle.getTranslateX() + " " + orangeCircle.getTranslateY()) {
                    case 570.0 + " " + 300.0:
                        if (case1 == -1 && interrupt) {
                            case17_1 = -1;
                            pathTransition.stop();

                            if (normal) {
                                orangeCircle.setFill(imagePatternLeft);
                            }

                            p.semaphore600_300_600_400.release();
                            last[17] = null;

                            case1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path2);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 300.0:
                        if (case2 == -1 && interrupt) {
                            case1 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore_inter_470_300__530_300.acquire();
                                last[35] = p.semaphore_inter_470_300__530_300;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case2 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 300.0:
                        if (case3 == -1 && interrupt) {
                            case2 = -1;
                            pathTransition.stop();

                            p.semaphore_inter_470_300__530_300.release();
                            last[35] = null;
                            p.semaphore500_300__600_300.release();
                            last[42] = null;

                            case3 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path4);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 430.0 + " " + 300.0:
                        if (case4 == -1 && interrupt) {
                            case3 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore_new_300_400__400_300.acquire();
                                last[7] = p.semaphore_new_300_400__400_300;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case4 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path5);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 300.0:
                        if (case6 == -1 && interrupt) {
                            case4 = -1;
                            pathTransition.stop();

                            while (interrupt) {
                                if (p.semaphore_new_300_300__300_600__500_600.tryAcquire()) {
                                    last[26] = p.semaphore_new_300_300__300_600__500_600;
                                    if (p.semaphore_inter_270_300__330_300.tryAcquire()) {
                                        last[10] = p.semaphore_inter_270_300__330_300;
                                        break;
                                    } else {
                                        p.semaphore_new_300_300__300_600__500_600.release();
                                        last[26] = null;
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
                                    pathTransition.setPath(path7);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 330.0:
                        if (case7 == -1 && interrupt) {
                            case6 = -1;
                            if (normal) {
                                orangeCircle.setFill(imagePatternDown);
                            }
                            pathTransition.stop();

                            p.semaphore_inter_270_300__330_300.release();
                            last[10] = null;

                            case7 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path8);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 370.0:
                        if (case8 == -1 && interrupt) {
                            case7 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore_inter_270_400__330_400.acquire();
                                last[9] = p.semaphore_inter_270_400__330_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case8 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path9);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 430.0:
                        if (case9 == -1 && interrupt) {
                            case8 = -1;
                            pathTransition.stop();

                            p.semaphore_inter_270_400__330_400.release();
                            last[9] = null;
                            p.semaphore_new_300_400__400_300.release();
                            last[7] = null;

                            case9 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path10);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 470.0:
                        if (case10 == -1 && interrupt) {
                            case9 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore300_500__300_600__400_600.acquire();
                                last[27] = p.semaphore300_500__300_600__400_600;
                                p.semaphore300_600__300_500.acquire();
                                last[28] = p.semaphore300_600__300_500;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case10 = 1;

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

                    case 300.0 + " " + 570.0:
                        if (case12 == -1 && interrupt) {
                            case10 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore300_600__500_600.acquire();
                                last[25] = p.semaphore300_600__500_600;
                                p.semaphore300_600__400_600.acquire();
                                last[29] = p.semaphore300_600__400_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case12 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path13);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 600.0:
                        if (case13 == -1 && interrupt) {
                            case12 = -1;
                            pathTransition.stop();

                            if (normal) {
                                orangeCircle.setFill(imagePatternRight);
                            }

                            p.semaphore300_600__300_500.release();
                            last[28] = null;

                            case13 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path14);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 430.0 + " " + 600.0:
                        if (case14_0 == -1 && interrupt) {
                            case13 = -1;
                            pathTransition.stop();

                            p.semaphore300_500__300_600__400_600.release();
                            last[27] = null;
                            p.semaphore300_600__400_600.release();
                            last[29] = null;

                            case14_0 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path14_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 600.0:
                        if (case14 == -1 && interrupt) {
                            case14_0 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore500_600__600_400.acquire();
                                last[15] = p.semaphore500_600__600_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case14 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path15);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 600.0:
                        if (case15 == -1 && interrupt) {
                            case14 = -1;
                            if (normal) {
                                orangeCircle.setFill(imagePatternUp);
                            }

                            pathTransition.stop();

                            p.semaphore300_600__500_600.release();
                            last[25] = null;

                            p.semaphore_new_300_300__300_600__500_600.release();
                            last[26] = null;

                            case15 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(9.6));
                                    pathTransition.setPath(path16);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 430.0:
                        if (case16 == -1 && interrupt) {
                            case15 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore600_300_600_400.acquire();
                                last[17] = p.semaphore600_300_600_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case16 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path17);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 370.0:
                        if (case17 == -1 && interrupt) {
                            case16 = -1;
                            pathTransition.stop();

                            p.semaphore500_600__600_400.release();
                            last[15] = null;

                            case17 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path17_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 330.0:
                        if (case17_1 == -1 && interrupt) {
                            case17 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore500_300__600_300.acquire();
                                last[42] = p.semaphore500_300__600_300;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case17_1 = 1;

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
        case17_1 = 0;
        case14_0 = 0;

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
