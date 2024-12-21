package com.dearnote.service.image;

import com.dearnote.domain.Image;
import com.dearnote.domain.Letter;

public interface ImageQueryService {

    Image getImageByLetter(Letter letter);

    Image getImage(Long imageId);
}
