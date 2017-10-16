package com.skishop.dao.product;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.skishop.dao.BaseDao;
import com.skishop.service.CartItem;

public class CartDao {
	//向数据库cart表中插入产品信息
	public boolean addProductInfo(CartItem ci){//boolean类型 插入成功与否
		try {
			Connection con = BaseDao.getCon();
			PreparedStatement pstm = con.prepareStatement(
				"insert into cartitem (id,name,count)values (?,?,?)"
				);
			pstm.setInt(1,ci.getProduct().getId());
			pstm.setString(2,ci.getProduct().getName());
			pstm.setInt(3,ci.getCount());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
