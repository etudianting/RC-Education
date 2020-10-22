package com.pfe.microserviceusers.controller;

import com.pfe.microserviceusers.exceptions.BadRequestException;
import com.pfe.microserviceusers.models.User;
import com.pfe.microserviceusers.models.token.PasswordRestToken;
import com.pfe.microserviceusers.repository.ResetPasswordRepository;
import com.pfe.microserviceusers.requests.ResetPasswordRequest;
import com.pfe.microserviceusers.service.EmailSenderService;
import com.pfe.microserviceusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    ResetPasswordRepository resetPasswordRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @RequestMapping("/change")
    public String reset(Model model, @RequestParam("token")String resetToken)
    {
        PasswordRestToken token = resetPasswordRepository.findByResetToken(resetToken);
        System.out.println(token);

        if(token != null && (token.getExpirationDate().after(new Date())))
        {
            token.getUser().getEmail();
            ResetPasswordRequest resetPasswordRequest =new ResetPasswordRequest();
            resetPasswordRequest.setEmail(token.getUser().getEmail());
            model.addAttribute("resetObject", resetPasswordRequest);
            return "reset";
        }
        return "error";
    }

    @PostMapping(value="/update")
    public String updatePassword(@ModelAttribute("resetObject") ResetPasswordRequest resetPasswordRequest)
    {
        System.out.println(resetPasswordRequest.getEmail());
        User user=userService.findByEmail(resetPasswordRequest.getEmail());
        if(user==null)
        {
            throw new BadRequestException("This user not exists");
        }
        String password=resetPasswordRequest.getNewPassword();
        String repassword=resetPasswordRequest.getConfirmNewPassword();
        System.out.println(repassword);
        if(!password.equals(repassword))
        {
            return "invalid";
        }
        user.setPassword(password);
        userService.saveUser(user);
        return "success";
    }
}
