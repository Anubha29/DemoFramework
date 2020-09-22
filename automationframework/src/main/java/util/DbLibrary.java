package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbLibrary {

    private static DbLibrary jdbc;

    //Default constructor
    private void JDBCSingleton() {
    }

    //Now we are providing global point of access.
    public static DbLibrary getInstance() {
        if (jdbc == null) {
            jdbc = new DbLibrary();

        }
        return jdbc;
    }

    // to get the connection from methods like insert, view etc.
    private static Connection getConnection(String dbType, String url, String... param) throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName(dbType);
        con = DriverManager.getConnection(url, param[0], param[1]);
        return con;
    }

    //to insert the record into the database
    public int insert(String[] connVar, String query) throws SQLException {
        Connection c = null;

        PreparedStatement ps = null;

        int recordCounter = 0;

        try {

            if (connVar.length > 2)
                c = this.getConnection(connVar[0], connVar[1], connVar[3], connVar[4]);
            else
                c = this.getConnection(connVar[0], connVar[1]);
            ps = c.prepareStatement(query);
            recordCounter = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return recordCounter;
    }

    //to view the data from the database
    public ArrayList<Object> view(String[] connVar, String query) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object> result = new ArrayList<Object>();

        try {
            if (connVar.length > 2)
                con = this.getConnection(connVar[0], connVar[1], connVar[3], connVar[4]);
            else
                con = this.getConnection(connVar[0], connVar[1]);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getObject(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return (ArrayList<Object>) result;
    }

    // to update the password for the given username
    public int update(String[] connVar, String query) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        int recordCounter = 0;
        try {
            if (connVar.length > 2)
                c = this.getConnection(connVar[0], connVar[1], connVar[3], connVar[4]);
            else
                c = this.getConnection(connVar[0], connVar[1]);
            ps = c.prepareStatement(query);
            recordCounter = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return recordCounter;
    }

    // to delete the data from the database
    public int delete(String[] connVar, String query) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        int recordCounter = 0;
        try {
            if (connVar.length > 2)
                c = this.getConnection(connVar[0], connVar[1], connVar[3], connVar[4]);
            else
                c = this.getConnection(connVar[0], connVar[1]);
            ps = c.prepareStatement(query);
            recordCounter = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return recordCounter;
    }
}
