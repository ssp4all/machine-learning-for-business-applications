import java.sql.*;

public class GetConnection {
	// Initialize class variables to create a connection
        static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/$USERNAME";
        private static Connection connection = null;
        private static Statement statement = null;
        private static ResultSet result = null;
        public static Connection connection() {
              try{
			// This creates a connection and returns a connection object to the caller.
                	Class.forName("org.mariadb.jdbc.Driver");
                	String user = "$USERNAME";
                	String password = "$PASSWORD";
                	connection = DriverManager.getConnection(jdbcURL, user, password);
			System.out.println("now the onnection is estb");
                	statement = connection.createStatement();
                	connection.setCatalog("$USERNAME");
               		return connection;
               }catch(Exception e){ System.out.println(e);}
		return null;
       }
	static void close(Connection connection) {
               if(connection != null) {
                   try {
                       connection.close();
                   } catch(Throwable whatever) {}
                }
        }
        static void close(Statement statement) {
               if(statement != null) {
		   try{
                   statement.close();
                   } catch(Throwable whatever) {}
               }
        }
        static void close(ResultSet result) {
              if(result != null) {
                  try {
                  result.close();
              } catch(Throwable whatever) {}
              }
        }

}
