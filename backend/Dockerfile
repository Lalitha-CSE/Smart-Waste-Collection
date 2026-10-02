FROM eclipse-temurin:17-jdk

WORKDIR /app

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

RUN curl -L -o mysql-connector.jar \
    https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/9.4.0/mysql-connector-j-9.4.0.jar

COPY . .

RUN mkdir -p bin

RUN javac -cp "mysql-connector.jar" -d bin $(find src -name "*.java")

EXPOSE 8080

CMD ["sh", "-c", "java -cp 'bin:mysql-connector.jar' com.smartwaste.api.ApiServer"]