
./gradlew clean
./gradlew build

echo '1_D.BFC7'

scp -r ./build/libs/allddaom-server-*.jar root@49.247.43.131:/home/ubuntu/allddaom/allddaom-server.jar
