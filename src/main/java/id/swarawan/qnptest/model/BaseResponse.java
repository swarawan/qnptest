package id.swarawan.qnptest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    @JsonProperty("success")
    private Boolean status = true;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;
}
