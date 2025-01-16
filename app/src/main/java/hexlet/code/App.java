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

    private static final String FILEPATH1 = "src/main/resources/filepath1.json";
    private static final String FILEPATH2 = "src/main/resources/filepath2.json";

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]",
            defaultValue = "stylish", paramLabel = "format")
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
        int result = 0;
        if (versionInfoRequested) {
            System.out.println("Version 1.0.0");
            return result;
        }

        if (usageHelpRequested) {
            return result;
        }
        if (filepath1 == null || filepath2 == null) {
            System.out.println("Please provide two configuration files to compare");
            return 1;
        }
        try {
            String output = Differ.generate(FILEPATH1, FILEPATH2);
            System.out.println(output);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            return 1;
        }
        return result;
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

            Differ.createFile(FILEPATH1, dataToAddFilepath1);
            Differ.createFile(FILEPATH2, dataToAddFilepath2);

            new CommandLine(new App()).execute(args);
        } catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }
}
