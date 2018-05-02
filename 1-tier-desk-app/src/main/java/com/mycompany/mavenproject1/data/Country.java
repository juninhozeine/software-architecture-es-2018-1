package com.mycompany.mavenproject1.data;

import java.util.Objects;

public class Country {
    
    private int id;
    private String name;
    private String acronym;
    private int digits;

    public Country() {
    }
    
    public Country (int id, String name, String acronym, int digits) {
        this();
        
        this.setId(id);
        this.setName(name);
        this.setAcronym(acronym);
        this.setDigits(digits);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Country other = (Country) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Country{" + "name=" + name + ", acronym=" + acronym + ", digits=" + digits + '}';
    }

}
