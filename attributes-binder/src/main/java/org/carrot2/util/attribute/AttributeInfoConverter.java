package org.carrot2.util.attribute;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public class AttributeInfoConverter implements Converter<AttributeInfo>
{
    @Override
    public AttributeInfo read(InputNode node) throws Exception
    {
        return new AttributeInfo(
            key, 
            className, 
            fieldName, 
            javaDoc, 
            label, 
            title, 
            description, 
            group, 
            level, 
            inheritFrom);
    }
    
    @Override
    public void write(OutputNode node, AttributeInfo value) throws Exception
    {
    }
}
