package com.dih.animepedia.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("About Us \n ▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬ \n- M. Irfan Ardiansyah / 1917051034 \n- Dota Ningtias / 1917051002 \n- Hans Christian Herwanto / 1917051022");
    }

    public LiveData<String> getText() {
        return mText;
    }
}