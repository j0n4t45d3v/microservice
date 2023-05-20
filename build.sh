./mvnw clean package #comando para buildar todos os projetos
cd ./docker
docker-compose up --build -d #comando para subir todos os containers
docker-compose ps #comando para listar todos os containers
