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
@WebServlet("*.test")
public class Test_controller extends HttpServlet {
	Gson gson = new Gson();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req.getServletPath());
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		
		
		if(req.getServletPath().equals("/login.test")) {
			System.out.println("login");
			loginCheck(req , res);
		}else if(req.getServletPath().equals("/idCheck.test")) {
			System.out.println("idCheck");
			idCheck(req , res);
		} else if(req.getServletPath().equals("/join.test")) {
			System.out.println("join");
			insertUser(req , res);
		} else if(req.getServletPath().equals("/listview.test")) {
			System.out.println("listview");
			selectList(req , res);
		}
		
		System.out.println("if문 밖");
	}
	public void loginCheck(HttpServletRequest req, HttpServletResponse res) {
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(req, req.getRealPath("/") , 3000000);
			String fromAnd = multi.getParameter("id");
			String id = gson.fromJson(fromAnd, String.class);
			try {
				
				String resource = "mybatis/config.xml";
				InputStream inputStream;
				inputStream = Resources.getResourceAsStream(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					try (SqlSession session = sqlSessionFactory.openSession()) {
						//select.selectone .. 기타등등.. mapper의 네임스페이스 . id
						  ListViewDTO dto = session.selectOne("csu.test.loginCheck", id);
						  String data = gson.toJson(dto);
						  PrintWriter out = res.getWriter();
						  out.print(data);
						  session.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void idCheck(HttpServletRequest req, HttpServletResponse res) {
		try {		
		String resource = "mybatis/config.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//select.selectone .. 기타등등.. mapper의 네임스페이스 . id
			  List<UserDTO> list = session.selectList("csu.test.idChek");
			  String data = gson.toJson(list);
			  PrintWriter out = res.getWriter();
			  out.print(data);
			  session.close();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertUser(HttpServletRequest req, HttpServletResponse res) {
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(req, req.getRealPath("/") , 3000000);
			String fromAnd = multi.getParameter("dto");
			UserDTO dto = gson.fromJson(fromAnd, UserDTO.class);
			try {
				
				String resource = "mybatis/config.xml";
				InputStream inputStream;
				inputStream = Resources.getResourceAsStream(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					try (SqlSession session = sqlSessionFactory.openSession()) {
						//select.selectone .. 기타등등.. mapper의 네임스페이스 . id
						  int succ = session.insert("csu.test.insertUser", dto);
						  String data = gson.toJson(succ);
						  PrintWriter out = res.getWriter();
						  out.print(data);
						  session.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void selectList(HttpServletRequest req, HttpServletResponse res) {
		try {
			
		String resource = "mybatis/config.xml";
		InputStream inputStream;
		inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//select.selectone .. 기타등등.. mapper의 네임스페이스 . id
			  List<UserDTO> list = session.selectList("csu.test.listView");
			  String data = gson.toJson(list);
			  PrintWriter out = res.getWriter();
			  out.print(data);
			  session.close();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
