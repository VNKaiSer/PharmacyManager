package entity;

import java.time.LocalDate;

public class HoaDon {
	private int maHD;
	private LocalDate ngayThanhToan;
	private NhanVien nhanVien;
	private KhachHang khachHang;

	public HoaDon(int maHD, LocalDate ngayThanhToan, NhanVien nhanVien,
			KhachHang khachHang) {
		this.maHD = maHD;
		this.ngayThanhToan = ngayThanhToan;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
	}

	public HoaDon(int maHD, LocalDate ngayThanhToan) {
		super();
		this.maHD = maHD;
		this.ngayThanhToan = ngayThanhToan;
	}

	public HoaDon(int maHD) {
		this.maHD = maHD;
	}

	public HoaDon() {
	}

	public int getMaHD() {
		return maHD;
	}

	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}

	public LocalDate getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(LocalDate ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maHD;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		if (maHD != other.maHD)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayThanhToan=" + ngayThanhToan
				+ ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + "]";
	}

}
