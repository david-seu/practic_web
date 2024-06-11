using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.ConstrainedExecution;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using PracticWeb.DTO;
using PracticWeb.Mapper;
using PracticWeb.Model;

namespace PracticWeb.Controllers
{
    [Route("api/[controller]")]
    [ApiController] 
    public class ChildClassController(Dao context) : ControllerBase
    {
        private readonly Dao _context = context;

        [HttpGet]
        public IActionResult GetAllChildClasses()
        {
            return Ok(_context.ChildClasses.ToList().Select(ChildClassMapper.ToDTO));
        }

        [HttpGet("{id}")]
        public IActionResult GetChildClassById(int id)
        {
            ChildClass? ChildClass = _context.ChildClasses.Find(id);
            if (ChildClass == null)
            {
                return NotFound();
            }
            return Ok(ChildClassMapper.ToDTO(ChildClass));
        }

        [HttpGet("/filterByName/{name}")]
        public IActionResult GetAllChildrenByName(string name)
        {
            List<ChildClass> childClasses = _context.ChildClasses.Where(c => c.Name == name).ToList();
            if (childClasses.Count == 0)
            {
                return NotFound();
            }
            return Ok(childClasses.Select(ChildClassMapper.ToDTO));
        }

        [HttpGet("/filterByParentId/{parentId}")]
        public IActionResult GetAllChildrenByParentId(int parentId)
        {
            List<ChildClass> childClasses = _context.ChildClasses.Where(c => c.ParentId == parentId).ToList();
            if (childClasses.Count == 0)
            {
                return NotFound();
            }
            return Ok(childClasses.Select(ChildClassMapper.ToDTO));
        }

        [HttpGet("/filterByParentIdAndName/{parentId}/{name}")]
        public IActionResult GetAllChildrenByParentIdAndName(int parentId, string name)
        {
            List<ChildClass> childClasses = _context.ChildClasses.Where(c => c.ParentId == parentId && c.Name == name).ToList();
            if (childClasses.Count == 0)
            {
                return NotFound();
            }
            return Ok(childClasses.Select(ChildClassMapper.ToDTO));
        }

        [HttpPost]
        public IActionResult AddChildClass([FromBody] ChildClassDTO ChildClassDTO)
        {
            ParentClass? parent = _context.ParentClasses.Find(ChildClassDTO.ParentId); 
            if (parent == null)
            {
                return NotFound();
            }
            ChildClass childClass = ChildClassMapper.ToModel(ChildClassDTO);
            childClass.Parent = parent;
            ChildClass savedChildClass = _context.ChildClasses.Add(childClass).Entity;
            _context.SaveChanges();
            return Ok(ChildClassMapper.ToDTO(savedChildClass));
        }

        [HttpPost("many")]
        public IActionResult AddManyChildClasses([FromBody] List<ChildClassDTO> ChildClassDTOs)
        {
            List<ChildClass> childClasses = ChildClassDTOs.Select(ChildClassMapper.ToModel).ToList();
            foreach (ChildClass childClass in childClasses)
            {
                ParentClass? parent = _context.ParentClasses.Find(childClass.ParentId);
                if (parent == null)
                {
                    return NotFound();
                }
                childClass.Parent = parent;
                _context.ChildClasses.Add(childClass);
            }
            _context.SaveChanges();
            return Ok(childClasses.Select(ChildClassMapper.ToDTO));
        }   

        [HttpPut]
        public IActionResult UpdateChildClass([FromBody] ChildClassDTO ChildClassDTO)
        {
            try
            {
                ParentClass? parent = _context.ParentClasses.Find(ChildClassDTO.ParentId);
                if (parent == null)
                {
                    return NotFound();
                }
                ChildClass childClass = ChildClassMapper.ToModel(ChildClassDTO);
                childClass.Parent = parent;
                ChildClass updatedChildClass = _context.ChildClasses.Update(childClass).Entity;
                _context.SaveChanges();
                return Ok(ChildClassMapper.ToDTO(updatedChildClass));
            }
            catch (Exception e)
            {
                return NotFound(e.Message);
            }
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteChildClass(int id)
        {
            try
            {
                ChildClass? childClass = _context.ChildClasses.Find(id);
                if (childClass == null)
                {
                    return NotFound();
                }
                _context.ChildClasses.Remove(childClass);
                _context.SaveChanges();
                return NoContent();
            }
            catch (Exception e)
            {
                return NotFound(e.Message);
            }
        }
    }
}
