import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class ThreadPath extends Thread {
    private int i;
    private PathTransition color;
    private Path path;

    private Path pathCritical1;
    private Path pathNextCritical1;
    Principal p;

    public ThreadPath(int i, PathTransition color, Path path, Path pathCritical1, Path pathNextCritical1, Principal p) {
        this.i = i;
        this.color = color;
        this.path = path;
        this.pathCritical1 = pathCritical1;
        this.pathNextCritical1 = pathNextCritical1;
        this.p = p;
    }

    @Override
    public void run() {
        
        
        

        
        while (true) {
            
            if(color.getStatus() == PathTransition.Status.STOPPED){
                System.out.println("Thread " + i + " stopped " + color.getNode().getTranslateX() + " " + color.getNode().getTranslateY());
                //System.out.println("Thread " + i + " stopped");
                try {
                color.stop();
                p.semaphore.acquire();
                System.out.println("Thread " + i + " acquired");
                color.setPath(pathCritical1);
                color.play();

                while(true){
                    if(color.getStatus() == PathTransition.Status.STOPPED){
                        color.stop();
                        p.semaphore.release();
                        color.setPath(pathNextCritical1);
                        color.play();
                        break;
                    } else {
                        Thread.sleep(100);
                    }
                }

                while(true){
                    if(color.getStatus() == PathTransition.Status.STOPPED){
                        color.stop();
                        color.setPath(path);
                        color.play();
                        break;
                    } else {
                        Thread.sleep(100);
                    }
                }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        }
        
    }
}
