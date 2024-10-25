package iuh.dao;

import java.util.List;

import iuh.entity.DienThoai;

public interface DienThoaiDAO {
	public DienThoai save(DienThoai user);

	public DienThoai update(DienThoai user);

	public boolean delete(int id);

	public DienThoai findById(int id);

	public List<DienThoai> findAll();
	
	public List<DienThoai> findByNhaCungCap(int maNCC);
	
	public List<DienThoai> searchDienThoai(String keyword);
}
