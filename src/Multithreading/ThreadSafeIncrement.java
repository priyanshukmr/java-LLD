package Multithreading;
import java.io.IOException;

class SharedResource {
    int cnt = 0;

    // remove sync and rerun to test
    public synchronized void inc() {
        cnt++;
    }
}


public class ThreadSafeIncrement {

    public static void main(String[] args) throws IOException, InterruptedException {
        SharedResource counter = new SharedResource();
        Runnable runnable = () -> {
            for(int i=0; i<1000;i++) {
                counter.inc();
            }
        };

        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(counter.cnt);  // 2000 when safe, 0-2000 otherwise
    }
}
