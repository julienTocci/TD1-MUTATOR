i=1;
name="Mutant""$i " 
touch ../error_test.log
tests_error=$(mvn package | grep "Tests run:")
echo "$name""$tests_error" >> ../error_test.log