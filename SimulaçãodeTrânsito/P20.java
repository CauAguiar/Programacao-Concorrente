/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: P20.java
* Funcao...........: Thread que faz o movimento do caminho 20 (fantasma verde). 
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

public class P20 extends Thread {

    private Rectangle greenCircle; // objeto da classe Rectangle

    ThreadInfo p; // objeto da classe ThreadInfo

    private volatile boolean interrupt = true; // variavel de controle para interromper a thread
    Semaphore[] last = new Semaphore[48]; // vetor de semaforos para controle de concorrencia

    public boolean normal = true; // variavel de controle para mudar a imagem do pacman
    // variaveis de controle para o controle de concorrencia
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
    int case38 = -1;
    int case39 = -1;
    int case40 = -1;

    public PathTransition pathTransition; // objeto da classe PathTransition

    Slider slider; // objeto da classe Slider

    public P20(int i, Rectangle greenCircle, ThreadInfo threadInfo, Slider sliderP20) {

        this.greenCircle = greenCircle;
        this.slider = sliderP20;

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
        Paint imagePatternRight = greenCircle.getFill();

        Path path1 = new Path(
                new MoveTo(400, 600),
                new LineTo(400, 530));

        Path path2 = new Path(
                new MoveTo(400, 530),
                new LineTo(400, 500),
                new LineTo(430, 500));

        Path path3 = new Path(
                new MoveTo(430, 500),
                new LineTo(470, 500));

        Path path4 = new Path(
                new MoveTo(470, 500),
                new LineTo(500, 500),
                new LineTo(500, 430));

        Path path6 = new Path(
                new MoveTo(500, 430),
                new LineTo(500, 400),
                new LineTo(530, 400));

        Path path7 = new Path(
                new MoveTo(530, 400),
                new LineTo(600, 400),
                new LineTo(600, 370));

        Path path9 = new Path(
                new MoveTo(600, 370),
                new LineTo(600, 330));

        Path path10 = new Path(
                new MoveTo(600, 330),
                new LineTo(600, 300),
                new LineTo(570, 300));

        Path path11 = new Path(
                new MoveTo(570, 300),
                new LineTo(500, 300),
                new LineTo(500, 270));

        Path path13 = new Path(
                new MoveTo(500, 270),
                new LineTo(500, 230));

        Path path14 = new Path(
                new MoveTo(500, 230),
                new LineTo(500, 200),
                new LineTo(470, 200));

        Path path15 = new Path(
                new MoveTo(470, 200),
                new LineTo(430, 200));

        Path path16 = new Path(
                new MoveTo(430, 200),
                new LineTo(400, 200),
                new LineTo(400, 170));

        Path path17 = new Path(
                new MoveTo(400, 170),
                new LineTo(400, 130));

        Path path18 = new Path(
                new MoveTo(400, 130),
                new LineTo(400, 100),
                new LineTo(370, 100));

        Path path19 = new Path(
                new MoveTo(370, 100),
                new LineTo(330, 100));

        Path path20 = new Path(
                new MoveTo(330, 100),
                new LineTo(300, 100),
                new LineTo(300, 130));

        Path path21 = new Path(
                new MoveTo(300, 130),
                new LineTo(300, 170));

        Path path22 = new Path(
                new MoveTo(300, 170),
                new LineTo(300, 200),
                new LineTo(270, 200));

        Path path23 = new Path(
                new MoveTo(270, 200),
                new LineTo(200, 200),
                new LineTo(200, 230));

        Path path25 = new Path(
                new MoveTo(200, 230),
                new LineTo(200, 270));

        Path path26 = new Path(
                new MoveTo(200, 270),
                new LineTo(200, 300),
                new LineTo(170, 300));

        Path path27 = new Path(
                new MoveTo(170, 300),
                new LineTo(130, 300));

        Path path28 = new Path(
                new MoveTo(130, 300),
                new LineTo(100, 300),
                new LineTo(100, 370));

        Path path30 = new Path(
                new MoveTo(100, 370),
                new LineTo(100, 400),
                new LineTo(130, 400));

        Path path31 = new Path(
                new MoveTo(130, 400),
                new LineTo(170, 400));

        Path path32 = new Path(
                new MoveTo(170, 400),
                new LineTo(200, 400),
                new LineTo(200, 430));

        Path path33 = new Path(
                new MoveTo(200, 430),
                new LineTo(200, 500),
                new LineTo(230, 500));

        Path path35 = new Path(
                new MoveTo(230, 500),
                new LineTo(270, 500));

        Path path36 = new Path(
                new MoveTo(270, 500),
                new LineTo(300, 500),
                new LineTo(300, 600),
                new LineTo(330, 600));

        Path path39 = new Path(
                new MoveTo(330, 600),
                new LineTo(400, 600),
                new LineTo(400, 570));

        Path path41 = new Path(
                new MoveTo(400, 570),
                new LineTo(400, 530));

        Image ghostleft = new Image("/img/ezgif.com-rotate (2).gif");
        Image ghostup = new Image("/img/blackghost_1_1_04039.gif");
        Image ghostdown = new Image("/img/blackghost_2_03954.gif");
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
            pathTransition.setNode(greenCircle);
            if (normal) {
                greenCircle.setFill(imagePatternUp);
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

        while (interrupt) {

            if (pathTransition.getStatus() == PathTransition.Status.STOPPED) {
                if (interrupt) {

                    switch (greenCircle.getTranslateX() + " " + greenCircle.getTranslateY()) {
                        case 400.0 + " " + 530.0:
                            if (case1 == -1 && interrupt) {
                                case40 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore_intersec_370_500__430_500.acquire();
                                    last[41] = p.semaphore_intersec_370_500__430_500;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case1 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path2);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                                pathTransition.play();
                                                pathTransition.setPath(null);
                                            }
                                        }
                                    }
                                });
                            }
                            break;

                        case 430.0 + " " + 500.0:
                            if (case2 == -1 && interrupt) {
                                case1 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternRight);
                                }

                                p.semaphore_intersec_370_500__430_500.release();
                                last[41] = null;

                                case2 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path3);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 470.0 + " " + 500.0:
                            if (case3 == -1 && interrupt) {
                                case2 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternUp);
                                }

                                while (interrupt) {
                                    if (p.semaphore_novo_500_500__500_400.tryAcquire()) {
                                        last[12] = p.semaphore_novo_500_500__500_400;
                                        if (p.semaphore600_300_600_400.tryAcquire()) {
                                            last[17] = p.semaphore600_300_600_400;
                                            if (p.semaphore500_400__600_400.tryAcquire()) {
                                                last[37] = p.semaphore500_400__600_400;
                                                break;
                                            } else {
                                                p.semaphore600_300_600_400.release();
                                                last[17] = null;
                                                p.semaphore_novo_500_500__500_400.release();
                                                last[12] = null;
                                            }
                                        } else {
                                            p.semaphore_novo_500_500__500_400.release();
                                            last[12] = null;
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
                                        pathTransition.setDuration(Duration.seconds(4));
                                        pathTransition.setPath(path4);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 500.0 + " " + 430.0:
                            if (case5 == -1 && interrupt) {
                                case3 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore_inter_470_400__530_400.acquire();
                                    last[13] = p.semaphore_inter_470_400__530_400;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case5 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path6);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 530.0 + " " + 400.0:
                            if (case6 == -1 && interrupt) {
                                case5 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternRight);
                                }

                                p.semaphore_novo_500_500__500_400.release();
                                last[12] = null;
                                p.semaphore_inter_470_400__530_400.release();
                                last[13] = null;

                                case6 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(4));
                                        pathTransition.setPath(path7);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 600.0 + " " + 370.0:
                            if (case8 == -1 && interrupt) {
                                case6 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternUp);
                                }

                                p.semaphore500_400__600_400.release();
                                last[37] = null;

                                case8 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path9);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 600.0 + " " + 330.0:
                            if (case9 == -1 && interrupt) {
                                case8 = -1;
                                pathTransition.stop();

                                while (interrupt) {
                                    if (p.semaphore500_300__600_300.tryAcquire()) {
                                        last[42] = p.semaphore500_300__600_300;
                                        if (p.semaphore_novo_500_300__500_200.tryAcquire()) {
                                            last[33] = p.semaphore_novo_500_300__500_200;
                                            if (p.semaphore_inter_470_300__530_300.tryAcquire()) {
                                                last[35] = p.semaphore_inter_470_300__530_300;
                                                break;
                                            } else {
                                                p.semaphore_novo_500_300__500_200.release();
                                                last[33] = null;
                                                p.semaphore500_300__600_300.release();
                                                last[42] = null;
                                            }
                                        } else {
                                            p.semaphore500_300__600_300.release();
                                            last[42] = null;
                                        }
                                    } else {
                                        try {
                                            Thread.sleep(10);
                                        } catch (InterruptedException e) {

                                            Thread.currentThread().interrupt();
                                        }
                                    }
                                }

                                case9 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path10);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 570.0 + " " + 300.0:
                            if (case10 == -1 && interrupt) {
                                case9 = -1;

                                if (normal) {
                                    greenCircle.setFill(imagePatternLeft);
                                }

                                pathTransition.stop();

                                p.semaphore600_300_600_400.release();
                                last[17] = null;

                                case10 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(4));
                                        pathTransition.setPath(path11);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 500.0 + " " + 270.0:
                            if (case12 == -1 && interrupt) {
                                case10 = -1;

                                if (normal) {
                                    greenCircle.setFill(imagePatternUp);
                                }

                                pathTransition.stop();

                                p.semaphore_inter_470_300__530_300.release();
                                last[35] = null;
                                p.semaphore500_300__600_300.release();
                                last[42] = null;

                                case12 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path13);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 500.0 + " " + 230.0:
                            if (case13 == -1 && interrupt) {
                                case12 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore500_200__400_200.acquire();
                                    last[44] = p.semaphore500_200__400_200;
                                    p.semaphore_inter_470_200__530_200.acquire();
                                    last[34] = p.semaphore_inter_470_200__530_200;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case13 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path14);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 470.0 + " " + 200.0:
                            if (case14 == -1 && interrupt) {
                                case13 = -1;

                                if (normal) {
                                    greenCircle.setFill(imagePatternLeft);
                                }

                                pathTransition.stop();

                                p.semaphore_inter_470_200__530_200.release();
                                last[34] = null;
                                p.semaphore_novo_500_300__500_200.release();
                                last[33] = null;

                                case14 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path15);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 430.0 + " " + 200.0:
                            if (case15 == -1 && interrupt) {
                                case14 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore400_200__400_100.acquire();
                                    last[20] = p.semaphore400_200__400_100;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case15 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path16);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 400.0 + " " + 170.0:
                            if (case16 == -1 && interrupt) {
                                case15 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternUp);
                                }

                                p.semaphore500_200__400_200.release();
                                last[44] = null;

                                case16 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path17);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 400.0 + " " + 130.0:
                            if (case17 == -1 && interrupt) {
                                case16 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore300_100__400_100.acquire();
                                    last[32] = p.semaphore300_100__400_100;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case17 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path18);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 370.0 + " " + 100.0:
                            if (case18 == -1 && interrupt) {
                                case17 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternLeft);
                                }

                                p.semaphore400_200__400_100.release();
                                last[20] = null;

                                case18 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path19);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 330.0 + " " + 100.0:
                            if (case19 == -1 && interrupt) {
                                case18 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore300_200__300_100.acquire();
                                    last[45] = p.semaphore300_200__300_100;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case19 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path20);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 300.0 + " " + 130.0:
                            if (case20 == -1 && interrupt) {
                                case19 = -1;

                                if (normal) {
                                    greenCircle.setFill(imagePatternDown);
                                }

                                pathTransition.stop();

                                p.semaphore300_100__400_100.release();
                                last[32] = null;

                                case20 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path21);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 300.0 + " " + 170.0:
                            if (case21 == -1 && interrupt) {
                                case20 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore200_200__300_200.acquire();
                                    last[46] = p.semaphore200_200__300_200;
                                    p.semaphore_inter_270_200__330_200.acquire();
                                    last[47] = p.semaphore_inter_270_200__330_200;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case21 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path22);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 270.0 + " " + 200.0:
                            if (case22 == -1 && interrupt) {
                                case21 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternLeft);
                                }

                                p.semaphore300_200__300_100.release();
                                last[45] = null;
                                p.semaphore_inter_270_200__330_200.release();
                                last[47] = null;

                                case22 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(4));
                                        pathTransition.setPath(path23);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 200.0 + " " + 230.0:
                            if (case24 == -1 && interrupt) {
                                case22 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternDown);
                                }

                                p.semaphore200_200__300_200.release();
                                last[46] = null;

                                case24 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path25);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 200.0 + " " + 270.0:
                            if (case25 == -1 && interrupt) {
                                case24 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore_inter_200_270__200_330.acquire();
                                    last[43] = p.semaphore_inter_200_270__200_330;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case25 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path26);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 170.0 + " " + 300.0:
                            if (case26 == -1 && interrupt) {
                                case25 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternLeft);
                                }

                                p.semaphore_inter_200_270__200_330.release();
                                last[43] = null;

                                case26 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path27);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 130.0 + " " + 300.0:
                            if (case27 == -1 && interrupt) {
                                case26 = -1;
                                pathTransition.stop();

                                while (interrupt) {
                                    if (p.semaphore100_300__100_400.tryAcquire()) {
                                        last[2] = p.semaphore100_300__100_400;
                                        if (p.semaphore100_400__200_400.tryAcquire()) {
                                            last[39] = p.semaphore100_400__200_400;
                                            if (p.semaphore200_500__200_400.tryAcquire()) {
                                                last[4] = p.semaphore200_500__200_400;
                                                break;
                                            } else {
                                                p.semaphore100_400__200_400.release();
                                                last[39] = null;
                                                p.semaphore100_300__100_400.release();
                                                last[2] = null;
                                            }
                                        } else {
                                            p.semaphore100_300__100_400.release();
                                            last[2] = null;
                                        }
                                    } else {
                                        try {
                                            Thread.sleep(10);
                                        } catch (InterruptedException e) {

                                            Thread.currentThread().interrupt();
                                        }
                                    }
                                }

                                case27 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(4));
                                        pathTransition.setPath(path28);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 100.0 + " " + 370.0:
                            if (case29 == -1 && interrupt) {
                                case27 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternDown);
                                }

                                case29 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path30);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 130.0 + " " + 400.0:
                            if (case30 == -1 && interrupt) {
                                case29 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternRight);
                                }

                                p.semaphore100_300__100_400.release();
                                last[2] = null;

                                case30 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path31);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 170.0 + " " + 400.0:
                            if (case31 == -1 && interrupt) {
                                case30 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore_inter_200_370__200_430.acquire();
                                    last[6] = p.semaphore_inter_200_370__200_430;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case31 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(2.4));
                                        pathTransition.setPath(path32);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 200.0 + " " + 430.0:
                            if (case32 == -1 && interrupt) {
                                case31 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternDown);
                                }

                                p.semaphore100_400__200_400.release();
                                last[39] = null;
                                p.semaphore_inter_200_370__200_430.release();
                                last[6] = null;

                                case32 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(4));
                                        pathTransition.setPath(path33);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 230.0 + " " + 500.0:
                            if (case34 == -1 && interrupt) {
                                case32 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternDown);
                                }

                                p.semaphore200_500__200_400.release();
                                last[4] = null;

                                case34 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path35);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 270.0 + " " + 500.0:
                            if (case35 == -1 && interrupt) {
                                case34 = -1;
                                pathTransition.stop();

                                try {
                                    p.semaphore300_500__300_600__400_600.acquire();
                                    last[27] = p.semaphore300_500__300_600__400_600;
                                    p.semaphore300_600__300_500.acquire();
                                    last[28] = p.semaphore300_600__300_500;
                                    p.semaphore300_600__400_600.acquire();
                                    last[29] = p.semaphore300_600__400_600;
                                } catch (InterruptedException e) {

                                    Thread.currentThread().interrupt();
                                }

                                case35 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(6.4));
                                        pathTransition.setPath(path36);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 330.0 + " " + 600.0:
                            if (case38 == -1 && interrupt) {
                                case35 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternRight);
                                }

                                p.semaphore300_600__300_500.release();
                                last[28] = null;

                                case38 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(4));
                                        pathTransition.setPath(path39);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                        case 400.0 + " " + 570.0:
                            if (case40 == -1 && interrupt) {
                                case38 = -1;
                                pathTransition.stop();

                                if (normal) {
                                    greenCircle.setFill(imagePatternUp);
                                }

                                p.semaphore300_500__300_600__400_600.release();
                                last[27] = null;
                                p.semaphore300_600__400_600.release();
                                last[29] = null;

                                case40 = 1;

                                Platform.runLater(() -> {
                                    if (interrupt) {
                                        pathTransition.setDuration(Duration.seconds(1.6));
                                        pathTransition.setPath(path41);
                                        if (pathTransition.getStatus() != PathTransition.Status.PAUSED) {
                                            pathTransition.play();
                                            pathTransition.setPath(null);
                                        }
                                    }
                                });
                            }
                            break;

                    }
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
        case38 = 0;
        case39 = 0;
        case40 = 0;

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

        if (b == true) {
            Platform.runLater(() -> {
                pathTransition.pause();
            });
        }
    }
}
