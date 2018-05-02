package com.mycompany.mavenproject1.dao;

import com.mycompany.mavenproject1.data.Country;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CountryDAOTest {

    private CountryDAO dao = new CountryDAO();

    public Country createEntity() {
        Country c = new Country();
        c.setName("Brazil");
        c.setAcronym("BR");
        c.setDigits(3);

        return c;
    }

    // @Before
    public void persistEntity() {
        Country c = createEntity();

        try {
            dao.create(c);

        } catch (Exception ex) {
            Logger.getLogger(CountryDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // @After
    public void deleteEntity() {
        dao.delete("Brazil");
        
    }

//    @Test
//    public void create() {
//        persistEntity();
//        
//        Assert.assertEquals("Brazil", dao.readByName("Brazil").getName());
//    }

//    @Test
//    public void read() {
//        Assert.assertTrue(dao.readAll().size() == 1);
//        Assert.assertEquals(dao.readByName("Brazil").getAcronym(), "BR");
//        Assert.assertEquals(dao.readByAcronym("BR").getName(), "Brazil");
//        Assert.assertEquals(dao.readById(1).getName(), "Brazil");
//    }
//
//    @Test
//    public void update() {
//        Country c = dao.readById(1);
//        c.setDigits(8);
//
//        dao.update(c, "Brazil");
//
//        Assert.assertTrue(dao.readByName("Brazil").getDigits() == 8);
//    }
//
//    @Test
//    public void delete() {
//        dao.delete("Brazil");
//
//        Assert.assertTrue(dao.readAll().size() == 0);
//    }
}
