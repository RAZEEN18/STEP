import java.util.*;

public class wp2 {

    private HashMap<String, Integer> stock = new HashMap<>();
    private HashMap<String, Queue<Integer>> waitingList = new HashMap<>();

    public synchronized String purchaseItem(String productId, int userId) {

        if (!stock.containsKey(productId)) {
            return "Product not found";
        }

        int available = stock.get(productId);

        if (available > 0) {
            stock.put(productId, available - 1);
            return "Purchase successful. Remaining stock: " + (available - 1);
        } else {
            waitingList.putIfAbsent(productId, new LinkedList<>());
            waitingList.get(productId).add(userId);
            return "Out of stock. Added to waiting list. Position: " +
                    waitingList.get(productId).size();
        }
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedList<>());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        wp2 system = new wp2();

        system.addProduct("IPHONE15_256GB", 5);

        while (true) {

            System.out.println("\n1. Check Stock");
            System.out.println("2. Purchase Item");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter product ID: ");
                String pid = sc.next();
                System.out.println("Available stock: " + system.checkStock(pid));
            }

            else if (choice == 2) {
                System.out.print("Enter product ID: ");
                String pid = sc.next();
                System.out.print("Enter user ID: ");
                int uid = sc.nextInt();
                System.out.println(system.purchaseItem(pid, uid));
            }

            else if (choice == 3) {
                break;
            }
        }

        sc.close();
    }
}