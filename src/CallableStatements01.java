import java.sql.*;

public class CallableStatements01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techprojdbc", "postgres", "Akincimehmet11@");
        Statement st = con.createStatement();
        // create function
        String sql1 = "create or replace function addf(a int, b int) returns int language plpgsql as $$ begin return a+b; end; $$;";
        // execute function
        boolean result1 = st.execute(sql1);
        System.out.println("execute(sql1) returns" + result1);
        //call function
        CallableStatement cst1 = con.prepareCall("{ ? = call addf(?,?)}");
        cst1.registerOutParameter(1,Types.INTEGER);
        cst1.setInt(2,50);
        cst1.setInt(3,100);
        // execute function
        cst1.execute();
        // see the result on the console
        System.out.println(cst1.getInt(1));



        con.close();
        st.close();
        cst1.close();
    }
}
