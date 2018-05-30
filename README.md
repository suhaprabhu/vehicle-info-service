Vehicle Info REST service
=========================

This project uses a new framework called (MicroNaut)[http://micronaut.io/] to create a REST micro service for listing config files from a directory. The example uses the directory `/tmp/vehicle-info`
Although a number of frameworks exist for creating a java based micro service, MicroNaut allows the services to be hosted in AWS lambda in a serverless architecture fashion.

## Run the service

```bash
cd vehicle-info-service
mkdir /tmp/vehicle-info
cp data/* /tmp/vehicle-info

./gradlew test
./gradlew run
```

## Test the service

### List all configs
http://localhost:8080/config

### Get the first config
http://localhost:8080/config/first

### Filter based on file extension
http://localhost:8080/config/filter/csv

### Download a file
http://localhost:8080/config/view/gu.xls

## About the project

## Entity classes

VehicleConfig is a POJO with fields describing a config file, extension, mime type and size

## Service

Service controller class (`InfoController`) uses reactive design patterns to improve scalability (Probably over kill for this simple project but is a good demonstration).

## Configuration

`application.yml` contains the configuration

