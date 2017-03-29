package be.solidsyntax.reactive.server.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurants")
public class Restaurant {

    private String name = "";
    private String cuisine = "";
    private double stars = 0.0;

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public double getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", stars=" + stars +
                '}';
    }
}
