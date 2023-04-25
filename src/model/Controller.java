package model;

import java.util.Calendar;

import java.util.GregorianCalendar;


public class Controller {

	private Project[] projects;

	public Controller() {

		projects = new Project[10];
	
	}

	public boolean RegisterProject(String projectName, String customerName, int startDay, int startMonth, int startYear,int finishDay, int finishMonth, int finishYear, double budget,String greenManagerName, String greenManagerPhone, String cusManagerName, String cusManagerPhone) {
		Calendar initialDate =	newDate(startDay,startMonth, startYear);
        Calendar finalDate=	newDate(finishDay, finishMonth, finishYear);

		Project newProject = new Project(projectName, customerName,initialDate,finalDate,budget,greenManagerName,greenManagerPhone,cusManagerName, cusManagerPhone);
		
		for(int i = 0; i<projects.length;i++){
			if(projects[i]==null){
				projects[i] = newProject;
				return true;
			}
		}
		return false;
	}
	public static Calendar newDate(int dayDate, int monthDate, int yearDate){
        Calendar fecha = new GregorianCalendar(dayDate,monthDate,yearDate);
        return fecha;
    }

	public String showProjects() {
        String msg = "";
        for (int i = 0; i < projects.length; i++) {
            if (projects[i] != null) {
                msg += (i + 1) + ". " + projects[i].toStringProject() + "\n";
            }
        }
        return msg;
    }
    
    public boolean editStage(int opcionStage, int months) {
        Calendar startMonth = projects[opcionStage].getliStages()[projects[opcionStage].getActiveStage()].getStartPlannedDate();
        startMonth.add(Calendar.MONTH, months);
        projects[opcionStage].getliStages()[projects[opcionStage].getActiveStage()].setFinalPlannedDate(startMonth);
        return true;
    }
    
        
    
    public boolean culminateStage(int opcionStage, int dayR, int monthR, int yearR, int dayRF, int monthRF, int yearRF) {
        int activeStage = projects[opcionStage].getActiveStage();
        Calendar newStartRealDate = newDate(dayR, monthR, yearR);
        Calendar newFinalRealDate = newDate(dayRF, monthRF, yearRF);
        
        if (activeStage >= 0 && activeStage < projects[opcionStage].getliStages().length - 1) {
            projects[opcionStage].getliStages()[activeStage].setStartPlannedDate(newStartRealDate);
            projects[opcionStage].getliStages()[activeStage].setFinalPlannedDate(newFinalRealDate);
            projects[opcionStage].getliStages()[activeStage].setState(State.INACTIVE);
            projects[opcionStage].getliStages()[activeStage + 1].setState(State.ACTIVE);
            return true;
        } else {
            return false;
        }
    }
    
        
    public String showStagesActive(int opcionStage){
        String msg="";
               
        int StageActive=projects[opcionStage].getActiveStage();
        msg=projects[opcionStage].getliStages()[StageActive].toStringStage();
       
        return msg;
       }
    
       public String showLastStages(int opcionStage){
           String msg="";
           int StageActive=projects[opcionStage].getActiveStage();
           msg=projects[opcionStage].getliStages()[StageActive-1].toStringStage();
    
           return msg;
       }

       public String showStagesHistorial(String projectName){
        String msg="";
        for (int i=0; i<projects.length; i++){
            if (projects[i] != null && projects[i].getProjectName().equalsIgnoreCase(projectName)){
                int StageActive=projects[i].getActiveStage();
                for (int j=0; j<StageActive+1; j++){
                    msg+="\n"+(j+1)+"."+projects[i].getliStages()[j].toStringStage();
                }
                break;
            }
        }
        return msg;
    }
    
       public boolean registerKU(String id, String description, int type, String learnedLessons, String colaboratorName, String charge, int opcionProject) {
        Project unit=projects[opcionProject];
        String projectUnit=unit.getProjectName();
		String URL="None";
		String Publicate="NO";
        boolean indicador5=false;
        int StageActive=projects[opcionProject].getActiveStage();
        for (int i=0; i<50;i++){
            if (projects[opcionProject].getliStages()[StageActive].getUnits()[i]==null){
                if(type==1){
                    projects[opcionProject].getliStages()[StageActive].getUnits()[i]=new KnowledgeUnit(id, description, Type.TECHNICAL, learnedLessons, colaboratorName, charge ,Status.TO_BE_DEFINED, Calendar.getInstance(), projectUnit, Publicate, URL);
                } else if(type==2){
                    projects[opcionProject].getliStages()[StageActive].getUnits()[i]=new KnowledgeUnit(id, description, Type.EXPERIENCES, learnedLessons, colaboratorName, charge ,Status.TO_BE_DEFINED, Calendar.getInstance(), projectUnit, Publicate, URL);
                }else if(type==3){
                    projects[opcionProject].getliStages()[StageActive].getUnits()[i]=new KnowledgeUnit(id, description, Type.MANAGEMENT, learnedLessons, colaboratorName, charge ,Status.TO_BE_DEFINED, Calendar.getInstance(), projectUnit, Publicate, URL);
                }else{
                    projects[opcionProject].getliStages()[StageActive].getUnits()[i]=new KnowledgeUnit(id, description, Type.DOMAIN, learnedLessons, colaboratorName, charge ,Status.TO_BE_DEFINED, Calendar.getInstance(), projectUnit, Publicate, URL);
                }
                indicador5=true;
                return indicador5;
            }
            
        }
   
		
		return indicador5;
       }
	

    
    public String showLastUnit(String projectName){
        String msg="";
        Project project = null;
        for (int i = 0; i < projects.length; i++) {
            if (projects[i] != null && projects[i].getProjectName().equalsIgnoreCase(projectName)) {
                project = projects[i];
                break;
            }
        }
        if (project != null) {
            int activeStage = project.getActiveStage();
            for (int i = 0; i < 50; i++){
                if (i > 0 && project.getliStages()[activeStage].getUnits()[i] == null){
                    msg += project.getliStages()[activeStage].getUnits()[i-1].toStringKU();
                    return msg;
                }
            }
        }
        return msg;
    }
                                
    
    public String showAllUnit(String projectName){
        String msg="";
        Project project = null;
        for (int i = 0; i < projects.length; i++) {
            if (projects[i] != null && projects[i].getProjectName().equalsIgnoreCase(projectName)) {
                project = projects[i];
                break;
            }
        }
        if (project != null) {
            int activeStage = project.getActiveStage();
            for (int i=0; i<50; i++){
                if (project.getliStages()[activeStage].getUnits()[i] == null){
                    return msg;
                } else {
                    msg += "\n" + (i+1) + "." + project.getliStages()[activeStage].getUnits()[i].toStringKU();
                }
            }
        }
        return msg;
    }


    public String showUnitSelect(String projectName, int numUnit){
        String msg="";
        int stageActive = -1;
        for (int i = 0; i < projects.length; i++) {
            if (projects[i] != null && projects[i].getProjectName().equalsIgnoreCase(projectName)) {
                stageActive = projects[i].getActiveStage();
                break;
            }
        }
        if (stageActive >= 0) {
            KnowledgeUnit[] units = projects[stageActive].getliStages()[stageActive].getUnits();
            if (numUnit >= 0 && numUnit < units.length && units[numUnit] != null) {
                msg = units[numUnit].toStringKU();
            }
        }
        return msg;
    }
    

    public boolean approveKnowledgeUnit(String projectName, int opcionStage, int numUnit) {
        boolean indicador = false;
        for (int i = 0; i < projects.length; i++) {
            if (projects[i] != null) {
                if (projects[i].getProjectName().equalsIgnoreCase(projectName)) {
                    if (indicador == false) {
                        projects[i].getliStages()[opcionStage].getUnits()[numUnit].setStatus(Status.APPROVED);
                        projects[i].getliStages()[opcionStage].getUnits()[numUnit].setDateProject(Calendar.getInstance());
                        indicador = true;
                    }
                }
            }
        }
        return indicador;
    }
    

    public boolean publishKnowledgeUnit(String projectName, int opcionStage, int numUnit, String URL){
        boolean indicador=false;
        for(int i=0; i<projects.length; i++){
            if(projects[i] != null){
                if(projects[i].getProjectName().equalsIgnoreCase(projectName)){
                    if(indicador==false){
                        projects[i].getliStages()[opcionStage].getUnits()[numUnit].setURL(URL);
                        projects[i].getliStages()[opcionStage].getUnits()[numUnit].setPublish("YES");
    
                        indicador=true;
                    }
                }
            }
        }
        return indicador;
    }
    

    public String showLastpublisUnit(String projectName, int stageOption) {
        String msg = "";
        Project project = null;
        for (Project p : projects) {
            if (p != null && p.getProjectName().equalsIgnoreCase(projectName)) {
                project = p;
                break;
            }
        }
        if (project != null) {
            KnowledgeUnit[] units = project.getliStages()[stageOption].getUnits();
            for (KnowledgeUnit unit : units) {
                if (unit == null) {
                    break;
                } else {
                    String publish = unit.getPublish();
                    if (publish.equals("YES")) {
                        msg = unit.toStringKU();
                    }
                }
            }
        }
        return msg;
    }
    

    public String showApproveUnit(String projectName, int opcionStage){
        String msg = "";
        for(Project project : projects){
            if(project != null && project.getProjectName().equalsIgnoreCase(projectName)){
                KnowledgeUnit[] units = project.getliStages()[opcionStage].getUnits();
                for(int i=0; i<units.length; i++){
                    KnowledgeUnit unit = units[i];
                    if(unit == null){
                        break;
                    }
                    if(unit.getStatus() == Status.APPROVED){
                        msg += (i+1) + ". " + unit.toStringKU() + "\n";
                    }else{
                        break;
                    }
                }
            }
        }
        return msg;
    }



    public String searchPSU(String word) {
        
        StringBuilder msg = new StringBuilder();
        
        for (Project project : projects) {
            if (project != null) {
                for (Stage stage : project.getliStages()) {
                    if (stage != null) {
                        for (KnowledgeUnit unit : stage.getUnits()) {
                            if (unit != null && unit.getDescription().contains(word)) {
                                msg.append("\nWord founded \n");
                                msg.append("Proyect: ").append(project.getProjectName()).append("\n");
                                msg.append("Stage: ").append(stage.getStagePhase()).append("\n");
                                msg.append("ID: ").append(unit.getId()).append("\n");
                            }
                        }
                    }
                }
            }
        }
        
        return msg.toString();
    }
    

    public String numberTypeUnit() {
        int[] counters = new int[4];
        
        for (Project project : projects) {
            if (project != null) {
                for (Stage stage : project.getliStages()) {
                    for (KnowledgeUnit unit : stage.getUnits()) {
                        if (unit != null) {
                            switch (unit.getType()) {
                                case TECHNICAL:
                                    counters[0]++;
                                    break;
                                case MANAGEMENT:
                                    counters[1]++;
                                    break;
                                case EXPERIENCES:
                                    counters[2]++;
                                    break;
                                case DOMAIN:
                                    counters[3]++;
                                    break;
                            }
                        }
                    }
                }
            }
        }
        

        return "TECHNICAL: " + counters[0] + "\n" +
               "MANAGEMENT: " + counters[1] + "\n" +
               "EXPERIENCES: " + counters[2] + "\n" +
               "DOMAIN: " + counters[3] + "\n";
    }
    
}