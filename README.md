<h1>Hiking Application</h1>

<h3><u><b>System Requirements:</b></u></h3>
- JDK 1.8+
- MongoDB 4.0+
- Maven 4.0+
- 2 GB RAM
- 10 GB HDD space

<h3><u><b>Steps to run application:</b></u></h3>
- Local machine:
    - Make sure you have mongodb running as a service on local and listening at port 27017. Details such as hostname/port number can be changed in the application.properties.
    - Verify other properties of application.properties file in order to access APIs.
    - run "mvn clean install"
    - run "mvn springboot:run"
- Using Docker to deploy on cloud:
    - run "mvn clean install" and copy the jar file or the complete folder into the cloud instance
    - sudo make run

- API information (required parameters such as requestbody and requestparams are mentioned in the swagger ui):
    - Details can be found on swagger API: http://{domain}:{port}/swagger-ui.html#/
        - For local environment: http://localhost:8080/swagger-ui.html#/
    1. View all the trails available for hiking: (HTTP GET)
        - http://localhost:8080/rest/trip/all
            - Returns List of trip objects in a json format. This will include tripId which is used in subsequent API calls.
    2. View a selected trail: (HTTP GET)
        - http://localhost:8080/rest/trip?tripId={tripId}
            - Returns trip object in json format.
    3. Book a selected trail for hiking: (HTTP POST)
        - http://localhost:8080/rest/order/book
            - Sample Request Body: <br><code>{
                                                                                    "hikers" :[{
                                                                                        "name" : "Ashish",
                                                                                        "age" : 18,
                                                                                        "email" : "test@email.com",
                                                                                        "contactNumber" : "9711043499"
                                                                                    }],
                                                                                    "orderValue" : 29.9,
                                                                                    "tripDetails" : {
                                                                                        "tripId": "60d3909ca5a3e5142b4d23c3",
                                                                                        "tripName": "Shire",
                                                                                        "startDt": "2021-06-23@19:28:39.791+0000",
                                                                                        "endDt": "2021-06-23@19:28:39.791+0000",
                                                                                        "minAge": 5,
                                                                                        "maxAge": 100,
                                                                                        "unitPrice": 29.9
                                                                                    }
                                                                                }</code> 
            - Expects Trip object to be sent with the request (Make sure you provide trip object stored and fetched from the above api)
    4. View a booking: (HTTP GET)
        - http://localhost:8080/rest/order?orderId={orderId}
            - Returns Order object in json format.
    5. Cancel a booking: (HTTP DELETE)
        - http://localhost:8080/rest/order/cancel?orderId={orderId}
            - Returns boolean status to notify details.
        

- Things couldn't do due to time constraint:
    - Environment profiling and fetching data based on it.
    - Test case coverage.
    - Making Makefile to shorten up the process.
    - Authentication mechanism (both API level and mongodb connection)
    - Pagination for certain APIs.
    - More comprehensive error handling mechanism

- Additionally, I have implemented:
    - Default caching solution provided by Springboot out of the box.
    - Basic Error handling mechanism.
    - Validation checks on received requests.
    - Swagger API
    - Scheduler to 
