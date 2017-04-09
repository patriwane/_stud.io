package net.pacee.studio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public static final String ARG_QUESTION = "ARG_QUESTION";
    public static final String ARG_RESPONSE = "ARG_RESPONSE";
    String q;
    String a;
    TextView question;
    TextView response;
    public BlankFragment() {
        // Required empty public constructor
    }


    public static BlankFragment newInstance(String question,String response) {
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION, question);
        args.putString(ARG_RESPONSE, response);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        q=getArguments().getString(ARG_QUESTION);
        a=getArguments().getString(ARG_RESPONSE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        question = (TextView)view.findViewById(R.id.tv_fragment_question);
        response = (TextView)view.findViewById(R.id.tv_fragment_response);
        question.setText(q);
        response.setText(a);

        return view;
    }

}
