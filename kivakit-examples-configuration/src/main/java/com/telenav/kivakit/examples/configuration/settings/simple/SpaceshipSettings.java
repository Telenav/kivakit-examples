package com.telenav.kivakit.examples.configuration.settings.simple;

import com.telenav.kivakit.kernel.interfaces.naming.Named;
import com.telenav.kivakit.kernel.language.reflection.property.KivaKitIncludeProperty;
import com.telenav.kivakit.kernel.language.strings.formatting.ObjectFormatter;

/**
 * Settings for spaceships participating in Operation Impending Doom III
 */
public class SpaceshipSettings implements Named
{
    @KivaKitIncludeProperty
    private String name;

    @KivaKitIncludeProperty
    private boolean cruiseControl;

    @KivaKitIncludeProperty
    private boolean airConditioning;

    @KivaKitIncludeProperty
    private int missileLaunchers;

    public boolean airConditioning()
    {
        return airConditioning;
    }

    public void airConditioning(final boolean airConditioning)
    {
        this.airConditioning = airConditioning;
    }

    public boolean cruiseControl()
    {
        return cruiseControl;
    }

    public void cruiseControl(final boolean cruiseControl)
    {
        this.cruiseControl = cruiseControl;
    }

    public int missileLaunchers()
    {
        return missileLaunchers;
    }

    public void missileLaunchers(final int missileLaunchers)
    {
        this.missileLaunchers = missileLaunchers;
    }

    @Override
    public String name()
    {
        return name;
    }

    public void name(final String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return new ObjectFormatter(this).toString();
    }
}
