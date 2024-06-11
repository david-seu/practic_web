using System.ComponentModel.DataAnnotations;

namespace PracticWeb.Model
{
    public class ChildClass
    {
        [Key]
        public int Id { get; set; }

        public string Name { get; set; }

        public ParentClass Parent { get; set; }

        public int ParentId { get; set; }

        public ChildClass(int id, string name, int parentId, ParentClass parent)
        {
            Id = id;
            Name = name;
            ParentId = parentId;
            Parent = parent;
        }

        public ChildClass(int id, string name, int parentId)
        {
            Id = id;
            Name = name;
            ParentId = parentId;
            Parent = new ParentClass();
        }


        public ChildClass()
        {
            Id = 0;
            Name = "";
            ParentId = 0;
            Parent = new ParentClass();
        }
    }
}
