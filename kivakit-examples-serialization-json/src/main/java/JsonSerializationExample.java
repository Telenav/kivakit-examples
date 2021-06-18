package com.telenav.kivakit.examples.linecount;

import com.telenav.kivakit.application.Application;
import com.telenav.kivakit.commandline.ArgumentParser;
import com.telenav.kivakit.commandline.SwitchParser;
import com.telenav.kivakit.filesystem.File;
import com.telenav.kivakit.kernel.language.values.count.Count;
import com.telenav.kivakit.resource.project.ResourceProject;

import java.util.List;
import java.util.Set;

import static com.telenav.kivakit.commandline.SwitchParser.booleanSwitchParser;
import static com.telenav.kivakit.filesystem.File.fileArgumentParser;

/**
 *
 */
public class JsonSerializationExample extends Application
{
    public static void main(final String[] arguments)
    {
        new JsonSerializationExample().run(arguments);
    }

    private JsonSerializationExample()
    {
        super(ResourceProject.get());
    }

    @Override
    protected void onRun()
    {
    }
}
