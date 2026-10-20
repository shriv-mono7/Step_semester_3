
package Day8.assignment_problems;

import java.util.*;

interface ScoringRule {
    double calculateScore(double idea, double execution,
                          double presentation);
}

class InnovationScoring implements ScoringRule {
    public double calculateScore(double idea, double execution,
                                 double presentation) {
        return idea * 0.50 + execution * 0.30
                + presentation * 0.20;
    }
}

class OpenScoring implements ScoringRule {
    public double calculateScore(double idea, double execution,
                                 double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Project {
    String name;
    double idea, execution, presentation;
    boolean scored = false;

    Project(String name) {
        this.name = name;
    }

    void recordScore(double idea, double execution,
                     double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
        scored = true;
    }
}

class Team {
    String name;
    List<Student> members;
    ScoringRule scoringRule;
    Project project;

    Team(String name, List<Student> members,
         ScoringRule scoringRule) {
        this.name = name;
        this.members = members;
        this.scoringRule = scoringRule;
    }

    boolean submitProject(String projectName) {
        if (project != null) {
            System.out.println("Submission failed: Team can submit only one project.");
            return false;
        }

        project = new Project(projectName);
        System.out.println("Project '" + projectName
                + "' submitted by " + name + ".");
        return true;
    }

    double getFinalScore() {
        return scoringRule.calculateScore(
                project.idea, project.execution,
                project.presentation);
    }
}

class Hackathon {
    private List<Team> teams = new ArrayList<>();
    private Set<String> registeredStudents = new HashSet<>();
    private boolean published = false;

    void registerTeam(Team team) {
        if (published) {
            System.out.println("Registration failed: Results are published.");
            return;
        }

        if (team.members.size() < 2 || team.members.size() > 4) {
            System.out.println(
                    "Registration failed: A team must have 2 to 4 members.");
            return;
        }

        for (Student student : team.members) {
            if (registeredStudents.contains(student.name)) {
                System.out.println("Registration failed: "
                        + student.name + " already belongs to a team.");
                return;
            }
        }

        teams.add(team);

        for (Student student : team.members) {
            registeredStudents.add(student.name);
        }

        String track = team.scoringRule instanceof InnovationScoring
                ? "Innovation" : "Open";

        System.out.println("Team " + team.name + " registered ("
                + team.members.size() + " members, "
                + track + " track).");
    }

    void scoreProject(Team team, double idea,
                      double execution, double presentation) {
        if (published) {
            System.out.println(
                    "Rescore rejected: Results have already been published.");
            return;
        }

        if (!teams.contains(team) || team.project == null) {
            System.out.println("Scoring failed: Team or project not found.");
            return;
        }

        if (idea < 0 || idea > 10 || execution < 0
                || execution > 10 || presentation < 0
                || presentation > 10) {
            System.out.println("Scores must be between 0 and 10.");
            return;
        }

        team.project.recordScore(idea, execution, presentation);
        System.out.println("Score recorded for '"
                + team.project.name + "'.");
    }

    void publishResults() {
        published = true;

        for (Team team : teams) {
            if (team.project != null && team.project.scored) {
                System.out.printf(Locale.US,
                        "Final score: %.2f%n", team.getFinalScore());
            }
        }

        System.out.println("Results published.");
    }
}

public class CodeSprintJudgingDesk {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();

        Team byteBusters = new Team(
                "ByteBusters",
                Arrays.asList(
                        new Student("Asha"),
                        new Student("Ravi"),
                        new Student("Neha")),
                new InnovationScoring());

        Team soloCoder = new Team(
                "SoloCoder",
                Arrays.asList(new Student("Kiran")),
                new OpenScoring());

        hackathon.registerTeam(byteBusters);
        hackathon.registerTeam(soloCoder);

        byteBusters.submitProject("SmartAttend");

        hackathon.scoreProject(byteBusters, 8, 7, 9);

        hackathon.publishResults();

        hackathon.scoreProject(byteBusters, 10, 7, 9);
    }
}
