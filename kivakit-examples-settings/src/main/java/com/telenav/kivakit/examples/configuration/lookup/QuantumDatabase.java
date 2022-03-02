package com.telenav.kivakit.examples.configuration.lookup;

import com.telenav.kivakit.language.time.Time;

import static com.telenav.kivakit.language.time.Duration.ONE_MINUTE;

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
