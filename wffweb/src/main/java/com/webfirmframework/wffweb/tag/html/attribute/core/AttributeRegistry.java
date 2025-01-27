/*
 * Copyright since 2014 Web Firm Framework
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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.webfirmframework.wffweb.InvalidValueException;
import com.webfirmframework.wffweb.tag.html.attribute.Accept;
import com.webfirmframework.wffweb.tag.html.attribute.AcceptCharset;
import com.webfirmframework.wffweb.tag.html.attribute.Action;
import com.webfirmframework.wffweb.tag.html.attribute.Align;
import com.webfirmframework.wffweb.tag.html.attribute.Alt;
import com.webfirmframework.wffweb.tag.html.attribute.Async;
import com.webfirmframework.wffweb.tag.html.attribute.AttributeNameConstants;
import com.webfirmframework.wffweb.tag.html.attribute.Border;
import com.webfirmframework.wffweb.tag.html.attribute.CellPadding;
import com.webfirmframework.wffweb.tag.html.attribute.CellSpacing;
import com.webfirmframework.wffweb.tag.html.attribute.Charset;
import com.webfirmframework.wffweb.tag.html.attribute.Checked;
import com.webfirmframework.wffweb.tag.html.attribute.CoOrds;
import com.webfirmframework.wffweb.tag.html.attribute.ColSpan;
import com.webfirmframework.wffweb.tag.html.attribute.ColorAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.Cols;
import com.webfirmframework.wffweb.tag.html.attribute.Defer;
import com.webfirmframework.wffweb.tag.html.attribute.DirName;
import com.webfirmframework.wffweb.tag.html.attribute.Disabled;
import com.webfirmframework.wffweb.tag.html.attribute.EncType;
import com.webfirmframework.wffweb.tag.html.attribute.Face;
import com.webfirmframework.wffweb.tag.html.attribute.For;
import com.webfirmframework.wffweb.tag.html.attribute.Headers;
import com.webfirmframework.wffweb.tag.html.attribute.Height;
import com.webfirmframework.wffweb.tag.html.attribute.Href;
import com.webfirmframework.wffweb.tag.html.attribute.HrefLang;
import com.webfirmframework.wffweb.tag.html.attribute.HttpEquiv;
import com.webfirmframework.wffweb.tag.html.attribute.IsMap;
import com.webfirmframework.wffweb.tag.html.attribute.MaxLength;
import com.webfirmframework.wffweb.tag.html.attribute.Method;
import com.webfirmframework.wffweb.tag.html.attribute.MinLength;
import com.webfirmframework.wffweb.tag.html.attribute.Name;
import com.webfirmframework.wffweb.tag.html.attribute.NoHref;
import com.webfirmframework.wffweb.tag.html.attribute.Nonce;
import com.webfirmframework.wffweb.tag.html.attribute.ReadOnly;
import com.webfirmframework.wffweb.tag.html.attribute.Rel;
import com.webfirmframework.wffweb.tag.html.attribute.Rev;
import com.webfirmframework.wffweb.tag.html.attribute.Role;
import com.webfirmframework.wffweb.tag.html.attribute.RowSpan;
import com.webfirmframework.wffweb.tag.html.attribute.Rows;
import com.webfirmframework.wffweb.tag.html.attribute.Scope;
import com.webfirmframework.wffweb.tag.html.attribute.Selected;
import com.webfirmframework.wffweb.tag.html.attribute.Shape;
import com.webfirmframework.wffweb.tag.html.attribute.Size;
import com.webfirmframework.wffweb.tag.html.attribute.Sorted;
import com.webfirmframework.wffweb.tag.html.attribute.Target;
import com.webfirmframework.wffweb.tag.html.attribute.Type;
import com.webfirmframework.wffweb.tag.html.attribute.UseMap;
import com.webfirmframework.wffweb.tag.html.attribute.Value;
import com.webfirmframework.wffweb.tag.html.attribute.Width;
import com.webfirmframework.wffweb.tag.html.attribute.event.animation.AnimationEnd;
import com.webfirmframework.wffweb.tag.html.attribute.event.animation.AnimationIteration;
import com.webfirmframework.wffweb.tag.html.attribute.event.animation.AnimationStart;
import com.webfirmframework.wffweb.tag.html.attribute.event.clipboard.OnCopy;
import com.webfirmframework.wffweb.tag.html.attribute.event.clipboard.OnCut;
import com.webfirmframework.wffweb.tag.html.attribute.event.clipboard.OnPaste;
import com.webfirmframework.wffweb.tag.html.attribute.event.drag.OnDrag;
import com.webfirmframework.wffweb.tag.html.attribute.event.drag.OnDragEnd;
import com.webfirmframework.wffweb.tag.html.attribute.event.drag.OnDragEnter;
import com.webfirmframework.wffweb.tag.html.attribute.event.drag.OnDragLeave;
import com.webfirmframework.wffweb.tag.html.attribute.event.drag.OnDragOver;
import com.webfirmframework.wffweb.tag.html.attribute.event.drag.OnDragStart;
import com.webfirmframework.wffweb.tag.html.attribute.event.drag.OnDrop;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnBlur;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnChange;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnFocus;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnFocusIn;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnFocusOut;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnInput;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnInvalid;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnReset;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnSearch;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnSelect;
import com.webfirmframework.wffweb.tag.html.attribute.event.form.OnSubmit;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnAbort;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnBeforeUnload;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnError;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnHashChange;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnLoad;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnPageHide;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnPageShow;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnResize;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnScroll;
import com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object.OnUnload;
import com.webfirmframework.wffweb.tag.html.attribute.event.keyboard.OnKeyDown;
import com.webfirmframework.wffweb.tag.html.attribute.event.keyboard.OnKeyPress;
import com.webfirmframework.wffweb.tag.html.attribute.event.keyboard.OnKeyUp;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnCanPlay;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnCanPlayThrough;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnDurationChange;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnEmptied;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnEnded;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnLoadStart;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnLoadedData;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnLoadedMetaData;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnPause;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnPlay;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnPlaying;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnProgress;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnRateChange;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnSeeked;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnSeeking;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnStalled;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnSuspend;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnTimeUpdate;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnVolumeChange;
import com.webfirmframework.wffweb.tag.html.attribute.event.media.OnWaiting;
import com.webfirmframework.wffweb.tag.html.attribute.event.misc.OnOffline;
import com.webfirmframework.wffweb.tag.html.attribute.event.misc.OnOnline;
import com.webfirmframework.wffweb.tag.html.attribute.event.misc.OnPopState;
import com.webfirmframework.wffweb.tag.html.attribute.event.misc.OnShow;
import com.webfirmframework.wffweb.tag.html.attribute.event.misc.OnStorage;
import com.webfirmframework.wffweb.tag.html.attribute.event.misc.OnToggle;
import com.webfirmframework.wffweb.tag.html.attribute.event.misc.OnWheel;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnClick;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnContextMenu;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnDblClick;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnMouseDown;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnMouseEnter;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnMouseLeave;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnMouseMove;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnMouseOut;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnMouseOver;
import com.webfirmframework.wffweb.tag.html.attribute.event.mouse.OnMouseUp;
import com.webfirmframework.wffweb.tag.html.attribute.event.print.OnAfterPrint;
import com.webfirmframework.wffweb.tag.html.attribute.event.print.OnBeforePrint;
import com.webfirmframework.wffweb.tag.html.attribute.event.touch.OnTouchCancel;
import com.webfirmframework.wffweb.tag.html.attribute.event.touch.OnTouchEnd;
import com.webfirmframework.wffweb.tag.html.attribute.event.touch.OnTouchMove;
import com.webfirmframework.wffweb.tag.html.attribute.event.touch.OnTouchStart;
import com.webfirmframework.wffweb.tag.html.attribute.event.transition.TransitionEnd;
import com.webfirmframework.wffweb.tag.html.attribute.global.AccessKey;
import com.webfirmframework.wffweb.tag.html.attribute.global.ClassAttribute;
import com.webfirmframework.wffweb.tag.html.attribute.global.Dir;
import com.webfirmframework.wffweb.tag.html.attribute.global.Id;
import com.webfirmframework.wffweb.tag.html.attribute.global.Lang;
import com.webfirmframework.wffweb.tag.html.attribute.global.Style;
import com.webfirmframework.wffweb.tag.html.attribute.global.TabIndex;
import com.webfirmframework.wffweb.tag.html.attribute.global.Title;
import com.webfirmframework.wffweb.tag.html.html5.attribute.AutoComplete;
import com.webfirmframework.wffweb.tag.html.html5.attribute.AutoFocus;
import com.webfirmframework.wffweb.tag.html.html5.attribute.AutoPlay;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Content;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Controls;
import com.webfirmframework.wffweb.tag.html.html5.attribute.DateTime;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Default;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Download;
import com.webfirmframework.wffweb.tag.html.html5.attribute.FormAction;
import com.webfirmframework.wffweb.tag.html.html5.attribute.FormAttribute;
import com.webfirmframework.wffweb.tag.html.html5.attribute.FormEncType;
import com.webfirmframework.wffweb.tag.html.html5.attribute.FormMethod;
import com.webfirmframework.wffweb.tag.html.html5.attribute.FormNoValidate;
import com.webfirmframework.wffweb.tag.html.html5.attribute.FormTarget;
import com.webfirmframework.wffweb.tag.html.html5.attribute.High;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Loop;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Low;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Max;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Media;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Min;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Multiple;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Muted;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Open;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Optimum;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Pattern;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Placeholder;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Poster;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Preload;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Required;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Reversed;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Sandbox;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Sizes;
import com.webfirmframework.wffweb.tag.html.html5.attribute.SrcSet;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Step;
import com.webfirmframework.wffweb.tag.html.html5.attribute.Wrap;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.ContentEditable;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.ContextMenu;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.DataAttribute;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.DataWffId;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.Draggable;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.Dropzone;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.Hidden;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.SpellCheck;
import com.webfirmframework.wffweb.tag.html.html5.attribute.global.Translate;
import com.webfirmframework.wffweb.tag.html.identifier.BooleanAttribute;

public class AttributeRegistry {

    private static final Logger LOGGER = Logger.getLogger(AttributeRegistry.class.getName());

    private static final Set<String> ATTRIBUTE_NAMES_SET;

    private static final Map<String, String> ATTRIBUTE_CLASS_NAME_BY_ATTR_NAME;

    private static final Map<String, Class<?>> ATTRIBUTE_CLASS_BY_ATTR_NAME;

    private static final List<Class<?>> INDEXED_ATTR_CLASSES = new ArrayList<>();

    private static volatile Map<String, Class<?>> attributeClassByAttrNameTmp = new ConcurrentHashMap<>();

    private static final List<String> SORTED_BOOLEAN_ATTR_NAMES;

    private static final List<String> SORTED_EVENT_ATTR_NAMES;

    static {

        final Field[] fields = AttributeNameConstants.class.getFields();
        final int initialCapacity = fields.length;

        Map<String, Class<?>> attributeClassByAttrName = new ConcurrentHashMap<>(initialCapacity);
        ATTRIBUTE_CLASS_NAME_BY_ATTR_NAME = new ConcurrentHashMap<>(initialCapacity);

        attributeClassByAttrName = new ConcurrentHashMap<>(initialCapacity);

        attributeClassByAttrName.put(DataWffId.ATTRIBUTE_NAME, DataWffId.class);

        attributeClassByAttrName.put(AttributeNameConstants.ACCEPT, Accept.class);
        attributeClassByAttrName.put(AttributeNameConstants.ACCESSKEY, AccessKey.class);
        attributeClassByAttrName.put(AttributeNameConstants.ALIGN, Align.class);
        attributeClassByAttrName.put(AttributeNameConstants.ALT, Alt.class);
        attributeClassByAttrName.put(AttributeNameConstants.AUTOCOMPLETE, AutoComplete.class);
        attributeClassByAttrName.put(AttributeNameConstants.AUTOFOCUS, AutoFocus.class);
        attributeClassByAttrName.put(AttributeNameConstants.AUTOPLAY, AutoPlay.class);
        attributeClassByAttrName.put(AttributeNameConstants.CHARSET, Charset.class);
        attributeClassByAttrName.put(AttributeNameConstants.CHECKED, Checked.class);
        attributeClassByAttrName.put(AttributeNameConstants.CLASS, ClassAttribute.class);
        attributeClassByAttrName.put(AttributeNameConstants.COLOR, ColorAttribute.class);
        attributeClassByAttrName.put(AttributeNameConstants.CONTENT, Content.class);
        attributeClassByAttrName.put(AttributeNameConstants.CONTENTEDITABLE, ContentEditable.class);
        attributeClassByAttrName.put(AttributeNameConstants.CONTEXTMENU, ContextMenu.class);
        attributeClassByAttrName.put(AttributeNameConstants.CONTROLS, Controls.class);
        attributeClassByAttrName.put(AttributeNameConstants.COORDS, CoOrds.class);
        attributeClassByAttrName.put(AttributeNameConstants.DATA, DataAttribute.class);
        attributeClassByAttrName.put(AttributeNameConstants.DIR, Dir.class);
        attributeClassByAttrName.put(AttributeNameConstants.DISABLED, Disabled.class);
        attributeClassByAttrName.put(AttributeNameConstants.DOWNLOAD, Download.class);
        attributeClassByAttrName.put(AttributeNameConstants.DRAGGABLE, Draggable.class);
        attributeClassByAttrName.put(AttributeNameConstants.DROPZONE, Dropzone.class);
        attributeClassByAttrName.put(AttributeNameConstants.FACE, Face.class);
        attributeClassByAttrName.put(AttributeNameConstants.FORM, FormAttribute.class);
        attributeClassByAttrName.put(AttributeNameConstants.FORMACTION, FormAction.class);
        attributeClassByAttrName.put(AttributeNameConstants.FORMENCTYPE, FormEncType.class);
        attributeClassByAttrName.put(AttributeNameConstants.FORMMETHOD, FormMethod.class);
        attributeClassByAttrName.put(AttributeNameConstants.FORMNOVALIDATE, FormNoValidate.class);
        attributeClassByAttrName.put(AttributeNameConstants.FORMTARGET, FormTarget.class);
        attributeClassByAttrName.put(AttributeNameConstants.HEIGHT, Height.class);
        attributeClassByAttrName.put(AttributeNameConstants.HIDDEN, Hidden.class);
        attributeClassByAttrName.put(AttributeNameConstants.HREF, Href.class);
        attributeClassByAttrName.put(AttributeNameConstants.HREFLANG, HrefLang.class);
        attributeClassByAttrName.put(AttributeNameConstants.HTTP_EQUIV, HttpEquiv.class);
        attributeClassByAttrName.put(AttributeNameConstants.ID, Id.class);
        attributeClassByAttrName.put(AttributeNameConstants.LANG, Lang.class);
        attributeClassByAttrName.put(AttributeNameConstants.LIST,
                com.webfirmframework.wffweb.tag.html.html5.attribute.List.class);
        attributeClassByAttrName.put(AttributeNameConstants.LOOP, Loop.class);
        attributeClassByAttrName.put(AttributeNameConstants.MAX, Max.class);
        attributeClassByAttrName.put(AttributeNameConstants.MAXLENGTH, MaxLength.class);
        attributeClassByAttrName.put(AttributeNameConstants.MEDIA, Media.class);
        attributeClassByAttrName.put(AttributeNameConstants.METHOD, Method.class);
        attributeClassByAttrName.put(AttributeNameConstants.MIN, Min.class);
        attributeClassByAttrName.put(AttributeNameConstants.MINLENGTH, MinLength.class);
        attributeClassByAttrName.put(AttributeNameConstants.MULTIPLE, Multiple.class);
        attributeClassByAttrName.put(AttributeNameConstants.MUTED, Muted.class);
        attributeClassByAttrName.put(AttributeNameConstants.NAME, Name.class);
        attributeClassByAttrName.put(AttributeNameConstants.NOHREF, NoHref.class);
        attributeClassByAttrName.put(AttributeNameConstants.PATTERN, Pattern.class);
        attributeClassByAttrName.put(AttributeNameConstants.PLACEHOLDER, Placeholder.class);
        attributeClassByAttrName.put(AttributeNameConstants.PRELOAD, Preload.class);
        attributeClassByAttrName.put(AttributeNameConstants.READONLY, ReadOnly.class);
        attributeClassByAttrName.put(AttributeNameConstants.REL, Rel.class);
        attributeClassByAttrName.put(AttributeNameConstants.REQUIRED, Required.class);
        attributeClassByAttrName.put(AttributeNameConstants.REV, Rev.class);
        attributeClassByAttrName.put(AttributeNameConstants.SHAPE, Shape.class);
        attributeClassByAttrName.put(AttributeNameConstants.SIZE, Size.class);
        attributeClassByAttrName.put(AttributeNameConstants.SPELLCHECK, SpellCheck.class);
        attributeClassByAttrName.put(AttributeNameConstants.SRC,
                com.webfirmframework.wffweb.tag.html.attribute.Src.class);
        attributeClassByAttrName.put(AttributeNameConstants.STEP, Step.class);
        attributeClassByAttrName.put(AttributeNameConstants.STYLE, Style.class);
        attributeClassByAttrName.put(AttributeNameConstants.TABINDEX, TabIndex.class);
        attributeClassByAttrName.put(AttributeNameConstants.TARGET, Target.class);
        attributeClassByAttrName.put(AttributeNameConstants.TITLE, Title.class);
        attributeClassByAttrName.put(AttributeNameConstants.TRANSLATE, Translate.class);
        attributeClassByAttrName.put(AttributeNameConstants.TYPE, Type.class);
        attributeClassByAttrName.put(AttributeNameConstants.VALUE, Value.class);
        attributeClassByAttrName.put(AttributeNameConstants.WIDTH, Width.class);
        attributeClassByAttrName.put(AttributeNameConstants.COLSPAN, ColSpan.class);
        attributeClassByAttrName.put(AttributeNameConstants.ROWSPAN, RowSpan.class);
        attributeClassByAttrName.put(AttributeNameConstants.HEADERS, Headers.class);
        attributeClassByAttrName.put(AttributeNameConstants.SCOPE, Scope.class);
        attributeClassByAttrName.put(AttributeNameConstants.SORTED, Sorted.class);
        attributeClassByAttrName.put(AttributeNameConstants.ENCTYPE, EncType.class);
        attributeClassByAttrName.put(AttributeNameConstants.ACTION, Action.class);
        attributeClassByAttrName.put(AttributeNameConstants.BORDER, Border.class);
        attributeClassByAttrName.put(AttributeNameConstants.CELLPADDING, CellPadding.class);
        attributeClassByAttrName.put(AttributeNameConstants.CELLSPACING, CellSpacing.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONCLICK, OnClick.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONCONTEXTMENU, OnContextMenu.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONDBLCLICK, OnDblClick.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONMOUSEDOWN, OnMouseDown.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONMOUSEENTER, OnMouseEnter.class);

        attributeClassByAttrName.put(AttributeNameConstants.ONMOUSELEAVE, OnMouseLeave.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONMOUSEMOVE, OnMouseMove.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONMOUSEOUT, OnMouseOut.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONMOUSEUP, OnMouseUp.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONKEYDOWN, OnKeyDown.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONKEYPRESS, OnKeyPress.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONKEYUP, OnKeyUp.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONABORT, OnAbort.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONBEFOREUNLOAD, OnBeforeUnload.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONERROR, OnError.class);

        attributeClassByAttrName.put(AttributeNameConstants.ONHASHCHANGE, OnHashChange.class);

        attributeClassByAttrName.put(AttributeNameConstants.ONLOAD, OnLoad.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONPAGESHOW, OnPageShow.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONPAGEHIDE, OnPageHide.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONSCROLL, OnScroll.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONUNLOAD, OnUnload.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONBLUR, OnBlur.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONCHANGE, OnChange.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONFOCUS, OnFocus.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONFOCUSIN, OnFocusIn.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONFOCUSOUT, OnFocusOut.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONINPUT, OnInput.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONINVALID, OnInvalid.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONRESET, OnReset.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONSEARCH, OnSearch.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONSELECT, OnSelect.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONSUBMIT, OnSubmit.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONDRAG, OnDrag.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONDRAGEND, OnDragEnd.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONDRAGENTER, OnDragEnter.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONDRAGLEAVE, OnDragLeave.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONDRAGOVER, OnDragOver.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONDRAGSTART, OnDragStart.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONDROP, OnDrop.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONCOPY, OnCopy.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONCUT, OnCut.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONPASTE, OnPaste.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONAFTERPRINT, OnAfterPrint.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONBEFOREPRINT, OnBeforePrint.class);

        attributeClassByAttrName.put(AttributeNameConstants.ONCANPLAY, OnCanPlay.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONCANPLAYTHROUGH, OnCanPlayThrough.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONDURATIONCHANGE, OnDurationChange.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONEMPTIED, OnEmptied.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONENDED, OnEnded.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONLOADEDDATA, OnLoadedData.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONLOADEDMETADATA, OnLoadedMetaData.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONLOADSTART, OnLoadStart.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONPAUSE, OnPause.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONPLAY, OnPlay.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONPLAYING, OnPlaying.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONPROGRESS, OnProgress.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONRATECHANGE, OnRateChange.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONSEEKED, OnSeeked.class);

        attributeClassByAttrName.put(AttributeNameConstants.ONSEEKING, OnSeeking.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONSTALLED, OnStalled.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONSUSPEND, OnSuspend.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONTIMEUPDATE, OnTimeUpdate.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONVOLUMECHANGE, OnVolumeChange.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONWAITING, OnWaiting.class);
        attributeClassByAttrName.put(AttributeNameConstants.ANIMATIONEND, AnimationEnd.class);
        attributeClassByAttrName.put(AttributeNameConstants.ANIMATIONITERATION, AnimationIteration.class);
        attributeClassByAttrName.put(AttributeNameConstants.ANIMATIONSTART, AnimationStart.class);
        attributeClassByAttrName.put(AttributeNameConstants.TRANSITIONEND, TransitionEnd.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONOFFLINE, OnOffline.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONONLINE, OnOnline.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONPOPSTATE, OnPopState.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONSHOW, OnShow.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONSTORAGE, OnStorage.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONTOGGLE, OnToggle.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONWHEEL, OnWheel.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONTOUCHCANCEL, OnTouchCancel.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONTOUCHEND, OnTouchEnd.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONTOUCHMOVE, OnTouchMove.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONTOUCHSTART, OnTouchStart.class);
        attributeClassByAttrName.put(AttributeNameConstants.ROLE, Role.class);
        attributeClassByAttrName.put(AttributeNameConstants.SRCSET, SrcSet.class);
        attributeClassByAttrName.put(AttributeNameConstants.SIZES, Sizes.class);

        attributeClassByAttrName.put(AttributeNameConstants.COLS, Cols.class);
        attributeClassByAttrName.put(AttributeNameConstants.ROWS, Rows.class);
        attributeClassByAttrName.put(AttributeNameConstants.FOR, For.class);
        attributeClassByAttrName.put(AttributeNameConstants.SELECTED, Selected.class);
        attributeClassByAttrName.put(AttributeNameConstants.ACCEPT_CHARSET, AcceptCharset.class);
        attributeClassByAttrName.put(AttributeNameConstants.ASYNC, Async.class);
        attributeClassByAttrName.put(AttributeNameConstants.DATETIME, DateTime.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONMOUSEOVER, OnMouseOver.class);
        attributeClassByAttrName.put(AttributeNameConstants.ONRESIZE, OnResize.class);
        attributeClassByAttrName.put(AttributeNameConstants.OPEN, Open.class);
        attributeClassByAttrName.put(AttributeNameConstants.OPTIMUM, Optimum.class);
        attributeClassByAttrName.put(AttributeNameConstants.DEFER, Defer.class);
        attributeClassByAttrName.put(AttributeNameConstants.DEFAULT, Default.class);
        attributeClassByAttrName.put(AttributeNameConstants.DIRNAME, DirName.class);
        attributeClassByAttrName.put(AttributeNameConstants.HIGH, High.class);
        attributeClassByAttrName.put(AttributeNameConstants.LOW, Low.class);
        attributeClassByAttrName.put(AttributeNameConstants.REVERSED, Reversed.class);
        attributeClassByAttrName.put(AttributeNameConstants.POSTER, Poster.class);
        attributeClassByAttrName.put(AttributeNameConstants.ISMAP, IsMap.class);
        attributeClassByAttrName.put(AttributeNameConstants.SANDBOX, Sandbox.class);
        attributeClassByAttrName.put(AttributeNameConstants.WRAP, Wrap.class);
        attributeClassByAttrName.put(AttributeNameConstants.USEMAP, UseMap.class);
        attributeClassByAttrName.put(AttributeNameConstants.NONCE, Nonce.class);

        ATTRIBUTE_CLASS_BY_ATTR_NAME = Map.copyOf(attributeClassByAttrName);

        attributeClassByAttrNameTmp.putAll(attributeClassByAttrName);

        final List<String> tmpSortedBooleanAttrNames = new ArrayList<>(8);

        for (final Entry<String, Class<?>> entry : attributeClassByAttrName.entrySet()) {
            final Class<?> attrClass = entry.getValue();
            final String attrName = entry.getKey();
            ATTRIBUTE_CLASS_NAME_BY_ATTR_NAME.put(attrName, attrClass.getSimpleName());

            if (BooleanAttribute.class.isAssignableFrom(attrClass)) {
                tmpSortedBooleanAttrNames.add(attrName);
            }

        }

        // sorting in ascending order of length is the first priority
        // then needs to sort by ascending order of name otherwise
        // in some machines multiple,selected comes as selected,multiple
        tmpSortedBooleanAttrNames.sort(Comparator.comparingInt(String::length).thenComparing(String::compareTo));

        SORTED_BOOLEAN_ATTR_NAMES = List.copyOf(tmpSortedBooleanAttrNames);

        final List<String> tmpSortedEventAttrNames = new ArrayList<>(8);

        ATTRIBUTE_NAMES_SET = new HashSet<>(initialCapacity);

        ATTRIBUTE_NAMES_SET.addAll(ATTRIBUTE_CLASS_NAME_BY_ATTR_NAME.keySet());

        for (final Field field : fields) {
            try {
                final String tagName = field.get(null).toString();
                ATTRIBUTE_NAMES_SET.add(tagName);
            } catch (final Exception e) {
                if (LOGGER.isLoggable(Level.SEVERE)) {
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        }

        int index = 0;
        for (final String attrName : IndexedAttributeName.INSTANCE.sortedAttrNames()) {
            final Class<?> attrClass = ATTRIBUTE_CLASS_BY_ATTR_NAME.get(attrName);
            INDEXED_ATTR_CLASSES.add(index, attrClass);

            index++;
        }

        // already sorted by length in ascending order and then by name in
        // ascending order in
        // PreIndexedAttributeName
        for (final PreIndexedAttributeName each : PreIndexedAttributeName.allEventAttributes()) {
            tmpSortedEventAttrNames.add(each.attrName());
        }

        SORTED_EVENT_ATTR_NAMES = List.copyOf(tmpSortedEventAttrNames);
    }

    /**
     * @param attrNames the attribute names to register, eg:-
     *                  AttributeRegistry.register("attri-name1", "attri-name2")
     * @author WFF
     * @since 1.1.3
     */
    public static void register(final String... attrNames) {

        final Set<String> tagNamesWithoutDuplicates = new HashSet<>(attrNames.length);

        Collections.addAll(tagNamesWithoutDuplicates, attrNames);

        ATTRIBUTE_NAMES_SET.addAll(tagNamesWithoutDuplicates);

        IndexedAttributeName.INSTANCE.sortedAttrNames().clear();
        IndexedAttributeName.INSTANCE.sortedAttrNames().addAll(ATTRIBUTE_NAMES_SET);

        IndexedAttributeName.INSTANCE.sortedAttrNames()
                .sort(Comparator.comparingInt(String::length).thenComparing(String::compareTo));
    }

    /**
     * @return the list of attribute names sorted in the ascending order of its
     *         length
     * @author WFF
     * @since 1.1.3
     * @since 12.0.0-beta.7 immutable list
     */
    public static List<String> getAttributeNames() {
        return List.copyOf(IndexedAttributeName.INSTANCE.sortedAttrNames());
    }

    /**
     * @return the list of boolean attribute names sorted in the ascending order of
     *         its length
     * @since 3.0.10
     */
    public static List<String> getBooleanAttributeNames() {
        return SORTED_BOOLEAN_ATTR_NAMES;
    }

    /**
     * @return only the list of event attribute names sorted in the ascending order
     *         of its length
     * @since 3.0.15
     */
    public static List<String> getEventAttributeNames() {
        return SORTED_EVENT_ATTR_NAMES;
    }

    /**
     * @param index the index got by
     *              {@link PreIndexedAttributeName#eventAttrIndex()}
     * @return the attribute name of event attribute at event attribute index
     * @since 3.0.15
     */
    public static String getAttrNameByEventAttrIndex(final int index) {
        return PreIndexedAttributeName.forEventAttrIndex(index).attrName();
    }

    /**
     * @param attributeName
     * @return the index of attribute name
     * @since 3.0.3
     */
    public static Integer getIndexByAttributeName(final String attributeName) {
        return IndexedAttributeName.INSTANCE.getIndexByAttributeName(attributeName);
    }

    /**
     * @return a map containing attribute name as key and value as tag class name
     *         without package name
     * @author WFF
     * @since 1.0.0
     */
    public static Map<String, String> getAttributeClassNameByAttributeName() {
        return ATTRIBUTE_CLASS_NAME_BY_ATTR_NAME;
    }

    /**
     * @return
     * @since 3.0.2
     */
    static Map<String, Class<?>> getAttributeClassByAttrName() {
        return ATTRIBUTE_CLASS_BY_ATTR_NAME;
    }

    /**
     * Loads all attribute classes.
     *
     * @author WFF
     * @since 2.1.13
     */
    public static void loadAllAttributeClasses() {

        final Map<String, Class<?>> attributeClassByAttrNameTmpLocal = attributeClassByAttrNameTmp;
        if (attributeClassByAttrNameTmpLocal != null) {
            final Map<String, Class<?>> unloadedClasses = new HashMap<>();

            for (final Entry<String, Class<?>> entry : attributeClassByAttrNameTmpLocal.entrySet()) {
                try {

                    Class.forName(entry.getValue().getName());

                } catch (final ClassNotFoundException e) {
                    unloadedClasses.put(entry.getKey(), entry.getValue());
                    if (LOGGER.isLoggable(Level.WARNING)) {
                        LOGGER.warning("Could not load attribute class " + entry.getValue().getName());
                    }

                }
            }
            attributeClassByAttrNameTmpLocal.clear();
            if (!unloadedClasses.isEmpty()) {
                attributeClassByAttrNameTmpLocal.putAll(unloadedClasses);
            } else {
                attributeClassByAttrNameTmp = null;
            }
        }

    }

    /**
     * @param attributeName
     * @return
     * @throws InvalidValueException
     * @since 3.0.2
     */
    public static AbstractAttribute getNewAttributeInstance(final String attributeName) {
        return getNewAttributeInstance(attributeName, null);
    }

    /**
     * @param attributeName
     * @param attributeValue
     * @return
     * @throws InvalidValueException
     * @since 3.0.2
     */
    public static AbstractAttribute getNewAttributeInstance(final String attributeName, final String attributeValue) {

        final Class<?> attrClass = ATTRIBUTE_CLASS_BY_ATTR_NAME.get(attributeName);

        if (attrClass == null) {
            return null;
        }

        try {

            if (attributeValue == null) {
                final AbstractAttribute newInstance = (AbstractAttribute) attrClass.getConstructor().newInstance();
                return newInstance;
            }

            final AbstractAttribute newInstance = (AbstractAttribute) attrClass.getConstructor(String.class)
                    .newInstance(attributeValue);

            return newInstance;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new InvalidValueException(
                    "The given attributeValue " + attributeValue + " is invalid for " + attributeName);
        }

    }

    /**
     * @param attributeName
     * @param attributeValue
     * @return new instance or null if failed
     * @since 3.0.2
     */
    public static AbstractAttribute getNewAttributeInstanceOrNullIfFailed(final String attributeName,
            final String attributeValue) {

        final Class<?> attrClass = ATTRIBUTE_CLASS_BY_ATTR_NAME.get(attributeName);

        if (attrClass == null) {
            return null;
        }

        try {

            if (attributeValue == null) {
                final AbstractAttribute newInstance = (AbstractAttribute) attrClass.getConstructor().newInstance();
                return newInstance;
            }

            final AbstractAttribute newInstance = (AbstractAttribute) attrClass.getConstructor(String.class)
                    .newInstance(attributeValue);

            return newInstance;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            // NOP
        }

        return null;
    }

    /**
     * @param attributeNameIndex index
     * @param attributeValue
     * @return new instance or null if failed
     * @since 3.0.3
     */
    public static AbstractAttribute getNewAttributeInstanceOrNullIfFailed(final int attributeNameIndex,
            final String attributeValue) {

        final Class<?> attrClass = INDEXED_ATTR_CLASSES.get(attributeNameIndex);

        if (attrClass == null) {
            return null;
        }

        try {

            if (attributeValue == null) {
                final AbstractAttribute newInstance = (AbstractAttribute) attrClass.getConstructor().newInstance();
                return newInstance;
            }

            final AbstractAttribute newInstance = (AbstractAttribute) attrClass.getConstructor(String.class)
                    .newInstance(attributeValue);

            return newInstance;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            // NOP
        }

        return null;
    }

    // only for testing purpose
    static void test() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, InvalidValueException {
        final Map<String, Class<?>> attributeClassByAttrNameTmpLocal = attributeClassByAttrNameTmp;
        for (final Entry<String, Class<?>> each : attributeClassByAttrNameTmpLocal.entrySet()) {
            final String expectedAttrName = each.getKey();
            final Class<?> attrClass = each.getValue();

            AbstractAttribute newInstance = null;
            try {
                newInstance = (AbstractAttribute) attrClass.getConstructor().newInstance();
            } catch (final Exception e) {
                try {
                    final Object[] initargs = { null };
                    newInstance = (AbstractAttribute) attrClass.getConstructor(String.class).newInstance(initargs);
                } catch (final InvocationTargetException e1) {
                    try {
                        newInstance = (AbstractAttribute) attrClass.getConstructor(int.class).newInstance(1);
                    } catch (final Exception e2) {
                        newInstance = (AbstractAttribute) attrClass.getConstructor(String.class).newInstance("");
                    }
                }
            }

            final String actualAttrName = newInstance.getAttributeName();
            if (!expectedAttrName.equals(actualAttrName)) {
                throw new InvalidValueException(
                        "expectedAttrName: " + expectedAttrName + " actualAttrName: " + actualAttrName);
            }
        }
    }

    // only for testing purpose
    static void test1() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, InvalidValueException {
        final Map<String, Class<?>> attributeClassByAttrNameTmpLocal = attributeClassByAttrNameTmp;
        for (final Entry<String, Class<?>> each : attributeClassByAttrNameTmpLocal.entrySet()) {
            String expectedHtmlString = each.getKey() + "=";
            final Class<?> attrClass = each.getValue();

            AbstractAttribute newInstance = null;

            if (attrClass.equals(DataAttribute.class)) {
                expectedHtmlString = each.getKey() + "attrName";
                final Object[] initargs = { "attrName" };
                newInstance = (AbstractAttribute) attrClass.getConstructor(String.class).newInstance(initargs);
                final String actualHtmlString = newInstance.toHtmlString();
                if (!expectedHtmlString.equals(actualHtmlString)) {
                    throw new InvalidValueException(
                            "expectedHtmlString: " + expectedHtmlString + " actualHtmlString: " + actualHtmlString);
                }
                continue;
            }

            if (attrClass.equals(Style.class)) {
                expectedHtmlString += "\"color:green;\"";
                final Object[] initargs = { "color:green;" };
                newInstance = (AbstractAttribute) attrClass.getConstructor(String.class).newInstance(initargs);
                final String actualHtmlString = newInstance.toHtmlString();
                if (!expectedHtmlString.equals(actualHtmlString)) {
                    throw new InvalidValueException(
                            "expectedHtmlString: " + expectedHtmlString + " actualHtmlString: " + actualHtmlString);
                }
                continue;
            }

            if (BooleanAttribute.class.isAssignableFrom(attrClass)) {
                final String expectedAttrValue = "\"" + attrClass.getSimpleName().toLowerCase() + "\"";
                final Object[] initargs = { attrClass.getSimpleName().toLowerCase() };
                newInstance = (AbstractAttribute) attrClass.getConstructor(String.class).newInstance(initargs);
                final String actualHtmlString = newInstance.toHtmlString();
                final String expectedHtmlStringTmp = expectedHtmlString + expectedAttrValue;
                if ((expectedHtmlStringTmp).equals(actualHtmlString)) {
                    expectedHtmlString = expectedHtmlStringTmp;
                    continue;
                } else {
                    throw new InvalidValueException(
                            "expectedHtmlString: " + expectedHtmlStringTmp + " actualHtmlString: " + actualHtmlString);
                }
            }

            try {
                final Object[] initargs = { "1" };
                newInstance = (AbstractAttribute) attrClass.getConstructor(String.class).newInstance(initargs);
                expectedHtmlString += "\"1\"";
            } catch (final InvocationTargetException e) {

                try {
                    final Object[] initargs = { "true" };
                    newInstance = (AbstractAttribute) attrClass.getConstructor(String.class).newInstance(initargs);
                    expectedHtmlString += "\"true\"";
                } catch (final Exception e1) {
                    try {
                        final Object[] initargs = { "yes" };
                        newInstance = (AbstractAttribute) attrClass.getConstructor(String.class).newInstance(initargs);
                        expectedHtmlString += "\"yes\"";
                    } catch (final Exception e2) {
                        throw new InvalidValueException("could not pass string true as arg for " + attrClass.getName());
                    }
                }

            }

            final String actualHtmlString = newInstance.toHtmlString();
            if (!expectedHtmlString.equals(actualHtmlString)) {
                throw new InvalidValueException(
                        "expectedHtmlString: " + expectedHtmlString + " actualHtmlString: " + actualHtmlString);
            }
        }
    }

}
