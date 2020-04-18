import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Yahtzee { 
    public static void main(String[] args) {
		YahtzeeSpel ySpel = new YahtzeeSpel();
		ySpel.spelen();
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
		System.out.println("Welkom bij Yahtzee!\n");
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
			speler1.history.get(speler1.history.size()-1).uitslag();
			System.out.println("\n================================\n");
			System.out.println("Druk Enter voor de eerste worp van de volgende beurt. Druk q voor het stoppen van het spel.");
		}
		System.out.println("\nU heeft het spel gestopt, dit zijn uw resultaten:");
		System.out.println("Aantal beurten: " + speler1.history.size());
		int totaal = 0;
		for (Worp w : speler1.history){
			totaal = totaal + w.score;
		}
		System.out.println("Totale score: " + totaal);	
	}
	
	void vasthouden(){
		for(int j = 0; j < blokArray.length; j++){
			blokArray[j] = 0;
		}
		System.out.println("\nWelke posities wilt u vasthouden? typ 0 voor geen en anders bijv. 124");
		System.out.print("Invoer: ");
		String str = input.nextLine();
		for (int i = 0; i < str.length(); i++){
			int a = Integer.parseInt(str.substring(i,i+1));
			if (a != 0) blokArray[a-1] = 1;
		}
		if(blokArray[0] == 1 && blokArray[1] == 1 && blokArray[2] == 1 && blokArray[3] == 1 && blokArray[4] == 1) eindeBeurt = true;
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
	Scanner input = new Scanner(System.in);
	int[] worp = new int[5];
	int score;
	Worp(int[] x){
		worp = x;
	}
	void uitslag(){
		System.out.println("\nDeze beurt had als resultaat " + Arrays.toString(worp));
		System.out.print("Welke score schrijft u hiervoor op: ");
		score = input.nextInt();
	}
}

class Speler{
	ArrayList<Worp> history = new ArrayList<>();
}
