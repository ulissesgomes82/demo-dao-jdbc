package application;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
//		
//		System.out.println("=== TEST 1:  seller findById ===");
//		Seller seller = sellerDao.findById(3);
//		System.out.println(seller);
//		
//		System.out.println();
//		System.out.println("\n=== TEST 2:  seller findByDepartment ===");
//		Department department = new Department(2, null);
//		List<Seller> list = sellerDao.findByDepartment(department);
//		for (Seller seller2 : list) {
//			System.out.println(seller2);
//		}
//		
//		System.out.println();
//		System.out.println("\\n=== TEST 3:  seller findAll ===");
//		list = sellerDao.findAll();
//		for(Seller obj : list)
//		System.out.println(obj);
//		
		System.out.println();
		System.out.println("\\n=== TEST 4:  seller insert ===");
		
		Seller seller1 = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, new Department(2, null));
		sellerDao.insert(seller1);
		System.out.println(seller1.getId());
		
		sc.close();
	}

}
