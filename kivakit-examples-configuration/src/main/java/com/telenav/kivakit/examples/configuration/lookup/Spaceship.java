package com.telenav.kivakit.examples.configuration.lookup;

import com.telenav.kivakit.application.component.BaseComponent;
import com.telenav.kivakit.examples.configuration.settings.simple.SettingsExample;
import com.telenav.kivakit.examples.configuration.settings.simple.SpaceshipSettings;
import com.telenav.kivakit.kernel.messaging.Message;

/**
 * Spaceship implementation for Operation Impending Doom III.
 * <p>
 * This spaceship object looks up a {@link QuantumDatabase} to query the attack plan for the ship, but does not know or
 * need to know where this object comes from, preventing this implementation detail from leaking out.
 * </p>
 *
 * @author jonathanl (shibo)
 * @see SettingsExample
 * @see SpaceshipSettings
 */
public class Spaceship extends BaseComponent
{
    /**
     * Begins an attack on some poor planet. Notice that this class does not expose where it finds the attack plan.
     */
    public void startAttack()
    {
        // Get database from global lookup
        final var database = lookup(QuantumDatabase.class);

        // and launch an attack.
        launch(database.retrieveAttackPlan());
    }

    private void launch(final AttackPlan plan)
    {
        Message.println("Launching $", plan);
    }
}
