import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        ArrayList<ArrayList<UUID>> setUuids = new ArrayList<>();


        ExecutorService executor = Executors.newFixedThreadPool( 8);
        ArrayList<Future<?>> futures = new ArrayList<>();


        for (long i = 0; i < 8; i++) {
            Future<?> get_row_task = executor.submit(new UUIDGenerator(i,1000000, setUuids));
            futures.add(get_row_task);
        }


        for (Future<?>sub_task_reso : futures) {
            try {
                sub_task_reso.get();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        }

        executor.shutdown();

//        for (ArrayList<UUID> uuids : setUuids) {
//            System.out.println(uuids);
//        }
        System.out.println(checkForDuplicates(setUuids));
    }

    static boolean checkForDuplicates(ArrayList<ArrayList<UUID>> arrayOfArrays) {
        // HashSet to store all UUIDs encountered
        Set<UUID> encounteredUUIDs = new HashSet<>();
        for (ArrayList<UUID>array: arrayOfArrays) {
            System.out.println("Checking array for duplicates index " + arrayOfArrays.indexOf(array));
            System.out.println("  Array size: " + array.size());
            // Iterate through each UUID in the array
            for (UUID uuid : array) {
                // If the UUID is already in the set, return true (duplicate found)
                if (!encounteredUUIDs.add(uuid)) {
                    return true;
                }
            }
            System.out.println("  No duplicates found in array index " + arrayOfArrays.indexOf(array));
        }

        // No duplicates found
        return false;
    }
}