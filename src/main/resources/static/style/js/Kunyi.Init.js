(function () { document.write("<div style=\"position:fixed; top:0px; left:0px; width:100%; height:100%; z-index:99999; background:#FFF url(images/loading.gif) center center no-repeat;\" id=\"Loading\"></div>"); })();
if ((navigator.userAgent.match(/(iPhone|iPod|Android|ios|iPad|Mobile)/i))) {
    var URL = document.URL.toLowerCase();
    if (URL.indexOf("http:") >= 0) {
        URL = document.URL.toLowerCase().replace(/http/, "https");
    }
    document.location.href = (URL.indexOf("www") > 0 ? URL.replace(/www/, "m") : "https://m.hnsrmyy.net/");
}
 