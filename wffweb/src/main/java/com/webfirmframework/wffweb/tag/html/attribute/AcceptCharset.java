/*
 * Copyright 2014-2017 Web Firm Framework
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
package com.webfirmframework.wffweb.tag.html.attribute;

import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.identifier.FormAttributable;

/**
 *
 * <code>name</code> attribute for the element. This attribute is supported by
 * multiple tags.
 *
 * @since 2.1.5
 * @author WFF
 *
 */
public class AcceptCharset extends AbstractAttribute
        implements FormAttributable {

    private static final long serialVersionUID = 1_0_0L;

    public static final String ISO_8859_1 = "ISO-8859-1";

    public static final String UTF_8 = "UTF-8";

    {
        super.setAttributeName(AttributeNameConstants.ACCEPT_CHARSET);
        init();
    }

    /**
     *
     * @param value
     *            the value for the attribute
     * @since 2.1.5
     * @author WFF
     */
    public AcceptCharset(final String value) {
        setAttributeValue(value);
    }

    /**
     * sets the value for this attribute
     *
     * @param value
     *            the value for the attribute.
     * @since 2.1.5
     * @author WFF
     */
    public void setValue(final String value) {
        super.setAttributeValue(value);
    }

    /**
     * gets the value of this attribute
     *
     * @return the value of the attribute
     * @since 2.1.5
     * @author WFF
     */
    public String getValue() {
        return super.getAttributeValue();
    }

    /**
     * invokes only once per object
     *
     * @author WFF
     * @since 2.1.5
     */
    protected void init() {
        // to override and use this method
    }

}