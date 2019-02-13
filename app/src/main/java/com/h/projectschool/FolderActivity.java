package com.h.projectschool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FolderActivity extends AppCompatActivity {
    private String folderName;
    private ListView listView;
    private ListViewAdapterImages listViewAdapterImages;
    private ArrayList<File> listOfImages;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        folderName = getIntent().getStringExtra("folderName");
        File f_root = new File(getApplicationContext().getFilesDir(), "SchoolAppData");
        if (!f_root.exists()) {
            f_root.mkdirs();
        }
        File f = new File(getApplicationContext().getFilesDir() + "/SchoolAppData", folderName);

        if (!f.exists()) {
            f.mkdirs();
        }
        listOfImages = new ArrayList<>();
        listOfImages.addAll(Arrays.asList(f.listFiles()));

        activity = this;

        listViewAdapterImages = new ListViewAdapterImages(this, listOfImages);
        listView = findViewById(R.id.images_listview);
        listView.setAdapter(listViewAdapterImages);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.delete_popup, null, false);
                PopupWindow pw = new PopupWindow(customView, 850, 400, true);
                final PopupWindow pwindo = new PopupWindow(customView, 850, 400, true);
                pwindo.showAtLocation(customView, Gravity.CENTER, 0, 40);
                //Button btn_closepopup=(Button)layout.findViewById(R.id.btn_closePoppup);
                customView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listOfImages.get(i).delete();
                        listOfImages.remove(i);
                        listViewAdapterImages.updateList(listOfImages);

                        if (pwindo.isShowing()) {
                            pwindo.dismiss();
                        }
                    }
                });
                customView.findViewById(R.id.cancle_delete_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (pwindo.isShowing()) {
                            pwindo.dismiss();
                        }
                    }
                });
                return false;
            }
        });


    }


    public void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Get ImageURi and load with help of picasso
        //Uri selectedImageURI = data.getData();
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                File selectedFile = new File(uri.getPath());
                File destinationFile = new File(getApplicationContext().getFilesDir() + "/SchoolAppData/" + folderName, selectedFile.getName() + "." + getFileExtension(selectedFile));

                try {
                    InputStream inputStream = getContentResolver()
                            .openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            destinationFile);
                    copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                listOfImages.add(destinationFile);
                listViewAdapterImages.updateList(listOfImages);
            }
        }
    }

    public static void copyStream(InputStream input, OutputStream output)
            throws IOException {

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }


    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "jpg"; // empty extension
        }
        return name.substring(lastIndexOf);
    }


    public static void copy(File src, File dst) throws IOException {
        try (InputStream in = new FileInputStream(src)) {
            try (OutputStream out = new FileOutputStream(dst)) {
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
        }
    }

    public void selectImage(View view) {
        pickImage();
    }
}
