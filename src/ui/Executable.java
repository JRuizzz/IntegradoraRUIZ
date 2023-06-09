package ui;


import java.util.Scanner;
import model.Controller;


public class Executable {

	private Scanner reader;
	private Controller controller;

	public Executable() {

		reader = new Scanner(System.in);
		controller = new Controller();
	}

	public static void main(String[] args){

		Executable exe = new Executable();
		exe.menu();

	}


	/**
	 * 
	 * 
	 */
	public void menu(){
		System.out.println("\nWelcome to GreenSQA Software ");
		boolean cond=false;

		while(!cond){

            System.out.println("1. Create Project");
            System.out.println("2. Edit stages of a project");
            System.out.println("3. Complete stage of a project");
			System.out.println("4. Register Knowledge Unit");
			System.out.println("5. Approve Knowledge Unit");
			System.out.println("6. Consult Knowledge Uniit created");
			System.out.println("7. Post a Knowledge unit");
			System.out.println("8. General information");
			System.out.println("9. Exit");
            int opcion=reader.nextInt();

            switch(opcion){
                case 1:
					RegisterProject();
                 break;

                case 2:
					editStage();
                break;

                case 3:
					CulminateStage();
                break;
      
                case 4:
					registerKU();
                break;

				case 5:
					approveKnowledgeUnit();
				break;

				case 6:
					SearchPSU();
				break;

				case 7:
					publishKnowledgeUnit();
				break;

				case 8:
					numberTypeUnit();
				break;
				case 9:
                cond=true;
				break;


          }
		}
	}

	  /**
     * This method prompts the user to enter the information about a project and calls the RegisterProject method in the Controller class.
     * If the project is registered successfully, a success message is displayed. Otherwise, an error message is displayed.
    */
	public void RegisterProject() {
		System.out.println("Type the information needed to register a new project: ");
		reader.nextLine();

		System.out.println("Type the name of the project: ");
		String projectName = reader.nextLine();
		
		System.out.println("Type the name of the customer: ");
		String customerName = reader.nextLine();

		System.out.println("Type the planned start date of the project");
		System.out.println("Day: ");
		int startDay = reader.nextInt();
		System.out.println("Month: ");
		int startMonth = reader.nextInt();
		System.out.println("Year: ");
		int startYear = reader.nextInt();
		
		System.out.println("Type the planned finish date of the project");
		System.out.println("Day: ");
		int finishDay = reader.nextInt();
		System.out.println("Month: ");
		int finishMonth = reader.nextInt();
		System.out.println("Year: ");
		int finishYear = reader.nextInt();
		
		reader.nextLine();

		System.out.println("Type GreenSQA Manager Name");
		String greenManagerName = reader.nextLine();
		System.out.println("Type GreenSQA Manager Phone");
		String greenManagerPhone =reader.nextLine();
		System.out.println("Type Customer Manager Name");
		String cusManagerName = reader.nextLine();
		System.out.println("Type Customer Manager Phone");
		String cusManagerPhone = reader.nextLine();

		System.out.println("Type the budget");
		double budget = reader.nextDouble();




		if(controller.RegisterProject(projectName, customerName,startDay,startMonth-1,startYear,finishDay,finishMonth-1,finishYear,budget,greenManagerName,greenManagerPhone,cusManagerName, cusManagerPhone)) {
			System.out.println("Project registered correctly");
		} else {
			System.out.println("Full memory, cant register the project");
		}
	}

	private void editStage(){
		reader.nextLine();
		String Consulta=controller.showProjects();
		if(Consulta.equals("")){
			System.out.println("No projects created yet");
		}else{
			System.out.println("These are the projects created:");
			System.out.println(controller.showProjects());
			System.out.println("Enter with a number to which project you want to edit the stage");
			int opcionStage=reader.nextInt();
			opcionStage=opcionStage-1;
			System.out.println("This is the current stage of that project:");
			System.out.println(controller.showStagesActive(opcionStage)); 
		
			System.out.println("Enter the number of months the stage will take");
			int months=reader.nextInt();
	
			if(controller.editStage(opcionStage, months)){
				System.out.println("Stage edited correctly");
				System.out.println(controller.showStagesActive(opcionStage)); 
			}
		}
	

	}

	private void CulminateStage(){
		String Consulta=controller.showProjects();
		if(Consulta.equals("")){
			System.out.println("No projects created yet");
		}else{
			System.out.println("These are the projects created:");
			System.out.println(controller.showProjects());
			System.out.println("\nEnter with a number to which project you want to complete the stage ");
			int opcionStage=reader.nextInt();
			System.out.println("\n-This is the current stage of that project:");
			System.out.println(controller.showStagesActive(opcionStage-1)); 
			
			System.out.println("Enter the starting actual date");
			System.out.println("Enter the day");
			int dayR=reader.nextInt();
			System.out.println("Enter the month");
			int monthR=reader.nextInt();
			System.out.println("Enter the year");
			int yearR=reader.nextInt();
	
			System.out.println("Enter actual end date");
			System.out.println("Enter the day");
			int dayRF=reader.nextInt();
			System.out.println("Enter the month");
			int monthRF=reader.nextInt();
			System.out.println("Enter the year");
			int yearRF=reader.nextInt();
	
	
			if (controller.culminateStage((opcionStage-1), dayR, (monthR-1), yearR, dayRF, (monthRF-1), yearRF)){
				System.out.println("\n Stage completed successfully-");
				System.out.println("\n"+controller.showLastStages(opcionStage-1));
				System.out.println("-The next stage is now available-");
			}
		}
	
	}
	

	private void registerKU(){
		String Consulta=controller.showProjects();
		if (Consulta.equals("")){
			System.out.println("No projects created yet");
		}else{
			System.out.println(controller.showProjects());
			System.out.println("Choose the project");
			int opcionProject=reader.nextInt();
			System.out.println("Enter the ID");
			String id=reader.nextLine();
			System.out.println("Type the description with a # at the beginning and end of each keyword");
			String description=reader.nextLine();
			System.out.println("\nEnter the type of knowledge");
			System.out.println("1.Technical\n2.Experience\n.3.Management\n4.Domain");
			int type=reader.nextInt();
			reader.nextLine();
			System.out.println("Type the learned lessons with a # at the beginning and end of each keyword");
			String learnedLessons=reader.nextLine();
			System.out.println("Enter the name of the collaborator");
			String colaboratorName=reader.nextLine();
			System.out.println("Enter the charge for "+colaboratorName);
			String charge=reader.nextLine();
			

			if (controller.registerKU(id, description, type, colaboratorName, charge, learnedLessons, opcionProject-1)){
				System.out.println("Knowledge Unit registered successfully");
			} else {
				System.out.println("Cant register the knowledge Unit");
			}
		}


	}	
	private void approveKnowledgeUnit(){
		String consulta= controller.showProjects();
		

		if(consulta.equals("")){
            System.out.println("There is nothing created");
        }else{
			System.out.println("Enter the following information:");
			System.out.println(controller.showProjects());
			System.out.println("What project do you want to approve the capsule for?");
			String projectName=reader.nextLine();
			System.out.println(controller.showStagesHistorial(projectName));
			System.out.println("At what stage do you want to approve the capsule?");
			int opcionStage=reader.nextInt();
			String consulta1=controller.showAllUnit(projectName);
			if(consulta1.equals("")){
				System.out.println("\nThis stage has no registered capsules");
			}else{
				System.out.println(controller.showAllUnit(projectName));
			System.out.println("\nWhat capsule do you want to approve?");
			int numUnit=reader.nextInt();
			reader.nextLine();
            
            if(controller.approveKnowledgeUnit(projectName, opcionStage-1,numUnit-1)){
				System.out.println("Capsule edited correctly");
				System.out.println("This is how your capsule was");
			System.out.println("\n"+controller.showUnitSelect(projectName, numUnit-1));
			}
			}
			
        }

	}

	private void publishKnowledgeUnit() {
		String consulta= controller.showProjects();
		if(consulta.equals("")){
            System.out.println("There is nothing created");
        }else{
			System.out.println("\n- Enter the following information:");
			System.out.println(controller.showProjects());
			System.out.println("What project is the capsule in?");
			String projectName=reader.nextLine();
			System.out.println(controller.showStagesHistorial(projectName));
			System.out.println("What stage is the capsule in?");
			int opcionStage=reader.nextInt();
			String consulta1=controller.showApproveUnit(projectName, opcionStage-1);
			if(consulta1.equals("")){
				System.out.println("That stage does not have approved capsules");
			}else{
				System.out.println("\n"+controller.showApproveUnit(projectName, opcionStage-1));
			System.out.println("\nWhat capsule do you want to publish?");
			int numUnit=reader.nextInt();
			reader.nextLine();
			System.out.println("Enter the URL");
			String URL=reader.nextLine();
			if(controller.publishKnowledgeUnit(projectName, opcionStage-1, numUnit-1, URL)){
				System.out.println("\n-Capsule published correctly-");
				System.out.println("This is how your capsule was:");
				System.out.println("\n"+controller.showLastpublisUnit(projectName, opcionStage-1));
			}
		
        }
	}
	}


	private void SearchPSU() {
		System.out.println("Type the word to search");
		reader.nextLine();
		String wordSearch=reader.nextLine();
		String consulta=controller.searchPSU(wordSearch);
        if(consulta.equals("")){
            System.out.println("Can't find that word");
        }else{
            System.out.println(controller.searchPSU(wordSearch));
        }
	}

	private void numberTypeUnit(){
		String Consulta=controller.numberTypeUnit();

		if(Consulta.equals("")){
			System.out.println("No capsules yet");
		}else{
			System.out.println(controller.numberTypeUnit());
		}
	}

}