package com.customers.CustomerManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = getCustomerById(id);
        if (existingCustomer != null) {
            customer.setId(id);
            return customerRepository.save(customer);
        }
        throw new CustomerNotFoundException("Customer not found with id: " + id);
    }

    public Page<Customer> getAllCustomers(Integer pageNo, Integer pageSize, String sortBy, String search) {
        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        if (search != null && !search.isEmpty()) {
            return customerRepository.findByFirstNameContainingOrLastNameContaining(search, search, paging);
        }
        return customerRepository.findAll(paging);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
