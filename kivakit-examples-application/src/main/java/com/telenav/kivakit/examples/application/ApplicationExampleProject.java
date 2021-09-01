package com.telenav.kivakit.examples.application;

import com.telenav.kivakit.kernel.language.collections.set.ObjectSet;
import com.telenav.kivakit.kernel.language.objects.Lazy;
import com.telenav.kivakit.kernel.project.Project;
import com.telenav.kivakit.resource.project.ResourceProject;

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
        return ObjectSet.of(ResourceProject.get());
    }
}

