package com.webfirmframework.wffweb.tag.html.lists;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.settings.WffConfiguration;
import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.TagNameConstants;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.core.IndexedTagName;
import com.webfirmframework.wffweb.tag.html.identifier.DtAttributable;
import com.webfirmframework.wffweb.tag.html.identifier.GlobalAttributable;

/**
 * @author WFF
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class Dt extends AbstractHtml {

    private static final long serialVersionUID = 1_0_0L;

    public static final Logger LOGGER = Logger.getLogger(Dt.class.getName());

    private static volatile int TAG_NAME_INDEX = -1;

    static {
        final Integer index = IndexedTagName.INSTANCE
                .getIndexByTagName(TagNameConstants.DT);
        TAG_NAME_INDEX = index != null ? index : -1;
    }

    {
        if (TAG_NAME_INDEX == -1) {
            final Integer index = IndexedTagName.INSTANCE
                    .getIndexByTagName(TagNameConstants.DT);
            TAG_NAME_INDEX = index != null ? index : -1;
        }
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
    public Dt(final AbstractHtml base, final AbstractAttribute... attributes) {
        super(TagNameConstants.DT, base, attributes);
        if (WffConfiguration.isDirectionWarningOn()) {
            warnForUnsupportedAttributes(attributes);
        }
    }

    private static void warnForUnsupportedAttributes(
            final AbstractAttribute... attributes) {
        for (final AbstractAttribute abstractAttribute : attributes) {
            if (!(abstractAttribute != null
                    && (abstractAttribute instanceof DtAttributable
                            || abstractAttribute instanceof GlobalAttributable))) {
                LOGGER.warning(abstractAttribute
                        + " is not an instance of DtAttribute");
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
