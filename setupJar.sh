mvn package
cp ./target/devops-1.0-SNAPSHOT.jar ../TD1-DEVOPS/src/main/resources/
cd ../TD1-DEVOPS/

i=1;
name="Mutant""$i " 
touch ../error_test.log
tests_error=$(mvn package | grep "Tests run:")
echo "$name""$tests_error" >> ../error_test.log