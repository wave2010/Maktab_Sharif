
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintResult {
	public static int flag = 0;

	public void printResult(ResultSet rs) {
		try {
			if (flag == 0) {
				while (rs.next()) {
					System.out.print("Name : " + rs.getString(1));
					System.out.println("    Number Student : " + rs.getString(2));

				}
				System.out.println("--------------------------");

			} else if (flag == 1) {

				while (rs.next()) {
					System.out.print("Id : " + rs.getInt(1));
					System.out.print("    Name :" + rs.getString(2));
					System.out.println("    Professor_Id :" + rs.getInt(3));
				}
				System.out.println("--------------------------");
			} else if (flag == 2) {

				while (rs.next()) {
					System.out.print("Id : " + rs.getInt(1));
					System.out.println("    Name :" + rs.getString(2));
				}
				System.out.println("--------------------------");
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
