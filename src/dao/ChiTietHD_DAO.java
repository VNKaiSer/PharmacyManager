package dao;

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
import entity.ChiTietHD;
import entity.HoaDon;
import entity.Thuoc;

public class ChiTietHD_DAO {
	public ArrayList<ChiTietHD> getAllCTHD() {
		ArrayList<ChiTietHD> dsCTHD = new ArrayList<ChiTietHD>();
		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietHD";
			Statement staement = con.createStatement();
			ResultSet resultSet = staement.executeQuery(sql);

			while (resultSet.next()) {
				int maCTHD = resultSet.getInt("maCTHD");
				int sl = resultSet.getInt("soLuong");
				String donVi = resultSet.getString("donVi");
				double giaBan = resultSet.getDouble("giaBan");
				double VAT = resultSet.getDouble("VAT");
				int maHD = resultSet.getInt("maHD");
				int maThuoc = resultSet.getInt("maThuoc");
				HoaDon hoaDon = new HoaDon(maHD);
				Thuoc thuoc = new Thuoc(maThuoc);

				ChiTietHD cTHD = new ChiTietHD(maCTHD, sl, donVi, giaBan, VAT, hoaDon, thuoc);
				dsCTHD.add(cTHD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCTHD;

	}

	public ArrayList<ChiTietHD> getAllCTHD_LSBH() {
		ArrayList<ChiTietHD> dsCTHD = new ArrayList<ChiTietHD>();
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnection();
			String sql = "select cthd.donVi,cthd.maCTHD, cthd.giaBan ,t.maThuoc , cthd.soLuong, cthd.VAT , hd.maHD, hd.ngayThanhToan, t.tenThuoc "
					+ " from ChiTietHD cthd join HoaDon hd " + " on cthd.maHD = hd.maHD "
					+ " join Thuoc t on t.maThuoc = cthd.maThuoc ";
			Statement staement = con.createStatement();
			ResultSet resultSet = staement.executeQuery(sql);

			while (resultSet.next()) {
				int maCTHD = resultSet.getInt("maCTHD");
				int maThuoc = resultSet.getInt("maThuoc");
				int maHD = resultSet.getInt("maHD");
				int sl = resultSet.getInt("soLuong");
				double giaBan = resultSet.getDouble("giaBan");
				double VAT = resultSet.getDouble("VAT");
				LocalDate ngayTT = null;
				if (resultSet.getDate("ngayThanhToan") != null) {
					ngayTT = LocalDate.parse(
							new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("ngayThanhToan")),
							DateTimeFormatter.ISO_LOCAL_DATE);
				}
				String tenThuoc = resultSet.getString("tenThuoc");
				HoaDon hoaDon = new HoaDon(maHD, ngayTT, null, null);
				Thuoc thuoc = new Thuoc(tenThuoc);
				ChiTietHD cTHD = new ChiTietHD(maCTHD, sl, null, giaBan, VAT, hoaDon, thuoc);
				dsCTHD.add(cTHD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCTHD;

	}

	public ResultSet getTop10Thuoc() {
		ResultSet resultSet = null;
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnection();
			String sql = "Select TOP(10) tenThuoc, SUM(cthd.soLuong) as soLuong\r\n"
					+ "From Thuoc t Join ChiTietHD cthd on  t.maThuoc= cthd.maThuoc \r\n" + "GROUP BY tenThuoc\r\n"
					+ "ORDER BY SUM(cthd.soLuong)  DESC";
			Statement staement = con.createStatement();
			resultSet = staement.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;

	}
//	while (resultSet.next()) {
//		int maCTHD = resultSet.getInt("maCTHD");
//		int maHD = resultSet.getInt("maHD");
//		int sl = resultSet.getInt("soLuong");
//		String tenThuoc = resultSet.getString("tenThuoc");
//		HoaDon hoaDon = new HoaDon(maHD, null, null, null);
//		Thuoc thuoc = new Thuoc(tenThuoc);
//		ChiTietHD cTHD = new ChiTietHD(maCTHD, sl, null, 0, 0, hoaDon, thuoc);
//		dsCTHD.add(cTHD);
//	}
//	private class ThongKeSoLuongThuoc {
//		private String tenThuoc;
//		private int soLuong;
//
//		public ThongKeSoLuongThuoc(String tenThuoc, int soLuong) {
//			super();
//			this.tenThuoc = tenThuoc;
//			this.soLuong = soLuong;
//		}
//
//		public ThongKeSoLuongThuoc() {
//			super();
//			// TODO Auto-generated constructor stub
//		}
//
//		public String getTenThuoc() {
//			return tenThuoc;
//		}
//
//		public void setTenThuoc(String tenThuoc) {
//			this.tenThuoc = tenThuoc;
//		}
//
//		public int getSoLuong() {
//			return soLuong;
//		}
//
//		public void setSoLuong(int soLuong) {
//			this.soLuong = soLuong;
//		}
//
//	}

	public int getHoaDonTiepTheo() {
		int n = 0;
		try {

			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT TOP(1) [maCTHD] FROM [dbo].[ChiTietHD]\r\n" + "ORDER BY [maCTHD] DESC";
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

	public void themChiTietHoaDon(ChiTietHD cthd) {
		try {
			String sql = "INSERT INTO [dbo].[ChiTietHD]\r\n" + "VALUES(?,?,?,?,?,?,?)";
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, cthd.getMaCTHD());
			statement.setInt(2, cthd.getHoaDon().getMaHD());
			statement.setInt(3, cthd.getThuoc().getMaThuoc());
			statement.setInt(4, cthd.getSoLuong());
			statement.setString(5, cthd.getDonVi());
			statement.setDouble(6, cthd.getGiaBan());
			statement.setDouble(7, 0.1);
			statement.executeUpdate();
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public class DoanhThuTheoNam {
		private int nam;
		private double doanhThu;

		public DoanhThuTheoNam(int nam, double doanhThu) {
			super();
			this.nam = nam;
			this.doanhThu = doanhThu;
		}

		public DoanhThuTheoNam() {
			super();
			// TODO Auto-generated constructor stub
		}

		public int getNam() {
			return nam;
		}

		public void setNam(int nam) {
			this.nam = nam;
		}

		public double getDoanhThu() {
			return doanhThu;
		}

		public void setDoanhThu(double doanhThu) {
			this.doanhThu = doanhThu;
		}

	}

	public ArrayList<DoanhThuTheoNam> getAllCTHD_DoanhThu() {
		ArrayList<DoanhThuTheoNam> dsCTHD = new ArrayList<DoanhThuTheoNam>();
		try {
			ConnectDB.getInstance().connect();
			;
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT  YEAR(hd.ngayThanhToan) as Nam, SUM(cthd.soLuong * cthd.giaBan + cthd.VAT*cthd.soLuong * cthd.giaBan) as DoanhThu "
					+ " FROM ChiTietHD cthd INNER JOIN " + " HoaDon hd ON cthd.maHD = hd.maHD "
					+ " GROUP BY YEAR(hd.ngayThanhToan)";
			Statement staement = con.createStatement();
			ResultSet resultSet = staement.executeQuery(sql);

			while (resultSet.next()) {
				int nam = resultSet.getInt("Nam");
				double doanhThu = resultSet.getDouble("DoanhThu");
				DoanhThuTheoNam dt = new DoanhThuTheoNam(nam, doanhThu);
				dsCTHD.add(dt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCTHD;

	}
}
