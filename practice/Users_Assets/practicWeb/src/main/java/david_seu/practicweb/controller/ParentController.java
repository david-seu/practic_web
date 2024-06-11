package david_seu.practicweb.controller;

import com.google.gson.Gson;
import david_seu.practicweb.dao.GenericDao;
import david_seu.practicweb.dao.ParentDao;
import david_seu.practicweb.dto.ParentDto;
import david_seu.practicweb.mapper.ParentMapper;
import david_seu.practicweb.model.Parent;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        Parent parent = parentDao.findByUsername(username);
        if(parent == null)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST, "Parent not found");
        }
        else
        {
            if(parent.getPassword().equals(password)){
                String parentJson = gson.toJson(parent);
                resp.getWriter().write(parentJson);
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST, "Invalid password");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // Handle POST request for Parent
        // Create a new Parent
        Gson gson = new Gson();
        try {
            ParentDto parentDto = gson.fromJson(req.getReader(), ParentDto.class);
            Parent parent = ParentMapper.toEntity(parentDto);
            if(parentDao.findByUsername(parent.getUsername()) == null) {
                parentDao.save(parent);
                Parent savedParent = parentDao.findByUsername(parent.getUsername());
                String parentJson = gson.toJson(savedParent);
                resp.getWriter().write(parentJson);
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST, "Parent already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}