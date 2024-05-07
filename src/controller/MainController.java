package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.MemberService;
import util.ScanUtil;
import util.View;
import view.Print;

public class MainController extends Print {
	
	// sessionStorage : 데이터를 담을 수 있게 관리
	//				  : 로그인 된 정보를 넣어줌
	static public Map<String, Object> sessionStorage = new HashMap<>();
	// memberService 객체 만듦
	MemberService memberService = MemberService.getInstance();
	

	public static void main(String[] args) {
		new MainController().start();
	}

	private void start() {
		// enum 
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				// home 메소드 호출 
				view = home();
				break;
			case LOGIN:
				// home 메소드 호출 
				view = login();
				break;
			case SIGN:
				view = sign();
				break;
			default:
				break;
			}
		}
	}

	public View sign() {
		System.out.println("회원가입 메뉴");
		
		List<Object> param = new ArrayList<Object>();
		
		String id = ScanUtil.nextLine("ID >> ");
		String pw = ScanUtil.nextLine("PASS >> ");
		String name = ScanUtil.nextLine("NAME >> ");
		param.add(id);
		param.add(pw);
		param.add(name);
		
		memberService.sign(param);
		return View.HOME;
	}
	
	public View login() {
		System.out.println("로그인 메뉴");
		
		// DB에 보내기 위해 param을 담음 -> Service로 보냄 -> Dao로 보냄
		List<Object> param = new ArrayList();
		String id = ScanUtil.nextLine("아이디 : ");
		String pass = ScanUtil.nextLine("패스워드 : ");
		param.add(id);
		param.add(pass);
		memberService.login(param);
		
		boolean loginChk = memberService.login(param);
		if (loginChk) {
			Map<String, Object> member = (Map<String, Object>) sessionStorage.get("member");
			System.out.println(member.get("NAME") + "님 환영합니다");
		}
		else {
			System.out.println("로그인에 실패하셨습니다.");
			// 로그인 실패 시 다시 LOGIN으로 보냄
			return View.LOGIN;
		}
		
		return View.HOME;
	}
	
	private View home() {
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		
		int sel = ScanUtil.nextInt("메뉴 선택 : ");
		if (sel == 1) return View.LOGIN;
		else if (sel == 2) return View.SIGN;
		
		else return View.HOME;
	}

}
