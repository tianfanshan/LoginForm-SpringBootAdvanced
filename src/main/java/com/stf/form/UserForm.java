package com.stf.form;

import com.stf.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

public class UserForm {

    public static final String PHONE_REG = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";

    @NotBlank
    private String userName;

    @NotBlank
    @Length(min = 6,max = 12,message = "密码最少需要6位，最大12位")
    private String password;

    @NotBlank
    @Pattern(regexp = PHONE_REG,message = "请输入正确的手机号")
    private String phone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String confirmPassword;

    public UserForm() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean confirm(){
        if (this.confirmPassword.equals(this.password)){
            return true;
        }
        return false;
    }

    public User convertToUser() {
        User user = new UserFormConverter().convert(this);
        return user;
    }

    private static class UserFormConverter implements FormConverter<UserForm, User> {
        @Override
        public User convert(UserForm userForm) {
            User user = new User();
            BeanUtils.copyProperties(userForm, user);
            return user;
        }
    }
}
