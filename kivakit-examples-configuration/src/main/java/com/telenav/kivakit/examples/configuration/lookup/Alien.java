package com.telenav.kivakit.examples.configuration.lookup;

import com.telenav.kivakit.configuration.lookup.Registry;

/**
 * Represents an alien from an arbitrary galaxy (possibly from Irk) in a spaceship.
 * <p>
 * This spaceship object looks up a {@link QuantumDatabase} to query the attack plan for the ship, but does not know or
 * need to know where this object comes from, preventing this implementation detail from leaking out.
 * </p>
 */
public class Alien
{
    /** The alien's spaceship */
    private final Spaceship spaceship;

    public Alien(final Spaceship spaceship)
    {
        this.spaceship = spaceship;
    }

    /**
     * Launches an attack based on an attack plan from the quantum database
     */
    public void attack()
    {
        // Look up the attack plan in the alien database and wait until it's time to attack
        final var database = Registry.lookup(QuantumDatabase.class);
        database.retrieveAttackPlan().waitForAttackTime();

        // then launch the attack. Prepare to meet your horrible doom!
        spaceship.startAttack();
    }
}
