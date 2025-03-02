# COMPILAÇÃO DO PROJETO
# -> Maven usado para compilar a aplicação Java, e JDK necessário para compilar a aplicação
FROM maven:3.8.1-openjdk-11-slim as build

# -> Copiar toddo o projeto para o diretório /app dentro do container
COPY . /app

# -> Definir o diretório de trabalho como /app
WORKDIR /app

# -> Compilar a aplicação e ignorar os testes
RUN mvn --quiet clean package -DskipTests

# EXECUÇÃO DA APLICAÇÃO
# -> A imagem otimizada que será usada em produção
FROM eclipse-temurin:11-jdk-alpine

# -> Criar o diretório /app
RUN mkdir -p /app

# -> Definir o diretório /app como diretório de trabalho
WORKDIR /app

# -> Copiar o JAR gerado na compilação da etapa anterior para o container
COPY --from=build /app/target/*.jar libraryApi.jar

# -> Expor a porta 8080 para acesso à aplicação
EXPOSE 8080

# -> Comando que o Docker vai usar para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "libraryApi.jar"]
