package kr.hs.emirim.app2015.odazum;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks, View.OnClickListener{

    final static int MAIN = 0;
    final static int WISH = 100;
    final static int GOODS_RECENT = 31;

    static int mLastMenu;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawFragment mNavigationDrawerFragment;


    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout drawer_layout;
    ActionBar actionBar;
    Toolbar toolbar;
    ViewPager pager;
    TextView profile_name;
    TextView profile_birth;

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    ImageButton cover;
    ImageButton but_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLastMenu = MainActivity.MAIN;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // profile_name = (TextView)findViewById(R.id.profile_name_text);
        //profile_name.setText(user.name);

        mNavigationDrawerFragment = (NavigationDrawFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, R.string.drawer_close, R.string.drawer_open) {
            //** Called when a drawer has settled in a completely open state. *//*
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawer_layout.setDrawerListener(mDrawerToggle);


        pager= (ViewPager)findViewById(R.id.pager);
        //ViewPager에 설정할 Adapter 객체 생성
        //ListView에서 사용하는 Adapter와 같은 역할.
        //다만. ViewPager로 스크롤 될 수 있도록 되어 있다는 것이 다름
        //PagerAdapter를 상속받은 CustomAdapter 객체 생성
        //CustomAdapter에게 LayoutInflater 객체 전달
        CustomAdapter adapter= new CustomAdapter(getLayoutInflater());
        //ViewPager에 Adapter 설정
        pager.setAdapter(adapter);



        cover = (ImageButton)findViewById(R.id.profile_cover_image);
        cover.setOnClickListener(this);

        but_main = (ImageButton)findViewById(R.id.but_main);
        but_main.setOnClickListener(this);

        /*
        myDB = new DB(this);
        myDB.open();
        Cursor all_cursor = myDB.AllRows();
        all_cursor.moveToFirst();
        if (all_cursor.getCount() == 0) {
            Log.d("TEST", "아무것도없다.");
            return;
        } else {
            profile_name=(TextView)findViewById(R.id.profile_name_text);
            profile_name.setText(all_cursor.getString(all_cursor.getColumnIndex("name")));

            profile_birth=(TextView)findViewById(R.id.profile_birth);
            profile_birth.setText( all_cursor.getString(all_cursor.getColumnIndex("birth")));

        }
        */



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        switch (position) {
            case NavigationDrawFragment.NAVDRAWER_ITEM_WISHLIST:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,new WishListFragment())
                        .commit();
                onSectionAttached(1);
                break;
            case NavigationDrawFragment.NAVDRAWER_ITEM_SETTING:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SettingActivity())
                        .commit();
                onSectionAttached(2);
                break;
            case NavigationDrawFragment.NAVDRAWER_ITEM_SEARCH:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new SearchFragment())
                        .commit();
                onSectionAttached(3);
                break;
            case NavigationDrawFragment.NAVDRAWER_ITEM_HOTTEST:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new MostWishFragment())
                        .commit();
                onSectionAttached(4);
                break;
            case NavigationDrawFragment.NAVDRAWER_ITEM_CLICK:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new MostViewFragment())
                        .commit();
                onSectionAttached(5);
                break;
            case NavigationDrawFragment.NAVDRAWER_ITEM_FASHION:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new ClothesFragment())
                        .commit();
                onSectionAttached(6);
                break;
            case NavigationDrawFragment.NAVDRAWER_ITEM_ACC:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new GoodsFragment())
                        .commit();
                onSectionAttached(7);
                break;
            case NavigationDrawFragment.NAVDRAWER_ITEM_BEAUTY:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new BeautyFragment())
                        .commit();
                onSectionAttached(8);
                break;
            case NavigationDrawFragment.NAVDRAWER_ITEM_FOOD:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new FoodFragment())
                        .commit();
                onSectionAttached(9);
                break;
            case NavigationDrawFragment.NAVDRAWER_ITEM_FANCY:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new FancyFragment())
                        .commit();
                onSectionAttached(10);
                break;
            default:

                break;
        }
    }

    public void onSectionAttached(int number) {
        Log.e("number", "--->" + number);
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
            case 7:
                mTitle = getString(R.string.title_section7);
                break;
            case 8:
                mTitle = getString(R.string.title_section8);
                break;
            case 9:
                mTitle = getString(R.string.title_section9);
                break;
            case 10:
                mTitle = getString(R.string.title_section10);
                break;


        }
    }

    public void restoreActionBar() {
        actionBar = getSupportActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mDrawerToggle.syncState();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            if(MyApplication.isLollipop()) {
                rootView = inflater.inflate(R.layout.fragment_main, container, false);

            }else{
                rootView = inflater.inflate(R.layout.fragment_main_pre, container, false);
            }

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            /*((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));*/
        }
    }

    public void PagerClick(View view){
        EditText et = (EditText)findViewById(R.id.text_search);

        switch (pager.getCurrentItem()){
            case 0:
                et.append("0");
                break;
            case 1:
                et.append("1");
                break;
            case 2:
                et.append("2");
                break;
            default:
        }
    }

    private void doTakePhotoAction()
    {
    /*
     * 참고 해볼곳
     * http://2009.hfoss.org/Tutorial:Camera_and_Gallery_Demo
     * http://stackoverflow.com/questions/1050297/how-to-get-the-url-of-the-captured-image
     * http://www.damonkohler.com/2009/02/android-recipes.html
     * http://www.firstclown.us/tag/android/
     */

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 임시로 사용할 파일의 경로를 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        // 특정기기에서 사진을 저장못하는 문제가 있어 다음을 주석처리 합니다.
        //intent.putExtra("return-data", true);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    /**
     * 앨범에서 이미지 가져오기
     */
    private void doTakeAlbumAction()
    {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != RESULT_OK)
        {
            return;
        }

        switch(requestCode)
        {
            case CROP_FROM_CAMERA:
            {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                final Bundle extras = data.getExtras();

                if(extras != null)
                {
                    Bitmap photo = extras.getParcelable("data");
                    cover.setImageBitmap(photo);
                }

                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if(f.exists())
                {
                    f.delete();
                }

                break;
            }

            case PICK_FROM_ALBUM:
            {
                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.

                mImageCaptureUri = data.getData();
            }

            case PICK_FROM_CAMERA:
            {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 90);
                intent.putExtra("outputY", 90);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }
    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId()) {
            case R.id.profile_cover_image:

                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakePhotoAction();
                    }
                };

                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                new android.app.AlertDialog.Builder(this)
                        .setTitle("프로필사진 변경")
                        .setPositiveButton("사진촬영", cameraListener)
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
                break;

            case R.id.but_main:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(this.getString(R.string.exit)) // 제목부분 텍스트
                    .setMessage(this.getString(R.string.exit2)) // 내용부분 텍스트
                    .setPositiveButton(this.getString(android.R.string.yes), //승인버튼을 눌렀을때..
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    moveTaskToBack(true);
                                    finish(); //종료
                                }
                            }
                    ).setNegativeButton(this.getString(android.R.string.no), null).show(); //취소버튼을 눌렀을때..
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
