using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace PracticWeb.Model
{
    [Table("Avatar")]
    public class ChildClass
    {
        [Key]
        public int Id { get; set; }

        public string Name { get; set; }


        public string Powers { get; set; }

        public int Rank { get; set; }
        public int Age { get; set; }

        public ChildClass(int id, string name, string powers, int rank, int age)
        {
            Id = id;
            Name = name;
            Powers = powers;
            Rank = rank;
            Age = age;
        }


        public ChildClass()
        {
            Id = 0;
            Name = "";
            Powers = "";
            Rank = 0;
            Age = 0;
        }
    }
}
