# Backend of auditing for SEGITTUR project

## The main goal of the project

Having a middle tier back-end application what is one part of an SEGITTUR project eco-system.
Where the main goal is auditing [Tractus-X EDC] https://github.com/eclipse-tractusx/tractusx-edc
main processes in [Control-plane] https://eclipse-edc.github.io/documentation/for-adopters/control-plane part
through web-api interfaces for:

- Tractus-X EDC Connector (upstream)
- front-end side (downstream)

# Technical details

## Developer guidelines

[Tractus-X EDC Development Documentation] https://github.com/eclipse-tractusx/tractusx-edc/blob/main/docs/development/README.md

## Build and run

`./gradlew build`

`docker compose up`

## Test

`curl -G http://localhost:8080/downstream/getBuildVersion`

# Open API documentation

http://<host>:8080/swagger-ui.html