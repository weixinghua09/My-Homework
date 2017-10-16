package com.skishop.dao.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.skishop.dao.BaseDao;
import com.skishop.entity.Product;

public class ProductDao {
	// 更新产品
		public void updateProduct(Product p,int id) {
			try {
				Connection con = BaseDao.getCon();
				PreparedStatement pstm = con
						.prepareStatement("update product set name=?,price=?,description= ? where id = ?");
				pstm.setString(1, p.getName());
				pstm.setInt(2, p.getPrice());
				pstm.setString(3, p.getDescription());
				pstm.setInt(4, id);
				pstm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	// 添加商品
	public void addProduct(Product p) {
		try {
			Connection con = BaseDao.getCon();
			PreparedStatement pstm = con
					.prepareStatement("insert into product(name,producttypeid,listimg) values(?,?,?)");
			pstm.setString(1, p.getName());
			pstm.setInt(2, p.getProductTypeId());
			pstm.setString(3, p.getListimg());
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除商品
	public void deleteProduct(int id) {
		try {
			Connection con = BaseDao.getCon();
			PreparedStatement pstm = con.prepareStatement("delete from product where id=?");
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 统计数据个数
	public int findCountByPage() {
		try {
			Connection con = BaseDao.getCon();
			PreparedStatement pstm = con.prepareStatement("select count(id) from product");
			ResultSet rs = pstm.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	// 分页查询数据
	public List<Product> findByPage(int pageNum, int pageSize) {
		try {
			List<Product> list = new ArrayList<Product>();
			Connection con = BaseDao.getCon();
			PreparedStatement pstm = con.prepareStatement("select * from product limit ?,?");
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 通过id查找商品
	public Product findById(int id) {
		try {
			Connection con = BaseDao.getCon();
			PreparedStatement pstm = con.prepareStatement("select * from product where id=?");
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			Product p = new Product();
			while (rs.next()) {
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setDescription(rs.getString(3));
				p.setPrice(rs.getInt(4));
				p.setDiscountprice(rs.getInt(5));
				p.setProductTypeId(rs.getInt(10));
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 查询所有商品
	public List<Product> findAll() {
		try {
			List<Product> list = new ArrayList<Product>();
			Connection con = BaseDao.getCon();
			PreparedStatement pstm = con.prepareStatement("select * from product");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setDiscountprice(rs.getInt(3));
				p.setPrice(rs.getInt(4));
				list.add(p);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}