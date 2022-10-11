package com.telenav.kivakit.examples.settings.simple;

import com.telenav.kivakit.interfaces.naming.Named;
import com.telenav.kivakit.core.language.reflection.property.IncludeProperty;
import com.telenav.kivakit.core.string.ObjectFormatter;

/**
 * Settings for spaceships participating in Operation Impending Doom III
 */
@SuppressWarnings("unused")
public class SpaceshipSettings implements Named
{
    @IncludeProperty
    private boolean airConditioning;

    @IncludeProperty
    private boolean cruiseControl;

    @IncludeProperty
    private int missileLaunchers;

    @IncludeProperty
    private String name;

    public boolean airConditioning()
    {
        return airConditioning;
    }

    public void airConditioning(boolean airConditioning)
    {
        this.airConditioning = airConditioning;
    }

    public boolean cruiseControl()
    {
        return cruiseControl;
    }

    public void cruiseControl(boolean cruiseControl)
    {
        this.cruiseControl = cruiseControl;
    }

    public int missileLaunchers()
    {
        return missileLaunchers;
    }

    public void missileLaunchers(int missileLaunchers)
    {
        this.missileLaunchers = missileLaunchers;
    }

    @Override
    public String name()
    {
        return name;
    }

    public void name(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return new ObjectFormatter(this).toString();
    }
}
