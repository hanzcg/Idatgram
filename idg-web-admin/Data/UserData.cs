using idg_web_admin.Models;
using MySql.Data.MySqlClient;
using Org.BouncyCastle.Asn1.Mozilla;
using System.Data;

namespace idg_web_admin.Data
{
    public class UserData
    {

        public List<UserModel> GetAll() {
            var oLista = new List<UserModel>();

            using (var conexion = new DatabaseConnection().GetConnection())
            {
                var cmd = new MySqlCommand("usp_users_get_all", conexion);
                cmd.CommandType = CommandType.StoredProcedure;

                using (var dr = cmd.ExecuteReader())
                {
                    while (dr.Read()) {
                        oLista.Add(new UserModel()
                        {
                            Id = Convert.ToInt32(dr["id"]),
                            Email = dr["email"].ToString(),
                            Name = dr["name"].ToString(),
                            Last_name = dr["last_name"].ToString(),
                        });
                    }
                }
            }

            return oLista;
        }

        public UserModel GetById(int id)
        {
            var user = new UserModel();

            using (var conexion = new DatabaseConnection().GetConnection())
            {
                var cmd = new MySqlCommand("usp_users_get_by_id", conexion);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("p_Id", id);
                //cmd.Parameters.Add(new MySqlParameter("id", MySqlDbType.Int64).Value = id);

                using var dr = cmd.ExecuteReader();
                while (dr.Read())
                {
                    user.Id = Convert.ToInt32(dr["id"]);
                    user.Email = dr["email"].ToString();
                    user.Name = dr["name"].ToString();
                    user.Last_name = dr["last_name"].ToString();
                }
            }

            return user;
        }

        public bool Add(UserModel user)
        {
            bool saved = true;

            try
            {
                using var conexion = new DatabaseConnection().GetConnection();
                var cmd = new MySqlCommand("usp_users_add", conexion);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("p_email", user.Email);
                cmd.Parameters.AddWithValue("p_name", user.Name);
                cmd.Parameters.AddWithValue("p_last_name", user.Last_name);
                cmd.ExecuteNonQuery();
            }
            catch (Exception)
            {
                saved = false;
            }
            
            return saved;
        }

        public bool Upd(UserModel user)
        {
            bool saved = true;

            try
            {
                using var conexion = new DatabaseConnection().GetConnection();
                var cmd = new MySqlCommand("usp_users_upd", conexion);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("p_id", user.Id);
                cmd.Parameters.AddWithValue("p_email", user.Email);
                cmd.Parameters.AddWithValue("p_name", user.Name);
                cmd.Parameters.AddWithValue("p_last_name", user.Last_name);
                cmd.ExecuteNonQuery();
            }
            catch (Exception)
            {
                saved = false;
            }

            return saved;
        }

        public bool Del(int id)
        {
            bool saved = true;

            try
            {
                using var conexion = new DatabaseConnection().GetConnection();
                var cmd = new MySqlCommand("usp_users_del", conexion);
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("p_id", id);
                cmd.ExecuteNonQuery();
            }
            catch (Exception)
            {
                saved = false;
            }

            return saved;
        }

    }
}
