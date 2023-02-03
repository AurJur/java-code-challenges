package com.linkedin.javacodechallenges.task0202;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Aurelijus Jurkus
 * @since 15-Jan-2023
 */
public class App0202 {

    public static List<String> findStudentsWithIncompleteVolunteerEvents(List<String> students, Map<String, List<String>> attendeesMapping) {

        Map<String, Integer> studentsAndEventCounts =
                students
                        .stream()
                        .collect(Collectors.toMap(student -> student, initialEventCount -> 0));

        attendeesMapping
                .values()
                .forEach(eventAttendees -> eventAttendees
                        .stream()
                        .filter(studentsAndEventCounts::containsKey)
                        .forEach((filteredEventAttendee -> studentsAndEventCounts
                                .put(filteredEventAttendee, studentsAndEventCounts.get(filteredEventAttendee) + 1))));

        return studentsAndEventCounts
                .entrySet()
                .stream()
                .filter(studentAndEventCount -> studentAndEventCount.getValue() < 2)
                .map(Map.Entry::getKey).toList();
    }

    public static void main(String[] args) {

        List<String> students = List.of("Sally", "Polly", "Molly", "Tony", "Harry");

        Map<String, List<String>> attendeesMapping = Map.of(
                "Farmer's Market", List.of("Sally", "Polly"),
                "Car Wash Fundraiser", List.of("Molly", "Tony", "Polly"),
                "Cooking Workshop", List.of("Sally", "Molly", "Polly"),
                "Midnight Breakfast", List.of("Polly", "Molly"));

        System.out.println(findStudentsWithIncompleteVolunteerEvents(students, attendeesMapping));
    }

}
