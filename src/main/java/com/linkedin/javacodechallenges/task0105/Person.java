package com.linkedin.javacodechallenges.task0105;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
public class Person {

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        if (age >= this.age) {
            this.age = age;
        }
    }

    public void introduceYourself() {
        System.out.print("Hi, my name is " + firstName + " " + lastName + " and I'm " + age+"\n");
    }
}
