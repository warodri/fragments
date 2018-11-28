package com.example.fragmentss.fragmentsstartingpoint;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentAttachedUsingMyFragmentsTransactor extends Fragment {

    /**
     * Intrerface to talk to the parent Activity
     */
    private IFragmentCommunication mIFragmentCommunication;

    TextView mTxtIncomingMessage;
    String incomingMessage;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Create instance of Interface
        mIFragmentCommunication = (IFragmentCommunication) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            incomingMessage = bundle.getString(getString(R.string.intent_message));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attached_layout, container, false);
        mTxtIncomingMessage = view.findViewById(R.id.txtIncomingMessage);
        if (!incomingMessage.equals("")) {
            mTxtIncomingMessage.setText(
                    "I'm a fragment inserted. Message sent form Activity is: " + incomingMessage);
        }
        return view;
    }

    private void inflateAnotherFragment() {
        /**
         * AnotherFragment f = new AnotherFragment();
         * mIFragmentCommunication.inflateFragment(f, "This is Antoher Fragment");
         */
    }

    private void sendMessageToActivity() {
        mIFragmentCommunication.setMessage("Fragment attached using MyFragmentsTransactor");
    }

}
