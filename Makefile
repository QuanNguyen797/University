JAVAC=javac
JAVA=java
SOURCES=$(wildcard *.java)

.PHONY: build test run clean

build:
	$(JAVAC) $(SOURCES)

test: build
	$(JAVA) -ea UniversityTest

run: build
	$(JAVA) University

clean:
	rm -f *.class
