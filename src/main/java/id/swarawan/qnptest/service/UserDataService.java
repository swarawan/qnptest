package id.swarawan.qnptest.service;

import id.swarawan.qnptest.config.exception.DataExistFoundException;
import id.swarawan.qnptest.config.exception.DataNotFoundException;
import id.swarawan.qnptest.database.entity.UserData;
import id.swarawan.qnptest.database.repository.UserDataRepository;
import id.swarawan.qnptest.model.UserDataRequest;
import id.swarawan.qnptest.model.UserDataResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDataService {

    private UserDataRepository repository;

    @Autowired
    public UserDataService(UserDataRepository repository) {
        this.repository = repository;
    }

    public List<UserDataResponse> readAll() {
        List<UserDataResponse> allUsers = repository.findAllByActive(true).stream().map(this::convert).collect(Collectors.toList());

        if (allUsers.isEmpty()) {
            throw new DataNotFoundException("Empty users");
        }
        return allUsers;
    }

    public UserDataResponse readOne(@RequestParam("id") Long id) {
        return repository.findById(id).map(this::convert).orElseThrow(() -> new DataNotFoundException("User not found"));
    }

    @Transactional
    public UserDataResponse create(UserDataRequest request) {
        Integer countExistUsers = repository.countByEmailOrPhoneNumber(request.getEmail(), request.getEmail());
        if (countExistUsers > 0) {
            throw new DataExistFoundException("User already exist");
        }

        UserData user = new UserData();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setActive(true);

        user = repository.saveAndFlush(user);
        return convert(user);
    }

    public UserDataResponse update(Long id, UserDataRequest request) {
        UserData user = repository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setActive(request.getActive());

        user = repository.saveAndFlush(user);
        return convert(user);
    }

    public void delete(Long id) {
        UserData userData = repository.findById(id).orElseThrow(() -> new DataNotFoundException("User not found"));
        repository.delete(userData);
    }

    private UserDataResponse convert(UserData data) {
        UserDataResponse result = new UserDataResponse();
        result.setId(data.getId());
        result.setName(data.getName());
        result.setEmail(data.getEmail());
        result.setPhoneNumber(data.getPhoneNumber());
        result.setAddress(data.getAddress());
        result.setActive(data.getActive());
        result.setCreatedAt(data.getCreatedAt());
        result.setUpdatedAt(data.getUpdatedAt());
        return result;
    }
}
