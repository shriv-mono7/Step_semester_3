package Day10.class_problems;

import java.util.Arrays;

class Resource {
    String id;
    String type;
    boolean reservable;
    boolean downloadable;

    Resource(String id, String type,
             boolean reservable, boolean downloadable) {
        this.id = id;
        this.type = type;
        this.reservable = reservable;
        this.downloadable = downloadable;
    }
}

public class LibraryPlatform {
    static Resource[] resources = new Resource[100];
    static int size = 0;

    static int find(String id) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = resources[mid].id.compareTo(id);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    static void add(Resource resource) {
        if (find(resource.id) != -1) {
            System.out.println("duplicate rejected");
            return;
        }

        int index = 0;

        while (index < size
                && resources[index].id.compareTo(resource.id) < 0) {
            index++;
        }

        for (int i = size; i > index; i--) {
            resources[i] = resources[i - 1];
        }

        resources[index] = resource;
        size++;

        System.out.println(resource.id + " added");
    }

    static void reserve(String id, String member) {
        int index = find(id);

        if (index == -1) {
            System.out.println(id + " not found");
        } else if (!resources[index].reservable) {
            System.out.println(id + " rejected: reserve unsupported");
        } else {
            System.out.println(id + " reserved for " + member);
        }
    }

    static void download(String id) {
        int index = find(id);

        if (index == -1) {
            System.out.println(id + " not found");
        } else if (!resources[index].downloadable) {
            System.out.println(id + " rejected: download unsupported");
        } else {
            System.out.println(id + " downloaded");
        }
    }

    public static void main(String[] args) {
        add(new Resource("B1", "Book", true, false));
        add(new Resource("B1", "Book", true, false));
        add(new Resource("E1", "EBook", false, true));

        reserve("B1", "M1");
        download("B1");
        download("E1");

        int index = find("E1");

        if (index != -1) {
            System.out.println("E1 found at index " + index);
        } else {
            System.out.println("E1 not found");
        }
    }
}