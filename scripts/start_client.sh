#!/bin/bash

cd ..

# Compile src/ files into class files located at bin/src
javac src/*.java
if [ $? -eq 0 ]
then
    printf "Successfully compiled source files\n"
else	
    exit 1
fi

# Start Client
printf "***** Client started *****\n"
xterm -fg white -bg black -hold -e "java src.Client" #2>/dev/null

sleep 1

# Clean up bin/
rm src/*.class

exit 0

