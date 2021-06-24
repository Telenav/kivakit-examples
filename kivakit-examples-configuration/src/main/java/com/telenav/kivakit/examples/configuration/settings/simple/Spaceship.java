package com.telenav.kivakit.examples.configuration.settings.simple;

import com.telenav.kivakit.configuration.settings.Settings;

import static com.telenav.kivakit.kernel.language.time.Duration.ONE_SECOND;
import static com.telenav.kivakit.kernel.messaging.Message.println;

/**
 * Spaceship implementation for Operation Impending Doom III.
 * <p>
 * This spaceship object requests a {@link SpaceshipSettings} object to configure itself, but does not know or need to
 * know where these settings come from. Other objects can also request spaceship settings.
 * </p>
 *
 * @author jonathanl (shibo)
 * @see SettingsExample
 * @see SpaceshipSettings
 */
public class Spaceship
{
    /**
     * Commence the mission!
     */
    public void launch()
    {
        // Count down
        for (var count = 10; count > 0; count--)
        {
            println("$", count);
            ONE_SECOND.sleep();
        }

        // engage engines,
        println("Liftoff for $", settings());

        // then if the spaceship has air conditioning,
        if (settings().airConditioning())
        {
            // turn it on
            ONE_SECOND.sleep();
            println("Air conditioning set to 'high'");
        }

        // and if the spaceship has cruise control
        if (settings().cruiseControl())
        {
            // engage it.
            ONE_SECOND.sleep();
            println("Cruise control engaged");
        }
    }

    @Override
    public String toString()
    {
        return settings().toString();
    }

    /**
     * @return Retrieve spaceship settings from whoever provided them to the {@link Settings} registry
     */
    private SpaceshipSettings settings()
    {
        return Settings.require(SpaceshipSettings.class);
    }
}