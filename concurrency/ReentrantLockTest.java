package concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
public class ReentrantLockTest {
    private final static Lock lock = new ReentrantLock();

     public static int clientCount = 5000;
     public static int threadTotal = 200;
     public static int count = 0;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        for(int i = 0;i<clientCount;i++){
            final int threadNum = i;
            exec.execute(() ->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("count: {}",count);
    }
    private static void add() {
       lock.lock();
       try{
           count++;

       }finally {
           lock.unlock();
       }
    }
}
