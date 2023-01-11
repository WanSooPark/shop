
./gradlew clean
./gradlew build

echo '1_D.BFC7'

scp -r ./build/libs/shop-server-*.jar root@49.247.43.131:/home/ubuntu/shop/shop-server.jar
