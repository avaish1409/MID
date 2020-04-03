package com.example.anirudh.mid.mid1707;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.anirudh.mid.mid1707.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;


public class MainActivity<setUserF> extends AppCompatActivity {

    FirebaseFirestore dbF =FirebaseFirestore.getInstance();
    Map<String, Object> docData = new HashMap<>();
    Map<String, Object> attData = new HashMap<>();
    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build();
    List<Integer> l1 = new ArrayList<Integer>();
    public boolean present;


    public MainActivity(){
//        SharedPreferences data2 = this.getSharedPreferences("com.example.anirudh.mid.mid1707",MODE_PRIVATE);

        dbF.setFirestoreSettings(settings);
        docData.put("name", "AppName3");
        docData.put("key", "newKey3");
        DocumentReference docRef = dbF.collection("users").document("SF");
// asynchronously retrieve the document
        //ApiFuture<DocumentSnapshot> future = docRef.get();
// ...
// future.get() blocks on response
        //DocumentSnapshot document = future.get();

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        present = true;
                        Log.d("Firestore4", "Document exists!");
                    } else {
                        present = false;
                        Log.d("Firestore4", "Document does not exist!");
                    }
                } else {
                    Log.d("Firestore4", "Failed with: ", task.getException());
                }
            }
        });

        dbF.collection("users")
                .add(docData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                        Log.i("Firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firebase", "Error adding document", e);
                        Log.d("Firebase", "Error adding document");
                    }
                });
    }
    public static String set="nil-init";

    public static Integer att[] = {80,70,60,66,72};
    public static Integer ttlCls[] = {10,10,5,6,25};
    public static Integer attCls[] = {8,7,3,4,18};
    public static Integer today_tt[];
    public static TextView home_showAtt[];
    public static TextView att_showAtt[];
    public static TextView att_showTtlCls[];
    public static TextView att_showAttCls[];


    public static Boolean cbox[]= {false,false,false,false,false,false,false,false};

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    //ArrayAdapter showAtt = new ArrayAdapter(this,R.layout.fragment_main,R.id.test2,sub);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //fab.startAnimation();

            }

        });



        String PREF_FILE_NAME = "PrefFile";
        SharedPreferences data = this.getSharedPreferences("com.example.anirudh.mid.mid1707",MODE_PRIVATE);

        l1.add(0, data.getInt("att",-5)); // adds 1 at 0 index
        l1.add(1, 2); // adds 2 at 1 index
        l1.add(2, 3); // adds 3 at 2 index
        l1.add(3, 4); // adds 4 at 3 index
        l1.add(4, 5); // adds 5 at 4 index
        attData.put("attCls", l1);
        dbF.collection("attSheet")
                .add(attData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firebase2", "DocumentSnapshot2 added with ID: " + documentReference.getId());
                        Log.i("Firebase2", "DocumentSnapshot2 added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firebase2", "Error adding document2", e);
                        Log.d("Firebase2", "Error adding document2");
                    }
                });

        String s= data.getString("subject","nil");
        Integer i= data.getInt("att",-10);

        TextView getData =(TextView) findViewById(R.id.getData);
        set= "Sub: "+s+" has Att: "+i;

        Integer indx= data.getInt("index",0);
        Integer val= data.getInt("value",80);
        Integer val2= data.getInt("value2",100);

        att[indx]= val/val2;
        attCls[indx]= val;
        ttlCls[indx]= val2;


        for(int j=0; j<8; j++){
            cbox[j]= data.getBoolean("cbox"+j,false);
            if(j<5) {
                att[j] = data.getInt("att" + j, 0);
                ttlCls[j]= data.getInt("ttlCls" + j, 0);
                attCls[j]= data.getInt("attCls" + j, 0);
            }
        }
        att = new Integer[]{80, 70, 60, 66, 72};
        ttlCls = new Integer[]{10,10,5,6,25};
        attCls = new Integer[]{8,7,3,4,18};





    }


    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public void sendMessage(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        Button btn = (Button) findViewById(R.id.login);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void testLogin(View v){
        Snackbar.make(v, "Re-direct ", Snackbar.LENGTH_LONG).setAction("Action", null).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        if(id == R.id.action_editProfile){
            Intent intent = new Intent(this, LoginActivity.class);

            startActivity(intent);
        }
        else if (id == R.id.action_editAtt) {
            Intent intent = new Intent(this, EditAttendanceActivity.class);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        String sub[] = {"Maths","CSE","ED","Electronics","HSE"};






        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

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





        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            //LinearLayout t=(LinearLayout) rootView.findViewById(R.id.test);

            RelativeLayout c0 = (RelativeLayout)rootView.findViewById(R.id.c0);
            ConstraintLayout c = (ConstraintLayout)rootView.findViewById(R.id.cl);
            ConstraintLayout c2 = (ConstraintLayout)rootView.findViewById(R.id.c2);

            TextView t1 = rootView.findViewById(R.id.test);

            TextView getData= rootView.findViewById(R.id.getData);



            TextView att_showSub[] = {rootView.findViewById(R.id.att_sub1),rootView.findViewById(R.id.att_sub2),rootView.findViewById(R.id.att_sub3),rootView.findViewById(R.id.att_sub4),rootView.findViewById(R.id.att_sub5)};

            att_showAtt = new TextView[]{rootView.findViewById(R.id.att_att1),rootView.findViewById(R.id.att_att2),rootView.findViewById(R.id.att_att3),rootView.findViewById(R.id.att_att4),rootView.findViewById(R.id.att_att5)};

            att_showTtlCls = new TextView[]{rootView.findViewById(R.id.att_ttlCls1),rootView.findViewById(R.id.att_ttlCls2),rootView.findViewById(R.id.att_ttlCls3),rootView.findViewById(R.id.att_ttlCls4),rootView.findViewById(R.id.att_ttlCls5)};

            att_showAttCls = new TextView[]{rootView.findViewById(R.id.att_attCls1), rootView.findViewById(R.id.att_attCls2), rootView.findViewById(R.id.att_attCls3), rootView.findViewById(R.id.att_attCls4), rootView.findViewById(R.id.att_attCls5)};

            TextView sch_showMonCls[] = {rootView.findViewById(R.id.sch_cls1),rootView.findViewById(R.id.sch_cls2),rootView.findViewById(R.id.sch_cls3),rootView.findViewById(R.id.sch_cls4),rootView.findViewById(R.id.sch_cls5),rootView.findViewById(R.id.sch_cls6),rootView.findViewById(R.id.sch_cls7),rootView.findViewById(R.id.sch_cls8)};

            TextView sch_showMonTime[] = {rootView.findViewById(R.id.sch_t1),rootView.findViewById(R.id.sch_t2),rootView.findViewById(R.id.sch_t3),rootView.findViewById(R.id.sch_t4),rootView.findViewById(R.id.sch_t5),rootView.findViewById(R.id.sch_t6),rootView.findViewById(R.id.sch_t7),rootView.findViewById(R.id.sch_t8)};

            TextView sch_showTueCls[] = {rootView.findViewById(R.id.sch_cls1_2),rootView.findViewById(R.id.sch_cls2_2),rootView.findViewById(R.id.sch_cls3_2),rootView.findViewById(R.id.sch_cls4_2),rootView.findViewById(R.id.sch_cls5_2),rootView.findViewById(R.id.sch_cls6_2),rootView.findViewById(R.id.sch_cls7_2),rootView.findViewById(R.id.sch_cls8_2)};

            TextView sch_showTueTime[] = {rootView.findViewById(R.id.sch_t1_2),rootView.findViewById(R.id.sch_t2_2),rootView.findViewById(R.id.sch_t3_2),rootView.findViewById(R.id.sch_t4_2),rootView.findViewById(R.id.sch_t5_2),rootView.findViewById(R.id.sch_t6_2),rootView.findViewById(R.id.sch_t7_2),rootView.findViewById(R.id.sch_t8_2)};

            TextView home_showTime[] = {rootView.findViewById(R.id.home_t1),rootView.findViewById(R.id.home_t2),rootView.findViewById(R.id.home_t3),rootView.findViewById(R.id.home_t4),rootView.findViewById(R.id.home_t5),rootView.findViewById(R.id.home_t6),rootView.findViewById(R.id.home_t7),rootView.findViewById(R.id.home_t8)};

            TextView home_showSub[] = {rootView.findViewById(R.id.home_sub1),rootView.findViewById(R.id.home_sub2),rootView.findViewById(R.id.home_sub3),rootView.findViewById(R.id.home_sub4),rootView.findViewById(R.id.home_sub5),rootView.findViewById(R.id.home_sub6),rootView.findViewById(R.id.home_sub7),rootView.findViewById(R.id.home_sub8)};

            home_showAtt = new TextView[]{rootView.findViewById(R.id.home_att1), rootView.findViewById(R.id.home_att2), rootView.findViewById(R.id.home_att3), rootView.findViewById(R.id.home_att4), rootView.findViewById(R.id.home_att5), rootView.findViewById(R.id.home_att6), rootView.findViewById(R.id.home_att7), rootView.findViewById(R.id.home_att8)};

            CheckBox home_showCb[] = {rootView.findViewById(R.id.home_cb1),rootView.findViewById(R.id.home_cb2),rootView.findViewById(R.id.home_cb3),rootView.findViewById(R.id.home_cb4),rootView.findViewById(R.id.home_cb5),rootView.findViewById(R.id.home_cb6),rootView.findViewById(R.id.home_cb7),rootView.findViewById(R.id.home_cb8)};

            //
            //
            Integer mon[] = {1,2,1,4,-2,0,0,3};                            // -1= no class; -2 = lunch_break(1:30) ; othrs = subject inddex;
            Integer tue[]= {2,-1,2,2,-2,1,0,4};
            Integer wed[]= {3,-1,2,2,-2,1,0,4};
            Integer thu[]= {4,-1,2,2,-2,1,0,4};
            Integer fri[]= {3,-1,2,2,-2,1,0,4};



            String currDate = DateFormat.getDateTimeInstance().format(new Date());
            int currDay = Calendar.DAY_OF_WEEK;

            int currDay2 = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            t1.setText(currDate+" - "+Integer.toString(currDay)+" - "+Integer.toString(currDay2));

            getData.setText(set);




            TableLayout home_tab=rootView.findViewById(R.id.home_table);
            TextView home_h=rootView.findViewById(R.id.home_holiday);



            int[] t={8,30};
            for(int i=0;i<8;i++){
                switch (mon[i]){
                    case -1:
                        //no class
                        t[0]+=1;
                        sch_showMonTime[i].setVisibility(View.GONE);
                        sch_showMonCls[i].setVisibility(View.GONE);
                        break;
                    case -2:
                        //lunch break
                        t[0]+=2;t[1]=00;
                        sch_showMonTime[i].setVisibility(View.GONE);
                        sch_showMonCls[i].setVisibility(View.GONE);
                        break;

                        default :
                            String s="";
                            if(t[1]==0){
                                s="00";
                            }else{
                                s=Integer.toString(t[1]);
                            }
                            sch_showMonTime[i].setText( Integer.toString(t[0]) + ":" +s);
                            sch_showMonCls[i].setText(sub[mon[i]]);
                            t[0]+=1;
                            break;



                }
            }
            today_tt= mon;




            t= new int[]{8, 30};
            for(int i=0;i<8;i++){
                switch (mon[i]){
                    case -1:
                        //no clas   s
                        t[0]+=1;
                        break;
                    case -2:
                        //lunch break
                        t[0]+=2;t[1]=00;
                        break;

                    default :
                        String s="";
                        if(t[1]==0){
                            s="00";
                        }else{
                            s=Integer.toString(t[1]);
                        }
                        sch_showTueTime[i].setText( Integer.toString(t[0]) + ":" +s);
                        sch_showTueCls[i].setText(sub[mon[i]]);
                        t[0]+=1;
                        break;



                }
            }
            currDay2=2;

            switch (currDay2){
                case 1:
                    home_h.setVisibility(View.VISIBLE);
                    home_tab.setVisibility(View.GONE);
                    break;

                case 2:
                    home_h.setVisibility(View.GONE);
                    home_tab.setVisibility(View.VISIBLE);
                    today_tt =mon;
                    break;
                case 3:
                    home_h.setVisibility(View.GONE);
                    home_tab.setVisibility(View.VISIBLE);
                    today_tt =tue;
                    break;
                case 4:
                    home_h.setVisibility(View.GONE);
                    home_tab.setVisibility(View.VISIBLE);
                    today_tt =wed;
                    break;
                case 5:
                    home_h.setVisibility(View.GONE);
                    home_tab.setVisibility(View.VISIBLE);
                    today_tt =thu;
                    break;
                case 6:
                    home_h.setVisibility(View.GONE);
                    home_tab.setVisibility(View.VISIBLE);
                    today_tt =fri;
                    break;
                case 7:
                    home_h.setVisibility(View.VISIBLE);
                    home_tab.setVisibility(View.GONE);
                    break;
            }


                //home_showTime[i].setText( Integer.toString(att[i]) );
                /*if(att[tue[sub[i]]]<75){
                    home_showAtt[i].setTextColor(Color.parseColor("#ff0000"));
                    //att_showSub[i].setTextColor(Color.parseColor("#ff0000"));

                }else {
                    home_showAtt[i].setTextColor(Color.parseColor("#000000"));
                    //att_showSub[i].setTextColor(Color.parseColor("#000000"));
                }*/


                t= new int[]{8, 30};
                for(int j=0;j<8;j++){


                    if(currDay2!=1 && currDay2!=7){

                        switch (today_tt[j]){
                            case -1:
                                //no class
                                home_showTime[j].setVisibility(View.GONE);
                                home_showSub[j].setVisibility(View.GONE);
                                home_showAtt[j].setVisibility(View.GONE);
                                home_showCb[j].setVisibility(View.GONE);
                                t[0]+=1;
                                break;
                            case -2:
                                //lunch break
                                home_showTime[j].setVisibility(View.GONE);
                                home_showSub[j].setVisibility(View.GONE);
                                home_showAtt[j].setVisibility(View.GONE);
                                home_showCb[j].setVisibility(View.GONE);
                                t[0]+=2;t[1]=00;
                                break;

                            default :
                                String s="";
                                if(t[1]==0){
                                    s="00";
                                }else{
                                    s=Integer.toString(t[1]);
                                }
                                if(j<5){
                                    if(att[today_tt[j]]<75){
                                        home_showAtt[j].setTextColor(Color.parseColor("#ff0000"));
                                    }
                                    else {
                                        home_showAtt[j].setTextColor(Color.parseColor("#000000"));
                                    }
                                    home_showTime[j].setText( Integer.toString(t[0]) + ":" +s);
                                    home_showSub[j].setText(sub[today_tt[j]]);
                                    home_showAtt[j].setText(Integer.toString(att[today_tt[j]]) );
                                    att_showAtt[j].setText(Integer.toString(att[today_tt[j]]) );
                                    att_showAttCls[j].setText(Integer.toString(attCls[today_tt[j]]) );
                                    att_showTtlCls[j].setText(Integer.toString(ttlCls[today_tt[j]]) );
                                    home_showCb[j].setChecked(cbox[j]);
                                    t[0]+=1;


                                }
                        }
                    }
                }



            for(int i=0;i<5;i++){
                att_showSub[i].setText(sub[i]);
                att_showAtt[i].setText( Integer.toString(att[i]) );
                att_showAttCls[i].setText(Integer.toString(attCls[i]) );
                att_showTtlCls[i].setText(Integer.toString(ttlCls[i]) );
                if(att[i]<75){
                    att_showAtt[i].setTextColor(Color.parseColor("#ff0000"));
                    att_showSub[i].setTextColor(Color.parseColor("#ff0000"));

                }else {
                    att_showAtt[i].setTextColor(Color.parseColor("#000000"));
                    att_showSub[i].setTextColor(Color.parseColor("#000000"));


                }
            }





            if(getArguments().getInt(ARG_SECTION_NUMBER)==1){
                //arr.setText("array");
                c0.setVisibility(View.VISIBLE);
                c.setVisibility(View.GONE);
                c2.setVisibility(View.GONE);


            }else if(getArguments().getInt(ARG_SECTION_NUMBER)==2){
                //arr.setText("B array");
                c0.setVisibility(View.GONE);
                c.setVisibility(View.VISIBLE);
                c2.setVisibility(View.GONE);

            }else{
                //arr.setText("C array");
                c0.setVisibility(View.GONE);
                c.setVisibility(View.GONE);
                c2.setVisibility(View.VISIBLE);

            }





            return rootView;


        }


    }


    //Integer att[] = {80,70,0,0,72};
    String sub[] = {"Maths","CSE","ED","Electronics","HSE"};
    //Integer ttlCls[] = {10,10,5,5,25};
    //Integer attCls[] = {8,7,0,0,18};



    public void mark(View v){

        TextView sub1;
        TextView home_att;
        TextView att_attcls;
        TextView att_ttlcls;
        TextView att_att;
        SharedPreferences data = this.getSharedPreferences("com.example.anirudh.mid.mid1707",MODE_PRIVATE);

        switch (v.getId()){
                case R.id.home_cb1:
                    CheckBox chk = (CheckBox)v;
                    sub1 = (TextView)findViewById(R.id.home_sub1);
                    chk.setChecked(!cbox[0]);
                    cbox[0]=!cbox[0];
                    data.edit().putBoolean("cbox0",cbox[0]).apply();
                    attCls[today_tt[0]]+=1;
                    ttlCls[today_tt[0]]+=1;
                    att[today_tt[0]]=100*attCls[today_tt[0]]/ttlCls[today_tt[0]];
                    data.edit().putInt("att"+today_tt[0],att[today_tt[0]]).apply();
                    data.edit().putInt("attCls"+today_tt[0],attCls[today_tt[0]]).apply();
                    data.edit().putInt("ttlCls"+today_tt[0],ttlCls[today_tt[0]]).apply();
                    home_showAtt[today_tt[0]].setText(Integer.toString(att[today_tt[0]]));
                    att_showAtt[today_tt[0]].setText(Integer.toString(att[today_tt[0]]));
                    att_showAttCls[today_tt[0]].setText(Integer.toString(attCls[today_tt[0]]));
                    att_showTtlCls[today_tt[0]].setText(Integer.toString(ttlCls[today_tt[0]]));

                    break;


                case R.id.home_cb2:
                    CheckBox chk2 = (CheckBox)v;
                    sub1 = (TextView)findViewById(R.id.home_sub2);
                    chk2.setChecked(!cbox[1]);
                    cbox[1]=!cbox[1];
                    data.edit().putBoolean("cbox",cbox[1]).apply();
                    attCls[today_tt[1]]+=1;
                    ttlCls[today_tt[1]]+=1;
                    att[today_tt[1]]=100*attCls[today_tt[1]]/ttlCls[today_tt[1]];
                    data.edit().putInt("att"+today_tt[1],att[today_tt[1]]).apply();
                    data.edit().putInt("attCls"+today_tt[1],attCls[today_tt[1]]).apply();
                    data.edit().putInt("ttlCls"+today_tt[1],ttlCls[today_tt[1]]).apply();
                    home_showAtt[today_tt[1]].setText(Integer.toString(att[today_tt[1]]));
                    att_showAtt[today_tt[1]].setText(Integer.toString(att[today_tt[1]]));
                    att_showAttCls[today_tt[1]].setText(Integer.toString(attCls[today_tt[1]]));
                    att_showTtlCls[today_tt[1]].setText(Integer.toString(ttlCls[today_tt[1]]));
                    break;
                case R.id.home_cb3:
                    CheckBox chk3 = (CheckBox)v;
                    sub1 = (TextView)findViewById(R.id.home_sub3);
                    home_att = (TextView)findViewById(R.id.home_att3);
                    att_attcls = (TextView)findViewById(R.id.att_attCls3);
                    att_ttlcls = (TextView)findViewById(R.id.att_ttlCls3);
                    att_att = (TextView)findViewById(R.id.att_att3);
                    chk3.setChecked(!cbox[2]);
                    cbox[2]=!cbox[2];
                    data.edit().putBoolean("cbox2",cbox[2]).apply();
                    attCls[today_tt[2]]+=1;
                    ttlCls[today_tt[2]]+=1;
                    att[today_tt[2]]=100*attCls[today_tt[2]]/ttlCls[today_tt[2]];
                    data.edit().putInt("att"+today_tt[2],att[today_tt[2]]).apply();
                    data.edit().putInt("attCls"+today_tt[2],attCls[today_tt[2]]).apply();
                    data.edit().putInt("ttlCls"+today_tt[2],ttlCls[today_tt[2]]).apply();
                    home_showAtt[today_tt[2]].setText(Integer.toString(att[today_tt[2]]));
                    att_showAtt[today_tt[2]].setText(Integer.toString(att[today_tt[2]]));
                    att_showAttCls[today_tt[2]].setText(Integer.toString(attCls[today_tt[2]]));
                    att_showTtlCls[today_tt[2]].setText(Integer.toString(ttlCls[today_tt[2]]));
                    break;
                case R.id.home_cb4:
                    CheckBox chk4 = (CheckBox)v;
                    sub1 = (TextView)findViewById(R.id.home_sub4);
                    home_att = (TextView)findViewById(R.id.home_att4);
                    att_attcls = (TextView)findViewById(R.id.att_attCls4);
                    att_ttlcls = (TextView)findViewById(R.id.att_ttlCls4);
                    att_att = (TextView)findViewById(R.id.att_att4);
                    chk4.setChecked(!cbox[3]);
                    cbox[3]=!cbox[3];
                    data.edit().putBoolean("cbox3",cbox[3]).apply();
                    attCls[today_tt[3]]+=1;
                    ttlCls[today_tt[3]]+=1;
                    att[today_tt[3]]=100*attCls[today_tt[3]]/ttlCls[today_tt[3]];
                    data.edit().putInt("att"+today_tt[3],att[today_tt[3]]).apply();
                    data.edit().putInt("attCls"+today_tt[3],attCls[today_tt[3]]).apply();
                    data.edit().putInt("ttlCls"+today_tt[3],ttlCls[today_tt[3]]).apply();
                    home_showAtt[today_tt[3]].setText(Integer.toString(att[today_tt[3]]));
                    att_showAtt[today_tt[3]].setText(Integer.toString(att[today_tt[3]]));
                    att_showAttCls[today_tt[3]].setText(Integer.toString(attCls[today_tt[3]]));
                    att_showTtlCls[today_tt[3]].setText(Integer.toString(ttlCls[today_tt[3]]));
                    break;
                case R.id.home_cb5:
                    CheckBox chk5 = (CheckBox)v;
                    sub1 = (TextView)findViewById(R.id.home_sub5);
                    home_att = (TextView)findViewById(R.id.home_att5);
                    att_attcls = (TextView)findViewById(R.id.att_attCls5);
                    att_ttlcls = (TextView)findViewById(R.id.att_ttlCls5);
                    att_att = (TextView)findViewById(R.id.att_att5);
                    chk5.setChecked(!cbox[4]);
                    cbox[4]=!cbox[4];
                    data.edit().putBoolean("cbox4",cbox[4]).apply();
                    attCls[today_tt[4]]+=1;
                    ttlCls[today_tt[4]]+=1;
                    att[today_tt[4]]=100*attCls[today_tt[4]]/ttlCls[today_tt[4]];
                    data.edit().putInt("att"+today_tt[4],att[today_tt[4]]).apply();
                    data.edit().putInt("attCls"+today_tt[4],attCls[today_tt[4]]).apply();
                    data.edit().putInt("ttlCls"+today_tt[4],ttlCls[today_tt[4]]).apply();
                    home_showAtt[today_tt[4]].setText(Integer.toString(att[today_tt[4]]));
                    att_showAtt[today_tt[4]].setText(Integer.toString(att[today_tt[4]]));
                    att_showAttCls[today_tt[4]].setText(Integer.toString(attCls[today_tt[4]]));
                    att_showTtlCls[today_tt[4]].setText(Integer.toString(ttlCls[today_tt[4]]));
                    break;
                case R.id.home_cb6:
                    CheckBox chk6 = (CheckBox)v;
                    sub1 = (TextView)findViewById(R.id.home_sub6);
                    home_att = (TextView)findViewById(R.id.home_att1);
                    att_attcls = (TextView)findViewById(R.id.att_attCls1);
                    att_ttlcls = (TextView)findViewById(R.id.att_ttlCls1);
                    att_att = (TextView)findViewById(R.id.att_att1);
                    chk6.setChecked(!cbox[5]);
                    cbox[5]=!cbox[5];
                    data.edit().putBoolean("cbox5",cbox[5]).apply();
                    attCls[today_tt[5]]+=1;
                    ttlCls[today_tt[5]]+=1;
                    att[today_tt[5]]=100*attCls[today_tt[5]]/ttlCls[today_tt[5]];
                    data.edit().putInt("att"+today_tt[5],att[today_tt[5]]).apply();
                    data.edit().putInt("attCls"+today_tt[5],attCls[today_tt[5]]).apply();
                    data.edit().putInt("ttlCls"+today_tt[5],ttlCls[today_tt[5]]).apply();
                    home_showAtt[today_tt[5]].setText(Integer.toString(att[today_tt[5]]));
                    att_showAtt[today_tt[5]].setText(Integer.toString(att[today_tt[5]]));
                    att_showAttCls[today_tt[5]].setText(Integer.toString(attCls[today_tt[5]]));
                    att_showTtlCls[today_tt[5]].setText(Integer.toString(ttlCls[today_tt[5]]));
                    break;
                case R.id.home_cb7:
                    CheckBox chk7 = (CheckBox)v;
                    sub1 = (TextView)findViewById(R.id.home_sub7);
                    chk7.setChecked(!cbox[6]);
                    cbox[6]=!cbox[6];
                    data.edit().putBoolean("cbox6",cbox[6]).apply();
                    attCls[today_tt[6]]+=1;
                    ttlCls[today_tt[6]]+=1;
                    att[today_tt[6]]=100*attCls[today_tt[6]]/ttlCls[today_tt[6]];
                    data.edit().putInt("att"+today_tt[6],att[today_tt[6]]).apply();
                    data.edit().putInt("attCls"+today_tt[6],attCls[today_tt[6]]).apply();
                    data.edit().putInt("ttlCls"+today_tt[6],ttlCls[today_tt[6]]).apply();
                    home_showAtt[today_tt[6]].setText(Integer.toString(att[today_tt[6]]));
                    att_showAtt[today_tt[6]].setText(Integer.toString(att[today_tt[6]]));
                    att_showAttCls[today_tt[6]].setText(Integer.toString(attCls[today_tt[6]]));
                    att_showTtlCls[today_tt[6]].setText(Integer.toString(ttlCls[today_tt[6]]));
                    break;
                case R.id.home_cb8:
                    CheckBox chk8 = (CheckBox)v;
                    sub1 = (TextView)findViewById(R.id.home_sub8);
                    chk8.setChecked(!cbox[7]);
                    cbox[7]=!cbox[7];
                    data.edit().putBoolean("cbox7",cbox[7]).apply();
                    attCls[today_tt[7]]+=1;
                    ttlCls[today_tt[7]]+=1;
                    att[today_tt[7]]=100*attCls[today_tt[7]]/ttlCls[today_tt[7]];
                    data.edit().putInt("att"+today_tt[7],att[today_tt[7]]).apply();
                    data.edit().putInt("attCls"+today_tt[7],attCls[today_tt[7]]).apply();
                    data.edit().putInt("ttlCls"+today_tt[7],ttlCls[today_tt[7]]).apply();
                    home_showAtt[today_tt[7]].setText(Integer.toString(att[today_tt[7]]));
                    att_showAtt[today_tt[7]].setText(Integer.toString(att[today_tt[7]]));
                    att_showAttCls[today_tt[7]].setText(Integer.toString(attCls[today_tt[7]]));
                    att_showTtlCls[today_tt[7]].setText(Integer.toString(ttlCls[today_tt[7]]));
                    break;
                default:
                    //CheckBox chk2 = (CheckBox)v;
                    sub1 = (TextView)findViewById(R.id.home_sub1);
                    break;


            }

            String data_sub = (String) sub1.getText();
            int data_index = 0;
            for(data_index=0; data_index<5 ;data_index++){
                if(sub[data_index]==data_sub){
                    break;
                }

            //attCls[data_index] += 1 ;
            //ttlCls[data_index] +=1;
            //att[data_index] = ttlCls[data_index]/attCls[data_index];

            switch (data_index){
                case 0:

                    home_att = (TextView)findViewById(R.id.home_att1);
                    att_attcls = (TextView)findViewById(R.id.att_attCls1);
                    att_ttlcls = (TextView)findViewById(R.id.att_ttlCls1);
                    att_att = (TextView)findViewById(R.id.att_att1);

                case 1:

                    home_att = (TextView)findViewById(R.id.home_att2);
                    att_attcls = (TextView)findViewById(R.id.att_attCls2);
                    att_ttlcls = (TextView)findViewById(R.id.att_ttlCls2);
                    att_att = (TextView)findViewById(R.id.att_att2);

                    default:

                        home_att = (TextView)findViewById(R.id.home_att3);
                        att_attcls = (TextView)findViewById(R.id.att_attCls3);
                        att_ttlcls = (TextView)findViewById(R.id.att_ttlCls3);
                        att_att = (TextView)findViewById(R.id.att_att3);
            }

            int data_att = att[data_index];
            //home_att.setText(att[data_index]);
            //att_attcls.setText(attCls[data_index]);
            //att_ttlcls.setText(ttlCls[data_index]);
            //att_att.setText(att[data_index]);
            boolean b = data_sub=="Maths";
            Snackbar.make(v , "Marking attendance  "+data_sub+Integer.toString(data_index)+b, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();





            }


        Log.i("Marking", "att :"+att[0]+" "+att[1]+" "+att[2]+" "+att[3]+" "+att[4]);

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

}

