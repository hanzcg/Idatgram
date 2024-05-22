using System.ComponentModel;

namespace idg_web_admin.Models
{
    public class UserModel
    {
        [DisplayName("Código")]
        public int Id { get; set; }
        [DisplayName("Correo electrónico")]
        public string? Email { get; set; }
        [DisplayName("Nombre")]
        public string? Name { get; set; }
        [DisplayName("Apellidos")]
        public string? Last_name { get; set; }
    }

}
