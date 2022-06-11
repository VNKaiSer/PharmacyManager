package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_DAO {
	public ArrayList<HoaDon> getAllHoaDon() {
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from HoaDon";
			Statement staement = con.createStatement();
			ResultSet resultSet = staement.executeQuery(sql);

			while (resultSet.next()) {
				int maHD = resultSet.getInt("maHD");
				LocalDate ngayTT = null;
				String sdtKhachHang = resultSet.getString("sdtKhachHang");
				String username = resultSet.getString("username");
				NhanVien nv = new NhanVien(username);
				KhachHang kh = new KhachHang(sdtKhachHang);
				HoaDon hd = new HoaDon(maHD, ngayTT, nv, kh);
				dsHD.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHD;

	}

	public ResultSet getAllHoaDonTheoSDT(String sdt) {
		ResultSet resultSet = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select tenKhachHang,HD.maHD,hoTen,ngayThanhToan , SUM(soLuong * giaBan) "
					+ "	From HoaDon HD, ChiTietHD CT, KhachHang KH, NhanVien NV\r\n"
					+ "	Where HD.maHD = CT.maHD AND HD.sdtKhachHang = KH.sdtKhachHang "
					+ "		AND	NV.username = HD.username	AND KH.sdtKhachHang = ? "
					+ "	Group by  tenKhachHang,HD.maHD,hoTen,ngayThanhToan ";
			PreparedStatement staement = con.prepareStatement(sql);
			staement.setString(1, sdt);
			resultSet = staement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public ResultSet getAllHoaDonTheoSDT(String sdt, String sapXep) {
		ResultSet resultSet = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select tenKhachHang,HD.maHD,hoTen,ngayThanhToan , SUM(soLuong * giaBan) "
					+ "	From HoaDon HD, ChiTietHD CT, KhachHang KH, NhanVien NV\r\n"
					+ "	Where HD.maHD = CT.maHD AND HD.sdtKhachHang = KH.sdtKhachHang "
					+ "		AND	NV.username = HD.username	AND KH.sdtKhachHang = ? "
					+ "	Group by  tenKhachHang,HD.maHD,hoTen,ngayThanhToan " + "	Order by " + sapXep;
			PreparedStatement staement = con.prepareStatement(sql);
			staement.setString(1, sdt);
			resultSet = staement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public int getHoaDonTiepTheo() {
		int n = 0;
		try {

			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT TOP(1) [maHD] FROM [dbo].[HoaDon]\r\n" + "ORDER BY [maHD] DESC";
			Statement staement = con.createStatement();
			ResultSet rs = staement.executeQuery(sql);
			while (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return n + 1;
	}

	public void themHoaDon(HoaDon hd) {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "INSERT INTO HoaDon \r\n" + "VALUES(?,?,?,?)";
			PreparedStatement staement = con.prepareStatement(sql);
			staement.setInt(1, hd.getMaHD());
			staement.setString(2, hd.getKhachHang().getSdtKhachHang());
			staement.setString(3, hd.getNhanVien().getUsername());
			staement.setString(4, hd.getNgayThanhToan().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			staement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
