package MySpringBootTest.MySpringBootTest.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SqliteConnUtil {
	
	/**
	 * 获取链接
	 * @return
	 */
	public static Connection getConn(){
		Connection conn = null;
		String jdbc = "org.sqlite.JDBC";
		String dburl = "jdbc:sqlite:E:\\软件\\huaxiahongtu\\YoconeGame2.0.1.app\\data\\sqlite\\new\\sanguo.sqlite";
		try {
			Class.forName(jdbc);
			conn = DriverManager.getConnection(dburl);
		} catch (ClassNotFoundException e) {
			System.out.println("加载sqlite驱动错误:"+jdbc);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("加载sqlite数据库文件错误:"+dburl);
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 关闭链接
	 * @param conn
	 * @param st
	 * @param rs
	 * @return
	 */
	public static boolean close(Connection conn, Statement st,ResultSet rs){
		try {
			if (null != conn) {
				conn.close();
			}
			if (null != st) {
				st.close();
			}
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭数据库链接异常");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 组装数据
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<HashMap<String,Object>> putDataByDB(ResultSet rs) throws SQLException{
		ArrayList<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> row;
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		while (rs.next()) {
			row = new HashMap<String,Object>();
			for (int i = 1; i <= count; i++) {
				row.put(rsmd.getColumnName(i), rs.getObject(i));
			}
			data.add(row);
		}
		return data;
	}

}
