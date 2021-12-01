package com.example.streamer;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class introductoryContactPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory_contact_page);

        Element adsElement = new Element();
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.meeting)
                .setDescription(" O2 Academia \n We Take Utmost Care To Give Attention To Individual Students Along With Imparting The Right Knowledge.\n Take A Quick Ride Of O2 Academia, Not Just Distributing Study Materials, The Institute Offers Demo Sessions To Get A Flair Of What Actually Happens On The Insides. ")

                .addGroup("CONNECT WITH US!")
                .addEmail("contact@o2academia.org")
                .addWebsite("www.o2academia.org/")
                .addFacebook("O2Academic")
                .addTwitter("o2_feel")
                .addYoutube("UCt-TDBEj2HHZ7zQepJAsleg")

                .addGroup(" Frequently Asked Questions !!")
                .addWebsite("www.o2academia.org/faq.php")
                .addGroup(" Privacy Policy !!")
                .addWebsite("www.o2academia.org/privacy.php")
                .addGroup(" Terms and Conditions !!")
                .addWebsite("www.o2academia.org/terms.php")
                .addItem(new Element().setTitle("Version 1.1"))
                .create();
        setContentView(aboutPage);
    }
}