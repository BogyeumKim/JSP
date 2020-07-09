package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Emp;

public class EmpDao {
	

	private EmpDao() {
		
	}

// 사용할 인스턴스 하나를 생성한다.
private static EmpDao dao = new EmpDao();

// Emp Dao 인스턴스의 참조값을 반환하는 메서드
public static EmpDao getInstance() {
	return dao;
}

	
	// Connection 을 매개변수로 받는 이유는 트랜젝션 처리 때문.
	public List<Emp> getEmpList(Connection conn) throws SQLException {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Emp> empList = new ArrayList<>();
		
		stmt = conn.createStatement();

		String sql = "select * from emp order by ename desc";

		/*  select 의 결과 Resultset 객체에 담는다. */
		rs = stmt.executeQuery(sql);

		/* stmt.executeQuery(sql); */

		/*  결과 출력 */
		while (rs.next()) {
			empList.add(new Emp(rs.getInt("empno"), rs.getString("ename"), rs.getInt("sal"), rs.getString("job")));
		}
		
		return empList;
		
		
	}
}
