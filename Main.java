import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SupportSystem sys = new SupportSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Tech Support Ticketing System!");
        boolean running = true;

        while (running) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Create Ticket");
            System.out.println("2. Search Ticket by ID");
            System.out.println("3. View All Tickets");
            System.out.println("4. Serve Next Ticket");
            System.out.println("5. Close a Ticket");
            System.out.println("6. Undo Last Ticket Closure");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter ticket priority (1=High, 2=Medium, 3=Low): ");
                    int priority = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter ticket description: ");
                    String desc = scanner.nextLine();

                    User user = new User(name, email);
                    Ticket newTicket = sys.createTicket(user, priority, desc);
                    System.out.println("Ticket created! ID: " + newTicket.getId());
                    break;

                case "2":
                    System.out.print("Enter Ticket ID to search: ");
                    int searchId = Integer.parseInt(scanner.nextLine());
                    Ticket found = sys.getTicketById(searchId);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Ticket not found.");
                    }
                    break;

                case "3":
                    sys.sortByPriority();
                    System.out.println("All Tickets (Sorted by Priority):");
                    for (Ticket ticket : sys.getAllTickets()) {
                        System.out.println(ticket);
                    }
                    break;

                case "4":
                    Ticket next = sys.serveNext();
                    if (next != null) {
                        System.out.println("Serving Ticket: " + next);
                    } else {
                        System.out.println("No pending tickets.");
                    }
                    break;

                case "5":
                    System.out.print("Enter Ticket ID to close: ");
                    int closeId = Integer.parseInt(scanner.nextLine());
                    sys.closeTicket(closeId);
                    System.out.println("Ticket " + closeId + " marked as closed.");
                    break;

                case "6":
                    sys.undoClose();
                    System.out.println("Last ticket closure undone.");
                    break;

                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Try again with a valid option.");
            }
        }
        scanner.close();
    }
}
