package com.ciel.provider.j8.thread;

import java.util.concurrent.*;

public class CallableTo  {  //有返回值,可以抛异常异常
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(17);
		
		Future<Integer> fi = pool.submit(new myCallable());
		
		System.out.println(fi.get());
	}
	
}
class myCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println(55);
		return 55;
	}
	
}