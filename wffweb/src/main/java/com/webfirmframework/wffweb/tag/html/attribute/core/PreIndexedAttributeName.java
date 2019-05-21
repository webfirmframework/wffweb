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
package com.webfirmframework.wffweb.tag.html.attribute.core;

import com.webfirmframework.wffweb.tag.html.attribute.AttributeNameConstants;
import com.webfirmframework.wffweb.tag.html.attribute.InternalAttrNameConstants;

/**
 * @author WFF
 * @since 3.0.3
 */
public enum PreIndexedAttributeName {

    // NB: order should not be changed it is ordered by length

    ID(AttributeNameConstants.ID),

    MIN(AttributeNameConstants.MIN),

    LOW(AttributeNameConstants.LOW),

    MAX(AttributeNameConstants.MAX),

    DIR(AttributeNameConstants.DIR),

    REL(AttributeNameConstants.REL),

    REV(AttributeNameConstants.REV),

    ALT(AttributeNameConstants.ALT),

    FOR(AttributeNameConstants.FOR),

    SRC(AttributeNameConstants.SRC),

    TYPE(AttributeNameConstants.TYPE),

    HREF(AttributeNameConstants.HREF),

    SIZE(AttributeNameConstants.SIZE),

    ROLE(AttributeNameConstants.ROLE),

    FACE(AttributeNameConstants.FACE),

    WRAP(AttributeNameConstants.WRAP),

    OPEN(AttributeNameConstants.OPEN),

    HIGH(AttributeNameConstants.HIGH),

    LOOP(AttributeNameConstants.LOOP),

    LIST(AttributeNameConstants.LIST),

    NAME(AttributeNameConstants.NAME),

    LANG(AttributeNameConstants.LANG),

    COLS(AttributeNameConstants.COLS),

    ROWS(AttributeNameConstants.ROWS),

    FORM(AttributeNameConstants.FORM),

    STEP(AttributeNameConstants.STEP),

    ISMAP(AttributeNameConstants.ISMAP),

    SIZES(AttributeNameConstants.SIZES),

    SHAPE(AttributeNameConstants.SHAPE),

    STYLE(AttributeNameConstants.STYLE),

    ONCUT(AttributeNameConstants.ONCUT),

    MUTED(AttributeNameConstants.MUTED),

    // not required to index
    // DATA_(AttributeNameConstants.DATA),

    WIDTH(AttributeNameConstants.WIDTH),

    ALIGN(AttributeNameConstants.ALIGN),

    NONCE(AttributeNameConstants.NONCE),

    DEFER(AttributeNameConstants.DEFER),

    COLOR(AttributeNameConstants.COLOR),

    MEDIA(AttributeNameConstants.MEDIA),

    TITLE(AttributeNameConstants.TITLE),

    SCOPE(AttributeNameConstants.SCOPE),

    CLASS(AttributeNameConstants.CLASS),

    VALUE(AttributeNameConstants.VALUE),

    ASYNC(AttributeNameConstants.ASYNC),

    ACTION(AttributeNameConstants.ACTION),

    HEIGHT(AttributeNameConstants.HEIGHT),

    METHOD(AttributeNameConstants.METHOD),

    ACCEPT(AttributeNameConstants.ACCEPT),

    SORTED(AttributeNameConstants.SORTED),

    ONDRAG(AttributeNameConstants.ONDRAG),

    POSTER(AttributeNameConstants.POSTER),

    ONPLAY(AttributeNameConstants.ONPLAY),

    SRCSET(AttributeNameConstants.SRCSET),

    COORDS(AttributeNameConstants.COORDS),

    USEMAP(AttributeNameConstants.USEMAP),

    ONBLUR(AttributeNameConstants.ONBLUR),

    NOHREF(AttributeNameConstants.NOHREF),

    ONCOPY(AttributeNameConstants.ONCOPY),

    BORDER(AttributeNameConstants.BORDER),

    HIDDEN(AttributeNameConstants.HIDDEN),

    ONDROP(AttributeNameConstants.ONDROP),

    TARGET(AttributeNameConstants.TARGET),

    ONLOAD(AttributeNameConstants.ONLOAD),

    ONSHOW(AttributeNameConstants.ONSHOW),

    ONFOCUS(AttributeNameConstants.ONFOCUS),

    PRELOAD(AttributeNameConstants.PRELOAD),

    DIRNAME(AttributeNameConstants.DIRNAME),

    ENCTYPE(AttributeNameConstants.ENCTYPE),

    ONCLICK(AttributeNameConstants.ONCLICK),

    ONKEYUP(AttributeNameConstants.ONKEYUP),

    ONRESET(AttributeNameConstants.ONRESET),

    CHARSET(AttributeNameConstants.CHARSET),

    PATTERN(AttributeNameConstants.PATTERN),

    COLSPAN(AttributeNameConstants.COLSPAN),

    DEFAULT(AttributeNameConstants.DEFAULT),

    OPTIMUM(AttributeNameConstants.OPTIMUM),

    ONENDED(AttributeNameConstants.ONENDED),

    ROWSPAN(AttributeNameConstants.ROWSPAN),

    ONPAUSE(AttributeNameConstants.ONPAUSE),

    CHECKED(AttributeNameConstants.CHECKED),

    ONWHEEL(AttributeNameConstants.ONWHEEL),

    ONABORT(AttributeNameConstants.ONABORT),

    ONERROR(AttributeNameConstants.ONERROR),

    CONTENT(AttributeNameConstants.CONTENT),

    ONINPUT(AttributeNameConstants.ONINPUT),

    HEADERS(AttributeNameConstants.HEADERS),

    SANDBOX(AttributeNameConstants.SANDBOX),

    ONPASTE(AttributeNameConstants.ONPASTE),

    REQUIRED(AttributeNameConstants.REQUIRED),

    READONLY(AttributeNameConstants.READONLY),

    ONSELECT(AttributeNameConstants.ONSELECT),

    ONSCROLL(AttributeNameConstants.ONSCROLL),

    TABINDEX(AttributeNameConstants.TABINDEX),

    ONCHANGE(AttributeNameConstants.ONCHANGE),

    ONSEEKED(AttributeNameConstants.ONSEEKED),

    ONTOGGLE(AttributeNameConstants.ONTOGGLE),

    DROPZONE(AttributeNameConstants.DROPZONE),

    DISABLED(AttributeNameConstants.DISABLED),

    CONTROLS(AttributeNameConstants.CONTROLS),

    ONRESIZE(AttributeNameConstants.ONRESIZE),

    DOWNLOAD(AttributeNameConstants.DOWNLOAD),

    DATETIME(AttributeNameConstants.DATETIME),

    SELECTED(AttributeNameConstants.SELECTED),

    AUTOPLAY(AttributeNameConstants.AUTOPLAY),

    HREFLANG(AttributeNameConstants.HREFLANG),

    ONONLINE(AttributeNameConstants.ONONLINE),

    ONSEARCH(AttributeNameConstants.ONSEARCH),

    ONSUBMIT(AttributeNameConstants.ONSUBMIT),

    MULTIPLE(AttributeNameConstants.MULTIPLE),

    ONUNLOAD(AttributeNameConstants.ONUNLOAD),

    REVERSED(AttributeNameConstants.REVERSED),

    ONFOCUSIN(AttributeNameConstants.ONFOCUSIN),

    ONDRAGEND(AttributeNameConstants.ONDRAGEND),

    ONINVALID(AttributeNameConstants.ONINVALID),

    ONKEYDOWN(AttributeNameConstants.ONKEYDOWN),

    MAXLENGTH(AttributeNameConstants.MAXLENGTH),

    ONOFFLINE(AttributeNameConstants.ONOFFLINE),

    DRAGGABLE(AttributeNameConstants.DRAGGABLE),

    ONSEEKING(AttributeNameConstants.ONSEEKING),

    ONPLAYING(AttributeNameConstants.ONPLAYING),

    ONSUSPEND(AttributeNameConstants.ONSUSPEND),

    ONWAITING(AttributeNameConstants.ONWAITING),

    ONCANPLAY(AttributeNameConstants.ONCANPLAY),

    ONEMPTIED(AttributeNameConstants.ONEMPTIED),

    TRANSLATE(AttributeNameConstants.TRANSLATE),

    ACCESSKEY(AttributeNameConstants.ACCESSKEY),

    ONSTORAGE(AttributeNameConstants.ONSTORAGE),

    MINLENGTH(AttributeNameConstants.MINLENGTH),

    ONMOUSEUP(AttributeNameConstants.ONMOUSEUP),

    ONSTALLED(AttributeNameConstants.ONSTALLED),

    AUTOFOCUS(AttributeNameConstants.AUTOFOCUS),

    ONKEYPRESS(AttributeNameConstants.ONKEYPRESS),

    ONDBLCLICK(AttributeNameConstants.ONDBLCLICK),

    ONFOCUSOUT(AttributeNameConstants.ONFOCUSOUT),

    SPELLCHECK(AttributeNameConstants.SPELLCHECK),

    ONPAGESHOW(AttributeNameConstants.ONPAGESHOW),

    ONMOUSEOUT(AttributeNameConstants.ONMOUSEOUT),

    ONTOUCHEND(AttributeNameConstants.ONTOUCHEND),

    FORMACTION(AttributeNameConstants.FORMACTION),

    ONPROGRESS(AttributeNameConstants.ONPROGRESS),

    HTTP_EQUIV(AttributeNameConstants.HTTP_EQUIV),

    FORMMETHOD(AttributeNameConstants.FORMMETHOD),

    ONPAGEHIDE(AttributeNameConstants.ONPAGEHIDE),

    ONDRAGOVER(AttributeNameConstants.ONDRAGOVER),

    FORMTARGET(AttributeNameConstants.FORMTARGET),

    ONPOPSTATE(AttributeNameConstants.ONPOPSTATE),

    ONMOUSEMOVE(AttributeNameConstants.ONMOUSEMOVE),

    ONMOUSEOVER(AttributeNameConstants.ONMOUSEOVER),

    FORMENCTYPE(AttributeNameConstants.FORMENCTYPE),

    CELLPADDING(AttributeNameConstants.CELLPADDING),

    ONDRAGENTER(AttributeNameConstants.ONDRAGENTER),

    DATA_WFF_ID(InternalAttrNameConstants.DATA_WFF_ID),

    CONTEXTMENU(AttributeNameConstants.CONTEXTMENU),

    ONMOUSEDOWN(AttributeNameConstants.ONMOUSEDOWN),

    ONLOADSTART(AttributeNameConstants.ONLOADSTART),

    PLACEHOLDER(AttributeNameConstants.PLACEHOLDER),

    ONDRAGLEAVE(AttributeNameConstants.ONDRAGLEAVE),

    ONTOUCHMOVE(AttributeNameConstants.ONTOUCHMOVE),

    ONDRAGSTART(AttributeNameConstants.ONDRAGSTART),

    CELLSPACING(AttributeNameConstants.CELLSPACING),

    ONRATECHANGE(AttributeNameConstants.ONRATECHANGE),

    ANIMATIONEND(AttributeNameConstants.ANIMATIONEND),

    ONAFTERPRINT(AttributeNameConstants.ONAFTERPRINT),

    ONMOUSELEAVE(AttributeNameConstants.ONMOUSELEAVE),

    ONLOADEDDATA(AttributeNameConstants.ONLOADEDDATA),

    ONTOUCHSTART(AttributeNameConstants.ONTOUCHSTART),

    ONTIMEUPDATE(AttributeNameConstants.ONTIMEUPDATE),

    ONHASHCHANGE(AttributeNameConstants.ONHASHCHANGE),

    ONMOUSEENTER(AttributeNameConstants.ONMOUSEENTER),

    AUTOCOMPLETE(AttributeNameConstants.AUTOCOMPLETE),

    ONCONTEXTMENU(AttributeNameConstants.ONCONTEXTMENU),

    ONBEFOREPRINT(AttributeNameConstants.ONBEFOREPRINT),

    ONTOUCHCANCEL(AttributeNameConstants.ONTOUCHCANCEL),

    TRANSITIONEND(AttributeNameConstants.TRANSITIONEND),

    ONBEFOREUNLOAD(AttributeNameConstants.ONBEFOREUNLOAD),

    ANIMATIONSTART(AttributeNameConstants.ANIMATIONSTART),

    FORMNOVALIDATE(AttributeNameConstants.FORMNOVALIDATE),

    ACCEPT_CHARSET(AttributeNameConstants.ACCEPT_CHARSET),

    ONVOLUMECHANGE(AttributeNameConstants.ONVOLUMECHANGE),

    CONTENTEDITABLE(AttributeNameConstants.CONTENTEDITABLE),

    ONCANPLAYTHROUGH(AttributeNameConstants.ONCANPLAYTHROUGH),

    ONDURATIONCHANGE(AttributeNameConstants.ONDURATIONCHANGE),

    ONLOADEDMETADATA(AttributeNameConstants.ONLOADEDMETADATA),

    ANIMATIONITERATION(AttributeNameConstants.ANIMATIONITERATION);

    private final String attrName;

    private final int index;

    /**
     * @param attrName
     * @since 3.0.3
     */
    private PreIndexedAttributeName(final String attrName) {
        this.attrName = attrName;
        index = ordinal();
    }

    /**
     * @return the attribute name
     * @since 3.0.3
     */
    public String getName() {
        return attrName;
    }

    /**
     * @return the index
     * @since 3.0.3
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param obj
     * @return the index
     * @since 3.0.3
     */
    public static int getIndex(final PreIndexedAttributeName obj) {
        return obj.ordinal();
    }
}