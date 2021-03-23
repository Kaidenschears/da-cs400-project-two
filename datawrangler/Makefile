default: run

run: compile
	java DataWranglerTest

compile: Event.class  EventObject.class EventDataReaderInterface.class DataReaderImplementation.class DataWranglerTests.class

Event.class:Event.java
	javac Event.java

EventObject.class:EventObject.java
	javac EventObject.java

EventDataReaderInterface.class: EventDataReaderInterface.java
	javac EventDataReaderInterface.java

DataReaderImplementation.class: DataReaderImplementation.java
	javac DataReaderImplementation.java

DataWranglerTests.class: DataWranglerTests.java
	javac DataWranglerTests.java
