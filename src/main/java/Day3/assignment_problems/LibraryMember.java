package Day3.assignment_problems;

public class LibraryMember {

    /*
     * BROKEN VERSION
     *
     * All fields are static, so they are shared by every member.
     * This is wrong because name, memberId and booksIssued
     * belong to individual members.
     */
    static class BrokenLibraryMember {

        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(
                String name,
                String memberId,
                int booksIssued) {

            BrokenLibraryMember.name = name;
            BrokenLibraryMember.memberId = memberId;
            BrokenLibraryMember.booksIssued = booksIssued;
        }
    }

    /*
     * FIXED VERSION
     *
     * name, memberId and booksIssued are instance fields because
     * every library member has their own values.
     *
     * libraryName and memberCount are static because they belong
     * to the library as a whole.
     */
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "SRM Central Library";
    static int memberCount = 0;

    public LibraryMember(String name, int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;

        this.memberId = "LIB" + memberCount;
    }

    public void printMemberCard() {

        System.out.println(
            name + " | " + memberId
            + " | Books issued: " + booksIssued
        );
    }

    public static void printTotalMembers() {

        System.out.println(
            "Total members: " + memberCount
        );
    }

    public static void main(String[] args) {

        // ---------- BROKEN VERSION ----------

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember(
                        "Ravi",
                        "LIB001",
                        2
                );

        BrokenLibraryMember member2 =
                new BrokenLibraryMember(
                        "Meera",
                        "LIB002",
                        3
                );

        System.out.println(
            BrokenLibraryMember.name
        );

        System.out.println(
            BrokenLibraryMember.name
        );

        /*
         * Both print Meera because name is static.
         * Creating member2 overwrites the shared field.
         */

        // ---------- FIXED VERSION ----------

        System.out.println("\nFixed version:");

        LibraryMember ravi =
                new LibraryMember("Ravi", 2);

        LibraryMember meera =
                new LibraryMember("Meera", 3);

        ravi.printMemberCard();
        meera.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}