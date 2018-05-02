package com.mycompany.mavenproject1.business;

import com.mycompany.mavenproject1.dao.CustomerDAO;
import com.mycompany.mavenproject1.data.Customer;
import java.util.Set;

public class CustomerBusiness {
    
    private CustomerDAO dao;
    
    private double setCreditLimit (int age, String countryAcronym) {
        
        double creditLimit = 0;
        
        if (age > 35)
            creditLimit = 500;
        
        else if (age > 18)
            creditLimit = 300;
        
        else
            creditLimit = 100;
        
        
        if (countryAcronym.equalsIgnoreCase("BR"))
            creditLimit += 100;
        
        return creditLimit;
    }
    
    private boolean isPhoneValid (int phoneDigitsSize, int phoneDigitsCountry) throws Exception {
        if (phoneDigitsSize != phoneDigitsCountry) 
            throw new Exception ("The phone number is not valid!");
            
        else
            return true;
        
    }

    public CustomerBusiness() {
        dao = new CustomerDAO();
        
    }
    
    public void create(Customer customer) throws Exception {
        if (isPhoneValid(
                customer.getPhone().length(), 
                customer.getCountry().getDigits())
            ) {
            customer.setCreditLimit(
                    setCreditLimit(
                            customer.getAge(), 
                            customer.getCountry().getAcronym()
                    )
            );
            
            dao.create(customer);
        }
            
    }

    public Set<Customer> readAll() {
        return dao.readAll();

    }

}
