package com.pfe.microserviceusers.controller;

import com.pfe.microserviceusers.models.User;
import com.pfe.microserviceusers.models.token.ConfirmationToken;
import com.pfe.microserviceusers.repository.ConfirmationTokenRepository;
import com.pfe.microserviceusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confirmation")
public class ConfirmationEmailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        //log.info(token.getConfirmationToken());
        if(token != null)
        {
            User user = userService.findByUsernameOrEmail(token.getUser().getEmail(),token.getUser().getEmail());
            //log.info(user.getEmail());
            user.setEnabled(true);
            userService.updateUser(user);
            return "Congratulations! Your account has been activated and email is verified!";
        }
        return "The link is invalid or broken!";
    }
}
