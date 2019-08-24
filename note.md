## spring 核心

1. `ApplicationEvent`

2. `ApplicationListener`

3. `ConfigFileApplicationListener`

4. `BootstraptApplicationListener`  
    1). 负责加载`bootstrapt.properties` and `application.yml`;     
    2). 负责初始化Bootstrap ApplicationContext ID = 'bootstrap';   
    3) `BootstraptApplicationListener` 优先级高于`ConfigFileApplicationListener` (by `DEFAULT_ORDER`)
     
5. `ConfigurableApplicationContext`

5. `AbstractApplicationContext` onFresh()核心方法