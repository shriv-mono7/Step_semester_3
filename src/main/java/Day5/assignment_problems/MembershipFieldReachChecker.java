package Day5.assignment_problems;

public class MembershipFieldReachChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier.toLowerCase()) {

            case "private":
                if (accessorContext.equals("SAME_CLASS")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "default":
                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "protected":
                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")
                        || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    static String summarizeByModifier(String[][] attempts) {

        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;

        for (String[] attempt : attempts) {

            String modifier = attempt[0];
            String context = attempt[1];

            String result = classifyAccess(modifier, context);

            if (modifier.equals("private")) {
                if (result.equals("ALLOWED")) {
                    privateAllowed++;
                } else {
                    privateDenied++;
                }
            }

            else if (modifier.equals("default")) {
                if (result.equals("ALLOWED")) {
                    defaultAllowed++;
                } else {
                    defaultDenied++;
                }
            }

            else if (modifier.equals("protected")) {
                if (result.equals("ALLOWED")) {
                    protectedAllowed++;
                } else {
                    protectedDenied++;
                }
            }

            else if (modifier.equals("public")) {
                if (result.equals("ALLOWED")) {
                    publicAllowed++;
                } else {
                    publicDenied++;
                }
            }
        }

        return "private: " + privateAllowed + " allowed / "
                + privateDenied + " denied | "

                + "default: " + defaultAllowed + " allowed / "
                + defaultDenied + " denied | "

                + "protected: " + protectedAllowed + " allowed / "
                + protectedDenied + " denied | "

                + "public: " + publicAllowed + " allowed / "
                + publicDenied + " denied";
    }

    static class LibraryMember {

        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId,
                             String branchCode,
                             double finesOwed,
                             String displayName) {

            if (membershipId == null
                    || membershipId.trim().isEmpty()
                    || membershipId.trim().length() < 4) {

                throw new IllegalArgumentException(
                        "Invalid membership ID"
                );
            }

            this.membershipId = membershipId;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }
    }

    public static void main(String[] args) {

        System.out.println("Access Classification:");

        System.out.println(
                "private + SAME_CLASS: "
                        + classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
                "private + SAME_PACKAGE: "
                        + classifyAccess("private", "SAME_PACKAGE")
        );

        System.out.println(
                "default + SAME_PACKAGE: "
                        + classifyAccess("default", "SAME_PACKAGE")
        );

        System.out.println(
                "protected + DIFFERENT_PACKAGE: "
                        + classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        System.out.println(
                "public + DIFFERENT_PACKAGE: "
                        + classifyAccess("public", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {

                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},

                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},

                {"protected", "SAME_CLASS"},
                {"protected", "SAME_PACKAGE"},

                {"public", "SAME_CLASS"}
        };

        System.out.println("\nSummary:");

        System.out.println(summarizeByModifier(attempts));

        System.out.println("\nMembership Validation:");

        try {

            LibraryMember member1 = new LibraryMember(
                    "LB9",
                    "BR01",
                    100.0,
                    "Shrivalli"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "LB9: Construction rejected"
            );
        }

        try {

            LibraryMember member2 = new LibraryMember(
                    "LB94",
                    "BR01",
                    100.0,
                    "Shrivalli"
            );

            System.out.println(
                    "LB94: Construction successful"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "LB94: Construction rejected"
            );
        }
    }
}