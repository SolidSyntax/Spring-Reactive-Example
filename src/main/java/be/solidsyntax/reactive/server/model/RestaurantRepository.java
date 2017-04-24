package be.solidsyntax.reactive.server.model;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RestaurantRepository extends ReactiveCrudRepository<Restaurant,ObjectId> {

    Flux<Restaurant> findByCuisineAndAndStarsGreaterThan(Mono<String> cuisine, Mono<Double> stars);
}
