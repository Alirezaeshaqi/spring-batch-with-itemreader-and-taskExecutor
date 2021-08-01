<h2>summary</h2>

In this project I'm demonstrating you the most significatnt and interesting features of spring batch for building microservices based on springboot.The version of springboot that I used in this project is 2.1.7.RELEASE


In this article I demonstrate how to use spring batch to run multithread and also multi-partitioned job.
The process starts by reading some lines from user tables.after parsing lines and extract important information,our application implmenets some business based on stored data and stores data into database.All of the processess are multi-partioned,from reading to executing query and fetches are based on chunk size.

<h2> Prerequisites </h2>
You need to have Maven and JDK8 and Mysql Server.

<h3>GETTING STARTED</h3>

You can get the source code and compile existing code with following commands:

mvn clean install 

java -jar  customer-transfer-job-0.0.1-SNAPSHOT.jar

Or directly run jar file with the command above.
if you want to run application with intellij idea you should start application with the following program argument:

  --Dtimestamp=product

<h3>Architecture</h3>


The job process consist of multiple steps.at first we read some some data from database using itemreader.Listener executes some methods to implement specific business.Partitionig is out-of-the-bx feature of spring batch.It is possible to partition input data based on desired field in input rows.Finally,process section which is the most imortant one is responsible for business process and insert step in our web application.You can change the concurrency level in taskeExecutor method.

Provide your desired database and business plan and create your own version of this application.
