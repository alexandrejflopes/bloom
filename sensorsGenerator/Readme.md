## Iniciar o kafka:

```
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

## Se não existirem os tópicos na máquina, criar:

```
bin/kafka-topics.sh --create --topic esp50-sensors-temperature --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic esp50-sensors-co2 --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --topic esp50-sensors-humidity --bootstrap-server localhost:9092

bin/kafka-topics.sh --create --topic esp50-sensors-actions --bootstrap-server localhost:9092

bin/kafka-topics.sh --create --topic esp50-alarms --bootstrap-server localhost:9092
```

## Consumer / Fazer listen dos tópicos:

```
bin/kafka-console-consumer.sh --topic esp50-sensors-temperature --bootstrap-server localhost:9092
bin/kafka-console-consumer.sh --topic esp50-sensors-co2 --bootstrap-server localhost:9092
bin/kafka-console-consumer.sh --topic esp50-sensors-humidity --bootstrap-server localhost:9092

bin/kafka-console-consumer.sh --topic esp50-sensors-actions --bootstrap-server localhost:9092

bin/kafka-console-consumer.sh --topic esp50-alarms --bootstrap-server localhost:9092
```

## Escrever no tópico da ação:

bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic esp50-sensors-actions

Mensagem: 0-Temperature-0-airConditioningOn
          0-Temperature-1-airConditioningOff
            ...
          2-Humidity-0-wateringOn
          2-Humidity-1-wateringOff
            ...

## Escrever no tópico dos alarmes:

bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic esp50-alarms

Mensagem: 0-Temperature-26-HIGH-0
          0-Temperature-24-LOW-1
            ...