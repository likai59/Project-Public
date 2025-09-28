using MySql.Data.MySqlClient;
class  Program
{
	static void Main(string[] args)
	{
		string connection = "server=localhost;user=root;password=123456;database=gtalent2";
		MySqlConnection conn = null;
		MySqlDataReader reader = null;

		try
		{
			conn = new MySqlConnection(connection);
			conn.Open();
			Console.WriteLine("===連線成功===");

			string sql = "SELECT * FROM employees " +
			             "ORDER BY hire_date ASC ";
			MySqlCommand cmd = new MySqlCommand(sql, conn);

			reader = cmd.ExecuteReader();
			Console.WriteLine($"User : root");
			while (reader.Read())
			{
				Console.WriteLine( "ID : {0,-3} - {1,-10}, hire_date : {2,-10}, job : {3,-10}, salary : {4,-5}",  
				reader.GetInt32("employee_id"),
				reader.GetString("first_name"),
				reader.GetDateTime("hire_date").ToString("yyyy-MM-dd"),
				reader.GetString("job_id"),
				reader.GetInt32("salary"));
			}
		}
		catch (Exception ex)
		{
			Console.WriteLine(ex.Message);
		}
		finally
		{
			if (reader != null)
			{
				reader.Close();
			}

			if (conn != null)
			{
				conn.Close();
			}
		}
		Console.ReadKey();
	}
}