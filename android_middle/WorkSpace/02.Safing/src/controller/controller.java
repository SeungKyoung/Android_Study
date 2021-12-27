package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import dto.UserDTO;
import dto.ListViewDTO;

/**
 * Servlet implementation class Test_controller
 */
@WebServlet("*.saf")
public class controller extends HttpServlet {
	Gson gson = new Gson();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");		 //받는 데이터 인코딩
		res.setCharacterEncoding("UTF-8");		 //보내는 데이터 인코딩
		res.setContentType("text/html");		 //보내는 데이터 형식
		System.out.println(req.getServletPath());//안드로이드에서 온 mapping 콘솔출력 
		

//============================== 컨트롤러(실행되는 부분) ===============================	
		
		if(req.getServletPath().equals("/login.saf")) {
			System.out.println("login");	//진행경로 확인
			loginCheck(req , res);			//메소드 호출
			
		}else if(req.getServletPath().equals("/idCheck.saf")) {
			System.out.println("idCheck");	//진행경로 확인
			idCheck(req , res);				//메소드 호출
			
		} else if(req.getServletPath().equals("/join.saf")) {
			System.out.println("join");		//진행경로 확인
			insertUser(req , res);			//메소드 호출
			
		} else if(req.getServletPath().equals("/listview.saf")) {
			System.out.println("listview");	//진행경로 확인
			selectList(req , res);			//메소드 호출
			
		}
		
		System.out.println("if문 밖");//진행경로 확인
	}

	
	
//==========================안드로이드 연결 메소드 ============================	
	
	//회원 로그인 확인
	public void loginCheck(HttpServletRequest req, HttpServletResponse res) {
		String id; //mybatis에 보낼 데이터 선언
		
		try {
			//========= 안드로이드에서 데이터 받는 부분 ==================
			MultipartRequest multi = new MultipartRequest(req, req.getRealPath("/") , 3000000);
			//안드로이드 데이터 받는 클래스 초기화
			
			int size =  Integer.parseInt(multi.getParameter("size") ); //받은 데이터 사이즈
			
			//DB에 맞게 데이터를 변환
			for (int i = 0; i < size; i++) {
				String fromAnd = multi.getParameter("param" + (i+1));
				System.out.println(fromAnd);
				id = gson.fromJson(fromAnd, MemberDTO.class);//변환되는 메소드
			}
			
			try {
				//========= mybatis 연결 부분 ==================
				String resource = "mybatis/config.xml";
				InputStream inputStream;
				inputStream = Resources.getResourceAsStream(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				
				//========= mybatis 실행 부분 ==================
				try (SqlSession session = sqlSessionFactory.openSession()) {
					  UserDTO dto = session.selectOne("mybatis.safing.loginCheck", id);//mapper에서 데이터 가져옴
					  
					//========= 안드로이드에 보내는 부분 ==================
					  String data = gson.toJson(dto);	//안드로이드로 보낼 수 있게 변환
					  PrintWriter out = res.getWriter();//화면에 출력하는 메소드
					  out.print(data);					//화면에 출력
					  session.close();					//mybatis 종료
					  
				}
			} catch (IOException e) {			
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}//loginCheck()
	
	//아이디 중복확인
	public void idCheck(HttpServletRequest req, HttpServletResponse res) {
		try {		
			//========= mybatis 연결 부분 ==================	
			String resource = "mybatis/config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			//========= mybatis 실행 부분 ==================
			try (SqlSession session = sqlSessionFactory.openSession()) {
				  List<UserDTO> list = session.selectList("mybatis.safing.idChek");//mapper에서 데이터 가져옴
				  
				//========= 안드로이드에 보내는 부분 ==================
				  String data = gson.toJson(list); 	//안드로이드로 보낼 수 있게 변환
				  PrintWriter out = res.getWriter();//화면에 출력하는 메소드
				  out.print(data);					//화면에 출력
				  session.close();					//mybatis 종료
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//idCheck()
	
	//회원가입
	public void insertUser(HttpServletRequest req, HttpServletResponse res) {
		MemberDTO dto; //mybatis에 보낼 데이터 선언
		
		try {
			//========= 안드로이드에서 데이터 받는 부분 ==================
			MultipartRequest multi = new MultipartRequest(req, req.getRealPath("/") , 3000000);
			//안드로이드 데이터 받는 클래스 초기화
			
			int size =  Integer.parseInt(multi.getParameter("size") ); //받은 데이터 사이즈
			
			//DB에 맞게 데이터를 변환
			for (int i = 0; i < size; i++) {
				String fromAnd = multi.getParameter("param" + (i+1));
				System.out.println(fromAnd);
				dto = gson.fromJson(fromAnd, MemberDTO.class);//변환되는 메소드
			}
			
			try {
				//========= mybatis 연결 부분 ==================
				String resource = "mybatis/config.xml";
				InputStream inputStream;
				inputStream = Resources.getResourceAsStream(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				
				//========= mybatis 실행 부분 ==================
					try (SqlSession session = sqlSessionFactory.openSession()) {
						  int succ = session.insert("mybatis.safing.insertUser", dto);//mapper에서 데이터 가져옴
						  session.commit(); //커밋
						  
						//========= 안드로이드에 보내는 부분 ==================
						  String data = gson.toJson(succ); 	//안드로이드로 보낼 수 있게 변환
						  PrintWriter out = res.getWriter();//화면에 출력하는 메소드
						  out.print(data);					//화면에 출력
						  session.close();					//mybatis 종료
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}//insertUser()
	
	//ListView 전체 목록 가져오기
	public void selectList(HttpServletRequest req, HttpServletResponse res) {
		try {
		//========= mybatis 연결 부분 ==================
			String resource = "mybatis/config.xml";
			InputStream inputStream;
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		//========= mybatis 실행 부분 ==================
			try (SqlSession session = sqlSessionFactory.openSession()) {
				  List<UserDTO> list = session.selectList("mybatis.safing.listView");//mapper에서 데이터 가져옴
					  
				//========= 안드로이드에 보내는 부분 ==================
				  String data = gson.toJson(list); 	//안드로이드로 보낼 수 있게 변환
				  PrintWriter out = res.getWriter();//화면에 출력하는 메소드
				  out.print(data);					//화면에 출력
				  session.close();					//mybatis 종료
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}//selectList()
	
	
	
}
