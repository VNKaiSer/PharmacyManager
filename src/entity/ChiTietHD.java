package entity;

public class ChiTietHD {
	private int maCTHD;
	private int soLuong;
	private String donVi;
	private double giaBan;
	private double VAT;
	private HoaDon hoaDon;
	private Thuoc thuoc;

	public ChiTietHD(int maCTHD, int soLuong, double giaBan, double vAT, HoaDon hoaDon, Thuoc thuoc) {
		super();
		this.maCTHD = maCTHD;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		VAT = vAT;
		this.hoaDon = hoaDon;
		this.thuoc = thuoc;
	}

	public ChiTietHD(int maCTHD, int soLuong, String donVi, double giaBan,
			double vAT, HoaDon hoaDon, Thuoc thuoc) {

		this.maCTHD = maCTHD;
		this.soLuong = soLuong;
		this.donVi = donVi;
		this.giaBan = giaBan;
		VAT = vAT;
		this.hoaDon = hoaDon;
		this.thuoc = thuoc;
	}

	public ChiTietHD(int maCTHD) {
		this.maCTHD = maCTHD;
	}

	public ChiTietHD() {
	}

	public int getMaCTHD() {
		return maCTHD;
	}

	public void setMaCTHD(int maCTHD) {
		this.maCTHD = maCTHD;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public double getVAT() {
		return VAT;
	}

	public void setVAT(double vAT) {
		VAT = vAT;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Thuoc getThuoc() {
		return thuoc;
	}

	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}

	public double thanhTien() {
		return (soLuong * giaBan) + (soLuong * giaBan) * VAT;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maCTHD;
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
		ChiTietHD other = (ChiTietHD) obj;
		if (maCTHD != other.maCTHD)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChiTietHD [maCTHD=" + maCTHD + ", soLuong=" + soLuong
				+ ", donVi=" + donVi + ", giaBan=" + giaBan + ", VAT=" + VAT
				+ ", hoaDon=" + hoaDon + ", thuoc=" + thuoc + ", thanhTien()="
				+ thanhTien() + "]";
	}

}
