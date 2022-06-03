# E-commerce
 E-commerce demostration project with an endpoint that returns a product rate based on a date, a product and a brand.
 
### Run the project

Make sure to be in the root directory

Clean and build the project, run the command:

```
mvnw clean package
```

This will also generate a jar file in target folder with all the dependencies which we will run once it has been created.

Go to the target folder to run the application

```
cd target
```

Run the application by running

```
java -jar demo-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the main method in Application.java in your chosen IDE

### Automated Test Execution

For the execution of the test, run the command:

```
mvnw clean test
```

### API Documentation

For API info: `http://locahost:8080/swagger-ui.html`

### BBDD initial data

#### Table: _PRICES_
| BRAND_ID | START_DATE | END_DATE | PRICE_LIST | PRODUCT_ID |PRIORITY| PRICE  | CURR   |
|------|------|----------|------|------|------|--------|--------|
|1|2020-06-14-00.00.00|2020-12-31-23.59.59|1|35455|0| 35.50  |EUR|
|1|2020-06-14-15.00.00|2020-06-14-18.30.00|2|35455|1| 25.45  |EUR|
|1|2020-06-15-00.00.00|2020-06-15-11.00.00|3|35455|1| 30.50  |EUR|
|1|2020-06-15-16.00.00|2020-12-31-23.59.59|4|35455|1| 38.95  |EUR|