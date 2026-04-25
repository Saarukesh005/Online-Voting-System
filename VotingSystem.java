import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VotingSystem {

    static String[] candidates = {"Alice", "Bob", "Charlie"};
    static int[] votes = {0, 0, 0};
    static HashSet<String> voters = new HashSet<>();

    static boolean votingEnded = false;

    public static void main(String[] args) {
        createMainUI();
    }

    // MAIN UI
    static void createMainUI() {
        JFrame frame = new JFrame("Online Voting System");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 1));

        JButton voteBtn = new JButton("Vote");
        JButton endBtn = new JButton("End Voting (Admin)");
        JButton resultBtn = new JButton("View Results");
        JButton exitBtn = new JButton("Exit");

        voteBtn.addActionListener(e -> openVotingUI());
        endBtn.addActionListener(e -> endVoting());
        resultBtn.addActionListener(e -> showResults());
        exitBtn.addActionListener(e -> System.exit(0));

        frame.add(voteBtn);
        frame.add(endBtn);
        frame.add(resultBtn);
        frame.add(exitBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // VOTING UI
    static void openVotingUI() {

        if (votingEnded) {
            JOptionPane.showMessageDialog(null, "Voting has ended!");
            return;
        }

        String name = JOptionPane.showInputDialog("Enter your name:");

        if (name == null || name.trim().isEmpty()) return;

        if (voters.contains(name)) {
            JOptionPane.showMessageDialog(null, "You already voted!");
            return;
        }

        JFrame voteFrame = new JFrame("Vote Now");
        voteFrame.setSize(400, 300);
        voteFrame.setLayout(new GridLayout(candidates.length, 1));

        for (int i = 0; i < candidates.length; i++) {
            int index = i;

            JButton btn = new JButton("Vote for " + candidates[i]);

            btn.addActionListener(e -> {
                votes[index]++;
                voters.add(name);
                JOptionPane.showMessageDialog(null, "Vote Submitted!");
                voteFrame.dispose();
            });

            voteFrame.add(btn);
        }

        voteFrame.setVisible(true);
    }

    // END VOTING
    static void endVoting() {
        votingEnded = true;
        JOptionPane.showMessageDialog(null, "Voting ended by Admin!");
    }

    // RESULTS UI
    static void showResults() {

        if (!votingEnded) {
            JOptionPane.showMessageDialog(null, "Results are hidden until voting ends!");
            return;
        }

        StringBuilder result = new StringBuilder("FINAL RESULTS:\n\n");

        int maxVotes = votes[0];
        int winnerIndex = 0;

        for (int i = 0; i < candidates.length; i++) {
            result.append(candidates[i])
                  .append(" : ")
                  .append(votes[i])
                  .append(" votes\n");

            if (votes[i] > maxVotes) {
                maxVotes = votes[i];
                winnerIndex = i;
            }
        }

        result.append("\nWinner: ").append(candidates[winnerIndex]);

        JOptionPane.showMessageDialog(null, result.toString());
    }
}