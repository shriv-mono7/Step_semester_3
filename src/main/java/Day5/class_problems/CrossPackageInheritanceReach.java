package Day5.class_problems;

public class CrossPackageInheritanceReach {

    // Classify access based on field modifier and accessor context
    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        fieldModifier = fieldModifier.toLowerCase();

        switch (fieldModifier) {

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

    // Convert context code into readable title-case text
    static String describeContext(String accessorContext) {

        if (accessorContext == null || accessorContext.isEmpty()) {
            return "";
        }

        String[] words = accessorContext.split("_");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            result.append(
                    word.substring(0, 1).toUpperCase()
                            + word.substring(1).toLowerCase()
            );

            result.append(" ");
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {

        System.out.println(
                classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
                )
        );

        System.out.println(
                classifyAccess(
                        "protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println(
                describeContext(
                        "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
                )
        );

        System.out.println(
                classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
                classifyAccess("private", "SAME_PACKAGE")
        );

        System.out.println(
                classifyAccess("default", "SAME_PACKAGE")
        );

        System.out.println(
                classifyAccess("public", "DIFFERENT_PACKAGE")
        );
    }
}