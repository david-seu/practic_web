using PracticWeb.DTO;
using PracticWeb.Model;

namespace PracticWeb.Mapper
{
    public class ChildClassMapper
    {
        public static ChildClassDTO ToDTO(ChildClass child)
        {
            return new ChildClassDTO(child.Id, child.Name, child.Age, child.Powers, child.Rank);
        }

        public static ChildClass ToModel(ChildClassDTO childDTO)
        {
            return new ChildClass(childDTO.Id, childDTO.Name, childDTO.Powers, childDTO.Rank, childDTO.Age);
        }
    }
}
