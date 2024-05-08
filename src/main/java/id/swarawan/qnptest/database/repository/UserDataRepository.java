package id.swarawan.qnptest.database.repository;

import id.swarawan.qnptest.database.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    List<UserData> findAllByActive(Boolean active);
    Integer countByEmailOrPhoneNumber(String email, String phoneNumber);
}
