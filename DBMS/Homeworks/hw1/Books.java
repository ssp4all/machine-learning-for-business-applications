// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all $USER$ with your unity id and $PASSWORD$ with your 9 digit student id or updated password (if changed)

import java.sql.*;

public class Books {

    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/$USER$";

    public static void main(String[] args) {
        try {

            // Load the driver. This creates an instance of the driver
	    // and calls the registerDriver method to make MariaDB Thin
	    // driver, available to clients.

      Class.forName("org.mariadb.jdbc.Driver");

	  String user = "$USER$";
    String passwd = "$PASSWORD$";

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {

		// Get a connection from the first driver in the
		// DriverManager list that recognizes the URL jdbcURL

		conn = DriverManager.getConnection(jdbcURL, user, passwd);

		// Create a statement object that will be sending your
		// SQL statements to the DBMS

		stmt = conn.createStatement();

		// Create the BOOKS table

		stmt.executeUpdate("CREATE TABLE BOOKS " +
			   "(BOOK_TITLE VARCHAR(32), ID INTEGER, " +
			   "PRICE FLOAT, AVAILABLE INTEGER)");

		// Populate the BOOKS table

		stmt.executeUpdate("INSERT INTO BOOKS " +
			   "VALUES ('All about DBMS', 17, 13.49, 5)");

		stmt.executeUpdate("INSERT INTO BOOKS " +
			   "VALUES ('Jack the Ripper', 13, 9.99, 1)");

		stmt.executeUpdate("INSERT INTO BOOKS " +
			   "VALUES ('Queen Lucia', 72, 5.99, 0)");

		stmt.executeUpdate("INSERT INTO BOOKS " +
			   "VALUES ('A Calendar of Sonnets', 101, 3.49, 15)");

		stmt.executeUpdate("INSERT INTO BOOKS " +
			   "VALUES ('Napoleon and Blucher', 5, 9.99, 0)");

            } finally {
                close(rs);
                close(stmt);
                close(conn);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
}
