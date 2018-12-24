package com.yile.boot.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemaphoreExample1 {
	private final static int threadCount = 200;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		final Semaphore semaphore = new Semaphore(3);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() -> {
				try {
					semaphore.acquire();
					test(threadNum);
					semaphore.release();
				} catch (Exception e) {
					log.error("exception",e);
				}
			});
		}
		exec.shutdown();
	}
	
	private static void test(int threadNum) throws Exception{
		log.info("{}", threadNum);
		Thread.sleep(1000);
	}

}
