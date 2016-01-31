package com.webfirmframework.wffweb.tag.html;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.identifier.BrAttributable;
import com.webfirmframework.wffweb.tag.html.identifier.GlobalAttributable;

/**
 * @author WFF
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class Body extends AbstractHtml {

    private static final long serialVersionUID = 1_0_0L;

    public static final Logger LOGGER = Logger.getLogger(Body.class.getName());

    {
        init();
    }

    /**
     * Represents the root of an HTML or XHTML document. All other elements must
     * be descendants of this element.
     *
     * @param base
     *            i.e. parent tag of this tag
     * @param attributes
     *            An array of {@code AbstractAttribute}
     *
     * @since 1.0.0
     */
    public Body(final AbstractHtml base,
            final AbstractAttribute... attributes) {
        super(TagNameConstants.BODY, base, attributes);
        for (final AbstractAttribute abstractAttribute : attributes) {
            if (!(abstractAttribute != null
                    && (abstractAttribute instanceof BrAttributable
                            || abstractAttribute instanceof GlobalAttributable))) {
                LOGGER.warning(abstractAttribute
                        + " is not an instance of BrAttribute");
            }
        }
    }

    /**
     * invokes only once per object
     *
     * @author WFF
     * @since 1.0.0
     */
    protected void init() {
    }

}
