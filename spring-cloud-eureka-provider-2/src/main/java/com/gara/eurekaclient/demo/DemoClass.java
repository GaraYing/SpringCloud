package com.gara.eurekaclient.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-11-01 15:59
 * @Version: 1.0
 **/
public class DemoClass {

    private static ExecutorService executorTask;

    public DemoClass(ExecutorService executorTask) {
        this.executorTask = Executors.newSingleThreadScheduledExecutor(
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r, "Test");
                        thread.setDaemon(true);
                        return thread;
                    }
                }
        );
    }

    public static void main(String[] args) {
        new DemoClass(executorTask).count();
    }



    void count(){
        Long startTime = System.currentTimeMillis();
        int sum = 0;
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
//            Future<Integer> submit = executorTask.submit(() -> finalI);
//            Future<Integer> submit = executorTask.submit(() -> add(finalI, finalI + 1));
//            if (submit.isDone()){
//                this.add(finalI, finalI+1);
//            }


            tasks.add(new Task(finalI));
        }
        try {
            List<Future<Integer>> futureList = executorTask.invokeAll(tasks);
            futureList.forEach(Future::isDone);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("the total exec Time is " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("final total result is" + sum);
    }

    int add(int a, int b){
        System.out.println("加法运算==="+ (a+b));
        return a + b;
    }

    int multi(int a, int b){
        System.out.println("乘法运算===" + a*b);
        return a * b;
    }
}
