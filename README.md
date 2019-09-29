# Amaro
The project was built using spring boot.

Api documentation
http://localhost:8080/swagger-ui.html

## Structure ##
The project is composed by two modules vector and similar.
The modules have the structure below:

![structure](https://user-images.githubusercontent.com/9370679/65839080-4930d700-e2e0-11e9-86af-f1ce958d7cb9.png)

- Ui: Contains all the apis
- Use case: Contains business use cases that are responsible to orchestrate the domain
- Service: Small functions that represents business rules

The arrow shows the dependency between layers
