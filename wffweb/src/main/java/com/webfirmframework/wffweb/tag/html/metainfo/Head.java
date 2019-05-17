package com.webfirmframework.wffweb.tag.html.metainfo;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.settings.WffConfiguration;
import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.TagNameConstants;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.core.IndexedTagName;
import com.webfirmframework.wffweb.tag.html.identifier.GlobalAttributable;
import com.webfirmframework.wffweb.tag.html.identifier.HeadAttributable;

/**
 * @author WFF
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class Head extends AbstractHtml {

    private static final long serialVersionUID = 1_0_0L;

    public static final Logger LOGGER = Logger.getLogger(Head.class.getName());

    private static final int TAG_NAME_INDEX;

    static {
        final Integer index = IndexedTagName.INSTANCE
                .getIndexByTagName(TagNameConstants.HEAD);
        TAG_NAME_INDEX = index != null ? index : -1;
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
    public Head(final AbstractHtml base,
            final AbstractAttribute... attributes) {
        super(TagNameConstants.HEAD, base, attributes);
        if (WffConfiguration.isDirectionWarningOn()) {
            warnForUnsupportedAttributes(attributes);
        }
    }

    private static void warnForUnsupportedAttributes(
            final AbstractAttribute... attributes) {
        for (final AbstractAttribute abstractAttribute : attributes) {
            if (!(abstractAttribute != null
                    && (abstractAttribute instanceof HeadAttributable
                            || abstractAttribute instanceof GlobalAttributable))) {
                LOGGER.warning(abstractAttribute
                        + " is not an instance of HeadAttribute");
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
