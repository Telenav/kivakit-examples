package com.telenav.kivakit.examples.configuration.lookup;

import com.telenav.kivakit.kernel.language.time.Time;
import com.telenav.kivakit.kernel.messaging.Message;

/**
 * Defines when and where an alien attack should occur.
 *
 * @author jonathanl (shibo)
 */
public class AttackPlan
{
    /** The planet to attack */
    private String planet;

    /** The time to launch the attack */
    private Time when;

    public AttackPlan()
    {
    }

    protected AttackPlan(final AttackPlan that)
    {
        this.planet = that.planet;
        this.when = that.when;
    }

    @Override
    public String toString()
    {
        return Message.format("attack on $ at $ (in $)", planet, when, when.fromNow());
    }

    /**
     * Notice that the field when is not exposed to users of this class
     */
    public void waitForAttackTime()
    {
        final var waitTime = this.when.fromNow();
        Message.println("Waiting until $ to launch $", when, this);
        waitTime.sleep();
    }

    /**
     * Notice that this method is package private
     */
    AttackPlan withPlanet(final String planet)
    {
        final var copy = new AttackPlan(this);
        copy.planet = planet;
        return copy;
    }

    /**
     * Notice that this method is package private
     */
    AttackPlan withWhen(final Time when)
    {
        final var copy = new AttackPlan(this);
        copy.when = when;
        return copy;
    }
}
