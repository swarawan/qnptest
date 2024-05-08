package id.swarawan.qnptest.service;

import id.swarawan.qnptest.model.JsonPlaceholderResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class JsonPlaceholderService {

    private RestTemplateBuilder restTemplateBuilder;

    public JsonPlaceholderService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public JsonPlaceholderResponse getPlaceholder() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<String> urls = new ArrayList<>();
        urls.add("https://jsonplaceholder.org/users");
        urls.add("https://jsonplaceholder.org/posts");

        JsonPlaceholderResponse response = new JsonPlaceholderResponse();

        List<CompletableFuture<String>> completableFutures = urls.stream().map(this::getData).toList();
        completableFutures.stream().map(CompletableFuture::join)
                .forEach(content -> {
                    if (response.getUsers() == null) {
                        response.setUsers(content);
                    } else {
                        response.setPosts(content);
                    }
                });
        stopWatch.stop();
        long execTime = stopWatch.getTotalTimeMillis();
        response.setTotalTime(execTime + " ms");

        return response;
    }

    private CompletableFuture<String> getData(String url) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String body = restTemplate.getForEntity(url, String.class).getBody();
        return CompletableFuture.completedFuture(body);
    }
}
