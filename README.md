# RatingSystem
Backend System to Rate a Ride by Passenger and Driver.

Assumption:
    Assuming Driver,Passenger already exists in system.
    
    Ride can have 2 states
        1.IN_PROGRESS
        2.COMPLETE
    Rating is done only for COMPLETE rides.

DataBase:

    There are 4 tables in database.
    ride : information ragarding ride such as driver_id,passenger_id,
    pickup and drop etc.
    passenger_rating : For some rideId passenger gets how much rating and remark from driver.
    driver_rating : For some rideId driver gets how much rating and remakr.
    aggregated_rating : Overall rating of passenger and driver.

Approach:
    
    For every complete ride driver and passenger gets some rating from each other.
    Based on these rating overall average is calculated.

Steps To Run Application

    Application running on Java8 and using Postgresql Database
    Download source code and make these changes in "application.properties" file.
   
    spring.datasource.username=databaseUser
    spring.datasource.password=Your Password
    
        

    
