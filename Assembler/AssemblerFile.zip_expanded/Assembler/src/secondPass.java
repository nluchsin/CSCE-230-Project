
public class secondPass {
	
	int pC = 0;
	String b6 = "0";  //6 would be the last or LEFTMOST hex value.
	String b5 = "0";
	String b4 = "0";
	String b3 = "0";
	String b2 = "0";
	String b1 = "0";
	String b4to1 = "0000";
	String b4to3 = "00";
	int flag = 0;
	
	public secondPass(firstPass first, String line, int pCin){
		String[] words = line.split("\\s+|--");  //delimits by spaces and --.
		this.pC = pCin;
		if(words[0].equalsIgnoreCase("add")){ //R
		b4 = "4";
		Rtype(first, words[1]);
			
		}else if(words[0].equalsIgnoreCase("sub")) {  //R
			b4 = "3";
			Rtype(first, words[1]);
			
		}else if(words[0].equalsIgnoreCase("and")) {  //R
			b4 = "7";
			Rtype(first, words[1]);
			
		}else if(words[0].equalsIgnoreCase("or")) {  //R
			b4 = "6";
			Rtype(first, words[1]);
			
		}else if(words[0].equalsIgnoreCase("xor")) {  //R
			b4 = "5";
			Rtype(first, words[1]);
			
		}else if(words[0].equalsIgnoreCase("cmp")) {  //R  User
			b6 = "2";
			Rtype(first, words[1]);
		}else if(words[0].equalsIgnoreCase("jr")) {  //R
				b6 = "1";
			jrtype(first,words[1]);
			
		}else if(words[0].equalsIgnoreCase("lw") || words[0].equalsIgnoreCase("ldw")) {  //D
			b6 = "4";
			Dtype(first,words[1]);
			
		}else if(words[0].equalsIgnoreCase("sw") || words[0].equalsIgnoreCase("stw")) {  //D
			b6 = "5";
			Dtype(first,words[1]);
			
		}else if(words[0].equalsIgnoreCase("addi")) {  //D
			b6 = "6";
			Addi(first,words[1]);
		}else if(words[0].equalsIgnoreCase("bal")){  //B
			b6 = "9";
			b5 = "0";
			Btype(first,words[1]);
			
		}else if(words[0].equalsIgnoreCase("bnv")){  //B
			b6 = "8";
			b5 = "1";
			Btype(first,words[1]);
			
		}else if(words[0].equalsIgnoreCase("beq")){  //B
			b6 = "8";
			b5 = "2";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bne")){  //B
			b6 = "8";
			b5 = "3";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bvs")){  //B
			b6 = "8";
			b5 = "4";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bvc")){  //B
			b6 = "8";
			b5 = "5";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bmi")){  //B
			b6 = "8";
			b5 = "6";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bpl")){  //B
			b6 = "8";
			b5 = "7";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bcs")){  //B
			b6 = "8";
			b5 = "8";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bcc")){  //B
			b6 = "8";
			b5 = "9";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bhi")){  //B
			b6 = "8";
			b5 = "A";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bls")){  //B
			b6 = "8";
			b5 = "B";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bgt")){  //B
			b6 = "8";
			b5 = "C";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("blt")){  //B
			b6 = "8";
			b5 = "D";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("bge")){  //B
			b6 = "8";
			b5 = "E";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("ble")){  //B
			b6 = "8";
			b5 = "F";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("b") || words[0].equalsIgnoreCase("br")){ //B
			b6 = "8";
			b5 = "0";
			Btype(first,words[1]);
			
			
		}else if(words[0].equalsIgnoreCase("j")) {  //J
			Jtype(first,words[1]);
			b6 = "C";
		}else if(words[0].equalsIgnoreCase("jal")) {  //J
			Jtype(first,words[1]);
			b6 = "D";
			
		}
		
		System.out.println(this.getString());
		System.out.println("The above is the corresponding code, " + line  +"\n");
	}
	
	public String getString(){  //called by main
		if (this.flag == 1){
			String output = b6+b5+b4to1;
			return output;
		} else if(this.flag == 2){
			String output = b6+b5+b4to3+b2+b1;
			return output;
		}
		else{
		String output = b6+b5+b4+b3+b2+b1;
		return output;
		}
	}	
	
	
	public void Rtype(firstPass first, String line){ //Note that line is everything AFTER the first command.
		String[] temp = line.split(",|\\s");
//		System.out.println("line = " + line);
		b3 = RegToHex(temp[0]);
		b2 = RegToHex(temp[1]);
		b1 = RegToHex(temp[2]);
	}
	

	public void jrtype(firstPass first, String line){  //Jr is different enough that I made its own class.
//		System.out.println("jrtype line is: " + line);
		String[] temp = line.split(",|\\s");
		b3 = RegToHex(temp[0]);  //There should be no consequence of doing this.
		b2 = RegToHex(temp[0]);
		b1 = RegToHex(temp[0]);
	}
	
	
	
	
	public void Dtype(firstPass first, String line){  //Dtype You cannot have a modifier less than -128 or more than 127 ONLY ADDI
		
//			System.out.println("stw loop called");
			this.flag = 2;

			
			String[] temp = line.split(",|\\s");  //pretty much just keeps slicing the string used.
			b1 = RegToHex(temp[0]);
			temp = temp[1].split("\\(|\\)");
//			System.out.println(temp[0]);
//			System.out.println(temp[1]);
			b2 = RegToHex(temp[1]);
			int cool = Integer.parseInt(temp[0]);
			this.b4to3 = decToHexTwoDigit(cool);

		
	}
	
	
	
	
	public void Addi(firstPass first, String line){  //For an unknown reason, if statements were failing to work??? I made a new function instead.
		String[] temp = line.split(",|\\s"); //for addi
//		System.out.println("flag is: " + this.flag);
//		System.out.println("temp[0] is: " + temp[0]);
//		System.out.println("temp[1] is: " + temp[1]);
//		System.out.println("temp[2] is: " + temp[2]);
//		System.out.println("TESTING PLERS " + temp[2]);
		this.flag = 2;

		b1 = RegToHex(temp[0]);
		b2 = RegToHex(temp[1]);
		int cool = Integer.parseInt(temp[2]);
//		System.out.println("cool is: " + cool);
		this.b4to3 = decToHexTwoDigit(cool);
//		System.out.println("b4to3 is: " + b4to3);
	}
	
	
	
	public void Btype(firstPass first, String line){  //Btype type, will find the value of a tag from the first pass.
		line = line.toLowerCase();
		if(first.retrieveNum(line) > -1){
			String[] temp = line.split(":");
			int withhold = first.retrieveNum(temp[0]);
			withhold = first.getNum(withhold);
			withhold = withhold - 1;
			if(withhold < pC){  //Critical for branches to branch backwards.
				withhold = pC - withhold;
				withhold = withhold;
				withhold = withhold * -1;
			}else if(withhold >= pC){
				withhold = withhold - pC;
			}
			
			this.flag = 1;
			this.b4to1 = decToHexFourDigit(withhold);
		}
		else{
			System.out.print("Error:  Tag " + line + " Does not exist.");
		}
	}
	
	public void Jtype(firstPass first, String line){
		System.out.println("WARNING: J-TYPES NOT IMPLEMENTED.");
	}
	
	public String decToHexMonoDigit(int i) { //Code to convert a number <= 15 to Hex.
		
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

	public String decToHexTwoDigit(int i) {
		String wow = Integer.toHexString(0xffffff00 | i).substring(6); //little hack found here: https://stackoverflow.com/questions/8689526/integer-to-two-digits-hex-in-java/19298151  by ziesemer
		return wow;
	}
	
	public String decToHexFourDigit(int i) {  //FourDigits
		String wow = Integer.toHexString(0xffff0000 | i).substring(4);
		return wow;
	}

	public String RegToHex(String i) { //Code to convert a Register < 15 to Hex.
		
		String hex = null;
		
		switch (i) {
		case "r0": hex = "0";
		break;
		case "r1": hex = "1";
		break;
		case "r2": hex = "2";
		break;
		case "r3": hex = "3";
		break;
		case "r4": hex = "4";
		break;
		case "r5": hex = "5";
		break;
		case "r6": hex = "6";
		break;
		case "r7": hex = "7";
		break;
		case "r8": hex = "8";
		break;
		case "r9": hex = "9";
		break;
		case "r10": hex = "A";
		break;
		case "r11": hex = "B";
		break;
		case "r12": hex = "C";
		break;
		case "r13": hex = "D";
		break;
		case "r14": hex = "E";
		break;
		case "r15": hex = "F";
		break;
		}
		
		return hex;
	}
	
}
