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
            t.open();
        }
    }
    
    public ArrayList<Ticket> getAllTickets() {
        return tickets;
    }
}


