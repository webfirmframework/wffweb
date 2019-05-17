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
package com.webfirmframework.wffweb.tag.html.html5.attribute;

import com.webfirmframework.wffweb.tag.html.attribute.AttributeNameConstants;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.core.IndexedAttributeName;
import com.webfirmframework.wffweb.tag.html.identifier.AAttributable;

/**
 *
 * This attribute specifies the media which the linked resource applies to. Its
 * value must be a media query. This attribute is mainly useful when linking to
 * external stylesheets by allowing the user agent to pick the best adapted one
 * for the device it runs on.
 *
 * @author WFF
 * @since 1.0.0
 */
public class Media extends AbstractAttribute implements AAttributable {

    private static final long serialVersionUID = 1_0_0L;

    private static final int ATTR_NAME_INDEX;

    static {
        final Integer index = IndexedAttributeName.INSTANCE
                .getIndexByAttributeName(AttributeNameConstants.MEDIA);
        ATTR_NAME_INDEX = index != null ? index : -1;
    }

    {
        super.setAttributeNameIndex(ATTR_NAME_INDEX);
        super.setAttributeName(AttributeNameConstants.MEDIA);
        init();
    }

    /**
     * This attribute specifies the media which the linked resource applies to.
     * Its value must be a media query. This attribute is mainly useful when
     * linking to external stylesheets by allowing the user agent to pick the
     * best adapted one for the device it runs on.
     *
     * @param value
     *                  the value for the attribute
     * @since 1.0.0
     * @author WFF
     */
    public Media(final String value) {
        setAttributeValue(value);
    }

    /**
     * sets the value for this attribute
     *
     * @param value
     *                  the value for the attribute.
     * @since 1.0.0
     * @author WFF
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

    /**
     * gets the value of this attribute
     *
     * @return the value of the attribute
     * @since 1.0.0
     * @author WFF
     */
    public String getValue() {
        return super.getAttributeValue();
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

    // TODO added implementation for value parsing features

}
