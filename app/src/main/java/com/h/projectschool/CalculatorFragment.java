package com.h.projectschool;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalculatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Calculator
    private EditText eingabe1;
    private EditText eingabe2;
    private Button addition;
    private Button subtraktion;
    private Button multiplikation;
    private Button division;
    private TextView nachricht;
    private TextView ergebnis;
    double zahl1;
    double zahl2;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatorFragment newInstance(String param1, String param2) {
        CalculatorFragment fragment = new CalculatorFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView= inflater.inflate(R.layout.fragment_calculator,container,false);
        addition = (Button) myView.findViewById(R.id.addition);
        subtraktion = (Button) myView.findViewById(R.id.subraktion);
        multiplikation = (Button) myView.findViewById(R.id.multiplikation);
        division = (Button) myView.findViewById(R.id.division);
        eingabe1 = (EditText) myView.findViewById(R.id.zahl1);
        eingabe2 = (EditText) myView.findViewById(R.id.zahl2);
        nachricht = (TextView) myView.findViewById(R.id.nachricht);
        ergebnis = (TextView) myView.findViewById(R.id.ergebnis);


        addition.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zahl1 = Double.parseDouble(eingabe1.getText().toString());
                zahl2 = Double.parseDouble(eingabe2.getText().toString());

                double ergebnis_zahl = zahl1 + zahl2;

                String erg = String.valueOf(ergebnis_zahl);
                ergebnis.setText(erg);

            }
        });
        subtraktion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zahl1 = Double.parseDouble(eingabe1.getText().toString());
                zahl2 = Double.parseDouble(eingabe2.getText().toString());

                double ergebnis_zahl = zahl1 - zahl2;

                String erg = String.valueOf(ergebnis_zahl);
                ergebnis.setText(erg);

            }
        });
        multiplikation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zahl1 = Double.parseDouble(eingabe1.getText().toString());
                zahl2 = Double.parseDouble(eingabe2.getText().toString());

                double ergebnis_zahl = zahl1 * zahl2;

                String erg = String.valueOf(ergebnis_zahl);
                ergebnis.setText(erg);

            }
        });
        division.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zahl1 = Double.parseDouble(eingabe1.getText().toString());
                zahl2 = Double.parseDouble(eingabe2.getText().toString());

                double ergebnis_zahl = zahl1 / zahl2;

                String erg = String.valueOf(ergebnis_zahl);
                ergebnis.setText(erg);

            }
        });

        // Inflate the layout for this fragment
        return myView;




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

}
