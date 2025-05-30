/*
Increment a shared common counter using 2 threads. It should be incremented exactly 2000 times.
 */

package Multithreading;
import java.io.IOException;


public class SafeIncrement {

    static int cnt = 0;

    // remove sync and rerun to test
    public static synchronized void inc() {
        cnt++;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Runnable runnable = () -> {
            for(int i=0; i<1000;i++) {
                inc();
            }
        };

        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(cnt);  // 2000 when safe, 0-2000 otherwise (run 5 times to be sure)
    }
}
