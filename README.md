Prerequisits
==============

1. Docker should be installed and running in the machine
2. Docker shall be logged in with docker hub account
2. JRE 8 or above & Maven should be installed in the machine with system variables being set


Steps
=================
1. Clone kakfaTrackingApp in your local machine
2. Open cmd prompt and navigate to this project folder
3. Run the cmd as below to set up the kafka and zookeeper servers 
   docker-compose -f docker-compose.yml up -d
4. Open another cmd prompt and Go to folder kafkaConsumer 
   cd /kafkaConsumer
5. Run the kafka consumer app 
   mvn spring-boot:run
6. Open another cmd prompt and Go to folder kafkaProducer
   cd /kafkaConsumer
7. Run the kafka producer app 
   mvn spring-boot:run


You can notice that as tracking data is published by producer from the test csv file, distance and time is calculated and displayed in consumer console.


