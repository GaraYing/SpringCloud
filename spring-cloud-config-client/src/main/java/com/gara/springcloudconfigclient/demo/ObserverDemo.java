package com.gara.springcloudconfigclient.demo;

import java.util.Observable;

/**
 * 观察者模式
 */
public class ObserverDemo {
    public static void main(String[] args) {

        // 发布者
        MyObservable observable = new MyObservable();

        // 添加订阅者
        observable.addObserver((o, value) -> {

            System.out.println(value);
            System.out.println(o.countObservers());
        });
        observable.addObserver((o,value) -> {
            int countObservers = o.countObservers();
            System.out.println(countObservers);
            System.out.println(value);
//            o.deleteObservers();

            System.out.println(o.countObservers());
        });

        // 表示observable发生改变
        observable.setChanged();

        // 发布者通知，订阅者是被动感知（推模式）
        observable.notifyObservers("Hello World");
    }

    public static class MyObservable extends Observable{
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }
    }
}
