package com.example.proiectpa.DBInteraction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
    public ResultSet GetQuestion(DBConnection dbConnection, String Qnumber) throws SQLException {

        String sql= "select * from intrebari where id='" + Qnumber +"'";
        Statement Query= dbConnection.getConn().createStatement();
        ResultSet result= Query.executeQuery(sql);
        System.out.println(result.getString("TEXT_INTREBARE"));
        return result;
    }

    public ResultSet GetAnswers(DBConnection dbConnection,String Qnumber) throws SQLException {

        String sql= "select * from RASPUNSURI where q_id='" + Qnumber +"'";
        Statement Query= dbConnection.getConn().createStatement();
        ResultSet result= Query.executeQuery(sql);
        return result;

    }
}
