using PracticWeb.Model;

namespace PracticWeb.DTO
{
    public class ParentClassDTO(int id, string name, int UserId)
    {
        public int Id { get; set; } = id;
        public string Name { get; set; } = name;

        public int UserId { get; set; } = UserId;
    }
}
