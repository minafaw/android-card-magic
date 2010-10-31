package com.maize.CardMagic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.text.util.Linkify.TransformFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends Activity 
{
	/* UI components */
	private ImageView 	maizeLogoImageView;
	private TextView	opensourceTextView;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        setTitle("About Card Magic");
        
        // Link Maize Labs logo to maizelabs.com
        maizeLogoImageView = (ImageView) findViewById(R.id.logo);
        maizeLogoImageView.setOnClickListener(new OnClickListener() 
        {			
			public void onClick(View v) 
			{
				// Load up the Maize Labs website
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maizelabs.com"));
				startActivity(intent);
			}
		});        
        
        
        // Link the string "Google Code" to project hosting page
        opensourceTextView = (TextView) findViewById(R.id.source_code);
        Pattern matcher = Pattern.compile("\\bGoogle Code\\b");
        String url = "http://code.google.com/p/android-card-magic/";
        Linkify.addLinks(opensourceTextView, matcher, url, null, new TransformFilter() 
        {
        	// A transform filter that simply returns just the text captured by the first regular expression group.
            public final String transformUrl(final Matcher match, String url) 
            {
                return "";
            }
        });
    }
}
