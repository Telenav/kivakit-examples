package com.telenav.kivakit.examples.settings.lookup;

import com.telenav.kivakit.component.BaseComponent;
import com.telenav.kivakit.core.os.Console;
import com.telenav.kivakit.examples.settings.simple.SettingsExample;
import com.telenav.kivakit.examples.settings.simple.SpaceshipSettings;

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
        var database = lookup(QuantumDatabase.class);

        // and launch an attack.
        launch(database.retrieveAttackPlan());
    }

    private void launch(AttackPlan plan)
    {
        Console.println("Launching $", plan);
    }
}
