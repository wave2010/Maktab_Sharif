import java.util.Scanner;

public class CLI {

	public void CLIi() {
		
		
		try {
			Scanner scan = new Scanner(System.in);
			while (true) {
				System.out.println("0- Exit From Program :");
				System.out.println("1- Add To Professor :");
				System.out.println("2- Add To Student :");
				System.out.println("3- Delete Professor :");
				System.out.println("4- Delete Student:");
				System.out.println("5- Name Professor Of Student :");
				System.out.println("6- List Students Of A Professor:");
				System.out.println("7- List Professor Of Less Average Students :");
				Professor professor = new Professor();
				Student student = new Student();
				ProfEntityManager pem = new ProfEntityManager();
				StudentEntityManager sem = new StudentEntityManager();
				
				System.out.println("Please Insert Number Between 0 To 7: ");
				
				int selectstate = scan.nextInt();
				
				 int id=0;
				  String name;
				switch (selectstate) {
				case 2: {
					System.out.println("ID : ");
					student.setId(scan.nextInt());
					System.out.println("NAME : ");
					student.setName(scan.next());
					System.out.println("PROFESSOR_id : ");
					student.setId_prof(scan.nextInt());
					try {
						sem.insertToTable(student.getId(), student.getName(), student.getId_prof());
					} catch (Exception e) {
						System.out.println("This Professor_id Not Found! Or This Id Student Is Duplicate ");
					}
					
					break;
				}
				case 1:
					System.out.println("ID : ");
					professor.setId(scan.nextInt());
					System.out.println("NAME : ");
					professor.setName(scan.next());
					try {
						pem.insertToTable(professor.getId(), professor.getName());
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("This Number Is Duplicate");
					}
					
					break;
				case 3:
					System.out.println("Please Enter Id Or Name Professor : ");
					 while(scan.hasNextInt()==true)
					    {
					    	id=scan.nextInt();
					    	professor.setId(id);
							pem.deleteFromTable(professor.getId(),"");
							CLIi();
							break;	
					    }
					       name=scan.next();
					 	   professor.setName(name);
						   pem.deleteFromTable(0,professor.getName());
					        break;
				case 4:
					System.out.println("Please Enter Id OR Name Student : ");
					
					 while(scan.hasNextInt()==true)
					    {
					    	
					    	
					    	id=scan.nextInt();
					    	student.setId(id);
							sem.deleteFromTable(student.getId(),"");
							CLIi();
							break;
							
					    }
					       name=scan.next();
					 	   student.setName(name);
						   sem.deleteFromTable(0,student.getName());
					        break;
				case 5:
					System.out.println("Please Enter Id OR Name Student : ");
					
						 while(scan.hasNextInt()==true)
						    {
						    	
						    	
						    	id=scan.nextInt();
						    	student.setId(id);
								sem.nameProf(student.getId(),"");
								CLIi();
								break;
								
						    }
						       name=scan.next();
						 	   student.setName(name);
							   sem.nameProf(0,student.getName());
						        break;
					
				case 6:
					System.out.println("Please Enter Id Or Name Professor : ");
						 while(scan.hasNextInt()==true)
						    {
						    	id=scan.nextInt();
						    	professor.setId(id);
								pem.listStudent(professor.getId(),"");
								CLIi();
								break;	
						    }
						       name=scan.next();
						 	   professor.setName(name);
							   pem.listStudent(0,professor.getName());
						        break;
				case 7:
					sem.listProfAVG();
					break;
					
				case 0:
					System.exit(0);
					break;
					
				default:
					break;
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("Input Invalid");
			CLIi();
			// TODO: handle exception
		}
	
		
		
	}
	
}
