Prerequisits
==============

1. Docker should be installed and running in the machine
2. Docker shall be logged in with docker hub account
2. JRE 8 or above & Maven should be installed in the machine with system variables being set


Steps
=================
1. Clone kakfaTrackingApp in your local machine
2. Open cmd prompt and navigate to this project folder
3. Run the cmd as below to set up the kafka and zookeeper servers --> 
   docker-compose -f docker-compose.yml up -d
4. Open another cmd prompt and Go to folder kafkaConsumer, cmd --> 
   cd kafkaConsumer
5. Run the kafka consumer app, cmd --> mvn spring-boot:run
6. Open another cmd prompt and Go to folder kafkaProducer, cmd --> cd kafkaProducer
7. Run the kafka producer app, cmd --> mvn spring-boot:run

You can notice that as tracking data is published by producer from the test csv file, distance and time is calculated and displayed in consumer console.



Note - Do not the start the producer before consumer as kafka Topic is created through Consumer app, So first start the consumer app and then producer app

Alternative way to create kafka topic
======================================

Run below cmds in kafka server cmd window after kafka and zookeeper server are up


1. docker exec -it kafka /bin/sh
2. cd /opt/bitnami/kafka
3. ./bin/kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic tracker
4. ./bin/kafka-topics.sh --list --zookeeper zookeeper:2181