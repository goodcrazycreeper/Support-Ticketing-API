import java.util.LinkedList;
import java.util.Queue;
public class TicketQueue {
    private final Queue < Ticket > queue = new LinkedList < > ();
    public void enqueue(Ticket t) {
        queue.offer(t);
    }
    public Ticket dequeue() {
        return queue.poll();
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}