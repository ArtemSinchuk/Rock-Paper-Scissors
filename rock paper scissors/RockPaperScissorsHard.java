import java.util.*;

enum Hand{
  ROCK, SCISSOR, PAPER, WELL;

  private static Map<Hand, Set<Hand>> beatsMap = Map.of(
    ROCK, Set.of(SCISSOR),
    SCISSOR, Set.of(PAPER),
    PAPER, Set.of(ROCK, WELL),
    WELL, Set.of(ROCK, SCISSOR)
  );

  private static Random random = new Random();

  boolean beats(Hand other) {
        return beatsMap.get(this).contains(other);
  }

  static Hand random() {
    Hand[] options = Hand.values();
    int selected = random.nextInt(options.length);
    return options[selected];
  }
}

abstract class Player {
  final String name;
   int score;
   Hand lastHand = null;

   Player(String name) {
    this.name = name;
   }

   void reset() {
    score = 0;
    lastHand = null;
   }

   Hand drawHand(){
    lastHand = selectHand();
    return lastHand;
   }
   protected abstract Hand selectHand();

}

class ComputerPlayer extends Player{
    ComputerPlayer(String name){
      super(name);
    }

    protected Hand selectHand() {
      return Hand.random();
    }
}

class Game {

    static void selectHands(Player[] players) {
      players[0].drawHand();
      players[1].drawHand();
    }

    static void showSelectedHands(Player[] players) {
      for (Player player: players) {
        System.out.printf("%s drawed %s\n", player.name, player.lastHand);
      }
    }

    static Player evaluateRound(Player[] players) {
      Hand first = players[0].lastHand;
      Hand second = players[1].lastHand;
      if (first == second) {
        return null;
      }

      if (first.beats(second)){
          return players[0];
      }else{
        return players[1];
      }
    }
    
    static void showWinner(Player winner) {
      if (winner == null) {
        System.out.println("Draw!");
      }else{
        System.out.printf("%s wins this round\n", winner.name);
        winner.score++;
      }
    }

    static void showScore(Player[] players) {
      System.out.printf("Score: %s %d : %s %d\n", 
      players[0].name, 
      players[0].score,
      players[1].name, 
      players[1].score);
    }

  static void Play(int maxScore, Player p1, Player p2) {
    p1.reset();
    p2.reset();

    Player[] players = {p1, p2};

    Player winner = null;

    int round = 0;

    while (winner == null || winner.score < maxScore) {
      round++;
      System.out.printf("Round %d\n", round);
      showScore(players);
      selectHands(players);
      showSelectedHands(players);
      winner = evaluateRound(players);
      showWinner(winner);

    }
    System.out.println("Game over!");
    showScore(players);
    System.out.printf("%s has won the game!\n", winner.name);
  }

  public static void main(String[] args) {
    Player left = new ComputerPlayer("Left");
    Player right = new ComputerPlayer("Right");
    Play(3, left, right);
  }
}