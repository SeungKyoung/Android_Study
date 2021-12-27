package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oreilly.servlet.MultipartRequest;

import dto.ListViewDTO;

@WebServlet("*.and")
public class Ex02_DtoListGson extends HttpServlet {
	Gson gson = new Gson();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		System.out.println(req.getServletPath());
		if(req.getServletPath().equals("/dto.and")) {
			MultipartRequest multi 
			= new MultipartRequest(req, req.getRealPath("/") , 3000000);
			String fromAnd = multi.getParameter("param1");

			ListViewDTO fromDto = gson.fromJson(fromAnd, ListViewDTO.class);
			System.out.println(fromDto.getUser_id() + "안드에서 보내준값.");
			ListViewDTO dto = new ListViewDTO(1, "servlet", "servletController");
			String data = gson.toJson(dto);
			out.println(data);
		}
		
		
	}

}
