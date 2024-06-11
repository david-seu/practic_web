

namespace PracticWeb.DTO
{
    public class ChildClassDTO(int id, string name, int parentId)
    {
        public int Id { get; set; } = id;
        public string Name { get; set; } = name;
        public int ParentId { get; set; } = parentId;
    }
}
