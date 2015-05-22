package com.edlogiq.neurongym.signin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.constant.RefrenceWrapper;
//import com.facebook.Session;
//import com.facebook.android.AsyncFacebookRunner;
//import com.facebook.android.DialogError;
//import com.facebook.android.Facebook;
//import com.facebook.android.FacebookError;
import com.edlogiq.neurongym.neurongym.ThemeSelect;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import com.edlogiq.neurongym.R;


public class SignInActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {


    LinearLayout facebook_login, twitter_login, google_login, linkedin_login;

    DrawerLayout dLayout;
    FrameLayout fram;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle,mTitle;
    private boolean sliderbar=false;
    private LinearLayout home,game,brain,compare,leaderbord,friend,settings,language,logout,exit;
    private ImageView profileImageView;
    private RefrenceWrapper refrence;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        refrence=RefrenceWrapper.getRefrenceWrapper(this);
        ((RelativeLayout)findViewById(R.id.signin)).setBackgroundResource(R.color.white);
        if(DataBase.getTheam(this).equals("dark")){
            ((RelativeLayout)findViewById(R.id.signin)).setBackgroundResource(R.color.black);
        }
        profileImageView=(ImageView)findViewById(R.id.profileimage);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.sign_in);



        ((LinearLayout)findViewById(R.id.facebooksign)).setOnClickListener(listner);
        ((LinearLayout)findViewById(R.id.twittersign)).setOnClickListener(listner);
        ((LinearLayout)findViewById(R.id.googlesignup)).setOnClickListener(listner);
        ((Button)findViewById(R.id.buttonlogin)).setOnClickListener(listner);
        ((LinearLayout)findViewById(R.id.forgetpasswordlayout)).setOnClickListener(listner);

        slidelayoute();
        googleConnection();
    }

    View.OnClickListener listner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.facebooksign){

            }else if(v.getId()==R.id.twittersign){
                tweeter();
            }else if(v.getId()==R.id.googlesignup && mGoogleApiClient.isConnected()){
                signInWithGplus();
            }else if(v.getId()==R.id.buttonlogin){
                login();
            }else if(v.getId()==R.id.forgetpasswordlayout){
                ((RelativeLayout)findViewById(R.id.forgetpassword)).setVisibility(View.VISIBLE);
            }
        }
    };



//    private void slidex() {
//        home=(LinearLayout)findViewById(R.id.home);
//        game=(LinearLayout)findViewById(R.id.game);
//        brain=(LinearLayout)findViewById(R.id.brain);
//        compare=(LinearLayout)findViewById(R.id.compare);
//        leaderbord=(LinearLayout)findViewById(R.id.leaderbord);
//        friend=(LinearLayout)findViewById(R.id.friends);
//        settings=(LinearLayout)findViewById(R.id.setting);
//        language=(LinearLayout)findViewById(R.id.language);
//        logout=(LinearLayout)findViewById(R.id.logout);
//        exit=(LinearLayout)findViewById(R.id.exit);
//
//        home.setOnClickListener(listner);
//        game.setOnClickListener(listner);
//        brain.setOnClickListener(listner);
//        compare.setOnClickListener(listner);
//        leaderbord.setOnClickListener(listner);
//        friend.setOnClickListener(listner);
//        settings.setOnClickListener(listner);
//        language.setOnClickListener(listner);
//        logout.setOnClickListener(listner);
//        exit.setOnClickListener(listner);
//    }

    private void slidelayoute() {

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fram=(FrameLayout)findViewById(R.id.content_frame);
        mTitle = mDrawerTitle = getTitle();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mDrawerToggle = new ActionBarDrawerToggle(this, dLayout,
                R.drawable.menu, //nav menu toggle icon
                R.drawable.language_icon, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                sliderbar=false;
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                sliderbar=true;
                invalidateOptionsMenu();
            }
        };
        dLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


//    View.OnClickListener listner=new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            if(v.getId()==R.id.home){
//                home.setBackgroundResource(R.color.cyan);
//            }else if(v.getId()==R.id.game){
//                game.setBackgroundResource(R.color.cyan);
//            }else if(v.getId()==R.id.brain){
//                brain.setBackgroundResource(R.color.cyan);
//            }else if(v.getId()==R.id.compare){
//                compare.setBackgroundResource(R.color.cyan);
//            }else if(v.getId()==R.id.leaderbord){
//                leaderbord.setBackgroundResource(R.color.cyan);
//            }else if(v.getId()==R.id.friends){
//                friend.setBackgroundResource(R.color.cyan);
//            }else if(v.getId()==R.id.setting){
//                settings.setBackgroundResource(R.color.cyan);
//            }else if(v.getId()==R.id.language){
//                language.setBackgroundResource(R.color.cyan);
//            }else if(v.getId()==R.id.logout){
//
////                SharedPreferences.Editor edit = pref.edit();
////                edit.putString("ACCESS_TOKEN", "");
////                edit.putString("ACCESS_TOKEN_SECRET", "");
////                edit.commit();
////                ((ImageView)findViewById(R.id.profileimage)).setBackgroundResource(0);
////                signOut();
////                callFacebookLogout(SignInActivity.this);
//
//
//            }else if(v.getId()==R.id.exit){
//                exit.setBackgroundResource(R.color.cyan);
//            }
//            dLayout.closeDrawers();
//        }
//    };


        private void login() {

         String emailid=((EditText)findViewById(R.id.loginemailid)).getText().toString();
        String password=((EditText)findViewById(R.id.loginpassword)).getText().toString();

       if ((((EditText)findViewById(R.id.loginemailid)).getText()).equals(" ")){
            Toast.makeText(this, "Enter your mailId", Toast.LENGTH_SHORT).show();

        } else
       if((((EditText)findViewById(R.id.loginpassword)).getText()).equals(null)) {
            Toast.makeText(this, "Enter the password", Toast.LENGTH_SHORT).show();
        }else{

           ParseUser.logInInBackground(emailid,password,new LogInCallback() {
               @Override
               public void done(ParseUser parseUser, ParseException e) {
                   if (parseUser != null) {
                       userinfo(parseUser);

                   } else {
                       Toast.makeText(SignInActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                       Log.e("usererror",e.getMessage());
                   }
               }
           });

        }
    }





//    Tweeter integration....

     private void tweeter(){
         ParseTwitterUtils.logIn(this, new LogInCallback() {
             @Override
             public void done(ParseUser user, ParseException err) {
                 if (user == null) {
                     Log.e("MyApp", "Uh oh. The user cancelled the Twitter login.");
                 } else if (user.isNew()) {
//                     nextactivity();
                     Toast.makeText(SignInActivity.this," This user is not valid ",Toast.LENGTH_SHORT).show();
                     Log.e("MyApp", "User signed up and logged in through Twitter!");
                 } else {
                     userinfo(user);
                     Log.e("MyApp", "User logged in through Twitter!");
                 }
             }
         });

     }


    private void userinfo(ParseUser parseUser) {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("UserIformation");
        query.whereEqualTo("parent", parseUser);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> markers, ParseException e) {
                if (e == null) {
                    retriveAllData(markers.get(0));
                } else {
                    Log.e("value error",""+e.getMessage());
                }
            }
        });
    }

    private void retriveAllData(ParseObject parseObject) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserIformation");
        query.getInBackground(parseObject.getObjectId().toString(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    Log.e("Allvalue",""+object.get("name")+"   "+object.get("email")+"   "+object.get("dateofbirth")
                            +"   "+object.get("country")+"   "+object.get("gender")+"   "+object.get("dualfocus")
                            +"   "+object.get("dualfocuspro")+"   "+object.get("blink")+"   "+object.get("name") );
                    DataBase.setUserObjectId(object.getObjectId().toString(),SignInActivity.this);
                    DataBase.setUserName(object.get("name").toString(),SignInActivity.this);
                    ParseFile image = (ParseFile) object.getParseFile("image");
                    image.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] bytes, ParseException e) {
                            if(e==null){
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
                                if(bmp!=null){
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                    byte imageInByte[] = stream.toByteArray();
                                    String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                                    DataBase.setUserImage(SignInActivity.this,encodedImage);
                                }
                            }else{
                                Log.e("errorimage",e.getMessage());
                            }
                        }
                    });
                } else {
                    Log.e("errorimagennnnnnnnnn",e.getMessage());
                    // something went wrong
                }
            }
        });
        nextactivity();
    }



//    private class TokenGet extends AsyncTask<String, String, String> {
//        @Override
//        protected String doInBackground(String... args) {
//            try {
//
//                requestToken = twitter.getOAuthRequestToken();
//                oauth_url = requestToken.getAuthorizationURL();
//            } catch (TwitterException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return oauth_url;
//        }
//        @Override
//        protected void onPostExecute(String oauth_url) {
//            if(oauth_url != null){
//                auth_dialog = new Dialog(SignInActivity.this);
//                auth_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//                auth_dialog.setContentView(R.layout.auth_dialog);
//                web = (WebView)auth_dialog.findViewById(R.id.webv);
//                web.getSettings().setJavaScriptEnabled(true);
//                web.loadUrl(oauth_url);
//                web.setWebViewClient(new WebViewClient() {
//                    boolean authComplete = false;
//                    @Override
//                    public void onPageStarted(WebView view, String url, Bitmap favicon){
//                        super.onPageStarted(view, url, favicon);
//                    }
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
//                        super.onPageFinished(view, url);
//                        if (url.contains("oauth_verifier") && authComplete == false){
//                            authComplete = true;
//                            Uri uri = Uri.parse(url);
//                            oauth_verifier = uri.getQueryParameter("oauth_verifier");
//                            auth_dialog.dismiss();
//                            new AccessTokenGet().execute();
//                        }else if(url.contains("denied")){
//                            auth_dialog.dismiss();
//                            Toast.makeText(getApplicationContext(), "Sorry !, Permission Denied", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                auth_dialog.show();
//                auth_dialog.setCancelable(true);
//            }else{
//                Toast.makeText(getApplicationContext(), "Sorry !, Network Error or Invalid Credentials", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//    private class AccessTokenGet extends AsyncTask<String, String, Boolean> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progress = new ProgressDialog(SignInActivity.this);
//            progress.setMessage("Fetching Data ...");
//            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progress.setIndeterminate(true);
//            progress.show();
//        }
//        @Override
//        protected Boolean doInBackground(String... args) {
//            try {
//                accessToken = twitter.getOAuthAccessToken(requestToken, oauth_verifier);
//                SharedPreferences.Editor edit = pref.edit();
//                edit.putString("ACCESS_TOKEN", accessToken.getToken());
//                edit.putString("ACCESS_TOKEN_SECRET", accessToken.getTokenSecret());
//                User user = twitter.showUser(accessToken.getUserId());
//                profile_url = user.getOriginalProfileImageURL();
//                edit.putString("NAME", user.getName());
//                edit.putString("IMAGE_URL", user.getOriginalProfileImageURL());
//                edit.putString("COUNTRY", user.getLocation());
//
//
//                edit.commit();
//            } catch (TwitterException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            return true;
//        }
//        @Override
//        protected void onPostExecute(Boolean response) {
//            if(response){
//                progress.hide();
//               tweeterdataget();
//            }
//        }
//    }
//
//    private void tweeterdataget() {
//        new LoadProfile().execute();
//    }
//
//    private class LoadProfile extends AsyncTask<String, String, Bitmap> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progress = new ProgressDialog(SignInActivity.this);
//            progress.setMessage("Loading Profile ...");
//            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progress.setIndeterminate(true);
//            progress.show();
//        }
//        protected Bitmap doInBackground(String... args) {
//            try {
//                bitmap = BitmapFactory.decodeStream((InputStream) new URL(pref.getString("IMAGE_URL", "")).getContent());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//        protected void onPostExecute(Bitmap image) {
//            Bitmap image_circle = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//            BitmapShader shader = new BitmapShader (bitmap,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//            Paint paint = new Paint();
//            paint.setShader(shader);
//            Canvas c = new Canvas(image_circle);
//            c.drawCircle(image.getWidth()/2, image.getHeight()/2, image.getWidth()/2, paint);
//            ((ImageView)findViewById(R.id.profileimage)).setImageBitmap(image_circle);
//            ((TextView)findViewById(R.id.proflename)).setText("" + pref.getString("NAME", ""));
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte imageInByte[] = stream.toByteArray();
//            String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
//            DataBase.setUserImage(SignInActivity.this,encodedImage);
//            DataBase.setUserName(pref.getString("NAME", ""),SignInActivity.this);
//            refrence.setName(pref.getString("NAME", ""));
//            refrence.setImage(image);
//            progress.hide();
//            nextactivity();
//        }
//    }



    // Gmail ya Google Plus connection provider...




    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "MainActivity";

    // Profile pic image size in pixels
    private static final int PROFILE_PIC_SIZE = 400;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;


    private void googleConnection(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(SignInActivity.this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }


    private void signInWithGplus() {
        Log.e("google",""+mGoogleApiClient.isConnecting()+"    "+mGoogleApiClient.isConnected());
        if (!mGoogleApiClient.isConnected()) {
            mSignInClicked = true;
            resolveSignInError();
        }else{
            getProfileInformation();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mGoogleApiClient!=null)
            mGoogleApiClient.connect();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(mGoogleApiClient!=null){
//            if (mGoogleApiClient.isConnected()) {
//                mGoogleApiClient.disconnect();
//            }
//        }
//    }

    @Override
    public void onConnected(Bundle arg0) {
        mSignInClicked = false;
        Log.e("google",""+mGoogleApiClient.isConnected());
        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }





    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;

            if (mSignInClicked) {
                resolveSignInError();
            }
        }

    }

    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode,Intent intent) {
//        ParseFacebookUtils.finishAuthentication(requestCode, responseCode, intent);


        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    private void getProfileInformation() {
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);
                String personName = currentPerson.getDisplayName();
                String personPhotoUrl = currentPerson.getImage().getUrl();
                String personGooglePlusProfile = currentPerson.getUrl();


                refrence.setName(personName);


                DataBase.setUserName(personName,SignInActivity.this);
//                ((TextView)findViewById(R.id.proflename)).setText("" + personName);
                personPhotoUrl = personPhotoUrl.substring(0,
                        personPhotoUrl.length() - 2)
                        + PROFILE_PIC_SIZE;


//                new LoadProfileImage(profileImageView).execute(personPhotoUrl);

            } else {
                Toast.makeText(getApplicationContext(),
                        "Person information is null", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadProfileImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {

            Log.e("Error",""+result);

            bmImage.setImageBitmap(result);

            Bitmap image_circle = Bitmap.createBitmap(result.getWidth(), result.getHeight(), Bitmap.Config.ARGB_8888);
            BitmapShader shader = new BitmapShader (result,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setShader(shader);
            Canvas c = new Canvas(image_circle);
            c.drawCircle(result.getWidth()/2, result.getHeight()/2, result.getWidth()/2, paint);
            ((ImageView)findViewById(R.id.profileimage)).setImageBitmap(image_circle);
            refrence.setImage(result);
            nextactivity();
        }
    }











    //Facebook Integration

    // Your Facebook APP ID
//    private static String APP_ID = "364311920437889"; // Replace your App ID here
//
//    // Instance of Facebook Class
//    private static Facebook facebook;
//    private AsyncFacebookRunner mAsyncRunner;
//    String FILENAME = "AndroidSSO_data";
//    private static SharedPreferences mPrefs;
//    private static String access_token;
//
//
//  private void facebook(){
//      facebook = new Facebook(APP_ID);
//      mAsyncRunner = new AsyncFacebookRunner(facebook);
//  }
//
//    public void loginToFacebook() {
//        mPrefs = getPreferences(MODE_PRIVATE);
//         access_token = mPrefs.getString("access_token", null);
//        long expires = mPrefs.getLong("access_expires", 0);
//
//        Log.e("Login", "hh  "+access_token+"  "+expires);
//
//        if (access_token != null) {
//            facebook.setAccessToken(access_token);
//        }
//
//        if (expires != 0) {
//            facebook.setAccessExpires(expires);
//        }
//        if (!facebook.isSessionValid()) {
//            facebook.authorize(this,
//                    new String[] { "email", "publish_stream" },
//                    new Facebook.DialogListener() {
//
//                        @Override
//                        public void onCancel() {
//                            // Function to handle cancel event
//                        }
//
//                        @Override
//                        public void onComplete(Bundle values) {
//                            // Function to handle complete event
//                            // Edit Preferences and update facebook acess_token
//                            SharedPreferences.Editor editor = mPrefs.edit();
//                            editor.putString("access_token",facebook.getAccessToken());
//                            editor.putLong("access_expires",facebook.getAccessExpires());
//                            editor.commit();
//                            getProfileInformationfacebook();
//                        }
//
//                        @Override
//                        public void onError(DialogError error) {
//                            // Function to handle error
//
//                        }
//
//                        @Override
//                        public void onFacebookError(FacebookError fberror) {
//                            // Function to handle Facebook errors
//
//                        }
//
//                    });
//
//        }
//
//    }
//
//    public void getProfileInformationfacebook() {
//        mAsyncRunner.request("me", new AsyncFacebookRunner.RequestListener() {
//            @Override
//            public void onComplete(String response, Object state) {
////                Log.e("response", response);
//                String json = response;
//                try {
//                    JSONObject profile = new JSONObject(json);
//                    refrence.setName(profile.getString("name"));
//                    refrence.setEmail(profile.getString("email"));
//                    refrence.setGender(profile.getString("gender"));
//                    nextactivity();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onIOException(IOException e, Object state) {
//            }
//
//            @Override
//            public void onFileNotFoundException(FileNotFoundException e,
//                                                Object state) {
//            }
//
//            @Override
//            public void onMalformedURLException(MalformedURLException e,
//                                                Object state) {
//            }
//
//            @Override
//            public void onFacebookError(FacebookError e, Object state) {
//            }
//        });
//    }


//    public static void callFacebookLogout(Context context) {
//
//        Session session = Session.getActiveSession();
//        Log.e("logout",""+session);
//        if (session != null) {
//
//            if (!session.isClosed()) {
//                session.closeAndClearTokenInformation();
//                facebook.setAccessToken(null);
//                facebook.setSession(session);
//                //clear your preferences if saved
//            }
//        } else  {
//
//            SharedPreferences.Editor editor = mPrefs.edit();
//            editor.putString("access_token",null);
//            editor.putLong("access_expires",0);
//            editor.commit();
//
//            facebook.setAccessToken(null);
//            facebook.setAccessExpires(0);
//
//            Log.e("logout",facebook.getAccessToken()+"  "+facebook.getAccessExpires());
//
//        }
//
//    }



    private void nextactivity(){
        SignInActivity.this.finish();
        Intent intent=new Intent(SignInActivity.this,ThemeSelect.class);
        startActivity(intent);
    }

}
