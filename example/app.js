var win = Ti.UI.createWindow({
  backgroundColor: '#ddd'
});

var webviewfragment = require('de.marcelpociot.webviewfragment');
Ti.API.info("module is => " + webviewfragment);
var webViewHolder = webviewfragment.createWebview({
	width: Ti.UI.FILL,
	height: Ti.UI.FILL,
	centerWindow: Ti.UI.createWebView({width:Ti.UI.FILL,height:Ti.UI.FILL,url: "http://slides.yearofmoo.com/1-2-animations-presentation/index.html"})
});
win.add( webViewHolder );
win.open();