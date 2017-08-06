package com.smallfe.messaging.clerk;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mehmet on 04.08.2017.
 */

public class Decryption extends Fragment {

    Button decryptButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View pageView = inflater.inflate(R.layout.tab_decryption, container, false);
        decryptButton = (Button) pageView.findViewById(R.id.decrypt_button);

        decryptButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                EditText cypherText = (EditText) getActivity().findViewById(R.id.cyphertext);
                TextView plainText = (TextView) getActivity().findViewById(R.id.decrypted_text);
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                String plainMessage = Utils.decrypt(cypherText.getText().toString(),Integer.parseInt(sharedPrefs.getString("magic_number", "1")));
                plainText.setText(plainMessage);
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Plain Message", plainMessage);
                clipboard.setPrimaryClip(clip);
            }

        });
        return pageView;
    }

}
