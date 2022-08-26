package com.application.model;

import java.util.Objects;
import java.util.regex.Pattern;

/***
 *   User module(class) with data fields and some methods.
 *   Fields:
 *      id - Integer, isn't null and unique.
 *      userName - String, isn't null.
 *      email - String, isn't null and unique.
 *      password - String, isn't null.
 */
public class User {
    private Integer id;
    private String userName;
    private String email;
    private String password;

    public Integer getId() { return id; }
    public String getUserName() { return userName; }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        checkUserNameValidation(userName);
        this.userName = userName;
    }
    public void setEmail(String email) throws IllegalArgumentException{
        checkEmailValidation(email);
        this.email = email;
    }
    public void setPassword(String password) throws IllegalArgumentException{
        checkPasswordValidation(password);
        this.password = password;
    }

    /**
     *  Check validation functions are to check whether set data is valid.
     *  They are for every User class fields.
     * */
    private static void checkUserNameValidation(String userName) throws IllegalArgumentException, NullPointerException{
        CheckDataNull.checkDataNull(userName, "Username");
        if(!Pattern.matches("^[a-zA-Z0-9_.-]{3,32}$", userName))
            throw new IllegalArgumentException("Username isn't valid! It should contain only that symbols(a-zA-Z0-9_.-) and be 3 more symbols length.");
    }
    private static void checkEmailValidation(String email) throws IllegalArgumentException, NullPointerException{
        CheckDataNull.checkDataNull(email, "Email");
        if(!Pattern.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email))
            throw new IllegalArgumentException("Email isn't valid! It must match the Internet mail standard.");
    }

    private static void checkPasswordValidation(String password) throws IllegalArgumentException, NullPointerException{
        CheckDataNull.checkDataNull(password, "Password");
        if(!Pattern.matches("^[a-zA-Z0-9\\p{Punct}]{8,64}$", password))
            throw new IllegalArgumentException("Password isn't valid! It should be with different symbols and 8 more symbols length.");
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

    /**
     * Realization of Builder pattern.
     * It's used for readable and convenient use when needs to create a User object.
     * */

    public static UserBuilder base(){return new UserBuilder();}

    public static class UserBuilder{
        private User user;

        private UserBuilder() {user = new User();}

        public UserBuilder id(Integer id) throws IllegalArgumentException, NullPointerException{
            this.user.id = id;
            return this;
        }

        public UserBuilder userName(String userName) throws IllegalArgumentException, NullPointerException{
            checkUserNameValidation(userName);
            this.user.userName = userName;
            return this;
        }

        public UserBuilder email(String email) throws IllegalArgumentException, NullPointerException{
            checkEmailValidation(email);
            this.user.email = email;
            return this;
        }

        public UserBuilder password(String password) throws IllegalArgumentException, NullPointerException{
            checkPasswordValidation(password);
            this.user.password = password;
            return this;
        }

        public UserBuilder checkPasswordsEquality(String password1, String password2) throws IllegalArgumentException, NullPointerException{
            CheckDataNull.checkDataNull(password1, "First password");
            CheckDataNull.checkDataNull(password2, "Last password");
            if(!password1.equals(password2))
                throw new IllegalArgumentException("Passwords aren't equal!");
            return this;
        }

        public User build() {return user;}
    }
}
