package be.solidsyntax.reactive.server.service;


import be.solidsyntax.reactive.server.model.Restaurant;
import be.solidsyntax.reactive.server.model.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

import static reactor.core.publisher.Mono.just;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Flux<Restaurant> restaurantsWithCuisine(String cuisine) {
        Flux<Restaurant> restaurantFlux =
                restaurantRepository.findByCuisineAndAndStarsGreaterThan(just(cuisine),just(4.5));

        //return restaurantFlux;
        return doSomethingSlow(restaurantFlux);
    }


    private Flux<Restaurant> doSomethingSlow(Flux<Restaurant> restaurantFlux) {
        Flux<Long> durationFlux = Flux.interval(Duration.ofMillis(5));
        return Flux.zip(restaurantFlux, durationFlux).map(Tuple2::getT1);
    }
}
