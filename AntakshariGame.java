import java.io.*;
import java.util.*;

public class AntakshariGame {
    static final int ROUNDS = 5;
    static Set<String> dictionary = new HashSet<>();
    static Set<String> usedWords = new HashSet<>();
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
    loadDictionary("words.txt");

        int playerScore = 0, computerScore = 0;
        String currentWord = getRandomWord();
        usedWords.add(currentWord);
        System.out.println("=== Welcome to Antakshari ===");
        System.out.println("Starter: " + currentWord);

        for (int round = 1; round <= ROUNDS; round++) {
            System.out.println("\n--- Round " + round + " ---");
            List<Character> bonus = generateBonusLetters();
            System.out.println("Bonus letters: " + bonus);

            char expectedStart = currentWord.charAt(currentWord.length() - 1);
            System.out.print("Your word (starts with '" + expectedStart + "'): ");
            String playerWord = scanner.nextLine().trim().toLowerCase();

            while (!isValid(playerWord, expectedStart)) {
                System.out.print("Invalid. Try again: ");
                playerWord = scanner.nextLine().trim().toLowerCase();
            }

            usedWords.add(playerWord);
            currentWord = playerWord;

            expectedStart = currentWord.charAt(currentWord.length() - 1);
            String computerWord = getComputerWord(expectedStart);
            usedWords.add(computerWord);
            currentWord = computerWord;

            int playerPoints = score(playerWord, bonus);
            int computerPoints = score(computerWord, bonus);
            playerScore += playerPoints;
            computerScore += computerPoints;

            System.out.println("Computer's word: " + computerWord);
            System.out.println("You earned: " + playerPoints + " | Total: " + playerScore);
            System.out.println("Computer earned: " + computerPoints + " | Total: " + computerScore);
        }
        
        System.out.println("\n=== Final Score ===");
        System.out.println("You: " + playerScore);
        System.out.println("Computer: " + computerScore);

        if (playerScore > computerScore) System.out.println("You win :)");
        else if (playerScore < computerScore) System.out.println("You lose TT");
        else System.out.println("Tie");
    }

    static void loadDictionary(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
                dictionary.add(line.trim().toLowerCase());
        } catch (IOException e) {
            System.out.println("Couldn't load dictionary.");
            System.exit(1);
        }
    }

    static String getRandomWord() {
        List<String> list = new ArrayList<>(dictionary);
        String word;
        do word = list.get(random.nextInt(list.size()));
        while (usedWords.contains(word) || word.length() < 2);
        return word;
    }

    static boolean isValid(String word, char start) {
        return word.charAt(0) == start &&
               dictionary.contains(word) &&
               !usedWords.contains(word);
    }

    static String getComputerWord(char start) {
        for (String word : dictionary)
            if (!usedWords.contains(word) && word.charAt(0) == start)
                return word;
        return null;
    }

    static List<Character> generateBonusLetters() {
        List<Character> bonus = new ArrayList<>();
        while (bonus.size() < 3) {
            char c = (char) ('a' + random.nextInt(26));
            if (!bonus.contains(c)) bonus.add(c);
        }
        return bonus;
    }

    static int score(String word, List<Character> bonus) {
        int points = word.length();
        for (char c : word.toCharArray())
            if (bonus.contains(c)) points += 2;
        return points;
    }
}