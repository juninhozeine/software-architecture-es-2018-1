package com.mycompany.mavenproject1.business;

import com.mycompany.mavenproject1.dao.CountryDAO;
import com.mycompany.mavenproject1.data.Country;
import java.util.Set;

public class CountryBusiness {
    
    private CountryDAO dao;

    public CountryBusiness() {
        dao = new CountryDAO();
        
    }
    
    public void create(Country country) throws Exception {
        dao.create(country);

    }

    public Set<Country> readAll() {
        return dao.readAll();

    }

}
