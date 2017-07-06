import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ProfEntityManager {
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	PrintResult printresult=new PrintResult();
	public void insertToTable (int id,String name) throws SQLException
	{
		
			printresult.flag=2;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO prof(id,name) VALUES('"+id+"','"+name+"');");
			rs=stmt.executeQuery("SELECT * FROM prof");
			printresult.printResult(rs);
			
		
	}
	
	public void deleteFromTable(int id,String name)
	{

		try {
			printresult.flag=2;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			if (id != 0) {
			stmt.executeUpdate("DELETE FROM prof WHERE id ='"+id+"';");
			rs=stmt.executeQuery("SELECT * FROM prof");
			}
			 else if (name != "") {
				 stmt.executeUpdate("DELETE FROM prof WHERE name ='"+name+"';");
					rs=stmt.executeQuery("SELECT * FROM prof");
			 }
			
			printresult.printResult(rs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listStudent(int id,String name)
	{
		try {
			printresult.flag = 2;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			if (id != 0) {
				rs = stmt.executeQuery(
						"SELECT student.id,student.name_student FROM student inner join prof on prof.id=student.prof_id WHERE prof.id ="+id+";");
			} else if (name != "") {
				rs = stmt.executeQuery(
						"SELECT student.id,student.name_student FROM student inner join prof on prof.id=student.prof_id WHERE prof.name ='"+name+"';");
			}
			printresult.printResult(rs);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
