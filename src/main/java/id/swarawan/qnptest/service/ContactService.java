package id.swarawan.qnptest.service;

import id.swarawan.qnptest.config.exception.DataExistFoundException;
import id.swarawan.qnptest.config.exception.DataNotFoundException;
import id.swarawan.qnptest.database.entity.Contacts;
import id.swarawan.qnptest.database.repository.ContactsRepository;
import id.swarawan.qnptest.model.ContactRequest;
import id.swarawan.qnptest.model.ContactResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private ContactsRepository repository;

    @Autowired
    public ContactService(ContactsRepository repository) {
        this.repository = repository;
    }

    public List<ContactResponse> readAll() {
        List<ContactResponse> contacts = repository.findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

        if (contacts.isEmpty()) {
            throw new DataNotFoundException("Empty contacts");
        }
        return contacts;
    }

    public ContactResponse readOne(@RequestParam("id") Long id) {
        return repository.findById(id).map(this::convert)
                .orElseThrow(() -> new DataNotFoundException("Contact not found"));
    }

    @Transactional
    public ContactResponse create(ContactRequest request) {
        Integer countExistContacts = repository.countByEmailOrPhoneNumber(request.getEmail(), request.getEmail());
        if (countExistContacts > 0) {
            throw new DataExistFoundException("Contact already exist");
        }

        Contacts contact = new Contacts();
        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setAddress(request.getAddress());
        contact.setPhoneNumber(request.getPhoneNumber());

        contact = repository.saveAndFlush(contact);
        return convert(contact);
    }

    public ContactResponse update(Long id, ContactRequest request) {
        Contacts contact = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Contact not found"));

        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setAddress(request.getAddress());
        contact.setPhoneNumber(request.getPhoneNumber());

        contact = repository.saveAndFlush(contact);
        return convert(contact);
    }

    public void delete(Long id) {
        Contacts contacts = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Contact not found"));
        repository.delete(contacts);
    }

    private ContactResponse convert(Contacts data) {
        ContactResponse result = new ContactResponse();
        result.setId(data.getId());
        result.setName(data.getName());
        result.setEmail(data.getEmail());
        result.setPhoneNumber(data.getPhoneNumber());
        result.setAddress(data.getAddress());

        result.setCreatedAt(data.getCreatedAt());
        result.setUpdatedAt(data.getUpdatedAt());
        return result;
    }
}
