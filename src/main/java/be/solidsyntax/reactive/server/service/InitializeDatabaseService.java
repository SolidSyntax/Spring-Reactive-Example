package be.solidsyntax.reactive.server.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipInputStream;

@Service
public class InitializeDatabaseService {
    private MongoTemplate mongoTemplate;

    @Autowired
    public InitializeDatabaseService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @PostConstruct
    public void initializeRestaurantCollection() {
        CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Starting async Restaurant import");
                InputStream zippedInputStream = new ClassPathResource("restaurants.zip").getInputStream();
                ZipInputStream zipStream = new ZipInputStream(zippedInputStream);
                zipStream.getNextEntry();

                Scanner sc = new Scanner(zipStream);
                while (sc.hasNextLine()) {
                    mongoTemplate.insert(Document.parse(sc.nextLine()), "restaurants");
                }
                System.out.println("Import finished");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
