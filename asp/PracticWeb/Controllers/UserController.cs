using Microsoft.AspNetCore.Mvc;
using PracticWeb.Model;

namespace PracticWeb.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController(Dao context) : ControllerBase
    {
        private readonly Dao _context = context;

        [HttpGet]
        public IActionResult Login(String username, String password)
        {
            User? user = _context.Users.FirstOrDefault(u => u.Username == username && u.Password == password);
            if(user == null)
            {
                return NotFound();
            }
            return Ok(user);
        }

        [HttpPost]
        public IActionResult Register([FromBody] User user)
        {
            _context.Users.Add(user);
            _context.SaveChanges();
            return Ok(user);
        }
    }
}
