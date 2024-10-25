package iuh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_ncc")
	private int maNCC;

	@Column(name = "ten_ncc")
	@NotBlank(message = "Ten nha cung cap khong duoc de trong")
	private String tenNCC;

	@NotBlank(message = "Dia chi khong duoc de trong")
	@Column(name = "dia_chi")
	private String diaChi;

	@NotBlank(message = "So dien thoai khong duoc de trong")
	@Pattern(regexp = "^\\d{10}$", message = "So dien thoai có 10 kí tự số")
	@Column(name = "so_dien_thoai")
	private String soDienThoai;

	public NhaCungCap() {
	}

	public NhaCungCap(@NotBlank(message = "Ten nha cung cap khong duoc de trong") String tenNCC,
			@NotBlank(message = "Dia chi khong duoc de trong") String diaChi,
			@NotBlank(message = "So dien thoai khong duoc de trong") @Pattern(regexp = "^\\d{10}$", message = "So dien thoai khong hop le") String soDienThoai) {
		super();
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
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

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", soDienThoai="
				+ soDienThoai + "]";
	}

}
