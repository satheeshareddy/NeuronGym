package com.edlogiq.neurongym.signin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.edlogiq.neurongym.constant.DataBase;
import com.edlogiq.neurongym.constant.RefrenceWrapper;
import com.edlogiq.neurongym.R;
import com.edlogiq.neurongym.neurongym.ThemeSelect;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Form extends ActionBarActivity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private RefrenceWrapper refrence;
    private RadioGroup radioGroup;
    TextView name,emai,dateofbirth,country,password;
    ParseObject userinfo;
    ParseUser user;
    ImageView profileimage;
    private ProgressDialog mProgressDialog;
    private boolean userval=true;
    private LoginButton fbbutton;
    private Dialog progressDialog;
//    private DatePicker date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        refrence=RefrenceWrapper.getRefrenceWrapper(this);
        AppEventsLogger.activateApp(this);
        init();

        googleConnection();

        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
            radioGroup.check(R.id.radioButtonman);


    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }



    private void init() {
        name=(EditText)findViewById(R.id.editName);
        emai=(EditText)findViewById(R.id.editemail);
        dateofbirth=(EditText)findViewById(R.id.editdate);
        country=(EditText)findViewById(R.id.editTextcountry);
        password=(EditText)findViewById(R.id.editpassword);
        profileimage=(ImageView)findViewById(R.id.imageView);
        ((TextView)findViewById(R.id.btnMainOk)).setOnClickListener(listner);
        ((Button)findViewById(R.id.buttonCamera)).setOnClickListener(listner);
        ((Button)findViewById(R.id.buttonGalary)).setOnClickListener(listner);
        ((LinearLayout)findViewById(R.id.layoutetwitter)).setOnClickListener(this);
        ((LinearLayout)findViewById(R.id.layoutefacebook)).setOnClickListener(this);
        ((LinearLayout)findViewById(R.id.layoutegoogle)).setOnClickListener(this);
        ((LinearLayout)findViewById(R.id.layoutgmail)).setOnClickListener(this);


    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }



    private final  boolean isValidDOB(String target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            String dob[]=target.split("/");
            Log.e("date",target+"   "+dob[0]+"  "+dob[1]+"  "+dob[2]);
            if(dob[0].length()<=2 && dob[0].length()>0){
                if(dob[1].length()<=2 && dob[1].length()>0) {
                    if(dob[2].length()==4) {
                        return true;
                    }else{ return false;}
                }else{ return false;}
            }else{ return false;}
        }
    }


   View.OnClickListener listner=new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           if(v.getId()==R.id.btnMainOk) {
               if(userval) {
                   register();
               }else{
                   login();
               }
           }else if(v.getId()==R.id.buttonCamera){
               Captureimage();
           }
           else if(v.getId()==R.id.buttonGalary) {
               Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(intent, RESULT_LOAD_IMAGE);
           }else if(v.getId()==R.id.editdate){
//               ((RelativeLayout)findViewById(R.id.Datlayoute)).setVisibility(View.VISIBLE);
           }
       }
   };

    @Override
    public void onClick(View v) {

        if( ((RelativeLayout)findViewById(R.id.loginform)).getVisibility()==View.VISIBLE){
            return;
        }
       if(v.getId()==R.id.layoutgmail){
           userval=true;
            ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.VISIBLE);
        }else if(v.getId()==R.id.layoutetwitter){
           userval=false;
            tweeter();
        }else if(v.getId()==R.id.layoutefacebook){
           userval=false;
            facebook();
        }else if(v.getId()==R.id.layoutegoogle ){  //&& mGoogleApiClient.isConnected()
           signInWithGplus();
       }
    }

    private void register() {

        Log.e("Values",name.getText()+" "+password.getText()+"  "+dateofbirth.getText()+"  "+country.getText());

        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(emai.getText())){
            Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show();
        } else if(password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter the password", Toast.LENGTH_SHORT).show();
        }else if(!isValidDOB(dateofbirth.getText().toString())) {
            Toast.makeText(this, "Enter the dateofbirth or dateofbirth is invalid", Toast.LENGTH_SHORT).show();
        }else if(country.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter the country", Toast.LENGTH_SHORT).show();
        }else if(camera==null) {
            Toast.makeText(this, "Select your image", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog = ProgressDialog.show(Form.this, "", "Logging in...", true);
            user = new ParseUser();
            user.setEmail(emai.getText().toString());
            user.setUsername(emai.getText().toString());
            user.setPassword(password.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException er) {
                    if (er != null) {
                        Toast.makeText(getApplicationContext(), er.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        new myAsyncTask().execute();
                        Log.e("hello","hello");
                    }
                }
            });

        }
    }


    private void login() {
        if (name.getText().equals(" ")) {
            Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show();
        }else if(!isValidDOB(dateofbirth.getText().toString())) {
            Toast.makeText(this, "Enter the dateofbirth or dateofbirth is invalid", Toast.LENGTH_SHORT).show();
        }else if(country.getText().equals(null)) {
            Toast.makeText(this, "Enter the country", Toast.LENGTH_SHORT).show();
        }else if(camera==null) {
            Toast.makeText(this, "Select your image", Toast.LENGTH_SHORT).show();
        }else{
            new myAsyncTask().execute();
        }
    }


    private void tweeter(){
        ParseTwitterUtils.logIn(this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.e("MyApp", "Uh oh. The user cancelled the Twitter login.");
                } else if (user.isNew()) {
                    ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.VISIBLE);
                    ((LinearLayout)findViewById(R.id.mailidlayoute)).setVisibility(View.GONE);
                    ((LinearLayout)findViewById(R.id.passwordlayoute)).setVisibility(View.GONE);
                    userval=false;
                    Log.e("MyApp", "User signed up and logged in through Twitter!");
                } else {
                    userinformation(user);
                    Log.e("MyApp", "User Already logged up through Twitter!");

                }

            }
        });
    }




    private void facebook(){
        progressDialog = ProgressDialog.show(Form.this, "", "Logging in...", true);
        List<String> permissions = Arrays.asList("public_profile,email,user_friends");


        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permissions, new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException err) {
                progressDialog.dismiss();

                if (user == null) {
                    Log.e("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    ((RelativeLayout) findViewById(R.id.loginform)).setVisibility(View.VISIBLE);
                    ((LinearLayout) findViewById(R.id.mailidlayoute)).setVisibility(View.GONE);
                    ((LinearLayout) findViewById(R.id.passwordlayoute)).setVisibility(View.GONE);
                    userval = false;
                    Log.e("MyApp", "User signed up and logged in through Facebook!");
                } else {
                    userinformation(user);
                    Log.e("MyApp", "User logged in through Facebook!");
                }
            }
        });
    }

    private void userinformation(ParseUser parseUser) {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("UserIformation");
        query.whereEqualTo("parent", parseUser);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> markers, ParseException e) {
                if (e == null && markers.size()!=0) {
                    retriveAllData(markers.get(0));
                } else {
//                    Log.e("value error",""+e.getMessage());
                }
            }
        });
    }


    private class myAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Form.this, "", "Logging in...", true);
            Log.e("pre","prehello");
        }

        @Override
        protected String doInBackground(String... params) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            camera.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte imageInByte[] = stream.toByteArray();
            String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
            DataBase.setUserImage(Form.this, encodedImage);
            DataBase.setUserName(name.getText().toString(),Form.this);
            ParseFile image = new ParseFile("image.txt", imageInByte);
            Log.e("middle","middle");
            int valuew= radioGroup.getCheckedRadioButtonId();
            userinfo = new ParseObject("UserIformation");
            userinfo.put("name", name.getText().toString());
            userinfo.put("dateofbirth", (dateofbirth.getText().toString()));
            userinfo.put("country", country.getText().toString());
            userinfo.put("gender",((RadioButton)findViewById(valuew)).getText());
            userinfo.put("parent", ParseUser.getCurrentUser());
            userinfo.put("image", image);

            userinfo.saveInBackground();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            Log.e("end","end");
            nextactiviy();
        }
    }




    private void retriveAllData(ParseObject parseObject) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserIformation");
        query.getInBackground(parseObject.getObjectId().toString(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                  Log.e("Allvalue",""+object.get("name")+"   "+object.get("email")+"   "+object.get("dateofbirth")
                          +"   "+object.get("country")+"   "+object.get("gender")+"   "+object.get("dualfocus")
                          +"   "+object.get("dualfocuspro")+"   "+object.get("blink")+"   "+object.get("name") );

                        DataBase.setUserObjectId(object.getObjectId().toString(),Form.this);
                        DataBase.setUserName(object.get("name").toString(),Form.this);
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
                                    DataBase.setUserImage(Form.this,encodedImage);
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
        nextactiviy();
    }

    private void nextactiviy() {

        if( ((RelativeLayout)findViewById(R.id.loginform)).getVisibility()==View.VISIBLE){
            ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.GONE);
        }
        DataBase.setLogin("login",Form.this);
        Intent intent = new Intent(this, ThemeSelect.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


//    Image capture and choose to galary.

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap camera;
    private String picturePath;

    private void Captureimage() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

    }
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }
    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),IMAGE_DIRECTORY_NAME);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "IMG_" + timeStamp + ".jpg");
        }else {
            return null;
        }
        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
          Log.e("String",resultCode+"   "+requestCode);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode != RESULT_OK) {
                mSignInClicked = false;
            }
            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }


//        mcallback.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {

                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            camera= BitmapFactory.decodeFile(picturePath);
            Log.e("Camera111",""+camera);
            setimage(camera);
        }
    }

    private void previewCapturedImage() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            camera = BitmapFactory.decodeFile(fileUri.getPath(),options);
            setimage(camera);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setimage(Bitmap image){
        Log.e("Camera",""+image);
        Bitmap image_circle = Bitmap.createBitmap(image.getWidth(), image.getHeight(),
                Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        Canvas c = new Canvas(image_circle);
        c.drawCircle(image.getWidth() / 2, image.getHeight() / 2, image.getWidth() / 3, paint);
        ((ImageView) findViewById(R.id.imageView)).setImageBitmap(image_circle);
    }

    @Override
    public void onBackPressed() {
        if( ((RelativeLayout)findViewById(R.id.loginform)).getVisibility()==View.VISIBLE){
            ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.GONE);
        }else{
            Intent intent= new Intent(this,LoginIn.class);
            startActivity(intent);
        }
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        this.finish();
    }



    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        Toast.makeText(this,"Internet is not connected",Toast.LENGTH_SHORT).show();
        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        Profile profile=Profile.getCurrentProfile();
        Log.e("profile",""+profile);
        display(profile);
    }

    @Override
    public void onStop() {
        super.onStop();

        if(mGoogleApiClient!=null){
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }
        }
    }

    private void display(Profile profile){
        if(profile!=null){
            Log.e("Name",profile.getName());
        }
    }




    // Gmail ya Google Plus connection provider...

    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "MainActivity";

    // Profile pic image size in pixels
    private static final int PROFILE_PIC_SIZE = 400;
    public static final int REQUEST_CODE_SIGN_IN = 100;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;
    private ConnectionResult mConnectionResult;


    private void googleConnection(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(Form.this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }


    private void signInWithGplus() {
        Log.e("google",""+mGoogleApiClient.isConnected());

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
        Log.e("googlestart",mGoogleApiClient+"  "+mGoogleApiClient.isConnected());
        if(mGoogleApiClient!=null) {
            mGoogleApiClient.connect();
        }
        Log.e("google",mGoogleApiClient+"  "+mGoogleApiClient.isConnected());
    }



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
        Log.e("connectionfailed",""+result);
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }
        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            Log.e("failed",""+result);
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

    private void getProfileInformation() {
        Log.e("information","Information");
        try {
            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                Person currentPerson = Plus.PeopleApi
                        .getCurrentPerson(mGoogleApiClient);
                String personName = currentPerson.getDisplayName();
                String personPhotoUrl = currentPerson.getImage().getUrl();

                refrence.setName(personName);
                String Gemail = Plus.AccountApi.getAccountName(mGoogleApiClient);

                user = new ParseUser();
                user.setEmail(Gemail);
                user.setUsername(Gemail);
                user.setPassword(currentPerson.getId());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException er) {
                        if (er != null) {
                            Toast.makeText(getApplicationContext(), er.getMessage(), Toast.LENGTH_SHORT).show();
                            userinformation(ParseUser.getCurrentUser());

                        } else {
                            ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.VISIBLE);
                            ((LinearLayout)findViewById(R.id.mailidlayoute)).setVisibility(View.GONE);
                            ((LinearLayout)findViewById(R.id.passwordlayoute)).setVisibility(View.GONE);
                            userval=false;
                        }
                    }
                });

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

        }
    }

}
