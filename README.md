# InspirationVibe - Backend
Original motivational application based on REST API, allowing users to add various data that inspires them: motives, books, movies, games, TV shows. They could also try out third party API: to check compatibility in terms of potential partnership candidates using Love Calculator or get inspired by one of over 150 000 quotes.

APP is currently running on Heroku server, check it out at:
[https://inspirationvibe.herokuapp.com](https://inspirationvibe.herokuapp.com)

# General purposes:
Frontend was entirely created using Vaadin framework.

The project runs without errors, endpoints return real data, no additional configuration is required to start, the application is not a typical CRUD and there are no exceptions in the logs despite the application running. The frontend layer communicates only with the backend, does not call other APIs and does not perform unnecessary actions. There are more than 50 endpoints, 2 third party API, scheduler to get information regarding data storage, databases perform 10 different write operations, design patterns e.g.: facade at the backend, builder at the frontend, also used Gson, included Spring Actuator and tested API with Postman.

Both backend and frontend has been deployed to Heroku servers.

Known inconveniences: Heroku servers are taking about 15-20 seconds to start and running | third party API “Love Calculator” needs some time to start | third party API “150 000-quotes” was deleted from rapid.api

# Technology stack and tools:
**Back-End:**
- Database for deployment: PostgreSQL
- Database during development: H2
- Build automation tool: Gradle
- Programming language: Java 8
- Test coverage - target 77%
- Framework: Spring

**Front-End:** [repository](https://github.com/ganzes/eprojectkfrontend)
* UI Library: Vaadin

**Tools:**
* Version control system: GIT
* SonarCloud for code quality