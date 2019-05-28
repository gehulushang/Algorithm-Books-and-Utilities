package concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


@Slf4j
public class SemaphoreTest {
    private static int threadCount = 20;

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for(int i = 0;i<threadCount;i++){
            final int threadNum = i;
            exec.execute(() ->{
                try {
                    semaphore.acquire();    //获取许可
                    fun(threadNum);
                    semaphore.release();    //释放许可
                }catch (Exception e){
                    log.error("exception",e);
                }finally {

                }
            });
        }

        exec.shutdown();
    }
    private static void fun(int threadNum) throws Exception{

        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
