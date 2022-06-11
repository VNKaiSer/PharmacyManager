package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhaCungCap;

public class NhaCungCap_DAO {
	public ArrayList<NhaCungCap> getAllNhaCC() {
		ArrayList<NhaCungCap> dsNCC = new ArrayList<NhaCungCap>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NhaCungCap";
			Statement staement = con.createStatement();
			ResultSet resultSet = staement.executeQuery(sql);

			while (resultSet.next()) {
				int maNCC = resultSet.getInt("maNCC");
				String ten = resultSet.getString("tenNCC");
				String loai = resultSet.getString("loaiNCC");
				String moTa = resultSet.getString("moTa");
				NhaCungCap nCC= new NhaCungCap(maNCC, ten, loai, moTa);
				dsNCC.add(nCC);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNCC;

	}
	public NhaCungCap getNhaCungCapTheoMa(int ma) {
		NhaCungCap nCC = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NhaCungCap Where maNCC = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, ma);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int maNCC = resultSet.getInt("maNCC");
				String ten = resultSet.getString("tenNCC");
				String loai = resultSet.getString("loaiNCC");
				String moTa = resultSet.getString("moTa");
				nCC = new NhaCungCap(maNCC, ten, loai, moTa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nCC;
	}
	
	public boolean themNCCThuoc(NhaCungCap nCC) {
		int n=0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Insert NhaCungCap(maNCC,tenNCC) Values (?, ?)";
			PreparedStatement staement = con.prepareStatement(sql);
			staement.setInt(1, nCC.getMaNCC());
			staement.setString(2, nCC.getTenNCC());
			n = staement.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
}
