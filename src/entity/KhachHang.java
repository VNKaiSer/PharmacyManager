package entity;

public class KhachHang {
	private String sdtKhachHang;
	private String tenKhachHang;

	public KhachHang(String sdtKhachHang, String tenKhachHang) {

		this.sdtKhachHang = sdtKhachHang;
		this.tenKhachHang = tenKhachHang;
	}

	public KhachHang() {
	}

	public KhachHang(String sdtKhachHang) {
		this.sdtKhachHang = sdtKhachHang;
	}

	public String getSdtKhachHang() {
		return sdtKhachHang;
	}

	public void setSdtKhachHang(String sdtKhachHang) {
		this.sdtKhachHang = sdtKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sdtKhachHang == null) ? 0 : sdtKhachHang.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (sdtKhachHang == null) {
			if (other.sdtKhachHang != null)
				return false;
		} else if (!sdtKhachHang.equals(other.sdtKhachHang))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KhachHang [sdtKhachHang=" + sdtKhachHang + ", tenKhachHang="
				+ tenKhachHang + "]";
	}

}
