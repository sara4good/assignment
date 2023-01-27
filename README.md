# abn-amro recipe assignment
This is the ABN-AMRO rcipe assignment.
</br>
The project  contains REST APIs in order to Create, Get, Update and Delete recipe from the database . 

###Objective Create a standalone java application which allows users to manage their favourite recipes. It should allow adding, updating, removing and fetching recipes. Additionally users should be able to filter available recipes based on one or more of the following criteria:
Whether or not the dish is vegetarian
The number of servings
Specific ingredients (either include or exclude)
Text search within the instructions. For example, the API should be able to handle the following search requests: • All vegetarian recipes • Recipes that can serve 4 persons and have “potatoes” as an ingredient • Recipes without “salmon” as an ingredient that has “oven” in the instructions.

## The tech stack 
* Java 17
* Spring boot
* Maven
## System Design
The project is implemented based on domain driven design . This service can be deployed independently on premise / cloud and can also be containerized to execute as docker containers. There are 4 layers from top to bottom:

API Layer
Top layer, which is main interface available for intgeration and interaction with front-end or end user to consume APIs
Contains endpoints implementation
Springboot-starter-web module used as a framework to implement ReSTful api end points
Service Layer
This layer sits in between API layer and Data access layer with some utility functionality
Mainly responsible for interacting with Data Access Layer and transferring the recipes data as required by top and below layers
It's just another module added to decouple business logic of recipes data transfer and mapping from/to API layer
Data Access Layer
Responsible to provide Object Relationship Mapping (ORM) between higher level recipe Java objects and persistence layer tables
Springboot-starter-data-JPA module is used to implement mappings between objects and tables
This layer contains recipe entity classes and JPA repositories 
Persistence Layer
Bottom most layer, responsible for physically storing the recipes data onto database table
Just one physical table - recipes is used to store the recipes data for the service
h2 is configured to be used as database service

how to run [To run](how_to_run.md)

to do  [To do](to_do.md)
