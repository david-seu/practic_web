package david_seu.practicweb.controller;


import com.google.gson.Gson;
import david_seu.practicweb.dao.UserDao;
import david_seu.practicweb.dto.UserDto;
import david_seu.practicweb.mapper.UserMapper;
import david_seu.practicweb.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet{

    private final UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        User user = userDao.findByUsername(username);
        if(user == null)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST, "User not found");
        }
        else
        {
            if(user.getPassword().equals(password)){
                String parentJson = gson.toJson(user);
                System.out.println(parentJson);
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
            UserDto userDto = gson.fromJson(req.getReader(), UserDto.class);
            User user = UserMapper.toUser(userDto);
            if(userDao.findByUsername(user.getUsername()) == null) {
                userDao.save(user);
                User savedUser = userDao.findByUsername(user.getUsername());
                String parentJson = gson.toJson(savedUser);
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
