package com.company;
import java.sql.*;
public class PreparedStatement {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techprojdbc", "postgres", "Akincimehmet11@");
        Statement st = con.createStatement();


//        String sql1 = "CREATE TABLE companies (company_id numeric(9),company varchar(20), number_of_employees numeric(20))";
//        boolean result = st.execute(sql1);

        String sql2 = "update companies set number_of_employees = ? where company = ?";
        java.sql.PreparedStatement pst1 = con.prepareStatement(sql2);
        pst1.setInt(1,9999);
        pst1.setString(2,"IBM");
        int numOfUpdatedRecs = pst1.executeUpdate();
        System.out.println(numOfUpdatedRecs+ "rows updated");

        String sql3 = " select * from companies";
        ResultSet result2 =st.executeQuery(sql3);
        while(result2.next()){
            System.out.println(result2.getInt(1)+" - "+ result2.getString(2)+ " - "+ result2.getInt(3));
        }
        pst1.setInt(1,2222);
        pst1.setString(2,"GOOGLE");
        int numOfUpdatedRecs2 = pst1.executeUpdate();
        System.out.println(numOfUpdatedRecs2+ "rows updated");

        String sql4 = " select * from companies";
        ResultSet result3 =st.executeQuery(sql3);
        while(result3.next()){
            System.out.println(result3.getInt(1)+" - "+ result3.getString(2)+ " - "+ result3.getInt(3));
        }


        con.close();
        st.close();
        pst1.close();
        result2.close();
        result3.close();



    }
}
