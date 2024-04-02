package com.socialmedia.Services.impl;

import com.socialmedia.Exceptions.UserException;
import com.socialmedia.Repository.UserRepository;
import com.socialmedia.Services.UserService;
import com.socialmedia.config.JwtProvider;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());

        User save = userRepository.save(newUser);
        return save;
    }

    @Override
    public User findUserById(long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw  new UserException("USER NOT EXIST WITH USERID"+id);

    }

    @Override
    public User findUserByEmail(String email) {
        User byEmail = userRepository.findByEmail(email);
        return byEmail;
    }

    @Override
    public User followUser(long reqUserId, long id2)throws UserException {
        User reqUser=findUserById(reqUserId);
        User user2=findUserById(id2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowing().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;
    }

    @Override
    public User updateUser(User user, long id)throws UserException{
        Optional<User> user1 = userRepository.findById(id);
        if(user1.isEmpty()){
            throw  new UserException("USER NOT EXIST WITH USERID"+ " "+id);
        }
        User oldUser=user1.get();
        if(user.getFirstName()!=null){
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }
        User updatedUser=userRepository.save(oldUser);
        return updatedUser;

    }

    @Override
    public List<User> searchUser(String query) {
         return userRepository.searchUser(query);

    }

    @Override
    public User findUserByJwtToken(String jwt) {
        String email= JwtProvider.getEmailFromJwtToken(jwt);
        User user=userRepository.findByEmail(email);

        return user;
    }
}
