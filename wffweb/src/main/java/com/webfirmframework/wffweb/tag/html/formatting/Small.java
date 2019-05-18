package com.webfirmframework.wffweb.tag.html.formatting;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.settings.WffConfiguration;
import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.TagNameConstants;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.core.PreIndexedTagName;
import com.webfirmframework.wffweb.tag.html.identifier.GlobalAttributable;
import com.webfirmframework.wffweb.tag.html.identifier.SmallAttributable;

/**
 * @author WFF
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class Small extends AbstractHtml {

    private static final long serialVersionUID = 1_0_0L;

    public static final Logger LOGGER = Logger.getLogger(Small.class.getName());

    private static final int TAG_NAME_INDEX;

    static {

        TAG_NAME_INDEX = PreIndexedTagName.getIndex(PreIndexedTagName.SMALL);

    }

    {

        super.setTagNameIndex(TAG_NAME_INDEX);
        init();
    }

    /**
     *
     * @param base
     *                       i.e. parent tag of this tag
     * @param attributes
     *                       An array of {@code AbstractAttribute}
     *
     * @since 1.0.0
     */
    public Small(final AbstractHtml base,
            final AbstractAttribute... attributes) {
        super(TagNameConstants.SMALL, base, attributes);
        if (WffConfiguration.isDirectionWarningOn()) {
            warnForUnsupportedAttributes(attributes);
        }
    }

    private static void warnForUnsupportedAttributes(
            final AbstractAttribute... attributes) {
        for (final AbstractAttribute abstractAttribute : attributes) {
            if (!(abstractAttribute != null
                    && (abstractAttribute instanceof SmallAttributable
                            || abstractAttribute instanceof GlobalAttributable))) {
                LOGGER.warning(abstractAttribute
                        + " is not an instance of SmallAttribute");
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
        // to override and use this method
    }

}
