package id.swarawan.qnptest.controller;

import id.swarawan.qnptest.model.BaseResponse;
import id.swarawan.qnptest.model.ContactRequest;
import id.swarawan.qnptest.model.ContactResponse;
import id.swarawan.qnptest.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "contacts")
public class ContactController {

    private ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ContactResponse>> readAll() {
        List<ContactResponse> responses = service.readAll();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContactResponse> readOne(@PathVariable("id") Long id) {
        ContactResponse response = service.readOne(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContactResponse> create(@Valid @RequestBody ContactRequest request) {
        ContactResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ContactResponse> update(@PathVariable("id") Long id,
                                                  @Valid @RequestBody ContactRequest request) {
        ContactResponse response = service.update(id, request);
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
