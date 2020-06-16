package practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        m.buildFileTree("/Users/z.khakimov/dev/payout/core", 1);
    }

    private void buildFileTree(String filepath, int tabCount) {
        try {
            Files.list(Path.of(filepath))
                .forEach(file -> {
                    int newTab = 0;
                    if (file.toFile().isDirectory()) {
                        newTab += tabCount;
                        if (newTab == 1) {
                            System.out.println(" ".repeat(tabCount) + "- " + file.getFileName());
                        } else {
                            System.out.format(
                                "%s|- %s %s\n",
                                " ".repeat(newTab),
                                file.getFileName(),
                                file.toFile().isDirectory() ? "(d)" : ""
                            );
                        }
                        buildFileTree(file.toFile().getPath(), newTab + 3);
                    } else {
                        System.out.format(
                            "%s|- %s %s\n",
                            " ".repeat(tabCount + newTab),
                            file.getFileName(),
                            file.toFile().isFile() ? "(f)" : ""
                        );
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}