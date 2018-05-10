
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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.processing.Messager;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.apache.velocity.util.ExtProperties;

final class ClassRelativeResourceLoader extends ResourceLoader
{
    private final Class<?> clazz;

    @SuppressWarnings("unused")
    private final Messager msg;

    ClassRelativeResourceLoader(Messager msg, Class<?> clazz)
    {
        this.clazz = clazz;
        this.msg = msg;
    }

    @Override
    public void init(ExtProperties props)
    {
        // ignore.
    }

    /**
     * 
     */
    @Override
    public boolean isSourceModified(Resource resource)
    {
        return false;
    }

    /**
     * 
     */
    @Override
    public long getLastModified(Resource resource)
    {
        return 0L;
    }

    @Override
    public Reader getResourceReader(String source, String encoding) throws ResourceNotFoundException {
      /*
       * Do some protocol connection magic because JAR URLs are cached and this complicates
       * development (the template is not found once loaded).
       */
      URL resource = clazz.getResource(source);
      if (resource == null) 
          throw new ResourceNotFoundException("Resource not found: " + source);

      try
      {
          URLConnection connection = resource.openConnection();
          connection.setUseCaches(false);
          InputStream is = connection.getInputStream();
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          byte [] buffer = new byte [1024];
          int len;
          while ((len = is.read(buffer)) >= 0) {
            baos.write(buffer, 0, len);
          }

          return new StringReader(new String(baos.toByteArray(), encoding));
      }
      catch (Exception e)
      {
          throw new ResourceNotFoundException(e);
      }
    }
}
