package app.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Radi pocetak za vrste osoba u booking sistemu
 *
 */

public abstract class Person {
    private static final Logger log = LoggerFactory.getLogger(Person.class);

    protected String name;
    protected String surname;
    protected BigDecimal age;

    protected Person() {
        // za JSON-B
    }

    protected Person(String name,String surname, BigDecimal age){
        this.name=name;
        this.surname=surname;
        this.age=age;

        log.info("Kreiranje objekta person: {} {} {}",name,surname,age);
    }

    public String getName(){
        log.info("Dohvacanje objekta person, name: {}",name);
        return name;
    }
    public String getSurname(){
        log.info("Dohvacanje objekta person, prezime: {}",surname);
        return surname;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public BigDecimal getAge(){
        log.info("Dohvacanje objekta person, godine: {}",age);
        return age;
    }
    public void setAge(BigDecimal age){
        this.age=age;
    }
    public abstract void isPerson();

}
