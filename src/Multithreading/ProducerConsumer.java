package Multithreading;
import java.util.concurrent.*;

public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        Runnable producer = () -> {
            try {
                while (true) {
                    String item = "item-" + System.currentTimeMillis();
                    queue.put(item); // blocks if full
                    System.out.println("Produced: " + item);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                while (true) {
                    String item = queue.take(); // blocks if empty
                    System.out.println("Consumed: " + item);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
