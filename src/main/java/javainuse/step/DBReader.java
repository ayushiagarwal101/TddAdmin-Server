package javainuse.step;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBReader {
    public ArrayList<UserList> searchUser(String firstname, String lastname) throws Exception, UnexpectedInputException,
            ParseException, NonTransientResourceException {
        ArrayList<UserList> userlist = new ArrayList<UserList>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@d-ebizdb-101.adesa.com:1521:ebizdev", "atlantis", "devint");
            String sql = "select first_name, last_name, login,status from ATLANTIS.ATL_User where first_name like '%" + firstname.trim()
                    + "%' and last_name like '%" + lastname.trim() + "%' FETCH FIRST 15 ROWS ONLY";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //Assuming you have a user object
                UserList user = new UserList();
                user.setFirstname(rs.getString("first_name"));
                user.setLastname(rs.getString("last_name"));
                user.setUsername(rs.getString("login"));
                int status = rs.getInt("status");
                String statusStr = "Inactive";
                if (status == 15659) {
                    statusStr = "Active";
                }
                user.setStatus(statusStr);
                user.setTitle("ADESA");
                userlist.add(user);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userlist;
    }

}