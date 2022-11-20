public class HomeTask {

    private static volatile long timeCounter = 0;

    private static synchronized void add(){
        timeCounter = timeCounter + 1;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            while (timeCounter<30) {
                add();
                System.out.println(timeCounter);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (timeCounter<30) {
                try {
                    Thread.sleep(5000);
                    System.out.println("Пройшло 5 секунд");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
