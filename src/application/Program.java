package application;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import modelo.dao.DaoFactory;
import modelo.dao.SellerDao;
import modelo.entities.Department;
import modelo.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
//		System.out.println("=== TEST 1:  seller findById ===");
//		Seller seller = sellerDao.findById(3);
//		System.out.println(seller);
		
		System.out.println("\n=== TEST 2:  seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller seller2 : list) {
			System.out.println(seller2);
		}
		
		sc.close();
	}

}
