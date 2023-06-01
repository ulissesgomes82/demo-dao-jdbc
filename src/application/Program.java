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
		
		Seller seller = sellerDao.findById(2);
		System.out.println(seller);

		
		sc.close();
	}

}
