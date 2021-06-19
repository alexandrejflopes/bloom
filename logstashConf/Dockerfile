FROM docker.elastic.co/logstash/logstash:7.13.2
RUN rm -f /usr/share/logstash/pipeline/logstash.conf
COPY ./logstash.conf /usr/share/logstash/pipeline/logstash.conf
