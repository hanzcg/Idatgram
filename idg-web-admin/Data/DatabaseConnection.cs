using MySql.Data.MySqlClient;

namespace idg_web_admin.Data
{
    public class DatabaseConnection
    {
        private string SQLstring = string.Empty;

        public DatabaseConnection()
        {
            IConfigurationRoot builder = new ConfigurationBuilder()
                .SetBasePath(Directory.GetCurrentDirectory())
                .AddJsonFile("appsettings.json").Build();

            SQLstring = builder.GetConnectionString("cadenaMySQL").ToString();
        }

        public MySqlConnection GetConnection() {
            var connection = new MySqlConnection(SQLstring);
            connection.Open();
            
            return connection;
        }
    }
}
