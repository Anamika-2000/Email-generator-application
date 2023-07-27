if [ -z $1 ]
then
    exit 0
fi

echo ' process start' 
chmod +x ./pre-requirement_installation_script.sh 
chmod +x ./build_script.sh

echo 'The Executable permissions are given to all the Scripts files.'

sh ./pre-requirement_installation_script.sh 

echo 'pre-requirement Script run Successfully'

BRANCH=$1

	if [ "BRANCH" = "main" -o "BRANCH" = "MAIN" ]; then
        	./build_script.sh main 
	else
        	./build_script.sh $BRANCH 
	fi