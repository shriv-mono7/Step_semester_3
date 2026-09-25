package Day5.assignment_problems;

public class ReferenceDeskSubclassReach {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

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
                        || accessorContext.equals(
                                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                    return "ALLOWED";
                }

                if (accessorContext.equals(
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

                    return "DENIED";
                }

                return "DENIED";

            case "public":

                return "ALLOWED";

            default:

                return "DENIED";
        }
    }

    static String describeContext(String context) {

        if (context == null || context.isEmpty()) {
            return "";
        }

        String[] words = context.split("_");

        StringBuilder result = new StringBuilder();

        for (String word : words) {

            if (word.isEmpty()) {
                continue;
            }

            String formattedWord =
                    word.substring(0, 1).toUpperCase()
                            + word.substring(1).toLowerCase();

            if (result.length() > 0) {
                result.append(" ");
            }

            result.append(formattedWord);
        }

        return result.toString();
    }

    public static void main(String[] args) {

        System.out.println("Reference Desk Subclass Reach");
        System.out.println("--------------------------------");

        System.out.println(
                "Private + SAME_CLASS: "
                        + classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
                "Private + SAME_PACKAGE: "
                        + classifyAccess("private", "SAME_PACKAGE")
        );

        System.out.println(
                "Default + SAME_PACKAGE: "
                        + classifyAccess("default", "SAME_PACKAGE")
        );

        System.out.println(
                "Default + DIFFERENT_PACKAGE: "
                        + classifyAccess("default", "DIFFERENT_PACKAGE")
        );

        System.out.println(
                "Protected + SAME_CLASS: "
                        + classifyAccess("protected", "SAME_CLASS")
        );

        System.out.println(
                "Protected + SAME_PACKAGE: "
                        + classifyAccess("protected", "SAME_PACKAGE")
        );

        System.out.println(
                "Protected + SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE: "
                        + classifyAccess(
                                "protected",
                                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
        );

        System.out.println(
                "Protected + SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE: "
                        + classifyAccess(
                                "protected",
                                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")
        );

        System.out.println(
                "Public + DIFFERENT_PACKAGE: "
                        + classifyAccess("public", "DIFFERENT_PACKAGE")
        );

        System.out.println("\nContext Description:");

        System.out.println(
                describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
        );

        System.out.println(
                describeContext("SAME_PACKAGE")
        );
    }
}