package MySpringBootTest.MySpringBootTest.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;








import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

import MySpringBootTest.MySpringBootTest.util.SqliteConnUtil;

@Service
public class MyTestService {

	public String readSqliteData(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String re = "error";
		try {
			conn = SqliteConnUtil.getConn();
			st = conn.createStatement();
			st.setQueryTimeout(120);
			String sql = "select * from hero";
			rs = st.executeQuery(sql);
			ArrayList<HashMap<String,Object>> data = SqliteConnUtil.putDataByDB(rs);
//			for (HashMap<String, Object> map : data) {
//				for (String key : map.keySet()) {
//					System.out.print(key+":"+map.get(key)+"|");
//				}
//				System.out.println();
//			}
			JSONArray obj = (JSONArray) JSONArray.toJSON(data);
			re = obj.toJSONString();
		} catch (SQLException e) {
			System.out.println("操作数据库异常");
			e.printStackTrace();
		}finally{
			SqliteConnUtil.close(conn, st, rs);
		}
		return re;
	}
}
