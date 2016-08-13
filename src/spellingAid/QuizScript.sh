function testSelection(){
	if [ $(grep -c ^ $1) -ge 3 ];
	then
		test $1 3
	elif [ $(grep -c ^ $1) -eq 2 ];
	then
		test $1 2
	elif [ $(grep -c ^ $1) -eq 1 ];
	then
		test $1 1
	else
		echo "There are no words to use for that test"
		enterSelection
	fi
	
}

function test(){
	wordNumber=1
	for i in `shuf -n $2 $1`;
		do
			echo -n "Spell word $wordNumber of $2: " 
		addToListConditions "$i"
		
		wordNumber=$((wordNumber+1));
	done
	clear
}

function addToListConditions(){
	#$1 is the word
	sayWord $1
	declare -l currentWord
	read currentWord
	if [ "$currentWord" == "$1" ];
	then
		masteredList $1
			
	else
		echo -n '   Incorrect, try once more: ' 
		incorrectTryOnceMore $1
		sayWord $1
		read currentWord
		if [ "$currentWord" == "$1" ];
		then
			faultedList $1
		else
			failedList $1
		fi
	fi
}

testSelection $1
