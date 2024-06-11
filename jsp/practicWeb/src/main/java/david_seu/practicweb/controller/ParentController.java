package david_seu.practicweb.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import david_seu.practicweb.dao.GenericDao;
import david_seu.practicweb.dao.ParentDao;
import david_seu.practicweb.dao.UserDao;
import david_seu.practicweb.dto.ParentDto;
import david_seu.practicweb.mapper.ParentMapper;
import david_seu.practicweb.model.Parent;

import david_seu.practicweb.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/parent")
public class ParentController extends HttpServlet {

    private final ParentDao parentDao = new ParentDao();

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String type = req.getParameter("type");
        switch (type) {
            case "all": {
                List<Parent> parents = parentDao.findAll();
                String parentsJson = gson.toJson(parents);
                resp.getWriter().write(parentsJson);
                break;
            }
            case "one": {
                String id = req.getParameter("id");
                Parent parent = parentDao.find(Long.parseLong(id));
                String parentJson = gson.toJson(ParentMapper.toDto(parent));
                resp.getWriter().write(parentJson);
                break;
            }
            case "all-by-user": {
                String userId = req.getParameter("userId");
                List<Parent> parents = parentDao.findAllByUserId(Long.parseLong(userId));
                String parentsJson = gson.toJson(parents.stream().map(ParentMapper::toDto).collect(Collectors.toList()));
                resp.getWriter().write(parentsJson);
                break;
            }
            case "all-by-name":
            {
                String name = req.getParameter("name");
                List<Parent> parents = parentDao.findAllByNameContaining(name);
                String parentsJson = gson.toJson(parents.stream().map(ParentMapper::toDto).collect(Collectors.toList()));
                resp.getWriter().write(parentsJson);
                break;
            }
            case "all-by-user-and-name":
            {
                String userId = req.getParameter("userId");
                String name = req.getParameter("name");
                List<Parent> parents = parentDao.findAllByUserIdByNameContaining(Long.parseLong(userId), name);
                String parentsJson = gson.toJson(parents.stream().map(ParentMapper::toDto).collect(Collectors.toList()));
                resp.getWriter().write(parentsJson);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // Handle POST request for Parent
        // Create a new Parent
        Gson gson = new Gson();
        String type = req.getParameter("type");
        if(type != null && type.equals("one")){
            try {
                ParentDto parentDto = gson.fromJson(req.getReader(), ParentDto.class);
                User user = userDao.find(parentDto.getUserId());
                Parent parent = ParentMapper.toEntity(parentDto);
                parent.setUser(user);
                if (parentDao.find(parent.getId()) == null) {
                    parentDao.save(parent);
                } else {
                    parentDao.update(parent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                // Get the request body as a string
                String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

                // Define the type for the list of ParentDto objects
                Type listType = new TypeToken<List<ParentDto>>(){}.getType();

                // Parse the JSON string to a list of ParentDto objects
                List<ParentDto> parentDtoList = gson.fromJson(requestBody, listType);

                // Now you can iterate over parentDtoList and process each ParentDto object
                for (ParentDto parentDto : parentDtoList) {
                    User user = userDao.find(parentDto.getUserId());
                    parentDto.setUser(user);
                    Parent parent = ParentMapper.toEntity(parentDto);
                    if (parentDao.find(parent.getId()) == null) {
                        parentDao.save(parent);
                    } else {
                        parentDao.update(parent);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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