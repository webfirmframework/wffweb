/*
 * Copyright 2014-2019 Web Firm Framework
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
 * @author WFF
 */
package com.webfirmframework.wffweb.tag.html.attribute.global;

import com.webfirmframework.wffweb.tag.html.attribute.AttributeNameConstants;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.core.AttributeRegistry;
import com.webfirmframework.wffweb.tag.html.identifier.GlobalAttributable;

/**
 * {@code <element title="text"> }
 *
 * <pre>
 * The title attribute specifies extra information about an element.
 *
 * The information is most often shown as a tooltip text when the mouse moves over the element.
 * </pre>
 *
 * @author WFF
 *
 */
public class Title extends AbstractAttribute implements GlobalAttributable {

    private static final long serialVersionUID = 1_0_0L;

    private static final int ATTR_NAME_INDEX;

    static {
        final Integer index = AttributeRegistry
                .getIndexByAttributeName(AttributeNameConstants.TITLE);
        ATTR_NAME_INDEX = index != null ? index : -1;
    }

    {
        super.setAttributeNameIndex(ATTR_NAME_INDEX);
        super.setAttributeName(AttributeNameConstants.TITLE);
        init();
    }

    /**
     * sets blank.
     *
     * @author WFF
     * @since 1.0.0
     */
    public Title() {
        super.setAttributeValue("");
    }

    /**
     * @param title
     * @author WFF
     * @since 1.0.0
     */
    public Title(final String title) {
        super.setAttributeValue(title);
    }

    /**
     * invokes only once per object
     *
     * @author WFF
     * @since 1.0.0
     */
    protected void init() {
        // to override and use this method
    }

    /**
     * @return the title value
     * @author WFF
     * @since 1.0.0
     */
    public String getValue() {
        return super.getAttributeValue();
    }

    /**
     * @param value
     *                  the title value to set
     * @author WFF
     * @since 1.0.0
     */
    public void setValue(final String value) {
        super.setAttributeValue(value);
    }

    /**
     * sets the value for this attribute
     *
     * @param updateClient
     *                         true to update client browser page if it is
     *                         available. The default value is true but it will
     *                         be ignored if there is no client browser page.
     * @param value
     *                         the value for the attribute.
     * @since 2.1.15
     * @author WFF
     */
    public void setValue(final boolean updateClient, final String value) {
        super.setAttributeValue(updateClient, value);
    }

}
