/*
 * Copyright since 2014 Web Firm Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webfirmframework.wffweb.tag.html.core;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.webfirmframework.wffweb.InvalidValueException;
import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.TagNameConstants;
import com.webfirmframework.wffweb.tag.html.attributewff.CustomAttribute;
import com.webfirmframework.wffweb.tag.htmlwff.CustomTag;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TagRegistryTest {
    
    /**
     * Note: this test case should be first
     * @throws Exception
     */
    @Test
    public void aTestTestMethods() throws Exception {
        try {
            //loadAllTagClasses must be after test
            //not possible to write a separate test case for test() method
            //as the internal map will be nullified in loadAllTagClasses
            TagRegistry.test();            
        } catch (InvalidValueException e) {
            fail(e.getMessage());
        } 
    }

    @Test
    public void testLoadAllTagClasses() throws Exception {
        TagRegistry.loadAllTagClasses();    
    }

    @Test
    public void testIfAllTagNamesAreReffered() throws Exception {
        Field[] fields = TagNameConstants.class.getFields();
        for (Field field : fields) {

            // value from constant name field
            final String constantName = field.get(null).toString();
            final String simpleClassName = TagRegistry
                    .getTagClassNameByTagName().get(constantName);
            if (simpleClassName == null) {
                fail(constantName
                        + " tag name is not included in all tag names map");

            } else if (!simpleClassName.replace("Tag", "")
                    .equalsIgnoreCase(constantName.replace("-", ""))) {
                fail(constantName + " tag has an incorrect class mapping");
            }
        }
    }

    @Test
    public void testGetNewTagInstance() {
        final Map<String, Class<?>> tagClassNameByTagName = TagRegistry
                .getTagClassByTagName();
        {
            for (Entry<String, Class<?>> entry : tagClassNameByTagName
                    .entrySet()) {
                final AbstractHtml tag = TagRegistry
                        .getNewTagInstance(entry.getKey());

                assertNotNull(tag);
                assertEquals(entry.getValue(), tag.getClass());
              //just for testing
//                assertEquals(tag.getTagNameIndex(), (int) TagRegistry.getIndexByTagName(tag.getTagName()));

            }
        }

        {
            for (Entry<String, Class<?>> entry : tagClassNameByTagName
                    .entrySet()) {

                CustomTag customTag = new CustomTag("custom-tag", null);
                CustomAttribute customAttribute = new CustomAttribute(
                        "custom-attr-name", "custom-attr-value");

                final String tagName = entry.getKey();
                final AbstractHtml tag = TagRegistry.getNewTagInstance(
                        tagName, customTag, customAttribute);

                assertNotNull(tag);
                assertEquals(entry.getValue(), tag.getClass());
                if (tag.toHtmlString().contains("</")) {
                    assertEquals("<" + tagName
                            + " custom-attr-name=\"custom-attr-value\"></" + tagName
                            + ">", tag.toHtmlString());
                } else if (tag.toHtmlString().endsWith("/>")) {
                    assertEquals("<" + tagName
                            + " custom-attr-name=\"custom-attr-value\"/>", tag.toHtmlString());
                } else if (tag.toHtmlString().endsWith(">")) {
                    assertEquals("<" + tagName
                            + " custom-attr-name=\"custom-attr-value\">", tag.toHtmlString());
                }


            }
        }

    }
    
    @Test
    public void testTagConstantsWithPreIndexedNames() throws Exception {
        for (final Field field : TagNameConstants.class.getFields()) {
            try {
                final String fieldName = field.getName().replace("_TAG", "");
                
                assertNotNull(PreIndexedTagName.valueOf(fieldName));
            } catch (final Exception e) {
                e.printStackTrace();
                fail("invalid PreIndexedTagName constant");
            }
        }
    }
    
    @Test
    public void testGetIndexByTagName() throws Exception {
        
        final List<String> tagNames = TagRegistry.getTagNames();
        for (String tagName : tagNames) {
            final int indexByTagName = TagRegistry.getIndexByTagName(tagName);
            
//            final String constantName = tagName.replace("-", "_").toUpperCase();
//            System.out.println(constantName + "(TagNameConstants."
//                    + constantName + "),\n");
            
            final String tagNameByIndex = tagNames.get(indexByTagName);
            assertEquals(tagName, tagNameByIndex);
        }

    }
    @Test
    public void testSortOrderOfTagName()  {


        List<String> names = new ArrayList<>();
        for (PreIndexedTagName each : PreIndexedTagName.values()) {
            names.add(each.tagName());
        }

        List<String> multiSortedNames = new ArrayList<>(names);
        multiSortedNames
                .sort(Comparator.comparingInt(String::length).thenComparing(String::compareTo));
        assertEquals(multiSortedNames, names);
    }
}
