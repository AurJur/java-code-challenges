package com.linkedin.javacodechallenges.task0301;

import java.util.List;

/**
 * @author Aurelijus Jurkus
 * @since 16-Jan-2023
 */
public class App0301 {
    public static void main(String[] args) {
        Team team1 = new Team("Sally", "Roger");
        Team team2 = new Team("Eric", "Rebecca");
        Team team3 = new Team("Tony", "Shannon");

        List<Team> teams = List.of(team1, team2, team3);
        int numberOfRounds = 4;

        TeamUtils.generateTeamsScores(teams, numberOfRounds);

        TeamUtils.revealResults(teams);
    }
}
