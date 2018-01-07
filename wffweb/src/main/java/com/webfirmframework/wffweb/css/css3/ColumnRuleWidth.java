/*
 * Copyright 2014-2018 Web Firm Framework
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
package com.webfirmframework.wffweb.css.css3;

import com.webfirmframework.wffweb.InvalidValueException;
import com.webfirmframework.wffweb.NullValueException;
import com.webfirmframework.wffweb.css.CssLengthUnit;
import com.webfirmframework.wffweb.css.CssNameConstants;
import com.webfirmframework.wffweb.css.core.AbstractCssProperty;
import com.webfirmframework.wffweb.util.TagStringUtil;

/**
 * <pre>
 * column-rule-width: medium|thin|thick|length|initial|inherit;
 *
 * The column-rule-width property specifies the width of the rule between columns.
 * Default value:  medium
 * Inherited:      no
 * Animatable:     yes
 * Version:        CSS3
 * JavaScript syntax:      <i>object</i>.style.columnRuleWidth="5px"
 * </pre>
 *
 *
 * @author WFF
 * @since 1.0.0
 */
public class ColumnRuleWidth extends AbstractCssProperty<ColumnRuleWidth> {

    private static final long serialVersionUID = 1_0_0L;

    public static final String MEDIUM = "medium";
    public static final String THIN = "thin";
    public static final String THICK = "thick";
    public static final String INITIAL = "initial";
    public static final String INHERIT = "inherit";

    private String cssValue;
    private Float value;
    private CssLengthUnit cssLengthUnit;

    /**
     * The {@code medium} will be set as the value
     */
    public ColumnRuleWidth() {
        setCssValue(MEDIUM);
    }

    /**
     * @param cssValue
     *            the css value to set.
     */
    public ColumnRuleWidth(final String cssValue) {
        setCssValue(cssValue);
    }

    /**
     * @param columnRuleWidth
     *            the {@code ColumnRuleWidth} object from which the cssValue to
     *            set.And, {@code null} will throw {@code NullValueException}
     */
    public ColumnRuleWidth(final ColumnRuleWidth columnRuleWidth) {
        if (columnRuleWidth == null) {
            throw new NullValueException("columnRuleWidth can not be null");
        }
        setCssValue(columnRuleWidth.getCssValue());
    }

    /**
     * @param percent
     *            the percentage value to set. The cssLengthUnit will
     *            automatically set to %.
     * @since 1.0.0
     * @author WFF
     */
    public ColumnRuleWidth(final float percent) {
        cssLengthUnit = CssLengthUnit.PER;
        value = percent;
        cssValue = String.valueOf(percent) + cssLengthUnit;
    }

    /**
     * @param value
     * @param cssLengthUnit
     */
    public ColumnRuleWidth(final float value,
            final CssLengthUnit cssLengthUnit) {
        this.value = value;
        this.cssLengthUnit = cssLengthUnit;
        cssValue = String.valueOf(value) + cssLengthUnit;
    }

    /**
     * @param value
     * @param cssLengthUnit
     * @return the current object
     * @since 1.0.0
     * @author WFF
     */
    public ColumnRuleWidth setValue(final float value,
            final CssLengthUnit cssLengthUnit) {
        this.value = value;
        this.cssLengthUnit = cssLengthUnit;
        cssValue = String.valueOf(value) + cssLengthUnit;
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
        cssValue = String.valueOf(percent) + cssLengthUnit;
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
        return CssNameConstants.COLUMN_RULE_WIDTH;
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
     * gets the column-rule-width in float value.
     * {@code ColumnRuleWidth#getUnit()} should be used to get the cssLengthUnit
     * for this value.
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
    public ColumnRuleWidth setCssValue(final String cssValue) {
        try {
            if (cssValue == null) {
                throw new NullValueException(
                        "null is an invalid value. The value format should be as for example 75px or 85%. Or, initial/inherit.");
            } else {
                final String trimmedCssValue = cssValue.trim();
                boolean invalidValue = true;
                for (final CssLengthUnit cssLengthUnit : CssLengthUnit
                        .values()) {
                    final String unit = cssLengthUnit.getUnit();
                    if (trimmedCssValue.endsWith(unit)) {
                        final String valueOnly = cssValue.replaceFirst(unit,
                                "");
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
                if (trimmedCssValue.equalsIgnoreCase(INITIAL)
                        || trimmedCssValue.equalsIgnoreCase(INHERIT)
                        || trimmedCssValue.equalsIgnoreCase(MEDIUM)
                        || trimmedCssValue.equalsIgnoreCase(THIN)
                        || trimmedCssValue.equalsIgnoreCase(THICK)) {
                    this.cssValue = trimmedCssValue.toLowerCase();
                    cssLengthUnit = null;
                    value = null;
                    invalidValue = false;
                }
                if (invalidValue) {
                    throw new InvalidValueException(cssValue
                            + " is an invalid value. The value format should be as for example 75px, 85%, initial, inherit etc..");
                }
            }
            if (getStateChangeInformer() != null) {
                getStateChangeInformer().stateChanged(this);
            }
        } catch (final NumberFormatException e) {
            throw new InvalidValueException(
                    cssValue + " is an invalid value. The value format should be as for example 75px or 85%. Or, initial/inherit.",
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
     * @param cssValue
     * @return true if the given css value is valid.
     * @since 1.0.0
     * @author WFF
     */
    public static boolean isValid(final String cssValue) {
        if (cssValue == null) {
            return false;
        }

        final String trimmedCssValue = cssValue.trim();

        if (trimmedCssValue.isEmpty()) {
            return false;
        }

        final String trimmedCssValueLowerCase = TagStringUtil
                .toLowerCase(trimmedCssValue);
        if (MEDIUM.equals(trimmedCssValueLowerCase)
                || THIN.equals(trimmedCssValueLowerCase)
                || THICK.equals(trimmedCssValueLowerCase)
                || INITIAL.equals(trimmedCssValueLowerCase)
                || INHERIT.equals(trimmedCssValueLowerCase)) {
            return true;
        }

        for (final CssLengthUnit cssLengthUnit : CssLengthUnit.values()) {
            final String unit = cssLengthUnit.getUnit();
            if (trimmedCssValue.endsWith(unit)) {
                final String valueOnly = cssValue.replaceFirst(unit, "");
                try {
                    Float.parseFloat(valueOnly);
                } catch (final NumberFormatException e) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

}
