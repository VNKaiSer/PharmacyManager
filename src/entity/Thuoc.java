package entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class Thuoc {

	private int maThuoc;
	private String tenThuoc;
	private String hangSX;
	private String donVi;
	private int soLuong;
	private double giaBan;
	private String moTa;
	private byte[] hinhAnh;
	private LocalDate hanSD;
	private NhaCungCap nhaCungCap;
	private LoaiThuoc loaiThuoc;

	public Thuoc(int maThuoc, String tenThuoc) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
	}

	public Thuoc(int maThuoc, String tenThuoc, String hangSX, String donVi, int soLuong, double giaBan, String moTa,
			byte[] hinhAnh, LocalDate hanSD, NhaCungCap nhaCungCap, LoaiThuoc loaiThuoc) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.hangSX = hangSX;
		this.donVi = donVi;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
		this.hanSD = hanSD;
		this.nhaCungCap = nhaCungCap;
		this.loaiThuoc = loaiThuoc;
	}

	public Thuoc(int maThuoc, String tenThuoc, String donVi, int soLuong, double giaBan, String moTa, byte[] hinhAnh) {
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donVi = donVi;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
	}

	public Thuoc(int maThuoc, String tenThuoc, int soLuong) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.soLuong = soLuong;
	}

	public Thuoc(String tenThuoc) {
		super();
		this.tenThuoc = tenThuoc;
	}

	public Thuoc(int maThuoc, String tenThuoc, int soLuong, LocalDate hanSD) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.soLuong = soLuong;
		this.hanSD = hanSD;
	}

	public Thuoc(int maThuoc) {
		this.maThuoc = maThuoc;
	}

	public Thuoc() {
	}

	public int getMaThuoc() {
		return maThuoc;
	}

	public void setMaThuoc(int maThuoc) {
		this.maThuoc = maThuoc;
	}

	public String getTenThuoc() {
		return tenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}

	public String getHangSX() {
		return hangSX;
	}

	public void setHangSX(String hangSX) {
		this.hangSX = hangSX;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public LocalDate getHanSD() {
		return hanSD;
	}

	public void setHanSD(LocalDate hanSD) {
		this.hanSD = hanSD;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public LoaiThuoc getLoaiThuoc() {
		return loaiThuoc;
	}

	public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Thuoc other = (Thuoc) obj;
		if (maThuoc != other.maThuoc) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", hangSX=" + hangSX + ", donVi=" + donVi
				+ ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", moTa=" + moTa + ", hinhAnh="
				+ Arrays.toString(hinhAnh) + ", hanSD=" + hanSD + ", nhaCungCap=" + nhaCungCap + ", loaiThuoc="
				+ loaiThuoc + "]";
	}

}
