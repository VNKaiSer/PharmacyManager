package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	private ConnectDB con = new ConnectDB();
	public NhanVien_DAO() {
		// TODO Auto-generated constructor stub
		try {
			con.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NhanVien";
			Statement staement = con.createStatement();
			ResultSet resultSet = staement.executeQuery(sql);

			while (resultSet.next()) {
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String hoTen = resultSet.getString("hoTen");
				LocalDate ngaySinh =null;
				boolean gioiTinh = resultSet.getBoolean("gioiTinh");
				String diaChi = resultSet.getString("diaChi");
				byte[] hinhAnh = resultSet.getBytes("hinhAnh");
				String chucVu = resultSet.getString("chucVu");
				NhanVien nv = new NhanVien(username, password, hoTen, ngaySinh, gioiTinh, diaChi, hinhAnh, chucVu);
				dsNV.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsNV;
		
	}

	public int login(String user, String pass) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "{? = call login_app(?,?) }";
		int result = 0;
		CallableStatement cstm = con.prepareCall(sql);
		cstm.registerOutParameter(1, java.sql.Types.INTEGER);
		cstm.setString(2, user);
		cstm.setString(3, pass);
//        try ( ResultSet rs = ;) {
//            while (rs.next()) {
//                result = rs.getBoolean(1);
//            }
//        }
		cstm.execute();
		result = cstm.getInt(1);
		return result;
	}

	public NhanVien getNhanVienTheoMa(String ma) {
		NhanVien nv = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NhanVien Where username = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String hoTen = rs.getString("hoTen");
				LocalDate ngaySinh = null;
				if (rs.getDate("ngaySinh") != null) {
					ngaySinh = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("ngaySinh")),
							DateTimeFormatter.ISO_LOCAL_DATE);
				}
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String diaChi = rs.getString("diaChi");
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				String chucVu = rs.getString("chucVu");
				nv = new NhanVien(username, password, hoTen, ngaySinh, gioiTinh, diaChi, hinhAnh, chucVu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nv;
	}

	public boolean themNhanVien(NhanVien nv) {
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Insert NhanVien Values\r\n" + "	(?,?,?,?,?,?,null,?)";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, nv.getUsername());
			statement.setString(2, nv.getPassword());
			statement.setString(3, nv.getHoTen());
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dateString = fmt.format(nv.getNgaySinh());
			statement.setString(4, dateString);
			statement.setBoolean(5, nv.isGioiTinh());
			statement.setString(6, nv.getDiaChi());
			statement.setString(7, nv.getChucVu());
			n = statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;

	}

	public boolean suaNhanVien(NhanVien nv) {
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Update NhanVien\r\n" + "Set hoTen = ?, ngaySinh = ?, gioiTinh = ?, diaChi = ?, chucVu = ?\r\n"
					+ "Where username = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, nv.getHoTen());
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dateString = fmt.format(nv.getNgaySinh());
			statement.setString(2, dateString);
			statement.setBoolean(3, nv.isGioiTinh());
			statement.setString(4, nv.getDiaChi());
			statement.setString(5, nv.getChucVu());
			statement.setString(6, nv.getUsername());
			n = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	public boolean xoaNhanVien(String id) {
		int n = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Delete NhanVien Where username = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, id);
			n = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n > 0;
	}
	
	public String getName(String userName) {
		String name = "user";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT hoTen FROM NhanVien Where username = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				name = rs.getString(1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}
