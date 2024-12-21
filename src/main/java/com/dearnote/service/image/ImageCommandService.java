package com.dearnote.service.image;

import com.dearnote.domain.Image;
import com.dearnote.domain.Letter;

public interface ImageCommandService {

    Image saveImage(String originFileName, Integer size, String storeFileName, String storeFileUrl, Letter letter);

    void deleteImage(Image image);
}
