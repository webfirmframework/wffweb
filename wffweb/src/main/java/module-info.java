module com.webfirmframework.wffweb {

    requires java.base;
    requires java.logging;
    

    exports com.webfirmframework.wffweb;
    exports com.webfirmframework.wffweb.clone;
    exports com.webfirmframework.wffweb.concurrent;
    exports com.webfirmframework.wffweb.css;
    exports com.webfirmframework.wffweb.css.core;
    exports com.webfirmframework.wffweb.css.css3;
    exports com.webfirmframework.wffweb.css.file;
    exports com.webfirmframework.wffweb.csswff;
    exports com.webfirmframework.wffweb.data;
    exports com.webfirmframework.wffweb.informer;
    exports com.webfirmframework.wffweb.interpreter;
    exports com.webfirmframework.wffweb.io;
    exports com.webfirmframework.wffweb.js;
    exports com.webfirmframework.wffweb.lang;
    exports com.webfirmframework.wffweb.server.page;
    exports com.webfirmframework.wffweb.server.page.action;
    exports com.webfirmframework.wffweb.settings;
    exports com.webfirmframework.wffweb.streamer;
    exports com.webfirmframework.wffweb.tag.core;
    exports com.webfirmframework.wffweb.tag.html;
    exports com.webfirmframework.wffweb.tag.html.attribute;
    exports com.webfirmframework.wffweb.tag.html.attribute.core;
    exports com.webfirmframework.wffweb.tag.html.attribute.event;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.animation;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.clipboard;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.drag;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.form;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.frame.or.object;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.keyboard;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.media;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.misc;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.mouse;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.print;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.touch;
    exports com.webfirmframework.wffweb.tag.html.attribute.event.transition;
    exports com.webfirmframework.wffweb.tag.html.attribute.global;
    exports com.webfirmframework.wffweb.tag.html.attributewff;
    exports com.webfirmframework.wffweb.tag.html.core;
    exports com.webfirmframework.wffweb.tag.html.formatting;
    exports com.webfirmframework.wffweb.tag.html.formsandinputs;
    exports com.webfirmframework.wffweb.tag.html.frames;
    exports com.webfirmframework.wffweb.tag.html.html5;
    exports com.webfirmframework.wffweb.tag.html.html5.attribute;
    exports com.webfirmframework.wffweb.tag.html.html5.attribute.global;
    exports com.webfirmframework.wffweb.tag.html.html5.audiovideo;
    exports com.webfirmframework.wffweb.tag.html.html5.formatting;
    exports com.webfirmframework.wffweb.tag.html.html5.formsandinputs;
    exports com.webfirmframework.wffweb.tag.html.html5.identifier;
    exports com.webfirmframework.wffweb.tag.html.html5.images;
    exports com.webfirmframework.wffweb.tag.html.html5.links;
    exports com.webfirmframework.wffweb.tag.html.html5.lists;
    exports com.webfirmframework.wffweb.tag.html.html5.programming;
    exports com.webfirmframework.wffweb.tag.html.html5.stylesandsemantics;
    exports com.webfirmframework.wffweb.tag.html.identifier;
    exports com.webfirmframework.wffweb.tag.html.images;
    exports com.webfirmframework.wffweb.tag.html.links;
    exports com.webfirmframework.wffweb.tag.html.lists;
    exports com.webfirmframework.wffweb.tag.html.metainfo;
    exports com.webfirmframework.wffweb.tag.html.model;
    exports com.webfirmframework.wffweb.tag.html.programming;
    exports com.webfirmframework.wffweb.tag.html.stylesandsemantics;
    exports com.webfirmframework.wffweb.tag.html.tables;
    exports com.webfirmframework.wffweb.tag.htmlwff;
    exports com.webfirmframework.wffweb.tag.repository;
    exports com.webfirmframework.wffweb.util;
    exports com.webfirmframework.wffweb.util.data;
    exports com.webfirmframework.wffweb.view;
    exports com.webfirmframework.wffweb.wffbm.data;
    exports com.webfirmframework.wffweb.tag.html.attribute.listener;
  

}