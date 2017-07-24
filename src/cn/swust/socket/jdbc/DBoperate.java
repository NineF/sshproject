package cn.swust.socket.jdbc;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import cn.swust.domain.Humiture;
import cn.swust.domain.Humiture_1;
import cn.swust.domain.Location;
import cn.swust.util.StringUtil;

public class DBoperate {
	public static int insert(Location location) {
		Connection conn = DBHelper.getConn();
		int i = 0;
		String sql = "insert into location (locationname) values(?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, location.getLocationname());
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int insert(Humiture humiture, int location_id) {

		Connection conn = DBHelper.getConn();
		int i = 0;
		String sql = "insert into humiture (temperature,dampness,time,isOut,location_id) values(?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, humiture.getTemperature());
			pstmt.setString(2, humiture.getDampness());
			pstmt.setTimestamp(3, new Timestamp(Calendar.getInstance().getTime().getTime())); // 当前时间
			pstmt.setBoolean(4, humiture.getIsOut());
			pstmt.setInt(5, location_id);
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int insert(Humiture_1 humiture_1,String locationname) {

		Connection conn = DBHelper.getConn();
		String name="h_"+StringUtil.getPingYin(locationname);
		int i = 0;
		String sql = "insert into "+name+" (temperature,dampness,time,isOut) values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, humiture_1.getTemperature());
			pstmt.setString(2, humiture_1.getDampness());
			pstmt.setTimestamp(3, new Timestamp(Calendar.getInstance().getTime().getTime())); // 当前时间
			pstmt.setBoolean(4, humiture_1.getIsOut());
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public static int delete(String name) {
		Connection conn = DBHelper.getConn();
		int i = 0;
		String sql = "delete from location where locationname='" + name + "'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int update(Location location) {
		Connection conn = DBHelper.getConn();
		int i = 0;
		String sql = "update students set locationname='" + location.getLocationname() + "'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static ArrayList<Location> getAll() {
		ArrayList<Location> list = new ArrayList<>();
		Connection conn = DBHelper.getConn();
		String sql = "select * from location";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int col = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Location location = new Location();
				location.setLocationid(Integer.parseInt(rs.getString(1)));
				location.setLocationname(rs.getString(2));
				list.add(location);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int selectLocation(String locationname) {

		Connection conn = DBHelper.getConn();
		String sql = "select * from location where locationname='" + locationname + "'";
		PreparedStatement pstmt;
		// Location location=null;
		int i = -1;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			// int col = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				// location=new Location();
				i = Integer.parseInt(rs.getString("locationid"));
				// location.setLocationid(i);
				// location.setLocationname(locationname);
			}

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static void createlocationSql(String name) {
		Connection conn = DBHelper.getConn();
		Statement stmt;
		name="h_"+name;
		try {
			stmt = conn.createStatement();
			String sql = "CREATE TABLE "+name+" (humitureId int(11) auto_increment PRIMARY KEY,temperature CHAR(20),dampness CHAR(20),time TIMESTAMP,isOut bit)"  ;
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getTokenfromUserId(int userid){

		Connection conn = DBHelper.getConn();
		String sql = "select token from tokenBean where userid='" + userid + "'";
		PreparedStatement pstmt;
		String str = "";
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				str=rs.getString("token");
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static int update(String token) {
		Connection conn = DBHelper.getConn();
		int i = 0;
		String sql = "update tokenBean set token='" + token + "' where userid = '3'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
