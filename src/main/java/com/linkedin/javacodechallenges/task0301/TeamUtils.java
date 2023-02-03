package com.linkedin.javacodechallenges.task0301;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TeamUtils {

    //in comments: the editor's choice

    public static void generateTeamsScores(List<Team> teams, int numberOfRounds) {
        Random random = new Random();
        teams.forEach(team -> {
            for (int i = 0; i < numberOfRounds; i++) {
                team.getScores().add(random.nextInt(11));
            }
        });
    }

    public static void revealResults(List<Team> teams) {

        //if (teams.size() == 0 || teams.stream().allMatch(team -> team.getScores().isEmpty())) {
        if (teams.isEmpty() || teams.stream().allMatch(team -> team.getScores().isEmpty())) {
            System.out.print("The game hasn't started yet.\n");
            return;
        }

        //TreeMap<Integer, List<Team>> scoreTeamsMap = teams.stream()
        //        .collect(Collectors.groupingBy(Team::sumTotalScore, TreeMap::new, Collectors.toList()));
        //Iterator<Integer> scoreResultsIterator = scoreTeamsMap.descendingKeySet()
        //        .stream()
        //        .iterator();
        ListIterator<Map.Entry<Integer, List<Team>>> scoreTeamsSorted = teams.stream()
                .collect(Collectors.groupingBy(Team::sumTotalScore, TreeMap::new, Collectors.toList()))
                .descendingMap().entrySet().stream().toList().listIterator();

        System.out.print("Now for the results, the WINNER is...\n");
        Map.Entry<Integer, List<Team>> first = scoreTeamsSorted.next();
        //announceResult(scoreResultsIterator.next(), scoreTeamsMap);
        announceResult(first);

        //while (scoreResultsIterator.hasNext()) {
        //  System.out.println("Then we have... ");
        //  announceResult(scoreResultsIterator.next(), scoreTeamsMap);
        //}
        while (scoreTeamsSorted.hasNext()) {
            System.out.print("Then we have... \n");
            announceResult(scoreTeamsSorted.next());
        }
    }

    //private static void announceResult(int score, TreeMap<Integer, List<Team>> scoreTeamsMap) {
    private static void announceResult(Map.Entry<Integer, List<Team>> scoreAndTeams) {

        //List<Team> teams = scoreTeamsMap.get(score);
        List<Team> teams = scoreAndTeams.getValue();

        if (teams.size() > 1) {
            System.out.print("It's a TIE!\n");
        }

        //teams.forEach(team -> System.out.println("With " + score + " points, it's team " + team.getPlayer1() + " and " + team.getPlayer2() + "!"));
        teams.forEach(team -> System.out.print("With " + team.sumTotalScore() + " points, it's team " + team.getPlayer1() + " and " + team.getPlayer2() + "!\n"));

        System.out.print("\n");
    }

}