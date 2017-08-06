package com.smallfe.messaging.clerk;

/**
 * Created by mehmet on 04.08.2017.
 */

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Encryption extends Fragment {

    Button encryptButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View pageView = inflater.inflate(R.layout.tab_encryption, container, false);
        encryptButton = (Button) pageView.findViewById(R.id.encrypt_button);

        encryptButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                EditText plainText = (EditText) getActivity().findViewById(R.id.plaintext);
                TextView encryptedText = (TextView) getActivity().findViewById(R.id.encrypted_text);
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                String encryptedMessage = Utils.encrypt(plainText.getText().toString(),Integer.parseInt(sharedPrefs.getString("magic_number", "1")));
                encryptedText.setText(encryptedMessage);
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Encrypted Message", encryptedMessage);
                clipboard.setPrimaryClip(clip);
            }

        });
        return pageView;
    }

}
