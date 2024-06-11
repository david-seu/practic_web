package david_seu.practicweb.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import david_seu.practicweb.dao.ChildDao;
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
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/child")
public class ChildController extends HttpServlet {

    private final ChildDao childDao = new ChildDao();

    private final ParentDao parentDao = new ParentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String type = req.getParameter("type");
        switch (type) {
            case "one":
                String id = req.getParameter("id");
                Child child = childDao.find(Long.parseLong(id));
                String childJson = gson.toJson(ChildMapper.toDto(child));
                resp.getWriter().write(childJson);
                break;
            case "all": {
                List<Child> children = childDao.findAll();
                String childrenJson = gson.toJson(children.stream().map(ChildMapper::toDto).collect(Collectors.toList()));
                resp.getWriter().write(childrenJson);
                break;
            }
            case "all-by-parent": {
                String parentId = req.getParameter("parentId");
                List<Child> children = childDao.findByParentId(Integer.parseInt(parentId));
                String childrenJson = gson.toJson(children.stream().map(ChildMapper::toDto).collect(Collectors.toList()));
                System.out.println(childrenJson);
                resp.getWriter().write(childrenJson);
                break;
            }
            case "all-by-name": {
                String name = req.getParameter("name");
                List<Child> children = childDao.findAllByNameContaining(name);
                String childrenJson = gson.toJson(children.stream().map(ChildMapper::toDto).collect(Collectors.toList()));
                System.out.println(childrenJson);
                resp.getWriter().write(childrenJson);
                break;
            }
            case "all-by-parent-and-name": {
                String parentId = req.getParameter("parentId");
                String name = req.getParameter("name");
                List<Child> children = childDao.findAllByParentIdByNameContaining(Integer.parseInt(parentId), name);
                String childrenJson = gson.toJson(children.stream().map(ChildMapper::toDto).collect(Collectors.toList()));
                System.out.println(childrenJson);
                resp.getWriter().write(childrenJson);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Handle POST request for Child
        // Create a new Child
        Gson gson = new Gson();
        String type = req.getParameter("type");
        if(type != null && type.equals("one")) {
            try {
                ChildDto childDto = gson.fromJson(req.getReader(), ChildDto.class);
                Parent parent = parentDao.find(childDto.getParentId());
                if (parent == null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST, "Parent not found");
                    return;
                }
                childDto.setParent(parent);
                Child child = ChildMapper.toEntity(childDto);
                if (childDao.find(child.getId()) == null) {
                    childDao.save(child);
                } else {
                    childDao.update(child);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

                // Define the type for the list of ChildDto objects
                Type listType = new TypeToken<List<ChildDto>>() {
                }.getType();

                // Parse the JSON string to a list of ChildDto objects
                List<ChildDto> childDtoList = gson.fromJson(requestBody, listType);
                System.out.println(childDtoList);
                // Now you can iterate over childDtoList and process each ChildDto object
                for (ChildDto childDto : childDtoList) {
                    Parent parent = parentDao.find(childDto.getParentId());
                    if (parent == null) {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST, "Parent not found");
                        return;
                    }
                    childDto.setParent(parent);
                    Child child = ChildMapper.toEntity(childDto);
                    if (childDao.find(child.getId()) == null) {
                        childDao.save(child);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST, "Child already exists");
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
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