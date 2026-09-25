
package Day5.assignment_problems;

public class LibraryMemberJavaBeanChainedConstructorsSecurityAnswer {

    static class LibraryMember {

        private String membershipId;
        private String name;
        private boolean premiumMember;

        private String securityAnswerHash;

        private boolean membershipIdSet;

        // No-argument constructor
        public LibraryMember() {
            this(null, null);
        }

        // Name-only constructor
        public LibraryMember(String name) {
            this(null, name);
        }

        // ID and name constructor
        public LibraryMember(String membershipId, String name) {

            this.membershipId = membershipId;
            this.name = name;

            if (membershipId != null && !membershipId.isEmpty()) {
                membershipIdSet = true;
            }
        }

        // Getter for membership ID
        public String getMembershipId() {
            return membershipId;
        }

        // Write-once setter for membership ID
        public void setMembershipId(String id) {

            if (!membershipIdSet
                    && id != null
                    && !id.trim().isEmpty()) {

                membershipId = id;
                membershipIdSet = true;
            }
        }

        // Getter for name
        public String getName() {
            return name;
        }

        // Setter for name
        public void setName(String name) {
            this.name = name;
        }

        // Boolean JavaBean getter
        public boolean isPremiumMember() {
            return premiumMember;
        }

        // Boolean JavaBean setter
        public void setPremiumMember(boolean premium) {
            this.premiumMember = premium;
        }

        // Write-only security answer
        public void setSecurityAnswer(String answer) {

            if (answer == null || answer.isEmpty()) {
                securityAnswerHash = null;
            } else {
                securityAnswerHash = Integer.toHexString(
                        answer.hashCode()
                );
            }
        }

        // No getter for securityAnswer
    }

    public static void main(String[] args) {

        System.out.println("LibraryMember JavaBean Test");
        System.out.println("--------------------------------");

        LibraryMember member1 = new LibraryMember();

        System.out.println(
                "No-argument constructor executed"
        );

        LibraryMember member2 =
                new LibraryMember("Shrivalli");

        System.out.println(
                "Name-only constructor: "
                        + member2.getName()
        );

        LibraryMember member3 =
                new LibraryMember("LB94", "Shrivalli");

        System.out.println(
                "ID and name constructor: "
                        + member3.getMembershipId()
                        + ", "
                        + member3.getName()
        );

        member1.setName("Library User");
        member1.setPremiumMember(true);

        System.out.println(
                "Name: " + member1.getName()
        );

        System.out.println(
                "Premium member: "
                        + member1.isPremiumMember()
        );

        member1.setMembershipId("LB100");

        System.out.println(
                "Initial membership ID: "
                        + member1.getMembershipId()
        );

        member1.setMembershipId("LB200");

        System.out.println(
                "After second ID update: "
                        + member1.getMembershipId()
        );

        member1.setSecurityAnswer("JavaBeanAnswer");

        System.out.println(
                "Security answer stored using write-only setter"
        );

        System.out.println(
                "Security answer has no getter"
        );
    }
}