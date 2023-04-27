package org.sid.customerservice.web;


import org.sid.customerservice.dtos.CustomerDto;
import org.sid.customerservice.services.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/v1/customers")
public class CustomerRestController {
    Logger logger = LoggerFactory.getLogger(getClass());
    private final CustomerServiceImpl customerServiceImpl;

    public CustomerRestController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @RequestMapping(value = "/elk")
    public String helloWorld() {
        String response = "Welcome to ELK Demo RedMal" + new Date();
        logger.info(response);

        return response;
    }

    @RequestMapping(value = "/exception")
    public String exception() {
        String response = "";
        try {
            throw new Exception("Opps Exception raised....");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.valueOf(e));

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            logger.error("Exception - " + stackTrace);
            response = stackTrace;
        }

        return response;
    }


   @GetMapping()
    public ResponseEntity<List<CustomerDto>> getCustomers(){

        logger.info("[CustomerController] Get list of all Customers");
        List<CustomerDto> customerList = customerServiceImpl.findAll();

        if(customerList.isEmpty()) {
            logger.info("[CustomerController] List of Customer is empty");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        try {
            logger.info("get Customer by id");
            var customerDto = customerServiceImpl.findById(id);

            return new ResponseEntity<>(customerDto, HttpStatus.OK);

        } catch (Exception e) {

            logger.error("Error during getting customer by id : {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 /*
    @PostMapping()
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {

        try{
            logger.info("[CustomerController] Add new customer");

            customerRepository.saveAndFlush(customer);

            return ResponseEntity.status(HttpStatus.CREATED).body("The customer has been successfully created");

        } catch (Exception e){
            logger.error("[CustomerController] Error while adding customer: {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred during the creation of the Customer");

        }
    }



    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") long id) {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("[CustomerController] Error while deleting Customer: {} ", e.getMessage());
        }
    }

    */
}
