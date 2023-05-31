package modelo.dao;

import modelo.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
