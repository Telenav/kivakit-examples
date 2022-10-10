package com.telenav.kivakit.examples.settings.lookup;

import com.telenav.kivakit.core.string.Strings;
import com.telenav.kivakit.core.time.Time;

import static com.telenav.kivakit.core.os.Console.console;

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

    protected AttackPlan(AttackPlan that)
    {
        planet = that.planet;
        when = that.when;
    }

    @Override
    public String toString()
    {
        return Strings.format("attack on $ at $ (in $)", planet, when, when.untilNow());
    }

    /**
     * Notice that the field when is not exposed to users of this class
     */
    public void waitForAttackTime()
    {
        var waitTime = when.untilNow();
        console().println("Waiting until $ to launch $", when, this);
        waitTime.sleep();
    }

    /**
     * Notice that this method is package private
     */
    @SuppressWarnings("SameParameterValue")
    AttackPlan withPlanet(String planet)
    {
        var copy = new AttackPlan(this);
        copy.planet = planet;
        return copy;
    }

    /**
     * Notice that this method is package private
     */
    AttackPlan withWhen(Time when)
    {
        var copy = new AttackPlan(this);
        copy.when = when;
        return copy;
    }
}
