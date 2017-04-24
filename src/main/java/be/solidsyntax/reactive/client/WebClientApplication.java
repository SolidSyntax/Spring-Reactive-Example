package be.solidsyntax.reactive.client;


import be.solidsyntax.reactive.server.model.Restaurant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootApplication
public class WebClientApplication {
    private WebClient webClient = WebClient.create("http://localhost:8080");

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebClientApplication.class)
                .properties(Collections.singletonMap("server.port", "8081"))
                .run(args);
    }

    @Bean
    public CommandLineRunner webClient() {
        return args -> {
            System.out.println("Starting HTTP call....");
            webClient.get()
                    .uri(builder ->
                            builder.replacePath("/restaurants").
                                    queryParam("cuisine", "Belgian").build())
                    .accept(MediaType.APPLICATION_STREAM_JSON)
                    //.accept(MediaType.APPLICATION_JSON_UTF8)
                    //.accept(MediaType.TEXT_EVENT_STREAM)
                    .exchange()
                    .flatMapMany(response -> response.bodyToFlux(Restaurant.class))
                    .subscribe(restaurant -> System.out.println(restaurant.toString()));

        };
    }
}


