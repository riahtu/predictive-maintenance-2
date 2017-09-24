package com.sap.pm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;

import com.sap.pm.util.DBUtils;

@Service
public class MainService {

	public String readDB(){
		String result = "success";
		Connection connection = null;
		try{
		connection = DBUtils.getDefaultDBConnection();
		
		Statement stmt=connection.createStatement();
		
		ResultSet rs=stmt.executeQuery("select * from \"PS_DATA\".\"SALES\"");
		
		while(rs.next()){
			result += rs.getString(1)+"  "+rs.getString(2)+"\n";
		}
		
		connection.close();
		}catch(SQLException e){
			
		}finally{
			if(connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
		return result;
	}
}
