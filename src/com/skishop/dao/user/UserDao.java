package com.skishop.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.skishop.dao.BaseDao;
import com.skishop.entity.User;

public class UserDao {
	//登录：在数据库中查询用户信息
	
	public List<User> checkUserInfo(){
		try {
			List<User> list = new ArrayList<User>();
			Connection con = BaseDao.getCon();
			PreparedStatement pstm = con.prepareStatement(
				"select * from user"
				);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setEmail(rs.getString(3));
				u.setPassword(rs.getString(4));
				list.add(u);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//注册：在数据库中添加新用户信息
	public boolean addUserInfo(User u){//boolean类型 插入成功与否
		try {
			Connection con = BaseDao.getCon();
			PreparedStatement pstm = con.prepareStatement(
				"insert into user (lastname,firstname,email,password,tpassword)values (?,?,?,?,?)"
				);
			
			pstm.setString(1,u.getLastname());
			pstm.setString(2,u.getFirstname());
			pstm.setString(3,u.getEmail());
			pstm.setString(4,u.getPassword());
			pstm.setString(5,u.getTpassword());
			
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
