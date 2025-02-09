cd  /data/code/fex-coin

git  pull
mvn clean install -DskipTests

cd buyer-api/

docker build -t buyer-api:v1 .

cd  ..
docker-compose up -d