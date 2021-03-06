package com.h.projectschool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoldersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoldersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoldersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FoldersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoldersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoldersFragment newInstance(String param1, String param2) {
        FoldersFragment fragment = new FoldersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getActivity(), "folders create ", Toast.LENGTH_SHORT).show();

        final View v = inflater.inflate(R.layout.fragment_folders, container, false);
        final ListView listView = v.findViewById(R.id.listview_folders);
        listView.setAdapter(new ListViewAdapterFolders(getActivity()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), FolderActivity.class);
                intent.putExtra("folderName", InterDatabase.getDataArray(getActivity(),"folders").get(i));
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, final long id) {

                // Inflate the custom layout/view
                View customView = inflater.inflate(R.layout.delete_popup, null);
                final PopupWindow pwindo = new PopupWindow(customView, 850, 400, true);
                pwindo.showAtLocation(v, Gravity.CENTER, 0, 40);
                //Button btn_closepopup=(Button)layout.findViewById(R.id.btn_closePoppup);
                customView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InterDatabase.remove(getActivity(),"folders",pos);
                        listView.setAdapter(new ListViewAdapterFolders(getActivity()));
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
                return true;
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    File myFile = new File(getFilesDir(), "/Images/abc/");

    private File getFilesDir() {
        return null;
    }
     /*        myFile.mkdirs();
             try {
        for (int i = 0; i < 3; i++) {
            String ab = i + ".png";
            File file = new File(myFile.getAbsolutePath()//folder path
                    +File.separator
                    +ab); //file name
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    } catch (IOException e) {

        e.printStackTrace();
    }*/
}
