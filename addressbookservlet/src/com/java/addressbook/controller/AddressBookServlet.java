package com.java.addressbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.addressbook.dao.AddressBookDao;
import com.java.addressbook.dao.AddressBookDaoImpl;
import com.java.addressbook.vo.AddressBookVO;

@WebServlet("/addressbook")
public class AddressBookServlet extends HttpServlet {
	AddressBookDao dao = new AddressBookDaoImpl();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AddressBookDao dao = new AddressBookDaoImpl();
		String action = req.getParameter("action");

		System.out.println(action);
		
		if("search".equals(action)) {
			String keyword = req.getParameter("keyword");
			List<AddressBookVO> list = dao.search(keyword);
			req.setAttribute("list", list);
			req.setAttribute("keyword", keyword);
			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, resp);
		}else if("insertform".equals(action)){
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/insert_form.jsp");
			rd.forward(req, resp);
		}else {
			List<AddressBookVO> list = dao.getList();
			req.setAttribute("list", list);
			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AddressBookDao dao = new AddressBookDaoImpl();
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");
			
			AddressBookVO vo = new AddressBookVO(name,hp,tel);
			if(dao.insert(vo)) {
				doGet(req, resp);
			}else {
				req.setAttribute("action", "insertform");
				doGet(req, resp);
			}
			
		}
	}
	
	

}