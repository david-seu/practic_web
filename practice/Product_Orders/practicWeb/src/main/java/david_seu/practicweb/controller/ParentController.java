package david_seu.practicweb.controller;

import com.google.gson.Gson;
import david_seu.practicweb.dao.GenericDao;
import david_seu.practicweb.dao.ParentDao;
import david_seu.practicweb.dto.ParentDto;
import david_seu.practicweb.mapper.ParentMapper;
import david_seu.practicweb.model.Parent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/parent")
public class ParentController extends HttpServlet {

    private final ParentDao parentDao = new ParentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Parent> parents = parentDao.findAll();
        req.setAttribute("parents", parents);
        req.getRequestDispatcher("parent-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // Handle POST request for Parent
        // Create a new Parent
        Gson gson = new Gson();
        try {
            ParentDto parentDto = gson.fromJson(req.getReader(), ParentDto.class);
            Parent parent = ParentMapper.toEntity(parentDto);
            if(parentDao.find(parent.getId()) == null) {
                parentDao.save(parent);
            }
            else
            {
                parentDao.update(parent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        // Handle DELETE request for Parent
        // Delete a specific Parent by ID
        String id = req.getParameter("id");
        Parent parent = parentDao.find(Long.parseLong(id));
        parentDao.delete(parent);
    }
}