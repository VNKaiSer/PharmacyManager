package entity;

public class NhaCungCap {
	private int maNCC;
	private String tenNCC;
	private String loaiNCC;
	private String moTa;

	public NhaCungCap(int maNCC, String tenNCC, String loaiNCC, String moTa) {
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.loaiNCC = loaiNCC;
		this.moTa = moTa;
	}

	public NhaCungCap(int maNCC) {
		this.maNCC = maNCC;
	}

	public NhaCungCap() {
	}

	public int getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getLoaiNCC() {
		return loaiNCC;
	}

	public void setLoaiNCC(String loaiNCC) {
		this.loaiNCC = loaiNCC;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maNCC;
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
		NhaCungCap other = (NhaCungCap) obj;
		if (maNCC != other.maNCC)
			return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
