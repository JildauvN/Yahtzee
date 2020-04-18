import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Yahtzee { 
    public static void main(String[] args) {
		YahtzeeSpel ySpel = new YahtzeeSpel();
		ySpel.spelen();
		//ySpel.vasthouden();
		//System.out.println(Arrays.toString(ySpel.blokArray));
    }
}

class YahtzeeSpel{
	ArrayList<Dobbelstenen> stenen = new ArrayList<>();
	Scanner input = new Scanner(System.in);
	int[] blokArray = {0, 0, 0, 0, 0};
	final int[] posArr = {1, 2, 3, 4, 5};
	int[] huidigeWorp = new int[5];
	boolean eindeBeurt = false;
	YahtzeeSpel(){
		for (int i = 0; i < 5; i++){
			stenen.add(new Dobbelstenen());
		}
	}
	
	void spelen(){
		Speler speler1 = new Speler();
		System.out.println("Druk Enter voor het werpen van een nieuwe worp. Druk q voor het stoppen van het spel.");
		while (!input.nextLine().equals("q")){
			eindeBeurt = false;
			gooien(1);
			vasthouden();
			if (!eindeBeurt){
				gooien(2);
				vasthouden();
			}
			if (!eindeBeurt) gooien(3);
			for(int j = 0; j < blokArray.length; j++){
					blokArray[j] = 0;
				}
			speler1.history.add(new Worp(huidigeWorp));
			System.out.println("\nDruk Enter voor de eerste worp van de volgende beurt. Druk q voor het stoppen van het spel.");
		}
	}
	
	void vasthouden(){
		
		System.out.println("\nWelke posities wilt u vasthouden? typ 0 voor geen en anders bijv. 124");
		System.out.print("Invoer: ");
		String str = input.nextLine();
		for (int i = 0; i < str.length(); i++){
			int a = Integer.parseInt(str.substring(i,i+1));
			if (a != 0) {
				blokArray[a-1] = 1;
			} else {
				eindeBeurt = true;
			}
		}
		
		
	}
	
	void gooien(int a){
		int i = 0;
		for (Dobbelstenen dobb : stenen){
			if (blokArray[i] != 1) dobb.aantalOgen = dobb.werpen();
			huidigeWorp[i] = dobb.aantalOgen;
			i++;
		}
		System.out.println("Worp "+ a +":");
		System.out.println("posities:\t" + Arrays.toString(posArr));
		System.out.println("ogen: \t\t" + Arrays.toString(huidigeWorp));
		
	}
	
	
}

class Dobbelstenen{
	int aantalOgen;
	int werpen(){
		Random getal = new Random();
		return getal.nextInt(6)+1;
	}
}

class Worp{
	int[] worp = new int[5];
	Worp(int[] x){
		worp = x;
	}
	void uitslag(){
		System.out.println("Deze beurt had als resultaat " + Arrays.toString(worp));
	}
}

class Speler{
	ArrayList<Worp> history = new ArrayList<>();
}
