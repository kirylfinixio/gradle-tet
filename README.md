# Test Kafka Consumer

1. make up
2. make kafka-test

This project demonstrates how to consume Kafka messages from the Platform. 

Platform Kafka is using TLS so before start the project you have to set the following environment variables:

**KAFKA_BOOTSTRAP_SERVERS** - list of kafka servers

**KAFKA_SSL_TRUSTSTORE_LOCATION** - location of your truststore.jks, e.g. file:./tls/truststore.jks if tls folder is located at the same level as src

**TRUSTSTORE_PASSWORD** - truststore password

**KAFKA_SSL_KEYSTORE_LOCATION** - location of your keystore.jks

**KEYSTORE_PASSWORD** - keystore password

**KEY_PASSWORD** - key password

**KAFKA_TOPIC** - the kafka topic which you want to consume

This test project won't commit offsets to the Kafka so once you start the project you will always start consuming kafka messages from the beginning of the KAFKA_TOPIC.