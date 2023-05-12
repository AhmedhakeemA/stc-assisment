FROM postgres:latest

WORKDIR /app

COPY . .

EXPOSE 5432


ENV PGUSER=postgres
ENV POSTGRES_PASSWORD=123456789
ENV POSTGRES_DB=stc_assissment


RUN apt-get update && apt-get install -y postgresql-client

CMD ["postgres"]
