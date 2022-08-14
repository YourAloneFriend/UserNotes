package com.application.model;

import java.util.Objects;
import java.util.regex.Pattern;

/***
 *   User module(class) with data fields and some methods.
 */
public class User {
    private String userName;
    private String email;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException{
        checkEmailValidation(email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws IllegalArgumentException{
        checkPasswordValidation(password);
        this.password = password;
    }

    private static void checkEmailValidation(String email) {
        if(Pattern.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email))
            throw new IllegalArgumentException("Email isn't valid!");
    }

    private static void checkPasswordValidation(String password) {
        if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,32}$", password))
            throw new IllegalArgumentException("Password isn't valid!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, password);
    }

    public static UserBuilder base(){return new UserBuilder();}

    public static class UserBuilder{
        private User user;

        private UserBuilder() {user = new User();}

        public UserBuilder userName(String userName){
            this.user.userName = userName;
            return this;
        }

        public UserBuilder email(String email){
            checkEmailValidation(email);
            this.user.email = email;
            return this;
        }

        public UserBuilder password(String password){
            checkPasswordValidation(password);
            this.user.password = password;
            return this;
        }

        public User build() {return user;}
    }
}
