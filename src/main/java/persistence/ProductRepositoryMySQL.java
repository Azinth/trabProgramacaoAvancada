package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import commons.Constants;
import models.Product;

public class ProductRepositoryMySQL implements ProductRepository {

	@Override
	public void insert(Product product) {
		Connection con;
		PreparedStatement prepStmt;
		String sql = "INSERT INTO Produtos (nome, categoria, fabricacao, validade) VALUES (?, ?, ?, ?)";
		try {
			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, product.getProductName());
			prepStmt.setString(2, product.getCategoria());
			prepStmt.setString(3, product.getFabricacao());
			prepStmt.setString(4, product.getValidade());
			prepStmt.executeUpdate();
			prepStmt.close();
		}catch(SQLException ex) {
		}
	}

	@Override
	public void remove(int id) {
		Connection con;
		PreparedStatement prepStmt;
		try {
			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			prepStmt = con.prepareStatement("DELETE FROM Produtos WHERE id=?");
			prepStmt.setInt(1, id);
			prepStmt.executeUpdate();
			prepStmt.close();
			con.close();
		} catch (SQLException e) {
		}
	}

	@Override
	public Product[] findBy(String fieldName, String value) {
		Connection con;
		PreparedStatement stmt;
		String sql = "SELECT * FROM Produtos WHERE";
		if(fieldName.equals("*")) {
			sql += "nome like ? OR categoria like ? OR fabricacao like ? OR validade like ?";
		} else  {
			sql +=fieldName+" like ?";
		}
		
		try {

			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			stmt = con.prepareStatement(sql);
			
			if(!fieldName.equals("*")) {
				stmt.setString(1, "%"+value+"%");
			}else {
				for (int i = 1; i <= 4; i++) {
					stmt.setString(i, "%"+value+"%");
				}
			}
			
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}

			Product[] products = new Product[count];
			rs = stmt.executeQuery();
			int index = 0;
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome1 = rs.getString("nome");
				String categoria1 = rs.getString("categoria");
				String fabricacao1 = rs.getString("fabricacao");
				String validade1 = rs.getString("validade");
                                
				Product c = new Product(id, nome1, categoria1, fabricacao1, validade1);
				products[index] = c;
				index++;
			}
			rs.close();
			stmt.close();
			con.close();

			return products;
			
		} catch (SQLException e) {

			return new Product[0];
		}
	}

	@Override
	public Product[] findAll() {
		Connection con;
		Statement stmt;
		String sql = "SELECT id, nome, categoria, fabricacao, validade FROM produtos";

		try {
			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				count++;
			}

			Product[] products = new Product[count];
			rs = stmt.executeQuery(sql); // rs.beforeFirst();
			int index = 0;
			while (rs.next()) {
				int id = rs.getInt("id");
				String nome1 = rs.getString("nome");
				String categoria1 = rs.getString("categoria");
				String fabricacao1 = rs.getString("fabricacao");
				String validade1 = rs.getString("validade");

				Product c = new Product(id, nome1, categoria1, fabricacao1, validade1);
				products[index] = c;
				index++;
			}
			rs.close();
			stmt.close();
			con.close();

			return products;

		} catch (SQLException e) {

			return new Product[0];
		}
	}

	@Override
	public void update(Product product) {
		Connection con;
		PreparedStatement prepStmt;
		String sql = "UPDATE Produtos SET nome=?, categoria=?, fabricacao=?, validade=? "
				+ "WHERE id=?";
		try {
			con = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASS);
			prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, product.getProductName());
			prepStmt.setString(2, product.getCategoria());
			prepStmt.setString(3, product.getFabricacao());
			prepStmt.setString(4, product.getValidade());
			prepStmt.setInt(5, product.getId());
			prepStmt.executeUpdate();
			prepStmt.close();
			con.close();
		}catch(SQLException ex) {
		}           

	}

}
