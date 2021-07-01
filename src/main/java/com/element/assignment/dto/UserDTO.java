package com.element.assignment.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message = "Name is mandatory. Please provide valid name")
    private String name;

    @Min(value = 5,message = "User is not allowed as his/her age is less than 5.")
    private Integer age;

    private String contactNumber;

    @Email(message = "Not a valid email address.")
    private String email;

    public UserDTO() {
    }

    public UserDTO(@NotBlank(message = "Name is mandatory. Please provide valid name") String name, @Min(value = 5, message = "User is not allowed as his/her age is less than 5.") Integer age, String contactNumber, @Email(message = "Not a valid email address.") String email) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
