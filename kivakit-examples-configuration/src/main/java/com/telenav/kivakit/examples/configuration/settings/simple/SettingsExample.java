package com.telenav.kivakit.examples.configuration.settings.simple;

import com.telenav.kivakit.configuration.settings.Settings;

/**
 * Shows how to configure a spaceship and launch it.
 * <p>
 * The register and lookup pattern allows decoupling of settings from their use, but without violating the cardinal
 * object-oriented design principle of encapsulation. Objects that need an interface request it via {@link
 * Settings#require(Class)} and objects providing interfaces register them with {@link Settings#register(Object)}. This
 * prevents the exposure of implementation details of the requesting object (either through primitive field access or
 * getters and setters, see <a href="https://www.infoworld.com/article/2073723/why-getter-and-setter-methods-are-evil.html">Why
 * Getter and Setter Methods are Evil, Infoworld, Alan Holub</a>).
 * </p>
 *
 * @author jonathanl (shibo)
 */
public class SettingsExample
{
    public static void main(final String[] arguments)
    {
        new SettingsExample().run();
    }

    public void run()
    {
        // Create the spaceship settings
        final var settings = new SpaceshipSettings();
        settings.name("Zim's Cool Spaceship");
        settings.airConditioning(true);
        settings.cruiseControl(true);
        settings.missileLaunchers(7);

        // and register them for any interested components
        Settings.register(settings);

        // then launch a new spaceship.
        new Spaceship().launch();
    }
}
