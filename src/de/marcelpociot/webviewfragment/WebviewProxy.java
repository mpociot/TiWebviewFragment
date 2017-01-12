package de.marcelpociot.webviewfragment;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiC;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiRHelper;
import org.appcelerator.titanium.util.TiRHelper.ResourceNotFoundException;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;


// This proxy can be created by calling Webviewfragment.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule=WebviewfragmentModule.class)
public class WebviewProxy extends TiViewProxy
{
	// Standard Debugging variables
	private static final String TAG = "ExampleProxy";

	private Webview view;

	// Constructor
	public WebviewProxy()
	{
		super();
	}

	@Override
	public TiUIView createView(Activity activity)
	{
		view = new Webview(this);
		return view;
	}
	
	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict options)
	{
		super.handleCreationDict(options);
		
		if (options.containsKey("message")) {
			Log.d(TAG, "example created with message: " + options.get("message"));
		}
	}
}