var sjdurl = "lol";
var theId = "temp";
var getCookies = function () {
    var pairs = document.cookie.split(";");
    var cookies = {};
    for (var i = 0; i < pairs.length; i++) {
        var pair = pairs[i].split("=");
        cookies[pair[0]] = unescape(pair[2]);
        theId = unescape(pair[2]);
    }
    return cookies;
}

function openlink() {
    sjdurl = "http://localhost:8080/TalesEstateServer/resources/MobileIdWrapper/getnames/"" + theId;

    document.getElementById('myframe').src = sjdurl;

}

window.onload = function () {
    getCookies();
    openlink();
};