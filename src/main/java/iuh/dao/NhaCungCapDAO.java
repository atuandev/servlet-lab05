package iuh.dao;

import java.util.List;

import iuh.entity.NhaCungCap;

public interface NhaCungCapDAO {
	public NhaCungCap save(NhaCungCap user);

	public NhaCungCap update(NhaCungCap user);

	public boolean delete(int id);

	public NhaCungCap findById(int id);

	public List<NhaCungCap> findAll();
	
	public List<NhaCungCap> searchNhaCungCap(String keyword);
}
