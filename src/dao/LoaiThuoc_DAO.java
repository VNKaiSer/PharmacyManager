package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiThuoc;

public class LoaiThuoc_DAO {
	public ArrayList<LoaiThuoc> getAllLoaiThuoc() {
		ArrayList<LoaiThuoc> dsLT = new ArrayList<LoaiThuoc>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from LoaiThuoc";
			Statement staement = con.createStatement();
			ResultSet resultSet = staement.executeQuery(sql);

			while (resultSet.next()) {
				int maLoai = resultSet.getInt("maLoai");
				String tenLoai = resultSet.getString("tenLoai");
				String moTa = resultSet.getString("moTa");
				LoaiThuoc lt = new LoaiThuoc(maLoai, tenLoai, moTa);
				dsLT.add(lt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsLT;

	}
	public LoaiThuoc getLoaiThuocTheoMa(int ma) {
		LoaiThuoc loai = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from LoaiThuoc Where maLoai = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, ma);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int maLoai = resultSet.getInt("maLoai");
				String tenLoai = resultSet.getString("tenLoai");
				String moTa = resultSet.getString("moTa");
				loai = new LoaiThuoc(maLoai, tenLoai, moTa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return loai;
	}
	
	public boolean themLoaiThuoc(LoaiThuoc loai) {
		int n=0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Insert LoaiThuoc(maLoai,tenLoai) Values (?, ?)";
			PreparedStatement staement = con.prepareStatement(sql);
			staement.setInt(1, loai.getMaLoai());
			staement.setString(2, loai.getTenLoai());
			n = staement.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
