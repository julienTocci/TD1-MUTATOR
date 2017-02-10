mvn clean
mvn compile
mvn package
cp ./target/devops-1.0-SNAPSHOT.jar ../TD1-DEVOPS/src/main/resources/
cd ../TD1-DEVOPS/
mvn clean
mvn compile
mvn package