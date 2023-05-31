package application;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import modelo.entities.Department;
import modelo.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Department department = new Department(4, "Books");
		
		
		Seller seller = new Seller(15, "Ulisses", "Ulisses@ulis", new Date(), 2500.00, department);
		System.out.println(department);
		System.out.println("----------------------------");
		System.out.println(seller);
		
		sc.close();
	}

}
