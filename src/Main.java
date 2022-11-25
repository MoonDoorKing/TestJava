import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(); //병렬 작업 시 여러 개의 작업을 효율적으로 처리하기 위해 제공되는 JAVA 라이브러리이다.

        // 1번은 오래걸리는 작업이고 2번은 오래 걸리지 않는 작업이다. 두 작업사이의 연관성도 없는데 1번이 끝날때 까지 기다리고 2를 시작하는건 비효율적이다.
        System.out.println("1번 시작");
        Thread.sleep(1500);
        System.out.println("1번 종료");


        System.out.println("2번 시작");
        Thread.sleep(500);
        System.out.println("2번 종료");


        // 작업1 (스레드)
        executorService.submit(() -> {
            log("작업 1 시작");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log("작업 1 종료");
        });

        // 작업2
        log("작업 2 시작");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("작업 2 종료");

        executorService.shutdown();
    }

    // 출력을 어떤 스레드에서 하고 있는지 확인
    private static void log(String content) {
        System.out.println(Thread.currentThread().getName() + "> " + content);
    }
}
