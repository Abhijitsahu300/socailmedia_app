package com.socialmedia.Controller;

import com.socialmedia.Repository.UserRepository;
import com.socialmedia.Response.AuthResponse;
import com.socialmedia.Services.CustomerUserDetailsService;
import com.socialmedia.Services.UserService;
import com.socialmedia.config.JwtProvider;
import com.socialmedia.model.User;
import com.socialmedia.requst.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;
     @Autowired
   private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{

        User isExist=userRepository.findByEmail(user.getEmail());

        if(isExist!=null){
            throw new Exception("email already used with another account");
        }

        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);
        Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        String token= JwtProvider.generateToken(authentication);

        AuthResponse res=new AuthResponse(token,"register success");
        return res;


    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){

        Authentication authenticate=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token= JwtProvider.generateToken(authenticate);

        AuthResponse res=new AuthResponse(token,"sign in succesfully");
        return res;

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails=customerUserDetailsService.loadUserByUsername(email);

        if (userDetails==null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("password not matched");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
