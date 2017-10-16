package com.skishop.dao.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.skishop.dao.BaseDao;
import com.skishop.entity.Product;
import com.skishop.entity.ProductColor;
import com.skishop.entity.ProductType;

public class ProductColorDao {
	//添加颜色
	public void addColor(){
		
	}
	//查询所有颜色
	public List<ProductColor> findAll(){
		try{
			List<ProductColor> list=new ArrayList<ProductColor>();
			Connection con=BaseDao.getCon();
			PreparedStatement pstm=con.prepareStatement("select * from producttype");
			ResultSet rs=pstm.executeQuery();
			while(rs.next()){
				ProductColor pt=new ProductColor();
				pt.setId(rs.getInt(1));
				pt.setColor(rs.getString(2));
				list.add(pt);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
