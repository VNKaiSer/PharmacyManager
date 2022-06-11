package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	public ArrayList<KhachHang> getAllCTHD() {
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from KhachHang";
			Statement staement = con.createStatement();
			ResultSet resultSet = staement.executeQuery(sql);

			while (resultSet.next()) {
				String SDT = resultSet.getString("SDT");
				String tenKhachHang = resultSet.getString("tenKhachHang");
				KhachHang kh = new KhachHang(SDT, tenKhachHang);
				dsKH.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKH;

	}

	public void themKhachHang(KhachHang kh) {
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO KhachHang\n" + "VALUES (?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, kh.getSdtKhachHang());
			statement.setString(2, kh.getTenKhachHang());

			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int kTKH(KhachHang kh) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "{? = call checkKhachHang(?) }";
		int result = 0;
		CallableStatement cstm = con.prepareCall(sql);
		cstm.registerOutParameter(1, java.sql.Types.INTEGER);
		cstm.setString(2, kh.getSdtKhachHang());

//	        try ( ResultSet rs = ;) {
//	            while (rs.next()) {
//	                result = rs.getBoolean(1);
//	            }
//	        }sz
		cstm.execute();
		result = cstm.getInt(1);
		return result;
	}
	
	public String getTenKH(String SDT)  {
		String tenKH = "";
		String sql = "SELECT tenKhachHang FROM [dbo].[KhachHang] WHERE sdtKhachHang = ?";
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement staement;
		try {
			staement = con.prepareCall(sql);
			staement.setString(1, SDT);
			ResultSet resultSet = staement.executeQuery();
			while (resultSet.next()) {
				tenKH = resultSet.getString("tenThuoc");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tenKH;
	}
}
