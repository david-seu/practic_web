

namespace PracticWeb.DTO
{
    public class ChildClassDTO(int id, string name, int age, string powers, int rank)
    {
        public int Id { get; set; } = id;
        public string Name { get; set; } = name;


        public string Powers { get; set; } = powers;

        public int Age { get; set; } = age;

        public int Rank { get; set; } = rank;
    }
}
