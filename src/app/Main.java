package app;

import graph.Digraph;
import graph.Topological;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/*
 * Input format:
 * course count;
 * one course name per line;
 * edge count;
 * pairs of zero-based indices: prerequisite course.
 *
 * Course names were supplied by the student.
 * Sample edges are assumptions, not verified AUK requirements.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 0 && args.length != 2) {
            System.out.println("Usage: java app.Main [input-file output-file]");
            return;
        }

        Path input = Paths.get(
                args.length == 2 ? args[0] : "courses.txt"
        );
        Path output = Paths.get(
                args.length == 2 ? args[1] : "order.txt"
        );

        if (input.toAbsolutePath().normalize()
                .equals(output.toAbsolutePath().normalize())) {
            throw new IllegalArgumentException(
                    "Input and output must be different files"
            );
        }

        Digraph graph;
        String[] courses;

        try (Scanner scanner = new Scanner(input.toFile(), "UTF-8")) {
            int count = scanner.nextInt();
            scanner.nextLine();

            graph = new Digraph(count);
            courses = new String[count];

            for (int i = 0; i < count; i++) {
                courses[i] = scanner.nextLine().trim();

                if (courses[i].isEmpty()) {
                    throw new IllegalArgumentException("Empty course name");
                }
            }

            int edges = scanner.nextInt();

            if (edges < 0) {
                throw new IllegalArgumentException("Negative edge count");
            }

            for (int i = 0; i < edges; i++) {
                int prerequisite = scanner.nextInt();
                int course = scanner.nextInt();

                graph.addEdge(prerequisite, course);
            }

            if (scanner.hasNext()) {
                throw new IllegalArgumentException(
                        "Unexpected data after the edges"
                );
            }
        }

        Topological topological = new Topological(graph);

        try (BufferedWriter writer =
                     Files.newBufferedWriter(output, StandardCharsets.UTF_8)) {

            if (!topological.isDAG()) {
                writer.write(
                        "No valid course order: prerequisites contain a cycle."
                );
                writer.newLine();
            } else {
                for (int v : topological.order()) {
                    writer.write(courses[v]);
                    writer.newLine();
                }
            }
        }

        System.out.println("Result written to " + output);
    }
}