package com.example.dummyproject.presentation.util.image_picker;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import pub.devrel.easypermissions.EasyPermissions;


public class DialogImageAttachment {
    public Activity activity;
    public Fragment fragment;
    private ImageAttachentCallback callback;
    String mCurrentPhotoPath;
    public static final int WRITE_PERMISSION = 10001;
    public static final int PICK_IMAGE_CAMERA = 111;
    public static final int PICK_IMAGE_GALLERY = 122;
    public static final int PICK_DOCUMENT = 100012;
    public static final int Profile = 100011;
    private Integer type;
    Boolean isPdf = false;

    public DialogImageAttachment(Activity activity) {
        this.activity = activity;
    }

    public DialogImageAttachment(Activity activity, Fragment fragment) {
        this.fragment = fragment;
        this.activity = activity;
    }

    public DialogImageAttachment(Activity activity, Fragment fragment, Boolean isPdf) {
        this.fragment = fragment;
        this.activity = activity;
        this.isPdf = isPdf;
    }

    public DialogImageAttachment setType(int type) {
        this.type = type;
        return this;
    }

    public static DialogImageAttachment make(Activity activity) {
        DialogImageAttachment dialogImageAttachment = new DialogImageAttachment(activity);
        return dialogImageAttachment;
    }

    public static DialogImageAttachment make(Activity activity, Fragment fragment, Boolean isPdf) {
        DialogImageAttachment dialogImageAttachment = new DialogImageAttachment(activity, fragment, isPdf);
        return dialogImageAttachment;
    }

    public static DialogImageAttachment make(Activity activity, Fragment fragment) {
        DialogImageAttachment dialogImageAttachment = new DialogImageAttachment(activity, fragment);
        return dialogImageAttachment;
    }

    public DialogImageAttachment show() {
        selectImage();
        return this;
    }

    public DialogImageAttachment setCallback(ImageAttachentCallback callback) {
        this.callback = callback;
        return this;
    }


    private void selectImage() {
        if (callback == null) {
            return;
        }
        if (isPdf) {
            try {
                if (EasyPermissions.hasPermissions(activity, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    final CharSequence[] options = {"Take Photo", "Choose From Gallery","Upload Document" , "Cancel"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Select Option");
                    builder.setItems(options, (dialog, item) -> {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            dispatchTakePictureIntent();
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            callback.onClick(pickPhoto, PICK_IMAGE_GALLERY);
                        }else if (options[item].equals("Upload Document")) {
                            dialog.dismiss();
                           /* Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            callback.onClick(pickPhoto, PICK_IMAGE_GALLERY);*/

                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("application/pdf");
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            callback.onClick(intent, PICK_DOCUMENT);


                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, WRITE_PERMISSION);
                }
            } catch (Exception e) {
//            Notify.INSTANCE.Toast("Camera Permission error");
                e.printStackTrace();
            }
        } else {
            try {
                if (EasyPermissions.hasPermissions(activity, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Select Option");
                    builder.setItems(options, (dialog, item) -> {
                        if (options[item].equals("Take Photo")) {
                            dialog.dismiss();
                            dispatchTakePictureIntent();
                        } else if (options[item].equals("Choose From Gallery")) {
                            dialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            callback.onClick(pickPhoto, PICK_IMAGE_GALLERY);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, WRITE_PERMISSION);
                }
            } catch (Exception e) {
//            Notify.INSTANCE.Toast("Camera Permission error");
                e.printStackTrace();
            }
        }

    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        try {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(activity,
                        activity.getPackageName() + ".provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                callback.onClick(takePictureIntent, PICK_IMAGE_CAMERA);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

   /* public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (callback != null) {
            if (resultCode == RESULT_OK) {
                if (requestCode == UCrop.REQUEST_CROP) {

                } else if (requestCode == PICK_IMAGE_GALLERY) {
                    if (data != null) {
                        try {
                            Uri pickedImage = data.getData();
                            String[] filePath = {MediaStore.Images.Media.DATA};
                            Cursor cursor = activity.getContentResolver().query(pickedImage, filePath, null, null, null);
                            cursor.moveToFirst();
                            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
                            File file1 = new File(imagePath);
                            UCrop.of(pickedImage, Uri.fromFile(createImageFile()))
                                    .withAspectRatio(16, 9)
                                    .start(activity);
                       *//* new ImageCompressionAsyncTask(activity).execute(mCurrentPhotoPath,
                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Silicompressor/images");
                       *//*
                            Bitmap bitmap = MediaStore.Images.Media
                                    .getBitmap(activity.getContentResolver(), FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file1));
                            *//* Bitmap bitmap = (Bitmap) data.getExtras().get("data");*//*
                            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                            File compressesdFile = null;

                            compressesdFile = new Compressor(activity)
                                    .setMaxWidth(bitmap.getWidth() / 2)
                                    .setMaxHeight(bitmap.getHeight() / 2)
                                    .setQuality(100)
                                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/compressor/images")
                                    .compressToFile(file1);
                            callback.onSuccess(compressesdFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onFailure(e.getMessage());
                        }
                    }
                } else if (requestCode == PICK_IMAGE_CAMERA) {
                    try {
                        File file1 = new File(mCurrentPhotoPath);
                       *//* new ImageCompressionAsyncTask(activity).execute(mCurrentPhotoPath,
                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Silicompressor/images");
                       *//*
                        Bitmap bitmap = MediaStore.Images.Media
                                .getBitmap(activity.getContentResolver(), FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file1));
                        *//* Bitmap bitmap = (Bitmap) data.getExtras().get("data");*//*
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                        File compressesdFile = null;

                        compressesdFile = new Compressor(activity)
                                .setMaxWidth(bitmap.getWidth() / 2)
                                .setMaxHeight(bitmap.getHeight() / 2)
                                .setQuality(100)
                                .setCompressFormat(Bitmap.CompressFormat.JPEG)
                                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/compressor/images")
                                .compressToFile(file1);
                        callback.onSuccess(compressesdFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onFailure(e.getMessage());
                    }
                }
            }
        } else {
            Notify.INSTANCE.Toast("ImageAttachmentCallback is null.Please set callback");
        }
    }*/

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (callback != null) {
            if (resultCode == RESULT_OK) {
                if (requestCode == UCrop.REQUEST_CROP) {
                    final Uri resultUri = UCrop.getOutput(data);
                    callback.onSuccess(new File(resultUri.getPath()));
                } else if (requestCode == PICK_IMAGE_GALLERY) {
                    if (data != null) {
                        try {
                            Uri pickedImage = data.getData();
                            cropImage(pickedImage);
                            // callback.onSuccess(compressesdFile);
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onFailure(e.getMessage());
                        }
                    }
                }else if (requestCode == PICK_DOCUMENT) {
                    if (data != null) {
                        try {
                            Uri pickedDocument = data.getData();
                             callback.onSuccess(new File(pickedDocument.getPath()));
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onFailure(e.getMessage());
                        }
                    }
                } else if (requestCode == PICK_IMAGE_CAMERA) {
                    try {
                        File file1 = new File(mCurrentPhotoPath);
                        cropImage(FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file1));
                        //  callback.onSuccess(compressesdFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onFailure(e.getMessage());
                    }
                }
            } else if (resultCode == RESULT_CANCELED) {
                //setResultCancelled();
            }
        } else {
//            Notify.INSTANCE.Toast("ImageAttachmentCallback is null.Please set callback");
        }
    }

    private static String queryName(ContentResolver resolver, Uri uri) {
        Cursor returnCursor =
                resolver.query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }

    private void setResultCancelled() {
//        Notify.INSTANCE.Toast("Something goes wrong");
    }

    private void cropImage(Uri sourceUri) {
        //    Uri destinationUri = Uri.fromFile(new File(activity.getCacheDir(), queryName(activity.getContentResolver(), sourceUri)));
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(80);

        // applying UI theme
     /*   options.setToolbarColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        options.setActiveWidgetColor(ContextCompat.getColor(activity, R.color.colorPrimary));*/
       /* if (type != null && type == Profile) {
            options.withAspectRatio(16, 16);
        } else
            options.withAspectRatio(16, 9);*/

        Uri uri;
        try {
            uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", createImageFile());
        } catch (Exception e) {
            uri = getCacheImagePath("cacheImage");
        }
        if (fragment == null) {
            try {
                UCrop.of(sourceUri, Uri.fromFile(createImageFile()))
                        .withOptions(options)
                        .start(activity);
            } catch (IOException e) {
                e.printStackTrace();
//                Notify.INSTANCE.Toast(e.getMessage());
            }
        } else {
            try {
                UCrop.of(sourceUri, Uri.fromFile(createImageFile()))
                        .withOptions(options)
                        .start(activity, fragment);
            } catch (IOException e) {
                e.printStackTrace();
//                Notify.INSTANCE.Toast(e.getMessage());
            }
        }

    }

    private Uri getCacheImagePath(String fileName) {
        File path = new File(activity.getExternalCacheDir(), "crop");
        if (!path.exists()) path.mkdirs();
        File image = new File(path, fileName);
        return FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", image);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == WRITE_PERMISSION) {
            if (grantResults.length > 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
            ) {
                selectImage();
            }
        }

    }
}