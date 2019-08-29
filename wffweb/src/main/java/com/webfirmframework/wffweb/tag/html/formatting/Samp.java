package com.webfirmframework.wffweb.tag.html.formatting;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.settings.WffConfiguration;
import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.core.PreIndexedTagName;
import com.webfirmframework.wffweb.tag.html.identifier.GlobalAttributable;
import com.webfirmframework.wffweb.tag.html.identifier.SampAttributable;

/**
 * @author WFF
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class Samp extends AbstractHtml {

    private static final long serialVersionUID = 1_0_0L;

    private static final Logger LOGGER = Logger.getLogger(Samp.class.getName());

    private static final PreIndexedTagName PRE_INDEXED_TAG_NAME;

    static {

        PRE_INDEXED_TAG_NAME = (PreIndexedTagName.SAMP);

    }

    {

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
    public Samp(final AbstractHtml base,
            final AbstractAttribute... attributes) {
        super(PRE_INDEXED_TAG_NAME, base, attributes);
        if (WffConfiguration.isDirectionWarningOn()) {
            warnForUnsupportedAttributes(attributes);
        }
    }

    private static void warnForUnsupportedAttributes(
            final AbstractAttribute... attributes) {
        for (final AbstractAttribute abstractAttribute : attributes) {
            if (!(abstractAttribute != null
                    && (abstractAttribute instanceof SampAttributable
                            || abstractAttribute instanceof GlobalAttributable))) {
                LOGGER.warning(abstractAttribute
                        + " is not an instance of SampAttribute");
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
