package david_seu.practicweb.controller;

import com.google.gson.Gson;
import david_seu.practicweb.dao.GenericDao;
import david_seu.practicweb.dao.ParentDao;
import david_seu.practicweb.dto.ChildDto;
import david_seu.practicweb.mapper.ChildMapper;
import david_seu.practicweb.model.Child;

import david_seu.practicweb.model.Parent;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/child")
public class ChildController extends HttpServlet {

    private final GenericDao<Child> childDao = new GenericDao<>(Child.class);
    private final ParentDao parentDao = new ParentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String id = req.getParameter("id");
        if (id == null) {
            List<Child> children = childDao.findAll();
            String childrenJson = gson.toJson(children);
            resp.getWriter().write(childrenJson);
        } else {
            Child child = childDao.find(Long.parseLong(id));
            String childJson = gson.toJson(child);
            resp.getWriter().write(childJson);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Handle POST request for Child
        // Create a new Child
        Child child = new Child();
        String parentName = req.getParameter("name");
        Parent parent = parentDao.findByName(parentName);
        child.setParent(parent);
        child.setUser(req.getParameter("user"));
        child.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        childDao.save(child);
        resp.sendRedirect("parent-list.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        // Handle DELETE request for Child
        // Delete a specific Child by ID
        String id = req.getParameter("id");
        Child child = childDao.find(Long.parseLong(id));
        childDao.delete(child);
    }
}