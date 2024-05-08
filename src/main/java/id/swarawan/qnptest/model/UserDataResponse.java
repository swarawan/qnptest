package id.swarawan.qnptest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDataResponse {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @JsonProperty(value = "is_active")
    private Boolean active;

    @JsonProperty(value = "createdAt")
    private LocalDateTime createdAt;

    @JsonProperty(value = "updatedAt")
    private LocalDateTime updatedAt;
}
