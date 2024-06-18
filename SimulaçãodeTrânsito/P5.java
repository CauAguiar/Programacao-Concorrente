/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: P5.java
* Funcao...........: Thread que controla o movimento do fantasma vermelho, caminho 5.
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

public class P5 extends Thread {
    private Rectangle redCircle;
    private volatile boolean interrupt = true; // variavel que define se a thread esta interrompida ou nao
    private volatile boolean pause = false; // variavel que define se a thread esta pausada ou nao

    ThreadInfo p; // objeto que contem as informacoes das threads
    public boolean normal = true; // variavel que define se o pacman esta normal ou nao
    // variaveis que definem se a thread esta em um determinado ponto do caminho
    int case1 = -1;
    int case2 = -1;
    int case3 = -1;
    int case4 = -1;
    int case5 = -1;
    int case6 = -1;
    int case7 = -1;
    int case8 = -1;
    int case2_1 = -1;
    int case3_1 = -1;
    int case4_1 = -1;
    int case5_1 = -1;
    int case6_1 = -1;
    int case7_1 = -1;
    int case5_01 = -1;
    int case6_01 = -1;
    int case9 = -1;
    int case10 = -1;
    int case11 = -1;
    int case1_1 = -1;
    int case7_0 = -1;
    int case3_01 = -1;
    int case7_00 = -1;
    int case6_001 = -1;

    Semaphore[] last = new Semaphore[8]; // vetor de semaforos que guarda os semaforos que a thread esta usando

    public PathTransition pathTransition; // variavel que controla a animacao do pacman

    Slider slider; // slider que controla a velocidade do pacman

    public P5(int i, Rectangle redCircle2, ThreadInfo threadInfo, Slider sliderP5) {
        this.redCircle = redCircle2;
        this.slider = sliderP5;

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

        Paint imagePatternRight = redCircle.getFill();

        Path path = new Path(
                new MoveTo(600, 100),
                new LineTo(530, 100));

        Path path4_0 = new Path(
                new MoveTo(370, 100),
                new LineTo(330, 100));

        Path path4_0_1 = new Path(
                new MoveTo(330, 100),
                new LineTo(270, 100));

        Path path4_1 = new Path(
                new MoveTo(270, 100),
                new LineTo(200, 100),
                new LineTo(100, 100),
                new LineTo(100, 170));

        Path path4_1_1 = new Path(
                new MoveTo(100, 170),
                new LineTo(100, 230));

        Path path4_2 = new Path(
                new MoveTo(100, 230),
                new LineTo(100, 270));

        Path path4_2_0 = new Path(
                new MoveTo(100, 270),
                new LineTo(100, 370));

        Path path4_2_1 = new Path(
                new MoveTo(100, 370),
                new LineTo(100, 430));

        Path path4_3 = new Path(
                new MoveTo(100, 430),
                new LineTo(100, 500),
                new LineTo(100, 600),
                new LineTo(170, 600));

        Path path4_3_1 = new Path(
                new MoveTo(170, 600),
                new LineTo(230, 600));

        Path path4_3_2 = new Path(
                new MoveTo(230, 600),
                new LineTo(270, 600));

        Path path4_3_3 = new Path(
                new MoveTo(270, 600),
                new LineTo(330, 600));

        Path path4_4 = new Path(
                new MoveTo(330, 600),
                new LineTo(430, 600));

        Path path4_4_01 = new Path(
                new MoveTo(430, 600),
                new LineTo(470, 600));

        Path path4_4_1 = new Path(
                new MoveTo(470, 600),
                new LineTo(530, 600));

        Path path4_5 = new Path(
                new MoveTo(530, 600),
                new LineTo(600, 600),
                new LineTo(600, 500),
                new LineTo(600, 430));

        Path path4_5_1 = new Path(
                new MoveTo(600, 430),
                new LineTo(600, 370));

        Path path4_6 = new Path(
                new MoveTo(600, 370),
                new LineTo(600, 330));

        Path path4_6_00 = new Path(
                new MoveTo(600, 330),
                new LineTo(600, 270));

        Path path4_6_0 = new Path(
                new MoveTo(600, 270),
                new LineTo(600, 230));

        Path path4_6_1 = new Path(
                new MoveTo(600, 230),
                new LineTo(600, 170));

        Path path4_7 = new Path(
                new MoveTo(600, 170),
                new LineTo(600, 100),
                new LineTo(530, 100));

        Path path4_8 = new Path(
                new MoveTo(530, 100),
                new LineTo(470, 100));

        Path path4_9 = new Path(
                new MoveTo(470, 100),
                new LineTo(430, 100));

        Path path4_10 = new Path(
                new MoveTo(430, 100),
                new LineTo(370, 100));

        Image ghostleft = new Image("/img/ezgif.com-rotate (6).gif");
        Image ghostup = new Image("/img/blackghost_1_1_04070.gif");
        Image ghostdown = new Image("/img/blackghost_2_03995.gif");
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
            pathTransition.setNode(redCircle);
            if (normal) {
                redCircle.setFill(imagePatternLeft);
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
                p.semaphore600_200__500_100.acquire();
                last[0] = p.semaphore600_200__500_100;
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }

        while (interrupt) {
            if (pathTransition.getStatus() == PathTransition.Status.STOPPED) {
                switch (redCircle.getTranslateX() + " " + redCircle.getTranslateY()) {
                    case 370.0 + " " + 100.0:
                        if (case1 == -1 && interrupt) {
                            case11 = -1;
                            pathTransition.stop();

                            p.semaphore500_100__400_100.release();
                            last[2] = null;
                            case1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path4_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 100.0:
                        if (case1_1 == -1 && interrupt) {
                            case1 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore300_100__100_200.acquire();
                                last[2] = p.semaphore300_100__100_200;
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                            case1_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_0_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 270.0 + " " + 100.0:
                        if (case2 == -1 && interrupt) {
                            case1_1 = -1;
                            pathTransition.stop();

                            p.semaphore300_100_500_100.release();
                            last[1] = null;
                            p.semaphore300_100__400_100.release();
                            last[0] = null;

                            case2 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(5.6));
                                    pathTransition.setPath(path4_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 170.0:
                        if (case2_1 == -1 && interrupt) {
                            case2 = -1;

                            if (normal) {
                                redCircle.setFill(imagePatternDown);
                            }

                            pathTransition.stop();
                            try {
                                p.semaphore100_200__100_400.acquire();
                                last[0] = p.semaphore100_200__100_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case2_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_1_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 230.0:
                        if (case3 == -1 && interrupt) {
                            case2_1 = -1;
                            pathTransition.stop();

                            p.semaphore300_100__100_200.release();
                            last[2] = null;

                            case3 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path4_2);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 270.0:
                        if (case3_01 == -1 && interrupt) {
                            case3 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore100_300__100_400.acquire();
                                last[2] = p.semaphore100_300__100_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case3_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path4_2_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 370.0:
                        if (case3_1 == -1 && interrupt) {
                            case3_01 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore100_400__200_600.acquire();
                                last[3] = p.semaphore100_400__200_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case3_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_2_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 430.0:
                        if (case4 == -1 && interrupt) {
                            case3_1 = -1;
                            pathTransition.stop();

                            p.semaphore100_300__100_400.release();
                            p.semaphore100_200__100_400.release();
                            last[2] = null;
                            last[0] = null;

                            case4 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(9.6));
                                    pathTransition.setPath(path4_3);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 170.0 + " " + 600.0:
                        if (case4_1 == -1 && interrupt) {
                            case4 = -1;

                            if (normal) {
                                redCircle.setFill(imagePatternRight);
                            }

                            pathTransition.stop();
                            try {
                                p.semaphore200_600__300_600.acquire();
                                last[0] = p.semaphore200_600__300_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case4_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_3_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 230.0 + " " + 600.0:
                        if (case5 == -1 && interrupt) {
                            case4_1 = -1;
                            pathTransition.stop();

                            p.semaphore100_400__200_600.release();
                            last[3] = null;

                            case5 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path4_3_2);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 270.0 + " " + 600.0:
                        if (case5_1 == -1 && interrupt) {
                            case5 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore300_600__500_600.acquire();
                                p.semaphore300_600__400_600.acquire();
                                last[3] = p.semaphore300_600__500_600;
                                last[4] = p.semaphore300_600__400_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case5_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_3_3);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 600.0:
                        if (case5_01 == -1 && interrupt) {
                            case5_1 = -1;
                            pathTransition.stop();
                            p.semaphore200_600__300_600.release();
                            last[0] = null;
                            case5_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path4_4);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 430.0 + " " + 600.0:
                        if (case6_001 == -1 && interrupt) {
                            case5_01 = -1;
                            pathTransition.stop();
                            p.semaphore300_600__400_600.release();
                            last[4] = null;
                            case6_001 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path4_4_01);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 600.0:
                        if (case6_01 == -1 && interrupt) {
                            case6_001 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore500_600__600_400.acquire();
                                last[4] = p.semaphore500_600__600_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case6_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_4_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 600.0:
                        if (case6 == -1 && interrupt) {
                            case6_01 = -1;
                            pathTransition.stop();
                            if (normal) {
                                redCircle.setFill(imagePatternUp);
                            }

                            p.semaphore300_600__500_600.release();
                            last[3] = null;

                            case6 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(9.6));
                                    pathTransition.setPath(path4_5);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 430.0:
                        if (case6_1 == -1 && interrupt) {
                            case6 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore600_400__600_200.acquire();
                                p.semaphore600_300_600_400.acquire();
                                last[3] = p.semaphore600_400__600_200;
                                last[5] = p.semaphore600_300_600_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case6_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_5_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 370.0:
                        if (case7 == -1 && interrupt) {
                            case6_1 = -1;
                            pathTransition.stop();

                            p.semaphore500_600__600_400.release();
                            last[4] = null;

                            case7 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path4_6);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 330.0:
                        if (case7_00 == -1 && interrupt) {
                            case7 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore600_300__600_200.acquire();
                                last[4] = p.semaphore600_300__600_200;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case7_00 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_6_00);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 270.0:
                        if (case7_0 == -1 && interrupt) {
                            case7_00 = -1;
                            pathTransition.stop();

                            p.semaphore600_300_600_400.release();
                            last[5] = null;

                            case7_0 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path4_6_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 230.0:
                        if (case7_1 == -1 && interrupt) {
                            case7_0 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore600_200__500_100.acquire();
                                last[5] = p.semaphore600_200__500_100;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case7_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_6_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 170.0:
                        if (case8 == -1 && interrupt) {
                            case7_1 = -1;
                            pathTransition.stop();

                            p.semaphore600_300__600_200.release();
                            last[4] = null;
                            p.semaphore600_400__600_200.release();
                            last[3] = null;

                            case8 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(5.6));
                                    pathTransition.setPath(path4_7);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 100.0:
                        if (case9 == -1 && interrupt) {
                            case8 = -1;
                            pathTransition.stop();

                            if (normal) {
                                redCircle.setFill(imagePatternLeft);
                            }

                            try {
                                p.semaphore300_100_500_100.acquire();
                                p.semaphore500_100__400_100.acquire();
                                last[1] = p.semaphore300_100_500_100;
                                last[2] = p.semaphore500_100__400_100;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case9 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_8);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 100.0:
                        if (case10 == -1 && interrupt) {
                            case9 = -1;
                            pathTransition.stop();

                            p.semaphore600_200__500_100.release();
                            last[0] = null;

                            case10 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path4_9);
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
                            pathTransition.stop();

                            try {
                                p.semaphore300_100__400_100.acquire();
                                last[0] = p.semaphore300_100__400_100;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case11 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path4_10);
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
        case2_1 = 0;
        case3_1 = 0;
        case4_1 = 0;
        case5_1 = 0;
        case6_1 = 0;
        case7_1 = 0;
        case5_01 = 0;
        case6_01 = 0;
        case9 = 0;
        case10 = 0;
        case11 = 0;
        case1_1 = 0;
        case7_0 = 0;
        case3_01 = 0;
        case7_00 = 0;
        case6_001 = 0;

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
