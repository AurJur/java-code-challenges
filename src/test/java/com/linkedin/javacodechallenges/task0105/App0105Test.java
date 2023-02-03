package com.linkedin.javacodechallenges.task0105;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
class App0105Test {

    private final ByteArrayOutputStream printOut = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(printOut));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    void personAttributes_firstName() {
        Person person = new Person("Sally", "Mills", 24);
        assertEquals("Sally", person.getFirstName());
        person.setFirstName("Rebecca");
        assertEquals("Rebecca", person.getFirstName());
    }

    @Test
    void personAttributes_lastName() {
        Person person = new Person("Ann", "Johnson", 10);
        assertEquals("Johnson", person.getLastName());
        person.setLastName("Martin");
        assertEquals("Martin", person.getLastName());
    }

    @Test
    void personAttributes_age() {
        Person person = new Person("Nancy", "Moore", 89);
        assertEquals(89, person.getAge());
        person.setAge(90);
        assertEquals(90, person.getAge());
    }

    @Test
    void personAttributes_ageInvalid() {
        Person person = new Person("Marty", "Campbell", 40);
        assertEquals(40, person.getAge());
        person.setAge(30);
        assertEquals(40, person.getAge());
    }

    @Test
    void personAttributes_introduceYourself() {
        Person person = new Person("Ruby", "Wilson", 56);
        person.introduceYourself();
        assertEquals("Hi, my name is Ruby Wilson and I'm 56\n", printOut.toString());
    }
}
