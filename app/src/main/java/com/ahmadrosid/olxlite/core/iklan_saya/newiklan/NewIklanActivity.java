package com.ahmadrosid.olxlite.core.iklan_saya.newiklan;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ahmadrosid.lib.baseapp.core.BaseActivity;
import com.ahmadrosid.lib.baseapp.helper.BaseImageCompresor;
import com.ahmadrosid.lib.baseapp.helper.BaseRecyclerAdapter;
import com.ahmadrosid.olxlite.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nguyenhoanglam.imagepicker.activity.ImagePickerActivity;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.nguyenhoanglam.imagepicker.activity.ImagePicker.create;

public class NewIklanActivity extends BaseActivity implements NewIklanView {

    private static final String TAG = "NewIklanActivity";

    private NewIklanPresenter presenter;
    private String url_photo;
    private ArrayList<Image> images;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    private UploadTask uploadTask;
    private int REQUEST_CODE_PICKER = 3000;
    private ArrayList<String> imagesUrl = new ArrayList<>();

    @BindView(R.id.input_judul) EditText input_judul;
    @BindView(R.id.input_harga) EditText input_harga;
    @BindView(R.id.input_lokasi) EditText input_lokasi;
    @BindView(R.id.input_description) EditText input_description;
    @BindView(R.id.content_image) LinearLayout content_image;
    @BindView(R.id.loader_image) ProgressBar loader_image;
    @BindView(R.id.list_image) RecyclerView list_image;

    @Override protected int getResourceLayout() {
        return R.layout.activity_new_iklan;
    }

    @Override protected void onViewReady(Bundle savedInstanceState) {
        presenter = new NewIklanPresenter(this);
        setOnBackToolbar();
    }

    @OnClick(R.id.btn_pasang_iklan) void clickSubmit() {
        if (validate()) {
            StringBuilder urls_photo = new StringBuilder();
            for (String url : imagesUrl) {
                urls_photo.append(url).append(", ");
            }
            url_photo = urls_photo.toString();
            presenter.postNewIklan(
                    string(input_judul),
                    string(input_harga),
                    string(input_lokasi),
                    string(input_description),
                    url_photo
            );
        }
    }

    private String string(EditText editText) {
        return editText.getText().toString();
    }

    private boolean validate() {
        if (imagesUrl.size() < 0) {
            alert("Please input photo");
        } else if (isEmpty(input_judul)) {
            alert("Please input judul.");
        } else if (isEmpty(input_harga)) {
            alert("Please Input Harga.");
        } else if (isEmpty(input_lokasi)) {
            alert("Please input your location.");
        } else if (isEmpty(input_description)) {
            alert("Please input your description.");
        } else {
            return true;
        }
        return false;
    }

    private boolean isEmpty(EditText input_judul) {
        return TextUtils.isEmpty(input_judul.getText().toString());
    }

    @Override public void startLoading() {
        showLoading();
    }

    @Override public void stopLoading() {
        hideLoading();
    }

    @OnClick(R.id.btn_add_photo) void clickAddNewPhoto() {
        create(this).folderMode(true)
                .folderTitle("Pilih Gambar Iklan")
                .imageTitle("Tap to select")
                .single()
                .multi()
                .limit(10)
                .showCamera(true)
                .imageDirectory("Camera")
                .origin(images)
                .start(REQUEST_CODE_PICKER);
    }

    public void uploadImage(File image) {
        showLoaderImageUploaded();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://olxlite-422b0.appspot.com/");

        Uri file = Uri.fromFile(image);
        StorageReference riversRef = storageRef.child("images/iklan" + presenter.randomString());
        uploadTask = riversRef.putFile(file);

        uploadTask.addOnFailureListener(this::onImageUploadError).addOnSuccessListener(this::onImageUplaodResult);
    }

    private void onImageUplaodResult(UploadTask.TaskSnapshot taskSnapshot) {
        Uri downloadUrl = taskSnapshot.getDownloadUrl();
        if (downloadUrl != null) {
            imagesUrl.add(downloadUrl.toString());
        }
        hideLoaderImageUppload();
    }

    private void onImageUploadError(Exception e) {
        Log.e(TAG, "onImageUploadError: ", e);
        hideLoaderImageUppload();
    }

    private void hideLoaderImageUppload() {
        loader_image.setVisibility(View.GONE);
        list_image.setVisibility(View.VISIBLE);
        loader_image.setVisibility(View.GONE);
    }

    private void showLoaderImageUploaded() {
        content_image.setVisibility(View.VISIBLE);
        list_image.setVisibility(View.GONE);
        loader_image.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICKER && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(ImagePickerActivity.INTENT_EXTRA_SELECTED_IMAGES);
            for (Image image : images) {
                File file = new File(image.getPath());
                File imgCompresed = BaseImageCompresor.pluck().compressImage(this, file);
                uploadImage(imgCompresed);
            }
            setListImage(images);
        }
    }

    private void setListImage(List<Image> data) {
        BaseRecyclerAdapter<Image, ItemHolderImage>
                adapter = new BaseRecyclerAdapter<Image, ItemHolderImage>(data, Image.class,
                R.layout.item_images, ItemHolderImage.class) {
            @Override protected void bindView(ItemHolderImage holder, Image model, int position) {
                holder.onBindView(model.getPath());
            }
        };
        list_image.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list_image.setAdapter(adapter);
    }

    @Override public Activity getActivity() {
        return this;
    }

    @Override public void finishAddNewIklan() {
        finish();
    }
}
