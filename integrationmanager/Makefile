default: run

run: 

compile: Event.class  EventObject.class EventDataReaderInterface.class DataReaderImplementation.class 

test: testData testBackend testFrontend

testBackend: 



testFrontend: EventInterface.class EventDummy.class BackendInterface.class BackendDummy.class Frontend.class FrontEndDeveloperTests.class
	@echo --- RUNNING FRONTEND TESTS ---
	java FrontEndDeveloperTest.class

EventInterface.class: EventInterface.java
	javac EventInterface.java 
		
EventDummy.class: EventDummy.java
	javac EventDummy.java
	
BackendInterface.class: BackendInterface.java
	javac BackendInterface.java

BackendDummy.class: BackendDummy.java
	javac BackendDummy.java
	
Frontend.class: Frontend.java 
	javac Frontend.java 
	
FrontEndDeveloperTests.class: FrontEndDeveloperTests.java
	javac FrontEndDeveloperTests.java
	
testData: Event.class  EventObject.class EventDataReaderInterface.class DataReaderImplementation.class DataWranglerTests.class
	@echo --- RUNNING DATA WRANGLER TESTS ---
	java DataWranglerTest


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
