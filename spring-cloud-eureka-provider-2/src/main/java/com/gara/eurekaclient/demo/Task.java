package com.gara.eurekaclient.demo;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-11-01 17:17
 * @Version: 1.0
 **/
public class Task implements Callable<Integer> {

    private Integer a;

    public Task(Integer a) {
        this.a = a;
    }

    @Override
    public Integer call() throws Exception {
        Random r = new Random();
        long time = (r.nextInt(10) + 1) * 100;
        Thread.sleep(time);
        System.out.println("Task is on *****" + Thread.currentThread().getId());
        return r.nextInt() + a;
    }
}
