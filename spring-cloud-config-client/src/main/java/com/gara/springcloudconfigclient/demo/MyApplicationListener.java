package com.gara.springcloudconfigclient.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApplicationListener {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册监听器
        context.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            // 得到事件
            @Override
            public void onApplicationEvent(MyApplicationEvent applicationEvent) {
                System.out.println("接收到事件" + applicationEvent.getSource()+ " @ "+ applicationEvent.getApplicationContext());
            }
        });

        context.refresh();
        // 发布事件
        context.publishEvent(new MyApplicationEvent(context, "Hello World"));
        context.publishEvent(new MyApplicationEvent(context, 1));
        context.publishEvent(new MyApplicationEvent(context, new Integer(100)));


    }


    public static class MyApplicationEvent extends ApplicationEvent{

        private final ApplicationContext applicationContext;

        public MyApplicationEvent(ApplicationContext applicationContext,  Object source) {
            super(source);
            this.applicationContext = applicationContext;
        }

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }
    }

}

