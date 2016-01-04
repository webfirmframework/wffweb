/*
 * Copyright 2014-2016 Web Firm Framework
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
package com.webfirmframework.wffweb.css;

import java.util.Arrays;
import java.util.List;

import com.webfirmframework.wffweb.InvalidValueException;
import com.webfirmframework.wffweb.NullValueException;
import com.webfirmframework.wffweb.css.core.AbstractCssProperty;
import com.webfirmframework.wffweb.util.TagStringUtil;

/**
 * <pre>
 * border-right-width: medium|thin|thick|length|initial|inherit;
 * 
 * The border-right-width property sets the width of an element's right border.
 * 
 * Note: Always declare the border-style property before the border-right-width property. An element must have borders before you can change the width.
 * Default value:  medium
 * Inherited:      no
 * Animatable:     yes
 * Version:        CSS1
 * JavaScript syntax:      <i>object</i>.style.borderRightWidth="5px"
 * </pre>
 *
 *
 * @author WFF
 * @since 1.0.0
 */
public class BorderRightWidth extends AbstractCssProperty<BorderRightWidth> {

    private static final long serialVersionUID = 6592682201749301761L;

    public static final String MEDIUM = "medium";
    public static final String THIN = "thin";
    public static final String THICK = "thick";
    public static final String INITIAL = "initial";
    public static final String INHERIT = "inherit";

    private static final List<String> PREDEFINED_CONSTANTS = Arrays.asList(
            INITIAL, INHERIT, MEDIUM, THIN, THICK);

    private String cssValue;
    private Float value;
    private CssLengthUnit cssLengthUnit;

    /**
     * The {@code medium} will be set as the value
     */
    public BorderRightWidth() {
        setCssValue(MEDIUM);
    }

    /**
     * @param cssValue
     *            the css value to set.
     */
    public BorderRightWidth(final String cssValue) {
        setCssValue(cssValue);
    }

    /**
     * @param borderRightWidth
     *            the {@code BorderRightWidth} object from which the cssValue to
     *            set.And, {@code null} will throw {@code NullValueException}
     */
    public BorderRightWidth(final BorderRightWidth borderRightWidth) {
        if (borderRightWidth == null) {
            throw new NullValueException("borderRightWidth can not be null");
        }
        setCssValue(borderRightWidth.getCssValue());
    }

    /**
     * @param percent
     *            the percentage value to set. The cssLengthUnit will
     *            automatically set to %.
     * @since 1.0.0
     * @author WFF
     */
    public BorderRightWidth(final float percent) {
        cssLengthUnit = CssLengthUnit.PER;
        value = percent;
        cssValue = percent + "" + cssLengthUnit;
    }

    /**
     * @param value
     * @param cssLengthUnit
     */
    public BorderRightWidth(final float value, final CssLengthUnit cssLengthUnit) {
        this.value = value;
        this.cssLengthUnit = cssLengthUnit;
        cssValue = value + "" + cssLengthUnit;
    }

    /**
     * @param value
     * @param cssLengthUnit
     * @return the current object
     * @since 1.0.0
     * @author WFF
     */
    public BorderRightWidth setValue(final float value,
            final CssLengthUnit cssLengthUnit) {
        this.value = value;
        this.cssLengthUnit = cssLengthUnit;
        cssValue = value + "" + cssLengthUnit;
        if (getStateChangeInformer() != null) {
            getStateChangeInformer().stateChanged(this);
        }
        return this;
    }

    /**
     * @param percent
     *            the percent to set
     * @since 1.0.0
     * @author WFF
     */
    public void setPercent(final float percent) {
        value = percent;
        cssLengthUnit = CssLengthUnit.PER;
        cssValue = percent + "" + cssLengthUnit;
        if (getStateChangeInformer() != null) {
            getStateChangeInformer().stateChanged(this);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.webfirmframework.wffweb.css.CssProperty#getCssName()
     * 
     * @since 1.0.0
     * 
     * @author WFF
     */
    @Override
    public String getCssName() {
        return CssNameConstants.BORDER_RIGHT_WIDTH;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.webfirmframework.wffweb.css.CssProperty#getCssValue()
     * 
     * @since 1.0.0
     * 
     * @author WFF
     */
    @Override
    public String getCssValue() {
        return cssValue;
    }

    @Override
    public String toString() {
        return getCssName() + ": " + getCssValue();
    }

    /**
     * gets the border-right-width in {@code Float} value.
     * {@code BorderRightWidth#getUnit()} should be used to get the
     * cssLengthUnit for this value.
     *
     * @return the value in float or null if the cssValue is
     *         <code>initial</code> or <code>inherit</code>.
     * @since 1.0.0
     * @author WFF
     */
    public Float getValue() {
        return value;
    }

    /**
     * @return the cssLengthUnit {@code PX}/{@code PER}, or {@code null} if the
     *         value is any inbuilt value like {@code inherit}.
     * @since 1.0.0
     * @author WFF
     */
    public CssLengthUnit getUnit() {
        return cssLengthUnit;
    }

    /**
     * @param cssValue
     *            the value should be in the format of <code>55px</code> or
     *            <code>95%</code>. {@code null} is considered as an invalid
     *            value and it will throw {@code NullValueException}.
     * @since 1.0.0
     * @author WFF
     */
    @Override
    public BorderRightWidth setCssValue(final String cssValue) {
        final String previousCssValue = this.cssValue;
        try {
            if (cssValue == null) {
                throw new NullValueException(
                        "null is an invalid value. The value format should be as for example 75px or 85%. Or, initial/inherit.");
            } else {
                final String trimmedCssValue = TagStringUtil
                        .toLowerCase(cssValue.trim());
                boolean invalidValue = true;
                for (final CssLengthUnit cssLengthUnit : CssLengthUnit.values()) {
                    final String unit = cssLengthUnit.getUnit();
                    if (trimmedCssValue.endsWith(unit)) {
                        final String valueOnly = trimmedCssValue.replaceFirst(
                                unit, "");
                        try {
                            value = Float.parseFloat(valueOnly);
                        } catch (final NumberFormatException e) {
                            break;
                        }
                        this.cssLengthUnit = cssLengthUnit;
                        this.cssValue = cssValue;
                        invalidValue = false;
                        break;
                    }
                }
                if (PREDEFINED_CONSTANTS.contains(trimmedCssValue)) {
                    this.cssValue = trimmedCssValue;
                    cssLengthUnit = null;
                    value = null;
                    invalidValue = false;
                }
                if (invalidValue) {
                    throw new InvalidValueException(
                            cssValue
                                    + " is an invalid value. The value format should be as for example 75px, 85%, initial, inherit etc..");
                }
            }
            if (getStateChangeInformer() != null) {
                getStateChangeInformer().stateChanged(this);
            }
        } catch (final NumberFormatException | InvalidValueException e) {
            this.cssValue = previousCssValue;
            throw new InvalidValueException(
                    cssValue
                            + " is an invalid value. The value format should be as for example 75px or 85%. Or, initial/inherit.",
                    e);
        }
        return this;
    }

    /**
     * sets as {@code initial}
     *
     * @since 1.0.0
     * @author WFF
     */
    public void setAsInitial() {
        setCssValue(INITIAL);
    }

    /**
     * sets as {@code inherit}
     *
     * @since 1.0.0
     * @author WFF
     */
    public void setAsInherit() {
        setCssValue(INHERIT);
    }

    /**
     * sets as {@code medium}.
     *
     * @since 1.0.0
     * @author WFF
     */
    public void setAsMedium() {
        setCssValue(MEDIUM);
    }

    /**
     * sets as {@code thin}.
     *
     * @since 1.0.0
     * @author WFF
     */
    public void setAsThin() {
        setCssValue(THIN);
    }

    /**
     * sets as {@code thick}.
     *
     * @since 1.0.0
     * @author WFF
     */
    public void setAsThick() {
        setCssValue(THICK);
    }

    /**
     * validates if the given cssValue is valid for this class.
     *
     * @param cssValue
     *            the value to check.
     * @return true if valid and false if invalid.
     * @author WFF
     * @since 1.0.0
     */
    public static boolean isValid(final String cssValue) {
        final String trimmedCssValue = cssValue.trim().toLowerCase();
        if (trimmedCssValue.contains(" ")) {
            return false;
        }
        for (final CssLengthUnit cssLengthUnit : CssLengthUnit.values()) {
            final String unit = cssLengthUnit.getUnit();
            if (trimmedCssValue.endsWith(unit)) {
                final String valueOnly = trimmedCssValue.replaceFirst(unit, "");
                try {
                    Float.parseFloat(valueOnly);
                } catch (final NumberFormatException e) {
                    break;
                }
                return true;
            }
        }
        if (PREDEFINED_CONSTANTS.contains(trimmedCssValue)) {
            return true;
        }
        return false;
    }
}
