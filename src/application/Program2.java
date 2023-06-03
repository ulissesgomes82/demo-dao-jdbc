package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
	
//		System.out.println("=== Test 1 : department findById");
//		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
//		Department department1 = departmentDao.findById(2);
//		System.out.println(department1);
//
//		System.out.println();
//		System.out.println("=== Test 2 : department findAll");
//		List<Department> list = departmentDao.findAll();
//		for(Department department2 : list) {
//			System.out.println(department2);
//		}
		
//		System.out.println();
//		System.out.println("=== Test 3 : department insert");
//		departmentDao.insert(new Department(null, "teste"));
//		System.out.println("insert completed");
		
		System.out.println();
		System.out.println("=== Test 4 : department update");
		Department department = departmentDao.findById(5);
		department.setName("Ulisses");
		departmentDao.update(department);
		
		System.out.println();
		System.out.println("=== Test 5 : department delete");
		departmentDao.deleteById(7);
	}

}
