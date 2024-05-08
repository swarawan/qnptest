package id.swarawan.qnptest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JsonPlaceholderResponse {

    @JsonProperty(value = "total_time")
    private String totalTime;

    @JsonProperty(value = "users")
    private String users;

    @JsonProperty(value = "posts")
    private String posts;
}
