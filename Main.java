public class Main {
    public static void main(String[] args) {
        SupportSystem sys = new SupportSystem();

        User alice = new User("alice", "alice@example.com");
        Ticket t1 = sys.createTicket(alice, 1, "Cannot login");
        Ticket t2 = sys.createTicket(alice, 2, "Page error");

        // Search for a ticket by ID
        System.out.println("Found Ticket: " + sys.getTicketById(t1.getId()));

        // Sort tickets by priority
        sys.sortByPriority();
        System.out.println("All Tickets Sorted by Priority:");
        for (Ticket ticket : sys.getAllTickets()) {
            System.out.println(ticket);
        }

        // Serve next pending ticket (first-in, first-out)
        Ticket nextServed = sys.serveNext();
        if (nextServed != null) {
            System.out.println("Serving Ticket: " + nextServed);
        } else {
            System.out.println("No tickets to serve.");
        }

        // Close a ticket and push it to the undo stack
        sys.closeTicket(t1.getId());
        System.out.println("Closed Ticket ID: " + t1.getId());

        // Undo the last ticket closure
        sys.undoClose();
        System.out.println("Undo ticket close operation performed.");
    }
}
