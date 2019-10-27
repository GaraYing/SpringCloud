## spring 核心

1. `ApplicationEvent`

2. `ApplicationListener`

3. `ConfigFileApplicationListener`

4. `BootstraptApplicationListener`  
    1). 负责加载`bootstrapt.properties` and `application.yml`;     
    2). 负责初始化Bootstrap ApplicationContext ID = 'bootstrap';   
    3) `BootstraptApplicationListener` 优先级高于`ConfigFileApplicationListener` (by `DEFAULT_ORDER`)
     
5. `ConfigurableApplicationContext`

6. `AbstractApplicationContext` onFresh()核心方法

## eureka client 
Eureka 客户端 `Eureka Client` 关联多个`Application`集合，一个应用`Application`关联多个实例`InstanceInfo`
Eureka 的应用信息获取的方式; 拉模式
Eureka 的应用信息上报的方式：推模式

### HttpClient 适配工厂 `ClientHttpRequestFactory`
    Spring 实现 `SimpleClientHttpRequestFactory`
    HttpClient 实现 `HttpComponentsClientHttpRequestFactory`
    OkHttp 实现 `OkHttp3ClientHttpRequestFactory`