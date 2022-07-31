import java.sql.*;

public class CallableStatements02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techprojdbc", "postgres", "Akincimehmet11@");
        Statement st = con.createStatement();
        // create a procedure ti find the minimum one of 2 numbers

        String sql1 = "create or replace procedure findMinP( in a  int, in b int, out c  int) language plpgsql as $$  begin if a<b then c:=a; else c:=b; end if; end; $$;";
        st.execute(sql1);

        CallableStatement cst1 = con.prepareCall("{ call findMinP(?,?,?)}");

        cst1.setInt(1,5);
        cst1.setInt(2,7);
        cst1.registerOutParameter(3,Types.INTEGER);

        cst1.execute();
        System.out.println(cst1.getInt(3));


        con.close();
        st.close();
    }
}
