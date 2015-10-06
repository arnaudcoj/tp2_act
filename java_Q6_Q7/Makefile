
Main.class:SkyLine.class Building.class Main.java
	javac Main.java

%.class: %.java
	javac $^

$PHONY: clean execute

execute:Main.class
	java Main

clean:
	rm -rf *~ *.class *.svg
