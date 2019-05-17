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
 */
package com.webfirmframework.wffweb.tag.html.html5.attribute.global;

import com.webfirmframework.wffweb.WffSecurityException;
import com.webfirmframework.wffweb.tag.html.attribute.AttributeNameConstants;
import com.webfirmframework.wffweb.tag.html.attribute.core.AttributeRegistry;

public class DataWffId extends DataAttribute {

    private static final long serialVersionUID = 1_0_0L;

    private static final String ATTRIBUTE_NAME_EXTENSION = "wff-id";

    // must be kept final to provide atomic consistency across multiple threads
    public static final String ATTRIBUTE_NAME = AttributeNameConstants.DATA
            .concat(ATTRIBUTE_NAME_EXTENSION);

    // must be kept final to provide atomic consistency across multiple threads
    /**
     * @deprecated incorrect naming, the correct naming is ATTRIBUTE_NAME. This
     *             constant variable will be removed in the later version, use
     *             {@link DataWffId#ATTRIBUTE_NAME} constant instead of this.
     */
    @Deprecated
    public static final String TAG_NAME = ATTRIBUTE_NAME;

    // must be kept final to provide atomic consistency across multiple threads
    private final String attributeValue;

    private static final int ATTR_NAME_INDEX;

    static {
        final Integer index = AttributeRegistry
                .getIndexByAttributeName(ATTRIBUTE_NAME);
        ATTR_NAME_INDEX = index != null ? index : -1;
    }

    {
        super.setAttributeNameIndex(ATTR_NAME_INDEX);
    }

    /**
     * @param value
     *                  the value for the attribute
     * @since 2.0.0
     * @author WFF
     */
    public DataWffId(final String value) {
        super(ATTRIBUTE_NAME_EXTENSION, value);
        attributeValue = value;
    }

    @Override
    public void setValue(final String value) {
        throw new WffSecurityException("Not allowed to change its value");
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute#
     * getAttributeName()
     */
    @Override
    public String getAttributeName() {
        // must override and return constant (final variable)
        // to provide atomic across multiple threads
        return ATTRIBUTE_NAME;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute#
     * getAttributeValue()
     */
    @Override
    public String getAttributeValue() {
        // must override and return constant (final variable)
        // to provide atomic across multiple threads
        return attributeValue;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.webfirmframework.wffweb.tag.html.html5.attribute.global.DataAttribute
     * #getValue()
     */
    @Override
    public String getValue() {
        // must override and return constant (final variable)
        // to provide atomic across multiple threads
        return attributeValue;
    }

    @Override
    protected void setAttributeValue(final String attributeValue) {
        throw new WffSecurityException(
                "Not allowed to change value for data-wff-id");
    }

    @Override
    protected void setAttributeName(final String attributeName) {
        throw new WffSecurityException(
                "Not allowed to change value for data-wff-id");
    }

    @Override
    protected void setAttributeValue(final boolean updateClient,
            final String attributeValue) {
        throw new WffSecurityException(
                "Not allowed to change value for data-wff-id");
    }

}
