package com.springcrm.rest;

import com.springcrm.entity.Customer;
import com.springcrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId) {

        Customer currentCustomer = customerService.getCustomer(customerId);

        if(currentCustomer == null)
            throw new CustomerNotFoundException("Customer " + customerId + " not found!!");

        return currentCustomer;
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer cust){

        cust.setId(0);
        customerService.saveCustomer(cust);

        return cust;
    }

    @PutMapping("/customers/{customerId}")
    public Customer updateCustomer(@PathVariable int customerId, @RequestBody Customer updatedCustomer){

        Customer customer = customerService.getCustomer(customerId);

        customer.setEmail(updatedCustomer.getEmail());
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());

        customerService.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer updatedCustomer){
        customerService.saveCustomer(updatedCustomer);
        return updatedCustomer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId){

        Customer temp = customerService.getCustomer(customerId);

        if(temp == null)
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exists!!!");

        customerService.deleteCustomer(customerId);
        return "Deleted Customer - " + customerId;
    }
}
