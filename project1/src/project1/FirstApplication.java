package project1;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FirstApplication {
	
	private static Scanner sc = new Scanner(System.in);
	private static String rootFolderPath = "F:\\simplilearn\\project1\\project1\\Virtual_Key_Repository";
	private static String fileName = null;
	private static int fileCount = 0;
	private static Path selFile;
	private static List<File> listFile = new ArrayList<File>();
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		boolean isInLoop = true;
		int getusrinput;
		welcomePage();
		fileListing();
		while(isInLoop)
		{
	        getusrinput = mainMenuPage();
	        
	        switch(getusrinput)
	        {
	            case 1:
	            	File fobj = new File(rootFolderPath);
	        		if(fobj.exists() && fobj.isDirectory())
	        		{
	        			File f[] = fobj.listFiles();
	        			System.out.println("-----------------------------------------------------------------");
	        			System.out.println("Show All Files in Directory:"+fobj);
	        			System.out.println("-----------------------------------------------------------------");
	        			showAllFiles(f, 0, 0);
	        		}
	        		break;
	            case 2:
	            	subOptionsMenu();
	            	break;
	            case 3:
	            	System.out.println("Application Close");				
					System.exit(1);
	            default:
	            	System.out.println("Please Choose Valid Option");
	            	break;
	        }
		}
	}
	
	private static void welcomePage()
	{
		System.out.println("===========================================================");
		System.out.println("<=      Welcome To Virtual Key Repositories Prototype    =>");
		System.out.println("<=              Company Lockers Pvt. Ltd                 =>");
		System.out.println("<=      Full Stack Developer Name: Swapnil Devkar        =>");
		System.out.println("=======================================================");
		
		System.out.println("");
		System.out.println("Press Enter key to continue...");
		
		try
        {
            System.in.read();
        }  
        catch(Exception e)
        {
        	System.out.println("Exception:"+e);
        }
	}
	
	private static void showAllFiles(File[] f,int i,int v)
	{
		if(i == f.length)
		{
			return;
		}
		
		for(int j=0;j < v;j++)
		{
			System.out.print("\t");
		}
		
		//check file
		if(f[i].isFile())
		{
			System.out.println(f[i].getName());
		}
		else if(f[i].isDirectory())
		{
			System.out.println(f[i].getName());
			//recursive call same function
			//showAllFiles(f[i].listFiles(),0,v+1);
		}
		
		//recursive call same function
		showAllFiles(f,i+1,v);
	}
	
	private static int mainMenuPage()
	{
		int usrChosMain = 0;
		
			System.out.println("=======================================================");
			System.out.println("<=                    Main Menu                      =>");
			System.out.println("=======================================================");
			System.out.println("1. Display All File in Ascending order");
			System.out.println("2. Perform File Operations");
			System.out.println("3. Close the Application");
			System.out.println("=======================================================");
			System.out.print("Choose Your Option:");
			usrChosMain = sc.nextInt();
			return usrChosMain;
	}
	
	private static void subOptionsMenu() throws IOException {
		
		boolean isInLoop = true;
		int nestedgetusrinput;
		while(isInLoop) 
		{
			System.out.println("1. Add File");
            System.out.println("2. Delete File");
            System.out.println("3. Search File");
            System.out.println("4. Back");
            System.out.println("Choose Your Option:");
            nestedgetusrinput = sc.nextInt();
            switch(nestedgetusrinput)
            {
            	case 1:
            		System.out.println("Enter Add File Name:");
            		fileName = sc.next();
					selFile = Paths.get(rootFolderPath + "\\" + fileName);						
					addFile();
					break;
            	case 2:
            		System.out.println("Enter Delete File Name:");
            		fileName = sc.next();
            		selFile = Paths.get(rootFolderPath + "\\" + fileName);
            		deleteFile();
            		break;
            	case 3:
            		System.out.println("Enter Search File Name:");
            		fileName = sc.next();
					selFile = Paths.get(rootFolderPath + "\\" + fileName);
					searchFile();
            		break;
            	case 4:
            		isInLoop = false;
            		mainMenuPage();
            		break;
            	default:
            		System.out.println("Please Choose Valid Option");
            		break;
            }
		}
	}
		
	
	private static void addFile() throws IOException{
		Path filePath = Paths.get(rootFolderPath, fileName);
		
		if(Files.notExists(filePath))
		{ 
			Files.createFile(filePath);
			System.out.println("-----------------------------------------------------------------");
			System.out.println(fileName+" File Successfully Added");
			System.out.println("-----------------------------------------------------------------");
			fileListing(); //fileListing after adding file 
			Collections.sort(listFile); //sort files in ascending order
		}
		else {
			System.out.println("-----------------------------------------------------------------");
			System.out.println(fileName+" File already exists");
			System.out.println("-----------------------------------------------------------------");
		}				
	}
	
	private static void fileListing() {
		int count = 0;
		
		File folderFiles = new File(rootFolderPath);
		
		for(File file : folderFiles.listFiles()) {
			listFile.add(file);
			count++;
		}
		
		fileCount = count;
		
	}
	
	private static void deleteFile() throws IOException {
		
		File folderFiles = new File(rootFolderPath);		
		
		if(Files.exists(selFile)) 
		{
			System.out.println("Press y to delete file:");
			String usrInput = sc.next();
			
			if(usrInput.equalsIgnoreCase("y")) 
			{
				for(File f : folderFiles.listFiles()) {
					if(f.getCanonicalFile().getName().equals(selFile.getFileName().toString())) 
					{
						Files.deleteIfExists(selFile);
						System.out.println("-----------------------------------------------------------------");
	        			System.out.println(selFile+" File Successfully Deleted");
	        			System.out.println("-----------------------------------------------------------------");
						fileListing(); //fileListing after delete file
						Collections.sort(listFile); //sort files in ascending order						
					}
				
				}
			}
			else {
				System.out.println("File Not Delete");				
				}
		}
		else
			System.out.println("File not found in " + folderFiles);
		
	}
	
	private static void searchFile() {
		
		File folderFiles = new File(rootFolderPath);
		boolean isFileExist = true;		
			
			for(File f : folderFiles.listFiles()) 
			{				
				try {
					if(f.getCanonicalFile().getName().equals(selFile.getFileName().toString())) {
						System.out.println("-----------------------------------------------------------------");
						System.out.println(selFile.getFileName().toString() + " exist in " + folderFiles);
	        			System.out.println("-----------------------------------------------------------------");
						isFileExist = false;
						break;
					}
				} catch (IOException e) {					
					System.out.println("Error:"+e);
				}				
			}				
			
		if(isFileExist != false)
		{
			System.out.println(selFile.getFileName().toString() + " Not exist in " + folderFiles);
		}	
	}
}
