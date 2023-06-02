package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
	
		System.out.println("=== Test 1 : department findById");
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Department department = departmentDao.findById(2);
		System.out.println(department);

	}

}
