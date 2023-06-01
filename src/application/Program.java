package application;

import java.util.Locale;
import java.util.Scanner;

import modelo.dao.DaoFactory;
import modelo.dao.SellerDao;
import modelo.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1:  seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		
		sc.close();
	}

}
