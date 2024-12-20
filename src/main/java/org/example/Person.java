package org.example;
import java.util.regex.Pattern;

public class Person {
    private String name;
    private  String dateOfBirth;
    private int age;
    private String email;

//    // метод проверки корректности записанной даты
//    private void validateDate(){
//        if (Pattern.matches("^\\d{2}-\\d{2}-\\d{4}$", this.dateOfBirth))
//            throw new IllegalArgumentException("Некорректный формат даты");
//    }

//    // метод проверки корректности почты
//    private void validateEmail(){
//        if (Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", this.email))
//            throw new IllegalArgumentException("Некорректный формат почты");
//    }

    public Person(String name, String dateOfBirth, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        // получаем возраст из даты (год в коце)
        this.age = 2024 - Integer.parseInt(dateOfBirth.substring(6, 10));
        this.email = email;
        //validateDate();
        //validateEmail();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        //validateDate();
    }


    public void setEmail(String email) {
        this.email = email;
        //validateEmail();
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age + " " + email;
    }
}
