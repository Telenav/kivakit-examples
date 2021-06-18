package com.telenav.kivakit.examples.configuration.lookup;

import com.telenav.kivakit.configuration.lookup.Lookup;

/**
 * Shows how to register a quantum database in the global {@link Lookup}. The database is then used by {@link Alien} and
 * {@link Spaceship} when planning an intergalactic attack. This example is part of Operation Impending Doom III. See <a
 * href="https://en.wikipedia.org/wiki/The_Nightmare_Begins">Operation Impending Doom I</a>
 * <p>
 * The lookup pattern allows interfaces to be registered for consumption with {@link Lookup#register(Object)} (normally
 * when the application starts up). These interfaces can then be consumed by other arbitrary objects with {@link
 * Lookup#lookup(Class)} anywhere in an application without violating the cardinal object-oriented design principle of
 * encapsulation.
 * </p>
 *
 * @author jonathanl (shibo)
 * @see <a href="https://www.infoworld.com/article/2073723/why-getter-and-setter-methods-are-evil.html">Why Getter and
 * Setter Methods are Evil, Infoworld, Alan Holub</a>
 */
public class LookupExample
{
    public static void main(final String[] arguments)
    {
        new LookupExample().run();
    }

    /**
     * Start invader Zim's attack on Earth (third try)
     */
    public void run()
    {
        // Register the quantum database containing alien information,
        final var database = new QuantumDatabase();
        Lookup.global().register(database);

        // then create Zim with his spaceship and launch an attack.
        final var zim = new Alien(new Spaceship());
        zim.attack();
    }
}
