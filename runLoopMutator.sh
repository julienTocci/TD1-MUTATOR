i=1;

cd src/main/java

function check {
	rm ../../../processorName.out
	for i in *;
	do
        echo "$i" | sed 's/.java//g' >> ../../../processorName.out
		
    done
	cat ../../../processorName.out

}

check

#name="Mutant""$i " 
#touch ../error_test.log
#tests_error=$(mvn package | grep "Tests run:")
#echo "$name""$tests_error" >> ../error_test.log