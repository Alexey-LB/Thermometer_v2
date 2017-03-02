package com.relsib.lesa.graph;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AMain extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener
        //,OnChartGestureListener, OnChartValueSelectedListener
{
    private LineChart mChart;
    private SeekBar mSeekBarValues;
    private TextView mTvCount;
    private int start = 100;
    private float temp = 40f;
    //из галереи УРЛ картинки
    private Uri uri = Uri.parse("content://media/external/images/media/417");
    private String tableHtml = "<?xml version=1.1 encoding=UTF-8?>" +
            "<!DOCTYPE HTML><html lang=en-RU>" +
            "<head><title>Temperature diagram (2017-02-24 09:58:51)</title>" +
            "<style>table{font-size:12pt;} td{text-align:center;}</style></head>" +
            "<body><table border=1 cellspacing=0 cellpadding=3>" +
            "<tr><th>Time, s</th><th>Temperature, ˚C</th><th>Temperature, ˚F</th>" +
            "</tr><tr><td>00:00:0,0</td><td>28,0</td><td>82,4</td></tr>" +
            "<tr><td>00:00:4,4</td><td>28,0</td><td>82,4</td></tr>" +
            "<tr><td>00:00:5,8</td><td>27,9</td><td>82,2</td></tr>" +
            "<tr><td>00:00:6,8</td><td>27,8</td><td>82,0</td></tr>" +
            "</table></body></html>";
    private String tableHtml2 = "<em>iujkuhiukiukjjk\nrr\n\n</em>";

    private String tableHtml3 = "<div><table border=1 cellspacing=0 cellpadding=3><tbody><tr><th>Time, s</th><th>Temperature, ˚C</th><th>Temperature, ˚F</th></tr><tr><td>00:00:0,0</td><td>28,0</td><td>82,4</td></tr><tr><td>00:00:4,4</td><td>28,0</td><td>82,4</td></tr><tr><td>00:00:5,8</td><td>27,9</td><td>82,2</td></tr><tr><td>00:00:6,8</td><td>27,8</td><td>82,0</td></tr><tr><td>00:00:7,8</td><td>27,7</td><td>81,9</td></tr><tr><td>00:00:8,8</td><td>27,6</td><td>81,7</td></tr><tr><td>00:00:9,8</td><td>27,5</td><td>81,5</td></tr><tr><td>00:00:10,8</td><td>27,4</td><td>81,3</td></tr><tr><td>00:00:11,8</td><td>27,3</td><td>81,1</td></tr><tr><td>00:00:12,8</td><td>27,2</td><td>81,0</td></tr><tr><td>00:00:13,8</td><td>27,1</td><td>80,8</td></tr><tr><td>00:00:14,8</td><td>27,0</td><td>80,6</td></tr><tr><td>00:00:15,8</td><td>26,9</td><td>80,4</td></tr><tr><td>00:00:16,8</td><td>26,8</td><td>80,2</td></tr><tr><td>00:00:18,8</td><td>26,7</td><td>80,1</td></tr><tr><td>00:00:19,8</td><td>26,6</td><td>79,9</td></tr><tr><td>00:00:20,8</td><td>26,5</td><td>79,7</td></tr><tr><td>00:00:21,8</td><td>26,4</td><td>79,5</td></tr><tr><td>00:00:22,8</td><td>26,3</td><td>79,3</td></tr><tr><td>00:00:24,8</td><td>26,2</td><td>79,2</td></tr><tr><td>00:00:25,8</td><td>26,1</td><td>79,0</td></tr><tr><td>00:00:27,8</td><td>25,9</td><td>78,6</td></tr><tr><td>00:00:29,8</td><td>25,8</td><td>78,4</td></tr><tr><td>00:00:31,8</td><td>25,7</td><td>78,3</td></tr><tr><td>00:00:32,8</td><td>25,6</td><td>78,1</td></tr><tr><td>00:00:34,8</td><td>25,5</td><td>77,9</td></tr><tr><td>00:00:35,8</td><td>25,3</td><td>77,5</td></tr><tr><td>00:00:38,8</td><td>25,2</td><td>77,4</td></tr><tr><td>00:00:39,8</td><td>25,1</td><td>77,2</td></tr><tr><td>00:00:41,8</td><td>25,0</td><td>77,0</td></tr><tr><td>00:00:42,7</td><td>24,9</td><td>76,8</td></tr><tr><td>00:00:44,7</td><td>24,8</td><td>76,6</td></tr><tr><td>00:00:46,7</td><td>24,7</td><td>76,5</td></tr><tr><td>00:00:48,7</td><td>24,6</td><td>76,3</td></tr><tr><td>00:00:50,7</td><td>24,5</td><td>76,1</td></tr><tr><td>00:00:52,7</td><td>24,4</td><td>75,9</td></tr><tr><td>00:00:55,7</td><td>24,3</td><td>75,7</td></tr><tr><td>00:00:57,7</td><td>24,2</td><td>75,6</td></tr><tr><td>00:00:59,7</td><td>24,1</td><td>75,4</td></tr><tr><td>00:01:2,7</td><td>24,0</td><td>75,2</td></tr><tr><td>00:01:4,7</td><td>23,9</td><td>75,0</td></tr><tr><td>00:01:6,7</td><td>23,8</td><td>74,8</td></tr><tr><td>00:01:10,7</td><td>23,7</td><td>74,7</td></tr><tr><td>00:01:11,7</td><td>23,6</td><td>74,5</td></tr><tr><td>00:01:15,7</td><td>23,5</td><td>74,3</td></tr><tr><td>00:01:17,7</td><td>23,4</td><td>74,1</td></tr><tr><td>00:01:20,7</td><td>23,3</td><td>73,9</td></tr><tr><td>00:01:24,6</td><td>23,2</td><td>73,8</td></tr><tr><td>00:01:26,6</td><td>23,1</td><td>73,6</td></tr><tr><td>00:01:30,6</td><td>23,0</td><td>73,4</td></tr><tr><td>00:01:33,6</td><td>22,9</td><td>73,2</td></tr><tr><td>00:01:37,6</td><td>22,8</td><td>73,0</td></tr><tr><td>00:01:41,6</td><td>22,8</td><td>73,0</td></tr><tr><td>00:01:42,6</td><td>22,7</td><td>72,9</td></tr><tr><td>00:01:45,6</td><td>22,6</td><td>72,7</td></tr><tr><td>00:01:49,6</td><td>22,6</td><td>72,7</td></tr><tr><td>00:01:51,1</td><td>22,5</td><td>72,5</td></tr><tr><td>00:01:53,6</td><td>22,4</td><td>72,3</td></tr><tr><td>00:01:57,6</td><td>22,4</td><td>72,3</td></tr><tr><td>00:02:0,6</td><td>22,3</td><td>72,1</td></tr><tr><td>00:02:4,5</td><td>22,3</td><td>72,1</td></tr><tr><td>00:02:6,5</td><td>22,2</td><td>72,0</td></tr><tr><td>00:02:10,5</td><td>22,1</td><td>71,8</td></tr><tr><td>00:02:15,0</td><td>22,1</td><td>71,8</td></tr><tr><td>00:02:19,0</td><td>22,0</td><td>71,6</td></tr><tr><td>00:02:22,0</td><td>21,9</td><td>71,4</td></tr><tr><td>00:02:30,0</td><td>21,9</td><td>71,4</td></tr><tr><td>00:02:31,0</td><td>21,8</td><td>71,2</td></tr><tr><td>00:02:39,0</td><td>21,8</td><td>71,2</td></tr><tr><td>00:02:42,0</td><td>21,7</td><td>71,1</td></tr><tr><td>00:02:45,9</td><td>21,6</td><td>70,9</td></tr><tr><td>00:02:57,9</td><td>21,6</td><td>70,9</td></tr><tr><td>00:03:0,9</td><td>21,5</td><td>70,7</td></tr><tr><td>00:03:4,9</td><td>21,5</td><td>70,7</td></tr><tr><td>00:03:5,9</td><td>21,4</td><td>70,5</td></tr><tr><td>00:03:17,9</td><td>21,4</td><td>70,5</td></tr><tr><td>00:03:18,9</td><td>21,3</td><td>70,3</td></tr><tr><td>00:03:19,9</td><td>21,4</td><td>70,5</td></tr><tr><td>00:03:20,9</td><td>21,3</td><td>70,3</td></tr><tr><td>00:03:36,8</td><td>21,3</td><td>70,3</td></tr><tr><td>00:03:39,8</td><td>21,2</td><td>70,2</td></tr><tr><td>00:03:43,8</td><td>21,2</td><td>70,2</td></tr><tr><td>00:03:47,8</td><td>21,1</td><td>70,0</td></tr><tr><td>00:03:49,9</td><td>21,2</td><td>70,2</td></tr><tr><td>00:03:50,8</td><td>21,1</td><td>70,0</td></tr><tr><td>00:04:10,3</td><td>21,1</td><td>70,0</td></tr></tbody></table></div>";
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        //==================================================================================
        activity = this;
        mChart = (LineChart) findViewById(R.id.chart1);
        mTvCount = (TextView) findViewById(R.id.tvValueCount);
        mSeekBarValues = (SeekBar) findViewById(R.id.seekbarValues);
        mTvCount.setText(start + " ");
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //запись в галерею картики изображения
                //   Log.v("=== SAVE = "," " + mChart.saveToGallery("saveImeg2.jpg",85));
                //переход на выбор из галереи УРЛ картинки выбранной
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }

                mChart.invalidate();
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//-----------------------------------------
                //запись
                Log.v("=== SAVE = "," " + mChart.saveToGallery("saveImeg2.jpg",85));
//                try {
//                    OutputStream outputStream = openFileOutput("file.html",0);
//                    OutputStreamWriter osw = new OutputStreamWriter(outputStream);
//                    osw.write(tableHtml);
//                    osw.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                //uri= /storage/emulated/0/Documents/file.html - так выдает на АСУС
                String dir = Environment.getExternalStorageDirectory().toString() +"/"+ Environment.DIRECTORY_DOCUMENTS;
                //     Log.i("write"," dir= " +dir);
                File folder = new File(dir);
                if(!folder.exists())folder.mkdir();
                uri = Uri.parse(dir + "/"+"file2.html");
                //--
                //  File file = new File(dir,"file.html");
                File file = new File(uri.toString());
                Log.i("write"," dir= " +dir + "   uri= "+ uri.toString());
                try {
                    FileOutputStream osw = new FileOutputStream(file);
                    osw.write(tableHtml.getBytes());
                    osw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //
                file = new File(dir,"file2.html");
                try {
                    FileInputStream fi = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fi);
                    BufferedReader read = new BufferedReader(isr);
                    String str;
                    StringBuffer buffer = new StringBuffer();
                    while ((str = read.readLine()) != null){
                        buffer.append(str+"\n");
                    }
                    fi.close();
                    Log.v("-- read file> ",buffer.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                SharedPreferences mSharedPref =getSharedPreferences("pref",MODE_PRIVATE);
//                SharedPreferences.Editor mEditor = mSharedPref.edit();
//                mEditor.putString("string1", " text test");
//                mEditor.commit();

                //            Log.v("=== SAVE = "," " + mChart.saveToGallery("saveImeg2.jpg",85));
// h Connecting to a new host gitlab.com:22 that has the key b6:03:0e:39:97:9e:d0:e7:24:ce:a3:77:3e:01:42:09 (type ssh-rsa). Do you want to add this host to known hosts database?
//============================================================================================
                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                //emailIntent.setType("plain/text");
                emailIntent.setType("text/html");
                //    emailIntent.setType("*/*");
                //  emailIntent.setType("message/rfc822");
                //    emailIntent.setType("application/octet-stream");
                // Кому
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        //    new String[] { address.getText().toString() });
                        new String[] {"9z@bk.ru"});
                // ТЕМА: Зачем
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        //subject.getText().toString());
                        "subject.test3");
                //      emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Spanned text = new SpannableString("This is underline and bold text.");
                // ТЕКСТ письма : О чём

                emailIntent.putExtra(
                        Intent.EXTRA_HTML_TEXT,
                      //  "text mail:"

                       // emailtext.getText().toString());
                       // "файл прикреплен к письму"
                        Html.fromHtml(
                        tableHtml3
                        )
                        //Html.toHtml(text)
                       );
                Log.i("-- HTML= ", Html.toHtml(text));

                emailIntent.putExtra(Intent.EXTRA_TEXT, "тест письма:");

                //(URI.parse("file://mnt/sdcard/cat.jpg"));
                //     (URI.parse("content://media/external/images/media/417);//на асус телефоне
//                // ПРИКРЕПИТЬ ФАЙЛ:С чем

                emailIntent.putExtra(
                        android.content.Intent.EXTRA_STREAM,
                        //К ПАМЯТИ ПРИЛОЖЕНИЯ нет ДОСТУПА внешним программам,
                        // например ПОЧТЫ- обламывает чтение!
                        //только так! получается "file:///storage/emulated/0/Documents/file2.html"
                        //3!! наклонных после "file:///"- иначе облом
                        Uri.parse(
                                "file://"
                                        +Environment.getExternalStorageDirectory()
                                        +"/"+ Environment.DIRECTORY_DOCUMENTS
                                        +"/file2.html")

                        //    Uri.parse("file://"
                        //           + Environment.getExternalStorageDirectory()
                        //           + "/Клипы/SOTY_ATHD.mp4")
                );
//
                //    emailIntent.setType("text/video");
                //    emailIntent.setType("text/html");
                //     emailIntent.setType("image/*");
                // Поехали!
                //SimpleEMail.this.
                startActivity(Intent.createChooser(emailIntent,
                        "Отправка письма..."));
                //===========================================================
                //-------------ВАРИАНТ №2
//                ShareCompat.IntentBuilder.from(activity)
//                        //.setType("message/rfc822")
//                        .setType("text/html")
//                        .addEmailTo("9z@bk.ru")
//                        .setSubject("subject")
//                       // .setText(tableHtml3)
//                        .setHtmlText(tableHtml3) //If you are using HTML in your body text
//                        //   .setChooserTitle(chooserTitle)
//                        .startChooser();


                //--------
            }
        });


        mSeekBarValues.setProgress(start);

        mSeekBarValues.setOnSeekBarChangeListener(this);
//--


//        mChart.setOnChartGestureListener(this);
//        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawGridBackground(false);

        // no description text
        mChart.getDescription().setEnabled(false);
        //mChart.getDescription().setEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(true);
        mChart.getAxisRight().setEnabled(false);
        mChart.getXAxis().setDrawGridLines(true);


        mChart.getXAxis().setDrawAxisLine(false);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        //маркер ОТОБРАЖЕНИЯ ТЕКУЩЕГО, ВЫБРАННОГО ЗНАЧЕНИЯ!! в перекрестии прямых линий
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

 setData(input1.length, temp,input1);
 //setData(start, temp, null);

       // dont forget to refresh the drawing
        mChart.invalidate();
        mChart.animateY(3000);
        mChart.animateX(3000);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.line, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }

                mChart.invalidate();
                break;
            }
//            case R.id.actionToggleIcons: {
//                List<ILineDataSet> sets = mChart.getData()
//                        .getDataSets();
//
//                for (ILineDataSet iSet : sets) {
//
//                    LineDataSet set = (LineDataSet) iSet;
//                    set.setDrawIcons(!set.isDrawIconsEnabled());
//                }
//
//                mChart.invalidate();
//                break;
//            }
            case R.id.actionToggleHighlight: {
                if(mChart.getData() != null) {
                    mChart.getData().setHighlightEnabled(!mChart.getData().isHighlightEnabled());
                    mChart.invalidate();
                }
                break;
            }
            case R.id.actionToggleFilled: {

                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleCircles: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawCirclesEnabled())
                        set.setDrawCircles(false);
                    else
                        set.setDrawCircles(true);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleCubic: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.CUBIC_BEZIER);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleStepped: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.STEPPED
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.STEPPED);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHorizontalCubic: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            :  LineDataSet.Mode.HORIZONTAL_BEZIER);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionTogglePinch: {
                if (mChart.isPinchZoomEnabled())
                    mChart.setPinchZoom(false);
                else
                    mChart.setPinchZoom(true);

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                mChart.setAutoScaleMinMaxEnabled(!mChart.isAutoScaleMinMaxEnabled());
                mChart.notifyDataSetChanged();
                break;
            }
            case R.id.animateX: {
                mChart.animateX(3000);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(3000, Easing.EasingOption.EaseInCubic);
                break;
            }
            case R.id.animateXY: {
                mChart.animateXY(3000, 3000);
                break;
            }
            case R.id.actionSave: {
                if (mChart.saveToPath("title" + System.currentTimeMillis(), "")) {
                    Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                            .show();

                // mChart.saveToGallery("title"+System.currentTimeMillis())
                break;
            }
        }
        return true;
    }
    static final int GALLERY_REQUEST = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);


        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    uri = imageReturnedIntent.getData();
                    Log.i("------- url: ",uri.toString());
                    //     content://media/external/images/media/417
                }
        }
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        int count = mSeekBarValues.getProgress() + start;
        mTvCount.setText("" + count);

        mChart.resetTracking();

        setData(count, temp, null);

        // redraw
        mChart.invalidate();
        Log.i("gr "," Width= "+ mChart.getWidth()+"  Height= "+mChart.getHeight()
                //определяет какую часть отображаем и начало отображения
                + " \n Left= "+ mChart.getHighestVisibleX()+"  Right= "+mChart.getLowestVisibleX()
                //диапазон выводимый на экран оси х
                + " \n Range= "+ mChart.getVisibleXRange()+"  Count= "+mChart.getMaxVisibleCount()
                + " \n Matrix= "+ mChart.getMatrix().toString());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
private int test1=0;
    private void setData(int count, float range, float[][] in) {
        test1=0;
        int i,j,n;float val,mult;Entry en;
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        n = count/200;
        if(n == 0) n = 1;


        for (i = 0; i < count; i++) {
            //mult = (range + 1);
            if(in == null) {
                val = (float) (Math.random() * (range + 1)) + 3;

//            val = range + range/5;
//            if((i % 1000) == 0)val -= range;
//            if((i % 1000) == 1)val -= range/3;
//            if((i % 1000) == 2)val -= range/2;

                // + (float)
                // ((mult *
                // 0.1) / 10);
                yVals.add(new Entry(i * 1f, val));
            } else{
                yVals.add(new Entry(in[i][0] * 60 + in[i][1], in[i][2]));
               //ПРИЦЕПИТЬ иконку в точке полученного значения
                // yVals.add(new Entry(in[i][0] * 60 + in[i][1], in[i][2],getResources().getDrawable(R.drawable.star)));
            }
        }



        for (i = 0; i < (count - n); i +=n) {
            val = yVals.get(i).getY();
            mult = val;
            //нашли максимум
            for(j = 1; j < n; j++){
                en = yVals.get(i+j);
                if(en.getY() > val)val = en.getY();
                if(en.getY() < mult) mult = en.getY();
            }
            //заполняем значения MAX
            yVals2.add(new Entry(yVals.get(i).getX(), val));
            yVals3.add(new Entry(yVals.get(i).getX(), mult));
            if(n > 1){
                yVals2.add(new Entry(yVals.get(i+j -1).getX(), val));
                yVals3.add(new Entry(yVals.get(i+j -1).getX(), mult));
            }
        }


        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        //линия прерывистая, ввиде тире
      //  set1.enableDashedLine(10f, 5f, 0f);
     //   set1.enableDashedHighlightLine(10f, 5f, 0f);

        set1.setColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setDrawValues(false);
        set1.setDrawCircles(false);
        set1.setMode(LineDataSet.Mode.LINEAR);
        set1.setDrawFilled(false);

        set1.setCircleColor(Color.BLACK);

        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);

    //    set1.setHighlightEnabled(true);
    //    set1.setDrawHighlightIndicators(true);

        // create a data object with the datasets
        //     LineData data = new LineData(set1);

        // set data
        //     mChart.setData(data);
        //----------------------
        // create a dataset and give it a type
        LineDataSet set2 = new LineDataSet(yVals2, "DataSet 2");

        set2.setColor(0x4de2c2);//Color.RED
        set2.setLineWidth(0.5f);
        set2.setDrawValues(false);
        set2.setDrawCircles(false);
        set2.setMode(LineDataSet.Mode.LINEAR);
        //set2.setDrawFilled(false);
//        set2.setDrawFilled(true);
//        set2.setFillAlpha(128);
//        set2.setFillColor(0x4de2c2);//Color.YELLOW
//        set2.setFillFormatter(new IFillFormatter() {
//            @Override
//            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
//                test1++;
//         //       if(test1 < 10) Log.v("Graph= ",dataSet.toString() +"  pr= "+dataProvider.toString());
//                return mChart.getAxisLeft().getAxisMinimum();
//              //  return dataProvider.getLineData().getDataSets();
//            }
//        });
        //--
        LineDataSet set3 = new LineDataSet(yVals3, "DataSet 3");


        set3.setColor(0x4de2c2);//Color.BLUE
        set3.setLineWidth(0.5f);
        set3.setDrawValues(false);
        set3.setDrawCircles(false);
        set3.setMode(LineDataSet.Mode.LINEAR);

        //set3.setDrawFilled(false);
//        set3.setDrawFilled(true);
//        set3.setFillColor(0xFFFFFFFF);
//        set3.setFillAlpha(255);
//        set3.setFillFormatter(new IFillFormatter() {
//            @Override
//            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
//                return mChart.getAxisLeft().getAxisMinimum();
//            }
//        });
        // create a data object with the datasets
        //    LineData data2 = new LineData(set2);
        //-------------------
        LimitLine line = new LimitLine(100f);
        line.setLineColor(Color.CYAN);
        line.setLineWidth(2f);
        //-------------------
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        //отрисовывает 1
        if(in == null) {
            dataSets.add(set2); // add the datasets
            dataSets.add(set3); // add the datasets
        }
        //отрисовывает после 1
        dataSets.add(set1);

        // create a data object with the datasets
        LineData data = new LineData(dataSets);
        data.setDrawValues(false);
        //-----------------------
        // set data
        mChart.setData(data);
       // mChart.getData().setHighlightEnabled(true);
       // mChart.getData().setDrawHighlightIndicators(true);
        //---------
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        l.setEnabled(false);
    }

//    @Override
//    public void onChartGestureStart(MotionEvent motionEvent, ChartTouchListener.ChartGesture chartGesture) {
//
//    }
//
//    @Override
//    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
//        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);
//
//        // un-highlight values after the gesture is finished and no single-tap
//        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
//            mChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
//    }
//
//    @Override
//    public void onChartLongPressed(MotionEvent motionEvent) {
//
//    }
//
//    @Override
//    public void onChartDoubleTapped(MotionEvent motionEvent) {
//
//    }
//
//    @Override
//    public void onChartSingleTapped(MotionEvent motionEvent) {
//
//    }
//
//    @Override
//    public void onChartFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//
//    }
//
//    @Override
//    public void onChartScale(MotionEvent motionEvent, float v, float v1) {
//
//    }
//
//    @Override
//    public void onChartTranslate(MotionEvent motionEvent, float v, float v1) {
//
//    }
//
//    @Override
//    public void onValueSelected(Entry e, Highlight h) {
//        Log.i("Entry selected", e.toString());
//        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleX() + ", high: " + mChart.getHighestVisibleX());
//        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin() + ", xmax: " + mChart.getXChartMax() + ", ymin: " + mChart.getYChartMin() + ", ymax: " + mChart.getYChartMax());
//    }
//
//    @Override
//    public void onNothingSelected() {
//
//    }
    
    private float[][] input1 = {{00f,00.0f,21.2f
           },{00f,14.4f,21.2f
           },{00f,16.9f,21.3f
           },{00f,17.4f,21.4f
           },{00f,18.4f,21.8f
           },{00f,19.4f,22.4f
           },{00f,20.4f,23.3f
           },{00f,21.4f,24.1f
           },{00f,22.9f,24.9f
           },{00f,23.9f,25.8f
           },{00f,24.9f,26.5f
           },{00f,25.9f,27.3f
           },{00f,26.9f,27.9f
           },{00f,27.9f,28.4f
           },{00f,28.9f,29f
           },{00f,29.9f,29.5f
           },{00f,30.9f,29.9f
           },{00f,31.9f,30.3f
           },{00f,32.9f,30.7f
           },{00f,33.9f,31.1f
           },{00f,34.9f,31.3f
           },{00f,35.9f,31.6f
           },{00f,36.9f,31.9f
           },{00f,37.9f,32.3f
           },{00f,38.9f,32.4f
           },{00f,39.9f,32.6f
           },{00f,40.9f,32.8f
           },{00f,41.9f,32.9f
           },{00f,42.9f,33.1f
           },{00f,43.9f,33.2f
           },{00f,44.9f,33.3f
           },{00f,45.9f,33.4f
           },{00f,47.8f,33.6f
           },{00f,49.8f,33.7f
           },{00f,50.8f,33.8f
           },{00f,52.8f,33.9f
           },{00f,54.8f,34f
           },{00f,55.8f,34.1f
           },{00f,59.8f,34.2f
           },{01f,01.8f,34.3f
           },{01f,04.8f,34.4f
           },{01f,08.8f,34.4f
           },{01f,11.8f,34.5f
           },{01f,14.8f,34.6f
           },{01f,18.8f,34.6f
           },{01f,22.8f,34.7f
           },{01f,26.8f,34.7f
           },{01f,28.7f,34.8f
           },{01f,40.7f,34.8f
           },{01f,41.7f,34.9f
           },{01f,53.7f,34.9f
           },{01f,56.7f,35f
           },{02f,00.7f,35f
           },{02f,03.7f,35.1f
           },{02f,19.6f,35.1f
           },{02f,22.6f,35.2f
           },{02f,30.6f,35.2f
           },{02f,34.6f,35.3f
           },{03f,07.0f,35.3f
           },{03f,09.0f,35.4f
           },{03f,44.9f,35.4f
           },{03f,47.9f,35.5f
           },{03f,48.9f,35.4f
           },{03f,49.9f,35.5f
           },{04f,05.9f,35.5f
           },{04f,06.9f,35.6f
           },{04f,58.7f,35.6f
           },{05f,00.7f,35.7f
           },{05f,28.7f,35.7f
           },{05f,31.6f,35.8f
           },{05f,34.6f,35.7f
           },{05f,35.6f,35.8f
           },{06f,44.0f,35.8f
           },{06f,47.0f,35.9f
           },{06f,48.0f,35.8f
           },{06f,51.9f,35.8f
           },{06f,55.9f,35.9f
           },{06f,56.9f,35.8f
           },{06f,58.9f,35.9f
           },{08f,06.8f,35.9f
           },{08f,07.8f,36f
           },{08f,09.8f,35.9f
           },{08f,13.7f,35.9f
           },{08f,17.7f,36f
           },{08f,54.1f,36f
           },{08f,58.1f,36.1f
           },{09f,00.1f,36f
           },{09f,02.1f,36.1f
           },{09f,03.1f,36f
           },{09f,04.1f,36.1f
           },{10f,19.9f,36.1f
           },{10f,22.9f,35.9f
           },{10f,23.9f,35.7f
           },{10f,24.9f,35.4f
           },{10f,25.9f,35.1f
           },{10f,26.9f,34.8f
           },{10f,27.9f,34.6f
           },{10f,28.9f,34.3f
           },{10f,29.9f,34f
           },{10f,30.9f,33.7f
           },{10f,31.9f,33.4f
           },{10f,32.9f,33.1f
           },{10f,33.9f,32.6f
           },{10f,34.9f,32.3f
           },{10f,35.9f,32.1f
           },{10f,36.9f,31.8f
           },{10f,37.9f,31.6f
           },{10f,38.9f,31.4f
           },{10f,39.9f,31.1f
           },{10f,40.9f,30.9f
           },{10f,41.9f,30.7f
           },{10f,42.9f,30.4f
           },{10f,43.9f,30.3f
           },{10f,44.9f,30f
           },{10f,45.9f,29.8f
           },{10f,46.9f,29.6f
           },{10f,47.9f,29.4f
           },{10f,48.9f,29.2f
           },{10f,49.9f,29f
           },{10f,50.9f,28.8f
           },{10f,51.9f,28.6f
           },{10f,52.9f,28.4f
           },{10f,53.8f,28.3f
           },{10f,54.8f,28.2f
           },{10f,55.8f,28f
           },{10f,57.3f,27.9f
           },{10f,58.3f,27.7f
           },{10f,59.3f,27.4f
           },{11f,00.3f,27.3f
           },{11f,01.3f,27.2f
           },{11f,02.3f,27.1f
           },{11f,03.3f,26.9f
           },{11f,04.3f,26.8f
           },{11f,05.3f,26.7f
           },{11f,06.3f,26.6f
           },{11f,07.3f,26.5f
           },{11f,08.3f,26.4f
           },{11f,09.3f,26.3f
           },{11f,10.3f,26.2f
           },{11f,11.3f,26.1f
           },{11f,12.3f,26f
           },{11f,13.3f,25.9f
           },{11f,14.3f,25.8f
           },{11f,16.3f,25.7f
           },{11f,17.3f,25.6f
           },{11f,18.3f,25.5f
           },{11f,19.3f,25.4f
           },{11f,21.3f,25.3f
           },{11f,23.3f,25.1f
           },{11f,25.3f,24.9f
           },{11f,28.3f,24.8f
           },{11f,30.3f,24.7f
           },{11f,31.3f,24.6f
           },{11f,34.2f,24.5f
           },{11f,35.2f,24.4f
           },{11f,38.2f,24.3f
           },{11f,41.2f,24.2f
           },{11f,42.2f,24.1f
           },{11f,46.2f,24f
           },{11f,47.2f,23.9f
           },{11f,51.2f,23.8f
           },{11f,54.2f,23.7f
           },{11f,56.2f,23.6f
           },{12f,00.2f,23.5f
           },{12f,01.2f,23.4f
           },{12f,05.2f,23.3f
           },{12f,09.2f,23.3f
           },{12f,10.2f,23.2f
           },{12f,12.2f,23.1f
           },{12f,16.1f,23.1f
           },{12f,17.1f,23f
           },{12f,19.1f,22.9f
           },{12f,23.1f,22.8f
           },{12f,27.1f,22.8f
           },{12f,29.1f,22.7f
           },{12f,32.1f,22.6f
           },{12f,36.1f,22.6f
           },{12f,39.1f,22.5f
           },{12f,41.1f,22.4f
           },{12f,49.1f,22.4f
           },{12f,51.1f,22.3f
           },{13f,15.0f,22.3f
           },{13f,16.0f,22.2f
           },{13f,28.0f,22.2f
           },{13f,30.5f,22.1f
           },{13f,31.5f,22.2f
           },{13f,32.5f,22.1f
           },{14f,16.3f,22.1f
           },{14f,17.3f,22f
           },{14f,18.3f,22.1f
           },{14f,22.3f,22f
           },{14f,23.3f,22.1f
           },{14f,24.3f,22f
           },{14f,26.3f,22.1f
           },{14f,27.3f,22f
           },{14f,43.3f,22f
           },{14f,44.3f,21.9f
           },{14f,45.3f,22f
           },{14f,49.3f,21.9f
           },{14f,50.3f,22f
           },{14f,53.3f,21.9f
           },{14f,59.3f,21.9f}};


}
