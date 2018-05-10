
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2012, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.util.preprocessor;

import javax.annotation.processing.Messager;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeInstance;
import org.apache.velocity.util.ExtProperties;

/**
 * Initialize velocity templating engine.
 */
final class VelocityInitializer
{
    private VelocityInitializer()
    {
        // No instances.
    }

    /**
     * Initialize Velocity engine instance, disables logging, sets bundle-relative
     * resource loader.
     */
    public static RuntimeInstance createInstance(final Messager msg)
    {
        try
        {
            final ExtProperties p = new ExtProperties();
            // p.setProperty(RuntimeConstants.SET_NULL_ALLOWED, "true");
            p.setProperty("resource.loader", "apt");
            p.setProperty("apt.resource.loader.instance",
                new ClassRelativeResourceLoader(msg, VelocityInitializer.class));

            final RuntimeInstance velocity = new RuntimeInstance();
            velocity.setConfiguration(p);
            return velocity;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Velocity initialization failed.", e);
        }
    }

    /**
     * Create Velocity context and place default tools into it.
     */
    public static VelocityContext createContext()
    {
        final VelocityContext context = new VelocityContext();

        context.put("macros", new Macros());
        context.put("methodutils", new MethodUtils());

        return context;
    }
}
