package com.socialmedia.Services;

import com.socialmedia.Exceptions.UserException;
import com.socialmedia.model.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);
    public User findUserById(long id) throws UserException;
    public User findUserByEmail(String email);
    public User followUser(long id1,long id2) throws UserException;
    public User updateUser(User user,long userId)throws UserException;
    public List<User> searchUser(String query);

    public User findUserByJwtToken(String jwt);


}
