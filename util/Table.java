package util;

import javafx.application.Platform;
import javafx.scene.image.Image;
import view.ScreenView;

public class Table extends Thread {
    
    static Semaphore mutex = new Semaphore(1);
    static Semaphore[] semaphores = new Semaphore[5];
    
    public static int[] state = new int[5];
    static Philosopher[] philosophers = new Philosopher[5];
    
    private String message;
    private String srcImg;
    
    public Table() {
        // inicializa todos estados para zero
        for (int i = 0; i < state.length; i++) {
            state[i] = 0;
        }

//        Thread.currentThread().setPriority(1);
        
        // inicializa todos filosofos
        philosophers[0] = new Philosopher("Micius", 0);
        philosophers[1] = new Philosopher("Han Fei", 1);
        philosophers[2] = new Philosopher("Lao Zi", 2);
        philosophers[3] = new Philosopher("Xunzi", 3);
        philosophers[4] = new Philosopher("Zhuangzi", 4);
        
        // saber quais garfos pertence aos filosofos
        for (int i = 0; i < philosophers.length; i++) {
            System.out.println("Garfo " + i + " - filosofo  " + i + " - garfo " + (i + 1) % 5);
        }
        
        for (int i = 0; i < semaphores.length; i++) {
            semaphores[i] = new Semaphore(0);
        }
        
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i].setDaemon(true);
            philosophers[i].start();
        }
    }
    
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (state[finalI] == 0) {
                            srcImg = "/img/thinking.jpg";
                            message = "THINKING";
                        } else if (state[finalI] == 1) {
                            srcImg = "/img/hungry.gif";
                            message = "HUNGRY";
                        } else if (state[finalI] == 2) {
                            srcImg = "/img/eating.png";
                            message = "EATING";
                        }
                        
                        Image statusImg = new Image(getClass().getResourceAsStream(srcImg));
                        
                        ScreenView.statusView[finalI].setImage(statusImg);
                        ScreenView.statusTxt[finalI].setText(message);
                    }
                });
            }
            try {
                Thread.sleep((int) (1000 - ScreenView.philosophersSld.getValue()));
//                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}
