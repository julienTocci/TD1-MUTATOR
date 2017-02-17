./CreateMutators.sh

cd ../TD1-DEVOPS
function check {
	touch ../log.txt
	rm ../log.txt
	for i in ../TD1-MUTATOR/src/main/java/*;
	do
        processor=$(echo "$i" | sed 's:.*/::' | sed 's/.java//g')
		echo $processor >> ../log.txt
		mvn package -Dprocessor=$processor |  grep -e 'Skipped: [0-9]*$' >> ../log.txt
		echo -e "\n" >> ../log.txt
    done
	echo "################## RESULT ##################"
	echo ""
	cat ../log.txt

}

check

#name="Mutant""$i " 
#touch ../error_test.log
#tests_error=$(mvn package | grep "Tests run:")
#echo "$name""$tests_error" >> ../error_test.log