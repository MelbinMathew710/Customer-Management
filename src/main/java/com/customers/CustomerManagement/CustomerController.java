package com.customers.CustomerManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        try {
            Customer customer = CustomerTransformer.customerRequestDTOToCustomer(customerRequestDTO);
            Customer createdCustomer = customerService.createCustomer(customer);
            CustomerResponseDTO customerResponseDTO = CustomerTransformer.customerToCustomerResponseDTO(createdCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
        }
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDTO customerRequestDTO) {
        try {
            Customer customer = CustomerTransformer.customerRequestDTOToCustomer(customerRequestDTO);
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            CustomerResponseDTO customerResponseDTO = CustomerTransformer.customerToCustomerResponseDTO(updatedCustomer);
            return ResponseEntity.ok("Updated Customer Successfully");
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String search) {
        try {
            Page<Customer> customersPage = customerService.getAllCustomers(pageNo, pageSize, sortBy, search);
            List<Customer> customers = customersPage.getContent();

            List<CustomerResponseDTO> customerResponseDtos = new ArrayList<>();
            for(Customer customer : customers){
                CustomerResponseDTO customerResponseDTO = CustomerTransformer.customerToCustomerResponseDTO(customer);
                customerResponseDtos.add(customerResponseDTO);
            }

            return ResponseEntity.ok(customerResponseDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch customers");
        }
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            CustomerResponseDTO customerResponseDTO = CustomerTransformer.customerToCustomerResponseDTO(customer);
            return ResponseEntity.ok(customerResponseDTO);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Deleted Successfully");
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

