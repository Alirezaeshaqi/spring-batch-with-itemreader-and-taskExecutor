in this project i'm demonstrating you the most significatnt and interesting feat
ures of spring batch for building microservices based on springboot.the version
of springboot that i used in this project is 2.1.7.RELEASE


in this article I demonstrate how to use spring batch to run multithread and also multi-partitioned job.
the process starts with reading some lines from user tables.after parsing lines and extract important information,our application implmenets some business based on stored data and stores data into database.all off the processess are multi partioned,from reading to executing query and most fetches are based chunk size.



Prerequisites

In the most cases you need to have Maven and JDK8 and Mysql Server.



GETTING STARTED

you can get the source code and compile existing code or run jar file with the following commands:

mvn clean install 

java -jar --timestamp=product customer-transfer-job-0.0.1-SNAPSHOT.jar

and if you tend to run application with intellij idea you should start application with the following program argument :

  -Dtimestamp=product

Architecture


the job process consists of number of steps.at first we read some csv file using itemreader.after that,listener executes some methods to implement specific business.partitionig is one of the out-of-the-bx features of spring batch.we can partition input data based on desired field in input rows.al last in process section which is the most imortant one is responsible for business process and insert step.you can change the concurrency level in taskeExecutor method.

you need to provide your desired database and business plan and run create your own version of this application.
