package com.telenav.kivakit.examples.linecount;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.commandline.ArgumentParser;
import com.telenav.kivakit.commandline.SwitchParser;
import com.telenav.kivakit.filesystem.File;
import com.telenav.kivakit.kernel.language.values.count.Count;
import com.telenav.kivakit.resource.project.ResourceProject;

import java.util.List;
import java.util.Set;

/**
 * Example application that counts the lines in its file argument. With no arguments, the application gives usage help.
 * With the argument -show-file-size=true, it shows the size of the file in bytes.
 *
 * @author jonathanl (shibo)
 */
public class LineCountApplication extends Application
{
    private final ArgumentParser<File> INPUT =
            File.fileArgument("Input text file")
                    .required()
                    .build();

    private final SwitchParser<Boolean> SHOW_FILE_SIZE =
            SwitchParser.booleanSwitch("show-file-size", "Show the file size in bytes")
                    .optional()
                    .defaultValue(false)
                    .build();

    public static void main(final String[] arguments)
    {
        new LineCountApplication().run(arguments);
    }

    private LineCountApplication()
    {
        super(ResourceProject.get());
    }

    @Override
    protected List<ArgumentParser<?>> argumentParsers()
    {
        return List.of(INPUT);
    }

    @Override
    protected Set<SwitchParser<?>> switchParsers()
    {
        return Set.of(SHOW_FILE_SIZE);
    }

    @Override
    protected void onRun()
    {
        final var input = argument(INPUT);

        if (get(SHOW_FILE_SIZE))
        {
            information("File size = $", input.bytes());
        }

        information("Lines = $", Count.count(input.reader().lines()));
    }
}
