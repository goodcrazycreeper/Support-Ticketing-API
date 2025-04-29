import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class SupportSystem {
    private final ArrayList<Ticket> tickets = new ArrayList<>();
    private final BST<Integer, Ticket> ticketTree = new BST<>();
    private final TicketQueue pendingQueue = new TicketQueue();
    private final Stack<Ticket> undoStack = new Stack<>();

    public Ticket createTicket(User user, int priority, String desc) {
        Ticket t = new Ticket(user, priority, desc);
        tickets.add(t);
        ticketTree.insert(t.getId(), t);
        pendingQueue.enqueue(t);
        return t;
    }

    public Ticket getTicketById(int id) {
        return ticketTree.search(id);
    }

    public void sortByPriority() {
        Collections.sort(tickets);
    }

    public Ticket serveNext() {
        return pendingQueue.dequeue();
    }

    public void closeTicket(int id) {
        Ticket t = getTicketById(id);
        if (t != null && !t.isClosed()) {
            t.close();
            undoStack.push(t);
        }
    }

    public void undoClose() {
        if (!undoStack.isEmpty()) {
            Ticket t = undoStack.pop();
            // cannot reopen closed boolean, add reopen method if needed
            // For now, we simply mark as open
            // t.open();
        }
    }
    
    SupportSystem sys = new SupportSystem();

    User alice = new User("alice","alice@example.com");
    Ticket t1 = sys.createTicket(alice, 1, "Cannot login");
    Ticket t2 = sys.createTicket(alice, 2, "Page error");

    Ticket found = sys.getTicketById(t1.getId()); 
    System.out.println("Found: " + found);

    // 4) Sort all tickets (by priority, then time)
    sys.sortByPriority();
    System.out.println("Sorted tickets: " + sys.getAllTickets());

    // 5) Serve next pending ticket (FIFO queue)
    Ticket next = sys.serveNext();
    System.out.println("Serving: " + next);

    // 6) Close a ticket and push to undo stack
    sys.closeTicket(t1.getId());

    // 7) Undo the last close
    sys.undoClose();

}


