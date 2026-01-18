package com.llb.controller;

import com.llb.entity.Customer;
import com.llb.repository.CustomerRepository;
import com.llb.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwt;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to corporate...!!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestBody Customer c) {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(c.getEmail(), c.getPassword());

        try {
            Authentication authenticate = authenticationManager.authenticate(token);

            if (authenticate.isAuthenticated()) {
                String jwtToken = jwt.generateToken(c.getEmail());
                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
            }

        } catch (Exception e) {
            //logger
        }

        return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/register")
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer){
        String encode = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encode);

        customerRepository.save(customer);

        return new ResponseEntity<>("Customer Registered", HttpStatus.CREATED);
    }

}

    /*@PostMapping("/login")
    public ResponseEntity<String> loginCheck(@RequestBody Customer customer){

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword());

        try {
            Authentication authenticate = authenticationManager.authenticate(token);

            if(authenticate.isAuthenticated()){
                // TODO : Generate JWT Token and send in response
                return new ResponseEntity<>("Logged in", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }*/