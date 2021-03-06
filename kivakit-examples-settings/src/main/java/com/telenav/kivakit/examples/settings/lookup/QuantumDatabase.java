package com.telenav.kivakit.examples.settings.lookup;

import com.telenav.kivakit.core.time.Time;

import static com.telenav.kivakit.core.time.Duration.ONE_MINUTE;

/**
 * Mock implementation of quantum storage for attack plans and other alien-related information.
 *
 * @author jonathanl (shibo)
 */
public class QuantumDatabase
{
    private final AttackPlan PLAN = new AttackPlan()
            .withPlanet("Earth")
            .withWhen(Time.now().roundUp(ONE_MINUTE));

    /**
     * Return mock attack plan for now, pending database implementation.
     */
    public AttackPlan retrieveAttackPlan()
    {
        return PLAN;
    }
}
