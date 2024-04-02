package com.socialmedia.Services;

import com.socialmedia.Repository.UserRepository;
import com.socialmedia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void sendPasswordResetEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user != null) {
            // Generate a unique token and save it to the user entity
            String resetToken = UUID.randomUUID().toString();
            user.setResetToken(resetToken);
            userRepository.save(user);

            // Send an email with the reset link
            String resetLink = "http://localhost:8080/auth/reset-password?token=" + resetToken;
            String subject = "Password Reset";
            String text = "To reset your password, click the link below:\n" + resetLink;

            sendSimpleMessage(userEmail, subject, text);
        }
    }

    public void resetPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user != null) {
            // Reset the password and clear the reset token
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null);
            userRepository.save(user);
        }
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }


}
