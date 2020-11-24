package com.gara.eurekaprovider;

import com.gara.eurekaprovider.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SpringBootApplication(scanBasePackages = "com.gara")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.gara")
@EnableHystrix
// 相当于@EnableHystrix + springCloud 相关功能
@EnableCircuitBreaker
@EnableEurekaClient
public class SpringCloudEurekaProvider {

    private static final Logger log = LoggerFactory.getLogger(SpringCloudEurekaProvider.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaProvider.class, args);
    }

    //    @Value("${server.port}")
//    String port;
//
//    @RequestMapping("/hi")
//    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
//        return "hi " + name + " ,i am from port:" + port;
//    }

    /**
     * 容器启动后回调
     * @param repository
     * @return
     */
//    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            // save a few customers
            Customer save = repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(save.getId().longValue());
            log.info("Customer found with findById():");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }

    private CustomerRepository repository;

    public SpringCloudEurekaProvider(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    void initData() {
        if (repository.count() >= 200) {
            repository.findAll().forEach(System.out::println);
            return;
        }
        for (int i = 0; i < 100; i++) {
            Customer customer = new Customer();
            customer.setFirstName("fn" + UUID.randomUUID().toString().substring(6, 10));
            customer.setLastName("ln" + UUID.randomUUID().toString().substring(0, 5));
            repository.save(customer);

            log.info("saved record: {}", customer);
        }

    }

}
