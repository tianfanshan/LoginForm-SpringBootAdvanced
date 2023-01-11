package com.stf.service;

import com.stf.domain.User;
import com.stf.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    public User findById(long id);

    public User save(User user);

    public User login(String userName,String password);
}
