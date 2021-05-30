CRIAR BASE DE DADOS:

influxDB.createDatabase("esp50sensors");
influxDB.createRetentionPolicy("defaultPolicy", "esp50sensors", "30d", 1, true);

ou pelo terminal


É necessário:
 - ter o kafka ligado e os topicos criados
 - sensorsGenerator a executar