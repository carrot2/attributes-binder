package org.carrot2.util.attribute;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public class CheckConverters
{
    public static class External
    {

    }

    @Root
    public static class Example
    {
        @Element
        @Convert(ExternalConverter.class)
        private External external;

        public External getExternal()
        {
            return external;
        }
    }

    public static class ExternalConverter implements Converter<External>
    {

        public External read(InputNode node)
        {
            return new External();
        }

        public void write(OutputNode node, External external)
        {
            try
            {
                node.setAttribute("name", "dawid");
                OutputNode child = node.getChild("subnode");
                child.setAttribute("foo", "bar");
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String [] args) throws Exception
    {
        External e = new External();
        Example ex = new Example();
        ex.external = e;

        new Persister(new AnnotationStrategy()).write(ex, System.out);
        System.out.println();

        Registry registry = new Registry();
        registry.bind(External.class, ExternalConverter.class);
        RegistryStrategy registryStrategy = new RegistryStrategy(registry);
        new Persister(registryStrategy).write(e, System.out);
    }
}
