# Backend Structure

Basic structure for our DPS backend to refer to as a resource. Implements a basic storage of exercises that consist of a title, a description, and a numeric ID.

## Workflow

This describes basic principles of interacting with the application.

### Setting up and running the application

To run the application, you need to have the Docker Engine installed, e.g. Docker Desktop. A local install of PostgreSQL is not required. After cloning this repository, the application is started by running ```docker-compose up```.

The command automatically does the following:

1. It checks whether there are existing images for the database and the application
2. It builds the images if they do not exist
3. It start two individual containers (```database-1``` and ```app-1```) and connects them via the ports specified in ```docker-compose.yml```, creating a composed container called ```dps-example-backend```
4. It initializes the database with two exemplary exercises that are defined in ```init-database/init.sql```. This only happens if the database does not yet exist.
5. It makes the application available on ```http://localhost:8080```, from where it can be queried

### Adapting the source code

When making changes to the source code, it is necessary to rebuild the project and the containers.

1. Adapt source code files in ```src/main/java```
2. Rebuild project using Gradle by running ```gradlew clean build```
3. If the Docker containers are running, stop them with ```Ctrl + C``` and disconnect them with ```docker-compose down```. If you run ```docker-compose down --volumes```, also the database entries will be deleted.
4. Restart and connect the containers with ```docker-compose up```

### Interacting with the database via psql

The database is run inside a docker container. To access it from outside, you must run ```psql``` in a terminal connected to the container, since otherwise you would be using a local instance of the database that does not reflect the actual data!

1. Obtain container name by running ```docker ps``` to list all running containers
2. Start a shell inside the container by running ```docker exec -it <container_name> bash```
3. Inside the container, run ```psql -U postgres -d postgres``` to access the database. From there, you can interact with it using SQL statements

## Important configuration files

These files are required for setting up the environment:

1. ```docker-compose.yml``` &rarr; Docker compose configuration, connection ports and database credentials
2. ```Dockerfile``` &rarr; build instructions for Docker
3. ```src/main/resources/application.properties``` &rarr; Spring application setup, must contain valid database credentials and package and build flags
4. ```build.gradle``` &rarr; Gradle configuration, package dependencies

## Architecture

Source code files are in ```src/main/java/backend```. Spring projects typically use the Controller-Service-Repository pattern:

```Controller``` &rarr; implements the REST API endpoints to handle user requests

```Service``` &rarr; implements business logic such as validity checks

```Repository``` &rarr; implements the connection to the database

The repository layer inherits from the standard ```JpaRepository``` class. It provides all central functionalities and most likely must not be extended.

## Querying the database

The database is queried via HTTP requests, e.g. by using ```curl```:

```GET``` &rarr; run ```curl http://localhost:8080/exercises``` to obtain a list of all saved exercises

```POST``` &rarr; run ```curl -X POST -H "Content-Type: application/json" -d "{\"title\":\"Crunches\", \"description\":\"Some description of the exercise.\"}" http://localhost:8080/exercises```

Caution: The ```POST``` request is very sensitive to letter case, double vs. single quotes, etc. Falsely formatted requests give vague error notifications.