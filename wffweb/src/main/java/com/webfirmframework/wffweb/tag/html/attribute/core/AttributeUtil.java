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
/**
 *
 */
package com.webfirmframework.wffweb.tag.html.attribute.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import com.webfirmframework.wffweb.tag.html.attributewff.CustomAttribute;
import com.webfirmframework.wffweb.util.WffBinaryMessageUtil;

/**
 *
 * @author WFF
 * @since 1.0.0
 */
public final class AttributeUtil {

    /**
     *
     */
    private AttributeUtil() {
        throw new AssertionError();
    }

    /**
     * @param rebuild
     * @param attributes
     * @return the attributes string starting with a space.
     * @author WFF
     * @since 1.0.0
     */
    public static String getAttributeHtmlString(final boolean rebuild,
            final AbstractAttribute... attributes) {
        if (attributes != null) {

            // prefix + total delimiters
            // 1 + (attributes.length - 1)
            // StringUtil.join for reference
            int capacity = attributes.length;

            final String[] htmlStrings = new String[attributes.length];
            for (int i = 0; i < attributes.length; i++) {
                final String htmlString = attributes[i].toHtmlString(rebuild);
                htmlStrings[i] = htmlString;
                capacity += htmlString.length();
            }

            final StringBuilder attributeSB = new StringBuilder(capacity);
            for (final String htmlString : htmlStrings) {
                attributeSB.append(' ').append(htmlString);
            }

            return attributeSB.toString();
        }
        return "";
    }

    /**
     * @param rebuild
     * @param attributes
     * @param charset
     *                       the charset
     * @return the attributes string starting with a space.
     * @author WFF
     * @since 1.0.0
     */
    public static String getAttributeHtmlString(final boolean rebuild,
            final Charset charset, final AbstractAttribute... attributes) {
        if (attributes != null) {

            // prefix + total delimiters
            // 1 + (attributes.length - 1)
            // StringUtil.join for reference
            int capacity = attributes.length;

            final String[] htmlStrings = new String[attributes.length];
            for (int i = 0; i < attributes.length; i++) {
                final String htmlString = attributes[i].toHtmlString(rebuild,
                        charset);
                htmlStrings[i] = htmlString;
                capacity += htmlString.length();
            }

            final StringBuilder attributeSB = new StringBuilder(capacity);
            for (final String htmlString : htmlStrings) {
                attributeSB.append(' ').append(htmlString);
            }

            return attributeSB.toString();
        }
        return "";
    }

    /**
     * @param rebuild
     * @param attributes
     * @param charset
     *                       the charset
     * @return the attributes bytes array compressed by index
     * @author WFF
     * @throws IOException
     * @since 1.1.3
     */
    public static byte[][] getAttributeHtmlBytesCompressedByIndex(
            final boolean rebuild, final Charset charset,
            final AbstractAttribute... attributes) throws IOException {

        if (attributes != null) {
            final byte[][] attributesArray = new byte[attributes.length][0];

            for (int i = 0; i < attributesArray.length; i++) {
                final AbstractAttribute attribute = attributes[i];
                attributesArray[i] = attribute.toCompressedBytesByIndex(rebuild,
                        charset);
            }
            return attributesArray;

        }
        return new byte[0][0];
    }

    /**
     * @param attributes
     * @param charset
     *                       the charset
     * @return the wff attributes strings bytes array.
     * @author WFF
     * @throws UnsupportedEncodingException
     *                                          throwing this exception will be
     *                                          removed in future version
     *                                          because its internal
     *                                          implementation will never make
     *                                          this exception due to the code
     *                                          changes since 3.0.1.
     * @since 2.0.0
     */
    public static byte[][] getWffAttributeBytes(final String charset,
            final AbstractAttribute... attributes)
            throws UnsupportedEncodingException {
        return getWffAttributeBytes(Charset.forName(charset), attributes);
    }

    /**
     * @param attributes
     * @param charset
     *                       the charset
     * @return the wff attributes strings bytes array.
     * @author WFF
     * @since 3.0.1
     */
    public static byte[][] getWffAttributeBytes(final Charset charset,
            final AbstractAttribute... attributes) {

        if (attributes != null) {
            final byte[][] attributesArray = new byte[attributes.length][0];

            for (int i = 0; i < attributesArray.length; i++) {
                final AbstractAttribute attribute = attributes[i];
                attributesArray[i] = attribute.toWffString().getBytes(charset);
            }
            return attributesArray;

        }
        return new byte[0][0];
    }

    /**
     * @param attributesBytes
     * @return the array of attributes built from bytes
     * @since 3.0.3
     */
    public static AbstractAttribute[] parseExactAttributeHtmlBytesCompressedByIndex(
            final byte[][] attributesBytes, final Charset charset) {

        final AbstractAttribute[] attributes = new AbstractAttribute[attributesBytes.length];

        int index = 0;
        for (final byte[] compressedBytesByIndex : attributesBytes) {

            final int lengthOfOptimizedBytesOfAttrNameIndex = compressedBytesByIndex[0];

            if (lengthOfOptimizedBytesOfAttrNameIndex > 0) {
                final byte[] tagNameIndexBytes = new byte[lengthOfOptimizedBytesOfAttrNameIndex];
                System.arraycopy(compressedBytesByIndex, 1, tagNameIndexBytes,
                        0, lengthOfOptimizedBytesOfAttrNameIndex);

                final int attrNameIndex = WffBinaryMessageUtil
                        .getIntFromOptimizedBytes(tagNameIndexBytes);

                final String attrValue = new String(compressedBytesByIndex,
                        compressedBytesByIndex[0] + 1,
                        compressedBytesByIndex.length
                                - (compressedBytesByIndex[0] + 1),
                        charset);

                attributes[index] = AttributeRegistry
                        .getNewAttributeInstanceOrNullIfFailed(attrNameIndex,
                                attrValue);

            } else {

                final String attrNameValue = new String(compressedBytesByIndex,
                        compressedBytesByIndex[0] + 1,
                        compressedBytesByIndex.length
                                - (compressedBytesByIndex[0] + 1),
                        charset);

                final int indexOfEqualChar = attrNameValue.indexOf('=');

                final String attrName;
                final String attrValue;

                if (indexOfEqualChar == -1) {
                    attrName = attrNameValue;
                    attrValue = null;
                } else {
                    attrName = attrNameValue.substring(0, indexOfEqualChar);
                    attrValue = attrNameValue.substring(indexOfEqualChar + 1,
                            attrNameValue.length());
                }

                final AbstractAttribute newAttributeInstance = AttributeRegistry
                        .getNewAttributeInstanceOrNullIfFailed(attrName,
                                attrValue);

                attributes[index] = newAttributeInstance != null
                        ? newAttributeInstance
                        : new CustomAttribute(attrName, attrValue);

            }

            index++;
        }
        return attributes;
    }

}
