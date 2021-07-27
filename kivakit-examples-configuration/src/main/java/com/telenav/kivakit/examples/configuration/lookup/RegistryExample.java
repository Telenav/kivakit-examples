package com.telenav.kivakit.examples.configuration.lookup;

import com.telenav.kivakit.configuration.BaseComponent;
import com.telenav.kivakit.configuration.lookup.Registry;

/**
 * Shows how to register a quantum database in the global {@link Registry}. The database is then used by {@link Alien}
 * and {@link Spaceship} when planning an intergalactic attack. This example is part of Operation Impending Doom III.
 * See <a href="https://en.wikipedia.org/wiki/The_Nightmare_Begins">Operation Impending Doom I</a>
 * <p>
 * The lookup pattern allows interfaces to be registered for consumption with {@link Registry#register(Object)}
 * (normally when the application starts up). These interfaces can then be consumed by other arbitrary objects with
 * {@link Registry#lookup(Class)} anywhere in an application without violating the cardinal object-oriented design
 * principle of encapsulation.
 * </p>
 *
 * @author jonathanl (shibo)
 * @see <a href="https://www.infoworld.com/article/2073723/why-getter-and-setter-methods-are-evil.html">Why Getter and
 * Setter Methods are Evil, Infoworld, Alan Holub</a>
 */
public class RegistryExample extends BaseComponent
{
    public static void main(final String[] arguments)
    {
        new RegistryExample().run();
    }

    /**
     * Start invader Zim's attack on Earth (third try)
     */
    public void run()
    {
        // Register the quantum database containing alien information,
        final var database = new QuantumDatabase();
        registerObject(database);

        // then create Zim with his spaceship and launch an attack.
        final var zim = new Alien(new Spaceship());
        zim.attack();
    }
}
