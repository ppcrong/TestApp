/*
 * Copyright (c) 2015, Nordic Semiconductor
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.ppcrong.testapp.activity.base;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.ppcrong.testapp.BuildConfig;
import com.ppcrong.testapp.R;

public class AppHelpFragment extends DialogFragment {
    private static final String ARG_TEXT = "ARG_TEXT";
    private static final String ARG_VERSION_NAME = "ARG_VERSION_NAME";
    private static final String ARG_VERSION_CODE = "ARG_VERSION_CODE";

    public static AppHelpFragment getInstance(final int aboutResId) {
        final AppHelpFragment fragment = new AppHelpFragment();

        final Bundle args = new Bundle();
        args.putInt(ARG_TEXT, aboutResId);
        args.putBoolean(ARG_VERSION_NAME, false);
        args.putBoolean(ARG_VERSION_CODE, false);
        fragment.setArguments(args);

        return fragment;
    }

    public static AppHelpFragment getInstance(final int aboutResId, final boolean appendVersionName) {
        final AppHelpFragment fragment = new AppHelpFragment();

        final Bundle args = new Bundle();
        args.putInt(ARG_TEXT, aboutResId);
        args.putBoolean(ARG_VERSION_NAME, appendVersionName);
        args.putBoolean(ARG_VERSION_CODE, false);
        fragment.setArguments(args);

        return fragment;
    }

    public static AppHelpFragment getInstance(final int aboutResId, final boolean appendVersionName,
                                              final boolean appendVersionCode) {
        final AppHelpFragment fragment = new AppHelpFragment();

        final Bundle args = new Bundle();
        args.putInt(ARG_TEXT, aboutResId);
        args.putBoolean(ARG_VERSION_NAME, appendVersionName);
        args.putBoolean(ARG_VERSION_CODE, appendVersionCode);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final Bundle args = getArguments();
        final StringBuilder text = new StringBuilder(getString(args.getInt(ARG_TEXT)));

        final boolean appendVersionName = args.getBoolean(ARG_VERSION_NAME);
        final boolean appendVersionCode = args.getBoolean(ARG_VERSION_CODE);
        if (appendVersionName && appendVersionCode) {
            text.append(getString(R.string.about_version, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
        } else if (appendVersionName) {
            text.append(getString(R.string.about_version2, BuildConfig.VERSION_NAME));
        }
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.about_title).setMessage(text)
                .setPositiveButton(R.string.ok, null).create();
    }
}
