package id.swarawan.qnptest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDataRequest {

    @JsonProperty(value = "name")
    @NotNull(message = "Name cannot be null")
    private String name;

    @JsonProperty(value = "email")
    @NotNull(message = "Email cannot be null")
    private String email;

    @JsonProperty(value = "address")
    @NotNull(message = "Address cannot be null")
    private String address;

    @JsonProperty(value = "phone_number")
    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^[0-9]{5,20}+$")
    private String phoneNumber;

    @JsonProperty("active")
    private Boolean active = true;
}
