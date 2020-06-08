package api;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector {
    static String url ="jdbc:db2://dashdb-txn-sbox-yp-lon02-04.services.eu-gb.bluemix.net:50001/BLUDB:sslConnection=true;;";
    static String username = "fsf96983";
    static String password ="2lcn+8xjbkrdvg2p";



    //If url(image_url as named below) is used as a variable for the function the DB2 driver will misinterpret it as
    // the database url and throw an error
    public  int insert(String cov_ind, String normal_ind,
                       String pneumonia_ind, String result, String image_url) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;

        String sql = "INSERT INTO ADDRESSES(cov_ind, normal_ind, pneumonia_ind, result, url) "
                + "VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url,username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            // set parameters for statement
            pstmt.setString(1, cov_ind);
            pstmt.setString(2, normal_ind);
            pstmt.setString(3, pneumonia_ind);
            pstmt.setString(4, result);
            pstmt.setString(5, image_url);

            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1)
            {
                // get candidate id
                rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);

            }
            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if(rs != null)  rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }

        return candidateId;
    }

    //Returns all the entries in the table
    public static ArrayList<String> queryAll() throws SQLException, SQLException {
        ArrayList<String> comments = new ArrayList<String>();
        try
        {
            // the db2 driver string
            Class.forName("com.ibm.db2.jcc.DB2Driver");


            // get a db2 database connection
            Connection connection = DriverManager.getConnection(url,username, password);
            Statement stmt  = connection.createStatement();

            // now do whatever you want to do with the connection
            // ...
            String sql = "SELECT cov_ind, normal_ind, pneumonia_ind, result, url " +

                    "FROM ADDRESSES";

            ResultSet rs    = stmt.executeQuery(sql);


            while (rs.next()) {
                System.out.println(rs.getString("cov_ind") + "\t" +
                        rs.getString("normal_ind")  + "\t" +
                        rs.getString("pneumonia_ind")+rs.getString("url"));
                comments.add(rs.getString("cov_ind") +","+
                        rs.getString("normal_ind")   +","+
                        rs.getString("pneumonia_ind")+","+rs.getString("result")
                        +","+rs.getString("url"));

            }
            connection.close();

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(2);
        }
        return comments;

    }

}
