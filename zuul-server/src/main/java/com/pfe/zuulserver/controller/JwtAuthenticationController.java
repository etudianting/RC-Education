package com.pfe.zuulserver.controller;

import com.pfe.zuulserver.beans.UserWithoutPassword;
import com.pfe.zuulserver.proxies.UsersProxy;
import com.pfe.zuulserver.security.*;
import com.pfe.zuulserver.security.JwtRequest;
import com.pfe.zuulserver.security.JwtResponse;
import com.pfe.zuulserver.security.JwtTokenUtil;
import com.pfe.zuulserver.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4455")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UsersProxy proxy;
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
        //System.out.println(authenticationRequest);
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        //System.out.println(userDetails.getUsername()+userDetails.getAuthorities());
        final String token = jwtTokenUtil.generateToken(userDetails);
        UserWithoutPassword user=proxy.findByUsernameOrEmail(authenticationRequest.getUsername())
                .mapToUserWithoutPassword();
        return ResponseEntity.ok(new JwtResponse(token,user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}