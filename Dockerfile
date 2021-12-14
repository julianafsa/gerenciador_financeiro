FROM openjdk

WORKDIR /app

COPY target/gerenciador_financeiro-0.0.1-SNAPSHOT.jar /app/gerenciador_financeiro.jar

ENTRYPOINT [ "java","-jar","gerenciador_financeiro.jar" ]
EXPOSE 8080