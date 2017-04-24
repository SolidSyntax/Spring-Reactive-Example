# Spring Reactive Example
A sample project to show the new reactive programming capabilities of Spring Boot 2.0.

### Technology used
 - Spring Boot 2.0.0.BUILD-SNAPSHOT  
    Depending on the snapshot build some features may still change!  
 - Reactive MongoDB drivers
 - Spring WebFlux
 - Oboe.js

### To run the example
 - Import the json file contained in src/main/resources/restaurants_collection.zip to a MongoDB database called 'test' in a collection named 'restaurants'.  
 - Run the server with 'gradle bootRun'

navigate to http://localhost:8000/index.html to view the sample website using Oboe.js

Or  

Use curl to fetch data from the server: 
```
curl -i -H "Accept: application/stream+json" --verbose http://localhost:8080/restaurants?cuisine=French  
```
Or

Use the WebFlux client in be.solidsyntax.reactive.client.WebClientApplication
