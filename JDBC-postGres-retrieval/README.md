1) Install Eclipse
2) Download the JDBC Jar (Similar ot a python package or C++ STL)
3) Configure a New Project in Eclipse
4) Add the JDBC jar/Driver to the project (After creating a new project -> Right click on Project name>Properties -> Select Java Buildpath-> Add External Jars-> Enter the downloaded jar file path -> Ok
5) Write the program, basic logic would be to connect to the DB Server using the servername, username and password,  
Class.forName("org.postgresql.Driver");
Connection con = DriverManager.getConnection(
"jdbc:postgresql://localhost: 5432/DBNAME”, “postgres”,
“Password”);
Statement st = con.createStatement();
rs = st.executeQuery("select * from sales“);
rs is the resultset pointer which points to the first element of the resultset. rs.next would fetch the next and rs.prev would fetch the previous.
6) Close the connection to SQL DB.
7) Note that in ur Java project bin , the executables would have been compiled