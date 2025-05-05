public class Ticket implements Comparable < Ticket > {
    private static int nextId = 1;
    private final int id;
    private final User creator;
    private final long creationTime;
    private int priority; // 1=High, 2=Medium, 3=Low
    private String description;
    private boolean closed;

    public Ticket(User creator, int priority, String description) {
        this.id = nextId++;
        this.creator = creator;
        this.priority = priority;
        this.description = description;
        this.creationTime = System.currentTimeMillis();
        this.closed = false;
    }

    public int getId() {
        return id;
    }
    public User getCreator() {
        return creator;
    }
    public long getCreationTime() {
        return creationTime;
    }
    public int getPriority() {
        return priority;
    }
    public String getDescription() {
        return description;
    }
    public boolean isClosed() {
        return closed;
    }
    public void close() {
        this.closed = true;
    }

    @Override
    public int compareTo(Ticket other) {
        int cmp = Integer.compare(this.priority, other.priority);
        if (cmp == 0) {
            return Long.compare(this.creationTime, other.creationTime);
        }
        return cmp;
    }

    @Override
    public String toString() {
        return String.format("Ticket[id=%d, priority=%d, closed=%b]", id, priority, closed);
    }

    public void open() {
        this.closed = false;
    }

}