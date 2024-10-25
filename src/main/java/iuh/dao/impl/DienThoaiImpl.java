package iuh.dao.impl;

import java.util.List;

import iuh.dao.DienThoaiDAO;
import iuh.entity.DienThoai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DienThoaiImpl implements DienThoaiDAO {

	private EntityManager entityManager;

	public DienThoaiImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public DienThoai save(DienThoai dienThoai) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(dienThoai);
			transaction.commit();
			return dienThoai;
		} catch (Exception e) {
			e.printStackTrace();

			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}

		return null;
	}

	@Override
	public DienThoai update(DienThoai dienThoai) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.merge(dienThoai);
			transaction.commit();
			return dienThoai;
		} catch (Exception e) {
			e.printStackTrace();

			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			DienThoai dienThoai = entityManager.find(DienThoai.class, id);
			if (dienThoai != null) {
				entityManager.remove(entityManager.contains(dienThoai) ? dienThoai : entityManager.merge(dienThoai));
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
		}
		return false;
	}

	@Override
	public DienThoai findById(int id) {
		try {
			return entityManager.find(DienThoai.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DienThoai> findAll() {
		try {
			return entityManager.createQuery("SELECT d FROM DienThoai d", DienThoai.class)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<DienThoai> findByNhaCungCap(int maNCC) {
	    String jpql = "SELECT d FROM DienThoai d WHERE d.nhaCungCap.maNCC = :maNCC";
	    return entityManager.createQuery(jpql, DienThoai.class)
	                        .setParameter("maNCC", maNCC)
	                        .getResultList();
	}

	@Override
	public List<DienThoai> searchDienThoai(String keyword) {
	    String sql = "SELECT * FROM DienThoai WHERE "
	               + "ma_dt LIKE :keyword OR "
	               + "ten_dt LIKE :keyword OR "
	               + "nam_san_xuat LIKE :keyword OR "
	               + "cau_hinh LIKE :keyword";
	    return entityManager.createNativeQuery(sql, DienThoai.class)
	    		.setParameter("keyword", "%" + keyword + "%").getResultList();
	}


}
