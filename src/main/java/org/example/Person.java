package org.example;
import java.util.regex.Pattern;

public class Person {
    private String name;
    private int age;
    //private  String dateOfBirth;
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

    public Person(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        //this.dateOfBirth = dateOfBirth;
        this.email = email;
        //validateDate();
        //validateEmail();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
        //получаем из года рождения
        //return 2024 - Integer.parseInt(dateOfBirth.substring(0, 4));
    }

//    public String getDateOfBirth() {
//        return dateOfBirth;
//    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setDateOfBirth(String dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//        //validateDate();
//    }


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
