package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection connection;

	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller obj) {
		String SQL = "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?,?,?,?,?)";
		PreparedStatement stm = null;
		try {
			stm = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, obj.getName());
			stm.setString(2, obj.getEmail());
			stm.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			stm.setDouble(4, obj.getBaseSalary());
			stm.setInt(5, obj.getDepartment().getId());
			int rowAffected = stm.executeUpdate();
			if (rowAffected > 0) {
				ResultSet rs = stm.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closePreparedStatement(stm);
		}
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		String SQL = "SELECT seller.*, department.Name AS DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ? ";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = connection.prepareStatement(SQL);
			stm.setInt(1, id);
			rs = stm.executeQuery();
			if (rs.next()) {
				Department department = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, department);
				return seller;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(stm);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Seller> findAll() {
		String SQL = "SELECT seller.*, department.Name AS DepName " + "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = connection.prepareStatement(SQL);
			rs = stm.executeQuery();
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department department = map.get(rs.getInt("DepartmentId"));
				if (department == null) {
					department = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), department);
				}
				Seller seller = instantiateSeller(rs, department);
				list.add(seller);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(stm);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department obj) {
		String SQL = "SELECT seller.*, department.Name AS DepName " + "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			stm = connection.prepareStatement(SQL);
			stm.setInt(1, obj.getId());
			rs = stm.executeQuery();
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department department = map.get(rs.getInt("DepartmentId"));
				if (department == null) {
					department = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), department);
				}
				Seller seller = instantiateSeller(rs, department);
				list.add(seller);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closePreparedStatement(stm);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
		return department;
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(department);
		return seller;
	}

}
