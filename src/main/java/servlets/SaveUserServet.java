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
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SaveUserServet")
public class SaveUserServet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.setContentType("text/html");
        PrintWriter print = response.getWriter();
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String birthDate = request.getParameter("birthdate");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        WebUser reg = new WebUser(firstName, secondName, date, login, password);
        MySqlDAOFactory daoFactory = new MySqlDAOFactory();
        try {
            GenericDAO dao = daoFactory.getDAO(daoFactory.getConnection(), WebUser.class);
        } catch (DAOOwnException e) {
            e.printStackTrace();
        }

    }
}
