package com.gara.eurekaprovider;

import com.gara.eurekaprovider.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);

//    List<Customer> findAll();


}
