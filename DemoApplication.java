package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {


		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 6, 3,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3)
		);

		Callable<String> callable = new Callable() {
			@Override
			public Object call() throws Exception {
			//	System.out.println(1000);
				return "朱剑锋";

			}
		};

		Callable<String> callable1 = new Callable() {
			@Override
			public Object call() throws Exception {
				//	System.out.println(1000);
				return "zxsddsfdf";

			}
		};

		Future<String> future = threadPool.submit(callable);
		try{
			System.out.println(future.get());
		}catch (InterruptedException ie){
			ie.printStackTrace();
		}catch (ExecutionException ee){
			ee.printStackTrace();
		}

		FutureTask<String> futureTask = new FutureTask<String>(callable1);
		threadPool.submit(futureTask);
        try{
			System.out.println(futureTask.get());
		}catch (InterruptedException ie){
        	ie.printStackTrace();
		}catch (ExecutionException ee){
			ee.printStackTrace();
		}

		threadPool.shutdown();

		SpringApplication.run(DemoApplication.class, args);
	}

}
