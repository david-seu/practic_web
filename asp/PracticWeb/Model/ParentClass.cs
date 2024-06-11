using System.ComponentModel.DataAnnotations;

namespace PracticWeb.Model
{
    public class ParentClass
    {
        [Key]
        public int Id { get; set; }
        public string Name { get; set; }

        public ICollection<ChildClass> Children { get; set; }

        public int UserId { get; set; }

        public User User { get; set; }

        public ParentClass(int id, string name, int userId, User user)
        {
            Id = id;
            Name = name;
            Children = [];
            UserId = userId;
            User = user;
        }

        public ParentClass(int id, string name, int userId)
        {
            Id = id;
            Name = name;
            Children = [];
            UserId = userId;
            User = new User();
        }

        public ParentClass(int id, string name)
        {
            Id = id;
            Name = name;
            Children = [];
            UserId = 0;
        }

        public ParentClass()
        {
            Id = 0;
            Name = "";
            Children = [];
            UserId = 0;
        }
    }
}
