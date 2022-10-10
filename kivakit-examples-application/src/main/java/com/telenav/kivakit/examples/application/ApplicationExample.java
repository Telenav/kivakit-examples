package com.telenav.kivakit.examples.application;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.commandline.ArgumentParser;
import com.telenav.kivakit.commandline.SwitchParser;
import com.telenav.kivakit.core.collections.set.ObjectSet;
import com.telenav.kivakit.core.value.count.Count;
import com.telenav.kivakit.filesystem.File;

import java.util.List;

import static com.telenav.kivakit.commandline.SwitchParsers.booleanSwitchParser;
import static com.telenav.kivakit.core.collections.set.ObjectSet.objectSet;
import static com.telenav.kivakit.filesystem.Files.fileArgumentParser;

/**
 * Example application that counts the lines in its file argument. With no arguments, the application gives usage help.
 * With the argument -show-file-size=true, it shows the size of the file in bytes.
 *
 * @author jonathanl (shibo)
 */
public class ApplicationExample extends Application
{
    public static void main(String[] arguments)
    {
        new ApplicationExample().run(arguments);
    }

    private final ArgumentParser<File> INPUT =
            fileArgumentParser(this, "Input text file")
                    .required()
                    .build();

    private final SwitchParser<Boolean> SHOW_FILE_SIZE =
            booleanSwitchParser(this, "show-file-size", "Show the file size in bytes")
                    .optional()
                    .defaultValue(false)
                    .build();

    @Override
    public String description()
    {
        return "Example application that counts the number of lines in the file argument passed to the command line";
    }

    @Override
    protected List<ArgumentParser<?>> argumentParsers()
    {
        return List.of(INPUT);
    }

    @Override
    protected void onRun()
    {
        var input = argument(INPUT);

        if (input.exists())
        {
            showFile(input);
        }
        else
        {
            problem("File does not exist: $", input.path());
        }
    }

    @Override
    protected ObjectSet<SwitchParser<?>> switchParsers()
    {
        return objectSet(SHOW_FILE_SIZE);
    }

    private void showFile(File input)
    {
        if (get(SHOW_FILE_SIZE))
        {
            information("File size = $", input.sizeInBytes());
        }

        information("Lines = $", Count.count(input.reader().readLines()));
    }
}
