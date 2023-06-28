# Angular 11 + Spring Boot + PostgreSQL: Build CRUD example

For more details, please visit:
[https://bezkoder.com/angular-11-spring-boot-postgresql/](https://bezkoder.com/angular-11-spring-boot-postgresql/)

In this tutorial, we will learn how to build a full stack Angular 11 + Spring Boot + PostgreSQL example with a CRUD App. The back-end server uses Spring Boot with Spring Web MVC for REST Controller and Spring Data JPA for interacting with PostgreSQL database. Front-end side is made with Angular 11, HTTPClient & Router.

We will build a full-stack Tutorial Application in that:

- Each Tutorial has id, title, description, published status.
- We can create, retrieve, update, delete Tutorials.
- We can also find Tutorials by title.
- The images below shows screenshots of our System.

Add an object:

![angular-11-spring-boot-postgresql-example-crud-create-tutorial](angular-11-spring-boot-postgresql-example-crud-create-tutorial.png)

Retrieve all objects:

![angular-11-spring-boot-postgresql-example-crud-retrieve-all-tutorial](angular-11-spring-boot-postgresql-example-crud-retrieve-all-tutorial.png)

Click on **Edit** button to go to a Tutorial page:

![angular-11-spring-boot-postgresql-example-crud-retrieve-one-tutorial](angular-11-spring-boot-postgresql-example-crud-retrieve-one-tutorial.png)

On this Page, you can:

- change status to *Published* using **Publish** button
- delete the Tutorial using **Delete** button
- update the Tutorial details with **Update** button

![angular-11-spring-boot-postgresql-example-crud-update-tutorial](angular-11-spring-boot-postgresql-example-crud-update-tutorial.png)

Search Tutorials by title:

![angular-11-spring-boot-postgresql-example-crud-search-tutorial](angular-11-spring-boot-postgresql-example-crud-search-tutorial.png)

## Angular 11 & Spring Boot PostgreSQL CRUD Architecture
This is the application architecture we will build:

![angular-11-spring-boot-postgresql-example-crud-architecture](angular-11-spring-boot-postgresql-example-crud-architecture.png)

- Spring Boot exports REST Apis using Spring Web MVC & interacts with PostgreSQL Database using Spring Data JPA.
- Angular Client sends HTTP Requests and retrieve HTTP Responses using axios, shows data on the components. We also use Angular Router for navigating to pages.

