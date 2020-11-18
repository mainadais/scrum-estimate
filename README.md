## Stepping through the project changes

Check out the individual branches to view the changes made through each step
```
master                    
modelling-entity-classes  
modelling-service-classes 
adding-graphql-frontend   
adding-subscription       
working-with-a-client     
```

### Getting started
- To get the database built and pre-populated with initial data, simply run
```
./gradlew clean build
```
- Start the application so that the server is ready for client requests

- When you run the application you can obtain a token for using the sample plain clients
```
com.dais.scrum.estimate.client.ScrumEstimateClientApp
```
- You can now merrily use the token with the any other client