package org.example.juc.c_026_01_ThreadPool;

import java.util.concurrent.Executor;

/**
 * 认识Executor
 */
public class T01_MyExecutor implements Executor{

	public static void main(String[] args) {
		new T01_MyExecutor().execute(() -> System.out.println("execute....."));
	}

	@Override
	public void execute(Runnable command) {
		//new Thread(command).run();
		command.run();

	}

}

