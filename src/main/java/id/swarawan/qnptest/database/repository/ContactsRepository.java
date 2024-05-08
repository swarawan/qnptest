package id.swarawan.qnptest.database.repository;

import id.swarawan.qnptest.database.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    Integer countByEmailOrPhoneNumber(String email, String phoneNumber);
}
