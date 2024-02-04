package com.customers.CustomerManagement;

public class CustomerTransformer {

    public static Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO) {
        Customer customer = Customer.builder()
                .firstName(customerRequestDTO.getFirstName())
                .lastName(customerRequestDTO.getLastName())
                .street(customerRequestDTO.getStreet())
                .address(customerRequestDTO.getAddress())
                .city(customerRequestDTO.getCity())
                .state(customerRequestDTO.getState())
                .email(customerRequestDTO.getEmail())
                .phone(customerRequestDTO.getPhone())
                .build();

        return customer;
    }

    public static CustomerResponseDTO customerToCustomerResponseDTO(Customer customer) {
        CustomerResponseDTO customerResponseDTO = CustomerResponseDTO.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .street(customer.getStreet())
                .address(customer.getAddress())
                .city(customer.getCity())
                .state(customer.getState())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();

        return customerResponseDTO;
    }

}
