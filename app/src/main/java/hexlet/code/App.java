package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.HashMap;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a differences.")

public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish", paramLabel = "format")
    String formatSelection;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this @|fg(cyan) help |@message and exit.")
    boolean usageHelpRequested;

    @Parameters(index = "0", description = "path to first file")
    String filepath1;
    @Parameters(index = "1", description = "path to second file")
    String filepath2;
    @Override
    public Integer call() {
        if (versionInfoRequested) {
            System.out.println("Version 1.0.0");
            return 0;
        }

        if (usageHelpRequested) {
            return 0;
        }
        if (filepath1 == null || filepath2 == null) {
            System.out.println("Please provide two configuration files to compare");
            return 1;
        }
        System.out.println("Files provided: " + filepath1 + ", " + filepath2);
        return 0;
    }

    public static void main(String[] args) {
        try {
            var dataToAddFilepath1 = new HashMap<String, Object>();
            dataToAddFilepath1.put("host", "hexlet.io");
            dataToAddFilepath1.put("timeout", 50);
            dataToAddFilepath1.put("proxy", "123.234.53.22");
            dataToAddFilepath1.put("follow", false);

            var dataToAddFilepath2 = new HashMap<String, Object>();
            dataToAddFilepath2.put("timeout", 20);
            dataToAddFilepath2.put("verbose", true);
            dataToAddFilepath2.put("host", "hexlet.io");
            Differ.createFile("src/main/resources/filepath1.json", dataToAddFilepath1);
            Differ.createFile("src/main/resources/filepath2.json", dataToAddFilepath2);

            new CommandLine(new App()).execute(args);
        } catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }
}