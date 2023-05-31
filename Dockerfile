# Define a imagem base
FROM adoptopenjdk:17-jdk-hotspot

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos do projeto para o contêiner
COPY . /app

# Define as variáveis de ambiente para o banco de dados
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/registration
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=100senha

# Define as variáveis de ambiente para o e-mail
ENV SPRING_MAIL_HOST=localhost
ENV SPRING_MAIL_PORT=1025
ENV SPRING_MAIL_USERNAME=hello
ENV SPRING_MAIL_PASSWORD=hello
ENV SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true
ENV SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE=true
ENV SPRING_MAIL_PROPERTIES_MAIL_SMTP_CONNECTIONTIMEOUT=5000
ENV SPRING_MAIL_PROPERTIES_MAIL_SMTP_TIMEOUT=3000
ENV SPRING_MAIL_PROPERTIES_MAIL_SMTP_WRITETIMEOUT=5000

# Instala o PostgreSQL
RUN apt-get update && apt-get install -y postgresql

# Cria um novo usuário e banco de dados no PostgreSQL
RUN service postgresql start && \
    su - postgres -c "psql -c 'CREATE USER postgres WITH SUPERUSER PASSWORD '\''100senha'\'';'" && \
    su - postgres -c "psql -c 'CREATE DATABASE registration OWNER postgres;'"

# Executa o comando para iniciar a aplicação Spring Boot
CMD ["./mvnw", "spring-boot:run"]
