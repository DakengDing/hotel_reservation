package service;

import model.Customer;

import java.util.*;


public class CustomerService {

    public Set<Customer> customerSet = new HashSet<Customer>();

    private static CustomerService customerService = null;
    private CustomerService() {}
    public static CustomerService getInstance(){
        if (null == customerService){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName){
        Customer newCustomer = new Customer(firstName,lastName,email);
        customerSet.add(newCustomer);


    }
    public Customer getCustomer(String customerEmail){

        for (Customer target: customerSet){
            if(customerEmail.equals(target.getEmail())){

                return target;
            }else {
            System.out.println("No customer information found!");
            }
        }
        return null;
    }
    public Collection<Customer> getAllCustomers(){
        return customerSet;


    }


}
