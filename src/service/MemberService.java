package service;

import java.util.List;
import java.util.Map;

import controller.MainController;
import dao.MemberDao;

public class MemberService {
// 로직 부분 처리

	private static MemberService instance;

	private MemberService() {

	}

	public static MemberService getInstance() {
		if (instance == null) {
			instance = new MemberService();
		}
		return instance;
	}

	// 메소드를 사용하기 위해서 객체가 필요 -> 객체 생성해야 함
	MemberDao dao = MemberDao.getInstance();
	
	public void sign(List<Object> param) {
		dao.sign(param);
	}

	// 객체를 만들었으면 메소드를 사용할 수 있음
	public boolean login(List<Object> param) {
		Map<String, Object> member = dao.login(param);

		if (member == null) {
			// 데이터 없음 -> 로그인 실패
			return false;
		} else {
			// 로그인을 성공했을 때 회원 정보를 넣어줌
			// 로그인 성공 시 sessionStorage에 로그인 정보 담아 줌 -> Control로 이동
			MainController.sessionStorage.put("member", member);
			return true;
		}

	}

}