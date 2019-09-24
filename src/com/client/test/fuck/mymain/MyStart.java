package com.client.test.fuck.mymain;

import java.sql.SQLException;
import com.client.test.fuck.mysocket.ServerListener;
import com.client.test.fuck.mysqlserver.Operate;

public class MyStart {
	public static void main(String[] args) {
		try {
			Operate.SqlQuery(0);
			Operate.SqlQuery(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ServerListener().start();
	}

}
