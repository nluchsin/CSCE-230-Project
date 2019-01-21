import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.Scanner;

public class VeryMain {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);  //used to read all data
		
		String FileName;
		
		
		
//		System.out.println("Please enter your filename:");		// Ask the user for the input of a filename
//		FileName = sc.next();
		sc.close();
		
		FileName = args[0];
		
		String[] words = FileName.split("\\.",2);  //Gets the filename before the FIRST period, any file should not have more than one.
		FileWriter fw = new FileWriter(words[0] + ".mif");  //Creates a new file for writing
		

		String temp = null;  //Puts a header on top.
		temp = "--Assembler by Rustin Haase and Nathan Luchsinger for Group 6" + "\n"
				+ "\n" + "WIDTH=24;" + "\n" +"DEPTH=1024;"+ "\n" + "\n" + "ADDRESS_RADIX=UNS;" + "\n" + "DATA_RADIX=HEX;" + "\n" +"\n"
				+ "CONTENT BEGIN" +"\n" + "0 : 000000;" + "\n";
		fw.write(temp);
		
		temp = "1 : 008000;" + "\n" + "2 : 004000;" + "\n" + "3 : 002000;" + "\n" + "4 : 001000;" + "\n";
		
		fw.write(temp);
		
		int pC = 5;
		firstPass first = new firstPass(FileName,pC);  //1st pass happens when being instantiated

		
		//2nd pass
		try (BufferedReader br = new BufferedReader(new FileReader(FileName))) {
			for(String line; (line = br.readLine()) != null;){	
				
				String Concat[] = line.split("--"); //takes off any comment.
				line = Concat[0];
				if(line.length() > 0 && (line.startsWith("--") != true) && (line.endsWith(":") != true) && (line.endsWith("\\s") != true && line.contains(":") != true) ){  //filters out comments, spaces, and tags.

				String outputString = new String();
				secondPass second = new secondPass(first,line,pC);
				outputString = second.getString();
				fw.write(pC + " : " + outputString + ";\n");
				pC++;
			
				}
				else{ //do nothing if not an op.
				
				}
			}
			}catch (IOException e) { //catch block
			e.printStackTrace();
			System.out.println("Error:  Invalid filename");
		
		}

		pC++;
		if (pC < 1023){
			fw.write("[" + pC + "..1023] : 000000;" + "\n");
		}
		fw.write("END;");
		
		fw.close(); //closes the reader and writer
		}

	private static String DecToHex(int i) { //Code to convert a number < 15 to Hex.
		String hex = null;
		
		switch (i) {
		case 0: hex = "0";
		break;
		case 1: hex = "1";
		break;
		case 2: hex = "2";
		break;
		case 3: hex = "3";
		break;
		case 4: hex = "4";
		break;
		case 5: hex = "5";
		break;
		case 6: hex = "6";
		break;
		case 7: hex = "7";
		break;
		case 8: hex = "8";
		break;
		case 9: hex = "9";
		break;
		case 10: hex = "A";
		break;
		case 11: hex = "B";
		break;
		case 12: hex = "C";
		break;
		case 13: hex = "D";
		break;
		case 14: hex = "E";
		break;
		case 15: hex = "F";
		break;
		}
		
		return hex;
	}
}
		
