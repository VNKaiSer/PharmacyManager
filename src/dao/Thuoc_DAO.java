package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.Thuoc;
import java.sql.SQLException;
import java.util.Date;

import connectDB.ConnectDB;

public class Thuoc_DAO {
	private ConnectDB con = new ConnectDB();

	public Thuoc_DAO() throws SQLException {
		con.getInstance().connect();
	}

	public ArrayList<Thuoc> getAllThuoc() {
		ArrayList<Thuoc> dsT = new ArrayList<Thuoc>();
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Thuoc";
			Statement staement = con.createStatement();
			ResultSet resultSet = staement.executeQuery(sql);

			while (resultSet.next()) {
				int maThuoc = resultSet.getInt("maThuoc");
				String tenThuoc = resultSet.getString("tenThuoc");
				String hangSX = resultSet.getString("hangSX");
				String donVi = resultSet.getString("donVi");
				int soLuong = resultSet.getInt("soLuong");
				double giaBan = resultSet.getDouble("giaBan");
				String moTa = resultSet.getString("moTa");
				byte[] hinhAnh = resultSet.getBytes("hinhAnh");// resultSet.getByte("hinhAnh");
				LocalDate hanSD = null;
				int maLoai = resultSet.getInt("maLoai");
				int maNCC = resultSet.getInt("maNCC");
				LoaiThuoc loaiThuoc = new LoaiThuoc(maLoai);
				NhaCungCap nhaCungCap = new NhaCungCap(maNCC);
				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, hangSX, donVi, soLuong, giaBan, moTa, hinhAnh, hanSD,
						nhaCungCap, loaiThuoc);
				dsT.add(thuoc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsT;

	}

	public ArrayList<Thuoc> getThuocBanHang() throws SQLException {
		ArrayList<Thuoc> listThuoc = new ArrayList<>();
		ConnectDB.getInstance();
		String sql = "select * from Thuoc";
		Statement staement = ConnectDB.getConnection().createStatement();
		ResultSet resultSet = staement.executeQuery(sql);
		while (resultSet.next()) {
			int maThuoc = resultSet.getInt("maThuoc");
			String tenThuoc = resultSet.getString("tenThuoc");
			int soLuongTon = resultSet.getInt("soLuong");
			double giaBan = resultSet.getDouble("giaBan");
			String moTa = resultSet.getString("moTa");
			byte[] hinhAnh = resultSet.getBytes("hinhAnh");
			String donVi = resultSet.getString("donVi");

			Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, donVi, soLuongTon, giaBan, moTa, hinhAnh);
			listThuoc.add(thuoc);
		}
		return listThuoc;

	}

	public ArrayList<String> getTenThuoc() throws SQLException {
		ArrayList<String> listTenThuoc = new ArrayList<String>();
		String sql = "select * from Thuoc";
		Statement staement = con.getConnection().createStatement();
		ResultSet resultSet = staement.executeQuery(sql);
		while (resultSet.next()) {
			String tenThuoc = resultSet.getString("tenThuoc");
			listTenThuoc.add(tenThuoc);
		}
		return listTenThuoc;
	}

	public ArrayList<Thuoc> getThuocCanHan() throws SQLException {
		ArrayList<Thuoc> listThuoc = new ArrayList<>();
		ConnectDB.getInstance();
		String sql = "select * from Thuoc where (YEAR(hanSD)=YEAR(GETDATE()) AND MONTH(hanSD)=MONTH(GETDATE()) AND (Day(hanSD)-Day(GETDATE())) <= 30)";
		Statement staement = con.getConnection().createStatement();
		ResultSet resultSet = staement.executeQuery(sql);
		while (resultSet.next()) {
			int maThuoc = resultSet.getInt("mathuoc");
			String tenThuoc = resultSet.getString("tenthuoc");
			int soLuongTon = resultSet.getInt("soluong");
			LocalDate hanSD = null;
			if (resultSet.getDate("hanSD") != null) {
				hanSD = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("hanSD")),
						DateTimeFormatter.ISO_LOCAL_DATE);
			}

			Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, soLuongTon, hanSD);
			listThuoc.add(thuoc);
		}
		return listThuoc;

	}

	public ArrayList<Thuoc> timThuocSapHetTonKho(String tenThuoc) throws SQLException {
		ArrayList<Thuoc> timThuoc = new ArrayList<Thuoc>();
		String sql = "select * from Thuoc Where tenThuoc = ?";
		Connection con = ConnectDB.getConnection();
		PreparedStatement ppsm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ppsm.setString(1, tenThuoc + "");
		ResultSet rs = ppsm.executeQuery();
		Thuoc thuoc = null;
		while (rs.next()) {
			thuoc = new Thuoc(rs.getInt("maThuoc"), rs.getString("tenThuoc"), rs.getInt("soLuong"));
			timThuoc.add(thuoc);
		}
		return timThuoc;
	}

	public ArrayList<Thuoc> timThuocCanHan(String tenThuoc) throws SQLException {
		ArrayList<Thuoc> timThuoc = new ArrayList<Thuoc>();
		String sql = "select * from Thuoc where (YEAR(hanSD)=YEAR(GETDATE()) AND MONTH(hanSD)=MONTH(GETDATE()) AND (Day(hanSD)-Day(GETDATE())) <= 30)";
		Connection con = ConnectDB.getConnection();
		PreparedStatement ppsm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = ppsm.executeQuery(sql);
		Thuoc thuoc = null;
		while (rs.next()) {
			thuoc = new Thuoc(rs.getInt("maThuoc"), rs.getString("tenThuoc"), rs.getInt("soLuong"));
			timThuoc.add(thuoc);
		}
		return timThuoc;
	}

	public ArrayList<Thuoc> getThuocSapHet() throws SQLException {
		ArrayList<Thuoc> listThuoc = new ArrayList<>();
		ConnectDB.getInstance();
		String sql = "select * from Thuoc where soLuong < 100";
		Statement staement = con.getConnection().createStatement();
		ResultSet resultSet = staement.executeQuery(sql);
		while (resultSet.next()) {
			int maThuoc = resultSet.getInt("mathuoc");
			String tenThuoc = resultSet.getString("tenthuoc");
			int soLuongTon = resultSet.getInt("soluong");

			Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, soLuongTon);
			listThuoc.add(thuoc);
		}
		return listThuoc;

	}

	public Thuoc getThuocTheoMa(int ma) {
		Thuoc thuoc = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Thuoc Where maThuoc = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, ma);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int maThuoc = resultSet.getInt("maThuoc");
				String tenThuoc = resultSet.getString("tenThuoc");
				String hangSX = resultSet.getString("hangSX");
				String donVi = resultSet.getString("donVi");
				int soLuong = resultSet.getInt("soLuong");
				double giaBan = resultSet.getDouble("giaBan");
				String moTa = resultSet.getString("moTa");
				byte[] hinhAnh = resultSet.getBytes("hinhAnh");// resultSet.getByte("hinhAnh");
				LocalDate hanSD = null;
				if (resultSet.getDate("hanSD") != null) {
					hanSD = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("hanSD")),
							DateTimeFormatter.ISO_LOCAL_DATE);
				}
				int maLoai = resultSet.getInt("maLoai");
				int maNCC = resultSet.getInt("maNCC");
				LoaiThuoc loaiThuoc = new LoaiThuoc(maLoai);
				NhaCungCap nhaCungCap = new NhaCungCap(maNCC);
				thuoc = new Thuoc(maThuoc, tenThuoc, hangSX, donVi, soLuong, giaBan, moTa, hinhAnh, hanSD, nhaCungCap,
						loaiThuoc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return thuoc;
	}

	public boolean themThuoc(Thuoc thuoc) {
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Insert Thuoc Values\r\n" + "	(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, thuoc.getMaThuoc());
			statement.setString(2, thuoc.getTenThuoc());
			statement.setInt(3, thuoc.getLoaiThuoc().getMaLoai());
			statement.setInt(4, thuoc.getNhaCungCap().getMaNCC());
			statement.setString(5, thuoc.getHangSX());
			statement.setString(6, thuoc.getDonVi());
			statement.setInt(7, thuoc.getSoLuong());
			statement.setDouble(8, thuoc.getGiaBan());
			statement.setString(9, thuoc.getMoTa());
			statement.setBytes(10, thuoc.getHinhAnh());
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = fmt.format(thuoc.getHanSD());
			statement.setString(11, date);

			n = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean capNhatThuoc(int ma, int soLuong, double gia, LocalDate hSD) {
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Update Thuoc \r\n" + "Set soLuong = soLuong + ?, giaBan = ?, hanSD = ?\r\n"
					+ "Where maThuoc = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, soLuong);
			statement.setDouble(2, gia);
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date = fmt.format(hSD);
			statement.setString(3, date);
			statement.setInt(4, ma);
			n = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public int getMaThuoc(String tenThuoc) {
		int maThuoc = 0;
		try {
			String sql = "SELECT maThuoc FROM Thuoc WHERE tenThuoc = ?";
			PreparedStatement staement = con.getConnection().prepareStatement(sql);
			staement.setString(1, tenThuoc);
			ResultSet rs = staement.executeQuery();
			while (rs.next()) {

				maThuoc = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return maThuoc;

	}
	
	public void capNhatSauBan(int maThuoc, int soLuong) {
		try {
			
	
		String sql = "UPDATE Thuoc\n"
				+ "SET soLuong = soLuong - ?\n"
				+ "WHERE maThuoc = ?";
		PreparedStatement staement = con.getConnection().prepareStatement(sql);
		staement.setInt(1, soLuong);
		staement.setInt(2, maThuoc);
		staement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
