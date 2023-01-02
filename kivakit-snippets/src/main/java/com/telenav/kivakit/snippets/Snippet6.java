package com.telenav.kivakit.snippets;

import com.telenav.kivakit.core.collections.list.ObjectList;
import com.telenav.kivakit.core.language.trait.TryTrait;
import com.telenav.kivakit.core.thread.ThreadLocalMap;
import com.telenav.kivakit.core.value.count.Count;
import com.telenav.kivakit.core.value.count.MutableCount;

import java.util.concurrent.Future;

import static com.telenav.kivakit.core.collections.list.ObjectList.list;
import static com.telenav.kivakit.core.ensure.Ensure.ensureEqual;
import static com.telenav.kivakit.core.thread.Threads.shutdownAndAwaitTermination;
import static com.telenav.kivakit.core.thread.Threads.threadPool;
import static com.telenav.kivakit.core.value.count.Count._10_000;
import static com.telenav.kivakit.core.value.count.Count._8;

public class Snippet6 extends BaseSnippet implements TryTrait
{
    public static void main(String[] args)
    {
        new Snippet6().run();
    }

    @Override
    protected Object onSnippet()
    {
        var threads = _8;
        var iterations = _10_000;

        var map = new ThreadLocalMap<Integer, Integer>();

        var executor = threadPool("Snippet-6", threads);
        ObjectList<Future<Count>> futures = list();
        threads.loop(() -> futures.add(executor.submit(() ->
        {
            iterations.forEachInteger(i -> map.put(i, i));
            iterations.forEachInteger(i -> ensureEqual(i, map.get(i)));
            return iterations;
        })));

        var total = new MutableCount();
        for (var future : futures)
        {
            total.plus(tryCatch(() -> future.get()));
        }

        shutdownAndAwaitTermination(executor);
        return total;
    }
}
