package com.telenav.kivakit.examples.application;

import com.telenav.kivakit.collections.set.ObjectSet;
import com.telenav.kivakit.core.object.Lazy;
import com.telenav.kivakit.coreproject.Project;
import com.telenav.kivakit.resource.ResourceProject;

public class ApplicationExampleProject extends Project
{
    private static final Lazy<ApplicationExampleProject> project = Lazy.of(ApplicationExampleProject::new);

    public static ApplicationExampleProject get()
    {
        return project.get();
    }

    protected ApplicationExampleProject()
    {
    }

    @Override
    public ObjectSet<Project> dependencies()
    {
        return ObjectSet.objectSet(ResourceProject.get());
    }
}
