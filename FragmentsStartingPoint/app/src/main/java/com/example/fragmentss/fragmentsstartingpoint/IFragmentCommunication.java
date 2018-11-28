package com.example.fragmentss.fragmentsstartingpoint;

import android.support.v4.app.Fragment;

public interface IFragmentCommunication {

    public void setMessage(String textFromFragment);
    public void inflateFragment(Fragment f, String fragmentTitle, boolean addToBackStack, String message);

}
