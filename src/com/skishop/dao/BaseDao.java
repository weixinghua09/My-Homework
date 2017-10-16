package com.skishop.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
	static{
		//1.加载驱动类
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//2.连接数剧库
	public static Connection getCon(){
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/ski_db?useUnicode=true&characterEncoding=UTF-8","root",""
					);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
