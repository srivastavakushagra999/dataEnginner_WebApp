**Charging session Assessment**


**Intoduction**
This is the rest webservice build using the Spring boot framework.
It has Get,Getsummary,Post,Put methods as requested.

**Thead safety**

Concurrency is achieved with the help of concurrent hash Maps and synchronized methods 

**Test Cases**

Test cases are handling all the end points and checking all the functionality. 

Test case approach is 
Step 1) post a message
Step 2) Check for the get and get summary
Step 3) Issue PUT request on the generated id
Step 4) Recheck get and get summary

**How to use**
1) Build the jar in which is generated in target Folder
    i) java -jar dataengineer-0.0.1-SNAPSHOT.jar
2) Once your web service is running. Use Postman or any other tool to call services.
    
    i) Some Sample response used
    
    POST-:
    
    Sample request-:
    
    { 
       "stationId":"Amterdam"
    }
    
    GET-: http://localhost:8080/chargingSessions
    
    GET-Summary-: http://localhost:8080/chargingSessions/summary
    
    ** Resources**
     
     Spring course(https://www.udemy.com/share/101YoiAEIceFxbQ3o=/)