package id.swarawan.qnptest.controller;

import id.swarawan.qnptest.model.BaseResponse;
import id.swarawan.qnptest.model.UserDataRequest;
import id.swarawan.qnptest.model.UserDataResponse;
import id.swarawan.qnptest.service.UserDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserDataController {

    private UserDataService service;

    @Autowired
    public UserDataController(UserDataService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDataResponse>> readAll() {
        List<UserDataResponse> responses = service.readAll();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDataResponse> readOne(@PathVariable("id") Long id) {
        UserDataResponse response = service.readOne(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDataResponse> create(@Valid @RequestBody UserDataRequest request) {
        UserDataResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<UserDataResponse> update(@PathVariable("id") Long id,
                                                   @Valid @RequestBody UserDataRequest request) {
        UserDataResponse response = service.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        service.delete(id);
        BaseResponse<Boolean> response = new BaseResponse<>();
        response.setMessage("Data deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
