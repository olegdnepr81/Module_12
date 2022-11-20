import java.util.concurrent.*;

public class HomeTask2 {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        MyProduser fizzProduser = new MyProduser() {
            int n;
            boolean updated = false;

            @Override
            public void setN(int n) {
                this.n = n;
                updated = true;
            }

            @Override
            public boolean isUpdated() {
                return updated;
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        if (updated) {
                            if (n % 3 == 0 && n%5!=0) {
                                queue.put("fizz");
                            }
                            updated = false;
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        MyProduser buzzProduser = new MyProduser() {


            int n;
            boolean updated = false;

            @Override
            public void setN(int n) {
                this.n = n;
                updated = true;
            }

            @Override
            public boolean isUpdated() {
                return updated;
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        if (updated) {
                            if (n % 5 == 0 && n%3 !=0) {
                                queue.put("buzz");
                            }
                            updated = false;
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        MyProduser numberProduser = new MyProduser() {


            int n;
            boolean updated = false;

            @Override
            public void setN(int n) {
                this.n = n;
                updated = true;
            }

            @Override
            public boolean isUpdated() {
                return updated;
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        if (updated) {
                            if (n % 5 != 0 && n%3 !=0) {
                                queue.put(String.valueOf(n));
                            }
                            updated = false;
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        MyProduser fizzBuzzProduser = new MyProduser() {


            int n;
            boolean updated = false;

            @Override
            public void setN(int n) {
                this.n = n;
                updated = true;
            }

            @Override
            public boolean isUpdated() {
                return updated;
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        if (updated) {
                            if (n % 5 == 0 & n%3 ==0) {
                                queue.put("fizzbuzz");
                            }
                            updated = false;
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable consumer = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (!queue.isEmpty()) {
                        System.out.println(queue.poll());
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(numberProduser);
        executor.execute(fizzProduser);
        executor.execute(buzzProduser);
        executor.execute(fizzBuzzProduser);
        executor.execute(consumer);
        for (int i = 1; i < 20; i++) {
            fizzProduser.setN(i);
            numberProduser.setN(i);
            buzzProduser.setN(i);
            fizzBuzzProduser.setN(i);
            while (fizzProduser.isUpdated() || numberProduser.isUpdated() || buzzProduser.isUpdated() || fizzBuzzProduser.isUpdated()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
