package id.swarawan.qnptest.controller;

import id.swarawan.qnptest.model.LoginResponse;
import id.swarawan.qnptest.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "login")
public class LoginController {

    private JwtService service;

    @Autowired
    public LoginController(JwtService service) {
        this.service = service;
    }

    @GetMapping("default")
    public ResponseEntity<LoginResponse> defaultLogin() {
        LoginResponse response = new LoginResponse();
        response.setAccessToken(service.generateToken());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
