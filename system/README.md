# STC Assessment

Foobar is a Python library for dealing with word pluralization.

## Installation

To build docker image inside folder "DokerDB" there is Dockerfile that responsable for this and you need to run below command


```bash
docker build -t postgres-image .
```

To Build application as a docker you can find Dockerfile inside project esponsable for this and you need to run below command

```bash
docker build -t stc-assessment .
```

After previous steps you can make all containers up at by using file "docker-compose.yml" inside project

and you need to run below command

```bash
docker compose up

```

Note : please don't forget to build project 

## License

[MIT](https://choosealicense.com/licenses/mit/)