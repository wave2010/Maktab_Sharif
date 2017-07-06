import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentEntityManager {
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	PrintResult printresult = new PrintResult();

	public void insertToTable(int id, String name, int prof_id) throws SQLException {
			printresult.flag = 1;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
		
			String qry = "INSERT INTO student(id,name_student,prof_id) VALUES('" + id + "','" + name + "','" + prof_id
					+ "');";
			System.out.println(qry);
			stmt.executeUpdate(qry);
			rs = stmt.executeQuery("Select * from student;");
			printresult.printResult(rs);

	}

	public void deleteFromTable(int id, String name) {

		try {
			printresult.flag = 1;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			if (id != 0) {
				stmt.executeUpdate("DELETE FROM student WHERE id ='" + id + "';");
				rs = stmt.executeQuery("SELECT * FROM student ");
			}

			else if (name != "") {
				stmt.executeUpdate("DELETE FROM student WHERE name_student ='" + name + "';");
				rs = stmt.executeQuery("SELECT * FROM student");
			}
			if (rs.wasNull() == false)
				printresult.printResult(rs);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteFromTable(int id) {
		deleteFromTable(id, "");
	}

	public void deleteFromTable(String name) {
		deleteFromTable(0, name);
	}

	public void nameProf(int id, String name) {
		try {
			printresult.flag = 2;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			if (id != 0) {
				rs = stmt.executeQuery(
						"SELECT prof.id,prof.name FROM student inner join prof on prof.id=student.prof_id WHERE student.id ="
								+ id + ";");
			} else if (name != "") {
				rs = stmt.executeQuery(
						"SELECT prof.id,prof.name FROM student inner join prof on prof.id=student.prof_id WHERE student.name_student ='"
								+ name + "';");
			}
			printresult.printResult(rs);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void listProfAVG() {
		try {
			printresult.flag = 0;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(
					"select * from (select prof.name,count(prof_id) as numberstudent from student inner join prof on student.prof_id=prof.id group by prof.name) as p1,(select sum(numberstudent)/count(c.name) as average from (select prof.name,count(prof_id) as numberstudent from student inner join prof on student.prof_id=prof.id group by prof.name) as c)as p2 where p1.numberstudent<p2.average;");
			printresult.printResult(rs);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void informationStudent(int id) {
		try {
			printresult.flag = 4;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * FROM student WHERE id ='" + id + "';");
			printresult.printResult(rs);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void updateStudent(int id, String name, int prof_id, int idasli) throws SQLException {
	//	try {
			printresult.flag = 1;
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();

			String qry5 = "update student set id='" + id + "',name_student='" + name + "',prof_id='" + prof_id
					+ "' WHERE student.id ='" + idasli + "';";
			System.out.println(qry5);
			stmt.executeUpdate(qry5);
			rs = stmt.executeQuery("select * FROM student WHERE id ='" + id + "';");
			printresult.printResult(rs);

		/*} catch (Exception e) {
			// TODO: handle exception
		}*/

	}

}
