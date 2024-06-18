/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: P10.java
* Funcao...........: Thread que controla o movimento do fantasma rosa, caminho 10
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

public class P10 extends Thread {
    private volatile boolean interrupt = true; // variavel para interromper a thread
    private Rectangle pinkCircle; // circulo do pacman
    private volatile boolean pause = false; // variavel para pausar a thread

    Semaphore[] last = new Semaphore[48]; // vetor de semaforos

    ThreadInfo p; // classe com informacoes das threads
 
    public boolean normal = true; // variavel para verificar se o pacman esta normal ou nao
    // variaveis para verificar se o fantasma esta em um determinado ponto do caminho
    int case1 = -1;
    int case2 = -1;
    int case3 = -1;
    int case4 = -1;
    int case5 = -1;
    int case6 = -1;
    int case7 = -1;
    int case8 = -1;
    int case0_1 = -1;
    int case1_1 = -1;
    int case2_1 = -1;
    int case3_1 = -1;
    int case4_1 = -1;
    int case5_1 = -1;
    int case6_1 = -1;
    int case7_1 = -1;
    int case7_01 = -1;
    int case7_02 = -1;
    int case7_001 = -1;
    public PathTransition pathTransition; // variavel para controlar a animacao do pacman
    public Slider slider; // slider para controlar a velocidade do pacman
 
    public P10(int i, Rectangle pinkCircle2, ThreadInfo threadInfo, Slider sliderP10) {

        this.pinkCircle = pinkCircle2;
        this.slider = sliderP10;

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
        Paint imagePatternRight = pinkCircle.getFill();

        Path path = new Path(
                new MoveTo(600, 600),
                new LineTo(600, 500),
                new LineTo(600, 430));

        Path path_1 = new Path(
                new MoveTo(600, 430),
                new LineTo(600, 400),
                new LineTo(570, 400));

        Path path10_0 = new Path(
                new MoveTo(570, 400),
                new LineTo(530, 400));

        Path path10_0_1 = new Path(
                new MoveTo(530, 400),
                new LineTo(470, 400));

        Path path10_1 = new Path(
                new MoveTo(470, 400),
                new LineTo(430, 400));

        Path path10_1_1 = new Path(
                new MoveTo(430, 400),
                new LineTo(370, 400));

        Path path10_2 = new Path(
                new MoveTo(370, 400),
                new LineTo(330, 400));

        Path path10_2_1 = new Path(
                new MoveTo(330, 400),
                new LineTo(270, 400));

        Path path10_3 = new Path(
                new MoveTo(270, 400),
                new LineTo(230, 400));

        Path path10_3_1 = new Path(
                new MoveTo(230, 400),
                new LineTo(170, 400));

        Path path10_4 = new Path(
                new MoveTo(170, 400),
                new LineTo(130, 400));

        Path path10_4_1 = new Path(
                new MoveTo(130, 400),
                new LineTo(100, 400),
                new LineTo(100, 430));

        Path path10_5 = new Path(
                new MoveTo(100, 430),
                new LineTo(100, 500),
                new LineTo(100, 600),
                new LineTo(170, 600));

        Path path10_5_1 = new Path(
                new MoveTo(170, 600),
                new LineTo(230, 600));

        Path path10_5_2 = new Path(
                new MoveTo(230, 600),
                new LineTo(270, 600));

        Path path10_5_3 = new Path(
                new MoveTo(270, 600),
                new LineTo(330, 600));

        Path path10_6 = new Path(
                new MoveTo(330, 600),
                new LineTo(430, 600));

        Path path10_6_01 = new Path(
                new MoveTo(430, 600),
                new LineTo(470, 600));

        Path path10_6_1 = new Path(
                new MoveTo(470, 600),
                new LineTo(530, 600));

        Path path10_7 = new Path(
                new MoveTo(530, 600),
                new LineTo(600, 600),
                new LineTo(600, 500),
                new LineTo(600, 430));

        Image ghostleft = new Image("/img/ezgif.com-rotate (4).gif");
        Image ghostup = new Image("/img/blackghost_1_1_04054.gif");
        Image ghostdown = new Image("/img/blackghost_2_03974.gif");
        ImagePattern imagePatternLeft = new ImagePattern(ghostleft);
        ImagePattern imagePatternUp = new ImagePattern(ghostup);
        ImagePattern imagePatternDown = new ImagePattern(ghostdown);

        pathTransition = new PathTransition();

        if (interrupt) {
            SimpleDoubleProperty dividedRateProperty = new SimpleDoubleProperty();
            dividedRateProperty.bind(Bindings.divide(this.slider.valueProperty(), 1.0));
            pathTransition.rateProperty().bind(dividedRateProperty);
            pathTransition.setDuration(Duration.seconds(6.8));
            pathTransition.setPath(path);
            pathTransition.setNode(pinkCircle);
            if (normal) {
                pinkCircle.setFill(imagePatternUp);
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
                p.semaphore500_600__600_400.acquire();
                last[15] = p.semaphore500_600__600_400;
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }

        while (interrupt) {
            if (pathTransition.getStatus() == PathTransition.Status.STOPPED) {
                /*
                 * System.out.println("yellow " + redCircle.getTranslateX() + " "
                 * + redCircle.getTranslateY());
                 */
                switch (pinkCircle.getTranslateX() + " " + pinkCircle.getTranslateY()) {
                    case 600.0 + " " + 430.0:
                        if (case0_1 == -1 && interrupt) {
                            case8 = -1;

                            pathTransition.stop();
                            try {
                                p.semaphore500_400__600_400.acquire();
                                last[37] = p.semaphore500_400__600_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }
                            case0_1 = 1;
                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });

                        }

                        break;

                    case 570.0 + " " + 400.0:
                        if (case1 == -1 && interrupt) {
                            case0_1 = -1;
                            pathTransition.stop();

                            if (normal) {
                                pinkCircle.setFill(imagePatternLeft);
                            }

                            p.semaphore500_600__600_400.release();
                            last[15] = null;

                            case1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path10_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 400.0:
                        if (case1_1 == -1 && interrupt) {
                            case1 = -1;
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

                            case1_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path10_0_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 400.0:
                        if (case2 == -1 && interrupt) {
                            case1_1 = -1;
                            pathTransition.stop();

                            p.semaphore500_400__600_400.release();
                            last[37] = null;
                            p.semaphore_inter_470_400__530_400.release();
                            last[13] = null;

                            case2 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path10_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 430.0 + " " + 400.0:
                        if (case2_1 == -1 && interrupt) {
                            case2 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore300_400__400_400.acquire();
                                last[38] = p.semaphore300_400__400_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case2_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path10_1_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 370.0 + " " + 400.0:
                        if (case3 == -1 && interrupt) {
                            case2_1 = -1;
                            pathTransition.stop();

                            p.semaphore400_400__500_400.release();
                            last[11] = null;

                            case3 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path10_2);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 400.0:
                        if (case3_1 == -1 && interrupt) {
                            case3 = -1;
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

                            case3_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path10_2_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 270.0 + " " + 400.0:
                        if (case4 == -1 && interrupt) {
                            case3_1 = -1;
                            pathTransition.stop();

                            p.semaphore300_400__400_400.release();
                            last[38] = null;
                            p.semaphore_inter_270_400__330_400.release();
                            last[9] = null;

                            case4 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path10_3);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 230.0 + " " + 400.0:
                        if (case4_1 == -1 && interrupt) {
                            case4 = -1;
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
                            case4_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path10_3_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 170.0 + " " + 400.0:
                        if (case5 == -1 && interrupt) {
                            case4_1 = -1;
                            pathTransition.stop();

                            p.semaphore200_400__300_400.release();
                            last[5] = null;
                            p.semaphore_inter_200_370__200_430.release();
                            last[6] = null;

                            case5 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path10_4);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 130.0 + " " + 400.0:
                        if (case5_1 == -1 && interrupt) {
                            case5 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore100_400__200_600.acquire();
                                last[3] = p.semaphore100_400__200_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case5_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path10_4_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 430.0:
                        if (case6 == -1 && interrupt) {
                            case5_1 = -1;

                            if (normal) {
                                pinkCircle.setFill(imagePatternDown);
                            }

                            pathTransition.stop();

                            p.semaphore100_400__200_400.release();
                            last[39] = null;

                            case6 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(9.6));
                                    pathTransition.setPath(path10_5);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 170.0 + " " + 600.0:
                        if (case6_1 == -1 && interrupt) {
                            case6 = -1;
                            pathTransition.stop();

                            if (normal) {
                                pinkCircle.setFill(imagePatternRight);
                            }

                            try {
                                p.semaphore200_600__300_600.acquire();
                                last[40] = p.semaphore200_600__300_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case6_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path10_5_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 230.0 + " " + 600.0:
                        if (case7 == -1 && interrupt) {
                            case6_1 = -1;
                            pathTransition.stop();

                            p.semaphore100_400__200_600.release();
                            last[3] = null;

                            case7 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path10_5_2);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 270.0 + " " + 600.0:
                        if (case7_01 == -1 && interrupt) {
                            case7 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore300_600__500_600.acquire();
                                last[25] = p.semaphore300_600__500_600;
                                p.semaphore300_600__400_600.acquire();
                                last[29] = p.semaphore300_600__400_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case7_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path10_5_3);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 600.0:
                        if (case7_02 == -1 && interrupt) {
                            case7_01 = -1;
                            pathTransition.stop();

                            p.semaphore200_600__300_600.release();
                            last[40] = null;

                            case7_02 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path10_6);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 430.0 + " " + 600.0:
                        if (case7_001 == -1 && interrupt) {
                            case7_02 = -1;
                            pathTransition.stop();
                            p.semaphore300_600__400_600.release();
                            last[29] = null;

                            case7_001 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path10_6_01);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 600.0:
                        if (case7_1 == -1 && interrupt) {
                            case7_001 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore500_600__600_400.acquire();
                                last[15] = p.semaphore500_600__600_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case7_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path10_6_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 600.0:
                        if (case8 == -1 && interrupt) {
                            case7_1 = -1;

                            if (normal) {
                                pinkCircle.setFill(imagePatternUp);
                            }

                            pathTransition.stop();

                            p.semaphore300_600__500_600.release();
                            last[25] = null;

                            case8 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(9.6));
                                    pathTransition.setPath(path10_7);
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
        case3 = 0;
        case4 = 0;
        case5 = 0;
        case6 = 0;
        case7 = 0;
        case8 = 0;
        case0_1 = 0;
        case1_1 = 0;
        case2_1 = 0;
        case3_1 = 0;
        case4_1 = 0;
        case5_1 = 0;
        case6_1 = 0;
        case7_1 = 0;
        case7_01 = 0;
        case7_02 = 0;
        case7_001 = 0;

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
