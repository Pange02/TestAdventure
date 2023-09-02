import java.util.Scanner;
public class Game {    
    public Game() 
    {
        Item.fillitemlist();
        Room room1 = new Room(true);
        
        System.out.println("Lege deinen Namen fest:");
        Scanner nameparser = new Scanner(System.in);
        Player player1 = new Player(nameparser.nextLine(), room1);
        System.out.println("Willkommen " + Player.getplayername(player1) + "!");
        System.out.println(" ");
        
        Parser mainparser = new Parser(player1);
    }
}