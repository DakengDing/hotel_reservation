package model;

import java.util.regex.Pattern;

public class Customer {
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String emailRegex = "^(.+)@(.+).(.+)$";
    private  Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstName,String lastName,String email){
        if (!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Error, Invalid email, please re-enter the email.");
        }
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return  firstName + " " + lastName + " and email is " + email;
    }

}
