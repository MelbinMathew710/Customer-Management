package com.customers.CustomerManagement;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName, Pageable pageable);

    Page<Customer> findAll(Pageable paging);
}

