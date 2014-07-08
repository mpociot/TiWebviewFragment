## Webview Fragment Module

Android 4.4 introduced a new WebView engine "Chromium".

This webview fails to render (in some cases) in Titanium - [See tracked Issue](https://jira.appcelerator.org/browse/TIDOC-1548)

The official workaround is to add a borderRadius to the Webview, so that hardwareAcceleration is disabled. Sadly this also removes the ability to view Youtube videos in the Webview, without making them fullscreen.

This module fixes this issue.