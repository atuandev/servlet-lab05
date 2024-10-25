package iuh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "dienthoai")
public class DienThoai {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_dt")
	private int maDT;

	@Column(name = "ten_dt")
	@NotBlank(message = "Ten dien thoai khong duoc de trong")
	private String tenDT;

	@Column(name = "nam_san_xuat")
    @Min(value = 1900, message = "Năm sản xuất phải từ 1900 trở đi")
	@Max(value = 2024, message = "Năm sản xuất không được vượt quá năm hiện tại")
	private int namSanXuat;

	@Column(name = "cau_hinh")
	@NotBlank(message = "Cau hinh khong duoc de trong")
	private String cauHinh;

	@Column(name = "hinh_anh")
	@NotBlank(message = "Hinh anh khong duoc de trong")
	private String hinhAnh;

	@ManyToOne()
	@JoinColumn(name = "ma_ncc")
	private NhaCungCap nhaCungCap;

	public DienThoai() {
	}

	public DienThoai(String tenDT, int namSanXuat, String cauHinh, NhaCungCap nhaCungCap, String hinhAnh) {
		this.tenDT = tenDT;
		this.namSanXuat = namSanXuat;
		this.cauHinh = cauHinh;
		this.nhaCungCap = nhaCungCap;
		this.hinhAnh = hinhAnh;
	}

	public int getMaDT() {
		return maDT;
	}

	public void setMaDT(int maDT) {
		this.maDT = maDT;
	}

	public String getTenDT() {
		return tenDT;
	}

	public void setTenDT(String tenDT) {
		this.tenDT = tenDT;
	}

	public int getNamSanXuat() {
		return namSanXuat;
	}

	public void setNamSanXuat(int namSanXuat) {
		this.namSanXuat = namSanXuat;
	}

	public String getCauHinh() {
		return cauHinh;
	}

	public void setCauHinh(String cauHinh) {
		this.cauHinh = cauHinh;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	@Override
	public String toString() {
		return "DienThoai [maDT=" + maDT + ", tenDT=" + tenDT + ", namSanXuat=" + namSanXuat + ", cauHinh=" + cauHinh
				+ ", nhaCungCap=" + nhaCungCap + ", hinhAnh=" + hinhAnh + "]";
	}

}
