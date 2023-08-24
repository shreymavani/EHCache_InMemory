package org.example.keyExtractor;

import net.sf.ehcache.Element;
import net.sf.ehcache.search.attribute.AttributeExtractor;
import net.sf.ehcache.search.attribute.AttributeExtractorException;
import org.example.model.CompositeKey;

//Facing Casting Error from string to CompositeKey,Can be Resolved
//Alternative to this is to directly provide with the expression in ehcache.xml

public class CompositeKeyExtractor implements AttributeExtractor {

    @Override public String attributeFor(Element element, String attributeName) throws AttributeExtractorException {
        CompositeKey compositeKey = (CompositeKey) element.getObjectValue();

        // Return the appropriate attribute based on attributeName
        if ("applicationId".equals(attributeName)) {
            return compositeKey.getApplicationId();
        } else if ("clusterId".equals(attributeName)) {
            return compositeKey.getClusterId();
        } else if ("engineType".equals(attributeName)) {
            return compositeKey.getEngineType();
        }

        throw new AttributeExtractorException("Unknown attribute: " + attributeName);
    }
}
