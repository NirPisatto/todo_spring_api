import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class UUIDGenerator implements RunnableFuture {
    private final ArrayList<ArrayList<UUID>> setUuids;

    private final long id;
    private final long size;
    public UUIDGenerator(long id, long size,ArrayList<ArrayList<UUID>> setUuids) {
        this.setUuids = setUuids;
        this.id = id;
        this.size = size;
    }
    @Override
    public void run() {
        System.out.println("["+id+"] Started generating UUIDs...");
        ArrayList<UUID> uuids = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            uuids.add(UUID.randomUUID());
        }
        this.setUuids.add(uuids);
        System.out.println("["+id+"] Ended generating UUIDs");
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return "hello";
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
