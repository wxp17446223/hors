/*v2.1.2*/!(function(a){a.fn.slide= function(b){a.fn.slide.defaults={type:"slide",effect:"fade",autoPlay:false,delayTime:500,interTime:2500, triggerTime: 150, defaultIndex: 0, titCell: ".hd li", mainCell: ".bd", targetCell: null, trigger: "mouseover", scroll: 1, vis: 1, titOnClassName: "on", autoPage: false, prevCell: ".prev", nextCell: ".next", pageStateCell: ".pageState", opp: false, pnLoop: true, easing: "swing", startFun: null, endFun: null, switchLoad: null, playStateCell: ".playState", mouseOverStop: true, defaultPlay: true, returnDefault: false }; return this.each(function () { var I = a.extend({}, a.fn.slide.defaults, b); var g = a(this); var af = I.effect; var o = a(I.prevCell, g); var H = a(I.nextCell, g); var G = a(I.pageStateCell, g); var N = a(I.playStateCell, g); var B = a(I.titCell, g); var m = B.size(); var aa = a(I.mainCell, g); var e = aa.children().size(); var h = I.switchLoad; var ai = a(I.targetCell, g); var K = parseInt(I.defaultIndex); var z = parseInt(I.delayTime); var p = parseInt(I.interTime); var aq = parseInt(I.triggerTime); var ac = parseInt(I.scroll); var ab = (I.autoPlay == "false" || I.autoPlay == false) ? false : true; var C = (I.opp == "false" || I.opp == false) ? false : true; var s = (I.autoPage == "false" || I.autoPage == false) ? false : true; var P = (I.pnLoop == "false" || I.pnLoop == false) ? false : true; var ae = (I.mouseOverStop == "false" || I.mouseOverStop == false) ? false : true; var x = (I.defaultPlay == "false" || I.defaultPlay == false) ? false : true; var Q = (I.returnDefault == "false" || I.returnDefault == false) ? false : true; var ag = isNaN(I.vis) ? 1 : parseInt(I.vis); var an = ! -[1, ] && !window.XMLHttpRequest; var J = 0; var F = 0; var q = 0; var E = 0; var R = I.easing; var ad = null; var M = null; var W = null; var ap = I.titOnClassName; var D = B.index(g.find("." + ap)); var ao = K = D == -1 ? K : D; var f = K; var am = K; var r = e >= ag ? (e % ac != 0 ? e % ac : ac) : 0; var V; var U = af == "leftMarquee" || af == "topMarquee" ? true : false; var al = function () { if (a.isFunction(I.startFun)) { I.startFun(K, m, g, a(I.titCell, g), aa, ai, o, H) } }; var j = function () { if (a.isFunction(I.endFun)) { I.endFun(K, m, g, a(I.titCell, g), aa, ai, o, H) } }; var c = function () { B.removeClass(ap); if (x) { B.eq(f).addClass(ap) } }; if (I.type == "menu") { if (x) { B.removeClass(ap).eq(K).addClass(ap) } B.hover(function () { V = a(this).find(I.targetCell); var i = B.index(a(this)); M = setTimeout(function () { K = i; B.removeClass(ap).eq(K).addClass(ap); al(); switch (af) { case "fade": V.stop(true, true).animate({ opacity: "show" }, z, R, j); break; case "slideDown": V.stop(true, true).animate({ height: "show" }, z, R, j); break } }, I.triggerTime) }, function () { clearTimeout(M); switch (af) { case "fade": V.animate({ opacity: "hide" }, z, R); break; case "slideDown": V.animate({ height: "hide" }, z, R); break } }); if (Q) { g.hover(function () { clearTimeout(W) }, function () { W = setTimeout(c, z) }) } return } if (m == 0) { m = e } if (U) { m = 2 } if (s) { if (e >= ag) { if (af == "leftLoop" || af == "topLoop") { m = e % ac != 0 ? (e / ac ^ 0) + 1 : e / ac } else { var S = e - ag; m = 1 + parseInt(S % ac != 0 ? (S / ac + 1) : (S / ac)); if (m <= 0) { m = 1 } } } else { m = 1 } B.html(""); var L = ""; if (I.autoPage == true || I.autoPage == "true") { for (var ak = 0; ak < m; ak++) { L += "<li>" + (ak + 1) + "</li>" } } else { for (var ak = 0; ak < m; ak++) { L += I.autoPage.replace("$", (ak + 1)) } } B.html(L); var B = B.children() } if (e >= ag) { aa.children().each(function () { if (a(this).width() > q) { q = a(this).width(); F = a(this).outerWidth(true) } if (a(this).height() > E) { E = a(this).height(); J = a(this).outerHeight(true) } }); var y = aa.children(); var w = function () { for (var ar = 0; ar < ag; ar++) { y.eq(ar).clone().addClass("clone").appendTo(aa) } for (var ar = 0; ar < r; ar++) { y.eq(e - ar - 1).clone().addClass("clone").prependTo(aa) } }; switch (af) { case "fold": aa.css({ "position": "relative", "width": F, "height": J }).children().css({ "position": "absolute", "width": q, "left": 0, "top": 0, "display": "none" }); break; case "top": aa.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:' + ag * J + 'px"></div>').css({ "top": -(K * ac) * J, "position": "relative", "padding": "0", "margin": "0" }).children().css({ "height": E }); break; case "left": aa.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:' + ag * F + 'px"></div>').css({ "width": e * F, "left": -(K * ac) * F, "position": "relative", "overflow": "hidden", "padding": "0", "margin": "0" }).children().css({ "float": "left", "width": q }); break; case "leftLoop": case "leftMarquee": w(); aa.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:' + ag * F + 'px"></div>').css({ "width": (e + ag + r) * F, "position": "relative", "overflow": "hidden", "padding": "0", "margin": "0", "left": -(r + K * ac) * F }).children().css({ "float": "left", "width": q }); break; case "topLoop": case "topMarquee": w(); aa.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:' + ag * J + 'px"></div>').css({ "height": (e + ag + r) * J, "position": "relative", "padding": "0", "margin": "0", "top": -(r + K * ac) * J }).children().css({ "height": E }); break } } var Z = function (ar) { var i = ar * ac; if (ar == m) { i = e } else { if (ar == -1 && e % ac != 0) { i = -e % ac } } return i }; var d = function (aw) { var av = function (ay) { for (var ax = ay; ax < (ag + ay); ax++) { aw.eq(ax).find("img[" + h + "]").each(function () { var aB = a(this); aB.attr("src", aB.attr(h)).removeAttr(h); if (aa.find(".clone")[0]) { var aA = aa.children(); for (var az = 0; az < aA.size(); az++) { aA.eq(az).find("img[" + h + "]").each(function () { if (a(this).attr(h) == aB.attr("src")) { a(this).attr("src", a(this).attr(h)).removeAttr(h) } }) } } }) } }; switch (af) { case "fade": case "fold": case "top": case "left": case "slideDown": av(K * ac); break; case "leftLoop": case "topLoop": av(r + Z(am)); break; case "leftMarquee": case "topMarquee": var at = af == "leftMarquee" ? aa.css("left").replace("px", "") : aa.css("top").replace("px", ""); var ar = af == "leftMarquee" ? F : J; var i = r; if (at % ar != 0) { var au = Math.abs(at / ar ^ 0); if (K == 1) { i = r + au } else { i = r + au - 1 } } av(i); break } }; var O = function (au) { if (x && ao == K && !au && !U) { return } if (U) { if (K >= 1) { K = 1 } else { if (K <= 0) { K = 0 } } } else { am = K; if (K >= m) { K = 0 } else { if (K < 0) { K = m - 1 } } } al(); if (h != null) { d(aa.children()) } if (ai[0]) { V = ai.eq(K); if (h != null) { d(ai) } if (af == "slideDown") { ai.not(V).stop(true, true).slideUp(z); V.slideDown(z, R, function () { if (!aa[0]) { j() } }) } else { ai.not(V).stop(true, true).hide(); V.animate({ opacity: "show" }, z, function () { if (!aa[0]) { j() } }) } } if (e >= ag) { switch (af) { case "fade": aa.children().stop(true, true).eq(K).animate({ opacity: "show" }, z, R, function () { j() }).siblings().hide(); break; case "fold": aa.children().stop(true, true).eq(K).animate({ opacity: "show" }, z, R, function () { j() }).siblings().animate({ opacity: "hide" }, z, R); break; case "top": aa.stop(true, false).animate({ "top": -K * ac * J }, z, R, function () { j() }); break; case "left": aa.stop(true, false).animate({ "left": -K * ac * F }, z, R, function () { j() }); break; case "leftLoop": var ar = am; aa.stop(true, true).animate({ "left": -(Z(am) + r) * F }, z, R, function () { if (ar <= -1) { aa.css("left", -(r + (m - 1) * ac) * F) } else { if (ar >= m) { aa.css("left", -r * F) } } j() }); break; case "topLoop": var ar = am; aa.stop(true, true).animate({ "top": -(Z(am) + r) * J }, z, R, function () { if (ar <= -1) { aa.css("top", -(r + (m - 1) * ac) * J) } else { if (ar >= m) { aa.css("top", -r * J) } } j() }); break; case "leftMarquee": var at = aa.css("left").replace("px", ""); if (K == 0) { aa.animate({ "left": ++at }, 0, function () { if (aa.css("left").replace("px", "") >= 0) { aa.css("left", -e * F) } }) } else { aa.animate({ "left": --at }, 0, function () { if (aa.css("left").replace("px", "") <= -(e + r) * F) { aa.css("left", -r * F) } }) } break; case "topMarquee": var i = aa.css("top").replace("px", ""); if (K == 0) { aa.animate({ "top": ++i }, 0, function () { if (aa.css("top").replace("px", "") >= 0) { aa.css("top", -e * J) } }) } else { aa.animate({ "top": --i }, 0, function () { if (aa.css("top").replace("px", "") <= -(e + r) * J) { aa.css("top", -r * J) } }) } break } } B.removeClass(ap).eq(K).addClass(ap); ao = K; if (!P) { H.removeClass("nextStop"); o.removeClass("prevStop"); if (K == 0) { o.addClass("prevStop") } if (K == m - 1) { H.addClass("nextStop") } } G.html("<span>" + (K + 1) + "</span>/" + m) }; if (x) { O(true) } if (Q) { g.hover(function () { clearTimeout(W) }, function () { W = setTimeout(function () { K = f; if (x) { O() } else { if (af == "slideDown") { V.slideUp(z, c) } else { V.animate({ opacity: "hide" }, z, c) } } ao = K }, 300) }) } var T = function (i) { ad = setInterval(function () { C ? K-- : K++; O() }, !!i ? i : p) }; var u = function (i) { ad = setInterval(O, !!i ? i : p) }; var A = function () { if (!ae && ab && !N.hasClass("pauseState")) { clearInterval(ad); T() } }; var Y = function () { if (P || K != m - 1) { K++; O(); if (!U) { A() } } }; var v = function () { if (P || K != 0) { K--; O(); if (!U) { A() } } }; var t = function () { clearInterval(ad); U ? u() : T(); N.removeClass("pauseState") }; var aj = function () { clearInterval(ad); N.addClass("pauseState") }; if (ab) { if (U) { C ? K-- : K++; u(); if (ae) { aa.hover(aj, t) } } else { T(); if (ae) { g.hover(aj, t) } } } else { if (U) { C ? K-- : K++ } N.addClass("pauseState") } N.click(function () { N.hasClass("pauseState") ? t() : aj() }); if (I.trigger == "mouseover") { B.hover(function () { var i = B.index(this); M = setTimeout(function () { K = i; O(); A() }, I.triggerTime) }, function () { clearTimeout(M) }) } else { B.click(function () { K = B.index(this); O(); A() }) } if (U) { H.mousedown(Y); o.mousedown(v); if (P) { var n; var k = function () { n = setTimeout(function () { clearInterval(ad); u(p / 10 ^ 0) }, 150) }; var ah = function () { clearTimeout(n); clearInterval(ad); u() }; H.mousedown(k); H.mouseup(ah); o.mousedown(k); o.mouseup(ah) } if (I.trigger == "mouseover") { H.hover(Y, function () { }); o.hover(v, function () { }) } } else { H.click(Y); o.click(v) } if (I.vis == "auto" && ac == 1 && (af == "left" || af == "leftLoop")) { var X; var l = function () { if (an) { aa.width("auto"); aa.children().width("auto") } aa.parent().width("auto"); F = aa.parent().width(); if (an) { aa.parent().width(F) } aa.children().width(F); if (af == "left") { aa.width(F * e); aa.stop(true, false).animate({ "left": -K * F }, 0) } else { aa.width(F * (e + 2)); aa.stop(true, false).animate({ "left": -(K + 1) * F }, 0) } if (!an && (F != aa.parent().width())) { l() } }; a(window).resize(function () { clearTimeout(X); X = setTimeout(l, 100) }); l() } }) } })(KunyiLibrary);;(function($){var selectors=[];var check_binded=false;var check_lock=false;var defaults={interval:250,force_process:false};var $window=$(window);var $prior_appeared;function process(){check_lock=false;for(var index=0;index<selectors.length;index++){var $appeared=$(selectors[index]).filter(function(){return $(this).is(':appeared')});$appeared.trigger('appear',[$appeared]);if($prior_appeared){var $disappeared=$prior_appeared.not($appeared);$disappeared.trigger('disappear',[$disappeared])}$prior_appeared=$appeared}}$.expr[':']['appeared']=function(element){var $element=$(element);if(!$element.is(':visible')){return false}var window_left=$window.scrollLeft();var window_top=$window.scrollTop();var offset=$element.offset();var left=offset.left;var top=offset.top;if(top+$element.height()>=window_top&&top-($element.data('appear-top-offset')||0)<=window_top+$window.height()&&left+$element.width()>=window_left&&left-($element.data('appear-left-offset')||0)<=window_left+$window.width()){return true}else{return false}};$.fn.extend({appear:function(options){var opts=$.extend({},defaults,options||{});var selector=this.selector||this;if(!check_binded){var on_check=function(){if(check_lock){return}check_lock=true;window.setTimeout(process,opts.interval)};$(window).scroll(on_check).resize(on_check);check_binded=true}if(opts.force_process){window.setTimeout(process,opts.interval)}selectors.push(selector);return $(selector)}});$.extend({force_appear:function(){if(check_binded){process();return true};return false}})})(KunyiLibrary);;(function($){$.fn.scrollToFade=function(){$(this).each(function(){$(this).addClass('be-animating-hide')})};$(window).on('scroll',function(){var win_st=$(this).scrollTop();$('.be-animating-hide').each(function(){var offset=$(this).offset().top;var opacity=0.7-((win_st-offset)/$(this).height());$(this).css('opacity',opacity)})})}(KunyiLibrary));;/*1.9.5*/!function(a,b,c,d){var e=a(b);a.fn.lazyload=function(f){function g(){var b=0;i.each(function(){var c=a(this);if(!j.skip_invisible||c.is(":visible"))if(a.abovethetop(this,j)||a.leftofbegin(this,j));else if(a.belowthefold(this,j)||a.rightoffold(this,j)){if(++b>j.failure_limit)return!1}else c.trigger("appear"),b=0})}var h,i=this,j={threshold:0,failure_limit:0,event:"scroll",effect:"show",container:b,data_attribute:"original",skip_invisible:!1,appear:null,load:null,placeholder:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"};return f&&(d!==f.failurelimit&&(f.failure_limit=f.failurelimit,delete f.failurelimit),d!==f.effectspeed&&(f.effect_speed=f.effectspeed,delete f.effectspeed),a.extend(j,f)),h=j.container===d||j.container===b?e:a(j.container),0===j.event.indexOf("scroll")&&h.bind(j.event,function(){return g()}),this.each(function(){var b=this,c=a(b);b.loaded=!1,(c.attr("src")===d||c.attr("src")===!1)&&c.is("img")&&c.attr("src",j.placeholder),c.one("appear",function(){if(!this.loaded){if(j.appear){var d=i.length;j.appear.call(b,d,j)}a("<img />").bind("load",function(){var d=c.attr("data-"+j.data_attribute);c.hide(),c.is("img")?c.attr("src",d):c.css("background-image","url('"+d+"')"),c[j.effect](j.effect_speed),b.loaded=!0;var e=a.grep(i,function(a){return!a.loaded});if(i=a(e),j.load){var f=i.length;j.load.call(b,f,j)}}).attr("src",c.attr("data-"+j.data_attribute))}}),0!==j.event.indexOf("scroll")&&c.bind(j.event,function(){b.loaded||c.trigger("appear")})}),e.bind("resize",function(){g()}),/(?:iphone|ipod|ipad).*os 5/gi.test(navigator.appVersion)&&e.bind("pageshow",function(b){b.originalEvent&&b.originalEvent.persisted&&i.each(function(){a(this).trigger("appear")})}),a(c).ready(function(){g()}),this},a.belowthefold=function(c,f){var g;return g=f.container===d||f.container===b?(b.innerHeight?b.innerHeight:e.height())+e.scrollTop():a(f.container).offset().top+a(f.container).height(),g<=a(c).offset().top-f.threshold},a.rightoffold=function(c,f){var g;return g=f.container===d||f.container===b?e.width()+e.scrollLeft():a(f.container).offset().left+a(f.container).width(),g<=a(c).offset().left-f.threshold},a.abovethetop=function(c,f){var g;return g=f.container===d||f.container===b?e.scrollTop():a(f.container).offset().top,g>=a(c).offset().top+f.threshold+a(c).height()},a.leftofbegin=function(c,f){var g;return g=f.container===d||f.container===b?e.scrollLeft():a(f.container).offset().left,g>=a(c).offset().left+f.threshold+a(c).width()},a.inviewport=function(b,c){return!(a.rightoffold(b,c)||a.leftofbegin(b,c)||a.belowthefold(b,c)||a.abovethetop(b,c))},a.extend(a.expr[":"],{"below-the-fold":function(b){return a.belowthefold(b,{threshold:0})},"above-the-top":function(b){return!a.belowthefold(b,{threshold:0})},"right-of-screen":function(b){return a.rightoffold(b,{threshold:0})},"left-of-screen":function(b){return!a.rightoffold(b,{threshold:0})},"in-viewport":function(b){return a.inviewport(b,{threshold:0})},"above-the-fold":function(b){return!a.belowthefold(b,{threshold:0})},"right-of-fold":function(b){return a.rightoffold(b,{threshold:0})},"left-of-fold":function(b){return!a.rightoffold(b,{threshold:0})}})}(KunyiLibrary,window,document);var Request=new Object();
Kunyi = {
    imgReSize: function (dom) {
        if (!dom) return;
        var img = $(dom);
        var imgWidth = img.width();
        var imgHeight = img.height();
        var resizeWidth, resizeHeight;
        function reSize() {
            var screenWidth = $__W.width();
            var screenHeight = $__W.height();
            if (((imgWidth * screenHeight) / imgHeight) < screenWidth) {
                resizeWidth = screenWidth;
                resizeHeight = (imgHeight * screenWidth) / imgWidth;
            } else if (((imgHeight * screenWidth) / imgWidth) < screenHeight) {
                resizeWidth = (imgWidth * screenHeight) / imgHeight;
                resizeHeight = screenHeight;
            }
            resizeMarginTop = -(resizeHeight / 2);
            resizeMarginLeft = -(resizeWidth / 2);
            img.css({ top: screenHeight / 2, left: screenWidth / 2, width: resizeWidth, height: resizeHeight, marginTop: resizeMarginTop, marginLeft: resizeMarginLeft })
        }
        $__D.ready(reSize);
        $__W.resize(reSize);
    },
    imageResize: function (dom) {
        if (!dom) return;
        var img = $(dom);
        var parent = img.parent();
        var imgWidth = img.width();
        var imgHeight = img.height();
        var resizeWidth, resizeHeight;
        function reSize() {
            var screenWidth = parent.width();
            var screenHeight = parent.height();
            if (((imgWidth * screenHeight) / imgHeight) < screenWidth) {
                resizeWidth = screenWidth;
                resizeHeight = (imgHeight * screenWidth) / imgWidth;
            } else if (((imgHeight * screenWidth) / imgWidth) < screenHeight) {
                resizeWidth = (imgWidth * screenHeight) / imgHeight;
                resizeHeight = screenHeight;
            }
            resizeMarginTop = -(resizeHeight / 2);
            resizeMarginLeft = -(resizeWidth / 2);
            img.css({ top: screenHeight / 2, left: screenWidth / 2, width: resizeWidth, height: resizeHeight, marginTop: resizeMarginTop, marginLeft: resizeMarginLeft })
        }
        $__D.ready(reSize);
        $__W.resize(reSize);
    },
	SetTime: function(obj) {
        if (CountDown == 0) {
            obj.removeAttribute("disabled");
            $(obj).removeClass("disabled");
            obj.value = "免费获取手机验证码";
            CountDown = 180;
            return;
        } else {
            obj.setAttribute("disabled", true);
            $(obj).addClass("disabled");
            obj.value = "重新发送(" + CountDown + ")";
            CountDown--;
        }
        window.setTimeout(function() {
            Kunyi.SetTime(obj)
        }, 1000)
    },
    Msg: {
        ShowTipsMsg: function (msg, time, type) {
            if (type == 1) {
                Kunyi.Msg.MsgTips(time, msg, 518, 'success'); //头部提示,1、success 2、error 3、warning
            } else if (type == 2) {
                Kunyi.Msg.MsgTips(time, msg, 518, 'error'); //头部提示,1、success 2、error 3、warning
            } else if (type == 3) {
                Kunyi.Msg.MsgTips(time, msg, 518, 'warning'); //头部提示,1、success 2、error 3、warning 
            }
        },
        MsgTips: function (timeOut, msg, speed, type) {
            $(".tip_container").remove();
            var bid = parseInt(Math.random() * 100000);
            $("body").prepend('<div id="tip_container' + bid + '" class="container tip_container"><div id="tip' + bid + '" class="mtip"><span id="tsc' + bid + '"></span></div></div>');
            var $this = $(this);
            var $tip_container = $("#tip_container" + bid);
            var $tip = $("#tip" + bid);
            var $tipSpan = $("#tsc" + bid);
            //先清楚定时器
            clearTimeout(window.timer);
            //主体元素绑定事件
            $tip.attr("class", type).addClass("mtip");
            $tipSpan.html(msg);
            $tip_container.slideDown(speed);
            //提示层隐藏定时器
            window.timer = setTimeout(function () {
                $tip_container.slideUp(speed);
                $(".tip_container").remove();
            }, timeOut);
            //鼠标移到提示层时清除定时器
            $tip_container.on("mouseover", function () {
                clearTimeout(window.timer);
            });
            //鼠标移出提示层时启动定时器
            $tip_container.on("mouseout", function () {
                window.timer = setTimeout(function () {
                    $tip_container.slideUp(speed);
                    $(".tip_container").remove();
                }, timeOut / 2);
            });
            $tip_container.css({ "left": ($__W.width() - $tip_container.width()) / 2
		, "top": ($__W.height() - $tip_container.height()) / 2
            });
        }
    },
    cls: function (event) {
        event = event ? event : window.event;
        var obj = event.srcElement ? event.srcElement : event.target;
        with (obj)
            if (value == defaultValue) value = "";
    },
    res: function (event) {
        event = event ? event : window.event;
        var obj = event.srcElement ? event.srcElement : event.target;
        with (obj)
            if (value == "") value = defaultValue;
    }, CheckSearchForm: function () {
        var obj = $("input#SearchWords");
        var str = $.trim(obj.val());
        if (str == "") {
			Kunyi.Msg.ShowTipsMsg('搜索内容不能为空！', '2888', '2');
            obj.focus();
            return false;
        }
        if (str == "请输入关键字") {
            obj.focus();
            return false;
        }
        return true;
    }, AddFavorite: function (sURL, sTitle) {
        try {
            window.external.addFavorite("" + sURL + "", "" + sTitle + "");
        }
        catch (e) {
            try {
                if (window.sidebar) {
                    window.sidebar.addPanel("" + sTitle + "", "" + sURL + "", "");
                } else {
                    alert("加入收藏失败，该浏览器不支持自动加入收藏功能，请使用Ctrl+D进行添加。");
                }
            }
            catch (e) {
                alert("加入收藏失败，该浏览器不支持自动加入收藏功能，请使用Ctrl+D进行添加。");
            }
        }
    }, InitFun: function () {
        $("div#Loading").fadeOut(1288, "easeOutQuart", function () { $(this).remove(); });
        window.setTimeout(function () {
            $("header .toolBase").animate({ top: "0px" }, 1188, "easeOutQuint");
        }, 588);
    }, IsFinish: false, ToTop: function () {
        $("html,body").stop().animate({ scrollTop: 0 }, 888, "easeOutQuint");
    }, QuickMenuBase: $("nav .quickMenuBase"), BaseMark: $(".BaseMark").eq(0), Index: {}
};
$(function(){
	$("img.load").lazyload({
			threshold: 200,
			effect: "fadeIn",
			failurelimit:28
	});
	$.ajax({
		url: "DoPostBack/GetMemberInfo",
		type: "GET",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		beforeSend:function(result){},
		success:function(result){
			$obj=$("#DIVMemberInfo");
			if(result.code==101){
				$obj.empty();
				$obj.append("您好，" + result.userName + "，请&nbsp;<a href=\"login.html\">登录</a>&nbsp;|&nbsp;<a href=\"Register.html\">注册</a>");
			}else if(result.code==102){
				$obj.empty();
				$obj.append("您好，<a href='MemberReg.html'>" + result.userName + "</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;<a href='loginOut.html'>[退出]</a>");
			}
		},
		error: function(result, status) {
		}
	});	
	$("nav .nav .PMenu").hover(function(){
		$t=$(this);
		$obj=$t.find(".navboxBase");
		$obj.css({"left":-$t.offset().left+"px","width":$("body").width()});
		if($IsLoser){
			$obj.show();
		}else{
		$obj.stop(true,false).fadeIn(588,"easeOutQuart");
		}
	},function(){
	    $(this).find('.navboxBase').hide(); 
	});
	 $("header .divType").click(function(){
	 	  $t=$(this);
		  $obj=$("header .searchBase .itemBase ul");
		  if($t.data("state")=="0"){
			  $obj.stop(true,false).fadeIn(function(){
				  $t.data("state","1");
				 });
		  }else{
			  $obj.stop(true,false).fadeOut(function(){
				  $t.data("state","0");
				 });
		  }
	 });
	 $("header .searchBase .itemBase ul li").click(function(){
		     $t=$(this);
			 $obj=$("header .divType");
			 $obj.text($t.text());
			 $("header .searchBase .itemBase ul").stop(true,false).fadeOut(function(){
				  $obj.data("state","0");
			 });
	      	 $("#Type").val($t.data("id"));
	 });
  window.setTimeout(function(){
	  if(!Kunyi.IsFinish){
		  Kunyi.InitFun();
		}
   },2888);
    Kunyi.QuickMenuBase.hover(function(){
	  if(Kunyi.QuickMenuBase.data("type")==0){
		    $("nav .quickMenus").stop(false,true).fadeIn(588,"easeOutQuint");
	  }
   },function(){
	   if(Kunyi.QuickMenuBase.data("type")==0){
		    $("nav .quickMenus").hide();
	  }
   });
   $("#BtnQRMore").click(function(){
	   $base=$("#QRBase");
	   $obj=$base.find(".QRBase");
	   $h=$obj.height();
	   $base.stop().animate({"height":$h+"px"},888,"easeOutQuart");
	   $("#QRMark").stop().animate({"top":"-18px"},1288,"easeInOutQuart");
	   $obj.stop(false,true).delay(288).animate({opacity:1},588,"easeInQuart");
   });
   $("#QRBase .QRClose").click(function(){
	   $base=$("#QRBase");
	   $obj=$base.find(".QRBase");
	   $("#QRMark").stop().animate({"top":"0px"},888,"easeInOutQuart");
	   $obj.stop(false,true).animate({opacity:0},588,"easeOutQuart",function(){
		   $base.stop().animate({"height":"1px"},888,"easeOutQuart");
	   });
	});
	$("#ddlLinks").change(function(e) {
        $t=$(this);
        var obj=$(this).get(0);
		var url=obj.options[obj.selectedIndex].value;
		if(url!="0"){	
		window.open(url);
		}
    });
	$("nav .quickMenus li.first").hover(function(){
		$t=$(this);
		$obj=$t.find(".QMSub");
		if($obj.length>0){
			$obj.css("top",-(12+$t.index()*61)+"px");
			$obj.show();
		}
	},function(){
		$t=$(this);
		$obj=$t.find(".QMSub");
		if($obj.length>0){
			$obj.hide();
		}
	});
	window.setTimeout(function(){		  
    $.getScript("js/Kunyi.Doctors.js",function(){
	   $DListUL=$("nav li.first .QMSubList .DList");
	    $("nav li.first .IndexWords li").click(function(){
		   $DListUL.empty();
		   var strTemp="",i=0;
		   $t=$(this);
		   if(!$t.hasClass("current")){			
			   i=$t.index();
			   $("nav li.first .IndexWords li.current").removeClass("current");			   
			   if(Kunyi.Doctors[i].Doctors.length>0){strTemp+="<ul>";
				   $(Kunyi.Doctors[i].Doctors).each(function(j) {
				strTemp+="<li class='animated'><div class='DName'><a href='Doctor-"+this.DID+"-"+this.OfficeID+".html' target='_blank' class='name'>"+this.DName+"</a></div><p><a href='Office-"+this.OfficeID+".html' target='_blank'>"+this.OfficeName+"</a></p></li>"; });
			   strTemp+="</ul>";
			   }else{
				 strTemp+="<div class='DNull'><span>对不起！</span><br/>没有找到相关医生！</div>";
			   }			
			   $t.addClass("current");
			   strTemp+="<div class='clear'></div>";
			   $DListUL.append(strTemp);
		   }		   
		});
	$("nav li.first .IndexWords li").eq(0).click();
    });
	$("img").error(function (e) {
        $t = $(this);
        var src = $t.attr("src");
        $t.attr("src", src + "?v=" + Math.random())
       // console.clear();
    });
    window.setTimeout(function () {
        $("img.load").each(function (i, e) {
            $t = $(this);
            $C = $t.attr("src");
            $L = $t.data("original");
            if ($C != $L) {
                $t.attr("src", $L);
            }
        });
    }, 5888);
    window.setTimeout(function () {
        $("img.loading").each(function (i, e) {
            $t = $(this);
            $C = $t.attr("src");
            $L = $t.data("original");
            if ($C != $L) {
                $t.attr("src", $L);
            }
        });
    }, 8888);
	$.getScript("js/Kunyi.Offices.js",function(){
		$QMSubList=$("#QMOffices");
		var strTemp="";
	    $(Kunyi.Offices).each(function(i){
		    strTemp+="<div class='item'><h5>"+this.DepName+"</h5>";
		    $(this.Offices).each(function(j){					 
				 if(j==0){
					  strTemp+="<a href='Office-"+this.OID+".html' class='first' target='_blank'>"+this.OName+"</a>";
				 }else{
				 strTemp+="<a href='Office-"+this.OID+".html' target='_blank'>"+this.OName+"</a>";
				 }
			});
			strTemp+="<div class='clear'></div></div>";
		}); 
		$QMSubList.append(strTemp);
		
		if($("#ddlDepartment").length>0){
			$("#ddlDepartment").change(function(e) {
					$t=$(this);
					var obj=$(this).get(0);
					var DepID=obj.options[obj.selectedIndex].value;
					$ddlOffices=$("#ddlOffices");
					if (DepID != "0") {
						$ddlOffices.html("<option value=\"0\">选择科室</option>");
						   for(var js in Kunyi.Offices){
							   if(Kunyi.Offices[js].DepID==DepID){
								   $(Kunyi.Offices[js].Offices).each(function(i){
$ddlOffices.append("<option value=\"" + this.OID + "\">" + this.OName + "</option>");
								   });
							    break;
							   }
						   }
				 } else
					$ddlOffices.html("<option value=\"0\">选择科室</option>");
			});
		}
	});
	},1288);
});
$__W.resize(function(){
   $("#QRMark").css("top","0px");
   $("#QRBase").css("height","1px");
});
$__W.load(function(){
	Kunyi.IsFinish=true;
	Kunyi.InitFun();
	$("#ddlYears").change(function(e) {
		$t=$(this);
        var obj=$(this).get(0);
		var year=obj.options[obj.selectedIndex].value;
		$ddlTimes=$("#ddlTimes");
		if(year!="0"){
			$.ajax({
				type: "post",
				dataType: "json",
				url: "DoPostBack/GetTimesByYear.ashx",
				data: {year:year},
				beforeSend: function() {
				   $ddlTimes.html("<option value=\"0\">选择期数</option>");
				},
				complete:function(){},
				error: function(XMLHttpRequest, textStatus, errorThrown) { 
				},
				success: function(msg){
					for (var i = 0; i < msg.length; i++){
					$ddlTimes.append("<option value=\""+msg[i].times+"\">第"+msg[i].times+"期</option>");             
					}
				}       
			});		
		}
		else{
		$("#ddlTimes").html("<option value=\"0\">选择期数</option>");	
		}
    });
    
	$("#btnGoNewsPaper").click(function(){
		if ($("#ddlTimes").val() != null && $("#ddlTimes").val() != "0") {
		var str = "Newspaper-"+$("#ddlYears").val()+"-"+$("#ddlTimes").val()+".html";
		window.location.href = str;
		}
	});
	$("#AChangeCode").click(function(){
		$("#IMGCheckcode").click();
	});
	$CurrentBase=$("nav").data("index");
	$("nav .nav .PMenu").each(function(i, e) {
		$t=$(this);
        if($t.data("index")==$CurrentBase){
		$t.addClass("current");	
		}
    });

});