package com.webfirmframework.wffweb.tag.html.images;

import java.util.logging.Logger;

import com.webfirmframework.wffweb.settings.WffConfiguration;
import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.tag.html.identifier.GlobalAttributable;
import com.webfirmframework.wffweb.tag.html.identifier.ImgAttributable;

/**
 * @author WFF
 * @since 1.0.0
 * @version 1.0.0
 *
 */
public class Img extends AbstractHtml {

    private static final long serialVersionUID = 1_0_0L;

    public static final Logger LOGGER = Logger.getLogger(Img.class.getName());

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
    public Img(final AbstractHtml base, final AbstractAttribute... attributes) {
        super(Img.class.getSimpleName().toLowerCase(), base, attributes);
        if (WffConfiguration.isDirectionWarningOn()) {
            for (final AbstractAttribute abstractAttribute : attributes) {
                if (!(abstractAttribute != null && (abstractAttribute instanceof ImgAttributable || abstractAttribute instanceof GlobalAttributable))) {
                    LOGGER.warning(abstractAttribute
                            + " is not an instance of ImgAttribute");
                }
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
