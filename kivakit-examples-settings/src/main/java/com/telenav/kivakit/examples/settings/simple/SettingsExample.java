package com.telenav.kivakit.examples.settings.simple;

import com.telenav.kivakit.component.BaseComponent;

/**
 * Shows how to configure a spaceship and launch it as part of Operation Impending Doom III. See <a
 * href="https://en.wikipedia.org/wiki/The_Nightmare_Begins">Operation Impending Doom I</a>
 * <p>
 * The register and lookup pattern allows decoupling of settings from their use, but without violating the cardinal
 * object-oriented design principle of encapsulation. Objects that need an interface request it via {@link
 * #require(Class)} and objects providing interfaces register them with {@link #register(Object)}. This prevents
 * implementation details of the requesting object from being exposed (either through primitive field access or getters
 * and setters, see <a href="https://www.infoworld.com/article/2073723/why-getter-and-setter-methods-are-evil.html">Why
 * Getter and Setter Methods are Evil, Infoworld, Alan Holub</a>).
 * </p>
 *
 * @author jonathanl (shibo)
 */
public class SettingsExample extends BaseComponent
{
    public static void main(String[] arguments)
    {
        new SettingsExample().run();
    }

    public void run()
    {
        // Create the spaceship settings
        var settings = new SpaceshipSettings();
        settings.name("Zim's Awesome Spaceship");
        settings.airConditioning(true);
        settings.cruiseControl(true);
        settings.missileLaunchers(7);

        // and register them for any interested components
        registerSettingsObject(settings);

        // then launch a new spaceship.
        new Spaceship().launch();
    }
}
