import java.util.*;

public class wp1 {

    private HashMap<String, Integer> users = new HashMap<>();
    private HashMap<String, Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public void register(String username, int userId) {
        users.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            suggestions.add(username + i);
        }
        return suggestions;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        wp1 system = new wp1();

        system.register("john_doe", 101);
        system.register("admin", 102);

        System.out.print("Enter username to check: ");
        String username = sc.nextLine();

        if (system.checkAvailability(username)) {
            System.out.println("Username is available");
        } else {
            System.out.println("Username already taken");
            System.out.println("Suggestions: " + system.suggestAlternatives(username));
        }

        sc.close();
    }
}