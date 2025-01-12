package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a differences.")

public class App implements Runnable {
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish", paramLabel = "format")
    String formatSelection;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this @|fg(cyan) help |@message and exit.")
    boolean usageHelpRequested;

    @Parameters(index = "0", description = "path to first file")
    File filepath1;
    @Parameters(index = "1", description = "path to second file")
    File filepath2;
    @Override
    public void run() {
        if (versionInfoRequested) {
            System.out.println("Version 1.0.0");
            return;
        }

        if (usageHelpRequested) {
            return;
        }
        System.out.println("Please provide two configuration files to compare");
    }

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
}