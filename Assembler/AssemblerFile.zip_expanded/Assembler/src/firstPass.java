import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.Scanner;

public class firstPass {

	String[] datatable = new String[1024];  //Sized to 1024 which is the maximum depth of the table.
	int[] numbertable = new int[1024];
	int pC = 0;
	int dattrack = -1;
	

	public firstPass(String filename,int PC){  //Reads the file on its own and fills out its table
				this.pC = PC - 1;

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for(String line; (line = br.readLine()) != null;){	
				processLine(line);			
			}
			br.close();
			
			System.out.println("First Pass completed.\n\n\n");
			
			}catch (IOException e) { //catch block
			e.printStackTrace();
			System.out.println("Error:  Invalid filename");
		
		}
		}

	public int processLine(String line) {//Use this to account for commands that might need more space than just one line.
		int command = 0;
		String[] words = line.split("\\s",2);
		System.out.println(words[0]);
		
		if(words[0].equalsIgnoreCase("add")){ 
			this.pC = this.pC + 1;
		}
		else if(words[0].equalsIgnoreCase("sub")) {
			this.pC = this.pC + 1;			
			
		}else if(words[0].equalsIgnoreCase("and")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("or")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("xor")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("cmp")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("jr")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("lw") || words[0].equalsIgnoreCase("ldw")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("sw") || words[0].equalsIgnoreCase("stw")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("addi")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].startsWith("b") && ( words[0].length() == 3 || words[0].length()==1 )) { //Potential issue, does allow "bad" or similar non-useful commands in.
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("br")){
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("jr") || words[0].equalsIgnoreCase("j")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].equalsIgnoreCase("jal")) {
			this.pC = this.pC + 1;
			
		}else if(words[0].contains("--")){  //allows for comments on individual lines
			
		}else if(words[0].contains("\n")){  //Explicitly handles blank lines.
			
		}else {
			words = line.split("\\:", 2);
			
			if(words[0] != null && line.contains(":") ){  //checks to make sure it both has a name and is a label
				this.dattrack++;
				words[0] = words[0].toLowerCase();
				datatable[dattrack] = words[0];
				numbertable[dattrack] = pC;
			}
			
		}
		System.out.println("pC for " + line + " is: " + this.pC);
		System.out.println("dattrack is: " + dattrack);
		
		return command;
	}

	public int retrieveNum(String use){  //gets number from which the String is used.
		use = use.toLowerCase();
		int i = 0;
		while(i <= dattrack){
			if(datatable[i].equals(use)){
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	public String getString(int i){  //used to get String per number given.
		return datatable[i];
	}
	
	public int getNum(int i){  //used to only get pC per number given.
		return numbertable[i];
	}
	
	public String[] getStringArr(){
		return datatable;
	}
	
	public int[] getNumArr(){
		return numbertable;
	}
	
	}
