## Iniciar o kafka:

```
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

## Se não existirem os tópicos na máquina, criar:

```
bin/kafka-topics.sh --create --topic sensors-temperature --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic sensors-co2 --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic sensors-humidity --bootstrap-server localhost:9092

bin/kafka-topics.sh --create --topic sensors-actions --bootstrap-server localhost:9092
```

## Consumer / Fazer listen dos tópicos:

```
bin/kafka-console-consumer.sh --topic sensors-temperature --bootstrap-server localhost:9092
bin/kafka-console-consumer.sh --topic sensors-co2 --bootstrap-server localhost:9092
bin/kafka-console-consumer.sh --topic sensors-humidity --bootstrap-server localhost:9092

bin/kafka-console-consumer.sh --topic sensors-actions --bootstrap-server localhost:9092
```

## Escrever no tópico da ação:

bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic sensors-actions

Mensagem: {"sensorId":"0", "sensorType":"Temperature", "timestamp":"0", "action":"airConditioningOn"}
          {"sensorId":"0", "sensorType":"Temperature", "timestamp":"0", "action":"airConditioningOff"}
            ...

          {"sensorId":"1", "sensorType":"Humidity", "timestamp":"0", "action":"wateringOn"}
          {"sensorId":"1", "sensorType":"Humidity", "timestamp":"0", "action":"wateringOff"}
            ...