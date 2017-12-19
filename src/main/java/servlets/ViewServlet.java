package servlets;

import beans.WebUser;
import dao.DAOOwnException;
import dao.GenericDAO;
import mysqql.MySqlDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ViewServlet")
public class ViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter print = response.getWriter();
        MySqlDAOFactory factory;
        GenericDAO UserDao = null;
        List<WebUser> userList = null;

        factory = new MySqlDAOFactory();
        try {
            UserDao = factory.getDAO(factory.getConnection(), WebUser.class);
        } catch (DAOOwnException e) {
            e.printStackTrace();
        }
        try {
            userList = UserDao.getAll();
        } catch (DAOOwnException e) {
            e.printStackTrace();
        }
        print.print("<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <th>id</th>\n" +
                "    <th>login</th> \n" +
                "    <th>password</th>\n" +
                "    <th>rights</th>\n" +
                "    <th>status</th>\n" +
                "  </tr>");
        for(int i = 0; i < userList.size(); i++){
            print.print(                "  <tr>\n" +
                    "    <td>"+ userList.get(i).getId() +"</th>\n" +
                    "    <td>" + userList.get(i).getLogin()+"</th> \n" +
                    "    <td>" + userList.get(i).getPassword() + "</th>\n" +
                    "    <td>" + userList.get(i).getRights() + "</th>\n" +
                    "    <td>" + userList.get(i).getStatus() + "</th>\n" +
                    "  </tr>");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    }
