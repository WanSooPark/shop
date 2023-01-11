
./gradlew clean
./gradlew build

scp -r ./mosazzi-api/build/libs/mosazzi-api-server-*.jar root@ec2-3-35-177-105.ap-northeast-2.compute.amazonaws.com:/home/ec2-user/mosazzi/mosazzi-api-server/mosazzi-api-server.jar
