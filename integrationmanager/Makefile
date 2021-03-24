default: run

run: compile
	java Frontend event_data.txt 

compile: Event.class  EventObject.class EventDataReaderInterface.class DataReaderImplementation.class 

test: testData testBackend testFrontend


testData: DataWranglerTests.class EventInterface.class Event.class EventDataReaderInterface.class DataReaderImplementation.class 
	@echo --- RUNNING DATA WRANGLER TESTS ---
	java DataWranglerTests



testBackend: EventInterface.class EventDataReaderInterface.class BackendInterface.class Event.class RedBlackTree.class BackendImplementation.class BackEndDeveloperTests.class DataReaderImplementation.class
	@echo --- RUNNING BACKEND TESTS ---
	java -jar junit5.jar -cp . --scan-classpath -n BackEndDeveloperTests






testFrontend: EventInterface.class EventDummy.class BackendInterface.class BackendDummy.class Frontend.class FrontEndDeveloperTests.class
	@echo --- RUNNING FRONTEND TESTS ---
	java -jar junit5.jar -cp . --scan-classpath -n FrontEndDeveloperTests





Event.class:Event.java
	javac Event.java


EventDataReaderInterface.class: EventDataReaderInterface.java
	javac EventDataReaderInterface.java


DataReaderImplementation.class: DataReaderImplementation.java
	javac DataReaderImplementation.java

DataWranglerTests.class: DataWranglerTests.java
	javac DataWranglerTests.java

EventInterface.class: EventInterface.java
	javac EventInterface.java

SortedCollectionInterface.class: SortedCollectionInterface.java
	javac SortedCollectionInterface.java

BackendInterface.class: BackendInterface.java
	javac BackendInterface.java

RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java

BackendImplementation.class: BackendImplementation.java
	javac BackendImplementation.java

BackEndDeveloperTests.class: BackEndDeveloperTests.java
	javac -cp .:junit5.jar BackEndDeveloperTests.java

EventDummy.class: EventDummy.java
	javac EventDummy.java

BackendDummy.class: BackendDummy.java
	javac BackendDummy.java

Frontend.class: Frontend.java
	javac Frontend.java

FrontEndDeveloperTests.class: FrontEndDeveloperTests.java
	javac -cp .:junit5.jar FrontEndDeveloperTests.java
