package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection connection;

	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findById(Integer id) {
		String SQL = "SELECT * FROM department WHERE id = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = connection.prepareStatement(SQL);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			if (rs.next()) {
				Department department = instantiateDepartment(rs);
				return department;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Department> findAll() {
		String SQL = "SELECT * FROM department";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(SQL);
			ResultSet rs = stm.executeQuery();
			
			List<Department> list = new ArrayList<>();
			while (rs.next()) {
				Department department = instantiateDepartment(rs);
				list.add(department);
			}
			DB.closeResultSet(rs);
			return list;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closePreparedStatement(stm);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("Id"));
		department.setName(rs.getString("Name"));
		return department;
	}
}
