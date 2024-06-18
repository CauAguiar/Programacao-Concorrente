/* ***************************************************************
* Autor............: Caue Rodrigues de Aguiar
* Matricula........: 202210181
* Inicio...........: 16/11/2023
* Ultima alteracao.: 02/12/2023
* Nome.............: ThreadInfo.java
* Funcao...........: Classe que controla o movimento do pacman e as colisoes
*************************************************************** */

import java.util.concurrent.Semaphore;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;

public class ThreadInfo extends Thread {

    Semaphore semaphore300_100__100_200 = new Semaphore(1);
    Semaphore semaphore500_600__600_400 = new Semaphore(1);
    Semaphore semaphore100_400__200_600 = new Semaphore(1);
    Semaphore semaphore400_200__300_200 = new Semaphore(1);
    Semaphore semaphore100_200__100_400 = new Semaphore(1);
    Semaphore semaphore600_400__600_200 = new Semaphore(1);
    Semaphore semaphore200_400__300_400 = new Semaphore(1);
    Semaphore semaphore400_400__500_400 = new Semaphore(1);
    Semaphore semaphore100_400__200_400 = new Semaphore(1);
    Semaphore semaphore300_400__400_400 = new Semaphore(1);
    Semaphore semaphore500_400__600_400 = new Semaphore(1);
    Semaphore semaphore600_200__500_100 = new Semaphore(1);
    Semaphore semaphore500_100__400_100 = new Semaphore(1);
    Semaphore semaphore300_100__400_100 = new Semaphore(1);
    Semaphore semaphore200_600__300_600 = new Semaphore(1);
    Semaphore semaphore300_600__500_600 = new Semaphore(1);
    Semaphore semaphore300_400__300_300 = new Semaphore(1);
    Semaphore semaphore500_500__500_600 = new Semaphore(1);
    Semaphore semaphore300_200__300_100 = new Semaphore(1);
    Semaphore semaphore300_100_500_100 = new Semaphore(1); 
    Semaphore semaphore_inter_270_400__330_400 = new Semaphore(1);
    Semaphore semaphore_inter_270_200__330_200 = new Semaphore(1);
    Semaphore semaphore_inter_470_200__530_200 = new Semaphore(1);
    Semaphore semaphore_inter_470_400__530_400 = new Semaphore(1);
    Semaphore semaphore600_300_600_400 = new Semaphore(1); 
    Semaphore semaphore_inter_470_300__530_300 = new Semaphore(1);
    Semaphore semaphore_new_300_400__400_300 = new Semaphore(1);
    Semaphore semaphore_new_300_300__300_600__500_600 = new Semaphore(1);
    Semaphore semaphore_novo_500_500__500_400 = new Semaphore(1);
    Semaphore semaphore500_300__600_300 = new Semaphore(1);
    Semaphore semaphore_novo_500_300__500_200 = new Semaphore(1);
    Semaphore semaphore400_200__400_100 = new Semaphore(1);
    Semaphore semaphore500_200__400_200 = new Semaphore(1);
    Semaphore semaphore100_300__100_400 = new Semaphore(1);
    Semaphore semaphore200_200__300_200 = new Semaphore(1);
    Semaphore semaphore200_500__200_400 = new Semaphore(1);
    Semaphore semaphore300_500__300_600__400_600 = new Semaphore(1);
    Semaphore semaphore300_600__300_500 = new Semaphore(1);
    Semaphore semaphore_intersec_370_500__430_500 = new Semaphore(1);
    Semaphore semaphore500_400__500_300 = new Semaphore(1);
    Semaphore semaphore600_300__600_200 = new Semaphore(1);
    Semaphore semaphore300_300__300_200 = new Semaphore(1);
    Semaphore semaphore_inter_270_300__330_300 = new Semaphore(1);
    Semaphore semaphore_inter_200_270__200_330 = new Semaphore(1);
    Semaphore semaphore_inter_200_370__200_430 = new Semaphore(1);
    Semaphore semaphore300_600__400_600 = new Semaphore(1);


    private Pane root; // painel principal
    private Circle pacman; // circulo do pacman
    private Rectangle blackCircle; // retangulo do fantasma preto
    private Rectangle blueCircle; // retangulo do fantasma azul
    private Rectangle pinkCircle; // retangulo do fantasma rosa
    private Rectangle redCircle; // retangulo do fantasma vermelho
    private Rectangle purpleCircle; // retangulo do fantasma roxo
    private Rectangle orangeCircle; // retangulo do fantasma laranja
    private Rectangle greenCircle; // retangulo do fantasma verde
    private Rectangle whiteCircle; // retangulo do fantasma branco
    private Path pathAll; // caminho de todos os fantasmas

    public boolean normal = true; // variavel que controla se os fantasmas estao normal ou nao

    private boolean movingRight = false;  // variavel que controla o movimento do pacman direita
    private boolean movingLeft = false;  // variavel que controla o movimento do pacman esquerda
    private boolean movingUp = false; // variavel que controla o movimento do pacman cima
    private boolean movingDown = false; // variavel que controla o movimento do pacman baixo

    ImagePattern imagePatternRight; // imagem do pacman direita

    ImagePattern imagePatternLeft; // imagem do pacman esquerda

    ImagePattern imagePatternUp; // imagem do pacman cima

    ImagePattern imagePatternDown; // imagem do pacman baixo
    ImagePattern imagePatternStaticRight; // imagem do pacman parado direita
    ImagePattern imagePatternStaticLeft; // imagem do pacman parado esquerda
    ImagePattern imagePatternStaticUp; // imagem do pacman parado cima
    ImagePattern imagePatternStaticDown; // imagem do pacman parado baixo
    private AnimationTimer collisionTimer; // timer que controla as colisoes
    private AnimationTimer collisionTimer2; // timer que controla as colisoes
    private AnimationTimer timer; // timer que controla o movimento do pacman
    public Principal principal; // classe principal
    private boolean dead; // variavel que controla se o pacman esta morto ou nao

    public ThreadInfo(Pane root, Circle pacman, Rectangle blackCircle, Rectangle blueCircle, Rectangle pinkCircle,
            Rectangle redCircle, Rectangle purpleCircle, Rectangle orangeCircle, Rectangle greenCircle,
            Rectangle whiteCircle, Path pathAll, Principal principal) {
        this.root = root;
        this.pacman = pacman;
        this.blackCircle = blackCircle;
        this.blueCircle = blueCircle;
        this.pinkCircle = pinkCircle;
        this.redCircle = redCircle;
        this.purpleCircle = purpleCircle;
        this.orangeCircle = orangeCircle;
        this.greenCircle = greenCircle;
        this.whiteCircle = whiteCircle;
        this.pathAll = pathAll;
        this.principal = principal;
    } // fim do construtor

    /* ***************************************************************
    * Metodo: run
    * Funcao: inicializa as imagens do pacman
    * Parametros: sem parametros
    * Retorno: void
    *************************************************************** */
    @Override
    public void run() {
        movingRight = false;
        movingLeft = false;
        movingUp = false;
        movingDown = false;

        Image pacmanRight = new Image("/img/pacmanright.gif");
        imagePatternRight = new ImagePattern(pacmanRight);
        Image pacmanLeft = new Image("/img/pacmanleft.gif");
        imagePatternLeft = new ImagePattern(pacmanLeft);
        Image pacmanUp = new Image("/img/pacmanup.gif");
        imagePatternUp = new ImagePattern(pacmanUp);
        Image pacmanDown = new Image("/img/pacmandown.gif");
        imagePatternDown = new ImagePattern(pacmanDown);

        Image pacmanImageStaticRight = new Image("/img/pacmanstaticright.png");
        imagePatternStaticRight = new ImagePattern(pacmanImageStaticRight);
        Image pacmanImageStaticLeft = new Image("/img/pacmanstaticleft.png");
        imagePatternStaticLeft = new ImagePattern(pacmanImageStaticLeft);
        Image pacmanImageStaticUp = new Image("/img/pacmanstaticup.png");
        imagePatternStaticUp = new ImagePattern(pacmanImageStaticUp);
        Image pacmanImageStaticDown = new Image("/img/pacmanstaticdown.png");
        imagePatternStaticDown = new ImagePattern(pacmanImageStaticDown);
    } // fim do metodo run

    /* ***************************************************************
    * Metodo: resetAllSemaphores
    * Funcao: reseta todos os semaforos
    * Parametros: sem parametros
    * Retorno: void
    *************************************************************** */
    public void resetAllSemaphores() {
        semaphore300_100__100_200.release(3);
        semaphore500_600__600_400.release(3);
        semaphore100_400__200_600.release(3);
        semaphore400_200__300_200.release(3);
        semaphore100_200__100_400.release(3);
        semaphore600_400__600_200.release(3);
        semaphore200_400__300_400.release(3);
        semaphore400_400__500_400.release(3);
        semaphore100_400__200_400.release(3);
        semaphore300_400__400_400.release(3);
        semaphore500_400__600_400.release(3);
        semaphore600_200__500_100.release(3);
        semaphore500_100__400_100.release(3);
        semaphore300_100__400_100.release(3);
        semaphore200_600__300_600.release(3);
        semaphore300_600__500_600.release(3);
        semaphore300_400__300_300.release(3);
        semaphore500_500__500_600.release(3);
        semaphore300_200__300_100.release(3);
        semaphore300_100_500_100.release(3); 
        semaphore_inter_270_400__330_400.release(3);
        semaphore_inter_270_200__330_200.release(3);
        semaphore_inter_470_200__530_200.release(3);
        semaphore_inter_470_400__530_400.release(3);
        semaphore600_300_600_400.release(3); 
        semaphore_inter_470_300__530_300.release(3);
        semaphore_new_300_400__400_300.release(3);
        semaphore_new_300_300__300_600__500_600.release(3);
        semaphore_novo_500_500__500_400.release(3);
        semaphore500_300__600_300.release(3);
        semaphore_novo_500_300__500_200.release(3);
        semaphore400_200__400_100.release(3);
        semaphore500_200__400_200.release(3);
        semaphore100_300__100_400.release(3);
        semaphore200_200__300_200.release(3);
        semaphore200_500__200_400.release(3);
        semaphore300_500__300_600__400_600.release(3);
        semaphore300_600__300_500.release(3);
        semaphore_intersec_370_500__430_500.release(3);
        semaphore500_400__500_300.release(3);
        semaphore600_300__600_200.release(3);
        semaphore300_300__300_200.release(3);
        semaphore_inter_270_300__330_300.release(3);
        semaphore_inter_200_270__200_330.release(3);
        semaphore_inter_200_370__200_430.release(3);
        semaphore300_600__400_600.release(3); 

    } // fim do metodo resetAllSemaphores

    /* ***************************************************************
    * Metodo: setPacmanMovement
    * Funcao: controla o movimento do pacman
    * Parametros: Pane root, Circle pacman
    * Retorno: void
    *************************************************************** */
    private void setPacmanMovement(Pane root, Circle pacman) {
        double stepB = 10;

        root.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    movingDown = false;
                    if (isPointInPath(pacman.getCenterX(), pacman.getCenterY() - stepB)) {
                        if (pacman.getFill() != imagePatternUp) {
                            pacman.setFill(imagePatternUp);
                        } // fim do if
                        movingRight = false;
                        movingLeft = false;
                        movingUp = true;
                    } // fim do if

                    break;
                case DOWN:
                    movingUp = false;
                    if (isPointInPath(pacman.getCenterX(), pacman.getCenterY() + stepB)) {
                        if (pacman.getFill() != imagePatternDown) {
                            pacman.setFill(imagePatternDown);
                        } // fim do if
                        movingRight = false;
                        movingLeft = false;
                        movingDown = true;
                    } // fim do if

                    break;
                case LEFT:
                    movingRight = false;
                    if (isPointInPath(pacman.getCenterX() - stepB, pacman.getCenterY())) {
                        if (pacman.getFill() != imagePatternLeft) {
                            pacman.setFill(imagePatternLeft);
                        } // fim do if
                        movingUp = false;
                        movingDown = false;
                        movingLeft = true;
                    } // fim do if

                    break;
                case RIGHT:

                    movingLeft = false;
                    if (isPointInPath(pacman.getCenterX() + stepB, pacman.getCenterY())) {
                        if (pacman.getFill() != imagePatternRight) {
                            pacman.setFill(imagePatternRight);
                        } // fim do if
                        movingDown = false;
                        movingUp = false;
                        movingRight = true;
                    } // fim do if

                    break;
                default:
                    break;
            } // fim do switch
        }); // fim do setOnKeyPressed

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double step = 0.8;
                double newX = pacman.getCenterX();
                double newY = pacman.getCenterY();

                if (movingUp) {
                    newY -= step;

                    if (isPointInPath(newX, newY)) {
                        principal.checkAndRemoveCollision(newX, newY);
                        if (newX > 100 && newX <= 105 && newY >= 195 && newY <= 205) {
                            pacman.setCenterX(100);
                        } else if (newX > 100 && newX <= 105 && newY >= 95 && newY <= 105) {
                            pacman.setCenterX(100);
                        } else if (newX > 100 && newX <= 105 && newY >= 195 && newY <= 205) {
                            pacman.setCenterX(100);
                        } else if (newX > 100 && newX <= 105 && newY >= 295 && newY <= 305) {
                            pacman.setCenterX(100);
                        } else if (newX > 100 && newX <= 105 && newY >= 395 && newY <= 405) {
                            pacman.setCenterX(100);
                        } else if (newX > 100 && newX <= 105 && newY >= 595 && newY <= 605) {
                            pacman.setCenterX(100);
                        } else if (newX >= 595 && newX < 600 && newY >= 195 && newY <= 205) {
                            pacman.setCenterX(600);
                        } else if (newX >= 595 && newX < 600 && newY >= 95 && newY <= 105) {
                            pacman.setCenterX(600);
                        } else if (newX >= 595 && newX < 600 && newY >= 195 && newY <= 205) {
                            pacman.setCenterX(600);
                        } else if (newX >= 595 && newX < 600 && newY >= 295 && newY <= 305) {
                            pacman.setCenterX(600);
                        } else if (newX >= 595 && newX < 600 && newY >= 395 && newY <= 405) {
                            pacman.setCenterX(600);
                        } else if (newX >= 595 && newX < 600 && newY >= 595 && newY <= 605) {
                            pacman.setCenterX(600);
                        } else if (newX >= 195 && newX <= 205  && newX != 200) {
                            pacman.setCenterX(200);
                            
                        } else if (newX >= 495 && newX <= 505  && newX != 500) {
                            pacman.setCenterX(500);
                            
                        } else if (newX >= 295 && newX <= 305  && newX != 300) {
                            pacman.setCenterX(300);
                            
                        } else if (newX >= 395 && newX <= 405  && newX != 400) {
                            pacman.setCenterX(400);
                            
                        } // fim do if

                        else {
                            pacman.setCenterX(newX);
                            pacman.setCenterY(newY);
                        } // fim do else

                    } // fim do if

                }// fim do if
                if (movingDown) {
                    newY += step;

                    if (isPointInPath(newX, newY)) {
                        principal.checkAndRemoveCollision(newX, newY);
                        if (newX > 100 && newX <= 105 && newY >= 195 && newY <= 205) {
                            pacman.setCenterX(100);
                        } else if (newX > 100 && newX <= 105 && newY >= 95 && newY <= 105) {
                            pacman.setCenterX(100);
                        } else if (newX > 100 && newX <= 105 && newY >= 195 && newY <= 205) {
                            pacman.setCenterX(100);
                        } else if (newX > 100 && newX <= 105 && newY >= 295 && newY <= 305) {
                            pacman.setCenterX(100);
                        } else if (newX > 100 && newX <= 105 && newY >= 395 && newY <= 405) {
                            pacman.setCenterX(100);
                        
                        } else if (newX > 100 && newX <= 105 && newY >= 595 && newY <= 605) {
                            pacman.setCenterX(100);
                        } else if (newX >= 595 && newX < 600 && newY >= 195 && newY <= 205) {
                            pacman.setCenterX(600);
                        } else if (newX >= 595 && newX < 600 && newY >= 295 && newY <= 305) {
                            pacman.setCenterX(600);
                        } else if (newX >= 595 && newX < 600 && newY >= 395 && newY <= 405) {
                            pacman.setCenterX(600);
                        
                        } else if (newX >= 595 && newX < 600 && newY >= 95 && newY <= 105) {
                            pacman.setCenterX(600);
                        } else if (newX >= 595 && newX < 600 && newY >= 595 && newY <= 605) {
                            pacman.setCenterX(600);
                        } else if (newX >= 195 && newX <= 205  && newX != 200) {
                            pacman.setCenterX(200);
                            
                        } else if (newX >= 495 && newX <= 505  && newX != 500) {
                            pacman.setCenterX(500);
                            
                        } else if (newX >= 295 && newX <= 305  && newX != 300) {
                            pacman.setCenterX(300);
                            
                        } else if (newX >= 395 && newX <= 405  && newX != 400) {
                            pacman.setCenterX(400);
                            

                        } // fim do if

                        else {
                            pacman.setCenterX(newX);
                            pacman.setCenterY(newY);
                        } // fim do else
                    } // fim do if

                } // fim do if
                if (movingLeft) {
                    newX -= step;
                    if (isPointInPath(newX, newY)) {
                        principal.checkAndRemoveCollision(newX, newY);
                        if (newX > 100 && newX < 600 && newY >= 195 && newY <= 205 && newY != 200) {
                            pacman.setCenterY(200);
                        } else if (newX > 100 && newX < 600 && newY >= 595 && newY <= 605 && newY != 600) {
                            pacman.setCenterY(600);
                        } else if (newX > 100 && newX < 600 && newY >= 95 && newY <= 105 && newY != 100) {
                            pacman.setCenterY(100);
                        } else if (newX > 100 && newX < 600 && newY >= 395 && newY <= 405 && newY != 400) {
                            pacman.setCenterY(400);

                        } else if (newX > 100 && newX < 600 && newY >= 295 && newY <= 305 && newY != 300) {
                            pacman.setCenterY(300);

                        } else if (newX > 100 && newX < 600 && newY >= 495 && newY <= 505 && newY != 500) {
                            pacman.setCenterY(500);

                        } else if (newX < 200 && newX > 190 && newY <= 505 && newY >= 495) {
                            pacman.setCenterX(200);
                            pacman.setFill(imagePatternStaticLeft);

                        } else if (newX < 510 && newX > 500 && newY <= 505 && newY >= 495) {
                            pacman.setCenterX(500);

                        } else {
                            pacman.setCenterX(newX);
                            pacman.setCenterY(newY);
                        } // fim do else
                    } // fim do if

                } // fim do if

                if (movingRight) {
                    newX += step;
                    if (isPointInPath(newX, newY)) {
                        principal.checkAndRemoveCollision(newX, newY);


                        if (newX > 100 && newX < 600 && newY >= 195 && newY <= 205 && newY != 200) {
                            pacman.setCenterY(200);
                        } else if (newX > 100 && newX < 600 && newY >= 595 && newY <= 605 && newY != 600) {
                            pacman.setCenterY(600);
                        } else if (newX > 100 && newX < 600 && newY >= 95 && newY <= 105 && newY != 100) {
                            pacman.setCenterY(100);
                        } else if (newX > 100 && newX < 600 && newY >= 395 && newY <= 405 && newY != 400) {
                            pacman.setCenterY(400);

                        } else if (newX > 100 && newX < 600 && newY >= 295 && newY <= 305 && newY != 300) {
                            pacman.setCenterY(300);

                        } else if (newX > 100 && newX < 600 && newY >= 495 && newY <= 505 && newY != 500) {
                            pacman.setCenterY(500);

                        } else if (newX < 200 && newX > 190 && newY <= 505 && newY >= 495) {
                            pacman.setCenterX(200);

                        } else if (newX < 510 && newX > 500 && newY <= 505 && newY >= 495) {
                            pacman.setFill(imagePatternStaticRight);
                            pacman.setCenterX(500);

                        } else {
                            pacman.setCenterX(newX);
                            pacman.setCenterY(newY);
                        } // fim do else
                    } // fim do if

                } // fim do if

                if (newY < 100) {
                    pacman.setCenterY(100);
                    pacman.setFill(imagePatternStaticUp);
                } else if (newX < 100) {
                    pacman.setCenterX(100);
                    pacman.setFill(imagePatternStaticLeft);
                } else if (newY > 600) {
                    pacman.setCenterY(600);
                    pacman.setFill(imagePatternStaticDown);
                } else if (newX > 600) {
                    pacman.setCenterX(600);
                    pacman.setFill(imagePatternStaticRight);
                } // fim do else if

                

            } // fim do handle

        }; // fim do timer
        timer.start();
    } // fim do metodo setPacmanMovement

    /* ***************************************************************
    * Metodo: isPointInPath
    * Funcao: verifica se o ponto esta no caminho
    * Parametros: double x, double y
    * Retorno: boolean
    *************************************************************** */
    private boolean isPointInPath(double x, double y) {
        if (pathAll.contains(x, y)) {
            return true;
        } else {
            return false;
        } // fim do else
    } // fim do metodo isPointInPath

    /* ***************************************************************
    * Metodo: checkCollisions
    * Funcao: verifica as colisoes do pacman com fantasmas
    * Parametros: sem parametros
    * Retorno: void
    *************************************************************** */
    private void checkCollisions() {
        collisionTimer = new AnimationTimer() {
            
            @Override
            public void handle(long now) {


                if (calculateDistance(pacman, blackCircle) || calculateDistance(pacman, blueCircle)
                        || calculateDistance(pacman, pinkCircle) || calculateDistance(pacman, redCircle)
                        || calculateDistance(pacman, purpleCircle) || calculateDistance(pacman, orangeCircle)
                        || calculateDistance(pacman, greenCircle) || calculateDistance(pacman, whiteCircle)) {

                    if(normal){
                    dead = true;
                    pacman.setFill(new ImagePattern(new Image("/img/dead.gif")));
                    principal.dead();
                    } // fim do if
                    
                } // fim do if

            } // fim do handle
        }; // fim do collisionTimer
        if(!dead){
            collisionTimer.start();
        } else {
            collisionTimer.stop();
        } // fim do else
    } // fim do metodo checkCollisions

    /* ***************************************************************
    * Metodo: calculateDistance
    * Funcao: calcula a distancia entre o pacman e os fantasmas
    * Parametros: Circle circle, Rectangle rectangle
    * Retorno: boolean
    *************************************************************** */
    private boolean calculateDistance(Circle circle, Rectangle rectangle) {
        double circleX = circle.getTranslateX() + circle.getCenterX();
        double circleY = circle.getTranslateY() + circle.getCenterY();
    
        double rectX = rectangle.getTranslateX() + rectangle.getWidth() / 2;
        double rectY = rectangle.getTranslateY() + rectangle.getHeight() / 2;
    
        double dx = Math.abs(circleX - rectX) - rectangle.getWidth() / 2;
        double dy = Math.abs(circleY - rectY) - rectangle.getHeight() / 2;

        if(((dx * dx + dy * dy) <= circle.getRadius() * circle.getRadius() / 3) == true){
            if(!normal){
                principal.kill(rectangle);
                principal.score += 200;
            } // fim do if
            return true;
        } else {
            return false;
        } // fim do else
        
        
    } // fim do metodo calculateDistance

    /* ***************************************************************
    * Metodo: checkRectangleDistance
    * Funcao: verifica a distancia entre os retangulos
    * Parametros: Rectangle rect1, Rectangle rect2, String name1, String name2
    * Retorno: void
    *************************************************************** */
    public void checkRectangleDistance(Rectangle rect1, Rectangle rect2, String name1, String name2) {
        double centerX1 = rect1.getTranslateX() + rect1.getWidth() / 2;
        double centerY1 = rect1.getTranslateY() + rect1.getHeight() / 2;

        double centerX2 = rect2.getTranslateX() + rect2.getWidth() / 2;
        double centerY2 = rect2.getTranslateY() + rect2.getHeight() / 2;
        
        double distance = Math.sqrt(Math.pow(centerX2 - centerX1, 2) + Math.pow(centerY2 - centerY1, 2));
        double sumOfRadii = (rect1.getWidth() / 2) + (rect2.getWidth() / 2);

        
        if (distance < sumOfRadii) {
            System.out.println("Conflito entre os retangulos!" + name1 + " " + rect1.getTranslateX() + " "
                    + rect1.getTranslateY() + " " + name2 + " " + rect2.getTranslateX() + " " + rect2.getTranslateY());

        } else {
            
        } // fim do else
    } // fim do metodo checkRectangleDistance

    /* ***************************************************************
    * Metodo: setInterrupt
    * Funcao: interrompe os timers
    * Parametros: sem parametros
    * Retorno: void
    *************************************************************** */
    public void setInterrupt() {
        if (collisionTimer != null) {
            collisionTimer.stop();
        } // fim do if

        if (collisionTimer2 != null) {
            collisionTimer2.stop();

        } // fim do if

        if (timer != null) {
            timer.stop();
        } // fim do if

    } // fim do metodo setInterrupt

    /* ***************************************************************
    * Metodo: setGameStart
    * Funcao: inicia o jogo
    * Parametros: sem parametros
    * Retorno: void
    *************************************************************** */
    public void setGameStart() {
        setPacmanMovement(root, pacman);
        checkCollisions();
    } // fim do metodo setGameStart

    /* ***************************************************************
    * Metodo: startEnabled
    * Funcao: habilita o botao start
    * Parametros: sem parametros
    * Retorno: void
    *************************************************************** */
    public void startEnabled() {
        principal.start.setDisable(false);
    } // fim do metodo startEnabled


} // fim da classe ThreadInfo
