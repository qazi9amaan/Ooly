package com.oolysolutions.oolys.Act.Verification;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.oolysolutions.oolys.R;
import com.oolysolutions.oolys.databinding.PhoneNumberVerifiyBottomSheetBinding;

import java.lang.reflect.Field;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class PhoneNumberVerifyBottomSheet extends BottomSheetDialogFragment {

    Context context;
    Activity activity;
    onVerificationComplete onVerificationComplete;

    PhoneNumberVerifiyBottomSheetBinding binding;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    FirebaseAuth mAuth;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    private String currentUserPhoneNumber;

    public PhoneNumberVerifyBottomSheet(Context context,Activity activity,onVerificationComplete onVerificationComplete) {
        this.context = context;
        this.activity = activity;
        this.onVerificationComplete = onVerificationComplete;
    }

    boolean isverify = false;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.phone_number_verifiy_bottom_sheet,
                container, false);

        binding = PhoneNumberVerifiyBottomSheetBinding.bind(v);

        binding.txtPinEntry.setMaxLength(10);
        binding.txtPinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
               if(str.length() == 10 && !isverify){
                   binding.sendCode.setEnabled(true);
               }
            }
        });

        binding.otpVerify.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                if(str.length() == 6 && isverify){
                    binding.sendCode.setEnabled(true);
                }
            }
        });

        FirebaseApp.initializeApp(context);
        mAuth = FirebaseAuth.getInstance();

        binding.sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mview) {
                binding.sendCode.setEnabled(false);
                binding.sendCode.setText("");
                binding.progessbar.setVisibility(View.VISIBLE);

               if(!isverify){
                   View view = getActivity().getCurrentFocus();
                   if (view != null) {
                       InputMethodManager manager
                               = (InputMethodManager)
                               context.getSystemService(
                                       Context.INPUT_METHOD_SERVICE);
                       manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                   }
                   currentUserPhoneNumber = "+91"+binding.txtPinEntry.getText().toString();
                   PhoneAuthOptions options =
                           PhoneAuthOptions.newBuilder(mAuth)
                                   .setPhoneNumber(currentUserPhoneNumber)
                                   .setTimeout(60L, TimeUnit.SECONDS)
                                   .setActivity(activity)
                                   .setCallbacks(mCallbacks)
                                   .build();
                   PhoneAuthProvider.verifyPhoneNumber(options);
               }else{
                   PhoneAuthCredential credential =
                           PhoneAuthProvider.getCredential(mVerificationId, binding.otpVerify.getText().toString());
                   signInWithPhoneAuthCredential(credential);
               }
            }
        });



        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(context, "Too many requests"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;

                binding.sendCode.setEnabled(false);
                binding.sendCode.setText("Verify code");
                binding.progessbar.setVisibility(View.GONE);
                binding.txtPinEntry.setText("");
                binding.txtPinEntry.setMaxLength(6);

                binding.resendView.setVisibility(View.VISIBLE);

                binding.heading.setText("enter the\nverification code");
                binding.body.setText("You'd have received a verification code on"+currentUserPhoneNumber);
                isverify = true;

                binding.otpVerify.setVisibility(View.VISIBLE);
                binding.txtPinEntry.setVisibility(View.GONE);
            }
        };


        binding.resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendVerificationCode(currentUserPhoneNumber,mResendToken);
                binding.resendBtn.setVisibility(View.GONE);
            }
        });
        return v;
    }
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                activity,
                mCallbacks,
                token);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String currentuserCode = String.valueOf(System.currentTimeMillis());
                            SharedPreferences userSharedPreferences = context.getSharedPreferences("UserSharedPreferences", MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = userSharedPreferences.edit();
                            if(currentUserPhoneNumber !=null){
                                myEdit.putString("CurrentUserPhoneNumber", String.valueOf(currentUserPhoneNumber));
                                myEdit.putString("CurrentUserId", currentuserCode);
                            }
                            myEdit.commit();

                            FirebaseMessaging.getInstance().getToken()
                                    .addOnCompleteListener(new OnCompleteListener<String>() {
                                        @Override
                                        public void onComplete(@NonNull Task<String> task) {
                                            if (!task.isSuccessful()) {return;}
                                            String token = task.getResult();
                                            FirebaseDatabase.getInstance().getReference()
                                                    .child("Users")
                                                    .child("Consumers")
                                                    .child(currentuserCode).child("Token").setValue(token);
                                        }
                                    });

                            onVerificationComplete.onComplete();
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(context, "Invalid verification code", Toast.LENGTH_SHORT).show();
                            }
                            dismiss();
                        }
                    }
                });
    }



    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
        bottomSheetDialog.setContentView(R.layout.phone_number_verifiy_bottom_sheet);
        bottomSheetDialog.setCanceledOnTouchOutside(false);


        try {
            Field behaviorField = bottomSheetDialog.getClass().getDeclaredField("behavior");
            behaviorField.setAccessible(true);
            final BottomSheetBehavior behavior = (BottomSheetBehavior) behaviorField.get(bottomSheetDialog);

            behavior.setHideable(false);


            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING){
                        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public interface onVerificationComplete{
        public void onComplete();
    }
}
