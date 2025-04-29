public class Main {
    public static void main(String[] args) {
        SupportSystem sys = new SupportSystem();
        User alice = new User("alice", "alice@example.com");
        Ticket t1 = sys.createTicket(alice, 1, "Cannot login");
        Ticket t2 = sys.createTicket(alice, 2, "Page error");

        // Search
        System.out.println(sys.getTicketById(t1.getId()));
        
        // Sort
        sys.sortByPriority();
        System.out.println("All tickets sorted: " + sys);

        // Serve
        System.out.println("Serving: " + sys.serveNext());

        // Close and undo
        sys.closeTicket(t1.getId());
        sys.undoClose();
    }
}
