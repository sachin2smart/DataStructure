package in.sachinshinde.heap_priority_queue;

import javafx.util.Pair;

import java.util.*;

//  https://leetcode.com/discuss/interview-question/5047205/Robinhood-Phonescreen-or-L4

/*
        A company is famous for its referral program.
        It’s exciting to see our users spreading the word across their friends and family.
        One thing that is interesting about the program is the network effect it creates.
        We would like to build a dashboard to track the status of the program.
        Specifically, we would like to learn about how people refer others through the chain of referral.

        For the purpose of this question, we consider that a person refers all other people down the referral chain.
        For example, A refers B, C, and D in a referral chain of A -> B -> C -> D.
         Please build a leaderboard for the top 3 users who have the most referred users along with the referral count.

        Referral rules:
        ---------------
            -   A user can only be referred once.
            -   Once the user is on the RH platform, he/she cannot be referred by other users.
                For example: if A refers B, no other user can refer A or B since both of them are on the RH platform.
            -   Referrals in the input will appear in the order they were made.

        Leaderboard rules:
        ------------------
            -   The user must have at least 1 referral count to be on the leaderboard.
            -   The leaderboard contains at most 3 users.
            -   The list should be sorted by the referral count in descending order.
            -   If there are users with the same referral count,
                    break the ties by the alphabetical order of the user name.


        Input
        -----
        -----
        rh_users = ["A", "B", "C"]
                    |     |    |
                    v     v    v
        new_users=["B", "C", "D"]


        Output
        -----
        -----
        ["A 3", "B 2", "C 1"]

 */

public class ReferralLeaderboard {

    public String[] getLeaderboard(String[] rh_users, String[] new_users) {
        Map<String, Node> graph = buildGraph(rh_users, new_users);
        PriorityQueue<Pair<Node, Integer>> pq = new PriorityQueue<>((p1, p2) -> {
            if (p2.getValue().equals(p1.getValue())) {
                return p1.getKey().user.compareTo(p2.getKey().user);
            }
            return p2.getValue() - p1.getValue();
        });

        for (String key: graph.keySet()) {
            Node node = graph.get(key);

            int res = graphTraversal(node, 0);

            Pair curPair = new Pair(node, res);
            pq.add(curPair);
        }

        List<String> list = new ArrayList<>();

        while (!pq.isEmpty()) {
            Pair<Node, Integer> curr = pq.poll();
            if (curr.getValue() != null && Arrays.stream(rh_users).anyMatch(r -> r.equals(curr.getKey().user))) {
                list.add(curr.getKey().user + " " + curr.getValue());
            }
        }

        return list.toArray(new String[list.size()]);
    }

    private int graphTraversal(Node node, int count) {
        if(node.children.isEmpty()) {
            return 0;
        }
        int res = 0;
        for(Node child : node.children) {
            res += graphTraversal(child, count + 1) + 1;
        }
        return res;
    }

    private Map<String, Node> buildGraph(String [] rhUsers, String [] newUsers) {
        Map<String, Node> graph = new HashMap<>();

        for(int i = 0; i < rhUsers.length; i++) {
            String rhUser = rhUsers[i];
            String newUser = newUsers[i];

            Node rhUserNode = graph.getOrDefault(rhUser, new Node(rhUser));
            Node newUserNode = graph.getOrDefault(newUser, new Node(newUser));

            rhUserNode.addChild(newUserNode);

            graph.put(rhUser, rhUserNode);
            graph.put(newUser, newUserNode);
        }

        return graph;
    }

    class Node {
        String user;
        List<Node> children;

        Node(String user) {
            this.user = user;
            children = new ArrayList<>();
        }

        void addChild(Node child) {
            this.children.add(child);
        }
    }
    
    public static void main(String[] args) {
        ReferralLeaderboard referralLeaderboard = new ReferralLeaderboard();
        System.out.println(Arrays.toString(referralLeaderboard.getLeaderboard(new String[]{"A","B","C"}, new String[]{"B","C","D"})));
        System.out.println(Arrays.toString(referralLeaderboard.getLeaderboard2(new String[]{"A","B","C"}, new String[]{"B","C","D"})));
    }

    //  Second Approach
     class User {
        String name;
        int count;

        User(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }

    public String[] getLeaderboard2(String[] referrers, String[] referees) {
        // Map to track the referral count of each user
        Map<String, Integer> referralCountMap = new HashMap<>();
        // Set to track the users who have been referred already
        Set<String> referredUsers = new HashSet<>();

        for (int i = 0; i < referrers.length; i++) {
            String referrer = referrers[i];
            String referee = referees[i];

            // If the referee has already been referred, skip this referral
            if (referredUsers.contains(referee)) {
                continue;
            }

            // Mark the referee as referred
            referredUsers.add(referee);

            // Increment the referral count for the referrer
            referralCountMap.put(referrer, referralCountMap.getOrDefault(referrer, 0));

            // Update the referral counts for the entire chain
            String current = referrer;
            while (current != null) {
                referralCountMap.put(current, referralCountMap.getOrDefault(current, 0) + 1);
                current = getReferrer(referrers, referees, current);
            }
        }

        // Create a priority queue to store the users sorted by referral count and name
        PriorityQueue<User> pq = new PriorityQueue<>((a, b) ->
                a.count == b.count ? a.name.compareTo(b.name) : b.count - a.count);

        // Add all users with at least 1 referral count to the priority queue
        for (Map.Entry<String, Integer> entry : referralCountMap.entrySet()) {
            if (entry.getValue() > 0) {
                pq.offer(new User(entry.getKey(), entry.getValue()));
            }
        }

        // Prepare the leaderboard
        List<String> leaderboard = new ArrayList<>();
        while (!pq.isEmpty()) {
            User user = pq.poll();
            leaderboard.add(user.name + " " + user.count);
        }

        // Convert the leaderboard list to an array and return
        return leaderboard.toArray(new String[0]);
    }

    // Helper method to find the referrer of a user
    private static String getReferrer(String[] referrers, String[] referees, String user) {
        for (int i = 0; i < referrers.length; i++) {
            if (referees[i].equals(user)) {
                return referrers[i];
            }
        }
        return null;
    }

}
