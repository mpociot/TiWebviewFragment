package de.marcelpociot.webviewfragment;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiRHelper;
import org.appcelerator.titanium.util.TiRHelper.ResourceNotFoundException;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class Webview extends TiUIView
{
	private LinearLayout layout;
	int layout_content_fragment = 0;
	int layout_webview_main = 0;
	int id_content_frame = 0;
	
	private TiViewProxy centerView;
	
	public static final String PROPERTY_CENTER_WINDOW = "centerWindow";
	
	public Webview(TiViewProxy proxy) {
		super(proxy);
		
		try {
			layout_content_fragment = TiRHelper.getResource("layout.webview_fragment");
			layout_webview_main 	= TiRHelper.getResource("layout.webview_main");
			id_content_frame 		= TiRHelper.getResource("id.content_frame");
		}
		catch (ResourceNotFoundException e) {
			Log.e("WEBVIEW", "XML resources could not be found!!!");
		}
		
		LayoutInflater inflater = LayoutInflater.from( proxy.getActivity() );
		layout = (LinearLayout) inflater.inflate(layout_webview_main, null, false);

		// TiUIView
		setNativeView(layout);
	}
	
	public static class ContentWrapperFragment extends Fragment {
		View mContentView;

		public ContentWrapperFragment(View cv) {
			mContentView = cv;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			int layout_content_fragment = 0;
			int id_content_frame = 0;
			try {
				layout_content_fragment = TiRHelper.getResource("layout.webview_fragment");
				id_content_frame = TiRHelper.getResource("id.content_frame");
			}
			catch (ResourceNotFoundException e) {
				Log.e("WEBVIEW", "XML resources could not be found!!!");
			}

			View view = inflater.inflate(layout_content_fragment, container, false);
			FrameLayout v = (FrameLayout) view.findViewById(id_content_frame);
			v.addView(mContentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			return view;
		}
	}
	
	private void replaceCenterView(TiViewProxy viewProxy) {
		if (viewProxy == this.centerView) {
			Log.d("WEBVIEW", "centerView was not changed");
			return;
		}
		if (viewProxy == null) {
			return;
		}

		// update the main content by replacing fragments
		View contentView = viewProxy.getOrCreateView().getOuterView();
		Fragment fragment = new ContentWrapperFragment(contentView);

		FragmentManager fragmentManager = (proxy.getActivity()).getFragmentManager();
		fragmentManager.beginTransaction().replace(id_content_frame, fragment).commit();

		this.centerView = viewProxy;
	}

	@Override
	public void processProperties(KrollDict d)
	{
		if (d.containsKey(PROPERTY_CENTER_WINDOW)) {
			Object centerView = d.get(PROPERTY_CENTER_WINDOW);
			if (centerView != null && centerView instanceof TiViewProxy) {
				replaceCenterView((TiViewProxy)centerView);
			} else {
				Log.e("WEBVIEW", "[ERROR] Invalid type for centerView");
			}
		}
		super.processProperties(d);
	}
}