import java.util.concurrent.*;

public class HomeTask1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingDeque<>();
        Runnable produser = new Runnable() {
            int currTime;

            @Override
            public void run() {
                currTime++;
                try {
                    queue.put("notification" + currTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("currTime = " + currTime);
            }
        };
        Runnable consumer = new Runnable() {
            @Override
            public void run() {

                System.out.println("Пройшло 5 секунд");
                while (!queue.isEmpty()) {
                    System.out.println(queue.poll());
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(consumer, 5, 5, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(produser, 1, 1, TimeUnit.SECONDS);
        Thread.sleep(20000);
        executor.shutdown();
    }
}
