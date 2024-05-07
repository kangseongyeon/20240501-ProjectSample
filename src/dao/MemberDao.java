package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MemberDao {
	// DB 관련된 sql문 처리
	private static MemberDao instance;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public void sign(List<Object> param) {
		String sql = "INSERT INTO JAVA_MEMBER\r\n" + 
				"VALUES ((SELECT NVL(MAX(MEM_NO),0) + 1 FROM JAVA_MEMBER), ?, ?, ?, 'N')";
		
		jdbc.update(sql, param);
	}
	
	// Controller에서 입력한 데이터를 가져옴
	// 한 줄만 가져오는 것이기 때문에 Map 사용
	// if 로그인 성공 -> 회원 정보 출력
	// if 로그인 실패 -> null로 날라옴
	public Map<String, Object> login(List <Object> param) {
		
		String sql = "SELECT *\r\n" + 
					 "FROM JAVA_MEMBER \r\n" + 
					 "WHERE ID = ?\r\n" + 
					 "AND PASS = ?";	 
		
		return jdbc.selectOne(sql, param);
		
	}
}
