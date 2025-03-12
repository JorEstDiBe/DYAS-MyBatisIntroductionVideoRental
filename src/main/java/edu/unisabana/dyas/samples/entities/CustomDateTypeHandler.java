package edu.unisabana.dyas.samples.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class CustomDateTypeHandler extends BaseTypeHandler<java.sql.Date> {

    // Define the date format (only year, month, day)
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, java.sql.Date parameter, JdbcType jdbcType) throws SQLException {
        // Convert java.sql.Date to String (YYYY-MM-DD format)
        ps.setString(i, format.format(parameter));
    }

    @Override
    public java.sql.Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // Retrieve the date as a String and convert it to java.sql.Date
        String date = rs.getString(columnName);
        if (date != null) {
            try {
                Date utilDate = format.parse(date);  // Convert String to java.util.Date
                return new java.sql.Date(utilDate.getTime());  // Convert to java.sql.Date
            } catch (Exception e) {
                throw new SQLException("Unable to parse date: " + date, e);
            }
        }
        return null;
    }

    @Override
    public java.sql.Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // Retrieve the date as a String and convert it to java.sql.Date
        String date = rs.getString(columnIndex);
        if (date != null) {
            try {
                Date utilDate = format.parse(date);  // Convert String to java.util.Date
                return new java.sql.Date(utilDate.getTime());  // Convert to java.sql.Date
            } catch (Exception e) {
                throw new SQLException("Unable to parse date: " + date, e);
            }
        }
        return null;
    }

    @Override
    public java.sql.Date getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        // Retrieve the date as a String and convert it to java.sql.Date
        String date = cs.getString(columnIndex);
        if (date != null) {
            try {
                Date utilDate = format.parse(date);  // Convert String to java.util.Date
                return new java.sql.Date(utilDate.getTime());  // Convert to java.sql.Date
            } catch (Exception e) {
                throw new SQLException("Unable to parse date: " + date, e);
            }
        }
        return null;
    }
}