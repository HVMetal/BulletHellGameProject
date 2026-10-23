package org.bullethell.game.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public final class HighscoreUtils {
    private final static Logger LOG = Logger.getGlobal();
    private final static int SIZE = Integer.parseInt(PropertyProvider.getProperty("LEADERBOARD_SIZE"));
    private final static int PADDING = 1 + MathUtils.nrOfDigits(SIZE);

    public static String getFormattedHighscores() {
        final StringBuilder builder = new StringBuilder();
        final List<Long> scores = readScores();

        for(int i = 0; i < SIZE; ++i) {
            builder.append(i + 1)
                    .append(".")
                    .append(String.valueOf(' ').repeat(PADDING - MathUtils.nrOfDigits(i + 1)))
                    .append(scores.get(i))
                    .append("\n");
        }

        return builder.toString();
    }

    public static void recordScore(long score) {
        final List<Long> currentScores = readScores();
        currentScores.add(score);
        currentScores.sort(Comparator.reverseOrder());
        writeScores(currentScores);
    }

    private static void writeScores(List<Long> scores) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(PropertyProvider.getProperty("LEADERBOARD_FILE_PATHNAME")));) {
            for(int i = 0; i < SIZE; ++i) {
                fileWriter.write(Long.toString(scores.get(i)));
                fileWriter.newLine();
            }

        } catch (IOException e) {
            LOG.warning("Could not write to score file.");
            throw new RuntimeException(e);
        }
    }

    private static List<Long> readScores() {
        final List<Long> scores = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(PropertyProvider.getProperty("LEADERBOARD_FILE_PATHNAME")))) {
            String line;
            
            for(int i = 0; i < SIZE; ++i) {
                line = fileReader.readLine();
                if(line != null) {
                    scores.add(Long.parseLong(line));
                } else {
                    scores.add(0L);
                }
            }

        } catch (FileNotFoundException e) {
            LOG.warning("Score file not found.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOG.warning("Could not read from score file.");
            throw new RuntimeException(e);
        }

        return scores;
    }
}
