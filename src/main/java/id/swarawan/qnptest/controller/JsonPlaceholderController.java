package id.swarawan.qnptest.controller;

import id.swarawan.qnptest.model.JsonPlaceholderResponse;
import id.swarawan.qnptest.service.JsonPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "json_placeholder")
public class JsonPlaceholderController {

    private JsonPlaceholderService service;

    @Autowired
    public JsonPlaceholderController(JsonPlaceholderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<JsonPlaceholderResponse> getData() {
        JsonPlaceholderResponse response = service.getPlaceholder();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
