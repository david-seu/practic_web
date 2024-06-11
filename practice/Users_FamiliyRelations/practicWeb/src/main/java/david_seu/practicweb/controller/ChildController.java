package david_seu.practicweb.controller;

import com.google.gson.Gson;
import david_seu.practicweb.dao.ChildDao;
import david_seu.practicweb.dao.GenericDao;
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

    private final ChildDao childDao = new ChildDao();

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // Handle POST request for Child
        // Create a new Child
        Gson gson = new Gson();
        try {
            ChildDto childDto = gson.fromJson(req.getReader(), ChildDto.class);
            Child child = ChildMapper.toEntity(childDto);
            if(childDao.find(child.getId()) == null) {
                childDao.save(child);
            }
            else
            {
                childDao.update(child);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}