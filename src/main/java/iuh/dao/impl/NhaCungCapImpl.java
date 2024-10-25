package iuh.dao.impl;

import java.util.List;

import iuh.dao.NhaCungCapDAO;
import iuh.entity.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class NhaCungCapImpl implements NhaCungCapDAO {

	private EntityManager entityManager;

	public NhaCungCapImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public NhaCungCap save(NhaCungCap dienThoai) {
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
	public NhaCungCap update(NhaCungCap dienThoai) {
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
			NhaCungCap dienThoai = entityManager.find(NhaCungCap.class, id);
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
	public NhaCungCap findById(int id) {
		try {
			return entityManager.find(NhaCungCap.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<NhaCungCap> findAll() {
		try {
			return entityManager.createQuery("FROM NhaCungCap", NhaCungCap.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<NhaCungCap> searchNhaCungCap(String keyword) {
	    String jpql = "SELECT n FROM NhaCungCap n WHERE "
	                + "n.maNCC LIKE :keyword OR "
	                + "n.tenNCC LIKE :keyword OR "
	                + "n.diaChi LIKE :keyword OR "
	                + "n.soDienThoai LIKE :keyword";
	    
	    return entityManager.createQuery(jpql, NhaCungCap.class)
	                        .setParameter("keyword", "%" + keyword + "%")
	                        .getResultList();
	}

}
