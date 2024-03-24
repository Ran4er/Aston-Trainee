package ru.aston.model.otherClass;

/**
 * The Person class for checking MyArrayList. The class implements all the basic methods and variables
 * @author Ru6ik 
 */
public class Person implements Comparable<Person>{
    
    private Integer age;
    private String firstname;
    private String lastname;

    public Person(int age, String firstname, String lastname) {
        this.age = age;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * A method for obtaining a person's age
     * @return age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * A method to set the user's age
     * @param age the age we want to set for a person
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * A method to get a person's name
     * @return the name of the person
     */
    public String getFirstname() {
        return firstname;
    }
    
    /**
     * A method to get a person's name
     * @param name the name of the person that we want to identify to the person
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * A method for getting a person's last name
     * @return the last name of the person
     */
    public String getSurName() {
        return lastname;
    }

    /**
     * A method for setting a person's last name
     * @param surname the last name that we want to set for the person
     */
    public void setSurName(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname + " " + age;
    }

    @Override
    public int compareTo(Person arg0) {
        int result = this.age.compareTo(arg0.age);
        if(result == 0) {
            result = this.lastname.compareTo(arg0.lastname);
            if(result == 0) {
                result = this.firstname.compareTo(arg0.firstname);
            }
        }
        return result;
    }

}
