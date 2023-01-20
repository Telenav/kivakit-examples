package com.telenav.kivakit.snippets;

import com.telenav.kivakit.core.language.trait.TryTrait;
import com.telenav.kivakit.core.messaging.listeners.MessageList;

import static com.telenav.kivakit.core.collections.list.StringList.stringList;

public class Snippet11 extends BaseSnippet implements TryTrait
{
    public static void main(String[] args)
    {
        new Snippet11().run();
    }

    @Override
    protected Object onSnippet()
    {
        var issues = new MessageList();
        var results = stringList();

        issues.listenTo(this);

        // 1, 2
        results.add("x = " + tryCatch(this::x));
        results.add("y = " + tryCatch(this::y));

        // 3, 4
        results.add("x = " + tryCatchDefault(this::x, -1));
        results.add("y = " + tryCatchDefault(this::y, -1));

        // 5, 6
        results.add("x = " + tryCatch(this::x, "Failed to x()"));
        results.add("y = " + tryCatch(this::y, "Failed to y()"));

        // 7
        results.addAll(issues.formatted());
        issues.clear();

        // 8
        results.add("x = " + tryCatchThrow(this::x, "Failed to x(): rethrowing"));
        try
        {
            results.add("y = " + tryCatchThrow(this::y, "Failed to y(): rethrowing"));
        }
        catch (Exception e)
        {
            // 9
            results.add("Caught rethrown exception");
        }

        // 10
        results.addAll(issues.formatted());
        issues.clear();

        return results.numbered().titledBox("Results");
    }

    private int x()
    {
        return 3;
    }

    private int y()
    {
        throw new IllegalStateException("Error in y()");
    }
}

