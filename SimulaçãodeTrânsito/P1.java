/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: P1.java
* Funcao...........: Classe que extende Thread e implementa o movimento do
*                    fantasma preto, caminho 1.
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

public class P1 extends Thread {

    private Rectangle blackCircle; // circulo preto
    private Slider slider; // slider para controle de velocidade
    private volatile boolean pause = false; // variavel para pausar o movimento

    Semaphore[] last = new Semaphore[48]; // vetor de semaforos para controle de fluxo

    ThreadInfo p; // objeto da classe ThreadInfo
    public boolean normal = true; // variavel para controle de imagem
    // variaveis para controle de fluxo
    int case1 = -1;
    int case2 = -1;
    int case21 = -1;
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
    int case0_1 = -1;
    int case8_1 = -1;
    int case9_1 = -1;
    int case7_01 = -1;
    int case4_1 = -1;
    int case6_1 = -1;
    int case7_1 = -1;
    int case11_01 = -1;
    int case10_01 = -1;
    int case9_0 = -1;
    int case6_10 = -1;
    int case6_010 = -1;
    int case13_00 = -1;
    int case2_0 = -1;
    int case6_0010 = -1;
    int case4_01 = -1;
    int case4_0 = -1;
    int case9_00 = -1;
    int case6_01 = -1;

    private volatile boolean interrupt = true; // variavel para controle de fluxo

    public PathTransition pathTransition; // variavel de animacao

    public P1(int i, Rectangle blackCircle2, ThreadInfo threadInfo, Slider slider) {

        this.slider = slider;
        this.blackCircle = blackCircle2;

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
        Paint imagePatternRight = blackCircle.getFill();

        Path path = new Path(
                new MoveTo(100, 100),
                new LineTo(100, 170));

        Path path_1 = new Path(
                new MoveTo(100, 170),
                new LineTo(100, 230));

        Path path_01 = new Path(
                new MoveTo(100, 270),
                new LineTo(100, 370));

        Path path3_0 = new Path(
                new MoveTo(100, 230),
                new LineTo(100, 270));

        Path path3_1 = new Path(
                new MoveTo(100, 370),
                new LineTo(100, 430));

        Path path3_1_1 = new Path(
                new MoveTo(100, 430),
                new LineTo(100, 500),
                new LineTo(100, 600),
                new LineTo(200, 600),
                new LineTo(200, 570));

        Path path3_2 = new Path(
                new MoveTo(200, 570),
                new LineTo(200, 530));

        Path path3_2_0 = new Path(
                new MoveTo(200, 530),
                new LineTo(200, 430));

        Path path3_3 = new Path(
                new MoveTo(200, 430),
                new LineTo(200, 400),
                new LineTo(230, 400));

        Path path3_3_0 = new Path(
                new MoveTo(230, 400),
                new LineTo(270, 400));

        Path path3_3_1 = new Path(
                new MoveTo(270, 400),
                new LineTo(300, 400),
                new LineTo(300, 370));

        Path path3_4 = new Path(
                new MoveTo(300, 370),
                new LineTo(300, 330));

        Path path3_4_0 = new Path(
                new MoveTo(300, 330),
                new LineTo(300, 300),
                new LineTo(330, 300));

        Path path3_4_1 = new Path(
                new MoveTo(330, 300),
                new LineTo(400, 300),
                new LineTo(400, 330));

        Path path3_4_10 = new Path(
                new MoveTo(400, 330),
                new LineTo(400, 370));

        Path path3_5 = new Path(
                new MoveTo(400, 370),
                new LineTo(400, 400),
                new LineTo(470, 400));

        Path path3_5_1 = new Path(
                new MoveTo(470, 400),
                new LineTo(500, 400),
                new LineTo(500, 430));

        Path path3_6 = new Path(
                new MoveTo(500, 430),
                new LineTo(500, 470));

        Path path3_6_00 = new Path(
                new MoveTo(500, 470),
                new LineTo(500, 530));

        Path path3_6_0 = new Path(
                new MoveTo(500, 530),
                new LineTo(500, 570));

        Path path3_7 = new Path(
                new MoveTo(500, 570),
                new LineTo(500, 600),
                new LineTo(530, 600));

        Path path3_7_01 = new Path(
                new MoveTo(530, 600),
                new LineTo(600, 600),
                new LineTo(600, 500),
                new LineTo(600, 430));

        Path path3_7_1 = new Path(
                new MoveTo(600, 430),
                new LineTo(600, 370));

        Path path3_8 = new Path(
                new MoveTo(600, 370),
                new LineTo(600, 330));

        Path path3_8_00 = new Path(
                new MoveTo(600, 330),
                new LineTo(600, 270));

        Path path3_8_0 = new Path(
                new MoveTo(600, 270),
                new LineTo(600, 230));

        Path path3_8_1 = new Path(
                new MoveTo(600, 230),
                new LineTo(600, 170));

        Path path3_9 = new Path(
                new MoveTo(600, 170),
                new LineTo(600, 100),
                new LineTo(530, 100));

        Path path3_9_1 = new Path(
                new MoveTo(530, 100),
                new LineTo(470, 100));

        Path path3_10 = new Path(
                new MoveTo(470, 100),
                new LineTo(400, 100),
                new LineTo(400, 130));

        Path path3_10_1 = new Path(
                new MoveTo(400, 130),
                new LineTo(400, 170));

        Path path3_11 = new Path(
                new MoveTo(400, 170),
                new LineTo(400, 200),
                new LineTo(370, 200));

        Path path3_11_0_new = new Path(
                new MoveTo(370, 200),
                new LineTo(300, 200),
                new LineTo(300, 170));

        Path path3_12 = new Path(
                new MoveTo(300, 170),
                new LineTo(300, 130));

        Path path3_13 = new Path(
                new MoveTo(300, 130),
                new LineTo(300, 100),
                new LineTo(270, 100));

        Path path3_14 = new Path(
                new MoveTo(270, 100),
                new LineTo(100, 100),
                new LineTo(100, 170));

        Image ghostleft = new Image("/img/ezgif.com-rotate.gif");
        Image ghostup = new Image("/img/blackghost (1) (1).gif");
        Image ghostdown = new Image("/img/blackghost (2).gif");
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
            pathTransition.setNode(blackCircle);
            if (normal) {
                blackCircle.setFill(imagePatternDown);
            }
            pathTransition.setCycleCount(1);
            pathTransition.setInterpolator(Interpolator.LINEAR);
        }

        Platform.runLater(() -> {
            if (interrupt) {
                if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                    pathTransition.play();
                    pathTransition.setPath(null);
                    p.startEnabled();
                }
            }
        });

        if (interrupt) {
            try {
                p.semaphore300_100__100_200.acquire();
                last[0] = p.semaphore300_100__100_200;
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }

        while (interrupt) {

            if (pathTransition.getStatus() == PathTransition.Status.STOPPED) {
                /*
                 * System.out.println("black " + blackCircle.getTranslateX() + " "
                 * + blackCircle.getTranslateY());
                 */

                switch (blackCircle.getTranslateX() + " " + blackCircle.getTranslateY()) {

                    case 100.0 + " " + 170.0:
                        if (case0_1 == -1 && interrupt) {
                            case15 = -1;
                            if (normal) {
                                blackCircle.setFill(imagePatternDown);
                            }
                            pathTransition.stop();
                            try {
                                p.semaphore100_200__100_400.acquire();
                                last[1] = p.semaphore100_200__100_400;
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

                    case 100.0 + " " + 230.0:
                        if (case1 == -1 && interrupt) {
                            case0_1 = -1;
                            pathTransition.stop();

                            p.semaphore300_100__100_200.release();
                            last[0] = null;

                            case1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 270.0:
                        if (case2_0 == -1 && interrupt) {
                            case1 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore100_300__100_400.acquire();
                                last[2] = p.semaphore100_300__100_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case2_0 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path_01);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });

                        }
                        break;

                    case 100.0 + " " + 370.0:
                        if (case2 == -1 && interrupt) {
                            case2_0 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore100_400__200_600.acquire();
                                last[3] = p.semaphore100_400__200_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }

                            case2 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 100.0 + " " + 430.0:
                        if (case21 == -1 && interrupt) {
                            case2 = -1;
                            pathTransition.stop();

                            p.semaphore100_300__100_400.release();
                            last[2] = null;
                            p.semaphore100_200__100_400.release();
                            last[1] = null;

                            case21 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(12));
                                    pathTransition.setPath(path3_1_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 200.0 + " " + 570.0:
                        if (case3 == -1 && interrupt) {
                            case21 = -1;
                            pathTransition.stop();

                            if (normal) {
                                blackCircle.setFill(imagePatternUp);
                            }

                            p.semaphore100_400__200_600.release();
                            last[3] = null;
                            case3 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_2);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 200.0 + " " + 530.0:
                        if (case4_0 == -1 && interrupt) {
                            case3 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore200_500__200_400.acquire();
                                last[4] = p.semaphore200_500__200_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case4_0 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path3_2_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 200.0 + " " + 430.0:
                        if (case4 == -1 && interrupt) {
                            case4_0 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore200_400__300_400.acquire();
                                last[5] = p.semaphore200_400__300_400;
                                p.semaphore_inter_200_370__200_430.acquire();
                                last[6] = p.semaphore_inter_200_370__200_430;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case4 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_3);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 230.0 + " " + 400.0:
                        if (case4_01 == -1 && interrupt) {
                            case4 = -1;
                            pathTransition.stop();

                            if (normal) {
                                blackCircle.setFill(imagePatternRight);
                            }

                            p.semaphore200_500__200_400.release();
                            last[4] = null;
                            p.semaphore_inter_200_370__200_430.release();
                            last[6] = null;

                            case4_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_3_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 270.0 + " " + 400.0:
                        if (case4_1 == -1 && interrupt) {
                            case4_01 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore_new_300_400__400_300.acquire();
                                last[7] = p.semaphore_new_300_400__400_300;
                                p.semaphore300_400__300_300.acquire();
                                last[8] = p.semaphore300_400__300_300;
                                p.semaphore_inter_270_400__330_400.acquire();
                                last[9] = p.semaphore_inter_270_400__330_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case4_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_3_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 370.0:
                        if (case5 == -1 && interrupt) {
                            case4_1 = -1;

                            if (normal) {
                                blackCircle.setFill(imagePatternUp);
                            }

                            pathTransition.stop();
                            p.semaphore200_400__300_400.release();
                            last[5] = null;
                            p.semaphore_inter_270_400__330_400.release();
                            last[9] = null;
                            case5 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_4);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 330.0:
                        if (case6_01 == -1 && interrupt) {
                            case5 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore_inter_270_300__330_300.acquire();
                                last[10] = p.semaphore_inter_270_300__330_300;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case6_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_4_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 330.0 + " " + 300.0:
                        if (case6_1 == -1 && interrupt) {
                            case6_01 = -1;

                            if (normal) {
                                blackCircle.setFill(imagePatternRight);
                            }

                            pathTransition.stop();
                            p.semaphore300_400__300_300.release();
                            last[8] = null;
                            case6_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path3_4_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 400.0 + " " + 330.0:
                        if (case6_10 == -1 && interrupt) {
                            case6_1 = -1;

                            if (normal) {
                                blackCircle.setFill(imagePatternDown);
                            }
                            pathTransition.stop();
                            p.semaphore_new_300_400__400_300.release();
                            last[7] = null;
                            p.semaphore_inter_270_300__330_300.release();
                            last[10] = null;
                            case6_10 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_4_10);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 400.0 + " " + 370.0:
                        if (case6 == -1 && interrupt) {
                            case6_10 = -1;
                            if (normal) {
                                blackCircle.setFill(imagePatternRight);
                            }

                            pathTransition.stop();
                            try {
                                p.semaphore400_400__500_400.acquire();
                                last[11] = p.semaphore400_400__500_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case6 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path3_5);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 400.0:
                        if (case7_1 == -1 && interrupt) {
                            case6 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore_novo_500_500__500_400.acquire();
                                last[12] = p.semaphore_novo_500_500__500_400;
                                p.semaphore_inter_470_400__530_400.acquire();
                                last[13] = p.semaphore_inter_470_400__530_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case7_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_5_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 430.0:
                        if (case7 == -1 && interrupt) {
                            case7_1 = -1;

                            if (normal) {
                                blackCircle.setFill(imagePatternDown);
                            }

                            pathTransition.stop();
                            p.semaphore400_400__500_400.release();
                            last[11] = null;
                            p.semaphore_inter_470_400__530_400.release();
                            last[13] = null;
                            case7 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_6);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 470.0:
                        if (case6_0010 == -1 && interrupt) {
                            case7 = -1;

                            pathTransition.stop();

                            try {
                                p.semaphore500_500__500_600.acquire();
                                last[14] = p.semaphore500_500__500_600;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case6_0010 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_6_00);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 530.0:
                        if (case6_010 == -1 && interrupt) {
                            case6_0010 = -1;
                            pathTransition.stop();

                            if (normal) {
                                blackCircle.setFill(imagePatternRight);
                            }

                            p.semaphore_novo_500_500__500_400.release();
                            last[12] = null;

                            case6_010 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_6_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 500.0 + " " + 570.0:
                        if (case8 == -1 && interrupt) {
                            case6_010 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore500_600__600_400.acquire();
                                last[15] = p.semaphore500_600__600_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case8 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_7);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 600.0:
                        if (case7_01 == -1 && interrupt) {
                            case8 = -1;
                            pathTransition.stop();

                            if (normal) {
                                blackCircle.setFill(imagePatternUp);
                            }

                            p.semaphore500_500__500_600.release();
                            last[14] = null;

                            case7_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(9.6));
                                    pathTransition.setPath(path3_7_01);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 600.0 + " " + 430.0:
                        if (case8_1 == -1 && interrupt) {
                            case7_01 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore600_400__600_200.acquire();
                                last[16] = p.semaphore600_400__600_200;
                                p.semaphore600_300_600_400.acquire();
                                last[17] = p.semaphore600_300_600_400;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {

                            }
                            case8_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_7_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });

                        }
                        break;

                    case 600.0 + " " + 370.0:
                        if (case9 == -1 && interrupt) {
                            case8_1 = -1;
                            pathTransition.stop();

                            p.semaphore500_600__600_400.release();
                            last[15] = null;

                            case9 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_8);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });

                        }

                        break;

                    case 600.0 + " " + 330.0:
                        if (case9_00 == -1 && interrupt) {
                            case9 = -1;
                            pathTransition.stop();

                            try {
                                p.semaphore600_300__600_200.acquire();
                                last[18] = p.semaphore600_300__600_200;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }

                            case9_00 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_8_00);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });

                        }

                        break;

                    case 600.0 + " " + 270.0:
                        if (case9_0 == -1 && interrupt) {
                            case9_00 = -1;
                            pathTransition.stop();

                            p.semaphore600_300_600_400.release();
                            last[17] = null;

                            case9_0 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_8_0);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });

                        }

                        break;

                    case 600.0 + " " + 230.0:
                        if (case9_1 == -1 && interrupt) {
                            case9_0 = -1;
                            pathTransition.stop();
                            try {
                                p.semaphore600_200__500_100.acquire();
                                last[19] = p.semaphore600_200__500_100;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            } finally {
                            }

                            case9_1 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_8_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });

                        }
                        break;

                    case 600.0 + " " + 170.0:
                        if (case10 == -1 && interrupt) {
                            case9_1 = -1;
                            pathTransition.stop();

                            if (normal) {
                                blackCircle.setFill(imagePatternLeft);
                            }

                            p.semaphore600_300__600_200.release();
                            last[18] = null;
                            p.semaphore600_400__600_200.release();
                            last[16] = null;

                            case10 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(5.6));
                                    pathTransition.setPath(path3_9);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 530.0 + " " + 100.0:
                        if (case10_01 == -1 && interrupt) {
                            case10 = -1;
                            pathTransition.stop();

                            while (interrupt) {
                                if (p.semaphore400_200__400_100.tryAcquire()) {
                                    last[20] = p.semaphore400_200__400_100;
                                    if (p.semaphore500_100__400_100.tryAcquire()) {
                                        last[21] = p.semaphore500_100__400_100;
                                        break;
                                    } else {
                                        p.semaphore400_200__400_100.release();
                                        last[20] = null;
                                    }
                                } else {
                                    try {
                                        Thread.sleep(10);
                                    } catch (InterruptedException e) {

                                        Thread.currentThread().interrupt();
                                    }
                                }
                            }

                            case10_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_9_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 470.0 + " " + 100.0:
                        if (case11_01 == -1 && interrupt) {
                            case10_01 = -1;
                            pathTransition.stop();

                            p.semaphore600_200__500_100.release();
                            last[19] = null;

                            case11_01 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path3_10);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 400.0 + " " + 130.0:
                        if (case11 == -1 && interrupt) {
                            case11_01 = -1;

                            if (normal) {
                                blackCircle.setFill(imagePatternDown);
                            }

                            pathTransition.stop();
                            p.semaphore500_100__400_100.release();
                            last[21] = null;
                            case11 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_10_1);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 400.0 + " " + 170.0:
                        if (case12 == -1 && interrupt) {
                            case11 = -1;
                            pathTransition.stop();

                            while (interrupt) {
                                if (p.semaphore400_200__300_200.tryAcquire()) {
                                    last[22] = p.semaphore400_200__300_200;
                                    if (p.semaphore300_200__300_100.tryAcquire()) {
                                        last[23] = p.semaphore300_200__300_100;
                                        if (p.semaphore_inter_270_200__330_200.tryAcquire()) {
                                            last[24] = p.semaphore_inter_270_200__330_200;
                                            break;
                                        } else {
                                            p.semaphore400_200__300_200.release();
                                            last[22] = null;
                                            p.semaphore300_200__300_100.release();
                                            last[23] = null;
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

                            case12 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_11);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 370.0 + " " + 200.0:
                        if (case13_00 == -1 && interrupt) {
                            case12 = -1;
                            pathTransition.stop();

                            if (normal) {
                                blackCircle.setFill(imagePatternLeft);
                            }

                            p.semaphore400_200__400_100.release();
                            last[20] = null;

                            case13_00 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(4));
                                    pathTransition.setPath(path3_11_0_new);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 170.0:
                        if (case13 == -1 && interrupt) {
                            case13_00 = -1;
                            pathTransition.stop();
                            p.semaphore400_200__300_200.release();
                            last[22] = null;
                            p.semaphore_inter_270_200__330_200.release();
                            last[24] = null;
                            case13 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(1.6));
                                    pathTransition.setPath(path3_12);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 300.0 + " " + 130.0:
                        if (case14 == -1 && interrupt) {
                            case13 = -1;

                            if (normal) {
                                blackCircle.setFill(imagePatternUp);
                            }

                            pathTransition.stop();
                            try {
                                p.semaphore300_100__100_200.acquire();
                                last[0] = p.semaphore300_100__100_200;
                            } catch (InterruptedException e) {

                                Thread.currentThread().interrupt();
                            }
                            case14 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(2.4));
                                    pathTransition.setPath(path3_13);
                                    if (pause == false) {
                                        pathTransition.play();
                                        pathTransition.setPath(null);
                                    }
                                }
                            });
                        }
                        break;

                    case 270.0 + " " + 100.0:
                        if (case15 == -1 && interrupt) {
                            case14 = -1;

                            if (normal) {
                                blackCircle.setFill(imagePatternLeft);
                            }

                            pathTransition.stop();

                            p.semaphore300_200__300_100.release();
                            last[23] = null;

                            case15 = 1;

                            Platform.runLater(() -> {
                                if (interrupt) {
                                    pathTransition.setDuration(Duration.seconds(9.6));
                                    pathTransition.setPath(path3_14);
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
        case21 = 0;
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
        case0_1 = 0;
        case8_1 = 0;
        case9_1 = 0;
        case7_01 = 0;
        case4_1 = 0;
        case6_1 = 0;
        case7_1 = 0;
        case11_01 = 0;
        case10_01 = 0;
        case9_0 = 0;
        case6_10 = 0;
        case6_010 = 0;
        case13_00 = 0;
        case2_0 = 0;
        case6_0010 = 0;
        case4_01 = 0;
        case4_0 = 0;
        case9_00 = 0;
        case6_01 = 0;

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
