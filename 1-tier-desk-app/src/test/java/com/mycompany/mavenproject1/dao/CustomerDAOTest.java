package com.mycompany.mavenproject1.dao;

import com.mycompany.mavenproject1.data.Country;
import com.mycompany.mavenproject1.data.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

public class CustomerDAOTest {

    private CustomerDAO dao = new CustomerDAO();

    public Customer createEntity() {
        
//        CountryDAOTest daoTest = new CountryDAOTest();
//        daoTest.persistEntity();
        
        Country country = new CountryDAO().readByName("Brazil");        
        Customer c = new Customer();
        c.setAge(21);
        
        try {
            c.setCountry(country);
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        c.setCreditLimit(0);
        
        try {
            c.setName("Andrew");
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.setPhone("123");
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }

    public void persistEntity() {
        Customer c = createEntity();

        try {
            dao.create(c);

        } catch (Exception ex) {
            Logger.getLogger(CountryDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Test
//    public void create() {
//
//        persistEntity();
//
//         Assert.assertEquals(dao.readByName("Andrew").getName(), "Andrew");
//    }

//    @Test
//    public void read() {
//
//        Assert.assertEquals(dao.readByName("Andrew").getName(), "Andrew");
//    }
//
//    @Test
//    public void update() {
//
//        Customer c = dao.readByName("Andrew");
//        c.setAge(56);
//
//        try {
//            dao.update(c, "Andrew");
//            
//        } catch (Exception ex) {
//            Logger.getLogger(CustomerDAOTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        Assert.assertTrue(dao.readByName("Andrew").getAge() == 56);
//    }
//
//    @Test
//    public void delete() {
//        dao.delete("Andrew");
//
//        Assert.assertTrue(dao.readAll().size() == 0);
//    }
}
