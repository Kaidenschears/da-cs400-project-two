default: run

run: compile
	java Frontend 

compile: Event.class  EventObject.class EventDataReaderInterface.class DataReaderImplementation.class 

test: testData testBackend testFrontend


testData: EventInterface.class Event.class EventDataReaderInterface.class DataReaderImplementation.class DataWranglerTests.class
	@echo --- RUNNING DATA WRANGLER TESTS ---
	java DataWranglerTest

EventInterface.class: EventInterface.java
	javac EventInterface.java 

Event.class:Event.java
	javac Event.java


EventDataReaderInterface.class: EventDataReaderInterface.java
	javac EventDataReaderInterface.java

DataReaderImplementation.class: DataReaderImplementation.java
	javac DataReaderImplementation.java


testBackend: EventInterface.class EventReaderInterface.class BackendInterface.class Event.class RedBlackTree.class BackendImplementation.class BackEndDeveloperTests.class
	@echo --- RUNNING BACKEND TESTS ---
	java BackEndDeveloperTest.java
	
SortedCollectionInterface.class: SortedCollectionInterface.java 
	javac SortedCollectionInterface.java

BackendInterface.class: BackendInterface.java
	javac BackendInterface.java

RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java

BackendImplementation.class: BackendImplementation.java
	javac BackendImplementation.java

BackEndDeveloperTests.class: BackEndDeveloperTests.java
	javac BackEndDeveloperTests.java





testFrontend: EventInterface.class EventDummy.class BackendInterface.class BackendDummy.class Frontend.class FrontEndDeveloperTests.class
	@echo --- RUNNING FRONTEND TESTS ---
	java FrontEndDeveloperTest.class

		
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
	
testData: EventInterface.class Event.class EventDataReaderInterface.class DataReaderImplementation.class DataWranglerTests.class
	@echo --- RUNNING DATA WRANGLER TESTS ---
	java DataWranglerTest

EventInterface.class: EventInterface.java
	javac EventInterface.java 

Event.class:Event.java
	javac Event.java


EventDataReaderInterface.class: EventDataReaderInterface.java
	javac EventDataReaderInterface.java

DataReaderImplementation.class: DataReaderImplementation.java
	javac DataReaderImplementation.java

DataWranglerTests.class: DataWranglerTests.java
	javac DataWranglerTests.java
