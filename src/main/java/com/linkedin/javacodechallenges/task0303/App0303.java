package com.linkedin.javacodechallenges.task0303;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Aurelijus Jurkus
 * @since 17-Jan-2023
 */
public class App0303 {

    //in comments: the editor's choice

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("What file would you like to redact information from?");
        String fileName = scanner.nextLine();

        System.out.println("What words would you like to redact? Separate each word or phrase with a comma. If you phrase includes punctuation, include that in your input.");
        String redactedWords = scanner.nextLine();
        String[] redactedWordsList = redactedWords.split(",");

        redactTextFile(fileName, redactedWordsList);

        scanner.close();
    }

    public static void redactTextFile(String fileName, String[] redactedWordsArray) {

        //if (!fileName.contains(".txt")) {
        if (!fileName.endsWith(".txt")) {
            //System.out.println("This is not a text file.");
            System.out.print("This is not a text file.\n");
            return;
        }

        //using older api...
        /*try {
            File originalFile = new File(fileName);
            BufferedReader originalFileReader = new BufferedReader(new FileReader(originalFile));

            File redactedFile = new File("redacted-" +
                    fileName);
            BufferedWriter redactedFileWriter = new BufferedWriter(
                    new FileWriter(redactedFile));

            String currentLine = originalFileReader.readLine();

            while (currentLine != null) {
                for (String redactedWord : redactedWordsArray) {
                    currentLine = StringUtils
                            .replaceIgnoreCase(currentLine,
                                    redactedWord, "REDACTED");
                }

                redactedFileWriter.append(currentLine).append("\n");
                currentLine = originalFileReader.readLine();
            }

            originalFileReader.close();
            redactedFileWriter.close();

        } catch (IOException e) {
            System.out.println("Trouble reading or writing to file" + e);
        }*/

        List<String> originalLines = read(fileName);
        List<String> redactedLines = redactLines(originalLines, redactedWordsArray);
        write(fileName, redactedLines);
    }

    private static List<String> read(String fileName) {
        Path path = Paths.get(fileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    private static List<String> redactLines(List<String> originalLines, String[] redactedWordsArray) {

        List<String> redactedLines = new ArrayList<>(originalLines);

        for (String redactedWord : redactedWordsArray) {
            redactedLines = redactedLines.stream().map(originalLine -> StringUtils.replaceIgnoreCase(originalLine, redactedWord, "REDACTED")).toList();
        }

        return redactedLines;
    }

    @SneakyThrows
    private static void write(String originalFileName, List<String> redactedLines) {

        Path path = Paths.get("redacted-" + originalFileName);
        for (int i = 0; i < redactedLines.size(); i++) {
            byte[] strToBytes = redactedLines.get(i).getBytes();
            if (i == 0) {
                Files.write(path, strToBytes);
            } else {
                String newLine = "\n";
                byte[] newLineBytes = newLine.getBytes();
                Files.write(path, newLineBytes, StandardOpenOption.APPEND);
                Files.write(path, strToBytes, StandardOpenOption.APPEND);
            }
        }
    }
}
